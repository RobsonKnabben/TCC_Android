package br.com.android.menus.model;


import android.content.Intent;

public class AppMenuItem {
    private String mTitle;
    private String mSubtitle;
    private int mIcon;
    private Intent mIntent;
    private boolean mExecuteFinish;



    public AppMenuItem(String title, String subtitle, int icon) {
        this.mTitle = title;
        this.mSubtitle = subtitle;
        this.mIcon = icon;
    }
    public AppMenuItem(String title, int icon) {
        this.mTitle = title;
        this.mIcon = icon;
    }
    public AppMenuItem(String title, String subtitle) {
        this.mTitle = title;
        this.mSubtitle = subtitle;
    }
    public AppMenuItem(String title) {
        this.mTitle = title;
    }

    public AppMenuItem(String title, String subtitle, int icon, Intent intent, boolean executeFinish) {
        this.mTitle = title;
        this.mSubtitle = subtitle;
        this.mIcon = icon;
        this.mIntent = intent;
        this.mExecuteFinish = executeFinish;
    }
    public AppMenuItem(String title, int icon, Intent intent, boolean executeFinish) {
        this.mTitle = title;
        this.mIcon = icon;
        this.mIntent = intent;
        this.mExecuteFinish = executeFinish;
    }
    public AppMenuItem(String title, String subtitle, Intent intent, boolean executeFinish) {
        this.mTitle = title;
        this.mSubtitle = subtitle;
        this.mIntent = intent;
        this.mExecuteFinish = executeFinish;
    }
    public AppMenuItem(String title, Intent intent, boolean executeFinish) {
        this.mTitle = title;
        this.mIntent = intent;
        this.mExecuteFinish = executeFinish;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getSubtitle() {
        return mSubtitle;
    }

    public void setSubtitle(String mSubtitle) {
        this.mSubtitle = mSubtitle;
    }

    public int getIcon() {
        return mIcon;
    }

    public void setIcon(int mIcon) {
        this.mIcon = mIcon;
    }

    public Intent getIntent() {
        return mIntent;
    }

    public void setIntent(Intent intent) {
        this.mIntent = intent;
    }

    public boolean ExecuteFinish() {
        return mExecuteFinish;
    }

    public void setExecuteFinish(boolean mExecuteFinish) {
        this.mExecuteFinish = mExecuteFinish;
    }
}
