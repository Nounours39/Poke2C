package com.example.lucas.poke2c.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "collection")
public class CollectionPerso {

    @DatabaseField(generatedId = true)
    private long id;
    @DatabaseField(columnName = "nom", canBeNull = false)
    private String nom;
    @DatabaseField(columnName = "nombre_max", canBeNull = false)
    private int nombre_max;
    @DatabaseField(columnName = "iconid", canBeNull = false)
    private int iconId;

    public CollectionPerso(){

    }
    public CollectionPerso(String nom, int nombre_max, int iconId, int langueId){
        this.nom = nom;
        this.nombre_max = nombre_max;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNombre_max() {
        return nombre_max;
    }
    public void setNombre_max(int nombre_max) {
        this.nombre_max = nombre_max;
    }

    public int getIconId() {
        return iconId;
    }
    public void setIconId(int iconId) {
        this.iconId = iconId;
    }
}
