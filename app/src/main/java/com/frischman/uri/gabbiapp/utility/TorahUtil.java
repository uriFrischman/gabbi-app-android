package com.frischman.uri.gabbiapp.utility;

import android.util.Log;

import com.frischman.uri.gabbiapp.R;
import com.frischman.uri.gabbiapp.model.Sefer;
import com.google.gson.Gson;
import com.snappydb.DB;
import com.snappydb.SnappydbException;

import java.util.ArrayList;
import java.util.List;

import static com.frischman.uri.gabbiapp.utility.FileUtil.rawFileToString;
import static com.frischman.uri.gabbiapp.utility.SnappyDBUtil.getDBWithName;
import static com.frischman.uri.gabbiapp.utility.StringUtil.getString;

public class TorahUtil {

    private static final String TAG = "TorahUtil";

    public static void addTorahToSnappyDB() {
        String bereishit = rawFileToString(R.raw.bereishit);
        String shemot = rawFileToString(R.raw.shemot);
        String vayikra = rawFileToString(R.raw.vayikra);
        String bamidbar = rawFileToString(R.raw.bamidbar);
        String devarim = rawFileToString(R.raw.devarim);

        Gson gson = new Gson();

        Sefer seferBereishit = gson.fromJson(bereishit, Sefer.class);
        Sefer seferShemot = gson.fromJson(shemot, Sefer.class);
        Sefer seferVayikra = gson.fromJson(vayikra, Sefer.class);
        Sefer seferBamidbar = gson.fromJson(bamidbar, Sefer.class);
        Sefer seferDevarim = gson.fromJson(devarim, Sefer.class);

        DB torahDatabse = getDBWithName(getString(R.string.database_name_torah));

        try {
            torahDatabse.put(getString(R.string.database_key_bereishit), seferBereishit);
            torahDatabse.put(getString(R.string.database_key_shemot), seferShemot);
            torahDatabse.put(getString(R.string.database_key_vayikra), seferVayikra);
            torahDatabse.put(getString(R.string.database_key_bamidbar), seferBamidbar);
            torahDatabse.put(getString(R.string.database_key_devarim), seferDevarim);
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
    }

    public static String getPasuk(String seferKey, int perek, int pasuk) {
        try {
            return getDBWithName(getString(R.string.database_name_torah)).getObject(seferKey, Sefer.class).text.get(perek - 1).get(pasuk - 1).toString();
        } catch (SnappydbException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<String> getPerek(String seferKey, int perek) {
        try {
            return getDBWithName(getString(R.string.database_name_torah)).getObject(seferKey, Sefer.class).text.get(perek - 1);
        } catch (SnappydbException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int getPerekLength(String seferKey, int perek) {
        return getPerek(seferKey, perek).size();
    }

    public static List<List<String>> getRangeOfText(String seferKey, int fromPerek, int fromPasuk, int toPerek, int toPasuk) {
        List<List<String>> listToReturn = new ArrayList<>();
        if (fromPerek == toPerek) {
            listToReturn.add(getPsukimFromPerek(seferKey, fromPerek, fromPasuk, toPasuk));
        } else if ((toPerek - fromPerek) == 1) {
            List<String> listOfFromPsukim = getPsukimFromPerek(seferKey, fromPerek, fromPasuk, getPerekLength(seferKey, fromPerek));
            List<String> listOfToPsukim = getPsukimFromPerek(seferKey, toPerek, 1, toPasuk);

            listToReturn.add(listOfFromPsukim);
            listToReturn.add(listOfToPsukim);
        } else {
            int fullPerekStart = fromPerek + 1;
            int fullPerekEnd = toPerek - 1;

            Log.d(TAG, String.format("%d %d", fullPerekStart, fullPerekEnd));

            List<String> listOfFromIncompletePerekPsukim = getPsukimFromPerek(seferKey, fromPerek, fromPasuk, getPerekLength(seferKey, fromPerek));
            listToReturn.add(listOfFromIncompletePerekPsukim);

            for (int currentPerek = fullPerekStart; currentPerek <= fullPerekEnd; currentPerek++) {
                listToReturn.add(getPerek(seferKey, currentPerek));
                Log.d(TAG, getPerek(seferKey, currentPerek).toString());
            }

            List<String> listOfToIncompletePerekPsukim = getPsukimFromPerek(seferKey, toPerek, 1, toPasuk);
            listToReturn.add(listOfToIncompletePerekPsukim);
        }
        return listToReturn;
    }

    private static List<String> getPsukimFromPerek(String seferKey, int perek, int fromPasuk, int toPasuk) {
        List<String> listToReturn = new ArrayList<>();
        for (int currentPasuk = fromPasuk; currentPasuk <= toPasuk; currentPasuk++) {
            listToReturn.add(getPasuk(seferKey, perek, currentPasuk));
        }
        return listToReturn;
    }

    public static int getSeferLength(String seferKey) {
        try {
            return getDBWithName(getString(R.string.database_name_torah)).getObject(seferKey, Sefer.class).text.size();
        } catch (SnappydbException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
