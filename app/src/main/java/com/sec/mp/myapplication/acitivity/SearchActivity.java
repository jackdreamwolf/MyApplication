package com.sec.mp.myapplication.acitivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.utils.ToastUtils;
import com.example.searchview_library.SearchALG;
import com.example.searchview_library.SearchView;
import com.sec.mp.myapplication.R;
import com.sec.mp.myapplication.adpter.SearchListAdapter;
import com.sec.mp.myapplication.base.BaseActivity;
import com.sec.mp.myapplication.base.BaseApplication;
import com.sec.mp.myapplication.contants.WhatConstants;
import com.sec.mp.myapplication.entity.SearchMusic;
import com.sec.mp.myapplication.model.Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseActivity {
    Model model = new Model();
    SearchListAdapter adapter;
    private SearchView searchView;
    private List<String> hot_datas;
    ListView listView;
    //提示列表数据
    private List<String> hint_datas;
    private SearchALG searchALG;
    private List<String> changedHintDatas;
    SearchMusic searchMusic;
    ArrayList<SearchMusic.ShowapiResBodyBean.PagebeanBean.ContentlistBean> songs;

    @Override
    protected void handler(Message msg) throws IOException {
        switch (msg.what) {
            case WhatConstants.GET_SEARCHLIST_SUCCESS:
                searchMusic = JSONObject.parseObject((String) msg.obj, SearchMusic.class);
                songs = (ArrayList<SearchMusic.ShowapiResBodyBean.PagebeanBean.ContentlistBean>) searchMusic.getShowapi_res_body().getPagebean().getContentlist();
                adapter = new SearchListAdapter(songs, context);
                adapter.setData(songs);
                listView.setVisibility(View.VISIBLE);
                listView.setAdapter(adapter);
                break;
            case WhatConstants.GET_HOTLIST_FAIL:
                String info = (String) msg.obj;
                if (TextUtils.isEmpty(info)) {
                    ToastUtils.showShortToast(BaseApplication.getmApplication(), "解析异常");
                } else {
                    ToastUtils.showShortToast(BaseApplication.getmApplication(), info);
                }
                break;
        }
    }

    @Override
    public void onCreate(FragmentManager manager, Bundle savedInstanceState) {
        searchView = (SearchView) findViewById(R.id.searchView1);
        searchView.setOnSearchListener(new MyOnSearchListener());
        listView = (ListView) searchView.findViewById(R.id.lv_search_hint);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getContext(),SeachMusicActivity.class);
                intent.putExtra("song",songs.get(position));
                startActivity(intent);
            }
        });
        initData();
    }

    @Override
    protected void onDestroy() {
        listView.setVisibility(View.GONE);
        super.onDestroy();
    }

    private void initData() {
        hot_datas = new ArrayList<>();
        hint_datas = new ArrayList<>();
        searchALG = new SearchALG(this);


        hot_datas.add("三生三世十里桃花 ");
        hot_datas.add("周杰伦 ");
        hot_datas.add("ladygaga ");
        hot_datas.add("汪峰");


        //注：若不需要热搜列表，可以不设置
        //设置热搜数据显示的列数
        searchView.setHotNumColumns(2);
        //设置热搜数据
        searchView.setHotSearchDatas(hot_datas);

        /**
         * 设置提示数据的集合
         */
        for (int i = 0; i < 10; i++) {
            hint_datas.add("ts" + "安卓学习" + "Android Hint " + i + " 你好");
        }

        /**
         * 设置自动保存搜索记录
         */
        searchView.keepSearchHistory(true);

        //设置提示列表的最大显示列数
        searchView.setMaxHintLines(8);
        //设置保存搜索记录的个数
        searchView.setMaxHistoryRecordCount(6);

    }

    /**
     * 设置searview的监听
     */
    class MyOnSearchListener implements SearchView.OnSearchListener {

        /**
         * 搜索回调
         *
         * @param searchText 进行搜索的文本
         */
        @Override
        public void onSearch(String searchText) {
            if (!TextUtils.isEmpty(searchText)) {
                model.LoadSearchList(searchText, handler, WhatConstants.GET_SEARCHLIST_SUCCESS, WhatConstants.GET_SEARCHLIST_FAIL);
            } else {
                Toast.makeText(SearchActivity.this, "搜索内容不能为空！", Toast.LENGTH_SHORT).show();
            }
        }

        /**
         * 刷新提示列表
         *
         * @param changedText 改变后的文本
         */
        @Override
        public void onRefreshHintList(String changedText) {
            if (changedHintDatas == null) {
                changedHintDatas = new ArrayList<>();
            } else {
                changedHintDatas.clear();
            }
            if (TextUtils.isEmpty(changedText)) {
                return;
            }
            for (int i = 0; i < hint_datas.size(); i++) {
                String hint_data = hint_datas.get(i);
                boolean isAdd = searchALG.isAddToHintList(hint_data, changedText);
                if (isAdd) {
                    changedHintDatas.add(hint_datas.get(i));
                }
            }

            /**
             * 根据搜索框文本的变化，动态的改变提示的listView
             */
            searchView.updateHintList(changedHintDatas);

        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }
}
