package com.example.lucas.poke2c.database;

import android.content.Context;

import com.example.lucas.poke2c.model.Information;
import com.example.lucas.poke2c.model.Langue;
import com.example.lucas.poke2c.model.Utilisateur;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBManagerLangue {
    private DatabaseHelper  helper;
    private static DBManagerLangue ourInstance;

    public static void init(Context context) {
        if (ourInstance == null)
            ourInstance = new DBManagerLangue(context);
    }

    public static DBManagerLangue getInstance() {
        return ourInstance;
    }

    private DBManagerLangue(Context context) {
        helper = new DatabaseHelper(context);
    }

    private DatabaseHelper getHelper() {
        return helper;
    }

    /* Function Langue*/
    public List<Langue> getAllLangues() {
        try {
            return getHelper().getLangueDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<Langue>();
        }
    }

    public List<Langue> getAllLanguesByUser(Utilisateur user) {
        List<Langue> langues;
        try {
            langues = getHelper().getLangueDao().queryForEq("user", user);
            return langues;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<Langue>();
        }
    }

    public long createLangue(Langue langue) {
        try {
            getHelper().getLangueDao().create(langue);
            return langue.getId();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void removeLangue(Langue langue) {
        try {
            getHelper().getLangueDao().delete(langue);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeAllLangue(List<Langue> lesLangues) {
        try {
            for(int i = 0;i<lesLangues.size();i++) {
                getHelper().getLangueDao().delete(lesLangues.get(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public long updateLangue(Langue langue) {
        try {
            getHelper().getLangueDao().update(langue);
            return langue.getId();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
