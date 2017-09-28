package com.frischman.uri.gabbiapp.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.frischman.uri.gabbiapp.EventRecyclerViewAdapter;
import com.frischman.uri.gabbiapp.R;
import com.frischman.uri.gabbiapp.databinding.ActivityMainBinding;
import com.frischman.uri.gabbiapp.model.Aliyah;
import com.frischman.uri.gabbiapp.model.Event;

import java.util.List;

import static com.frischman.uri.gabbiapp.GabbiApp.loadRunnableOnToMainLooper;
import static com.frischman.uri.gabbiapp.utility.AliyahUtil.getAllAliyahsFromEvent;
import static com.frischman.uri.gabbiapp.utility.EventUtil.getAllEvents;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Event>> {

    private static final String TAG = "MainActivity";
    private Context mContext = this;
    private EventRecyclerViewAdapter mEventRecyclerViewAdapter;
    private RecyclerView mRecyclerView;
    private EventRecyclerViewAdapter.ItemClickListener mItemClickListener;
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mItemClickListener = new EventRecyclerViewAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                final String eventName = mEventRecyclerViewAdapter.getItem(position).getEventName();
                Runnable x = new Runnable() {
                    @Override
                    public void run() {
                        final List<Aliyah> listOfAliyahs = getAllAliyahsFromEvent(eventName);
                        Runnable x = new Runnable() {
                            @Override
                            public void run() {
                                new AlertDialog.Builder(mContext)
                                        .setTitle(listOfAliyahs.get(1).getAliyahName())
                                        .setMessage(listOfAliyahs.get(1).toString())
                                        .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        }).create().show();
                            }
                        };
                        loadRunnableOnToMainLooper(x);
                    }
                };
                Thread myThread = new Thread(x);
                myThread.start();
            }
        };

        mEventRecyclerViewAdapter = new EventRecyclerViewAdapter(mContext, null);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewListOfEvents);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(mEventRecyclerViewAdapter);
        mEventRecyclerViewAdapter.setItemClickListener(mItemClickListener);
        getSupportLoaderManager().initLoader(0, null, this).forceLoad();

    }

    @Override
    public Loader<List<Event>> onCreateLoader(int id, Bundle args) {
        return new FetchData(mContext);
    }

    @Override
    public void onLoadFinished(Loader<List<Event>> loader, List<Event> data) {
        mEventRecyclerViewAdapter.addListOfEvents(data);
        Log.d("data", data.toString());
    }

    @Override
    public void onLoaderReset(Loader<List<Event>> loader) {

    }

    public static class FetchData extends AsyncTaskLoader<List<Event>> {

        public FetchData(Context context) {
            super(context);
        }

        @Override
        public List<Event> loadInBackground() {
            Log.d("loadInBackground", "reached");
            return getAllEvents(false);
        }

        @Override
        public void deliverResult(List<Event> data) {
            super.deliverResult(data);
        }
    }
}
