package com.example.lucas.poke2c.database;

import android.content.Context;

import com.example.lucas.poke2c.model.CollectionN;
import com.example.lucas.poke2c.model.Information;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBManagerInformation {

    private DatabaseHelper  helper;
    private static DBManagerInformation ourInstance;

    public static void init(Context context) {
        if (ourInstance == null)
            ourInstance = new DBManagerInformation(context);
    }

    public static DBManagerInformation getInstance() {
        return ourInstance;
    }

    private DBManagerInformation(Context context) {
        helper = new DatabaseHelper(context);
    }

    private DatabaseHelper getHelper() {
        return helper;
    }

    /* Function Information*/
    public List<Information> getAllInformations() {
        try {
            return getHelper().getInformationDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<Information>();
        }
    }

    public long createInformation(Information information) {
        try {
            getHelper().getInformationDao().create(information);
            return information.getNumero();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void removeInformation(Information information) {
        try {
            getHelper().getInformationDao().delete(information);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public long updateInformation(Information information) {
        try {
            getHelper().getInformationDao().update(information);
            return information.getNumero();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
