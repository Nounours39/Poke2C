package com.example.lucas.poke2c.database;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.lucas.poke2c.model.Collection;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class DBManagerCollection {
    /** A/D
     *
     */
    private DatabaseHelper  helper;
    private static DBManagerCollection ourInstance;
    private static List<Collection> listCol = new ArrayList<Collection>();

    public static void init(Context context) {
        if (ourInstance == null)
            ourInstance = new DBManagerCollection(context);
    }

    public static DBManagerCollection getInstance() {
        return ourInstance;
    }

    private DBManagerCollection(Context context) {
        helper = new DatabaseHelper(context);
    }

    private DatabaseHelper getHelper() {
        return helper;
    }

    /** Methods [Person] **/

    public List<Collection> getAllCollections() {
        try {
            return getHelper().getCollectionDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<Collection>();
        }
    }

    public long createCollection(Collection collection) {
        try {
            getHelper().getCollectionDao().create(collection);
            return collection.getId();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void removeCollection(Collection collection) {
        try {
            getHelper().getCollectionDao().delete(collection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public long updateCollection(Collection collection) {
        try {
            getHelper().getCollectionDao().update(collection);
            return collection.getId();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public List<Collection> getCollectionByNom(String nom) {
        try {
            return getHelper().getCollectionDao().queryForEq("nom", nom);
        } catch (SQLException e) {
            e.printStackTrace();
            return listCol;
        }
    }


}