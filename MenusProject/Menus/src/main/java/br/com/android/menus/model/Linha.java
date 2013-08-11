package br.com.android.menus.model;

import com.google.gson.annotations.SerializedName;

public class Linha extends BaseModel {
    @SerializedName("name")
    private String mName;

    @SerializedName("estabelecimento")
    private Estabelecimento mEstabelecimento;

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
}