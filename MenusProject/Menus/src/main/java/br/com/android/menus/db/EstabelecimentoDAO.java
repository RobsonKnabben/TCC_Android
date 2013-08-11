package br.com.android.menus.db;

import android.content.Context;

public class EstabelecimentoDAO extends BaseDAO {
    public static final String C_NAME = "name";
    public static final String C_DESCRIPTION = "description";
    public static final String C_RAMOS = "ramos";
    public static final String C_IS_FAVORITE = "is_favorite";

    public static final String TABLE_NAME = "estabelecimentos";

    public static final String CREATE_TABLE = "CREATE TABLE "
            + TABLE_NAME + " ( "
            + C_ID + " INTEGER PRIMARY KEY, "
            + C_NAME + " TEXT, "
            + C_DESCRIPTION + " TEXT, "
            + C_IS_FAVORITE + " INTEGER"
            + "); ";


    public EstabelecimentoDAO(Context context) {
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
