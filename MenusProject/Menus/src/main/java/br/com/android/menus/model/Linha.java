package br.com.android.menus.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import br.com.android.menus.db.LinhaDAO;
import br.com.android.menus.db.ProdutoDAO;

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

    public static Linha getLinhaById(Context context, int id){
        Linha linha = null;
        LinhaDAO linhaDAO = new LinhaDAO(context);

        Cursor c = linhaDAO.fetchById(id);
        if (c.moveToFirst()){

            linha = CursorToLinha(c);
        }
        c.close();
        return linha;
    }

    public static List<Linha> getLinhasByEstabelecimentoId(Context context, int id){
        List<Linha> linhas = null;
        LinhaDAO linhaDAO = new LinhaDAO(context);

        Cursor c = linhaDAO.fetchByAttr(LinhaDAO.C_ESTABELECIMENTO_ID, id);
        if (c.moveToFirst()){
            linhas = new ArrayList<Linha>();
            while (!c.isAfterLast()){
                Linha linha = CursorToLinha(c);
                linha.setProdutos(Produto.getProdutosByLinhaId(context, linha.getId()));
                if (linha.getProdutos() != null) linhas.add(linha);
                c.moveToNext();
            }
        }
        c.close();
        return linhas;
    }

    private static Linha CursorToLinha(Cursor c){
        Linha linha = new Linha();
        linha.setId(c.getInt(c.getColumnIndex(LinhaDAO.C_ID)));
        linha.setName(c.getString(c.getColumnIndex(LinhaDAO.C_NAME)));
        return linha;
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