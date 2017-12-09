package com.wearenumberone.androidautomne2017;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by V-ed on 2017-12-08.
 */

public class VSQLiteDatabase extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String NOM_BD = "WeAreNumberOne.db";

    private ArrayList<Table> tables;

    private SQLiteDatabase db;

    public VSQLiteDatabase(Context context, SQLiteDatabase.CursorFactory factory, Table... tables) {
        super(context, NOM_BD, factory, VERSION);

        this.tables = new ArrayList<>(Arrays.asList(tables));
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for (Table table : tables)
            db.execSQL(table.getTableCreationScript());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (Table table : tables)
            db.execSQL("DROP TABLE " + table.getTableName());

        onCreate(db);
    }

}
