package com.sec.mp.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.utils.ToastUtils;
import com.sec.mp.myapplication.R;
import com.sec.mp.myapplication.acitivity.SongActivity;
import com.sec.mp.myapplication.adpter.HotListAdapter;
import com.sec.mp.myapplication.base.BaseApplication;
import com.sec.mp.myapplication.base.BaseFragment;
import com.sec.mp.myapplication.contants.WhatConstants;
import com.sec.mp.myapplication.entity.Music;
import com.sec.mp.myapplication.model.Model;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/16.
 */

public class GangTaiListFragment extends BaseFragment implements HotListAdapter.OnItemClickLitener{
    private LinearLayoutManager linearLayoutManager = null;
    private RecyclerView recyclerView;
    HotListAdapter adapter;
    Music music;
    public static boolean isRefresh = true;
    Model model=new Model();
    ArrayList<Music.ShowapiResBodyBean.PagebeanBean.Song> songs;
    @Override
    protected void handler(Message msg) {
        switch (msg.what){
            case WhatConstants.GET_GANGTAILIST_SUCCESS:
                music= JSONObject.parseObject((String) msg.obj,Music.class);
                songs= (ArrayList<Music.ShowapiResBodyBean.PagebeanBean.Song>) music.getShowapi_res_body().getPagebean().getSonglist();
                adapter.setData(songs);
                break;
            case WhatConstants.GET_GANGTAILIST_FAIL:
                String info = (String) msg.obj;
                if (TextUtils.isEmpty(info)) {
                    ToastUtils.showShortToast(BaseApplication.getmApplication(), "登陆状态异常");
                } else {
                    ToastUtils.showShortToast(BaseApplication.getmApplication(), info);
                }
                break;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_hotlist,null);
        model.LoadgangtaiList(handler, WhatConstants.GET_GANGTAILIST_SUCCESS,WhatConstants.GET_GANGTAILIST_FAIL);
        linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerView= (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter=new HotListAdapter(songs,getContext());
        adapter.setOnItemClickLitener(this);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(getActivity(),SongActivity.class);
        intent.putExtra("song",  songs.get(position));
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(View view, int position) {

    }
}
