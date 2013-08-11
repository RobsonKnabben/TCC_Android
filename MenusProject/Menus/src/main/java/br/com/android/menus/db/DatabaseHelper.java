package br.com.android.menus.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = DatabaseHelper.class.getSimpleName();
    private static final String DATABASE_NAME = "data";
    private static final int DATABASE_VERSION = 1;

    private static DatabaseHelper sInstance;
    private SQLiteDatabase mDatabase;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DatabaseHelper getInstance(Context context){
        if (sInstance == null){
            sInstance = new DatabaseHelper(context);
        }
        return sInstance;
    }

    public void OpenDatabase(){
        if (this.mDatabase == null) {
            this.mDatabase = this.getWritableDatabase();
        } else if (!this.mDatabase.isOpen()){
            this.mDatabase = this.getWritableDatabase();
        }
    }

    public SQLiteDatabase getDatabase(){
        this.OpenDatabase();
        return this.mDatabase;
    }

    public void CloseDatabase(){
        this.mDatabase.close();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(TAG, "Creating table: " + RamoDAO.TABLE_NAME + ", sql: " + RamoDAO.CREATE_TABLE);
        sqLiteDatabase.execSQL(RamoDAO.CREATE_TABLE);
        Log.d(TAG, "Creating table: " + EstabelecimentoDAO.TABLE_NAME + ", sql: " + EstabelecimentoDAO.CREATE_TABLE);
        sqLiteDatabase.execSQL(EstabelecimentoDAO.CREATE_TABLE);
        Log.d(TAG, "Creating table: " + EstabelecimentoRamoDAO.TABLE_NAME + ", sql: " + EstabelecimentoRamoDAO.CREATE_TABLE);
        sqLiteDatabase.execSQL(EstabelecimentoRamoDAO.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        Log.d(TAG, "Upgrading table " + RamoDAO.CREATE_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + RamoDAO.TABLE_NAME);
        Log.d(TAG, "Upgrading table " + EstabelecimentoDAO.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + EstabelecimentoDAO.TABLE_NAME);
        Log.d(TAG, "Upgrading table " + EstabelecimentoRamoDAO.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + EstabelecimentoRamoDAO.TABLE_NAME);
    }
}
