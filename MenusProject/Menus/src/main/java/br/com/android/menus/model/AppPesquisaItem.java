package br.com.android.menus.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Robson on 10/09/13.
 */
public class AppPesquisaItem<T> implements Serializable{
    List<T> mItemList;

    public AppPesquisaItem() {
    }

    public AppPesquisaItem(List<T> mItemPesquisaList) {
        this.mItemList = mItemPesquisaList;
    }

    public List<T> getItens() {
        return mItemList;
    }

    public void setItens(List<T> mItemPesquisaList) {
        this.mItemList = mItemPesquisaList;
    }
}
