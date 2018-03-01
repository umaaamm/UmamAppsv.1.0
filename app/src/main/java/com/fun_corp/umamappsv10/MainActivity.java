package com.fun_corp.umamappsv10;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.telephony.SmsManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.fun_corp.umamappsv10.Tab.SlidingTabLayout;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;


import static com.fun_corp.umamappsv10.R.layout.fragment_aplikasi;


public class MainActivity extends AppCompatActivity  {

    public Drawer.Result navigationDrawerLeft;
    public AccountHeader.Result headerNavigatorLeft;
    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Toolbar toolbar=(Toolbar) findViewById(R.id.my_awesome_toolbar);
        //toolbar.setLogo(R.mipmap.ic_label_white_48dp);
        toolbar.setTitle("Mandang Elu");
        toolbar.setSubtitle("Smart Kandang Ayam Petelur");
        setSupportActionBar(toolbar);

        //Tab
        mViewPager=(ViewPager)findViewById(R.id.vp_tabs);
        mViewPager.setAdapter(new MyAdapter(getSupportFragmentManager(),this));

        mSlidingTabLayout=(SlidingTabLayout)findViewById(R.id.stl_tabs);
        mSlidingTabLayout.setDistributeEvenly(true);
        mSlidingTabLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        mSlidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.colorAccent));
        mSlidingTabLayout.setCustomTabView(R.layout.tab_view,R.id.tv_tab);
        mSlidingTabLayout.setViewPager(mViewPager);
        mSlidingTabLayout.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

//


            @Override
            public void onPageSelected(int position) {
navigationDrawerLeft.setSelection(position);
                switch (position){
                    case   0:
                        toolbar.setTitle("Beranda");
                        break;
                    case   1:
                        toolbar.setTitle("Statistik");
                        break;
                    case   2:
                        toolbar.setTitle("Tentang Kami");
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //navigation Drawer
        //header
        headerNavigatorLeft =new AccountHeader()
                .withActivity(this)
                .withCompactStyle(false)
                .withSavedInstance(savedInstanceState)
                .withThreeSmallProfileImages(false)
                .withHeaderBackground(R.drawable.desain_belakang)
                .build();
        //left  .addProfiles(new ProfileDrawerItem().withName("MandangElu").withEmail("HambaAllah@gmail.com").withIcon(getResources().getDrawable(R.mipmap.hamba)))
        navigationDrawerLeft = new Drawer()
        .withActivity(this)
                .withToolbar(toolbar)
                .withDisplayBelowToolbar(false)
                .withActionBarDrawerToggleAnimated(true)
                .withAccountHeader(headerNavigatorLeft)
                .withDrawerGravity(Gravity.LEFT)
                .withSavedInstance(savedInstanceState)
                .withSelectedItem(0)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int id, long l, IDrawerItem drawerItem) {

                        //script link nya
                        mViewPager.setCurrentItem(id);
                        toolbar.setTitle(((PrimaryDrawerItem) drawerItem).getName());

                    }
                })
                .build();
        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Beranda").withIcon(getResources().getDrawable(R.mipmap.ic_content_paste_white_24dp)));
        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Statistik").withIcon(getResources().getDrawable(R.mipmap.ic_trending_up_white_24dp)));
        //navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Lokasi").withIcon(getResources().getDrawable(R.mipmap.ic_location_on_b_48dp)));
        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Tentang Kami").withIcon(getResources().getDrawable(R.mipmap.ic_group_white_24dp)));



    }

}
