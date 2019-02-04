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
import com.example.lucas.poke2c.database.DBManagerCollection;
import com.example.lucas.poke2c.database.DBManagerLangue;
import com.example.lucas.poke2c.database.DBManagerUtilisateur;
import com.example.lucas.poke2c.model.CollectionN;
import com.example.lucas.poke2c.model.Icon;
import com.example.lucas.poke2c.model.Langue;
import com.example.lucas.poke2c.model.Utilisateur;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.lang.Integer.parseInt;

public class ActivityCreateCollection extends AppCompatActivity {

    private DBManagerLangue dbManagerLangue;
    private DBManagerCollection dbManagerCollection;

    private Toolbar toolbarColl;
    private EditText nom;
    private EditText nbMax;
    private Spinner langue;
    private TextView chemin;
    private ImageView image;
    private boolean val_img = false;
    private Button createColl;
    private Button createLangueColl;
    private ArrayAdapter adapterlangue;

    private Utilisateur user;
    private CollectionN coll;
    private Langue lan;
    private List<Langue> lesLangues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_collection);

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

            nom = findViewById(R.id.edtNom);
            nbMax = findViewById(R.id.edtNbMax);
            langue = findViewById(R.id.spinnerLangue);
            chemin = findViewById(R.id.chemin);
            image = findViewById(R.id.imgColl);
            createColl = findViewById(R.id.btnCreateCollection);
            createLangueColl = findViewById(R.id.btnCreateLangueColl);

            recupererUser();
            lesLangues = dbManagerLangue.getAllLanguesByUser(user);
            adapterlangue = new ArrayAdapter(this, android.R.layout.simple_spinner_item, lesLangues);
            adapterlangue.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            langue.setAdapter(adapterlangue);

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
        ////Code page

            createColl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!nom.getText().toString().equals("")&&!nbMax.getText().toString().equals("")){
                        DBManagerCollection.init(getApplicationContext());
                        dbManagerCollection = DBManagerCollection.getInstance();
                        final Intent createColl = new Intent(getApplicationContext(), ActivityCollection.class);
                        coll = new CollectionN(nom.getText().toString(), parseInt(nbMax.getText().toString()), "", lan, user);
                        //Toast.makeText(getApplicationContext(), "Nb : "+parseInt(nbMax.getText().toString())+lan.getNom()+user.getName(), Toast.LENGTH_LONG).show();
                        dbManagerCollection.createCollection(coll);
                        createColl.putExtra(Intent.EXTRA_USER, user);
                        startActivity(createColl);
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext(), "Il y a un champ text vide ! ", Toast.LENGTH_LONG).show();
                    }
                }
            });

        ////Bouton or popup
            createLangueColl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Intent intentAddLangue = new Intent(getApplicationContext(), ActivityCreateLangue.class);
                    intentAddLangue.putExtra(Intent.EXTRA_USER, user);
                    startActivity(intentAddLangue);
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
