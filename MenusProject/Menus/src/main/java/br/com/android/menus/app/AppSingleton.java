package br.com.android.menus.app;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

import br.com.android.menus.R;
import br.com.android.menus.activity.EstabelecimentosFavoritosFragmentActivity;
import br.com.android.menus.activity.MainActivity;
import br.com.android.menus.activity.RamosFragmentActivity;
import br.com.android.menus.fragments.EstabelecimentosFavoritosFragment;
import br.com.android.menus.fragments.RamosFragments;
import br.com.android.menus.model.*;

public class AppSingleton {
    private static AppSingleton sInstance;
    private Context mContext;

    public AppSingleton(Context context){
        this.mContext = context;
    }

    public static AppSingleton getInstance(Context context){
        if (AppSingleton.sInstance == null){
            AppSingleton.sInstance = new AppSingleton(context);
        }
        else{
            AppSingleton.sInstance.mContext = context;
        }
        return AppSingleton.sInstance;
    }

    public List<AppMenuItem> getDefaultDrawerMenuItemList(){
        AppMenuItem menuCategories = new AppMenuItem(
                "Categorias",
                "Pizzarias, Restaurantes, ...",
                R.drawable.menu_icon_categories,
                new RamosFragments(),
                //new Intent(context, RamosFragmentActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK),
                true
        );

        AppMenuItem menuFavoritos = new AppMenuItem(
                "Favoritos",
                R.drawable.menu_icon_favorites,
                new EstabelecimentosFavoritosFragment(),
                //new Intent(context, EstabelecimentosFavoritosFragmentActivity.class),
                false
        );

        AppMenuItem menuAtualizar = new AppMenuItem(
                "Atualizar",
                "Ultima atualização: 10/08/2013",
                R.drawable.menu_icon_update,
                new Intent(mContext, MainActivity.class),
                true
        );

        List<AppMenuItem> menuItems = new ArrayList<AppMenuItem>();
        menuItems.add(menuCategories);
        menuItems.add(menuFavoritos);
        menuItems.add(menuAtualizar);

        return menuItems;
    }
}
