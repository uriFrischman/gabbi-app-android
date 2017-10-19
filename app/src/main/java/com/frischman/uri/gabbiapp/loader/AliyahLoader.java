package com.frischman.uri.gabbiapp.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.frischman.uri.gabbiapp.model.Aliyah;

import java.util.List;

import static com.frischman.uri.gabbiapp.utility.AliyahUtil.getAllAliyahsFromEvent;

public class AliyahLoader extends AsyncTaskLoader<List<Aliyah>> {

    private static final String TAG = "AliyahLoader";
    private String mEventName;

    public AliyahLoader(Context context, String eventName) {
        super(context);
        mEventName = eventName;
    }

    @Override
    public List<Aliyah> loadInBackground() {
        return getAllAliyahsFromEvent(mEventName);
    }

    public String getEventName() {
        return mEventName;
    }
}
