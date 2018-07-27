package com.sandro.openalprsample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class panelAdministrativo extends AppCompatActivity {

    private Button bComunity, bOwner, bOwnerShip, bVehicle, bAccess;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_administrativo);

        bComunity = (Button)findViewById(R.id.bComunity);
        bOwner = (Button)findViewById(R.id.bOwner);
        bOwnerShip = (Button) findViewById(R.id.bOwnerShip);
        bVehicle = (Button)findViewById(R.id.bVehicle);
        bAccess = (Button)findViewById(R.id.bAccess);



    }


    public void comunity(View vista) throws Exception {

        Intent view = new Intent(this, Comunity.class);
        startActivity(view);

    }

    public void owner(View vista) throws Exception {

        Intent view = new Intent(this, Owner.class);
        startActivity(view);

    }

    public void ownerShip (View vista) throws Exception {

        Intent view = new Intent(this, OwnerShip.class);
        startActivity(view);

    }


    public void vehicle (View vista) throws Exception {

        Intent view = new Intent(this, Vehicle.class);
        startActivity(view);

    }

    public void access (View vista) throws Exception {

        Intent view = new Intent(this, AccessHistory.class);
        startActivity(view);

    }


}
