package br.com.android.menus.model;

import java.io.Serializable;

/**
 * Created by Robson on 10/09/13.
 */
public class AppPesquisaGroup implements Serializable{
    String mTitle;
    AppPesquisaItem mItemPesquisaList;

    public AppPesquisaGroup(String mTitle) {
        this.mTitle = mTitle;
    }

    public AppPesquisaGroup(String mTitle, AppPesquisaItem mItemPesquisaList) {
        this.mTitle = mTitle;
        this.mItemPesquisaList = mItemPesquisaList;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public AppPesquisaItem getItemPesquisaList() {
        return mItemPesquisaList;
    }

    public void setItemPesquisaList(AppPesquisaItem mItemPesquisaList) {
        this.mItemPesquisaList = mItemPesquisaList;
    }
}
