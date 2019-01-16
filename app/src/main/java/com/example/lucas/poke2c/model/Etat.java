package com.example.lucas.poke2c.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "etat")
public class Etat {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "etat", canBeNull = false)
    private String etat;

    public Etat(){

    }

    public Etat(String etat) {
        this.etat = etat;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getEtat() {
        return etat;
    }
    public void setEtat(String etat) {
        this.etat = etat;
    }
}
