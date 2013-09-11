package br.com.android.menus.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import br.com.android.menus.db.EstabelecimentoDAO;
import br.com.android.menus.db.EstabelecimentoRamoDAO;

public class Estabelecimento extends BaseModel {
    @SerializedName("name")
    private String mName;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("ramos")
    private List<Ramo> mRamos;

    @SerializedName("linhas")
    private List<Linha> mLinhas;

    @SerializedName("telefones")
    private List<Telefone> mTelefones;

    private boolean mIsFavorite;

    public String getName(){
        return this.mName;
    }
    public void setName(String name){
        this.mName = name;
    }

    public String getDescription(){
        return this.mDescription;
    }
    public void setDescription(String description){
        this.mDescription = description;
    }

    public List<Ramo> getRamos(){
        return this.mRamos;
    }
    public void setRamos(List<Ramo> ramos){
        this.mRamos = ramos;
    }

    public List<Telefone> getTelefones() {
        return mTelefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.mTelefones = telefones;
    }

    public List<Linha> getLinhas() {
        return mLinhas;
    }
    public void setLinhas(List<Linha> linhas) {
        this.mLinhas = linhas;
    }

    public boolean getIsFavorite() {
        return this.mIsFavorite;
    }
    public void setIsFavorite(boolean isFavorite) {
        this.mIsFavorite = isFavorite;
    }
    public void setIsFavorite(Integer isFavorite) {
        this.mIsFavorite = isFavorite == 1;
    }

    public static List<Estabelecimento> getAllEstabelecimentos(Context context){
        List<Estabelecimento> estabelecimentos = null;
        EstabelecimentoDAO estabelecimentoDAO = new EstabelecimentoDAO(context);

        Cursor c = estabelecimentoDAO.fetch();
        if (c.moveToFirst()){
            estabelecimentos = new ArrayList<Estabelecimento>();
            while (!c.isAfterLast()){
                Estabelecimento estabelecimento = CursorToEstabelecimento(c);
                estabelecimento.setTelefones(Telefone.getTelefonesByEstabelecimentoId(context, estabelecimento.getId()));
                estabelecimentos.add(estabelecimento);
                c.moveToNext();
            }
        }
        c.close();
        return estabelecimentos;
    }

    public static List<Estabelecimento> getEstabelecimentosFavoritos(Context context){
        List<Estabelecimento> estabelecimentos = null;
        EstabelecimentoDAO estabelecimentoDAO = new EstabelecimentoDAO(context);

        Cursor c = estabelecimentoDAO.fetchByAttr(EstabelecimentoDAO.C_IS_FAVORITE, 1);
        if (c.moveToFirst()){
            estabelecimentos = new ArrayList<Estabelecimento>();
            while (!c.isAfterLast()){
                Estabelecimento estabelecimento = CursorToEstabelecimento(c);
                estabelecimento.setTelefones(Telefone.getTelefonesByEstabelecimentoId(context, estabelecimento.getId()));
                estabelecimentos.add(estabelecimento);
                c.moveToNext();
            }
        }
        c.close();
        return estabelecimentos;
    }

    public static Estabelecimento getEstabelecimentoById(Context context, int id){
        Estabelecimento estabelecimento = null;
            EstabelecimentoDAO estabelecimentoDAO = new EstabelecimentoDAO(context);

        Cursor c = estabelecimentoDAO.fetchById(id);
        if (c.moveToFirst()){
            estabelecimento = CursorToEstabelecimento(c);
            estabelecimento.setTelefones(Telefone.getTelefonesByEstabelecimentoId(context, estabelecimento.getId()));
        }
        c.close();
        return estabelecimento;
    }

    public static List<Estabelecimento> getEstabelecimentosByRamoId(Context context, int id){
        List<Estabelecimento> estabelecimentos = null;
        EstabelecimentoDAO estabelecimentoDAO = new EstabelecimentoDAO(context);

        String query = " SELECT DISTINCT * FROM "
                + EstabelecimentoDAO.TABLE_NAME + " INNER JOIN "
                + EstabelecimentoRamoDAO.TABLE_NAME + " ON "
                + EstabelecimentoDAO.TABLE_NAME + "." + EstabelecimentoDAO.C_ID + " = "
                + EstabelecimentoRamoDAO.TABLE_NAME + "." + EstabelecimentoRamoDAO.C_ESTABELECIMENTO_ID
                + " WHERE " + EstabelecimentoRamoDAO.TABLE_NAME + "." + EstabelecimentoRamoDAO.C_RAMO_ID + " = " + id;


        Cursor c = estabelecimentoDAO.fetchByQuery(query);
        if (c.moveToFirst()){
            estabelecimentos = new ArrayList<Estabelecimento>();
            while (!c.isAfterLast()){
                Estabelecimento estabelecimento = CursorToEstabelecimento(c);
                estabelecimento.setTelefones(Telefone.getTelefonesByEstabelecimentoId(context, estabelecimento.getId()));
                estabelecimentos.add(estabelecimento);
                c.moveToNext();
            }
        }
        c.close();
        return estabelecimentos;
    }

    private static Estabelecimento CursorToEstabelecimento(Cursor c){
        Estabelecimento estabelecimento = new Estabelecimento();
        estabelecimento.setId(c.getInt(c.getColumnIndex(EstabelecimentoDAO.C_ID)));
        estabelecimento.setName(c.getString(c.getColumnIndex(EstabelecimentoDAO.C_NAME)));
        estabelecimento.setDescription(c.getString(c.getColumnIndex(EstabelecimentoDAO.C_DESCRIPTION)));
        estabelecimento.setIsFavorite(c.getInt(c.getColumnIndex(EstabelecimentoDAO.C_IS_FAVORITE)));
        return estabelecimento;
    }

    public boolean CreateOrUpdate(Context context) {
        boolean result;

        ContentValues values = new ContentValues();
        values.put(EstabelecimentoDAO.C_ID, this.getId());
        values.put(EstabelecimentoDAO.C_NAME, this.getName());
        values.put(EstabelecimentoDAO.C_DESCRIPTION, this.getDescription());


        EstabelecimentoDAO estabelecimentoDAO = new EstabelecimentoDAO(context);
        result = estabelecimentoDAO.InsertOrUpdate(values);

        if (result){
            for (Ramo ramo : this.getRamos()){
                EstabelecimentoRamo estabelecimentoRamo = new EstabelecimentoRamo();
                estabelecimentoRamo.setEstabelecimentoId(this.getId());
                estabelecimentoRamo.setRamoId(ramo.getId());
                result = estabelecimentoRamo.CreateOrUpdate(context);
            }

            for (Telefone telefone : this.getTelefones()){
                telefone.setEstabelecimento(this);
                result = telefone.CreateOrUpdate(context);
            }

            for (Linha linha : this.getLinhas()){
                linha.setEstabelecimento(this);
                result = linha.CreateOrUpdate(context);
            }
        }
        return result;
    }

    public boolean ChangeIsFavorite(Context context) {
        this.setIsFavorite(!getIsFavorite());

        ContentValues values = new ContentValues();
        values.put(EstabelecimentoDAO.C_ID, this.getId());
        values.put(EstabelecimentoDAO.C_IS_FAVORITE, this.getIsFavorite());

        EstabelecimentoDAO estabelecimentoDAO = new EstabelecimentoDAO(context);
        return estabelecimentoDAO.InsertOrUpdate(values);
    }
}
