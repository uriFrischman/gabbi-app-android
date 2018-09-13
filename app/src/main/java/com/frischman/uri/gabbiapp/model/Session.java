package com.frischman.uri.gabbiapp.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Uri on 2018-07-03.
 */

@Entity(tableName = "session")
public class Session {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id = 0;

    @ColumnInfo(name = "current_user")
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
