package com.wearenumberone.androidautomne2017;

/**
 * Created by V-ed on 2017-12-11.
 */

public class QueryElem {

    public enum Type {
        SELECT, WHERE, GROUP, ORDER
    }

    private QueryElem(Type type, Object data){
        this.type = type;
        this.data = data;
    }

    private Type type;
    private Object data;

    public static QueryElem SELECT(String... columns) {
        return new QueryElem(Type.SELECT, columns);
    }

    public static QueryElem WHERE(int id){
        Object[] wheres = {
                "id = ?", new String[]{ String.valueOf(id) }
        };

        return new QueryElem(Type.WHERE, wheres);
    }

    public QueryElem WHERE(String[] columns, String[] values) {
        if (columns.length != values.length)
            throw new IllegalArgumentException("Not the same amount of columns and values!");

        String whereClause = "";
        for (int i = 0; i < columns.length; i++) {
            whereClause += columns[i] + " = ?";

            if (i < columns.length - 1)
                whereClause += ", ";
        }

        Object[] wheres = {
                whereClause, values
        };

        return new QueryElem(Type.WHERE, wheres);
    }

    public QueryElem GROUP(String column) {
        return new QueryElem(Type.GROUP, column);
    }

    public QueryElem ORDER(String column) {
        return new QueryElem(Type.ORDER, column);
    }

    public Object getData() {
        return this.data;
    }

    public Type getType() {
        return this.type;
    }

}
