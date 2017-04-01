package com.sec.mp.myapplication.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.IOException;

/**
 * Created by Administrator on 2017/3/27.
 */

public class MusicService extends Service {
    MediaPlayer player;
    Boolean isLoop=true;
    @Override
    public void onCreate() {
        super.onCreate();
        player=new MediaPlayer();
        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });
    }

    @Override
    public void onDestroy() {
        isLoop=false;
        player.release();
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MusicBinder();
    }
    public class MusicBinder extends Binder {
        /* 开始或暂停 */
        public void startOrPause(){
            if(player.isPlaying()){
                player.pause();
            }else{
                player.start();
            }
        }

        /* 跳转到某个位置继续播放 */
        public void seekTo(int position){
            player.seekTo(position);
        }

        /* 播放音乐 */
        public void playMusic(String url){
            player.reset();
            player.prepareAsync();
        }

    }
}
