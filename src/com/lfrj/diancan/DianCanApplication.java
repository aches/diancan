package com.lfrj.diancan;

import com.lfrj.diancan.http.Command;
import com.lfrj.diancan.http.ContextPool;
import com.lfrj.diancan.http.ObserverImp;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class DianCanApplication extends Application implements Command {
	public final static String TAG = "DianCanApplication";
	public final static int App_requestCode = 1;
	public static int width;
	public static int height;
	public static String CACHE_DIR = "";
	private static Context context;
	public static String TOKEN = "";

	@Override
	public void onCreate() {
		super.onCreate();
		ObserverImp.getInstance().register(TAG, this);
		context = this;
		ContextPool.initialize(this);
		initUser();
		getScreenWidth(this);
		
//		ObserverImp.getInstance().register(TAG, this);

		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
//				.showImageForEmptyUri(R.drawable.moviedetail_img_02)
				.bitmapConfig(Bitmap.Config.RGB_565).considerExifParams(true)
//				.showImageOnFail(R.drawable.moviedetail_img_02)
				.cacheInMemory(true).cacheOnDisk(true)
				// .displayer(new SimpleBitmapDisplayer()) // default
				// 可以设置动画，比如圆角或者渐变
				.build();

		@SuppressWarnings("deprecation")
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				getApplicationContext())
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.defaultDisplayImageOptions(defaultOptions)
				.memoryCache(new WeakMemoryCache())
				.discCacheSize(50 * 1024 * 1024).discCacheFileCount(3000)
				// .writeDebugLogs()
				.build();

		CACHE_DIR = getApplicationContext().getFilesDir().getPath();

		ImageLoader.getInstance().init(config);
	}
	

	@Override
	public void onTerminate() {
		super.onTerminate();
	}

	private void initUser() {
		
	}

	public static Context getContext() {
		return context;
	}

	/**
	 * 获取屏幕宽和高
	 * 
	 * @param context
	 */
	public void getScreenWidth(Context context) {
		WindowManager manager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = manager.getDefaultDisplay();
		DisplayMetrics outMetrics = new DisplayMetrics();
		display.getMetrics(outMetrics);
		width = outMetrics.widthPixels;
		height = outMetrics.heightPixels;
	}

	@Override
	public <T> void onFailure(int requestCode, int resultCode, T parameter) {

	}

	@Override
	public <T> void onSuccess(int requestCode, int resultCode, T parameter) {
		
	}
}
