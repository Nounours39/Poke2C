package com.example.lucas.poke2c.database;

import android.content.Context;

import com.example.lucas.poke2c.model.CartePokemon;
import com.example.lucas.poke2c.model.CollectionN;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBManagerCarte {

    private DatabaseHelper  helper;
    private static DBManagerCarte ourInstance;

    public static void init(Context context) {
        if (ourInstance == null)
            ourInstance = new DBManagerCarte(context);
    }
    public static DBManagerCarte getInstance() {
        return ourInstance;
    }

    private DBManagerCarte(Context context) {
        helper = new DatabaseHelper(context);
    }

    private DatabaseHelper getHelper() {
        return helper;
    }


    /** Fonction pour créer une carte **/

    public long createCarte(CartePokemon carte) {
        try {
            getHelper().getCartePokemonDao().create(carte);
            return carte.getId();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /** Supprimer une carte **/

    public void removeCarte(CartePokemon carte) {
        try {
            getHelper().getCartePokemonDao().delete(carte);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public long updateCarte(CartePokemon carte) {
        try {
            getHelper().getCartePokemonDao().update(carte);
            return carte.getId();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
