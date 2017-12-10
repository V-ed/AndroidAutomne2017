package com.wearenumberone.androidautomne2017;

import android.database.Cursor;

import java.util.ArrayList;

/**
 * Created by V-ed on 2017-12-09.
 */

public class TableUsers extends Table<User> {

    public static final String TABLE_NAME = "users";

    public TableUsers() {
        super();
    }

    @Override
    public String getName() {
        return TABLE_NAME;
    }

    @Override
    protected Column[] getRawColumns() {

        Column[] columns = {
                new Column("email", Column.Type.TEXT),
                new Column("password", Column.Type.TEXT),
                new Column("telephone", Column.Type.TEXT),
        };

        return columns;

    }

    @Override
    protected ArrayList<User> convertResultSet(Cursor c) {

        ArrayList<User> listUsers = new ArrayList<>();
        while (c.moveToNext()) {

            User user = new User(c.getString(1), c.getString(2), c.getString(3));

            listUsers.add(user);

        }
        c.close();

        return listUsers;

    }

    @Override
    public Object[] convertEntity(User user) {

        Object[] values = {
                user.getEmail(),
                user.getPassword(),
                user.getTelephone()
        };

        return values;

    }

}
