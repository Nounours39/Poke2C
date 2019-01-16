package com.example.lucas.poke2c.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "langue")
public class Langue {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "nom", canBeNull = false)
    private String nom;

    public Langue(){

    }

    public Langue(String nom) {
        this.nom = nom;
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
}
