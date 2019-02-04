package com.example.lucas.poke2c.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lucas.poke2c.MainActivity;
import com.example.lucas.poke2c.R;
import com.example.lucas.poke2c.database.DBManagerUtilisateur;
import com.example.lucas.poke2c.model.Utilisateur;


public class ActivityCreateUser extends AppCompatActivity {
    private DBManagerUtilisateur dbManagerUtilisateur;
    private Utilisateur util;
    private Toolbar toolbar;
    private ImageView img;
    private TextView chemin;
    private boolean val_img = false;
    private String imgPath;
    Context context = this;

    private EditText nom;
    private EditText desc;
    private EditText login;
    private EditText mdp;

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

        nom = findViewById(R.id.nomUser);
        desc = findViewById(R.id.descrUser);
        login = findViewById(R.id.loginUser);
        mdp = findViewById(R.id.mdpUser);
        chemin = findViewById(R.id.chemin);
        img = findViewById(R.id.imgProfil);

        int d2 = getResources().getIdentifier("ic_launcher", "mipmap", getPackageName());
        Drawable d = getResources().getDrawable(d2);
        img.setImageDrawable(d);
        imgPath = "";
        Button btnCreate = findViewById(R.id.btnCreateUser);

        createOnClickPhoto();

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!nom.getText().toString().equals("") && !login.getText().toString().equals("") && !mdp.getText().toString().equals("")) {
                    if(nom.getText().length() < 6 && login.getText().length() < 6 && mdp.getText().length() < 6){
                        Toast.makeText(ActivityCreateUser.this, "Un des champs saisis est trop cours, nom : 4 caractères, login : 6 caractères, mdp : 6 caractères !", Toast.LENGTH_LONG).show();
                   }else{
                        if(val_img = false){
                            Toast.makeText(ActivityCreateUser.this, "Il doit y avoir une image de profil séléctionnée !", Toast.LENGTH_LONG).show();
                        }else {
                            DBManagerUtilisateur.init(context);
                            dbManagerUtilisateur = DBManagerUtilisateur.getInstance();
                            final Intent create = new Intent(getApplicationContext(), MainActivity.class);

                            util = new Utilisateur(nom.getText().toString(), desc.getText().toString(), login.getText().toString(), mdp.getText().toString(), imgPath);
                            dbManagerUtilisateur.createUtilisateur(util);
                            startActivity(create);
                            finish();
                        }
                   }
                } else {
                    //Toast.makeText(ActivityCreateUser.this, "Tous les champs ne sont pas remplis, veuillez tous les saisir ! ", Toast.LENGTH_LONG).show();
                    Toast.makeText(ActivityCreateUser.this, "Un des champs de saisi n'est pas rempli ! ", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    private void createOnClickPhoto(){
        img.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 1);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode==RESULT_OK){
            Uri selectedImage = data.getData();
            String[] filePathColonne = {MediaStore.Images.Media.DATA};
            Cursor cursor = this.getContentResolver().query(selectedImage, filePathColonne, null,null,null);
            cursor.moveToFirst();
            int colonneIndex = cursor.getColumnIndex(filePathColonne[0]);
            imgPath = cursor.getString(colonneIndex);
            cursor.close();
            Bitmap image = BitmapFactory.decodeFile(imgPath);
            img.setImageBitmap(image);
            chemin.setText(imgPath);
            Toast.makeText(this, "Chemin : " + imgPath, Toast.LENGTH_SHORT).show();
            val_img = true;
        }else{
            Toast.makeText(this, "Aucune image séléctionnée ! ", Toast.LENGTH_SHORT).show();
        }
    }
}
