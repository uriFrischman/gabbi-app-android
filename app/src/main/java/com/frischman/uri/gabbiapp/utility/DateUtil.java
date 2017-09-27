package com.frischman.uri.gabbiapp.utility;

import android.text.format.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    public static Date getDateFromString(String dateString, String dateFormat) {
        DateFormat format = new SimpleDateFormat(dateFormat, Locale.US);
        Date date = null;
        try {
            date = format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static boolean isDateToday(Date date) {
        return new DateUtils().isToday(date.getTime());
    }

    public static boolean isDateAfterToday(Date date) {
        Date today = new Date();
        return date.after(today);
    }
}
