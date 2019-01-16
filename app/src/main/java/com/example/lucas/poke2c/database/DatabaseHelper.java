package com.example.lucas.poke2c.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.lucas.poke2c.model.CartePokemon;
import com.example.lucas.poke2c.model.CollectionN;
import com.example.lucas.poke2c.model.Information;
import com.example.lucas.poke2c.model.Utilisateur;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    /** A/D
     *
     */
    private Dao<CollectionN, Integer> CollectionDAO = null;
    private Dao<Information, Integer> InformationDAO = null;
    private Dao<Utilisateur, Integer> UtilisateurDAO = null;
    private Dao<CartePokemon, Integer> CartePokemonDAO = null;


    /** CONSTANT
     *
     */
    private static final String     DB_NAME = "myDatabase.poke2c";
    private static final int        DB_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, com.j256.ormlite.support.ConnectionSource connectionSource) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onCreate");
            TableUtils.createTable(connectionSource, CollectionN.class);
            TableUtils.createTable(connectionSource, Information.class);
            TableUtils.createTable(connectionSource, Utilisateur.class);
            TableUtils.createTable(connectionSource, CartePokemon.class);
            Log.i(DatabaseHelper.class.getName(), "Les tables ont été créé ! ");
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
            Log.i(DatabaseHelper.class.getName(), "Error : Les tables n'ont pas été créé ! ");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, com.j256.ormlite.support.ConnectionSource connectionSource, int i, int i2) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onUpgrade");
            TableUtils.dropTable(connectionSource, CollectionN.class, true);
            TableUtils.dropTable(connectionSource, Information.class, true);
            TableUtils.dropTable(connectionSource, Utilisateur.class, true);
            TableUtils.dropTable(connectionSource, CartePokemon.class, true);
            Log.i(DatabaseHelper.class.getName(), "Les tables ont été supprimé ! ");
            onCreate(sqLiteDatabase,connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
            Log.i(DatabaseHelper.class.getName(), "Error : Les tables n'ont pas été supprimé ! ");
        }
    }

    /** Getter/Setter
     *
     */
    public Dao<CollectionN, Integer> getCollectionDao() {
        if (CollectionDAO == null) {
            try {
                CollectionDAO = getDao(CollectionN.class);
            }catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return CollectionDAO;
    }

    public Dao<Utilisateur, Integer> getUtilisateurDao() {
        if (UtilisateurDAO == null) {
            try {
                UtilisateurDAO = getDao(Utilisateur.class);
            }catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return UtilisateurDAO;
    }

    public Dao<Information, Integer> getInformationDao() {
        if (InformationDAO == null) {
            try {
                InformationDAO = getDao(Information.class);
            }catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return InformationDAO;
    }

    public Dao<CartePokemon, Integer> getCartePokemonDao() {
        if (CartePokemonDAO == null) {
            try {
                CartePokemonDAO = getDao(CartePokemon.class);
            }catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return CartePokemonDAO;
    }


}