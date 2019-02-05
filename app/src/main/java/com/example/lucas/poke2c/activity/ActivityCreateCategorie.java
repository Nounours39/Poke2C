package com.example.lucas.poke2c.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lucas.poke2c.R;
import com.example.lucas.poke2c.database.DBManagerCategorie;
import com.example.lucas.poke2c.database.DBManagerLangue;
import com.example.lucas.poke2c.model.Categorie;
import com.example.lucas.poke2c.model.Langue;
import com.example.lucas.poke2c.model.Utilisateur;

import java.util.List;

public class ActivityCreateCategorie extends AppCompatActivity {
    private DBManagerCategorie dbManagerCategorie;

    private EditText edtNom;
    private Button btnCreateCategorie;

    private Toolbar toolbarCategorie;
    private Utilisateur user;
    private List<Categorie> lesCategories;
    private Categorie categorie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_categorie);

        edtNom = findViewById(R.id.edtNomCategorie);
        btnCreateCategorie = findViewById(R.id.btnCreateCategorie);

        DBManagerCategorie.init(this);
        dbManagerCategorie = DBManagerCategorie.getInstance();

        toolbarCategorie = findViewById(R.id.toolbar);
        ////definir notre toolbar en tant qu'actionBar
        setSupportActionBar(toolbarCategorie);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarCategorie.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        recupererUser();

        btnCreateCategorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtNom.getText().toString()!=null){
                    final Intent intentCreateCategorie = new Intent(getApplicationContext(), ActivityCollection.class);
                    Log.e("Error", user.getName()+" - "+user.getId());
                    lesCategories = dbManagerCategorie.getAllCategoriesByUser(user);
                    categorie = new Categorie();
                    categorie.setCategorie(edtNom.getText().toString());
                    categorie.setUser(user);
                    Boolean fin = false;
                    int i = 0;
                    while(i < lesCategories.size()){
                        if(lesCategories.get(i).getCategorie().toString().equals(categorie.getCategorie().toString())){
                            Toast.makeText(getApplicationContext(), "Cette Categorie existe déjà ! ", Toast.LENGTH_SHORT).show();
                            fin = true;
                        }else{

                        }
                        i++;
                    }
                    if(fin != true){
                        dbManagerCategorie.createCategorie(categorie);
                        intentCreateCategorie.putExtra(Intent.EXTRA_USER, user);
                        startActivity(intentCreateCategorie);
                        finish();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Une champ n'est pas rempli ! ", Toast.LENGTH_SHORT).show();
                }
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
