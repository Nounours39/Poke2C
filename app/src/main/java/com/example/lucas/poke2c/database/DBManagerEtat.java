package com.example.lucas.poke2c.database;

import android.content.Context;

import com.example.lucas.poke2c.model.Etat;
import com.example.lucas.poke2c.model.Langue;
import com.example.lucas.poke2c.model.Utilisateur;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBManagerEtat {
    private DatabaseHelper  helper;
    private static DBManagerEtat ourInstance;

    public static void init(Context context) {
        if (ourInstance == null)
            ourInstance = new DBManagerEtat(context);
    }

    public static DBManagerEtat getInstance() {
        return ourInstance;
    }

    private DBManagerEtat(Context context) {
        helper = new DatabaseHelper(context);
    }

    private DatabaseHelper getHelper() {
        return helper;
    }

    /* Function Etat*/
    public List<Etat> getAllEtats() {
        try {
            return getHelper().getEtatDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<Etat>();
        }
    }

    public long createEtat(Etat etat) {
        try {
            getHelper().getEtatDao().create(etat);
            return etat.getId();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
