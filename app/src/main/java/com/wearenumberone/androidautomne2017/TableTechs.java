package com.wearenumberone.androidautomne2017;

import android.database.Cursor;

import java.util.ArrayList;

/**
 * Created by V-ed on 2017-12-10.
 */

public class TableTechs extends Table<Technicien> {

    public static final String TABLE_NAME = "techniciens";

    public TableTechs(){
        super();
    }

    @Override
    public String getName() {
        return TABLE_NAME;
    }

    @Override
    protected Column[] getRawColumns() {

        Column[] columns = {
                new Column("name", Column.Type.TEXT),
                new Column("email", Column.Type.TEXT),
                new Column("password", Column.Type.TEXT),
                new Column("telephone", Column.Type.TEXT),
        };

        return columns;

    }

    @Override
    protected ArrayList<Technicien> convertResultSet(Cursor c) {

        ArrayList<Technicien> listTechs = new ArrayList<>();
        while (c.moveToNext()) {

            Technicien tech = new Technicien(c.getString(1), c.getString(2), c.getString(3), c.getString(4));

            listTechs.add(tech);

        }
        c.close();

        return listTechs;

    }

    @Override
    public Object[] convertEntity(Technicien entity) {

        Object[] values = {
                entity.getName(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getTelephone()
        };

        return values;

    }

}
