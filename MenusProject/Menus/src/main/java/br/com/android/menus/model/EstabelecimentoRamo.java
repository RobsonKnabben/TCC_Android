package br.com.android.menus.model;

import android.content.ContentValues;
import android.content.Context;

import com.google.gson.annotations.SerializedName;

import br.com.android.menus.db.EstabelecimentoRamoDAO;

public class EstabelecimentoRamo extends BaseModel {
    @SerializedName("estabelecimento_id")
    private Integer mEstabelecimentoId;

    @SerializedName("ramo_id")
    private Integer mRamoId;

    public Integer getEstabelecimentoId(){
        return this.mEstabelecimentoId;
    }

    public void setEstabelecimentoId(Integer estabelecimentoId){
        this.mEstabelecimentoId = estabelecimentoId;
    }

    public Integer getRamoId(){
        return this.mRamoId;
    }

    public void setRamoId(Integer ramoId){
        this.mRamoId = ramoId;
    }

    public boolean CreateOrUpdate(Context context) {
        String where = EstabelecimentoRamoDAO.C_ESTABELECIMENTO_ID + " = " + this.getEstabelecimentoId().toString()
                + " AND " + EstabelecimentoRamoDAO.C_RAMO_ID + " = " + this.getRamoId().toString();

        ContentValues values = new ContentValues();
        values.put(EstabelecimentoRamoDAO.C_ESTABELECIMENTO_ID, this.getEstabelecimentoId());
        values.put(EstabelecimentoRamoDAO.C_RAMO_ID, this.getRamoId());

        EstabelecimentoRamoDAO estabelecimentoRamoDAO = new EstabelecimentoRamoDAO(context);
        return estabelecimentoRamoDAO.InsertOrUpdate(values, where);
    }

    public static boolean DeleteByEstabelecimento(Context context, int EstabelecimentoId){
        return new EstabelecimentoRamoDAO(context).DeleteByAttr(EstabelecimentoRamoDAO.C_ESTABELECIMENTO_ID, EstabelecimentoId);
    }

    public static boolean DeleteByRamo(Context context, int RamoId){
        return new EstabelecimentoRamoDAO(context).DeleteByAttr(EstabelecimentoRamoDAO.C_RAMO_ID, RamoId);
    }
}
