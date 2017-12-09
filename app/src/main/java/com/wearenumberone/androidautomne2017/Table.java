package com.wearenumberone.androidautomne2017;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by V-ed on 2017-12-08.
 */

public class Table {

    public enum Type {
        INTEGER, TEXT, DATE
    }

    public class Column {
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

    private String tableName;
    private ArrayList<Column> columns;

    protected Table(String tableName, ArrayList<Column> columns) {

        this.tableName = tableName;

        this.columns = columns;

        Column idColumn = new Column("id", Type.INTEGER);
        this.columns.add(0, idColumn);

    }

    protected Table(String tableName, Column... columns) {
        this(tableName, new ArrayList<>(Arrays.asList(columns)));
    }

    protected Table(String tableName, Object[][] rawColumns) throws IllegalArgumentException, ClassCastException {
        this(tableName, new ArrayList<Column>());

        this.columns = processRawColumns(rawColumns);
    }

    private ArrayList<Column> processRawColumns(Object[][] rawColumns) throws IllegalArgumentException, ClassCastException {
        for (int i = 0; i < rawColumns.length; i++) {
            if (rawColumns[i].length < 2 || rawColumns[i].length > 3) {
                throw new IllegalArgumentException("Raw columns must have between 2 or 3 values : First being the column name, second being it's type and an optional third for removing the non null default behavior.");
            }
        }

        ArrayList<Column> processedColumns = new ArrayList<>();

        for (int i = 0; i < rawColumns.length; i++) {

            Column newColumn;

            switch (rawColumns[i].length) {
                case 2:
                    newColumn = new Column(rawColumns[i][0].toString(), (Type) rawColumns[i][1]);
                    break;
                case 3:
                    newColumn = new Column(rawColumns[i][0].toString(), (Type) rawColumns[i][1], (boolean) rawColumns[i][2]);
                    break;
                default:
                    // how did you even get there
                    throw new IllegalArgumentException("You broke the system somehow...");
            }

            processedColumns.add(newColumn);

        }

        return processedColumns;
    }

    public String getTableName() {
        return this.tableName;
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

}
