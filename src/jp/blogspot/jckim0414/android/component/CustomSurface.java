package jp.blogspot.jckim0414.android.component;

import jp.blogspot.jckim0414.android.unlimitedpush.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.FrameLayout.LayoutParams;

public class CustomSurface extends SurfaceView implements
		SurfaceHolder.Callback, Runnable {
	private SurfaceHolder holder = null;
	private Thread thread = null;
	public boolean isAttached = false;
	private Paint paint = null;

	public CustomSurface(Context context) {
		super(context);
		getHolder().addCallback(this);
		getHolder().setFormat(PixelFormat.TRANSLUCENT);
		setFocusable(true);
		setZOrderOnTop(true);
		paint = new Paint();
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		this.holder = holder;
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		isAttached = false;
		thread = null;
	}

	@Override
	public void run() {
		while (isAttached) {
			doDraw();
		}
	}

	public void setPosition(int x, int y) {
		((LayoutParams) getLayoutParams()).setMargins(x, y, 0, 0);
	}

	public void setSize(int width, int height) {
		getLayoutParams().width = width;
		getLayoutParams().height = height;
	}

	Matrix matrix;
	Bitmap bitmap;
	int startX;

	public void setImage() {
		matrix = new Matrix();
		bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.s02_circle1);
		bitmap = Bitmap.createScaledBitmap(bitmap, 300, 300, false);
		startX = -300;
	}

	public void start() {
		isAttached = true;
		thread = new Thread(this);
		if (!thread.isAlive()) {
			thread.start();
		}
	}

	public void stop() {
		if (isAttached) {
			isAttached = false;
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void doDraw() {
		Canvas canvas = holder.lockCanvas();
		if (canvas == null) {
			return;
		}
		startX++;
		canvas.drawColor(0, Mode.CLEAR);
		canvas.drawBitmap(bitmap, startX, 300, paint);
		holder.unlockCanvasAndPost(canvas);
	}
}