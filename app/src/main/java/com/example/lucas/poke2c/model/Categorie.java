package com.example.lucas.poke2c.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "categorie")
public class Categorie {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "categorie", canBeNull = false)
    private String categorie;
    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "user")
    private Utilisateur user;

    public Categorie(){

    }

    public Categorie(String categorie, Utilisateur user) {
        this.categorie = categorie;
        this.user = user;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getCategorie() {
        return categorie;
    }
    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Utilisateur getUser() {
        return user;
    }
    public void setUser(Utilisateur user) {
        this.user = user;
    }
}
