package com.frischman.uri.gabbiapp.dataBase.dao;

import com.frischman.uri.gabbiapp.model.Session;

/**
 * Created by Uri on 2018-07-03.
 */

public class SessionDataBase {

    public static Session getSessionById(int id) {
        SessionDao dao = DataBase.getInstance().getSessionDao();
        return dao.getSessionById(id);
    }
}
