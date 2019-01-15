package com.example.lucas.poke2c.model;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "carte_pokemon")
public class CartePokemon {
    @DatabaseField(generatedId = true)
    private long id;
    @DatabaseField(columnName = "numero", canBeNull = false)
    private int numero;
    @DatabaseField(columnName = "nom", canBeNull = false)
    private String nom;
    @DatabaseField(columnName = "rarete", canBeNull = false)
    private String rareté;
    @DatabaseField(columnName = "reverse", canBeNull = false)
    private boolean reverse;
    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "collection")
    private CollectionN collection;
    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "photo")
    private Photo photo;
    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "collectionperso")
    private CollectionPerso collectionper;
    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "etat")
    private Etat etat;
    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "langue")
    private Langue langue;

    public CartePokemon(){

    }

    public CartePokemon(int numero, String nom, String rareté, boolean reverse, CollectionN collection, Photo photo, CollectionPerso collectionPer, Etat etat, Langue langue) {
        this.numero = numero;
        this.nom = nom;
        this.rareté = rareté;
        this.reverse = reverse;
        this.collection = collection;
        this.photo = photo;
        this.collectionper = collectionPer;
        this.etat = etat;
        this.langue = langue;
    }

    public long getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getRareté() {
        return rareté;
    }
    public void setRareté(String rareté) {
        this.rareté = rareté;
    }

    public boolean isReverse() {
        return reverse;
    }
    public void setReverse(boolean reverse) {
        this.reverse = reverse;
    }

    public CollectionN getCollection() {
        return collection;
    }
    public void setCollection(CollectionN collection) {
        this.collection = collection;
    }

    public Photo getPhoto() {
        return photo;
    }
    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public CollectionPerso getCollectionper() {
        return collectionper;
    }
    public void setCollectionper(CollectionPerso collectionper) {
        this.collectionper = collectionper;
    }

    public Etat getEtat() {
        return etat;
    }
    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public Langue getLangue() {
        return langue;
    }
    public void setLangue(Langue langue) {
        this.langue = langue;
    }
}
