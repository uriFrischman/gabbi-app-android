package com.frischman.uri.gabbiapp.utility;

import com.frischman.uri.gabbiapp.R;
import com.frischman.uri.gabbiapp.model.Sefer;
import com.google.gson.Gson;
import com.snappydb.DB;
import com.snappydb.SnappydbException;

import static com.frischman.uri.gabbiapp.utility.FileUtil.rawFileToString;
import static com.frischman.uri.gabbiapp.utility.SnappyDBUtil.getDBWithName;
import static com.frischman.uri.gabbiapp.utility.StringUtil.getString;

public class TorahUtil {

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
}
