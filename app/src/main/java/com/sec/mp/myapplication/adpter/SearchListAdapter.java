package com.sec.mp.myapplication.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.sec.mp.myapplication.R;
import com.sec.mp.myapplication.entity.SearchMusic;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/6.
 */

public class SearchListAdapter extends BaseAdapter {
    ArrayList<SearchMusic.ShowapiResBodyBean.PagebeanBean.ContentlistBean> songs;
    Context context;
    LayoutInflater inflater;

    public SearchListAdapter(ArrayList<SearchMusic.ShowapiResBodyBean.PagebeanBean.ContentlistBean> songs, Context context) {
        this.songs = songs;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setData(ArrayList<SearchMusic.ShowapiResBodyBean.PagebeanBean.ContentlistBean> songs) {
        this.songs = songs;
    }

    @Override
    public int getCount() {
        return songs.size();
    }

    @Override
    public SearchMusic.ShowapiResBodyBean.PagebeanBean.ContentlistBean getItem(int position) {
        return songs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_search, null);
            holder.tvablumname= (TextView) convertView.findViewById(R.id.tvablumname);
            holder.imageView = (ImageView) convertView.findViewById(R.id.ivsingpiv);
            holder.tvsinger = (TextView) convertView.findViewById(R.id.tv_singername);
            holder.tvsongname = (TextView) convertView.findViewById(R.id.tv_songname);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();
        SearchMusic.ShowapiResBodyBean.PagebeanBean.ContentlistBean song = getItem(position);
        holder.tvablumname.setText(song.getAlbumname());
        holder.tvsongname.setText(song.getSongname());
        holder.tvsinger.setText(song.getSingername());
        Glide.with(context).load(song.getAlbumpic_small()).into(holder.imageView);
        return convertView;

    }

    class ViewHolder{

        ImageView imageView;
        TextView tvablumname;
        TextView tvsinger;
        TextView tvsongname;
    }

}
