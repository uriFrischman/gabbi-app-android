package com.frischman.uri.gabbiapp.utility;


import android.content.Intent;

import com.frischman.uri.gabbiapp.ui.activity.GabbiAppBaseActivity;
import com.frischman.uri.gabbiapp.ui.activity.GetTextActivity;

import static com.frischman.uri.gabbiapp.ui.activity.GetTextActivity.EXTRA_GET_TEXT_READING;

public class NavigationUtil {

    public static void goToGetTextActivityWithReading(GabbiAppBaseActivity activity, String reading) {
        Intent intent = new Intent(activity, GetTextActivity.class);
        intent.putExtra(EXTRA_GET_TEXT_READING, reading);
        activity.startActivity(intent);
    }
}
