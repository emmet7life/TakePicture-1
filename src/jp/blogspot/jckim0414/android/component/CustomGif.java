package jp.blogspot.jckim0414.android.component;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;

public class CustomGif extends ImageView {
	public CustomGif(Context context) {
		super(context);
		setScaleType(ScaleType.FIT_XY);
		setPadding(0, 0, 0, 0);
	}

	public void setPosition(int x, int y) {
		((LayoutParams) getLayoutParams()).gravity=Gravity.TOP;
		((LayoutParams) getLayoutParams()).setMargins(x, y, 0, 0);
	}

	public void setSize(int width, int height) {
		getLayoutParams().width = width;
		getLayoutParams().height = height;
	}

	private int imageIdx = 0;
	private Drawable[] images;

	public void setImages(int[] imageIds) {
		images = new Drawable[imageIds.length];
		for (int i = 0; i < imageIds.length; i++) {
			images[i] = getResources().getDrawable(imageIds[i]);
		}
		animGif();
	}
	
	public void animGif() {
		if (imageIdx >= images.length) {
			imageIdx = 0;
		}
		setImageDrawable(images[imageIdx]);
		imageIdx++;
	}
}
