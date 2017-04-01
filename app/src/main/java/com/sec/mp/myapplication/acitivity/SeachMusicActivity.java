package com.sec.mp.myapplication.acitivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sec.mp.myapplication.R;
import com.sec.mp.myapplication.base.BaseActivity;
import com.sec.mp.myapplication.contants.WhatConstants;
import com.sec.mp.myapplication.entity.Music;
import com.sec.mp.myapplication.entity.SearchMusic;
import com.sec.mp.myapplication.model.Model;

import java.io.IOException;

import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by Administrator on 2017/3/15.
 */

public class SeachMusicActivity extends BaseActivity {
    TextView tvsongname;
    TextView tvsingername;
    TextView tvsonglrc;
    TextView tvtotaltime;
    TextView tvcurrtime;
    ImageView ivpic, ivsinger;
    Model model = new Model();
    @Override
    protected void handler(Message msg) throws IOException {

    }

    @Override
    public void onCreate(FragmentManager manager, Bundle savedInstanceState) {
        Intent intent = getIntent();
        SearchMusic.ShowapiResBodyBean.PagebeanBean.ContentlistBean song = (SearchMusic.ShowapiResBodyBean.PagebeanBean.ContentlistBean) intent.getSerializableExtra("song");
        model.LoadLyric(song.getSongid(), handler, WhatConstants.GET_LRC_SUCCESS, WhatConstants.GET_LRC_FAIL);
        tvsongname = (TextView) findViewById(R.id.tvPMTitle);
        tvsingername = (TextView) findViewById(R.id.tvPMSinger);
        tvcurrtime = (TextView) findViewById(R.id.tvPMCurrentTime);
        tvtotaltime = (TextView) findViewById(R.id.tvPMTotalTime);
        tvsonglrc = (TextView) findViewById(R.id.tvPMLrc);
        ivpic = (ImageView) findViewById(R.id.ivPMBackground);
        ivsinger = (ImageView) findViewById(R.id.ivPMAlbum);
        tvsongname.setText(song.getSongname());
        tvsingername.setText(song.getSingername());
        tvsonglrc.setText(song.getAlbumname());
        Glide.with(this).load(song.getAlbumpic_big()).into(ivsinger);
        Glide.with(this).load(song.getAlbumpic_small()).bitmapTransform(new BlurTransformation(this, 15)).into(ivpic);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activitysonginfo;
    }
}
