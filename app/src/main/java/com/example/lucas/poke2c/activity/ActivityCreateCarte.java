package com.example.lucas.poke2c.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.example.lucas.poke2c.R;
import com.example.lucas.poke2c.database.DBManagerCarte;
import com.example.lucas.poke2c.model.CartePokemon;
import com.example.lucas.poke2c.popup.Pop;
import com.example.lucas.poke2c.popup.PopupCollection;
import com.example.lucas.poke2c.popup.PopupCollectionPerso;
import com.example.lucas.poke2c.popup.PopupEtat;

public class ActivityCreateCarte extends AppCompatActivity {
    private DBManagerCarte dbManagerCarte;
    private CartePokemon carte;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_carte);

        final Intent create = new Intent(this, ActivityCreateCarte.class);
        final EditText nom = findViewById(R.id.nomCarte);
        final EditText photo = findViewById(R.id.photoCarte);
        final Spinner langue = findViewById(R.id.langueCarte);
        final EditText rarete = findViewById(R.id.rareCarte);
        final RadioButton reverse = findViewById(R.id.reverCarte);
        final RadioButton holo = findViewById(R.id.holoCarte);
        final Spinner etat = findViewById(R.id.etatCarte);
        final EditText prix = findViewById(R.id.prixCarte);
        final EditText nb_posseder = findViewById(R.id.nbCarte);
        final Spinner collection = findViewById(R.id.collectionCarte);
        final Spinner collectionPerso = findViewById(R.id.collectionpersoCarte);

        Button btnCreate = findViewById(R.id.btnCreateCarte);
        Button btnLangue = findViewById(R.id.btnLanguage);
        Button btnEtat = findViewById(R.id.btnEtat);
        Button btnCollection = findViewById(R.id.btnCollection);
        Button btnCollectionPerso = findViewById(R.id.btnCollectionPerso);

        btnLangue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityCreateCarte.this,Pop.class));
            }
        });

        btnEtat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityCreateCarte.this,PopupEtat.class));
            }
        });

        btnCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityCreateCarte.this,PopupCollection.class));
            }
        });

        btnCollectionPerso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityCreateCarte.this,PopupCollectionPerso.class));
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextNom = findViewById(R.id.nomCarte);
                EditText editTextPhoto = findViewById(R.id.photoCarte);
                Spinner spinnerLangue = findViewById(R.id.langueCarte);
                EditText editTextRarete = findViewById(R.id.rareCarte);
                RadioButton radioButtonReverse = findViewById(R.id.reverCarte);
                RadioButton radioButtonHolo = findViewById(R.id.holoCarte);
                Spinner spinnerEtat = findViewById(R.id.etatCarte);
                EditText editTextPrix = findViewById(R.id.prixCarte);
                EditText editTextNbPosseder = findViewById(R.id.nbCarte);
                Spinner spinnerCollection = findViewById(R.id.collectionCarte);

            }
        });
    }
}
