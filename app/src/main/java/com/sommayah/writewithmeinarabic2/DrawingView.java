package com.sommayah.writewithmeinarabic2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


@SuppressLint("DrawAllocation")
public class DrawingView extends View {
	
	//drawing path
	private Path drawPath;
	//drawing and canvas paint
	private Paint drawPaint, canvasPaint;
	//initial color
	private int paintColor = 0xFF0000FF;
	//canvas
	private Canvas drawCanvas;
	//canvas bitmap
	private Bitmap canvasBitmap;
	private float brushSize, lastBrushSize;
	private boolean erase=false;
	private boolean anyText = false; //to write the wanted text to trace
	private String anyTextString = "";

	public DrawingView(Context context, AttributeSet attrs) {
		super(context, attrs);
	    setupDrawing();
	}

	private void setupDrawing() {
		brushSize = getResources().getInteger(R.integer.small_size); //small size brush all the time
		lastBrushSize = brushSize;
		drawPath = new Path();
		drawPaint = new Paint();
		drawPaint.setColor(paintColor);
		drawPaint.setAntiAlias(true);
		drawPaint.setStrokeWidth(brushSize);
		drawPaint.setStyle(Paint.Style.STROKE);
		drawPaint.setStrokeJoin(Paint.Join.ROUND);
		drawPaint.setStrokeCap(Paint.Cap.ROUND);
		canvasPaint = new Paint(Paint.DITHER_FLAG);
		
	}
	
	public void setColor(String newColor){
		invalidate();
		paintColor = Color.parseColor(newColor);
		drawPaint.setColor(paintColor);
	}
	
	public void setBrushSize(float newSize){
	//	float pixelAmount = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
	//			newSize, getResources().getDisplayMetrics());
	//	brushSize=pixelAmount;
		brushSize = getResources().getInteger(R.integer.small_size);
		drawPaint.setStrokeWidth(brushSize);
	}
	
	public void setLastBrushSize(float lastSize){
	    lastBrushSize=lastSize;
	}
	public float getLastBrushSize(){
	    return lastBrushSize;
	}
	
	public void writeText(String _anyTextString){
		anyTextString = _anyTextString;
	}
	public void setanyText(boolean check){
		anyText = check;
	}
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		drawCanvas = new Canvas(canvasBitmap);
	
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		if(anyText == true){
			Paint paint = new Paint();
	        paint.setTextSize(172);
	        paint.setColor(0xCCCCCCCC);
	        paint.setStyle(Paint.Style.FILL);
	        int h = canvas.getHeight()/3;
			int w = canvas.getWidth()/10;
			canvas.drawText(anyTextString, w/*50*/, h/*200*/, paint);
			Log.v("onDraw method height", String.valueOf(h));
			Log.v("onDraw method width", String.valueOf(w));
			
		}
		canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
		canvas.drawPath(drawPath, drawPaint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float touchX = event.getX();
		float touchY = event.getY();
		if(erase){
			drawPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
		}
		else drawPaint.setXfermode(null);
		
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
		    drawPath.moveTo(touchX, touchY);
		    break;
		case MotionEvent.ACTION_MOVE:
			drawPath.lineTo(touchX, touchY);
			if(erase){
				drawPath.lineTo(touchX, touchY);
				drawCanvas.drawPath(drawPath, drawPaint);
				drawPath.reset();
				drawPath.moveTo(touchX, touchY);
			} 
		    break;
		case MotionEvent.ACTION_UP:
		    drawCanvas.drawPath(drawPath, drawPaint);
		    drawPath.reset();
		    break;
		default:
		    return false;
		}
		invalidate();
		return true;
	}
	
	public void setErase(boolean isErase){
		erase=isErase;
	}
	
	public boolean getErase(){
		return erase;
	}

	public void startNew(){
	    drawCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
	    invalidate();
	}

}
