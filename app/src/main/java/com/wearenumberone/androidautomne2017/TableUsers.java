package com.wearenumberone.androidautomne2017;

/**
 * Created by V-ed on 2017-12-09.
 */

public class TableUsers extends Table {

    public TableUsers() {
        super();
    }

    @Override
    public String getTableName() {
        return "users";
    }

    @Override
    protected Column[] getColumns() {

        Column[] columns = {
                new Column("email", Column.Type.TEXT),
                new Column("password", Column.Type.TEXT),
                new Column("telephone", Column.Type.TEXT),
        };

        return columns;

    }

}
