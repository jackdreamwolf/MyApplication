package com.sec.mp.myapplication.adpter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.sec.mp.myapplication.R;
import com.sec.mp.myapplication.adpter.viewholder.HotListViewHolder;
import com.sec.mp.myapplication.entity.Music;

import java.util.ArrayList;

import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by Administrator on 2017/3/6.
 */

public class HotListAdapter extends RecyclerView.Adapter<HotListViewHolder> {
    ArrayList<Music.ShowapiResBodyBean.PagebeanBean.Song> songs;
    Context context;

    public HotListAdapter(ArrayList<Music.ShowapiResBodyBean.PagebeanBean.Song> songs, Context context) {
        this.songs = songs;
        this.context = context;
    }

    public void setData(ArrayList<Music.ShowapiResBodyBean.PagebeanBean.Song> songs) {
        this.songs = songs;
        this.notifyDataSetChanged();
    }

    @Override
    public HotListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        HotListViewHolder holder = new HotListViewHolder(LayoutInflater.from(context).inflate(R.layout.item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final HotListViewHolder holder, final int position) {
        Glide.with(context).load(songs.get(position).getAlbumpic_small()).into(holder.getIvsingpic());
        holder.getRelativeLayout().setBackgroundColor(context.getResources().getColor(android.R.color.white));
        Glide.with(context).load(songs.get(position).getAlbumpic_small()).bitmapTransform(new BlurTransformation(context, 25, 4)).into(new SimpleTarget<GlideDrawable>() {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                holder.getRelativeLayout().setBackground(resource);
            }
        });

        holder.getTvsongname().setText(songs.get(position).getSongname());
        holder.getTvsingname().setText(songs.get(position).getSingername());

        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickLitener.onItemClick(holder.itemView, position);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
//                    int pos = holder.getAdapterPosition()-1;
                    mOnItemClickLitener.onItemLongClick(holder.itemView, position);
                    return false;
                }
            });
        }
    }


    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public int getItemCount() {
        if (songs == null) {
            return 0;
        }
        return songs.size();
    }

    private Bitmap drawableToBitamp(Drawable drawable) {
        Bitmap bitmap;
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        System.out.println("Drawable转Bitmap");
        Bitmap.Config config =
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                        : Bitmap.Config.RGB_565;
        bitmap = Bitmap.createBitmap(w, h, config);
        //注意，下面三行代码要用到，否在在View或者surfaceview里的canvas.drawBitmap会看不到图
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        return bitmap;
    }
}
