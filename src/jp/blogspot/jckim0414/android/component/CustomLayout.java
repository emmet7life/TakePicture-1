package jp.blogspot.jckim0414.android.component;

import jp.blogspot.jckim0414.android.util.CustomMethod;
import android.content.Context;
import android.view.Gravity;
import android.widget.FrameLayout;

public class CustomLayout extends FrameLayout {
	private CustomMethod cm = null;

	public CustomLayout(Context context) {
		super(context);
		cm = CustomMethod.getInstance(context);

	}

	public void setPosition(int x, int y) {
		((LayoutParams) getLayoutParams()).gravity = Gravity.TOP;
		((LayoutParams) getLayoutParams()).setMargins(
				cm.getAppX() + cm.getPx(x), cm.getAppY() + cm.getPx(y), 0, 0);
	}

	public void setSize(int width, int height) {
		getLayoutParams().width = cm.getPx(width);
		getLayoutParams().height = cm.getPx(height);
	}
}
