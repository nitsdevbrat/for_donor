package fordonor.com.fordonor.Utility;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import fordonor.com.fordonor.Contribution.activity.MycontributionActivity;
import fordonor.com.fordonor.R;
import fordonor.com.fordonor.campaign.activity.AddCampaignActivity;
import fordonor.com.fordonor.campaign.activity.AddPerkActivity;
import fordonor.com.fordonor.campaign.activity.DonarCampaignActivity;
import fordonor.com.fordonor.campaign.activity.MycampaignActivity;
import fordonor.com.fordonor.home.activity.HomeActivity;

public abstract class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private TextView toolbar_title;
    private NavigationView nav_view;
    private AppCompatImageView menu_new;
    private ImageView search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        initview();
        setupNavView();
        setToolbarTitle();

    }


    private void initview() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar_title = (TextView) findViewById(R.id.tv_menu);
        nav_view = (NavigationView) findViewById(R.id.nav_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        menu_new = (AppCompatImageView) findViewById(R.id.menu_new);
        search=(ImageView) findViewById(R.id.search);

        setToolbarTitle();
    }


    protected void addContentView(int layoutResId) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ((FrameLayout) findViewById(R.id.frame_container)).addView(inflater.inflate(layoutResId, null),
                new ViewGroup.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));

    }

    private void setToolbarTitle() {
        toolbar_title.setText(toolBarTitle());
    }

    public abstract String toolBarTitle();


    /*//////////////////////////////////////////Drawer Set/////////////////////////////////////*/
    private void setupNavView() {
        nav_view.setNavigationItemSelectedListener(this);
        Menu menu = nav_view.getMenu();

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    protected void enableDrawer(boolean enable) {
        if (enable) {
            if (mDrawerLayout != null) {
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                mDrawerToggle.setDrawerIndicatorEnabled(enable);
                mDrawerLayout.setDrawerListener(mDrawerToggle);

                mDrawerToggle.syncState();
            }
        } else {
            if (mDrawerLayout != null) {
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                mDrawerToggle.setDrawerIndicatorEnabled(enable);
                toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.back));
                addOrRemoveProperty(toolbar_title, RelativeLayout.CENTER_IN_PARENT, true);

                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackPressed();
                    }
                });
            }
        }
    }
    protected void enablesearch(boolean enable) {
        if (enable) {
           search.setVisibility(View.VISIBLE);
        } else {
            search.setVisibility(View.GONE);

        }
    }

    private void addOrRemoveProperty(View view, int property, boolean flag){
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
        if(flag){
            layoutParams.addRule(property);
        }else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                layoutParams.removeRule(property);
            }
        }
        view.setLayoutParams(layoutParams);
    }

    private void closeDrawer() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawers();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_account:

                Intent nav_profile = new Intent(BaseActivity.this, AddCampaignActivity.class);
              //  nav_profile.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(nav_profile);
               // finish();
                break;

            case R.id.nav_statistics:
                Intent nav_perk = new Intent(BaseActivity.this, AddPerkActivity.class);
               // nav_perk.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(nav_perk);
              //  finish();
                break;


            case R.id.nav_settings:
                Intent nav_donar = new Intent(BaseActivity.this, DonarCampaignActivity.class);
               // nav_donar.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(nav_donar);
                //finish();
                break;

            case R.id.nav_help:
                Intent nav_home = new Intent(BaseActivity.this, HomeActivity.class);
                nav_home.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(nav_home);
                finish();

                break;

            case R.id.nav_refer:
                Intent nav_contibution= new Intent(BaseActivity.this, MycampaignActivity.class);
               // nav_contibution.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(nav_contibution);
              //  finish();
                break;

            case R.id.nav_logout:
                Intent nav_mycampaign = new Intent(BaseActivity.this, MycontributionActivity.class);
               // nav_mycampaign.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(nav_mycampaign);
              //  finish();
                break;


        }
        return true;
    }

}

