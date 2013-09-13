package br.com.android.menus.db;

import android.content.Context;

/**
 * Created by Robson on 11/08/13.
 */
public class ProdutoDAO extends BaseDAO {
    public static final String C_NAME = "name";
    public static final String C_DESCRIPTION = "description";
    public static final String C_PRICE = "price";
    public static final String C_LINHA_ID = "linha_id";
    public static final String C_ESTABELECIMENTO_ID = "estabelecimento_id";
    public static final String C_INATIVO = "inativo";

    public static final String TABLE_NAME = "produtos";

    public static final String CREATE_TABLE = "CREATE TABLE "
            + TABLE_NAME + " ( "
            + C_ID + " INTEGER PRIMARY KEY, "
            + C_NAME + " TEXT, "
            + C_DESCRIPTION + " TEXT, "
            + C_PRICE + " REAL, "
            + C_INATIVO + " INTEGER, "
            + C_LINHA_ID + " INTEGER, "
            + C_ESTABELECIMENTO_ID + " INTEGER, "
            + "FOREIGN KEY(" + C_LINHA_ID + ") REFERENCES " + LinhaDAO.TABLE_NAME + "(" + LinhaDAO.C_ID + "), "
            + "FOREIGN KEY(" + C_ESTABELECIMENTO_ID + ") REFERENCES " + EstabelecimentoDAO.TABLE_NAME + "(" + EstabelecimentoDAO.C_ID + ") "
            + "); ";

    public ProdutoDAO(Context context) {
        super(context);
    }

    @Override
    String TAG() {
        return null;
    }

    @Override
    String OBJECT() {
        return "produto";
    }

    @Override
    String TABLE() {
        return TABLE_NAME;
    }
}
