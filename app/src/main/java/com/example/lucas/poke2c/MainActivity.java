package com.example.lucas.poke2c;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lucas.poke2c.activity.ActivityCollection;
import com.example.lucas.poke2c.activity.ActivityCreateUser;
import com.example.lucas.poke2c.activity.ActivityInfo;
import com.example.lucas.poke2c.database.DBManagerUtilisateur;
import com.example.lucas.poke2c.model.Information;
import com.example.lucas.poke2c.model.Utilisateur;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private DBManagerUtilisateur dbManagerUtilisateur;
    Context context;
    private List<Utilisateur> lesUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView ima1 = findViewById(R.id.ima1);
        ImageView ima2 = findViewById(R.id.ima2);
        ImageView ima3 = findViewById(R.id.ima3);
        ImageView ima4 = findViewById(R.id.ima4);
        TextView name1 = findViewById(R.id.Name1);
        TextView name2 = findViewById(R.id.Name2);
        TextView name3 = findViewById(R.id.Name3);
        TextView name4 = findViewById(R.id.Name4);
        Button btnCreateUser = findViewById(R.id.CreateUser);
        Button btnInfo = findViewById(R.id.Info);
        final Intent intentInfo = new Intent(this, ActivityInfo.class);
        final Intent intentConnexion = new Intent(this, ActivityCollection.class);
        final Intent intentCreateUser = new Intent(this, ActivityCreateUser.class);

        context = MainActivity.this;
        //Gerer les utilisateurs sur la page d'accueil
        //Initialise la connexion
        DBManagerUtilisateur.init(this);
        dbManagerUtilisateur = DBManagerUtilisateur.getInstance();


        lesUsers =  dbManagerUtilisateur.getAllUtilisateurs();

        if(lesUsers.size()==0){
            ima1.setVisibility(View.INVISIBLE);
            name1.setText("");
            name2.setText("");
            name3.setText("");
            name4.setText("");
            btnCreateUser.setVisibility(View.VISIBLE);
        }
        else if(lesUsers.size()==1)
        {
            ima1.setVisibility(View.VISIBLE);
            name1.setText(lesUsers.get(0).getName());
            name2.setText("");
            name3.setText("");
            name4.setText("");
            btnCreateUser.setVisibility(View.VISIBLE);
        }
        else if(lesUsers.size()==2)
        {
            name1.setText(lesUsers.get(0).getName());
            name2.setText(lesUsers.get(1).getName());
            name3.setText("");
            name4.setText("");
            btnCreateUser.setVisibility(View.VISIBLE);
        }
        else if(lesUsers.size()==3)
        {
            name1.setText(lesUsers.get(0).getName());
            name2.setText(lesUsers.get(1).getName());
            name3.setText(lesUsers.get(2).getName());
            name4.setText("");
            btnCreateUser.setVisibility(View.VISIBLE);
        }
        else if(lesUsers.size()==4)
        {
            name1.setText(lesUsers.get(0).getName());
            name2.setText(lesUsers.get(1).getName());
            name3.setText(lesUsers.get(2).getName());
            name4.setText(lesUsers.get(3).getName());
            btnCreateUser.setVisibility(View.INVISIBLE);
        }

        ima1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.layout_identifiation);
                final TextView titre = dialog.findViewById(R.id.Titre);
                final EditText login = dialog.findViewById(R.id.login);
                final EditText mdp = dialog.findViewById(R.id.mdp);
                titre.setText("Identification de " + lesUsers.get(0).getName().toString()+ " : ");
                Button btnValider = dialog.findViewById(R.id.btn_valider);
                btnValider.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (login.getText().toString().equals(lesUsers.get(0).getLogin().toString())) {
                            if (mdp.getText().toString().equals(lesUsers.get(0).getMdp().toString())) {
                                Utilisateur user = new Utilisateur(lesUsers.get(0).getName().toString(), lesUsers.get(0).getDescription().toString(), login.getText().toString(), mdp.getText().toString(),"");
                                intentConnexion.putExtra(Intent.EXTRA_USER, user);
                                startActivity(intentConnexion);
                                dialog.dismiss();
                            } else {
                                Toast.makeText(MainActivity.this, "Mot de passe incorrecte ! ", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Login incorrecte ! ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.show();
            }
        });

        ima2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.layout_identifiation);
                final TextView titre = dialog.findViewById(R.id.Titre);
                final EditText login = dialog.findViewById(R.id.login);
                final EditText mdp = dialog.findViewById(R.id.mdp);
                titre.setText("Identification de " + lesUsers.get(1).getName().toString()+ " : ");
                Button btnValider = dialog.findViewById(R.id.btn_valider);
                btnValider.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (login.getText().toString().equals(lesUsers.get(1).getLogin().toString())) {
                            if (mdp.getText().toString().equals(lesUsers.get(1).getMdp().toString())) {
                                Utilisateur user = new Utilisateur(lesUsers.get(1).getName().toString(), lesUsers.get(1).getDescription().toString(), login.getText().toString(), mdp.getText().toString(),"");
                                intentConnexion.putExtra(Intent.EXTRA_USER, user);
                                startActivity(intentConnexion);
                                dialog.dismiss();
                            } else {
                                Toast.makeText(MainActivity.this, "Mot de passe incorrecte ! ", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Login incorrecte ! ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.show();
            }
        });

        ima3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.layout_identifiation);
                final TextView titre = dialog.findViewById(R.id.Titre);
                final EditText login = dialog.findViewById(R.id.login);
                final EditText mdp = dialog.findViewById(R.id.mdp);
                titre.setText("Identification de " + lesUsers.get(2).getName().toString()+ " : ");
                Button btnValider = dialog.findViewById(R.id.btn_valider);
                btnValider.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (login.getText().toString().equals(lesUsers.get(2).getLogin().toString())) {
                            if (mdp.getText().toString().equals(lesUsers.get(2).getMdp().toString())) {
                                Utilisateur user = new Utilisateur(lesUsers.get(2).getName().toString(), lesUsers.get(2).getDescription().toString(), login.getText().toString(), mdp.getText().toString(),"");
                                intentConnexion.putExtra(Intent.EXTRA_USER, user);
                                startActivity(intentConnexion);
                                dialog.dismiss();
                            } else {
                                Toast.makeText(MainActivity.this, "Mot de passe incorrecte ! ", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Login incorrecte ! ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.show();
            }
        });

        ima4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.layout_identifiation);
                final TextView titre = dialog.findViewById(R.id.Titre);
                final EditText login = dialog.findViewById(R.id.login);
                final EditText mdp = dialog.findViewById(R.id.mdp);
                titre.setText("Identification de " + lesUsers.get(3).getName().toString()+ " : ");
                Button btnValider = dialog.findViewById(R.id.btn_valider);
                btnValider.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (login.getText().toString().equals(lesUsers.get(3).getLogin().toString())) {
                            if (mdp.getText().toString().equals(lesUsers.get(3).getMdp().toString())) {
                                Utilisateur user = new Utilisateur(lesUsers.get(3).getName().toString(), lesUsers.get(3).getDescription().toString(), login.getText().toString(), mdp.getText().toString(),"");
                                intentConnexion.putExtra(Intent.EXTRA_USER, user);
                                startActivity(intentConnexion);
                                dialog.dismiss();
                            } else {
                                Toast.makeText(MainActivity.this, "Mot de passe incorrect ! ", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Login incorrect ! ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.show();
            }
        });
        ////toolbar = findViewById(R.id.toolbar);
        //definir notre toolbar en tant qu'actionBar
        ////setSupportActionBar(toolbar);

        //afficher le bouton retour
        ////getSupportActionBar().setHomeButtonEnabled(true);
        ////getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    startActivity(intentInfo);
            }
        });

        btnCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentCreateUser);
            }
        });

    }


}
