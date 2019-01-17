package com.example.lucas.poke2c.popup;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.example.lucas.poke2c.R;

public class PopupCollection extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popup_collection);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.6));


    }
}
