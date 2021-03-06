package com.example.lucas.poke2c.activity;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lucas.poke2c.MainActivity;
import com.example.lucas.poke2c.R;
import com.example.lucas.poke2c.RecyclerViewAdapterCate;
import com.example.lucas.poke2c.RecyclerViewAdapterColl;
import com.example.lucas.poke2c.database.DBManagerCategorie;
import com.example.lucas.poke2c.database.DBManagerCollection;
import com.example.lucas.poke2c.database.DBManagerEtat;
import com.example.lucas.poke2c.database.DBManagerLangue;
import com.example.lucas.poke2c.database.DBManagerUtilisateur;
import com.example.lucas.poke2c.model.Categorie;
import com.example.lucas.poke2c.model.CollectionN;
import com.example.lucas.poke2c.model.Etat;
import com.example.lucas.poke2c.model.Langue;
import com.example.lucas.poke2c.model.Utilisateur;

import java.util.List;

import static com.example.lucas.poke2c.R.menu.menu;

public class ActivityCollection extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DBManagerUtilisateur dbManagerUtilisateur;
    private DBManagerLangue dbManagerLangue;
    private DBManagerEtat dbManagerEtat;
    private DBManagerCollection dbManagerCollection;
    private DBManagerCategorie dbManagerCategorie;
    private DrawerLayout drawer;
    private Toolbar toolbar;
    Context context;

    public SharedPreferences sharedPreferences;
    public static final String connecter = "Connecter";
    public Boolean Connecter;
    public static final String connec = "BooleanConnecte";
    public static final String idC = "id";
    public boolean bool = false;

    private Utilisateur user = null;
    private List<Langue> langues;
    private List<Etat> etats;
    private List<Categorie> categories;
    private List<CollectionN> lesCollections;
    private List<Categorie> lesCategories;

    private RecyclerView recyclerViewCa;
    private RecyclerViewAdapterCate adapterCa;
    private LinearLayoutManager linearLayoutManagerCa;

    private RecyclerView recyclerViewCo;
    private RecyclerViewAdapterColl adapterCo;
    private LinearLayoutManager linearLayoutManagerCo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);

        sharedPreferences = getApplicationContext().getSharedPreferences("Connecter", MODE_PRIVATE);

        ////Initialise la connexion
            DBManagerUtilisateur.init(this);
            dbManagerUtilisateur = DBManagerUtilisateur.getInstance();
            DBManagerLangue.init(this);
            dbManagerLangue = DBManagerLangue.getInstance();
            DBManagerEtat.init(this);
            dbManagerEtat = DBManagerEtat.getInstance();
            DBManagerCollection.init(this);
            dbManagerCollection = DBManagerCollection.getInstance();
            DBManagerCategorie.init(this);
            dbManagerCategorie = DBManagerCategorie.getInstance();
            recupererUser();
            createDataOnDownload();

        ////Navigation Menu + Toolbar
            NavigationView navigationView = findViewById(R.id.nav_view);
            ////Test pour le haeder du menu
            //View inflaterView = getLayoutInflater().inflate(R.layout.nav_header,null);
            //TextView nom = (TextView) inflaterView.findViewById(R.id.nomUserOpen);
            //ImageView imageProfil = (ImageView) inflaterView.findViewById(R.id.imgProfilOpen);
            View header = navigationView.getHeaderView(0);
            TextView nom = (TextView) header.findViewById(R.id.nomUserOpen);
            ImageView imageProfil = (ImageView) header.findViewById(R.id.imgProfilOpen);
            if(getIntent() !=null) {
                Intent intent = getIntent();
                Utilisateur user = intent.getParcelableExtra(Intent.EXTRA_USER);
                nom.setText(user.getName());
                Log.e("error", user.getName());
                Bitmap image = BitmapFactory.decodeFile(user.getLienImage());
                imageProfil.setImageBitmap(image);
                Log.e("error", user.getLienImage());
            }

            context = this;
            toolbar = findViewById(R.id.toolbar10);
            setSupportActionBar(toolbar);



            drawer = findViewById(R.id.drawerLayout);
            ////Test pour le haeder du menu
            //NavigationView navigationView = findViewById(R.id.nav_view);

            //navigationView.addHeaderView(inflaterView);
            navigationView.setNavigationItemSelectedListener(this);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,toolbar, R.string.ouvert, R.string.fermé);

            drawer.addDrawerListener(toggle);
            toggle.syncState();
        ////Fin

        ////Code page
            lesCollections = dbManagerCollection.getAllCollectionsByUser(user);

            ////Les categories
            // chercher l'element qui a l'id my_recycler_view
            /*recyclerViewCa = findViewById(R.id.my_recycler_view_cate);
            // change pas la taille de la liste (deroulement)
            recyclerViewCa.setHasFixedSize(true);

            linearLayoutManagerCa = new LinearLayoutManager(this);
            //raffrechir affichage
            recyclerViewCa.setLayoutManager(linearLayoutManagerCa);

            adapterCa = new RecyclerViewAdapterCate(R.layout.recycler_view_collection, lesCategories,this);
            recyclerViewCa.setAdapter(adapterCa);*/

            ////Les collections
            //View inflaterViewColl = getLayoutInflater().inflate(R.layout.recycler_view_collection,null);
            // chercher l'element qui a l'id my_recycler_view
            recyclerViewCo = findViewById(R.id.my_recycler_view_coll);
            // change pas la taille de la liste (deroulement)
            recyclerViewCo.setHasFixedSize(true);

            linearLayoutManagerCo = new LinearLayoutManager(this);
            //raffrechir affichage
            recyclerViewCo.setLayoutManager(linearLayoutManagerCo);

            adapterCo = new RecyclerViewAdapterColl(R.layout.recycler_view_collection, lesCollections,this);
            recyclerViewCo.setAdapter(adapterCo);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){

            case R.id.profil:

                break;
            case R.id.collection:
                SharedPreferences preferences2 = context.getSharedPreferences(connecter, Context.MODE_PRIVATE);
                Connecter = preferences2.getBoolean(connec, bool);
                int id2 = preferences2.getInt(idC, 0);
                if(Connecter) {
                    Toast.makeText(this, "Vous êtes déjà sur la page collection", Toast.LENGTH_SHORT).show();
                }else{
                    final Intent intentDeco = new Intent(this, MainActivity.class);
                    startActivity(intentDeco);
                    finish();
                }
                break;

            case R.id.collectionperso:
                SharedPreferences preferences3 = context.getSharedPreferences(connecter, Context.MODE_PRIVATE);
                Connecter = preferences3.getBoolean(connec, bool);
                int id3 = preferences3.getInt(idC, 0);
                if(Connecter) {
                    final Intent intentCollectionPerso = new Intent(this, ActivityCollectionPerso.class);
                    intentCollectionPerso.putExtra(Intent.EXTRA_USER, user);
                    startActivity(intentCollectionPerso);
                    finish();
                }else{
                    final Intent intentDeco = new Intent(this, MainActivity.class);
                    startActivity(intentDeco);
                    finish();
                }
                break;

            case R.id.carte:

                break;

            case R.id.deconnexion:
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                Intent intentDeconnexion = new Intent(ActivityCollection.this, MainActivity.class);
                startActivity(intentDeconnexion);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            return;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        menu.findItem(R.id.titleMenu).setTitle("Collection");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.action_add_coll){
            final Intent intentAddCollection = new Intent(this, ActivityCreateCollection.class);
            intentAddCollection.putExtra(Intent.EXTRA_USER, user);
            startActivity(intentAddCollection);
        }
        if(id == R.id.action_add_coll_perso){
            final Intent intentAddCollectionPerso = new Intent(this, ActivityCreateCollectionPerso.class);
            intentAddCollectionPerso.putExtra(Intent.EXTRA_USER, user);
            startActivity(intentAddCollectionPerso);
        }
        if(id == R.id.action_add_carte){
            final Intent intentAddCarte = new Intent(this, ActivityCreateCarte.class);
            intentAddCarte.putExtra(Intent.EXTRA_USER, user);
            startActivity(intentAddCarte);
        }
        if(id == R.id.action_add_categorie){
            final Intent intentAddCategorie = new Intent(this, ActivityCreateCategorie.class);
            intentAddCategorie.putExtra(Intent.EXTRA_USER, user);
            startActivity(intentAddCategorie);
        }
        if(id == R.id.action_add_langue){
            final Intent intentAddLangue = new Intent(this, ActivityCreateLangue.class);
            intentAddLangue.putExtra(Intent.EXTRA_USER, user);
            startActivity(intentAddLangue);
        }
        return super.onOptionsItemSelected(item);
    }

    public void recupererUser(){
        if(getIntent() !=null) {
            Bundle data = getIntent().getExtras();
            user = data.getParcelable(getIntent().EXTRA_USER);
            Toast.makeText(this, "Bienvenue " + user.getName(), Toast.LENGTH_SHORT).show();
            Log.e("error", user.getId()+"");
        }else{
            Toast.makeText(this, "Error intent ! ", Toast.LENGTH_SHORT).show();
        }
    }

    public void createDataOnDownload(){
        Log.e("Error", user.getId()+" - "+user.getName());
        langues = dbManagerLangue.getAllLanguesByUser(user);
        etats = dbManagerEtat.getAllEtats();
        categories = dbManagerCategorie.getAllCategoriesByUser(user);
        if(langues.size()<2){
            Langue l = new Langue("Francais",user);
            Langue l2 = new Langue("Anglais",user);
            Langue l3 = new Langue("Coréen",user);
            Langue l4 = new Langue("Japonnais",user);
            Toast.makeText(this, "Nom : " +user.getName(), Toast.LENGTH_SHORT).show();
            dbManagerLangue.createLangue(l);
            dbManagerLangue.createLangue(l2);
            dbManagerLangue.createLangue(l3);
            dbManagerLangue.createLangue(l4);
        }else{

        }
        if(etats.size()<2){
            Etat et1 = new Etat("Neuf");
            Etat et2 = new Etat("Tres bon état");
            Etat et3 = new Etat("Bon état");
            Etat et4 = new Etat("Etat moyen");
            Etat et5 = new Etat("Mauvais état");
            Etat et6 = new Etat("Tres Abimé");
            dbManagerEtat.createEtat(et1);
            dbManagerEtat.createEtat(et2);
            dbManagerEtat.createEtat(et3);
            dbManagerEtat.createEtat(et4);
            dbManagerEtat.createEtat(et5);
            dbManagerEtat.createEtat(et6);
        }
        if(categories.size()<2){
            Categorie cat1 = new Categorie("Wizards",false,user);
            Categorie cat2 = new Categorie("Ex",false,user);
            Categorie cat3 = new Categorie("Diamant & Perle",false,user);
            Categorie cat4 = new Categorie("Platine",false,user);
            Categorie cat5 = new Categorie("HeartGold & SoulSilver",false,user);
            Categorie cat6 = new Categorie("Appel des légendes",false,user);
            Categorie cat7 = new Categorie("Noir & Blanc",false,user);
            Categorie cat8 = new Categorie("X & Y",false,user);
            Categorie cat9 = new Categorie("Soleil & Lune",false,user);
            dbManagerCategorie.createCategorie(cat1);
            dbManagerCategorie.createCategorie(cat2);
            dbManagerCategorie.createCategorie(cat3);
            dbManagerCategorie.createCategorie(cat4);
            dbManagerCategorie.createCategorie(cat5);
            dbManagerCategorie.createCategorie(cat6);
            dbManagerCategorie.createCategorie(cat7);
            dbManagerCategorie.createCategorie(cat8);
            dbManagerCategorie.createCategorie(cat9);
        }
    }
}

