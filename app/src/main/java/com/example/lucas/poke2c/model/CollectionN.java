package com.example.lucas.poke2c.model;

import com.example.lucas.poke2c.database.DBManagerCarte;
import com.example.lucas.poke2c.database.DBManagerUtilisateur;
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
    @DatabaseField(columnName = "icon")
    private String icon;
    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "langue")
    private Langue langue;
    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "user")
    private Utilisateur user;
    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "categorie")
    private Categorie categorie;
    @ForeignCollectionField
    Collection<CartePokemon> cartepokemon;

    public CollectionN(){

    }

    public CollectionN(String nom, int nombre_max, String icon, Langue langue, Utilisateur user, Categorie categorie){
        this.nom = nom;
        this.nombre_max = nombre_max;
        this.icon = icon;
        this.langue = langue;
        this.user = user;
        this.categorie = categorie;
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

    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Langue getLangue() {
        return langue;
    }
    public void setLangue(Langue langue) {
        this.langue = langue;
    }

    public Utilisateur getUser() {
        return user;
    }
    public void setUser(Utilisateur user) {
        this.user = user;
    }

    public Categorie getCategorie() {
        return categorie;
    }
    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public int nbCarte(){
        return cartepokemon.size();
    }



}
