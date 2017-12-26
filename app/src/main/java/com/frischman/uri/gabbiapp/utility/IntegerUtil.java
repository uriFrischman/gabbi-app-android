package com.frischman.uri.gabbiapp.utility;


import java.util.ArrayList;

public class IntegerUtil {

    public boolean intToBoolean(int integer) {
        return integer != 0;
    }

    public static ArrayList<Integer> getRange(int begin, int end) {
        ArrayList<Integer> range = new ArrayList<>();
        for (int i = begin; i <= end; i++) {
            range.add(i);
        }
        return range;
    }
}
