package com.frischman.uri.gabbiapp.utility;


import android.content.Context;
import android.graphics.Typeface;

public class FontUtil {

    public static Typeface createFontFromAssets(Context context, String fontFileName) {
        return Typeface.createFromAsset(context.getAssets(), String.format("fonts/%s", fontFileName));
    }
}
