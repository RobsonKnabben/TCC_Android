package br.com.android.menus.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;


public abstract class BaseDAO {
    abstract String TAG();
    abstract String OBJECT();
    abstract String TABLE();

    public static final String C_ID = "_id";

    private DatabaseHelper mDatabaseHelper;

    public BaseDAO(Context context){
        this.mDatabaseHelper = DatabaseHelper.getInstance(context);
    }

    public boolean Insert(ContentValues values) {
        Log.d(TAG(), "Creating " + OBJECT() + " with values " + values);
        try {
            if (this.mDatabaseHelper.getDatabase().insert(TABLE(), null, values) != -1) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            Log.e(TAG(), "Error on insert: " + e.getMessage());
            return false;
        }
    }

    public boolean Delete(int id) {
        Log.d(TAG(), "Deleting " + OBJECT() + " with id: " + id);
        try{
            if (this.mDatabaseHelper.getDatabase().delete(TABLE(), C_ID + "=" + id, null) > 0){
                return true;
            }else{
                return false;
            }
        }catch(SQLException e) {
            Log.e(TAG(), "Error on delete: " + e.getMessage());
            return false;
        }
    }

    public <T> boolean DeleteByAttr (String key, T value){
        Log.d(TAG(), "Deleting " + OBJECT() + " with id: " + value);
        try{
            if (this.mDatabaseHelper.getDatabase().delete(TABLE(), key + "=" + value, null) > 0){
                return true;
            }else{
                return false;
            }
        }catch(SQLException e) {
            Log.e(TAG(), "Error on delete: " + e.getMessage());
            return false;
        }
    }

    public boolean Update(ContentValues values) {
        String where = C_ID + "=" + values.getAsString(C_ID);
        return DoUpdate(values, where);
    }

    public boolean Update(ContentValues values, String where) {
        return DoUpdate(values, where);
    }

    private boolean DoUpdate(ContentValues values, String where) {
        Log.d(TAG(), "Updating " + OBJECT() + " with values: " + values);
        try {
            if (this.mDatabaseHelper.getDatabase().update(TABLE(), values, where, null) > 0) {
                return true;
            } else {
                return false;
            }
        } catch(SQLException e) {
            Log.e(TAG(), "Error on update: " + e.getMessage());
            return false;
        }
    }

    public boolean InsertOrUpdate(ContentValues values) {
        if (fetchById(values.getAsInteger(C_ID)).getCount() > 0){
            return Update(values);
        }
        else {
            return Insert(values);
        }
    }

    public boolean InsertOrUpdate(ContentValues values, String where) {
        String query = "SELECT * FROM " + TABLE() + " WHERE " + where;

        Cursor c = fetchByQuery(query);
        if (c.getCount() > 0){
            return Update(values, where);
        }
        else {
            return Insert(values);
        }
    }

    public Cursor fetch() {
        Log.d(TAG(), "Fetching all " + TABLE());
        return this.mDatabaseHelper.getDatabase().query(TABLE(), null, null, null, null, null, null);
    }

    public Cursor fetchById(int id) {
        return this.mDatabaseHelper.getDatabase().query(TABLE(), null, C_ID + "=" + id, null, null, null, null);
    }

    public <T> Cursor fetchByAttr(String key, T value) {
        return this.mDatabaseHelper.getDatabase().query(TABLE(), null, key + "=" + value, null, null, null, null);
    }

    public Cursor fetchByQuery(String query){
        return this.mDatabaseHelper.getDatabase().rawQuery(query, null);
    }
}
