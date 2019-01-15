package com.example.lucas.poke2c.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "utilisateur")
public class Utilisateur implements Parcelable {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "nom", canBeNull = false)
    private String name;
    @DatabaseField(columnName = "description", canBeNull = false)
    private String description;
    @DatabaseField(columnName = "login", canBeNull = false)
    private String login;
    @DatabaseField(columnName = "mdp", canBeNull = false)
    private String mdp;

    public Utilisateur(String name, String description, String login, String mdp){
        this.name = name;
        this.description = description;
        this.login = login;
        this.mdp = mdp;
    }

    public Utilisateur(){

    }

    protected Utilisateur(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
        login = in.readString();
        mdp = in.readString();
    }

    public static final Creator<Utilisateur> CREATOR = new Creator<Utilisateur>() {
        @Override
        public Utilisateur createFromParcel(Parcel in) {
            return new Utilisateur(in);
        }

        @Override
        public Utilisateur[] newArray(int size) {
            return new Utilisateur[size];
        }
    };

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeString(login);
        parcel.writeString(mdp);
    }
}
