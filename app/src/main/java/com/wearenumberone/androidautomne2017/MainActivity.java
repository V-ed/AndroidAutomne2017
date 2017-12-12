package com.wearenumberone.androidautomne2017;

import android.content.Intent;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText txtUserName;
    EditText txtPassword;
    Button btnConnexion;
    VSQLiteDatabase db;

    Button buttonLogin;
    EditText editTextUsername;
    EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        txtUserName = findViewById(R.id.idUserName);
        txtPassword = findViewById(R.id.idPassword);
        btnConnexion = findViewById(R.id.idValider);

        Table[] tables = {
                new TableUsers(),
                new TableTechs(),
                new TableInterventions()
        };

        db = new VSQLiteDatabase(this, tables);

        db.table(TableUsers.TABLE_NAME).insertEntity(new User("test@gmail.com", "lol", "234-567-7890"));
        db.table(TableTechs.TABLE_NAME).insertEntity(new Technicien("Bob", "bob@gmail.com", "password", "123-456-7890"));
        db.table(TableTechs.TABLE_NAME).insertEntity(new Technicien("Robert", "robert@gmail.com", "password", "098-765-4321"));
        db.table(TableInterventions.TABLE_NAME).insertEntity((new Intervention(1, "Dépannage", "Attacher mes souliers", "Robert", "1970-01-01")));

        btnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TableTechs table = (TableTechs) db.table(TableTechs.TABLE_NAME);
                Technicien technicien = null;
                try {
                    ArrayList<Technicien> liste = table.queryAll();
                    String userName = txtUserName.getText().toString();
                    String password = txtPassword.getText().toString();
                    for (int i = 0; i < liste.size(); i++) {
                        Technicien t = liste.get(i);
                        if (t.getName().equalsIgnoreCase(userName) && t.getPassword().equals(password)) {
                            technicien = t;
                        }
                    }
                } catch (Exception e) {

                }
                if (technicien != null) {
                    Intent intent = new Intent(MainActivity.this, ListeClient.class);
                    intent.putExtra("technicien", technicien);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "User name and password invalid", Toast.LENGTH_LONG).show();
                }
            }
        });


//        buttonLogin = findViewById(R.id.idButtonLogin);
//
//        buttonLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                editTextUsername = findViewById(R.id.idEditTextUsername);
//                editTextPassword = findViewById(R.id.idEditTextPassword);
//                if (editTextUsername.getText().toString().equalsIgnoreCase("toto") && editTextPassword.getText().toString().equalsIgnoreCase("toto")) {
//                    Intent intent = new Intent(MainActivity.this, Planification.class);
//                    startActivity(intent);
//                }
//
//            }
//        });

    }

   /* try{

        TableTechs table = (TableTechs) db.table(TableTechs.TABLE_NAME);

        Technicien tech = table.query(QueryElem.WHERE(2)).get(0);

        test.setText("Hello, " + tech.getEmail() + "!");

    }
    catch (Exception e){

        test.setText(":(");

        e.printStackTrace();

    } */

}


