package com.example.lucas.poke2c.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Collection;

@DatabaseTable(tableName = "collection")
public class CollectionN {

    @DatabaseField(generatedId = true)
    private long id;
    @DatabaseField(columnName = "nom", canBeNull = false)
    private String nom;
    @DatabaseField(columnName = "nombre_max", canBeNull = false)
    private int nombre_max;
    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "icon")
    private Icon icon;
    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "langue")
    private Langue langue;
    @ForeignCollectionField
    Collection<CartePokemon> cartepokemon;

    public CollectionN(){

    }
    public CollectionN(String nom, int nombre_max, Icon icon, Langue langue){
        this.nom = nom;
        this.nombre_max = nombre_max;
        this.icon = icon;
        this.langue = langue;
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

    public Icon getIcon() {
        return icon;
    }
    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public Langue getLangue() {
        return langue;
    }
    public void setLangue(Langue langue) {
        this.langue = langue;
    }
}
