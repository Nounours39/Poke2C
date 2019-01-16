package com.example.lucas.poke2c.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.example.lucas.poke2c.InfoAdapter;
import com.example.lucas.poke2c.database.DBManagerInformation;
import com.example.lucas.poke2c.database.DBManagerUtilisateur;
import com.example.lucas.poke2c.model.Information;
import com.example.lucas.poke2c.R;

import java.util.ArrayList;
import java.util.List;

public class ActivityInfo extends AppCompatActivity {
    private Toolbar toolbar;
    private ListView listViewInfo;
    private List<Information> lesInfos = new ArrayList<>();

    private DBManagerInformation dbManagerInformation;
    public static InfoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        listViewInfo = findViewById(R.id.listViewInfo);
        toolbar = findViewById(R.id.toolbar);
        ////definir notre toolbar en tant qu'actionBar
        setSupportActionBar(toolbar);

        ////Initialise la connexion
        DBManagerInformation.init(this);
        dbManagerInformation = DBManagerInformation.getInstance();

        ////Ajouter des infos
        //InformationsCreate();

        ////Supprimer toutes les infos
        //InformationsDelete(dbManagerInformation.getAllInformations());

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        afficherListeInfo();
    }

    private void afficherListeInfo(){
        List<Information> infos = dbManagerInformation.getAllInformations();
        adapter = new InfoAdapter(this, infos);
        listViewInfo.setAdapter(adapter);
    }

    private void createInfo(List<Information> lesInfos){
        for(int i = 0; i< lesInfos.size();i++){
            dbManagerInformation.createInformation(lesInfos.get(i));
        }
    }

    private void InformationsCreate(){
        Information info = new Information(1, "Mise en place de l'application ","\r Application Poke2C a été créer par Lucas Monrolin. \n Son but est d'aidé les collectionneurs de cartes à pouvoir gerer plus facilement leurs collections de carte Pokemon ou autre. \n Mais également de pouvoir faire des statistiques sur les achats ou les ventes voir plus celon les differents besoins de l'utilisateur.", "12/12/2018");
        Information info2 = new Information(2, "Fonctionnement de l'application ","\r L'application se lance sur une page d'acceuil avec 2 boutons : \n\r - Un bouton ''Création d'Utilisateur'' car il peut y avoir 4 collectionneurs aux totals pour gerer differents collectionneurs. \n\r - Un bouton ''Information'' pour voir les mises à jour mais egalement les differentes fonctionnalités de l'application.", "12/12/2018");
        Information info3 = new Information(3, "Essai de l'application ","\r L'application se lance sur une page d'acceuil avec 2 boutons : \n\r - Un bouton ''Création d'Utilisateur'' car il peut y avoir 4 collectionneurs aux totals pour gerer differents collectionneurs. \n\r - Un bouton ''Information'' pour voir les mises à jour mais egalement les differentes fonctionnalités de l'application. essai", "12/12/2018");

        lesInfos.add(info);
        lesInfos.add(info2);
        lesInfos.add(info3);

        createInfo(lesInfos);
    }

    private void InformationsDelete(List<Information> lesInfos){
        dbManagerInformation.removeAllInformation(lesInfos);
    }

}
