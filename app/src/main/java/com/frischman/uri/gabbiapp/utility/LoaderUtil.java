package com.frischman.uri.gabbiapp.utility;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;

public class LoaderUtil {

    public static void restartLoader(Fragment fragment, int loaderId, Bundle args, LoaderManager.LoaderCallbacks callback) {
        fragment.getLoaderManager().restartLoader(loaderId, args, callback).forceLoad();
    }
}
