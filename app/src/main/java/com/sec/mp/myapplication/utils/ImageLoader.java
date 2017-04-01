package com.sec.mp.myapplication.utils;

import java.io.File;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;

import com.sec.mp.myapplication.R;

/** ͼƬ������ */
public class ImageLoader {
	//弱引用
	private Map<String, SoftReference<Bitmap>> cache = new HashMap<String, SoftReference<Bitmap>>();
	private Context context;
	//ͼƬ�������񼯺�
	private List<ImageLoadTask> tasks = new ArrayList<ImageLoadTask>();
	//����������ѭ���񼯺ϵ����߳�
	private Thread workThread;
	private boolean isLoop = true;
	private AbsListView listView;
	private Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case HANDLER_IMAGE_LOADED:
				ImageLoadTask task = (ImageLoadTask) msg.obj;
				//ͨ��tag�ҵ���Ӧ��ImageView:
				ImageView iv=(ImageView) listView.findViewWithTag(task.path);
				if(iv != null){ //ImageView�ҵ���
					if(task.bitmap != null ){
						iv.setImageBitmap(task.bitmap);
					}else{
						iv.setImageResource(R.drawable.ic_launcher);
					}
				}
				break;
			}
		}
	};
	public static final int HANDLER_IMAGE_LOADED=1;

	
	
	public ImageLoader(Context context, AbsListView listView) {
		this.listView = listView;
		this.context = context;
		workThread = new Thread(){
			public void run() {
				//������ѭ���񼯺� ��������� ���ȡ����������
				while(isLoop){
					if(!tasks.isEmpty()){ //������
						ImageLoadTask task = tasks.remove(0);
						Bitmap bitmap = loadBitmap(task.path);
						task.bitmap = bitmap;
						//��bitmap���õ�ImageView��
						Message msg=new Message();
						msg.what = HANDLER_IMAGE_LOADED;
						msg.obj = task;
						handler.sendMessage(msg);
					}else{ //û����  �߳������ȴ�
						try {
							synchronized (workThread) {
								workThread.wait();
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		};
		workThread.start();

	}
	
	/** ͨ��һ�������ַ ��ȡͼƬ */
	public Bitmap loadBitmap(String path){
		Bitmap bitmap = null;
		try {
			InputStream is = HttpUtils.get(path);
			bitmap=BitmapUtils.loadBitmap(is, 50, 50);
			//��bitmap�����ڴ滺��
			cache.put(path, new SoftReference<Bitmap>(bitmap));
			//��bitmap�����ļ�����
			// /data/data/com.tarena.musicplayer/cache/images/filename
			String filename = path.substring(path.lastIndexOf("/"));
			File file = new File(context.getCacheDir(), "images"+filename);
			BitmapUtils.save(bitmap, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bitmap;
	}
	
	
	/**
	 * �첽����ͼƬ�����õ���Ӧ��ImageView��
	 * @param path
	 * @param imageView
	 */
	public void get(String path, ImageView imageView){
		//ȥ�ڴ滺���в�ѯ �Ƿ���ͼƬ����
		SoftReference<Bitmap> ref = cache.get(path);
		if(ref!=null){  //��ǰ���
			Bitmap b = ref.get();  
			if(b!=null) { //��ǰ���Bitmap����
				//Log.i("info", "���ڴ滺���л�ȡ��ͼƬ...");
				imageView.setImageBitmap(b);
				return;
			}
		}
		//��ȥ�ļ�����Ŀ¼�в����ļ�����
		String filename = path.substring(path.lastIndexOf("/"));
		File file = new File(context.getCacheDir(), "images"+filename);
		Bitmap b = BitmapUtils.loadBitmap(file);
		if(b!=null){
			//Log.i("info", "���ļ������л�ȡ��ͼƬ...");
			imageView.setImageBitmap(b);
			//���ļ���bitmap�����ڴ滺��
			cache.put(path, new SoftReference<Bitmap>(b));
			return;
		}
		//����tag��Ŀ����Ϊ����handler��ͨ��tag
		//�ҵ�ImageView
		imageView.setTag(path);
		//�����񼯺������ͼƬ��������
		ImageLoadTask task = new ImageLoadTask();
		task.path = path;
		tasks.add(task);
		//���ѹ����߳�  �����ɻ�
		synchronized (workThread) {
			workThread.notify();
		}
	}
	
	//��װһ��ͼƬ�����������
	class ImageLoadTask{
		String path;
		Bitmap bitmap;
	}

	/** ֹͣ�����߳� */
	public void stopThread() {
		isLoop = false;
		synchronized (workThread) {
			workThread.notify();
		}
	}

}
