package br.com.android.menus.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Robson on 10/09/13.
 */
public class AppPesquisaGroup implements Serializable{
    String mTitle;
    AppItemPesquisa mItemPesquisaList;

    public AppPesquisaGroup(String mTitle) {
        this.mTitle = mTitle;
    }

    public AppPesquisaGroup(String mTitle, AppItemPesquisa mItemPesquisaList) {
        this.mTitle = mTitle;
        this.mItemPesquisaList = mItemPesquisaList;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public AppItemPesquisa getItemPesquisaList() {
        return mItemPesquisaList;
    }

    public void setItemPesquisaList(AppItemPesquisa mItemPesquisaList) {
        this.mItemPesquisaList = mItemPesquisaList;
    }
}
