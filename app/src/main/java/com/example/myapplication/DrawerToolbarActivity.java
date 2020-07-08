package com.example.myapplication;


import android.os.Bundle;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;





public abstract class DrawerToolbarActivity extends ToolbarActivity implements DrawerLayout.DrawerListener {
    protected DrawerLayout drawerLayout;
    protected ActionBarDrawerToggle drawerToggle;
    protected NavigationView navigationView;

    private MenuItem mLastItem;

    /**
     * {@inheritDoc ToolbarActivity}
     * <p/>
     * <b>Note that</b> this layout must have drawer and navigation view labeled<br/>
     * as <b> "drawer"</b>  and <b> "navigation_view"</b>  respectively.
     *
     * @param layoutResID {@inheritDoc Activity}
     */
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

        // Init drawer layout
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        onCreateNavigationMenu(navigationView.getMenu());
        drawerLayout.setDrawerListener(this);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                if (menuItem.isCheckable()) {
                    if (mLastItem != null) {
                        mLastItem.setChecked(false);
                    }
                    menuItem.setChecked(true);
                    mLastItem = menuItem;
                }
                drawerLayout.closeDrawers();
                return onNavigationItemClick(menuItem);
            }
        });

        // Drawer Toggle
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        if (navigationView.getMenu().size() > 0)
            mLastItem = navigationView.getMenu().getItem(0);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
        drawerToggle.onDrawerSlide(drawerView, slideOffset);
    }

    @Override
    public void onDrawerOpened(View drawerView) {
        drawerToggle.onDrawerOpened(drawerView);
        invalidateOptionsMenu();
    }

    @Override
    public void onDrawerClosed(View drawerView) {
        drawerToggle.onDrawerClosed(drawerView);
        invalidateOptionsMenu();
    }

    @Override
    public void onDrawerStateChanged(int newState) {
        drawerToggle.onDrawerStateChanged(newState);
    }

    /**
     * This method is called when menuItem is clicked.
     *
     * @param menuItem item that was clicked.
     * @return {@inheritDoc NavigationView}
     */
    protected abstract boolean onNavigationItemClick(MenuItem menuItem);

    /**
     * Set Up your navigation menu here.
     * Called when navigation menu is created.
     *
     * @param navigationMenu navigation menu.
     */
    protected abstract void onCreateNavigationMenu(Menu navigationMenu);
}
