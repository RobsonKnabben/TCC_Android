package br.com.android.menus.fragments;

import android.os.Bundle;

import com.actionbarsherlock.app.SherlockFragment;

import br.com.android.menus.app.AppSingleton;
import br.com.android.menus.db.DatabaseHelper;


public class BaseFragments extends SherlockFragment {
    protected final String EXTRA_RAMO = "EXTRA_RAMO";
    protected final String EXTRA_ESTABELECIMENTO = "EXTRA_ESTABELECIMENTO";

    private DatabaseHelper mDatabaseHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mDatabaseHelper = DatabaseHelper.getInstance(this.getSherlockActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
        this.mDatabaseHelper.OpenDatabase();
    }

    @Override
    public void onPause() {
        this.mDatabaseHelper.CloseDatabase();
        super.onPause();
    }

    public AppSingleton getAppSingleton(){
        return AppSingleton.getInstance(this.getSherlockActivity());
    }

}
