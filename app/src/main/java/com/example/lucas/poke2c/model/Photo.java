package com.example.lucas.poke2c.model;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "photo")
public class Photo {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "libelle", canBeNull = false)
    private String libelle;
    @DatabaseField(columnName = "lien", canBeNull = false)
    private String lien;

    public Photo(){

    }

    public Photo(String libelle, String lien) {
        this.libelle = libelle;
        this.lien = lien;
    }

    public String getLibelle() {
        return libelle;
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getLien() {
        return lien;
    }
    public void setLien(String lien) {
        this.lien = lien;
    }
}
