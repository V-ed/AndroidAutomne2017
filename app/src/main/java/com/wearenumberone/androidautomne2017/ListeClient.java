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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listejournal);
        TextView txtTechnicien = (TextView) findViewById(R.id.idTextTechnicien);
        Intent intent = getIntent();
        final Technicien technicien = (Technicien) intent.getSerializableExtra("technicien");
        txtTechnicien.setText(technicien.getEmail() + " " + technicien.getName());

        Button itemPlanification = findViewById(R.id.idButtonItemPlanification);

        itemPlanification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentPlanification = new Intent(ListeClient.this, Planification.class);
                intentPlanification.putExtra("technicien", technicien);
                startActivity(intentPlanification);
            }
        });

        Button punchOut = findViewById(R.id.idButtonPunchOut);

        punchOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentPlanification = new Intent(ListeClient.this, PunchOut.class);
                intentPlanification.putExtra("technicien", technicien);
                startActivity(intentPlanification);
            }
        });
    }
}
