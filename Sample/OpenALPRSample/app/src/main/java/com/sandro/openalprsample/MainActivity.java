package com.sandro.openalprsample;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);


    }

    public void reconocimientoPlaca(View vista) throws Exception {

        Intent reconocimiento = new Intent(this, ReconocimientoPlaca.class);
        startActivity(reconocimiento);

    }


    public void panelAdministrativo(View vista){

        Intent reconocimiento = new Intent(this, panelAdministrativo.class);
        startActivity(reconocimiento);


    }



}
