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
                new TableUsers(),
                new TableTechs()
        };

        VSQLiteDatabase db = new VSQLiteDatabase(this, tables);

        db.table(TableUsers.TABLE_NAME).insertEntity(new User("test@gmail.com", "lol", "234-567-7890"));
        db.table(TableTechs.TABLE_NAME).insertEntity(new Technicien("Bob", "bob@gmail.com", "password", "123-456-7890"));
        db.table(TableTechs.TABLE_NAME).insertEntity(new Technicien("Robert", "robert@gmail.com", "password", "098-765-4321"));

        TextView test = findViewById(R.id.test);

        try{

            TableTechs table = (TableTechs) db.table(TableTechs.TABLE_NAME);

            Technicien tech = table.query(QueryElem.WHERE(2)).get(0);

            test.setText("Hello, " + tech.getEmail() + "!");

        }
        catch (Exception e){

            test.setText(":(");

            e.printStackTrace();

        }

    }

}
