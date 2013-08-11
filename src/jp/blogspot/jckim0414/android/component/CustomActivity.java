package jp.blogspot.jckim0414.android.component;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

public class CustomActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.i("JcKim0414", "onStart");
	}

	@Override
	public void onRestart() {
		super.onRestart();
		Log.i("JcKim0414", "onRestart");
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.i("JcKim0414", "onResume");
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.i("JcKim0414", "onPause");
	}

	@Override
	public void onStop() {
		super.onStop();
		Log.i("JcKim0414", "onStop");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i("JcKim0414", "onDestroy");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		Log.i("JcKim0414", "onCreateOptionsMenu");
		return true;
	}
}
