package com.example.lucas.poke2c.activity;

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
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lucas.poke2c.MainActivity;
import com.example.lucas.poke2c.R;
import com.example.lucas.poke2c.RecyclerViewAdapterCate;
import com.example.lucas.poke2c.RecyclerViewAdapterColl;
import com.example.lucas.poke2c.RecyclerViewAdapterPerso;
import com.example.lucas.poke2c.database.DBManagerCategorie;
import com.example.lucas.poke2c.database.DBManagerCollection;
import com.example.lucas.poke2c.database.DBManagerCollectionPerso;
import com.example.lucas.poke2c.database.DBManagerEtat;
import com.example.lucas.poke2c.database.DBManagerLangue;
import com.example.lucas.poke2c.database.DBManagerUtilisateur;
import com.example.lucas.poke2c.model.Categorie;
import com.example.lucas.poke2c.model.CollectionN;
import com.example.lucas.poke2c.model.CollectionPerso;
import com.example.lucas.poke2c.model.Etat;
import com.example.lucas.poke2c.model.Langue;
import com.example.lucas.poke2c.model.Utilisateur;

import java.util.List;

public class ActivityCollectionPerso extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DBManagerUtilisateur dbManagerUtilisateur;
    private DBManagerCollectionPerso dbManagerCollectionPerso;
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
    private List<CollectionPerso> lesCollectionsPerso;

    private RecyclerView recyclerViewPerso;
    private RecyclerViewAdapterPerso adapterPerso;
    private LinearLayoutManager linearLayoutManagerPerso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_perso);

        sharedPreferences = getApplicationContext().getSharedPreferences("Connecter", MODE_PRIVATE);

        ////Initialise la connexion
        DBManagerUtilisateur.init(this);
        dbManagerUtilisateur = DBManagerUtilisateur.getInstance();
        DBManagerCollectionPerso.init(this);
        dbManagerCollectionPerso = DBManagerCollectionPerso.getInstance();
        recupererUser();

        ////Navigation Menu + Toolbar
            NavigationView navigationView = findViewById(R.id.nav_view2);
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
            toolbar = findViewById(R.id.toolbar100);
            setSupportActionBar(toolbar);

            drawer = findViewById(R.id.drawerLayout2);
            ////Test pour le haeder du menu
            //NavigationView navigationView = findViewById(R.id.nav_view);

            //navigationView.addHeaderView(inflaterView);
            navigationView.setNavigationItemSelectedListener(this);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,toolbar, R.string.ouvert, R.string.fermé);

            drawer.addDrawerListener(toggle);
            toggle.syncState();
        ////Fin

        ////Code page
        lesCollectionsPerso = dbManagerCollectionPerso.getAllCollectionsPersoByUser(user);

        ////Les collections
        //View inflaterViewColl = getLayoutInflater().inflate(R.layout.recycler_view_collection,null);
        // chercher l'element qui a l'id my_recycler_view
        recyclerViewPerso = findViewById(R.id.my_recycler_view_coll_perso);
        // change pas la taille de la liste (deroulement)
        recyclerViewPerso.setHasFixedSize(true);

        linearLayoutManagerPerso = new LinearLayoutManager(this);
        //raffrechir affichage
        recyclerViewPerso.setLayoutManager(linearLayoutManagerPerso);

        adapterPerso = new RecyclerViewAdapterPerso(R.layout.recycler_view_collection_perso, lesCollectionsPerso,this);
        recyclerViewPerso.setAdapter(adapterPerso);
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
                    final Intent intentCollection = new Intent(this, ActivityCollection.class);
                    intentCollection.putExtra(Intent.EXTRA_USER, user);
                    startActivity(intentCollection);
                    finish();
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
                    Toast.makeText(this, "Vous êtes déjà sur la page collection perso", Toast.LENGTH_SHORT).show();
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
                Intent intentDeconnexion = new Intent(ActivityCollectionPerso.this, MainActivity.class);
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
        menu.findItem(R.id.titleMenu).setTitle("Collection Perso");
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
            Log.e("User", user.getId()+" - " + user.getName());
        }else{
            Toast.makeText(this, "Error intent ! ", Toast.LENGTH_SHORT).show();
        }
    }
}
