package com.wearenumberone.androidautomne2017;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Created by patof on 2017-10-20.
 */

public class Planification extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_planification);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroupTypes);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                ImageView imageView = (ImageView) findViewById(R.id.imageView);
                RadioButton radioButton = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
                switch (radioButton.getText().toString()) {
                    case "Nouveau Service" :
                        imageView.setImageResource(R.drawable.facebook);
                        break;
                    case "Modification de Service" :
                        imageView.setImageResource(R.drawable.google);
                        break;
                    case "Dépannage" :
                        imageView.setImageResource(R.drawable.camera);
                        break;
                }


            }
        });
    }


}
