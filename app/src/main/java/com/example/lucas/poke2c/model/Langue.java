package com.example.lucas.poke2c.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "langue")
public class Langue {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "nom", canBeNull = false)
    private String nom;
    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "user")
    private Utilisateur user;

    public Langue(){

    }

    public Langue(String nom, Utilisateur user) {
        this.nom = nom;
        this.user = user;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public Utilisateur getUser() {
        return user;
    }
    public void setUser(Utilisateur user) {
        this.user = user;
    }

    @Override
    public String toString(){
        return this.nom;
    }
}
