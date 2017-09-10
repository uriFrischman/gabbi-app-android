package com.frischman.uri.gabbiapp.utility;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    public static Date getDateFromString(String dateString, String dateFormat) {
        DateFormat format = new SimpleDateFormat(dateFormat, Locale.CANADA);
        Date date = null;
        try {
            date = format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
