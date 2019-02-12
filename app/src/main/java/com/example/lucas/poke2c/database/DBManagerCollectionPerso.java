package com.example.lucas.poke2c.database;

import android.content.Context;

import com.example.lucas.poke2c.model.CollectionN;
import com.example.lucas.poke2c.model.CollectionPerso;
import com.example.lucas.poke2c.model.Utilisateur;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DBManagerCollectionPerso {
    /** A/D
     *
     */
    private DatabaseHelper  helper;
    private static DBManagerCollectionPerso ourInstance;
    private static List<CollectionPerso> listColPers = new ArrayList<CollectionPerso>();

    public static void init(Context context) {
        if (ourInstance == null)
            ourInstance = new DBManagerCollectionPerso(context);
    }

    public static DBManagerCollectionPerso getInstance() {
        return ourInstance;
    }

    private DBManagerCollectionPerso(Context context) {
        helper = new DatabaseHelper(context);
    }

    private DatabaseHelper getHelper() {
        return helper;
    }

    /** Methods [Person] **/

    public List<CollectionPerso> getAllCollectionsPerso() {
        try {
            return getHelper().getCollectionPersoDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<CollectionPerso>();
        }
    }

    public List<CollectionPerso> getAllCollectionsPersoByUser(Utilisateur user) {
        try {
            listColPers = getHelper().getCollectionPersoDao().queryForEq("user", user);
            Collections.sort(listColPers, new Comparator<CollectionPerso>(){
                @Override
                public int compare(CollectionPerso t1, CollectionPerso t2) {
                    CollectionPerso c1 = (CollectionPerso) t1;
                    CollectionPerso c2 = (CollectionPerso) t2;
                    return t1.getCategorie().getCategorie().compareToIgnoreCase(t2.getCategorie().getCategorie());
                }
            });
            return listColPers;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<CollectionPerso>();
        }
    }


    public long createCollectionPerso(CollectionPerso collection) {
        try {
            getHelper().getCollectionPersoDao().create(collection);
            return collection.getId();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void removeCollectionPerso(CollectionPerso collection) {
        try {
            getHelper().getCollectionPersoDao().delete(collection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public long updateCollectionPerso(CollectionPerso collection) {
        try {
            getHelper().getCollectionPersoDao().update(collection);
            return collection.getId();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public List<CollectionPerso> getCollectionByNomPerso(String nom) {
        try {
            return getHelper().getCollectionPersoDao().queryForEq("nom", nom);
        } catch (SQLException e) {
            e.printStackTrace();
            return listColPers;
        }
    }
}