package jp.blogspot.jckim0414.android.unlimitedpush;

import jp.blogspot.jckim0414.android.component.CustomActivity;
import jp.blogspot.jckim0414.android.component.CustomImage;
import jp.blogspot.jckim0414.android.component.CustomLayout;
import jp.blogspot.jckim0414.android.component.CustomSurface;
import jp.blogspot.jckim0414.android.util.CustomMethod;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class S02 extends CustomActivity {

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
	private CustomSurface target = null;
	private CustomImage camera = null;

	private void init() {
		cm = CustomMethod.getInstance(this);
		sc = new CustomLayout(this);
		bg = new CustomImage(this);
		target = new CustomSurface(this);
		camera = new CustomImage(this);
		sc.addView(bg);
		sc.addView(target);
		sc.addView(camera);
		setContentView(sc);

		sc.setSize(cm.getAppWidth(), cm.getAppHeight());
		sc.setPosition(0, 0);

		bg.setImage(R.drawable.s02_bg);
		bg.setSize(cm.getAppWidth(), cm.getAppHeight());
		bg.setPosition(0, 0);

		target.setImage();
		target.setSize(cm.getAppWidth(), cm.getAppHeight());
		target.setPosition(0, 0);

		camera.setImage(R.drawable.s01_camera);
		camera.setSize(cm.getAppWidth(), cm.getAppHeight());
		camera.setPosition(0, 0);

		camera.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				if (!target.isAttached) {
					target.start();
				} else {
					target.stop();
				}

				cm.alert("touch");
				return false;
			}
		});
	}
}