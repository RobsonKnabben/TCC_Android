package br.com.android.menus.model;

import android.content.ContentValues;
import android.content.Context;

import com.google.gson.annotations.SerializedName;

import br.com.android.menus.db.TelefoneDAO;

public class Telefone extends BaseModel {
    @SerializedName("numero")
    private String mNumero;

    @SerializedName("estabelecimento")
    private Estabelecimento mEstabelecimento;

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

    public boolean CreateOrUpdate(Context context) {
        ContentValues values = new ContentValues();
        values.put(TelefoneDAO.C_ID, this.getId());
        values.put(TelefoneDAO.C_NUMERO, this.getNumero());
        values.put(TelefoneDAO.C_ESTABELECIMENTO_ID, this.getEstabelecimento().getId());

        TelefoneDAO telefoneDAO = new TelefoneDAO(context);
        return telefoneDAO.InsertOrUpdate(values);
    }
}
