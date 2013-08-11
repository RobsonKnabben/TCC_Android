package br.com.android.menus.activity;

import android.support.v4.app.Fragment;

import java.util.List;

import br.com.android.menus.model.AppMenuItem;
import br.com.android.menus.fragments.EstabelecimentosFragments;

public class EstabelecimentosFragmentActivity extends BaseFragmentsActivity {

    @Override
    protected List<AppMenuItem> MenuItems() {
        return getSingleton().getDefaultDrawerMenuItemList(this);
    }

    @Override
    protected Fragment Fragment() {
        return new EstabelecimentosFragments();
    }
}
