package com.frischman.uri.gabbiapp.utility;

import android.content.Context;

public class StringUtil {

    private static Context mContext;

    public StringUtil(Context context) {
        this.mContext = context;
    }

    public static String getString(int id) {
        return mContext.getString(id);
    }
}
