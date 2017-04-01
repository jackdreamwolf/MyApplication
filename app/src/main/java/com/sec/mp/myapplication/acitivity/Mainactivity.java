package com.sec.mp.myapplication.acitivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.sec.mp.myapplication.R;
import com.sec.mp.myapplication.base.BaseActivity;
import com.sec.mp.myapplication.fragment.GangTaiListFragment;
import com.sec.mp.myapplication.fragment.HanguoListFragment;
import com.sec.mp.myapplication.fragment.HotListFragment;
import com.sec.mp.myapplication.fragment.JanpanListFragment;
import com.sec.mp.myapplication.fragment.LandListFragment;
import com.sec.mp.myapplication.fragment.MeiZuListFragment;
import com.sec.mp.myapplication.fragment.OumeiListFragment;
import com.sec.mp.myapplication.fragment.RuckListFragment;
import com.sec.mp.myapplication.fragment.SolesListFragment;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/16.
 */

public class Mainactivity extends BaseActivity {
    ArrayList<Fragment> fragments;
    ImageButton ibpic;
    RadioGroup radiogroup;
    Mayadpter adapter;
    ViewPager viewpager;
    RadioButton rbhot, rbsole, rbland, rboumei, rbgangtai, rbhanguo, rbjanpen, rbmeiyou, rbruck;

    @Override
    protected void handler(Message msg) throws IOException {

    }

    @Override
    public void onCreate(FragmentManager manager, Bundle savedInstanceState) {
        /**
         * 控件初始化
         */
        setview();
        /**
         * 设置fragment
         */
        setfragment();
        /**
         * 设置监听器
         */
        setLitenser();
    }

    private void setview() {
        ibpic = (ImageButton) findViewById(R.id.iBsearch);
        radiogroup = (RadioGroup) findViewById(R.id.radiogroup);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        rbhot = (RadioButton) findViewById(R.id.rb_hot);
        rbsole = (RadioButton) findViewById(R.id.rb_sole);
        rbland = (RadioButton) findViewById(R.id.rb_land);
        rboumei = (RadioButton) findViewById(R.id.rb_oumei);
        rbgangtai = (RadioButton) findViewById(R.id.rb_gangtai);
        rbhanguo = (RadioButton) findViewById(R.id.rb_hanguo);
        rbjanpen = (RadioButton) findViewById(R.id.rb_janpen);
        rbmeiyou = (RadioButton) findViewById(R.id.rb_minyao);
        rbruck = (RadioButton) findViewById(R.id.rb_ruck);
    }

    private void setfragment() {
        fragments = new ArrayList<Fragment>();
        fragments.add(new HotListFragment());
        fragments.add(new SolesListFragment());
        fragments.add(new LandListFragment());
        fragments.add(new OumeiListFragment());
        fragments.add(new GangTaiListFragment());
        fragments.add(new HanguoListFragment());
        fragments.add(new JanpanListFragment());
        fragments.add(new MeiZuListFragment());
        fragments.add(new RuckListFragment());
        adapter = new Mayadpter(getSupportFragmentManager());
        viewpager.setAdapter(adapter);
    }

    private void setLitenser() {
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        rbhot.setChecked(true);
                        break;
                    case 1:
                        rbsole.setChecked(true);
                        break;
                    case 2:
                        rbland.setChecked(true);
                        break;
                    case 3:
                        rboumei.setChecked(true);
                        break;
                    case 4:
                        rbgangtai.setChecked(true);
                        break;
                    case 5:
                        rbhanguo.setChecked(true);
                        break;
                    case 6:
                        rbjanpen.setChecked(true);
                        break;
                    case 7:
                        rbmeiyou.setChecked(true);
                        break;
                    case 8:
                        rbruck.setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_hot:
                        viewpager.setCurrentItem(0);
                        break;
                    case R.id.rb_sole:
                        viewpager.setCurrentItem(1);
                        break;
                    case R.id.rb_land:
                        viewpager.setCurrentItem(2);
                        break;
                    case R.id.rb_oumei:
                        viewpager.setCurrentItem(3);
                        break;
                    case R.id.rb_gangtai:
                        viewpager.setCurrentItem(4);
                        break;
                    case R.id.rb_hanguo:
                        viewpager.setCurrentItem(5);
                        break;
                    case R.id.rb_janpen:
                        viewpager.setCurrentItem(6);
                        break;
                    case R.id.rb_minyao:
                        viewpager.setCurrentItem(7);
                        break;
                    case R.id.rb_ruck:
                        viewpager.setCurrentItem(8);
                        break;
                }

            }
        });

        ibpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mainactivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    class Mayadpter extends FragmentPagerAdapter {
        public Mayadpter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
}
