package br.com.android.menus.db;

import android.content.Context;

/**
 * Created by Robson on 22/07/13.
 */
public class RamoDAO extends BaseDAO {
    public static final String C_NAME = "name";

    public static final String TABLE_NAME = "ramos";

    public static final String CREATE_TABLE = "CREATE TABLE "
            + TABLE_NAME + " ( "
            + C_ID + " INTEGER PRIMARY KEY, "
            + C_NAME + " TEXT); ";

    public RamoDAO(Context context) {
        super(context);
    }

    @Override
    String TAG() {
        return null;
    }

    @Override
    String OBJECT() {
        return "ramo";
    }

    @Override
    String TABLE() {
        return TABLE_NAME;
    }
}
