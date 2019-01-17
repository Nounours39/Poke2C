package com.example.lucas.poke2c.popup;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.example.lucas.poke2c.R;

public class PopupCollectionPerso extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popup_collectionperso);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.6));


    }
}
