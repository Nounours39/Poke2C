package com.example.lucas.poke2c.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.lucas.poke2c.R;
import com.example.lucas.poke2c.model.Utilisateur;

public class ActivityCollection extends AppCompatActivity {

    private Toolbar toolbar;
    private int id_user;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //definir notre toolbar en tant qu'actionBar
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerToggle = new ActionBarDrawerToggle(this,this.drawerLayout,0,0);


        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        drawerLayout.addDrawerListener(drawerToggle);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ActivityCollection.this, "Menu", Toast.LENGTH_SHORT).show();
                drawerLayout.openDrawer(Gravity.START);
                if(drawerLayout.isDrawerOpen(GravityCompat.START)){
                    drawerLayout.closeDrawer(Gravity.START);
                }else{
                    drawerLayout.openDrawer(Gravity.START);
                }

            }
        });

        if(getIntent() !=null) {
            Intent intent = getIntent();
            Utilisateur user = intent.getParcelableExtra(Intent.EXTRA_USER);
            id_user = user.getId();

        }

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // synchroniser le drawerToggle après la restauration via onRestoreInstanceState
        drawerToggle.syncState();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    private void afficherCacherToolbar() {
        if(toolbar.getAlpha() == 1){ //si alpha==1 alors elle est affichee

            //cacher
            toolbar.animate()
                    .alpha(0) //la rendre invisible
                    .translationY(-toolbar.getHeight()) //la déplacer vers le haut
                    .start();
        }
        else{ //si alpha==0 alors elle est cachee

            //afficher
            toolbar.animate()
                    .alpha(1) //la rendre visible
                    .translationY(0) //retour à la position d'origine
                    .start();
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit:
                return true;
            case R.id.action_add:

                return true;
            case R.id.action_delete:

                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
