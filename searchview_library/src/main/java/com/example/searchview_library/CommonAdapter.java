package com.example.searchview_library;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 笨货 on 2016/3/30.
 * 热门搜索和历史记录的Adapter
 */
public class CommonAdapter extends BaseAdapter {
    TextView tv_item_hint;
    private Context context;
    private List<String> datas;

    public CommonAdapter(Context context, List<String> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        //最大返回40条数据
        return Math.min(40, datas.size());
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_search1, null);
            tv_item_hint = (TextView) convertView.findViewById(R.id.tv_item_hint);
        }
        String data = datas.get(position);
        tv_item_hint.setText(data);

        return convertView;
    }

    public void updateRecordList(List<String> historySearchDatas) {
        this.datas = historySearchDatas;
        notifyDataSetChanged();
    }

}
