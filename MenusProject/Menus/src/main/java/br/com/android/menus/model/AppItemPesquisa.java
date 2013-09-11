package br.com.android.menus.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Robson on 10/09/13.
 */
public class AppItemPesquisa <T> implements Serializable{
    List<T> mItemPesquisaList;

    public AppItemPesquisa() {
    }

    public AppItemPesquisa(List<T> mItemPesquisaList) {
        this.mItemPesquisaList = mItemPesquisaList;
    }

    public List<T> getItens() {
        return mItemPesquisaList;
    }

    public void setItens(List<T> mItemPesquisaList) {
        this.mItemPesquisaList = mItemPesquisaList;
    }
}
