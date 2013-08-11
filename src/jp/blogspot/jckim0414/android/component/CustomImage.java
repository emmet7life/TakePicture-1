package jp.blogspot.jckim0414.android.component;

import jp.blogspot.jckim0414.android.util.CustomMethod;
import android.content.Context;
import android.view.Gravity;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;

public class CustomImage extends ImageView {
	private CustomMethod cm = null;

	public CustomImage(Context context) {
		super(context);
		setScaleType(ScaleType.FIT_XY);
		setPadding(0, 0, 0, 0);
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

	public void setImage(int id) {
		setImageDrawable(cm.getDrawableFromResource(id));
	}
}
