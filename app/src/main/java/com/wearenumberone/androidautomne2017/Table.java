package com.wearenumberone.androidautomne2017;

import android.content.ContentValues;
import android.database.Cursor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by V-ed on 2017-12-08.
 */

public abstract class Table<T> implements Serializable {

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

        String params;

        public Column(String columnName, Type type, boolean notNull, String params) {
            this.name = columnName;
            this.type = type;
            this.notNull = notNull;
            this.params = params;
        }

        public Column(String columnName, Type type, String params) {
            this(columnName, type, true, params);
        }

        public Column(String columnName, Type type, boolean notNull) {
            this(columnName, type, notNull, null);
        }

        public Column(String columnName, Type type) {
            this(columnName, type, null);
        }
    }

    private ArrayList<Column> columns;

    private VSQLiteDatabase db;

    protected Table() {
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
            Column idColumn = new Column("id", Column.Type.INT, false, "PRIMARY KEY AUTOINCREMENT");
            this.columns.add(0, idColumn);
        }

    }

    protected void setDatabase(VSQLiteDatabase db) {
        this.db = db;
    }

    protected VSQLiteDatabase getDatabase() {
        return this.db;
    }

    protected VSQLiteDatabase getDb() {
        return this.getDatabase();
    }

    public ArrayList<Column> getColumns() {
        return this.columns;
    }

    public String[] getColumnNames() {

        ArrayList<String> columnNames = new ArrayList<>();

        for (Column column : getColumns()) {
            columnNames.add(column.name);
        }

        return columnNames.toArray(new String[0]);

    }

    public String getTableCreationScript() {

        StringBuilder sb = new StringBuilder();

        sb.append("CREATE TABLE");
        sb.append(" ");
        sb.append(this.getName());
        sb.append("(");

        for (Column column : columns) {

            sb.append(column.name);
            sb.append(" ");
            sb.append(column.type.toString());

            if (column.notNull)
                sb.append(" NOT NULL");

            if (column.params != null) {
                sb.append(" ");
                sb.append(column.params);
            }

            sb.append(", ");

        }

        sb.delete(sb.length() - 2, sb.length());

        sb.append(");");

        return sb.toString();

    }

    public void insertEntity(T entity) {

        Object[] values = this.convertEntity(entity);

        this.addObject(values);

    }

    private long addObject(Object... values) {

        if (columns.size() - 1 != values.length)
            throw new IllegalArgumentException("The number of values entered to add an object in the class "
                    + this.getClass().getSimpleName()
                    + " does not equals to the number of columns (there should be "
                    + (columns.size() - 1)
                    + " values passed as parameter).");

        ContentValues content = new ContentValues();

        for (int i = 0; i < values.length; i++) {

            Object value = values[i];

            if (value instanceof String)
                content.put(columns.get(i + 1).name, (String) values[i]);
            else if (value instanceof Integer)
                content.put(columns.get(i + 1).name, (Integer) values[i]);

        }

        return db.insertInto(this, content);

    }

    public ArrayList<T> queryAll() throws Exception {

        Cursor c = db.queryAll(this);

        if (c == null)
            return null;

        if (c.getColumnCount() != getColumns().size())
            throw new Exception("Query did not return expected column count. In theory, this exception should never occur; praise some dark wizard if you get it.");

        return this.convertResultSet(c);

    }

    public ArrayList<T> query(QueryElem... queryElements) throws Exception {

        Cursor c = db.query(this, queryElements);

        if (c == null)
            return null;

        if (c.getColumnCount() != getColumns().size())
            throw new Exception("Query did not return expected column count. In theory, this exception should never occur; praise some dark wizard if you get it.");

        return this.convertResultSet(c);

    }

    public abstract String getName();

    protected abstract Column[] getRawColumns();

    protected abstract ArrayList<T> convertResultSet(Cursor c);

    public abstract Object[] convertEntity(T entity);

}
