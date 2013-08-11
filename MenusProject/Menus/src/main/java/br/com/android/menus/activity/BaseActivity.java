package br.com.android.menus.activity;


import android.os.Bundle;

import com.actionbarsherlock.app.SherlockActivity;

import br.com.android.menus.app.AppSingleton;
import br.com.android.menus.db.DatabaseHelper;

public class BaseActivity extends SherlockActivity {
    protected final String EXTRA_RAMO = "EXTRA_RAMO";
    protected final String EXTRA_ESTABELECIMENTO = "EXTRA_ESTABELECIMENTO";
    protected final String EXTRA_MENU_DRAWER_OPENED = "EXTRA_MENU_DRAWER_OPENED";

    private DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.mDatabaseHelper = DatabaseHelper.getInstance(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.mDatabaseHelper.OpenDatabase();
    }

    @Override
    protected void onPause() {
        this.mDatabaseHelper.CloseDatabase();
        super.onPause();
    }

    public AppSingleton getAppSingleton(){
        return AppSingleton.getInstance(this);
    }
}
