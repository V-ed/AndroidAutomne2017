package com.wearenumberone.androidautomne2017;

import android.database.Cursor;

import java.util.ArrayList;

/**
 * Created by V-ed on 2017-12-09.
 */

public class TableUsers extends Table {

    public TableUsers(VSQLiteDatabase db) {
        super(db);
    }

    @Override
    public String getName() {
        return "users";
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

    public void newUser(User user) {

        Object[] values = {
                user.getEmail(),
                user.getPassword(),
                user.getTelephone()
        };

        this.addObject(values);

    }

    @Override
    protected ArrayList<User> convertResultSet(Cursor c) {

        ArrayList<User> listUsers = new ArrayList<>();
        while (c.moveToNext()) {

            User fanClub = new User(c.getString(1), c.getString(2), c.getInt(3));

            listUsers.add(fanClub);

        }
        c.close();

        return listUsers;

    }

}
