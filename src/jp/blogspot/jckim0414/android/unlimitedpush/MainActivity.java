package jp.blogspot.jckim0414.android.unlimitedpush;

import jp.blogspot.jckim0414.android.component.CustomActivity;
import jp.blogspot.jckim0414.android.component.CustomImage;
import jp.blogspot.jckim0414.android.component.CustomLayout;
import jp.blogspot.jckim0414.android.util.CustomMethod;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends CustomActivity {

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

	private void init() {
		cm = CustomMethod.getInstance(this);
		sc = new CustomLayout(this);
		bg = new CustomImage(this);
		sc.addView(bg);
		setContentView(sc);

		sc.setSize(cm.getAppWidth(), cm.getAppHeight());
		sc.setPosition(0, 0);

		bg.setImage(R.drawable.main_bg);
		bg.setSize(cm.getAppWidth(), cm.getAppHeight());
		bg.setPosition(0, 0);
		s01();
	}

	private void s01() {
		final Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				// TODO MODI
				Intent intent = new Intent(cm.getContext(), S02.class);
				intent.putExtra("SCREEN", "01");
				startActivityForResult(intent, 0);
			}
		}, 2000);
	}
}
