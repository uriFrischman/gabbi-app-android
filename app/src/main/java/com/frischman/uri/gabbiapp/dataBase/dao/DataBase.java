package com.frischman.uri.gabbiapp.dataBase.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.frischman.uri.gabbiapp.GabbiApp;
import com.frischman.uri.gabbiapp.model.Session;

/**
 * Created by Uri on 2018-07-03.
 */

@Database(entities = {Session.class}, version = 1)
public abstract class DataBase extends RoomDatabase {

    public abstract SessionDao getSessionDao();

    private static DataBase sInstance;

    public static synchronized DataBase getInstance() {
        if (sInstance == null){
            sInstance = create(GabbiApp.getAppContext());
        }
        return sInstance;
    }

    private static DataBase create(Context context) {
        return Room.databaseBuilder(context, DataBase.class, "gabbiAppDataBase")
                .build();
    }

}
