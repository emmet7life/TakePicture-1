package jp.blogspot.jckim0414.android.unlimitedpush;

import jp.blogspot.jckim0414.android.component.CustomActivity;
import jp.blogspot.jckim0414.android.component.CustomImage;
import jp.blogspot.jckim0414.android.component.CustomLayout;
import jp.blogspot.jckim0414.android.util.CustomMethod;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class S01 extends CustomActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 0) {
			if (resultCode == RESULT_OK) {
			}
		}
	}

	private CustomMethod cm = null;
	private CustomLayout sc = null;
	private CustomImage bg = null;
	private CustomImage camera = null;
	private CustomImage btnStart = null;
	private CustomImage btnRecord = null;

	private void init() {
		cm = CustomMethod.getInstance(this);
		sc = new CustomLayout(this);
		bg = new CustomImage(this);
		camera = new CustomImage(this);
		btnStart = new CustomImage(this);
		btnRecord = new CustomImage(this);
		sc.addView(bg);
		sc.addView(camera);
		sc.addView(btnStart);
		sc.addView(btnRecord);
		setContentView(sc);

		sc.setSize(cm.getAppWidth(), cm.getAppHeight());
		sc.setPosition(0, 0);

		bg.setImage(R.drawable.s01_bg);
		bg.setSize(cm.getAppWidth(), cm.getAppHeight());
		bg.setPosition(0, 0);

		camera.setImage(R.drawable.s01_camera);
		camera.setSize(cm.getAppWidth(), cm.getAppHeight());
		camera.setPosition(-240, 0);

		btnStart.setImage(R.drawable.s01_btn_start);
		btnStart.setSize(440, 128);
		btnStart.setPosition(748, 316);

		btnRecord.setImage(R.drawable.s01_btn_record);
		btnRecord.setSize(440, 128);
		btnRecord.setPosition(748, 460);

		btnStart.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				s02();
				return false;
			}
		});
	}

	private void s02() {
		final Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				Intent intent = new Intent(cm.getContext(), S02.class);
				intent.putExtra("SCREEN", "01");
				startActivityForResult(intent, 0);
			}
		}, 2000);
	}
}
