package com.lfrj.diancan.view;

import com.lfrj.diancan.R;
import com.lfrj.diancan.http.Command;
import com.lfrj.diancan.http.ObserverImp;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public abstract class BaseToolbarActivity extends ActionBarActivity implements Command{

	
	protected int TOKEN_LOGIN = 45;
	protected String TAG = getClass().getSimpleName();
	
	protected TextView center_title;

	protected Toast mToast;
	protected TextView message_textview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ObserverImp.getInstance().register(TAG, this);
	}

	
	protected abstract void initToolSuperBar();

	protected void setCenterTitel(String title) {
		((TextView) findViewById(R.id.center_title)).setText(title);
	}

	protected void setCenterTitel(int title) {
		((TextView) findViewById(R.id.center_title)).setText(title);
	}

	/**
	 * @param resid
	 *            R.color.toolbar_color
	 */
	protected void initToolBar(int resid) {
		setTitleBarResColor(resid);
	}

	@Override
	public void startActivity(Intent intent) {
		super.startActivity(intent);
		overridePendingTransition(R.anim.activity_slide_in_right,R.anim.activity_slide_no);
	}
	
	@Override
	public void startActivityForResult(Intent intent, int requestCode) {
		// TODO Auto-generated method stub
		super.startActivityForResult(intent, requestCode);
		overridePendingTransition(R.anim.activity_slide_in_right,R.anim.activity_slide_no);
	}
	/**
	 * @param color
	 *            颜色值
	 */
	protected void initToolBarColor(int color) {
		setTitleBarColor(color);
	}

	@TargetApi(21)
	private void setTitleBarResColor(int resid) {
		if (Build.VERSION.SDK_INT >= 21) {
			getWindow().setStatusBarColor(getResources().getColor(resid));
		} else {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
				setTranslucentStatus(true);
				SystemBarTintManager tintManager = new SystemBarTintManager(
						this);
				tintManager.setStatusBarTintEnabled(true);
				tintManager.setStatusBarTintResource(resid);
			}

		}
	}

	@Override
	public void finish() {
		super.finish();
		ObserverImp.getInstance().removeObserver(TAG);
	}

	@TargetApi(21)
	protected void setTitleBarColor(int color) {
		if (Build.VERSION.SDK_INT >= 21) {
			getWindow().setStatusBarColor(color);
		} else {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
				setTranslucentStatus(true);
				SystemBarTintManager tintManager = new SystemBarTintManager(
						this);
				tintManager.setStatusBarTintEnabled(true);
				tintManager.setStatusBarTintColor(color);
			}

		}
	}

	@TargetApi(19)
	protected void setTranslucentStatus(boolean on) {
		Window win = getWindow();
		WindowManager.LayoutParams winParams = win.getAttributes();
		final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
		if (on) {
			winParams.flags |= bits;
		} else {
			winParams.flags &= ~bits;
		}
		win.setAttributes(winParams);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
}
