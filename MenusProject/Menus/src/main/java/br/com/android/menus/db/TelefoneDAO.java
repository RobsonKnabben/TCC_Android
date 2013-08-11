package br.com.android.menus.db;

import android.content.Context;

/**
 * Created by Robson on 11/08/13.
 */
public class TelefoneDAO extends BaseDAO {
    public static final String C_NUMERO = "numero";
    public static final String C_ESTABELECIMENTO_ID = "estabelecimento_id";

    public static final String TABLE_NAME = "telefones";

    public static final String CREATE_TABLE = "CREATE TABLE "
            + TABLE_NAME + " ( "
            + C_ID + " INTEGER PRIMARY KEY, "
            + C_NUMERO + " TEXT, "
            + C_ESTABELECIMENTO_ID + " INTEGER, "
            + "FOREIGN KEY(" + C_ESTABELECIMENTO_ID + ") REFERENCES " + EstabelecimentoDAO.TABLE_NAME + "(" + EstabelecimentoDAO.C_ID + ") "
            + "); ";

    public TelefoneDAO(Context context) {
        super(context);
    }

    @Override
    String TAG() {
        return null;
    }

    @Override
    String OBJECT() {
        return "telefone";
    }

    @Override
    String TABLE() {
        return TABLE_NAME;
    }
}
