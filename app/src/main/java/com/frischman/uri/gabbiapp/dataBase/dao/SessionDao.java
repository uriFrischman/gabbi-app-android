package com.frischman.uri.gabbiapp.dataBase.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.frischman.uri.gabbiapp.model.Session;

import java.util.List;

/**
 * Created by Uri on 2018-07-03.
 */

@Dao
public interface SessionDao {

    @Query("SELECT * FROM session")
    List<Session> getAllSessions();

    @Query("SELECT * FROM session WHERE id = :id")
    Session getSessionById(int id);
}
