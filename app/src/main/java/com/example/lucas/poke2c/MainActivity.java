package com.example.lucas.poke2c;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.os.storage.StorageManager;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lucas.poke2c.activity.ActivityCollection;
import com.example.lucas.poke2c.activity.ActivityCreateCarte;
import com.example.lucas.poke2c.activity.ActivityCreateUser;
import com.example.lucas.poke2c.activity.ActivityInfo;
import com.example.lucas.poke2c.database.DBManagerCarte;
import com.example.lucas.poke2c.database.DBManagerUtilisateur;
import com.example.lucas.poke2c.model.Information;
import com.example.lucas.poke2c.model.Utilisateur;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {
    //Permission
    private int MY_PERMISSION_REQUEST_ACCESS_FINE_STORAGE = 1;
    private int MY_PERMISSION_REQUEST_ACCESS_FINE_STORAGE2 = 1;

    private DBManagerUtilisateur dbManagerUtilisateur;
    Context context;
    private List<Utilisateur> lesUsers;
    private Utilisateur user;

    public static final String connecter = "Connecter";
    public static final String idC = "id";
    public static final String nomC = "nom";
    public static final String connec = "BooleanConnecte";
    public boolean bool = false;

    public SharedPreferences sharedPreferences;

    public Boolean Connecter;

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

        //Permission
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            requestPermission();
        } else {
        }
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            requestPermission();
        } else {
        }

        final Intent intentConnexion = new Intent(this, ActivityCollection.class);
        final Intent intentInfo = new Intent(this, ActivityInfo.class);
        final Intent intentCreateUser = new Intent(this, ActivityCreateUser.class);
        final Intent intentTest = new Intent(this, ActivityCreateCarte.class);

        //SharedPreference pour rester connecté
        sharedPreferences = getSharedPreferences(connecter, Context.MODE_PRIVATE);

        context = MainActivity.this;
        //Gerer les utilisateurs sur la page d'accueil

        //Initialise la connexion
        DBManagerUtilisateur.init(this);
        dbManagerUtilisateur = DBManagerUtilisateur.getInstance();

        //Tester si il est connecter
        redirectionIfConnected();

        lesUsers =  dbManagerUtilisateur.getAllUtilisateurs();
        for (int i = 0; i < lesUsers.size(); i++) {
            Log.d("Ici", "User : " + lesUsers.get(i));
        }
        if(lesUsers.size()==0){
            ima1.setVisibility(View.INVISIBLE);
            ima2.setVisibility(View.INVISIBLE);
            ima3.setVisibility(View.INVISIBLE);
            ima4.setVisibility(View.INVISIBLE);
            name1.setText("");
            name2.setText("");
            name3.setText("");
            name4.setText("");
            btnCreateUser.setVisibility(View.VISIBLE);
        }
        else if(lesUsers.size()==1)
        {
            Bitmap image = BitmapFactory.decodeFile(lesUsers.get(0).getLienImage());
            ima1.setImageBitmap(image);
            ima1.setVisibility(View.VISIBLE);
            ima2.setVisibility(View.INVISIBLE);
            ima3.setVisibility(View.INVISIBLE);
            ima4.setVisibility(View.INVISIBLE);
            name1.setText(lesUsers.get(0).getName());
            name2.setText("");
            name3.setText("");
            name4.setText("");
            btnCreateUser.setVisibility(View.VISIBLE);
        }
        else if(lesUsers.size()==2)
        {
            Bitmap image = BitmapFactory.decodeFile(lesUsers.get(0).getLienImage());
            Bitmap image2 = BitmapFactory.decodeFile(lesUsers.get(1).getLienImage());
            ima1.setImageBitmap(image);
            ima2.setImageBitmap(image2);
            ima1.setVisibility(View.VISIBLE);
            ima2.setVisibility(View.VISIBLE);
            ima3.setVisibility(View.INVISIBLE);
            ima4.setVisibility(View.INVISIBLE);
            name1.setText(lesUsers.get(0).getName());
            name2.setText(lesUsers.get(1).getName());
            name3.setText("");
            name4.setText("");
            btnCreateUser.setVisibility(View.VISIBLE);
        }
        else if(lesUsers.size()==3)
        {
            Bitmap image = BitmapFactory.decodeFile(lesUsers.get(0).getLienImage());
            Bitmap image2 = BitmapFactory.decodeFile(lesUsers.get(1).getLienImage());
            Bitmap image3 = BitmapFactory.decodeFile(lesUsers.get(2).getLienImage());
            ima1.setImageBitmap(image);
            ima2.setImageBitmap(image2);
            ima3.setImageBitmap(image3);
            ima1.setVisibility(View.VISIBLE);
            ima2.setVisibility(View.VISIBLE);
            ima3.setVisibility(View.VISIBLE);
            ima4.setVisibility(View.INVISIBLE);
            name1.setText(lesUsers.get(0).getName());
            name2.setText(lesUsers.get(1).getName());
            name3.setText(lesUsers.get(2).getName());
            name4.setText("");
            btnCreateUser.setVisibility(View.VISIBLE);
        }
        else if(lesUsers.size()==4)
        {
            Bitmap image = BitmapFactory.decodeFile(lesUsers.get(0).getLienImage());
            Bitmap image2 = BitmapFactory.decodeFile(lesUsers.get(1).getLienImage());
            Bitmap image3 = BitmapFactory.decodeFile(lesUsers.get(2).getLienImage());
            Bitmap image4 = BitmapFactory.decodeFile(lesUsers.get(3).getLienImage());
            ima1.setImageBitmap(image);
            ima2.setImageBitmap(image2);
            ima3.setImageBitmap(image3);
            ima4.setImageBitmap(image4);
            ima1.setVisibility(View.VISIBLE);
            ima2.setVisibility(View.VISIBLE);
            ima3.setVisibility(View.VISIBLE);
            ima4.setVisibility(View.VISIBLE);
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
                                user = new Utilisateur(lesUsers.get(0).getName().toString(), lesUsers.get(0).getDescription().toString(), login.getText().toString(), mdp.getText().toString(),lesUsers.get(0).getLienImage().toString());

                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                editor.putInt(idC, lesUsers.get(0).getId());
                                editor.putString(nomC, lesUsers.get(0).getName().toString());
                                bool = true;
                                editor.putBoolean(connec, bool);

                                editor.commit();

                                intentConnexion.putExtra(Intent.EXTRA_USER, user);
                                startActivity(intentConnexion);
                                dialog.dismiss();
                                finish();
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
                                user = new Utilisateur(lesUsers.get(1).getName().toString(), lesUsers.get(1).getDescription().toString(), login.getText().toString(), mdp.getText().toString(),lesUsers.get(1).getLienImage().toString());

                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                editor.putInt(idC, lesUsers.get(1).getId());
                                editor.putString(nomC, lesUsers.get(1).getName().toString());
                                bool = true;
                                editor.putBoolean(connec, bool);

                                editor.commit();

                                intentConnexion.putExtra(Intent.EXTRA_USER, user);
                                startActivity(intentConnexion);
                                dialog.dismiss();
                                finish();
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
                                user = new Utilisateur(lesUsers.get(2).getName().toString(), lesUsers.get(2).getDescription().toString(), login.getText().toString(), mdp.getText().toString(),lesUsers.get(2).getLienImage());

                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                editor.putInt(idC, lesUsers.get(2).getId());
                                editor.putString(nomC, lesUsers.get(2).getName().toString());
                                bool = true;
                                editor.putBoolean(connec, bool);

                                editor.commit();

                                intentConnexion.putExtra(Intent.EXTRA_USER, user);
                                startActivity(intentConnexion);
                                dialog.dismiss();
                                finish();
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
                                user = new Utilisateur(lesUsers.get(3).getName().toString(), lesUsers.get(3).getDescription().toString(), login.getText().toString(), mdp.getText().toString(),lesUsers.get(3).getLienImage());

                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                editor.putInt(idC, lesUsers.get(3).getId());
                                editor.putString(nomC, lesUsers.get(3).getName().toString());
                                //editor.putString(descriptionC, lesUsers.get(3).getDescription().toString());
                                //editor.putString(loginC, login.getText().toString());
                                //editor.putString(mdpC, mdp.getText().toString());
                                //editor.putString(lienImageC, lesUsers.get(3).getLienImage().toString());
                                bool = true;
                                editor.putBoolean(connec, bool);

                                editor.commit();

                                intentConnexion.putExtra(Intent.EXTRA_USER, user);
                                startActivity(intentConnexion);
                                dialog.dismiss();
                                finish();
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

    public void redirectionIfConnected(){
        SharedPreferences preferences = context.getSharedPreferences(connecter, Context.MODE_PRIVATE);
        Connecter = preferences.getBoolean(connec, bool);
        int id = preferences.getInt(idC, 0);
        if(Connecter){
            final Intent intentConnexion = new Intent(this, ActivityCollection.class);
            Log.e("Info 1 : id ", "l'id : " + id);
            Utilisateur user1 =  dbManagerUtilisateur.getUtilisateur(id);
            Log.e("Info 2 : nom ", "l'utilisateur : " + user1.getName());
            intentConnexion.putExtra(Intent.EXTRA_USER, user1);
            startActivity(intentConnexion);
            finish();
        }else{

        }
    }

    private void requestPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //Afficher une explication sur la nécessité de cette permission
            new AlertDialog.Builder(this)
                    .setTitle("Besoin de cette permission")
                    .setMessage("Cette permission est requise pour l'application")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST_ACCESS_FINE_STORAGE);
                        }
                    }).setNegativeButton("Non", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).create().show();
        }else{
            //Demander la permission.
            //Toast.makeText(MainActivity.this, "Vous devez avoir cette permission pour accèder aux images de votre téléphone ! ", Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST_ACCESS_FINE_STORAGE);
        }
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            //Afficher une explication sur la nécessité de cette permission
            new AlertDialog.Builder(this)
                    .setTitle("Besoin de cette permission")
                    .setMessage("Cette permission est requise pour l'application")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST_ACCESS_FINE_STORAGE2);
                        }
                    }).setNegativeButton("Non", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).create().show();
        }else{
            //Demander la permission.
            //Toast.makeText(MainActivity.this, "Vous devez avoir cette permission pour accèder aux images de votre téléphone ! ", Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST_ACCESS_FINE_STORAGE2);
        }
    }
}
