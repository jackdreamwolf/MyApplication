package com.sec.mp.myapplication.acitivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.sec.mp.myapplication.R;
import com.sec.mp.myapplication.base.BaseActivity;
import com.sec.mp.myapplication.contants.WhatConstants;
import com.sec.mp.myapplication.entity.Music;
import com.sec.mp.myapplication.entity.SongLrc;
import com.sec.mp.myapplication.model.Model;

import org.apache.commons.lang.StringEscapeUtils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import jp.wasabeef.glide.transformations.BlurTransformation;


/**
 * Created by Administrator on 2017/3/9.
 */

public class SongActivity extends BaseActivity {
    TextView tvsongname;
    TextView tvsingername;
    TextView tvsonglrc;
    TextView tvtotaltime;
    TextView tvcurrtime;
    ImageView ivpic, ivsinger;
    Model model = new Model();
    SongLrc songLrc;
    HashMap<String, String> lrcmap;

    @Override
    protected void handler(Message msg) throws IOException {
        switch (msg.what) {
            case WhatConstants.GET_LRC_SUCCESS:
                songLrc = JSONObject.parseObject((String) msg.obj, SongLrc.class);
                //  PrintWriter out=null;
                // out=new PrintWriter(StringEscapeUtils.unescapeHtml(songLrc.getShowapi_res_body().getLyric()));
                lrcmap = new HashMap<>();
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(StringEscapeUtils.unescapeHtml(songLrc.getShowapi_res_body().getLyric()))));
                String line="";
                while((line=reader.readLine())!=null){
                    if("".equals(line)){
                        continue;
                    }
                    if(line.contains(".")||line.length()>10){
                        String time=line.substring(1,9);
                        String content=line.substring(10);
                        lrcmap.put(time,content);
                    }
                }

                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activitysonginfo;
    }

    @Override
    public void onCreate(FragmentManager manager, Bundle savedInstanceState) {
        Intent intent = getIntent();
        Music.ShowapiResBodyBean.PagebeanBean.Song song = (Music.ShowapiResBodyBean.PagebeanBean.Song) intent.getSerializableExtra("song");
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
        int a = song.getSeconds() / 60;
        int b = song.getSeconds() - a * 60;
        String r;
        if (b > 10) {
            r = a + ":" + b;
        } else {
            r = a + ":0" + b;
        }
        tvtotaltime.setText(r);
        Glide.with(this).load(song.getAlbumpic_big()).into(ivsinger);
        Glide.with(this).load(song.getAlbumpic_small()).bitmapTransform(new BlurTransformation(this, 15)).into(ivpic);
        if(lrcmap!=null){
          //TODO  get值为currenttime  String content=lrcmap.get()
        }
    }

}
