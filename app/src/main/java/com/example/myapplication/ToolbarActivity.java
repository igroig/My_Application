package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;




public class ToolbarActivity extends AppCompatActivity {
    protected Toolbar toolbar;
    private ImageView logo;

    /**
     * {@inheritDoc Activity}
     * <p/>
     * <b>Note that</b>  layout must have toolbar as child with id <b>"toolbar"</b> <p/>
     * If toolbar contains imageView with layout id <b>"logo"</b>
     * it will show that instead of toolbar title. Otherwise it will show toolbar title.
     *
     * @param layoutResID {@inheritDoc Activity}
     */
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

//        getSupportActionBar().hide();//Ocultar ActivityBar anterior


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (logo != null) {
            showLogo();
        } else {
            enableTitle();
        }
    }

    /**
     * @return toolbar of this layout.
     */
    public Toolbar getToolbar() {
        return toolbar;
    }

    /**
     * Show title instead of logo on {@code toolbar}.
     */
    public void enableTitle() {
        if (logo != null) {
            logo.setVisibility(View.GONE);
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }
    }

    /**
     * Show logo instead of title on {@code toolbar}.
     */
    public void showLogo() {
        if (logo != null) {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayShowTitleEnabled(false);
            }
            logo.setVisibility(View.VISIBLE);
        }
    }

    /**
     * {@inheritDoc Activity}
     * <p>
     * This method also handles <b>home</b> button click and calls {@code onBackPressed()}
     * </p>
     *
     * @param item {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * This method sets fragment title to activity, or shows logo if fragment doesn't have title.
     *
     * @param fragment Fragment to attach to this activity.
     */


}
