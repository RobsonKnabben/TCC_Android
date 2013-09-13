package br.com.android.menus.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import br.com.android.menus.db.LinhaDAO;
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

    @SerializedName("inativo")
    private Boolean mInativo;

    @SerializedName("deleted")
    private Boolean mDeletado;


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

    public Boolean getInativo() {
        return mInativo;
    }

    public void setInativo(Boolean mInativo) {
        this.mInativo = mInativo;
    }

    public void setInativo(int mInativo) {
        this.mInativo = mInativo == 1;
    }

    public Boolean getDeletado() {
        return mDeletado;
    }

    public void setDeletado(Boolean mDeletado) {
        this.mDeletado = mDeletado;
    }


    public boolean CreateOrUpdate(Context context) {
        ContentValues values = new ContentValues();
        values.put(ProdutoDAO.C_ID, this.getId());
        values.put(ProdutoDAO.C_NAME, this.getName());
        values.put(ProdutoDAO.C_DESCRIPTION, this.getDescription());
        values.put(ProdutoDAO.C_PRICE, this.getPrice());
        values.put(ProdutoDAO.C_INATIVO, this.getInativo());
        values.put(ProdutoDAO.C_LINHA_ID, this.getmLinha().getId());
        values.put(TelefoneDAO.C_ESTABELECIMENTO_ID, this.getEstabelecimento().getId());

        ProdutoDAO produtoDAO = new ProdutoDAO(context);
        return produtoDAO.InsertOrUpdate(values);
    }

    public boolean Sync(Context context){
        if (this.getDeletado()) {
            return new ProdutoDAO(context).Delete(this.getId());
        } else {
            return this.CreateOrUpdate(context);
        }
    }

    public static List<Produto> getAllProdutos(Context context){
        List<Produto> produtos = null;
        ProdutoDAO produtoDAO = new ProdutoDAO(context);

        Cursor c = produtoDAO.fetch();
        if (c.moveToFirst()){
            produtos = new ArrayList<Produto>();
            while (!c.isAfterLast()){
                Produto produto = CursorToProduto(c, context);
                if (!produto.getInativo()) produtos.add(produto);
                c.moveToNext();
            }
        }
        c.close();

        return produtos;
    }

    public static List<Produto> getAllProdutosByEstebelecimentosAtivos(Context context){
        List<Produto> produtos = null;
        ProdutoDAO produtoDAO = new ProdutoDAO(context);

        Cursor c = produtoDAO.fetch();
        if (c.moveToFirst()){
            produtos = new ArrayList<Produto>();
            while (!c.isAfterLast()){
                Produto produto = CursorToProduto(c, context);
                if (!produto.getInativo() && !produto.getEstabelecimento().getInativo()) produtos.add(produto);
                c.moveToNext();
            }
        }
        c.close();

        return produtos;
    }

    public static List<Produto> getProdutosByLinhaId(Context context, int id){
        List<Produto> produtos = null;
        ProdutoDAO produtoDAO = new ProdutoDAO(context);

        Cursor c = produtoDAO.fetchByAttr(ProdutoDAO.C_LINHA_ID, id);
        if (c.moveToFirst()){
            produtos = new ArrayList<Produto>();
            while (!c.isAfterLast()){
                Produto produto = CursorToProduto(c , context);
                if (!produto.getInativo()) produtos.add(produto);
                c.moveToNext();
            }
        }
        c.close();
        return produtos;
    }

    private static Produto CursorToProduto(Cursor c, Context context){
        Produto produto = new Produto();
        produto.setId(c.getInt(c.getColumnIndex(ProdutoDAO.C_ID)));
        produto.setName(c.getString(c.getColumnIndex(ProdutoDAO.C_NAME)));
        produto.setDescription(c.getString(c.getColumnIndex(ProdutoDAO.C_DESCRIPTION)));
        produto.setPrice(c.getDouble(c.getColumnIndex(ProdutoDAO.C_PRICE)));
        produto.setInativo(c.getInt(c.getColumnIndex(ProdutoDAO.C_INATIVO)));

        produto.setLinha(Linha.getLinhaById(context, c.getInt(c.getColumnIndex(ProdutoDAO.C_LINHA_ID))));
        produto.setEstabelecimento(Estabelecimento.getEstabelecimentoById(context , c.getInt(c.getColumnIndex(ProdutoDAO.C_ESTABELECIMENTO_ID))));
        return produto;
    }
}
