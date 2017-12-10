package com.wearenumberone.androidautomne2017;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Table[] tables = {
                new TableUsers()
        };

        VSQLiteDatabase db = new VSQLiteDatabase(this, tables);

        db.table(TableUsers.TABLE_NAME).insertEntity(new User("test@gmail.com", "lol", "234-567-7890"));

        TextView test = findViewById(R.id.test);

        try{

            User user = (User)db.table("users").queryAll().get(0);

            test.setText("Hello, " + user.getEmail() + "!");

        }
        catch (Exception e){

            test.setText(":(");

            e.printStackTrace();

        }

    }

}
