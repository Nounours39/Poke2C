package com.example.lucas.poke2c.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lucas.poke2c.MainActivity;
import com.example.lucas.poke2c.R;
import com.example.lucas.poke2c.database.DBManagerUtilisateur;
import com.example.lucas.poke2c.model.Utilisateur;


public class ActivityCreateUser extends AppCompatActivity {
    private DBManagerUtilisateur dbManagerUtilisateur;
    private Utilisateur util;
    private Toolbar toolbar;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        toolbar = findViewById(R.id.toolbar);
        //definir notre toolbar en tant qu'actionBar
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        final Intent create = new Intent(this, MainActivity.class);
        final EditText nom = findViewById(R.id.nomUser);
        final EditText desc = findViewById(R.id.descrUser);
        final EditText login = findViewById(R.id.loginUser);
        final EditText mdp = findViewById(R.id.mdpUser);

        Button btnCreate = findViewById(R.id.btnCreateUser);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextNom = findViewById(R.id.nomUser);
                EditText editTextDescription = findViewById(R.id.descrUser);
                EditText editTextLogin = findViewById(R.id.loginUser);
                EditText editTextMdp = findViewById(R.id.mdpUser);
                if (!editTextNom.getText().toString().equals("") && !editTextLogin.getText().toString().equals("") && !editTextMdp.getText().toString().equals("")) {
                    if(editTextNom.getText().length() < 6 && editTextLogin.getText().length() < 6 && editTextMdp.getText().length() < 6){
                        Toast.makeText(ActivityCreateUser.this, "Un des champs saisis est trop cours, nom : 4 caractères, login : 6 caractères, mdp : 6 caractères !", Toast.LENGTH_LONG).show();
                   }else{
                        DBManagerUtilisateur.init(context);
                        dbManagerUtilisateur = DBManagerUtilisateur.getInstance();

                        util = new Utilisateur(nom.getText().toString(),desc.getText().toString(),login.getText().toString(),mdp.getText().toString(), "");
                        dbManagerUtilisateur.createUtilisateur(util);
                        startActivity(create);
                        finish();
                       }
                } else {
                    Toast.makeText(ActivityCreateUser.this, "Tous les champs ne sont pas remplis, veuillez tous les saisir ! ", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
