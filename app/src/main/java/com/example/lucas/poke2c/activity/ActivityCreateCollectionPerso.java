package com.example.lucas.poke2c.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lucas.poke2c.MainActivity;
import com.example.lucas.poke2c.R;
import com.example.lucas.poke2c.database.DBManagerCategorie;
import com.example.lucas.poke2c.database.DBManagerCollection;
import com.example.lucas.poke2c.database.DBManagerCollectionPerso;
import com.example.lucas.poke2c.database.DBManagerLangue;
import com.example.lucas.poke2c.database.DBManagerUtilisateur;
import com.example.lucas.poke2c.model.Categorie;
import com.example.lucas.poke2c.model.CollectionN;
import com.example.lucas.poke2c.model.CollectionPerso;
import com.example.lucas.poke2c.model.Icon;
import com.example.lucas.poke2c.model.Langue;
import com.example.lucas.poke2c.model.Utilisateur;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.lang.Integer.parseInt;

public class ActivityCreateCollectionPerso extends AppCompatActivity {

    private DBManagerLangue dbManagerLangue;
    private DBManagerCollectionPerso dbManagerCollectionPerso;
    private DBManagerCategorie dbManagerCategorie;

    private Toolbar toolbarColl;
    private EditText nom;
    private EditText nbMax;
    private Spinner langue;
    private Spinner categorie;
    private TextView chemin;
    private ImageView image;
    private boolean val_img = false;
    private Button createCollPerso;
    private Button createLangueCollPerso;
    private Button createCategorieCollPerso;
    private ArrayAdapter adapterlanguePerso;
    private ArrayAdapter adapterCategoriePerso;

    private Utilisateur user;
    private CollectionPerso collPerso;
    private Langue lan;
    private Categorie cate;
    private List<Langue> lesLangues;
    private List<Categorie> lesCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_collection_perso);

        toolbarColl = findViewById(R.id.toolbar);
        ////definir notre toolbar en tant qu'actionBar
        setSupportActionBar(toolbarColl);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarColl.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ////Declaration des variable
        //Initialise la connexion
        DBManagerLangue.init(this);
        dbManagerLangue = DBManagerLangue.getInstance();
        DBManagerCategorie.init(this);
        dbManagerCategorie = DBManagerCategorie.getInstance();

        nom = findViewById(R.id.edtNomPerso);
        nbMax = findViewById(R.id.edtNbMaxPerso);
        langue = findViewById(R.id.spinnerLanguePerso);
        categorie = findViewById(R.id.spinnerCategoriePerso);
        chemin = findViewById(R.id.cheminPerso);
        image = findViewById(R.id.imgCollPerso);
        createCollPerso = findViewById(R.id.btnCreateCollectionPerso);
        createLangueCollPerso = findViewById(R.id.btnCreateLangueCollPerso);
        createCategorieCollPerso = findViewById(R.id.btnCreateCategorieCollPerso);

        recupererUser();

        lesLangues = dbManagerLangue.getAllLanguesByUser(user);
        adapterlanguePerso = new ArrayAdapter(this, android.R.layout.simple_spinner_item, lesLangues);
        adapterlanguePerso.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        langue.setAdapter(adapterlanguePerso);

        langue.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lan = (Langue) parent.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getApplicationContext(), "Il y a un champ vide ! ", Toast.LENGTH_LONG).show();
            }
        });

        lesCategories = dbManagerCategorie.getAllCategoriesByUserByCollection(user, true);
        adapterCategoriePerso = new ArrayAdapter(this, android.R.layout.simple_spinner_item, lesCategories);
        adapterCategoriePerso.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorie.setAdapter(adapterCategoriePerso);

        categorie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cate = (Categorie) parent.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getApplicationContext(), "Il y a un champ vide ! ", Toast.LENGTH_LONG).show();
            }
        });

        ////Code page
        createCollPerso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!nom.getText().toString().equals("")&&!nbMax.getText().toString().equals("")){
                    DBManagerCollectionPerso.init(getApplicationContext());
                    dbManagerCollectionPerso = DBManagerCollectionPerso.getInstance();
                    final Intent createCollPerso = new Intent(getApplicationContext(), ActivityCollectionPerso.class);
                    collPerso = new CollectionPerso(nom.getText().toString(), parseInt(nbMax.getText().toString()), "", lan, user, cate);
                    //Toast.makeText(getApplicationContext(), "Nb : "+parseInt(nbMax.getText().toString())+lan.getNom()+user.getName(), Toast.LENGTH_LONG).show();
                    dbManagerCollectionPerso.createCollectionPerso(collPerso);
                    createCollPerso.putExtra(Intent.EXTRA_USER, user);
                    startActivity(createCollPerso);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "Il y a un champ text vide ! ", Toast.LENGTH_LONG).show();
                }
            }
        });

        ////Bouton or popup
        createLangueCollPerso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intentAddLangue = new Intent(getApplicationContext(), ActivityCreateLangue.class);
                intentAddLangue.putExtra(Intent.EXTRA_USER, user);
                startActivity(intentAddLangue);
                finish();
            }
        });

        createCategorieCollPerso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intentAddCate = new Intent(getApplicationContext(), ActivityCreateCategorie.class);
                intentAddCate.putExtra(Intent.EXTRA_USER, user);
                startActivity(intentAddCate);
                finish();
            }
        });
    }

    public void recupererUser(){
        if(getIntent() !=null) {
            Bundle data = getIntent().getExtras();
            user = data.getParcelable(getIntent().EXTRA_USER);
            Toast.makeText(this, "Bienvenue " + user.getName(), Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Error intent ! ", Toast.LENGTH_SHORT).show();
        }
    }
}
