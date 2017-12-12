package com.wearenumberone.androidautomne2017;

import android.database.Cursor;

import java.util.ArrayList;

/**
 * Created by Malaria on 2017-12-09.
 */

public class TableInterventions extends Table<Intervention> {

    public static final String TABLE_NAME = "interventions";

    public TableInterventions() {
        super();
    }

    @Override
    public String getName() {
        return TABLE_NAME;
    }

    @Override
    protected Column[] getRawColumns() {

        Column[] columns = {
                new Column("img", Column.Type.INT),
                new Column("tabType", Column.Type.TEXT),
                new Column("nom", Column.Type.TEXT),
                new Column("prenom", Column.Type.TEXT),
                new Column("heureDebut", Column.Type.TEXT),
        };

        return columns;

    }

    @Override
    protected ArrayList<Intervention> convertResultSet(Cursor c) {

        ArrayList<Intervention> listeInterventions = new ArrayList<>();
        while (c.moveToNext()) {

            Intervention intervention = new Intervention(c.getInt(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5));

            listeInterventions.add(intervention);

        }
        c.close();

        return listeInterventions;

    }

    @Override
    public Object[] convertEntity(Intervention entity) {

        Object[] values = {
            entity.getImg(),
            entity.getTabType(),
            entity.getNom(),
            entity.getPrenom(),
            entity.getHeureDebut()
        };

        return values;

    }

}
