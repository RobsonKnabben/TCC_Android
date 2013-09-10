package br.com.android.menus.activity;

import android.support.v4.app.Fragment;

import java.util.List;

import br.com.android.menus.fragments.RamosFragment;
import br.com.android.menus.model.AppMenuItem;


public class RamosFragmentActivity extends BaseFragmentsActivity {

    @Override
    protected List<AppMenuItem> MenuItens() {
        return getSingleton().getDefaultDrawerMenuItemList();
    }

    @Override
    protected Fragment InicialFragment() {
        return new RamosFragment();
    }
}
