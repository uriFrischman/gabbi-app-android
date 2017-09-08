package com.frischman.uri.gabbiapp.utility;

import android.content.Context;

import com.snappydb.DB;
import com.snappydb.DBFactory;
import com.snappydb.SnappydbException;


public class SnappyDBUtil {

    private static Context mContext;

    public SnappyDBUtil(Context context) {
        mContext = context;
    }

    public static DB getDBWithName(String name) {
        try {
            return DBFactory.open(mContext, name);
        } catch (SnappydbException e) {
            e.printStackTrace();
            return null;
        }
    }
}
