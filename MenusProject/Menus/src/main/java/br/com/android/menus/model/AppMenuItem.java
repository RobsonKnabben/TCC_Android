package br.com.android.menus.model;


import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;

public class AppMenuItem {
    private String mTitle;
    private String mSubtitle;
    private int mIcon;
    private Intent mIntent;
    private Fragment mFragment;
    private boolean mExecuteFinish;
    private boolean mTopFragment;
    private AsyncTask mAsyncTask;



    public AppMenuItem(String title, String subtitle, int icon) {
        this.mTitle = title;
        this.mSubtitle = subtitle;
        this.mIcon = icon;
    }
    public AppMenuItem(String title, int icon) {
        this.mTitle = title;
        this.mIcon = icon;
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

    public AppMenuItem(String title, String subtitle, int icon, Fragment fragment, boolean isTop) {
        this.mTitle = title;
        this.mSubtitle = subtitle;
        this.mIcon = icon;
        this.mFragment = fragment;
        this.mTopFragment = isTop;
    }
    public AppMenuItem(String title, int icon, Fragment fragment, boolean isTop) {
        this.mTitle = title;
        this.mIcon = icon;
        this.mFragment = fragment;
        this.mTopFragment = isTop;
    }

    public AppMenuItem(String title, String subtitle, int icon, AsyncTask asyncTask) {
        this.mTitle = title;
        this.mSubtitle = subtitle;
        this.mIcon = icon;
        this.mAsyncTask = asyncTask;
    }
    public AppMenuItem(String title, int icon, AsyncTask asyncTask) {
        this.mTitle = title;
        this.mIcon = icon;
        this.mAsyncTask = asyncTask;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getSubtitle() {
        return mSubtitle;
    }

    public void setSubtitle(String subtitle) {
        this.mSubtitle = subtitle;
    }

    public int getIcon() {
        return mIcon;
    }

    public void setIcon(int icon) {
        this.mIcon = icon;
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

    public void setExecuteFinish(boolean executeFinish) {
        this.mExecuteFinish = executeFinish;
    }

    public Fragment getFragment() {
        return mFragment;
    }

    public void setFragment(Fragment fragment) {
        this.mFragment = fragment;
    }

    public boolean isTopFragment() {
        return mTopFragment;
    }

    public void setIsTopFragment(boolean isTopFragment) {
        this.mTopFragment = isTopFragment;
    }

    public AsyncTask getAsyncTask() {
        return mAsyncTask;
    }

    public void setAsyncTask(AsyncTask asyncTask) {
        this.mAsyncTask = asyncTask;
    }
}
