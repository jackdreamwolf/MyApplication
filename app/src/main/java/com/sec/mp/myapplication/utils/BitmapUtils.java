package com.sec.mp.myapplication.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.AsyncTask;

import com.sec.mp.myapplication.base.BaseApplication;

/** ͼƬ��ع����� */
public class BitmapUtils {

	/**
	 * �첽�ڹ����߳���  ģ��������ͼƬ
	 * @param bitmap  ԴͼƬ
	 * @param radius  ģ���뾶
	 * @param call	  ģ����ɺ󽫻��Զ����øûص�����
	 */
	public static void loadBluredBitmap(final Bitmap bitmap,final int radius,final BitmapCallback call){
		AsyncTask<String, String, Bitmap> task = new AsyncTask<String, String, Bitmap>(){
			protected Bitmap doInBackground(String... params) {
				Bitmap b = createBlurBitmap(bitmap, radius);
				return b;
			}
			protected void onPostExecute(Bitmap result) {
				call.onBitmapLoaded(result);
			}
		};
		task.execute();
	}
	
	/**
	 * ����bitmap ����ģ���뾶 ����һ����ģ����bitmap �ȽϺ�ʱ
	 * 
	 * @param sentBitmap
	 * @param radius
	 * @return
	 */
	private static Bitmap createBlurBitmap(Bitmap sentBitmap, int radius) {
		Bitmap bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);
		if (radius < 1) {
			return (null);
		}
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();
		int[] pix = new int[w * h];
		bitmap.getPixels(pix, 0, w, 0, 0, w, h);
		int wm = w - 1;
		int hm = h - 1;
		int wh = w * h;
		int div = radius + radius + 1;
		int r[] = new int[wh];
		int g[] = new int[wh];
		int b[] = new int[wh];
		int rsum, gsum, bsum, x, y, i, p, yp, yi, yw;
		int vmin[] = new int[Math.max(w, h)];
		int divsum = (div + 1) >> 1;
		divsum *= divsum;
		int dv[] = new int[256 * divsum];
		for (i = 0; i < 256 * divsum; i++) {
			dv[i] = (i / divsum);

		}
		yw = yi = 0;
		int[][] stack = new int[div][3];
		int stackpointer;
		int stackstart;
		int[] sir;
		int rbs;
		int r1 = radius + 1;
		int routsum, goutsum, boutsum;
		int rinsum, ginsum, binsum;
		for (y = 0; y < h; y++) {
			rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
			for (i = -radius; i <= radius; i++) {
				p = pix[yi + Math.min(wm, Math.max(i, 0))];
				sir = stack[i + radius];
				sir[0] = (p & 0xff0000) >> 16;
				sir[1] = (p & 0x00ff00) >> 8;
				sir[2] = (p & 0x0000ff);
				rbs = r1 - Math.abs(i);
				rsum += sir[0] * rbs;
				gsum += sir[1] * rbs;
				bsum += sir[2] * rbs;
				if (i > 0) {
					rinsum += sir[0];
					ginsum += sir[1];
					binsum += sir[2];

				} else {
					routsum += sir[0];
					goutsum += sir[1];
					boutsum += sir[2];

				}

			}
			stackpointer = radius;
			for (x = 0; x < w; x++) {
				r[yi] = dv[rsum];
				g[yi] = dv[gsum];
				b[yi] = dv[bsum];
				rsum -= routsum;
				gsum -= goutsum;
				bsum -= boutsum;
				stackstart = stackpointer - radius + div;
				sir = stack[stackstart % div];
				routsum -= sir[0];
				goutsum -= sir[1];
				boutsum -= sir[2];
				if (y == 0) {
					vmin[x] = Math.min(x + radius + 1, wm);

				}
				p = pix[yw + vmin[x]];
				sir[0] = (p & 0xff0000) >> 16;
				sir[1] = (p & 0x00ff00) >> 8;
				sir[2] = (p & 0x0000ff);
				rinsum += sir[0];
				ginsum += sir[1];
				binsum += sir[2];
				rsum += rinsum;
				gsum += ginsum;
				bsum += binsum;
				stackpointer = (stackpointer + 1) % div;
				sir = stack[(stackpointer) % div];
				routsum += sir[0];
				goutsum += sir[1];
				boutsum += sir[2];
				rinsum -= sir[0];
				ginsum -= sir[1];
				binsum -= sir[2];
				yi++;

			}
			yw += w;

		}
		for (x = 0; x < w; x++) {
			rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
			yp = -radius * w;
			for (i = -radius; i <= radius; i++) {
				yi = Math.max(0, yp) + x;
				sir = stack[i + radius];
				sir[0] = r[yi];
				sir[1] = g[yi];
				sir[2] = b[yi];
				rbs = r1 - Math.abs(i);
				rsum += r[yi] * rbs;
				gsum += g[yi] * rbs;
				bsum += b[yi] * rbs;
				if (i > 0) {
					rinsum += sir[0];
					ginsum += sir[1];
					binsum += sir[2];

				} else {
					routsum += sir[0];
					goutsum += sir[1];
					boutsum += sir[2];

				}
				if (i < hm) {
					yp += w;
				}
			}
			yi = x;
			stackpointer = radius;
			for (y = 0; y < h; y++) {
				pix[yi] = (0xff000000 & pix[yi]) | (dv[rsum] << 16) | (dv[gsum] << 8) | dv[bsum];
				rsum -= routsum;
				gsum -= goutsum;
				bsum -= boutsum;
				stackstart = stackpointer - radius + div;
				sir = stack[stackstart % div];
				routsum -= sir[0];
				goutsum -= sir[1];
				boutsum -= sir[2];
				if (x == 0) {
					vmin[y] = Math.min(y + r1, hm) * w;

				}
				p = x + vmin[y];
				sir[0] = r[p];
				sir[1] = g[p];
				sir[2] = b[p];
				rinsum += sir[0];
				ginsum += sir[1];
				binsum += sir[2];
				rsum += rinsum;
				gsum += ginsum;
				bsum += binsum;
				stackpointer = (stackpointer + 1) % div;
				sir = stack[stackpointer];
				routsum += sir[0];
				goutsum += sir[1];
				boutsum += sir[2];
				rinsum -= sir[0];
				ginsum -= sir[1];
				binsum -= sir[2];
				yi += w;
			}
		}
		bitmap.setPixels(pix, 0, w, 0, 0, w, h);
		return (bitmap);
	}

	/**
	 * ͨ��һ������·�� ����ͼƬ
	 * 
	 * @param httpPath
	 * @param scale ѹ������ 
	 * 		0  ��  ��ѹ��
	 * 		n   �� ����ָ���ı�������ͼƬѹ��
	 */
	public static void loadBitmap(final String httpPath, final int scale, final BitmapCallback callback) {
		AsyncTask<String, String, Bitmap> task = new AsyncTask<String, String, Bitmap>() {
			protected Bitmap doInBackground(String... params) {
				try {
					Options opts = new Options();
					if(scale != 0){  //����ѹ������
						opts.inSampleSize = scale;
					}
					// ��ȥ����Ŀ¼��Ѱ��
					String filename = httpPath.substring(httpPath.lastIndexOf("/"));
					File file = new File(BaseApplication.getmApplication().getCacheDir(), "images" + filename);
					//���ļ��а���opts��Ҫ�� ��ȡbm
					Bitmap bm = loadBitmap(file, opts);
					if (bm != null) {
						return bm;
					}
					// ����ͼƬ
					InputStream is = HttpUtils.get(httpPath);
					bm = BitmapFactory.decodeStream(is);
					// ���浽����Ŀ¼
					save(bm, file);
					//���ļ��а���Ҫ�� �ٽ�������Bitmap
					bm = loadBitmap(file, opts);
					return bm;
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}

			protected void onPostExecute(Bitmap bitmap) {
				callback.onBitmapLoaded(bitmap);
			}
		};
		task.execute();
	}

	/**
	 * ���ļ��м��س�һ��ͼƬ
	 * 
	 * @param file
	 * @return
	 */
	public static Bitmap loadBitmap(File file) {
		if (!file.exists()) {
			return null;
		}
		return BitmapFactory.decodeFile(file.getAbsolutePath());
	}
	
	public static Bitmap loadBitmap(File file, Options opts) {
		if (!file.exists()) {
			return null;
		}
		return BitmapFactory.decodeFile(file.getAbsolutePath(), opts);
	}

	
	/**
	 * ����ͼƬ��file�ļ���
	 * 
	 * @param bitmap
	 * @param file
	 * @throws FileNotFoundException
	 */
	public static void save(Bitmap bitmap, File file) throws FileNotFoundException {
		// ��Ŀ¼�����ڣ��򴴽���Ŀ¼
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		FileOutputStream fos = new FileOutputStream(file);
		bitmap.compress(CompressFormat.JPEG, 100, fos);
	}

	/**
	 * ���մ��ݵĿ��ѹ��ͼƬ������
	 * 
	 * @param is
	 *            ͼƬ������
	 * @param width
	 *            Ŀ����
	 * @param height
	 *            Ŀ��߶�
	 * @return
	 * @throws IOException
	 */
	public static Bitmap loadBitmap(InputStream is, int width, int height) throws IOException {
		// ��is�е����ݶ���byte[]��
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024 * 10];
		int length = 0;
		while ((length = is.read(buffer)) != -1) {
			bos.write(buffer, 0, length);
			bos.flush();
		}
		byte[] bytes = bos.toByteArray();
		bos.close();
		// ����byte[] ��ȡԭʼ�ߴ�
		Options opts = new Options();
		// �������ر߽�����
		opts.inJustDecodeBounds = true;
		BitmapFactory.decodeByteArray(bytes, 0, bytes.length, opts);
		// ����ԭʼ�ߴ���Ŀ��ߴ� ����ѹ������
		int w = opts.outWidth / width;
		int h = opts.outHeight / height;
		int scale = w > h ? w : h;
		// �ٴν���byte[] ��ȡbitmap
		opts.inJustDecodeBounds = false;
		opts.inSampleSize = scale;
		Bitmap b = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, opts);
		return b;
	}
}
