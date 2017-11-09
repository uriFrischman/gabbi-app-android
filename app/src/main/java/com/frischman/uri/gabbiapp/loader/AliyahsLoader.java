package com.frischman.uri.gabbiapp.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.frischman.uri.gabbiapp.model.Aliyah;

import java.util.List;

import static com.frischman.uri.gabbiapp.utility.AliyahUtil.getAllAliyahsFromEvent;

public class AliyahsLoader extends AsyncTaskLoader<List<Aliyah>> {

    private String mEventName;

    public AliyahsLoader(Context context, String eventName) {
        super(context);
        mEventName = eventName;
    }

    @Override
    public List<Aliyah> loadInBackground() {
        return getAllAliyahsFromEvent(mEventName);
    }
}
