package br.com.android.menus.db;

import android.content.Context;

public class LinhaDAO extends BaseDAO {
    public static final String C_NAME = "name";
    public static final String C_ESTABELECIMENTO_ID = "estabelecimento_id";

    public static final String TABLE_NAME = "linhas";

    public static final String CREATE_TABLE = "CREATE TABLE "
            + TABLE_NAME + " ( "
            + C_ID + " INTEGER PRIMARY KEY, "
            + C_NAME + " TEXT, "
            + C_ESTABELECIMENTO_ID + " INTEGER, "
            + "FOREIGN KEY(" + C_ESTABELECIMENTO_ID + ") REFERENCES " + EstabelecimentoDAO.TABLE_NAME + "(" + EstabelecimentoDAO.C_ID + ") "
            + "); ";

    public LinhaDAO(Context context) {
        super(context);
    }

    @Override
    String TAG() {
        return null;
    }

    @Override
    String OBJECT() {
        return "linha";
    }

    @Override
    String TABLE() {
        return TABLE_NAME;
    }
}
