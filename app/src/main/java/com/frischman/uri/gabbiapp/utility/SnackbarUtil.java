package com.frischman.uri.gabbiapp.utility;


import android.support.design.widget.Snackbar;
import android.view.View;

public class SnackbarUtil {

    public static void showSnackbar(View view, int text, int duration) {
        Snackbar snackbar = Snackbar.make(view, text, duration);
        snackbar.show();
    }
}
