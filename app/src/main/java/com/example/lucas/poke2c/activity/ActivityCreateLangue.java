package com.example.lucas.poke2c.activity;

import android.content.Intent;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lucas.poke2c.MainActivity;
import com.example.lucas.poke2c.R;
import com.example.lucas.poke2c.database.DBManagerLangue;
import com.example.lucas.poke2c.model.Langue;
import com.example.lucas.poke2c.model.Utilisateur;

import java.util.ArrayList;
import java.util.List;

public class ActivityCreateLangue extends AppCompatActivity {

    private DBManagerLangue dbManagerLangue;

    private EditText edtNom;
    private Button btnCreateLangue;

    private Toolbar toolbarLangue;
    private Utilisateur user;
    private List<Langue> lesLangues;
    private Langue langue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_langue);

        edtNom = findViewById(R.id.edtNomLangue);
        btnCreateLangue = findViewById(R.id.btnCreateLangue);

        DBManagerLangue.init(this);
        dbManagerLangue = DBManagerLangue.getInstance();

        toolbarLangue = findViewById(R.id.toolbar);
        ////definir notre toolbar en tant qu'actionBar
        setSupportActionBar(toolbarLangue);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarLangue.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        recupererUser();

        btnCreateLangue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtNom.getText().toString()!=null){
                    final Intent intentCreateLangue = new Intent(getApplicationContext(), ActivityCollection.class);
                    lesLangues = dbManagerLangue.getAllLanguesByUser(user);
                    langue = new Langue();
                    langue.setNom(edtNom.getText().toString());
                    langue.setUser(user);
                    Boolean fin = false;
                    int i = 0;
                    while(i < lesLangues.size()){
                        if(lesLangues.get(i).getNom().toString().equals(langue.getNom().toString())){
                            Toast.makeText(getApplicationContext(), "Cette langue existe déjà ! ", Toast.LENGTH_SHORT).show();
                            fin = true;
                        }else{

                        }
                        i++;
                    }
                    if(fin != true){
                        dbManagerLangue.createLangue(langue);
                        intentCreateLangue.putExtra(Intent.EXTRA_USER, user);
                        startActivity(intentCreateLangue);
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
