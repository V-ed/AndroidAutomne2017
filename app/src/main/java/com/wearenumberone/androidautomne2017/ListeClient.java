package com.wearenumberone.androidautomne2017;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by 201412728 on 2017-12-11.
 */



public class ListeClient extends AppCompatActivity {

    Button intervention1;
    Button intervention2;
    Button intervention3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listejournal);
        TextView txtTechnicien = (TextView) findViewById(R.id.idTextTechnicien);
        Intent intent = getIntent();
        Technicien technicien = (Technicien) intent.getSerializableExtra("fanclub");
        txtTechnicien.setText(technicien.getEmail() + " " + technicien.getName());

        intervention1 = findViewById(R.id.buttonIntervention1);
        intervention2 = findViewById(R.id.buttonIntervention2);
        intervention3 = findViewById(R.id.buttonIntervention3);


        intervention1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListeClient.this, PunchOut.class);
                startActivity(intent);
            }
        });

        intervention2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListeClient.this, PunchOut.class);
                startActivity(intent);
            }
        });

        intervention3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListeClient.this, PunchOut.class);
                startActivity(intent);
            }
        });
    }


}
