package br.com.android.menus.activity;

import android.support.v4.app.Fragment;

import java.util.List;

import br.com.android.menus.fragments.RamosFragment;
import br.com.android.menus.model.AppMenuDrawerItem;


public class AppFragmentActivity extends BaseFragmentsActivity {

    @Override
    protected List<AppMenuDrawerItem> MenuItens() {
        return getSingleton().getDefaultDrawerMenuItemList();
    }

    @Override
    protected Fragment InicialFragment() {
        return new RamosFragment();
    }
}
