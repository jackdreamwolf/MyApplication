package com.sec.mp.myapplication.adpter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sec.mp.myapplication.R;


/**
 * Created by Administrator on 2017/3/6.
 */

public class HotListViewHolder extends RecyclerView.ViewHolder {
    TextView tvsongname;
    TextView tvsingname;
    ImageView ivsingpic;
    RelativeLayout relativeLayout;

    public RelativeLayout getRelativeLayout() {
        return relativeLayout;
    }

    public TextView getTvsongname() {
        return tvsongname;
    }

    public TextView getTvsingname() {
        return tvsingname;
    }

    public ImageView getIvsingpic() {
        return ivsingpic;
    }

    public HotListViewHolder(View view) {
        super(view);
        tvsongname = (TextView) view.findViewById(R.id.tv_songname);
        tvsingname = (TextView) view.findViewById(R.id.tv_singername);
        ivsingpic = (ImageView) view.findViewById(R.id.ivsingpiv);
        relativeLayout= (RelativeLayout) view.findViewById(R.id.rl_layout);
    }
}
