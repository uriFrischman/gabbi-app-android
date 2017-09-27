package com.frischman.uri.gabbiapp.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.frischman.uri.gabbiapp.GabbiApp.getAppResources;

public class FileUtil {

    public static String rawFileToString(int rawFile) {

        StringBuffer sb = new StringBuffer();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(getAppResources().openRawResource(rawFile)));
            String temp;
            while ((temp = br.readLine()) != null) {
                sb.append(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
