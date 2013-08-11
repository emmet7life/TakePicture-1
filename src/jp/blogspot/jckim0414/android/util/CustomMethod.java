package jp.blogspot.jckim0414.android.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CustomMethod {
	// ******************************
	// Variable
	private static CustomMethod cm = null;// this class
	private Context context;// view's context
	private int stndWidth;// app's standard width
	private int stndHeight;// app's standard height
	private int appX;// app's start position x
	private int appY;// app's start position y
	private int appWidth;// app's width
	private int appHeight;// app's height
	private float appDensity;// app's density

	// ******************************
	// Constructor_Singleton
	public static CustomMethod getInstance(Context context) {
		if (cm == null) {
			cm = new CustomMethod(context);
			cm.appWidth = cm.getDeviceWidth();
			cm.appHeight = cm.getDeviceHeight();
			if (cm.getOrientation() == Configuration.ORIENTATION_LANDSCAPE) {
				cm.trace("orientation : LANDSCAPE");
				if (cm.appWidth * 9 != cm.appHeight * 16) {
					if (cm.appWidth * 9 > cm.appHeight * 16) {
						cm.appWidth = cm.appHeight * 16 / 9;
					} else {
						cm.appHeight = cm.appWidth * 9 / 16;
					}
				}
				cm.stndWidth = 1280;
				cm.stndHeight = 720;
			} else {
				cm.trace("orientation : PORTRAIT");
				if (cm.appWidth * 16 != cm.appHeight * 9) {
					if (cm.appWidth * 16 > cm.appHeight * 9) {
						cm.appWidth = cm.appHeight * 9 / 16;
					} else {
						cm.appHeight = cm.appWidth * 16 / 9;
					}
				}
				cm.stndWidth = 720;
				cm.stndHeight = 1280;
			}

			cm.appDensity = (float) cm.appWidth / (float) cm.stndWidth;
			cm.appDensity = (int) ((cm.appDensity * 100) / 25);
			cm.appDensity = cm.appDensity * 25 / 100;
			cm.appWidth = (int) (cm.stndWidth * cm.appDensity);
			cm.appHeight = (int) (cm.stndHeight * cm.appDensity);
			cm.appX = cm.getDeviceWidth() - cm.appWidth;
			cm.appY = cm.getDeviceHeight() - cm.appHeight;

			cm.trace("deviceResolution : " + cm.getDeviceWidth() + " * "
					+ cm.getDeviceHeight());
			cm.trace("screenResolution : " + cm.appWidth + " * " + cm.appHeight);
			cm.trace("screenDensity : " + cm.appDensity);
		}
		return cm;
	}

	// ******************************
	// STANDARD_Method
	private CustomMethod(Context context) {
		this.context = context;
	}

	public Context getContext() {
		return context;
	}

	public Resources getResources() {
		return context.getResources();
	}

	public AssetManager getAssetManager() {
		return context.getAssets();
	}

	public Configuration getConfiguration() {
		return getResources().getConfiguration();
	}

	public int getOrientation() {
		return getConfiguration().orientation;
	}

	public int getColor(int id) {
		return getResources().getColor(id);
	}

	public LayoutInflater getLayoutInflater() {
		return ((Activity) context).getLayoutInflater();
	}

	public Drawable getDrawableFromPath(String path) {
		InputStream is = null;
		try {
			is = getAssetManager().open(path);
		} catch (Exception e) {
			trace("image load fail");
		}
		return Drawable.createFromStream(is, null);
	}

	public AssetFileDescriptor getSoundFromPath(String path) {
		AssetFileDescriptor afd = null;
		System.out.println();
		try {
			afd = getAssetManager().openFd(path);
		} catch (IOException e) {
			trace("sound load fail");
		}
		return afd;
	}

	public Drawable getDrawableFromResource(int id) {
		return getResources().getDrawable(id);
	}

	public View inflate(int resource, ViewGroup root) {
		return getLayoutInflater().inflate(resource, root);
	}

	public void setImageDrawable(View view, String path) {
		((ImageView) view).setImageDrawable(getDrawableFromPath(path));
	}

	public int getDeviceWidth() {
		return (int) (getResources().getDisplayMetrics().widthPixels);
	}

	public int getDeviceHeight() {
		return (int) (getResources().getDisplayMetrics().heightPixels);
	}

	public float getDensity() {
		return getResources().getDisplayMetrics().density;
	}

	public int getAppX() {
		return appX;
	}

	public int getAppY() {
		return appY;
	}

	public int getAppWidth() {
		return appWidth;
	}

	public int getAppHeight() {
		return appHeight;
	}

	public float getAppDensity() {
		return appDensity;
	}

	public int getPx(int realPx) {
		return (int) (realPx * getAppDensity());
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public void accelerate(View view) {
		view.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
	}

	private Typeface font;

	public void setFont(String fontPath) {
		try {
			font = Typeface.createFromAsset(context.getAssets(), fontPath);
		} catch (Exception e) {
			font = null;
		}
	}

	public void setText(View view, String text) {
		if (font != null) {
			((TextView) view).setTypeface(font);
		}
		((TextView) view).setText(text);
	}

	// ******************************
	// STANDARD_DATE
	private Date stndDate = new Date();

	public void setStndDate() {
		setStndDate(System.currentTimeMillis());
	}

	public void setStndDate(Date date) {
		stndDate = date;
	}

	public void setStndDate(Long milliseconds) {
		stndDate = new Date(milliseconds);
	}

	public Date getStndDate() {
		return stndDate;
	}

	public String getTwoDigitFromInt(int number) {
		String rslt = number + "";
		if (rslt.length() == 1) {
			rslt = "0" + rslt;
		}
		return rslt;
	}

	public String getTwoDigitFromInt(int number, boolean withSpace) {
		if (!withSpace) {
			return getTwoDigitFromInt(number);
		}
		String rslt = number + "";
		if (rslt.length() == 1) {
			rslt = " " + rslt;
		}
		return rslt;
	}

	@SuppressWarnings("deprecation")
	public Date getDateFromString(String string) {
		return new Date(Integer.parseInt(string.substring(0, 4)) - 1900,
				Integer.parseInt(string.substring(5, 7)) - 1,
				Integer.parseInt(string.substring(8, 10)),
				Integer.parseInt(string.substring(11, 13)),
				Integer.parseInt(string.substring(14, 16)),
				Integer.parseInt(string.substring(17, 19)));
	}

	public String getStringFromDate(Long timeMillis) {
		return getStringFromDate(new Date(timeMillis));
	}

	@SuppressWarnings("deprecation")
	public String getStringFromDate(Date date) {
		return date.getYear() + 1900 + "-"
				+ getTwoDigitFromInt(date.getMonth() + 1) + "-"
				+ getTwoDigitFromInt(date.getDate()) + " "
				+ getTwoDigitFromInt(date.getHours()) + ":"
				+ getTwoDigitFromInt(date.getMinutes()) + ":"
				+ getTwoDigitFromInt(date.getSeconds());
	}

	// ******************************
	// ALERT
	public void alert(String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}

	// ******************************
	// TRACE
	public void trace(String str) {
		Log.i("JcKim0414", str);
	}

	public void trace(int num) {
		Log.i("JcKim0414", Integer.toString(num));
	}
}