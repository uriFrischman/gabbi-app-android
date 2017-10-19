package com.frischman.uri.gabbiapp.utility;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class FragmentUtil {

    public static void replaceViewWithFragment(FragmentManager fragmentManager, int viewId, Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = fragmentManager.beginTransaction().replace(viewId, fragment);
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    public static void removeFragmentFromView(FragmentManager fragmentManager, int viewId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction().remove(fragmentManager.findFragmentById(viewId));
        transaction.commit();
    }

    public static boolean checkIfViewHasFragment(FragmentManager fragmentManager, int viewId) {
        if (fragmentManager.findFragmentById(viewId) == null) {
            return false;
        } else {
            return true;
        }
    }
}
