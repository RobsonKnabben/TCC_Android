package br.com.android.menus.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

import java.util.List;

import br.com.android.menus.R;
import br.com.android.menus.adapters.DrawerMenuListAdapter;
import br.com.android.menus.app.ItemMenuAsyncTask;
import br.com.android.menus.model.AppMenuItem;
import br.com.android.menus.app.AppSingleton;

public abstract class BaseFragmentsActivity extends SherlockFragmentActivity {
    protected final String EXTRA_MENU_DRAWER_OPENED = "EXTRA_MENU_DRAWER_OPENED";

    protected abstract List<AppMenuItem> MenuItens();
    protected abstract Fragment InicialFragment();

    // Declare Variable
    DrawerLayout mDrawerLayout;
    ListView mDrawerList;
    ActionBarDrawerToggle mDrawerToggle;
    DrawerMenuListAdapter mMenuAdapter;
    CharSequence mActionBarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_menu_drawer);

        this.setTitle(this.getTitle());
        // Enable ActionBar app icon to behave as action to toggle nav drawer
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        // Locate ListView in drawer_main.xml
        mDrawerList = (ListView) findViewById(R.id.left_drawer);


        // Set a custom shadow that overlays the main content when the drawer
        // opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);


        // Pass results to MenuListAdapter Class
        mMenuAdapter = new DrawerMenuListAdapter(this, R.layout.list_item_menu_drawer, MenuItens());


        // Set the MenuListAdapter to the ListView
        mDrawerList.setAdapter(mMenuAdapter);
        // Capture button clicks on side menu
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_navigation_drawer, R.string.drawer_open,
                R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                // TODO Auto-generated method stub
                getSupportActionBar().setTitle(getActionBarTitle());
                super.onDrawerClosed(view);
            }

            public void onDrawerOpened(View drawerView) {
                // TODO Auto-generated method stub
                getSupportActionBar().setTitle(R.string.app_name);
                super.onDrawerOpened(drawerView);
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, InicialFragment()).commit();
        }

        if (this.getIntent().getBooleanExtra(EXTRA_MENU_DRAWER_OPENED, false)) {
            mDrawerLayout.openDrawer(mDrawerList);
            this.setTitle(R.string.app_name);
            this.getIntent().putExtra(EXTRA_MENU_DRAWER_OPENED, false);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportMenuInflater().inflate(R.menu.base_menu_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {

            if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
                mDrawerLayout.closeDrawer(mDrawerList);
            } else {
                mDrawerLayout.openDrawer(mDrawerList);
            }
        }

        return super.onOptionsItemSelected(item);
    }

       @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void setTitle(CharSequence title) {
        mActionBarTitle = title;
        getSupportActionBar().setTitle(mActionBarTitle);
    }

    // The click listener for ListView in the navigation drawer
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            SelectItem(parent, view, position, id);
        }
    }

    protected void SelectItem(AdapterView<?> parent, View view, int position, long id) {
        AppMenuItem item = (AppMenuItem) parent.getItemAtPosition(position);

        if (item != null){
            mDrawerList.setItemChecked(position, true);
            mDrawerLayout.closeDrawer(mDrawerList);
            //new ItemMenuAsyncTask(this, item).execute();
            if (item.getFragment() != null){
                if (item.isTopFragment()){
                    for(int i = 0; i < getSupportFragmentManager().getBackStackEntryCount(); ++i) {
                        getSupportFragmentManager().popBackStack();
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, item.getFragment()).commit();
                }
                else{
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, item.getFragment()).addToBackStack("back").commit();
                }

            }
            if (item.getIntent() != null){
                startActivity(item.getIntent());
                if (item.ExecuteFinish()){
                    finish();
                }
            }
        }
    }

    public AppSingleton getSingleton(){
        return AppSingleton.getInstance(this);
    }

    public CharSequence getActionBarTitle() {
        return mActionBarTitle;
    }

}
