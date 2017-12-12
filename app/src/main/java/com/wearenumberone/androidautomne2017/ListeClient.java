package com.wearenumberone.androidautomne2017;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by 201412728 on 2017-12-11.
 */

public class ListeClient extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listejournal);
        TextView txtTechnicien = (TextView) findViewById(R.id.idTextTechnicien);
        Intent intent = getIntent();
        Technicien technicien = (Technicien) intent.getSerializableExtra("fanclub");
        //txtTechnicien.setText(technicien.getEmail() + " " + technicien.getName());
    }
}
