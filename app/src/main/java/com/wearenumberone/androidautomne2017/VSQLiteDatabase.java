package com.wearenumberone.androidautomne2017;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Created by V-ed on 2017-12-08.
 */

public class VSQLiteDatabase {

    private static final int VERSION = 1;
    private static final String NOM_BD = "WeAreNumberOne.db";

    private class VSQLiteDatabaseHelper extends SQLiteOpenHelper {

        private ArrayList<Table> tables;

        public VSQLiteDatabaseHelper(Context context, SQLiteDatabase.CursorFactory factory, ArrayList<Table> tables) {

            super(context, NOM_BD, factory, VERSION);

            this.tables = tables;

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            for (Table table : this.tables)
                db.execSQL(table.getTableCreationScript());
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            for (Table table : this.tables)
                db.execSQL("DROP TABLE " + table.getName());

            onCreate(db);
        }
    }

    private ArrayList<Table> tables;

    private SQLiteDatabase db;
    private VSQLiteDatabaseHelper dbHelper;

    public VSQLiteDatabase(Context context, ArrayList<Table> tables) {

        this.tables = tables;

        for (Table table : tables)
            table.setDatabase(this);

        dbHelper = new VSQLiteDatabaseHelper(context, null, tables);

    }

    public VSQLiteDatabase(Context context, Table[] tables) {
        this(context, new ArrayList<>(Arrays.asList(tables)));
    }


    private void openForWrite() {
        db = dbHelper.getWritableDatabase();
    }

    private void openForRead() {
        db = dbHelper.getReadableDatabase();
    }

    public void close() {
        db.close();
    }

    public long insertInto(Table table, ContentValues content) {

        this.openForWrite();
        long returnValue = db.insert(table.getName(), null, content);
        this.close();

        return returnValue;

    }

    public Cursor queryAll(Table table) {

        String[] columns = table.getColumnNames();

        this.openForRead();

        Cursor c = db.query(table.getName(), columns,
                null, null, null, null, columns[0]);

        if (c.getCount() == 0) {
            c.close();
            return null;
        }

        return c;
    }

    public Table table(String tableName) {

        for (Table table : tables) {

            if (table.getName().equals(tableName))
                return table;

        }

        throw new NoSuchElementException("No table with the name \"" + tableName + "\" was found.");

    }

}
