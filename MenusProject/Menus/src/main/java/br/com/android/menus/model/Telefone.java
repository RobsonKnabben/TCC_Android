package br.com.android.menus.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import br.com.android.menus.db.EstabelecimentoDAO;
import br.com.android.menus.db.TelefoneDAO;

public class Telefone extends BaseModel {
    @SerializedName("numero")
    private String mNumero;

    @SerializedName("estabelecimento")
    private Estabelecimento mEstabelecimento;

    @SerializedName("deleted")
    private Boolean mDeletado;

    public String getNumero() {
        return mNumero;
    }

    public void setNumero(String numero) {
        this.mNumero = numero;
    }

    public Estabelecimento getEstabelecimento() {
        return mEstabelecimento;
    }

    public void setEstabelecimento(Estabelecimento estabelecimento) {
        this.mEstabelecimento = estabelecimento;
    }

    public Boolean getDeletado() {
        return mDeletado;
    }

    public void setDeletado(Boolean mDeletado) {
        this.mDeletado = mDeletado;
    }

    public boolean CreateOrUpdate(Context context) {
        ContentValues values = new ContentValues();
        values.put(TelefoneDAO.C_ID, this.getId());
        values.put(TelefoneDAO.C_NUMERO, this.getNumero());
        values.put(TelefoneDAO.C_ESTABELECIMENTO_ID, this.getEstabelecimento().getId());

        TelefoneDAO telefoneDAO = new TelefoneDAO(context);
        return telefoneDAO.InsertOrUpdate(values);
    }

    public boolean Sync(Context context){
        if (this.getDeletado()) {
            return new TelefoneDAO(context).Delete(this.getId());
        } else {
            return this.CreateOrUpdate(context);
        }
    }

    public static List<Telefone> getTelefonesByEstabelecimentoId(Context context, int id){
        List<Telefone> telefones = null;
        TelefoneDAO telefoneDAO = new TelefoneDAO(context);

        Cursor c = telefoneDAO.fetchByAttr(TelefoneDAO.C_ESTABELECIMENTO_ID, id);
        if (c.moveToFirst()){
            telefones = new ArrayList<Telefone>();
            while (!c.isAfterLast()){
                Telefone telefone = CursorToTelefone(c);
                telefones.add(telefone);
                c.moveToNext();
            }
        }
        c.close();
        return telefones;
    }

    private static Telefone CursorToTelefone(Cursor c){
        Telefone telefone = new Telefone();
        telefone.setId(c.getInt(c.getColumnIndex(TelefoneDAO.C_ID)));
        telefone.setNumero(c.getString(c.getColumnIndex(TelefoneDAO.C_NUMERO)));
        return telefone;
    }


}
