package com.wearenumberone.androidautomne2017;

import android.content.ContentValues;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by V-ed on 2017-12-08.
 */

public abstract class Table implements Serializable {

    protected static class Column {

        protected enum Type {
            INT("INTEGER"),
            TEXT("TEXT");

            private final String name;

            Type(String s) {
                name = s;
            }

            public boolean equalsName(String otherName) {
                return name.equals(otherName);
            }

            public String toString() {
                return this.name;
            }
        }

        String name;
        Type type;
        boolean notNull;

        public Column(String columnName, Type type, boolean notNull) {
            this.name = columnName;
            this.type = type;
            this.notNull = notNull;
        }

        public Column(String columnName, Type type) {
            this(columnName, type, true);
        }
    }

    private ArrayList<Column> columns;

    private VSQLiteDatabase db;

    protected Table(VSQLiteDatabase db) {

        this.db = db;

        this.initializeTable(this.getRawColumns());

    }

    private void initializeTable(Column... columns) {

        this.columns = new ArrayList<>(Arrays.asList(columns));

        boolean canAddDefaultId = true;

        try {
            Column firstColumn = columns[0];

            canAddDefaultId = !firstColumn.name.matches("^id.*");
        } catch (Exception e) {
        }

        if (canAddDefaultId) {
            Column idColumn = new Column("id", Column.Type.INT);
            this.columns.add(0, idColumn);
        }

    }

    protected VSQLiteDatabase getDatabase(){
        return this.db;
    }
    protected VSQLiteDatabase getDb(){
        return this.getDatabase();
    }

    public ArrayList<Column> getColumns(){
        return this.columns;
    }

    public String getTableCreationScript() {

        StringBuilder sb = new StringBuilder();

        sb.append("CREATE TABLE");
        sb.append(" ");
        sb.append(this.getTableName());
        sb.append("(");

        for (Column column : columns) {

            sb.append(column.name);
            sb.append(" ");
            sb.append(column.type.toString());

            if (column.notNull)
                sb.append(" NOT NULL");

            sb.append(", ");

        }

        sb.delete(sb.length() - 2, sb.length());

        sb.append(");");

        return sb.toString();

    }

    protected long addObject(Object... values) {

        if (columns.size() != values.length)
            throw new IllegalArgumentException("The number of values entered to add an object in the class "
                    + this.getClass().getSimpleName()
                    + " does not equals to the number of columns (there should be "
                    + columns.size()
                    + " values passed as parameter).");

        ContentValues content = new ContentValues();

        for (int i = 0; i < columns.size(); i++) {

            Object value = values[i];

            if (value instanceof String)
                content.put(columns.get(i).name, (String) values[i]);
            else if (value instanceof Integer)
                content.put(columns.get(i).name, (Integer) values[i]);

        }

        return db.insertInto(this, content);

    }

    public abstract String getTableName();

    protected abstract Column[] getRawColumns();

}
