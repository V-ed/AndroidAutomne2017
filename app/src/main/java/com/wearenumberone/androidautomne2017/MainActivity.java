package com.wearenumberone.androidautomne2017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    Button buttonLogin;
    EditText editTextUsername;
    EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Table[] tables = {
                new TableUsers(),
                new TableTechs()
        };

        VSQLiteDatabase db = new VSQLiteDatabase(this, tables);

        db.table(TableUsers.TABLE_NAME).insertEntity(new User("test@gmail.com", "lol", "234-567-7890"));
        db.table(TableTechs.TABLE_NAME).insertEntity(new Technicien("Bob", "bob@gmail.com", "password", "123-456-7890"));

        TextView test = findViewById(R.id.test);

        try{

            User user = (User)db.table("users").queryAll().get(0);

            test.setText("Hello, " + user.getEmail() + "!");

        }
        catch (Exception e){

            test.setText(":(");

            e.printStackTrace();

        }

        buttonLogin =  findViewById(R.id.idButtonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editTextUsername = findViewById(R.id.idEditTextUsername);
                editTextPassword = findViewById(R.id.idEditTextPassword);
                if(editTextUsername.getText().toString().equalsIgnoreCase("toto") && editTextPassword.getText().toString().equalsIgnoreCase("toto")){
                    Intent intent = new Intent(MainActivity.this, Planification.class);
                    startActivity(intent);
                }

            }
        });

    }

}
