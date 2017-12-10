package com.wearenumberone.androidautomne2017;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by V-ed on 2017-12-08.
 */

public class VSQLiteDatabase {

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
                db.execSQL("DROP TABLE " + table.getTableName());

            onCreate(db);
        }
    }

    private static final int VERSION = 1;
    private static final String NOM_BD = "WeAreNumberOne.db";

    private ArrayList<Table> tables;

    private SQLiteDatabase db;
    private VSQLiteDatabaseHelper dbHelper;

    public VSQLiteDatabase(Context context, ArrayList<Table> tables){

        this.tables = tables;

        dbHelper = new VSQLiteDatabaseHelper(context, null, tables);

    }

    public VSQLiteDatabase(Context context, Table... tables) {
        this(context, new ArrayList<>(Arrays.asList(tables)));
    }

    protected long insertInto(Table table, ContentValues content) {
        return db.insert(table.getTableName(), null, content);
    }

}
