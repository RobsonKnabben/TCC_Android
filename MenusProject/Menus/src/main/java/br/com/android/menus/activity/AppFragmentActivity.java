package br.com.android.menus.activity;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;

import java.util.List;

import br.com.android.menus.fragments.RamosFragments;
import br.com.android.menus.model.AppMenuItem;


public class AppFragmentActivity extends BaseFragmentsActivity {

    @Override
    protected List<AppMenuItem> MenuItens() {
        return getSingleton().getDefaultDrawerMenuItemList();
    }

    @Override
    protected Fragment InicialFragment() {
        return new RamosFragments();
    }
}
