package com.example.lucas.poke2c.database;

import android.content.Context;

import com.example.lucas.poke2c.model.CollectionN;
import com.example.lucas.poke2c.model.Utilisateur;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBManagerUtilisateur {

    private DatabaseHelper helper;
    private static DBManagerUtilisateur ourInstance;

    public static void init(Context context) {
        if (ourInstance == null)
            ourInstance = new DBManagerUtilisateur(context);
    }

    public static DBManagerUtilisateur getInstance() {
        return ourInstance;
    }

    private DBManagerUtilisateur(Context context) {
        helper = new DatabaseHelper(context);
    }

    private DatabaseHelper getHelper() {
        return helper;
    }

    /* Function Utilisateur */
    public List<Utilisateur> getAllUtilisateurs() {
        try {
            return getHelper().getUtilisateurDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<Utilisateur>();
        }
    }

    public long createUtilisateur(Utilisateur utilisateur) {
        try {
            getHelper().getUtilisateurDao().create(utilisateur);
            return utilisateur.getId();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void removeUtilisateur(Utilisateur utilisateur) {
        try {
            getHelper().getUtilisateurDao().delete(utilisateur);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public long updateUtilisateur(Utilisateur utilisateur) {
        try {
            getHelper().getUtilisateurDao().update(utilisateur);
            return utilisateur.getId();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
