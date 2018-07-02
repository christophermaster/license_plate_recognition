package com.sandro.openalprsample;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    //private ImageView reconocimiento;
    private Button bComunity,bOwner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
       // reconocimiento = (ImageView) findViewById(R.id.reconocimiento);
        bComunity = (Button)findViewById(R.id.bComunity);
        bOwner = (Button)findViewById(R.id.bOwner);
    }

    public void reconocimientoPlaca(View vista) throws Exception {

        Intent reconocimiento = new Intent(this, ReconocimientoPlaca.class);
        startActivity(reconocimiento);

    }

    public void comunity(View vista) throws Exception {

        Intent reconocimiento = new Intent(this, Create.class);
        startActivity(reconocimiento);
    }

    public void owner(View vista) throws Exception {

        Intent reconocimiento = new Intent(this, Owner.class);
        startActivity(reconocimiento);
    }



}
