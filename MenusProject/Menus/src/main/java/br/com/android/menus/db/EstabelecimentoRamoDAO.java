package br.com.android.menus.db;

import android.content.Context;

/**
 * Created by Robson on 06/08/13.
 */
public class EstabelecimentoRamoDAO extends BaseDAO{
    public static final String C_ESTABELECIMENTO_ID = "estabelecimento_id";
    public static final String C_RAMO_ID = "ramo_id";

    public static final String TABLE_NAME = "estabelecimento_ramo";

    public static final String CREATE_TABLE = "CREATE TABLE "
            + TABLE_NAME + " ( "
            + C_ESTABELECIMENTO_ID + " INTEGER, "
            + C_RAMO_ID + " INTEGER, "
            + "FOREIGN KEY(" + C_ESTABELECIMENTO_ID + ") REFERENCES " + EstabelecimentoDAO.TABLE_NAME + "(" + EstabelecimentoDAO.C_ID + "), "
            + "FOREIGN KEY(" + C_RAMO_ID + ") REFERENCES " + RamoDAO.TABLE_NAME + "(" + RamoDAO.C_ID + ") "
            + "); ";

    public EstabelecimentoRamoDAO(Context context) {
        super(context);
    }

    @Override
    String TAG() {
        return null;
    }

    @Override
    String OBJECT() {
        return null;
    }

    @Override
    String TABLE() {
        return TABLE_NAME;
    }
}
