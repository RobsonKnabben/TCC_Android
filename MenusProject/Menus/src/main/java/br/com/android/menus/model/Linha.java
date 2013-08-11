package br.com.android.menus.model;

import android.content.ContentValues;
import android.content.Context;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import br.com.android.menus.db.LinhaDAO;
import br.com.android.menus.db.TelefoneDAO;

public class Linha extends BaseModel {
    @SerializedName("name")
    private String mName;

    @SerializedName("estabelecimento")
    private Estabelecimento mEstabelecimento;

    @SerializedName("produtos")
    private List<Produto> mProdutos;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public Estabelecimento getEstabelecimento() {
        return mEstabelecimento;
    }

    public void setEstabelecimento(Estabelecimento estabelecimento) {
        this.mEstabelecimento = estabelecimento;
    }

    public List<Produto> getProdutos() {
        return mProdutos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.mProdutos = produtos;
    }

    public boolean CreateOrUpdate(Context context) {
        boolean result;

        ContentValues values = new ContentValues();
        values.put(LinhaDAO.C_ID, this.getId());
        values.put(LinhaDAO.C_NAME, this.getName());
        values.put(LinhaDAO.C_ESTABELECIMENTO_ID, this.getEstabelecimento().getId());

        LinhaDAO linhaDAO = new LinhaDAO(context);
        result = linhaDAO.InsertOrUpdate(values);

        if (result){
            for (Produto produto : this.getProdutos()){
                produto.setLinha(this);
                produto.setEstabelecimento(this.getEstabelecimento());
                result = produto.CreateOrUpdate(context);
            }
        }
        return result;
    }
}