package com.frischman.uri.gabbiapp.utility;


import android.view.View;

public class ViewUtil {

    public static void hideOrShow(View view) {
        if (view.getVisibility() == View.VISIBLE) {
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.VISIBLE);
        }
    }
}
