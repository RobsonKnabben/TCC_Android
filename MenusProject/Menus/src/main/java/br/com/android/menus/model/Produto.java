package br.com.android.menus.model;

import android.content.ContentValues;
import android.content.Context;

import com.google.gson.annotations.SerializedName;

import br.com.android.menus.db.ProdutoDAO;
import br.com.android.menus.db.TelefoneDAO;

/**
 * Created by Robson on 11/08/13.
 */
public class Produto extends BaseModel {
    @SerializedName("name")
    private String mName;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("price")
    private Double mPrice;

    @SerializedName("linha")
    private Linha mLinha;

    @SerializedName("estabelecimento")
    private Estabelecimento mEstabelecimento;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public Double getPrice() {
        return mPrice;
    }

    public void setPrice(Double price) {
        this.mPrice = price;
    }

    public Linha getmLinha() {
        return mLinha;
    }

    public void setLinha(Linha linha) {
        this.mLinha = linha;
    }

    public Estabelecimento getEstabelecimento() {
        return mEstabelecimento;
    }

    public void setEstabelecimento(Estabelecimento estabelecimento) {
        this.mEstabelecimento = estabelecimento;
    }

    public boolean CreateOrUpdate(Context context) {
        ContentValues values = new ContentValues();
        values.put(ProdutoDAO.C_ID, this.getId());
        values.put(ProdutoDAO.C_NAME, this.getName());
        values.put(ProdutoDAO.C_DESCRIPTION, this.getDescription());
        values.put(ProdutoDAO.C_PRICE, this.getPrice());
        values.put(ProdutoDAO.C_LINHA_ID, this.getmLinha().getId());
        values.put(TelefoneDAO.C_ESTABELECIMENTO_ID, this.getEstabelecimento().getId());

        ProdutoDAO produtoDAO = new ProdutoDAO(context);
        return produtoDAO.InsertOrUpdate(values);
    }
}
