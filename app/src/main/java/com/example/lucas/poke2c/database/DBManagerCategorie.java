package com.example.lucas.poke2c.database;

import android.content.Context;

import com.example.lucas.poke2c.model.Categorie;
import com.example.lucas.poke2c.model.Langue;
import com.example.lucas.poke2c.model.Utilisateur;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBManagerCategorie {
    private DatabaseHelper  helper;
    private static DBManagerCategorie ourInstance;

    public static void init(Context context) {
        if (ourInstance == null)
            ourInstance = new DBManagerCategorie(context);
    }

    public static DBManagerCategorie getInstance() {
        return ourInstance;
    }

    private DBManagerCategorie(Context context) {
        helper = new DatabaseHelper(context);
    }

    private DatabaseHelper getHelper() {
        return helper;
    }

    /* Function Langue*/
    public List<Categorie> getAllCategories() {
        try {
            return getHelper().getCategorieDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<Categorie>();
        }
    }

    public List<Categorie> getAllCategoriesByUser(Utilisateur user) {
        List<Categorie> categories;
        try {
            categories = getHelper().getCategorieDao().queryForEq("user",user);
            return categories;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<Categorie>();
        }
    }

    public List<Categorie> getAllCategoriesByUserByCollection(Utilisateur user, boolean bool) {
        List<Categorie> categories;
        List<Categorie> lesCategories = new ArrayList<Categorie>();
        try {
            categories = getHelper().getCategorieDao().queryForEq("user",user);
            for(int i =0; i<categories.size();i++){
                if(categories.get(i).getPerso()==bool){
                    lesCategories.add(categories.get(i));
                }
            }
            return lesCategories;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<Categorie>();
        }
    }

    public long createCategorie(Categorie categorie) {
        try {
            getHelper().getCategorieDao().create(categorie);
            return categorie.getId();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void removeCategorie(Categorie categorie) {
        try {
            getHelper().getCategorieDao().delete(categorie);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeAllCategorie(List<Categorie> lesCategories) {
        try {
            for(int i = 0;i<lesCategories.size();i++) {
                getHelper().getCategorieDao().delete(lesCategories.get(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public long updateCategorie(Categorie categorie) {
        try {
            getHelper().getCategorieDao().update(categorie);
            return categorie.getId();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
