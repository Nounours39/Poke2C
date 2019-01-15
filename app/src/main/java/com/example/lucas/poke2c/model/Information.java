package com.example.lucas.poke2c.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = "information")
public class Information {
    @DatabaseField(generatedId = true)
    private int numero;
    @DatabaseField(columnName = "titre", canBeNull = false)
    private String titre;
    @DatabaseField(columnName = "description", canBeNull = false)
    private String description;
    @DatabaseField(columnName = "date_creation", canBeNull = false)
    private String date_creation;


    public Information(int numero, String titre, String description, String date_creation){
        this.numero = numero;
        this.titre = titre;
        this.description = description;
        this.date_creation = date_creation;
    }

    public Information(){

    }

    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate_creation() {
        return date_creation;
    }
    public void setDate_creation(String date_creation) {
        this.date_creation = date_creation;
    }
}
