package com.fun_corp.umamappsv10;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;

public class MyAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private String[] titles={"Beranda","Statistik","Tentang Kami"};
    int[] icon = new int[]{R.mipmap.ic_content_paste_white_24dp,R.mipmap.ic_trending_up_white_24dp,R.mipmap.ic_group_white_24dp};
    private int heightIcon;
    public MyAdapter(FragmentManager fm, Context c) {
        super(fm);
        mContext=c;
        double scale=c.getResources().getDisplayMetrics().density;
        heightIcon=(int)(24*scale+0.5f);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag=null;
        if ((position==0)){
            frag=new Beranda();
        }else if(position==1){
            frag=new Statistik();
        }else if (position==2){
            frag=new Tentang();
        }
        Bundle b=new Bundle();
        b.putInt("position",position);
        return frag;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Drawable d=mContext.getResources().getDrawable(icon[position]);
        d.setBounds(0,0,heightIcon,heightIcon);
        ImageSpan is=new ImageSpan(d);
        SpannableString sp = new SpannableString(" ");
        sp.setSpan(is,0,sp.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return  (sp);
    }
}
