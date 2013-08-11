package jp.blogspot.jckim0414.android.component;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;

public class CustomButton extends ImageView {
	public CustomButton(Context context) {
		super(context);
		setScaleType(ScaleType.FIT_XY);
	}

	public void setPosition(int x, int y) {
		((LayoutParams) getLayoutParams()).gravity=Gravity.TOP;
		((LayoutParams) getLayoutParams()).setMargins(x, y, 0, 0);
	}

	public void setSize(int width, int height) {
		getLayoutParams().width = width;
		getLayoutParams().height = height;
	}

	private Drawable offImage;
	private Drawable onImage;
	
	public void setImage(int offId) {
		offImage = getResources().getDrawable(offId);
		onImage = getResources().getDrawable(offId);
		setOff();
	}

	public void setImage(int offId, int onId) {
		offImage = getResources().getDrawable(offId);
		onImage = getResources().getDrawable(onId);
		setOff();
	}

	public void setOff() {
		setImageDrawable(offImage);
	}

	public void setOn() {
		setImageDrawable(onImage);
	}
}
