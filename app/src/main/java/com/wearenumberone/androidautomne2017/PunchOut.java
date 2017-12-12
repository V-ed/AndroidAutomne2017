package com.wearenumberone.androidautomne2017;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by patof on 2017-10-20.
 */

public class PunchOut extends AppCompatActivity {

    Button punchOut;
    Intervention intervention;
    TextView textViewPrenom;
    TextView textViewNom;
    TextView textViewIntervention;
    VSQLiteDatabase db;

/*   public PunchOut(VSQLiteDatabase db) {
        super();
        this.db = db;
    }*/


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.punchout);
        punchOut = findViewById(R.id.buttonPunchOut);
        textViewPrenom = findViewById(R.id.textViewPrenomClient);
        textViewNom = findViewById(R.id.textViewNomCLient);
        textViewIntervention = findViewById(R.id.textViewIntervention);




        punchOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Delete intervention.
            }
        });

    }


}
