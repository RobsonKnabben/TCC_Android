package br.com.android.menus.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;

import org.apache.http.entity.SerializableEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.android.menus.db.EstabelecimentoDAO;
import br.com.android.menus.db.EstabelecimentoRamoDAO;
import br.com.android.menus.db.RamoDAO;

public class Ramo extends BaseModel {
    @SerializedName("name")
    private String mName;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public static List<Ramo> getAllRamos(Context context){
        List<Ramo> ramos = null;
        RamoDAO ramoDAO = new RamoDAO(context);

        String query = " SELECT DISTINCT " + RamoDAO.TABLE_NAME + ".* FROM "
                + RamoDAO.TABLE_NAME + " INNER JOIN "
                + EstabelecimentoRamoDAO.TABLE_NAME + " ON "
                + RamoDAO.TABLE_NAME + "." + RamoDAO.C_ID + " = "
                + EstabelecimentoRamoDAO.TABLE_NAME + "." + EstabelecimentoRamoDAO.C_RAMO_ID;


        Cursor c = ramoDAO.fetchByQuery(query);
        if (c.moveToFirst()){
            ramos = new ArrayList<Ramo>();
            while (!c.isAfterLast()){
                Ramo ramo = CursorToRamo(c);
                ramos.add(ramo);
                c.moveToNext();
            }
        }
        c.close();
        return ramos;
    }

    public static Ramo getRamoById(Context context, int id){
        Ramo ramo = null;
        RamoDAO ramoDAO = new RamoDAO(context);

        Cursor c = ramoDAO.fetchById(id);
        if (c.moveToFirst()){
            ramo = CursorToRamo(c);
        }
        c.close();
        return ramo;
    }

    private static Ramo CursorToRamo(Cursor c){
        Ramo ramo = new Ramo();
        ramo.setId(c.getInt(c.getColumnIndex(RamoDAO.C_ID)));
        ramo.setName(c.getString(c.getColumnIndex(RamoDAO.C_NAME)));
        return ramo;
    }

    public boolean CreateOrUpdate(Context context) {
        ContentValues values = new ContentValues();
        values.put(RamoDAO.C_ID, this.getId());
        values.put(RamoDAO.C_NAME, this.getName());

        RamoDAO ramoDAO = new RamoDAO(context);
        return ramoDAO.InsertOrUpdate(values);
    }
}
