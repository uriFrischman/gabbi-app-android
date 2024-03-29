package com.frischman.uri.gabbiapp.utility;

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


    public static final int MAX_NUMBER_OF_PERAKIM_IN_TORAH = 50;
    public static final int MAX_NUMBER_OF_PSUKIM_IN_PEREK_IN_TORAH = 69;
    public static final String[] SEFERS = {"Genesis", "Exodus", "Leviticus", "Numbers", "Deuteronomy"};

    public static void addTorahToSnappyDB() {
        String bereishit = rawFileToString(R.raw.bereishit);
        String shemot = rawFileToString(R.raw.shemot);
        String vayikra = rawFileToString(R.raw.vayikra);
        String bamidbar = rawFileToString(R.raw.bamidbar);
        String devarim = rawFileToString(R.raw.devarim);

        String bereishitNoVowels = rawFileToString(R.raw.bereishit_no_vowels);
        String shemotNoVowels = rawFileToString(R.raw.shemot_no_vowels);
        String vayikraNoVowels = rawFileToString(R.raw.vayikra_no_vowels);
        String bamidbarNoVowels = rawFileToString(R.raw.bamidbar_no_vowels);
        String devarimNoVowels = rawFileToString(R.raw.devarim_no_vowels);

        Gson gson = new Gson();

        Sefer seferBereishit = gson.fromJson(bereishit, Sefer.class);
        Sefer seferShemot = gson.fromJson(shemot, Sefer.class);
        Sefer seferVayikra = gson.fromJson(vayikra, Sefer.class);
        Sefer seferBamidbar = gson.fromJson(bamidbar, Sefer.class);
        Sefer seferDevarim = gson.fromJson(devarim, Sefer.class);

        Sefer seferBereishitNoVowels = gson.fromJson(bereishitNoVowels, Sefer.class);
        Sefer seferShemotNoVowels = gson.fromJson(shemotNoVowels, Sefer.class);
        Sefer seferVayikraNoVowels = gson.fromJson(vayikraNoVowels, Sefer.class);
        Sefer seferBamidbarNoVowels = gson.fromJson(bamidbarNoVowels, Sefer.class);
        Sefer seferDevarimNoVowels = gson.fromJson(devarimNoVowels, Sefer.class);

        DB torahDatabse = getDBWithName(getString(R.string.database_name_torah));

        try {
            torahDatabse.put(getString(R.string.database_key_bereishit), seferBereishit);
            torahDatabse.put(getString(R.string.database_key_shemot), seferShemot);
            torahDatabse.put(getString(R.string.database_key_vayikra), seferVayikra);
            torahDatabse.put(getString(R.string.database_key_bamidbar), seferBamidbar);
            torahDatabse.put(getString(R.string.database_key_devarim), seferDevarim);

            torahDatabse.put(getString(R.string.database_key_bereishit_no_vowels), seferBereishitNoVowels);
            torahDatabse.put(getString(R.string.database_key_shemot_no_vowels), seferShemotNoVowels);
            torahDatabse.put(getString(R.string.database_key_vayikra_no_vowels), seferVayikraNoVowels);
            torahDatabse.put(getString(R.string.database_key_bamidbar_no_vowels), seferBamidbarNoVowels);
            torahDatabse.put(getString(R.string.database_key_devarim_no_vowels), seferDevarimNoVowels);
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
    }

    private static String getPasuk(String seferKey, int perek, int pasuk) {
        try {
            return getDBWithName(getString(R.string.database_name_torah)).getObject(seferKey, Sefer.class).text.get(perek - 1).get(pasuk - 1);
        } catch (SnappydbException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static List<String> getPerek(String seferKey, int perek) {
        try {
            return getDBWithName(getString(R.string.database_name_torah)).getObject(seferKey, Sefer.class).text.get(perek - 1);
        } catch (SnappydbException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static int getPerekLength(String seferKey, int perek) {
        return getPerek(seferKey, perek).size();
    }

    public static List<List<String>> getRangeOfText(String seferKey, int fromPerek, int fromPasuk, int toPerek, int toPasuk) {

        if (!isValidRangeOfText(seferKey, fromPerek, fromPasuk, toPerek, toPasuk)) {
            return null;
        }

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

            List<String> listOfFromIncompletePerekPsukim = getPsukimFromPerek(seferKey, fromPerek, fromPasuk, getPerekLength(seferKey, fromPerek));
            listToReturn.add(listOfFromIncompletePerekPsukim);

            for (int currentPerek = fullPerekStart; currentPerek <= fullPerekEnd; currentPerek++) {
                listToReturn.add(getPerek(seferKey, currentPerek));
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

    private static int getSeferLength(String seferKey) {
        try {
            return getDBWithName(getString(R.string.database_name_torah)).getObject(seferKey, Sefer.class).text.size();
        } catch (SnappydbException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private static boolean isValidRangeOfText(String seferKey, int fromPerek, int fromPasuk, int toPerek, int toPasuk) {
        int seferLength = getSeferLength(seferKey);

        if (fromPerek > toPerek || fromPerek > seferLength || toPerek > seferLength) {
            return false;
        }

        int fromPerekLength = getPerekLength(seferKey, fromPerek);

        if (fromPerek == toPerek) {
            if (fromPasuk > toPasuk || fromPasuk > fromPerekLength || toPasuk > fromPerekLength) {
                return false;
            }
        } else {
            int toPerekLength = getPerekLength(seferKey, toPerek);

            if (fromPasuk > fromPerekLength || toPasuk > toPerekLength) {
                return false;
            }
        }

        return true;
    }
}
