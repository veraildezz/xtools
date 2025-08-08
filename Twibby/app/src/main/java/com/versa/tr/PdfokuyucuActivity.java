package com.versa.tr;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.*;
import android.graphics.*;
import android.graphics.Typeface;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.firebase.FirebaseApp;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;

public class PdfokuyucuActivity extends AppCompatActivity {
	
	public final int REQ_CD_FİLE_PİCKER = 101;
	
	private double page = 0;
	private double pageCount = 0;
	private String pdfFile = "";
	
	private LinearLayout linear2;
	private LinearLayout linear3;
	private Button button1;
	private EditText edittext1;
	private TextView textview4;
	private TextView textview3;
	private TextView textview1;
	private TextView textview2;
	
	private Intent file_picker = new Intent(Intent.ACTION_GET_CONTENT);
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.pdfokuyucu);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 1000);
		} else {
			initializeLogic();
		}
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear2 = findViewById(R.id.linear2);
		linear3 = findViewById(R.id.linear3);
		button1 = findViewById(R.id.button1);
		edittext1 = findViewById(R.id.edittext1);
		textview4 = findViewById(R.id.textview4);
		textview3 = findViewById(R.id.textview3);
		textview1 = findViewById(R.id.textview1);
		textview2 = findViewById(R.id.textview2);
		file_picker.setType("application/pdf");
		file_picker.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (renderer != null){
					renderer.close(); }
				startActivityForResult(file_picker, REQ_CD_FİLE_PİCKER);
			}
		});
		
		textview3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!edittext1.getText().toString().equals("")) {
					if ((Double.parseDouble(edittext1.getText().toString()) > 0) && (Double.parseDouble(edittext1.getText().toString()) < (1 + pageCount))) {
						page = Double.parseDouble(edittext1.getText().toString()) - 1;
						_displaypage(page);
					}
				}
			}
		});
		
		textview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (page > 0) {
					page--;
					_displaypage(page);
				}
			}
		});
		
		textview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (page < (pageCount - 1)) {
					page++;
					_displaypage(page);
				}
			}
		});
	}
	
	private void initializeLogic() {
		touch = new ZoomableImageView(this);
		linear3.addView(touch);
		_RoundAndBorder(button1, "#696BE6", 0, "#ffffff", 25);
		_RoundAndBorder(linear2, "#ffffff", 3, "#EDB754", 20);
		_RoundAndBorder(linear3, "#ffffff", 3, "#EDB754", 35);
		textview4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		button1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		edittext1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			case REQ_CD_FİLE_PİCKER:
			if (_resultCode == Activity.RESULT_OK) {
				ArrayList<String> _filePath = new ArrayList<>();
				if (_data != null) {
					if (_data.getClipData() != null) {
						for (int _index = 0; _index < _data.getClipData().getItemCount(); _index++) {
							ClipData.Item _item = _data.getClipData().getItemAt(_index);
							_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _item.getUri()));
						}
					}
					else {
						_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _data.getData()));
					}
				}
				pdfFile = _filePath.get((int)(0));
				page = 0;
				try {
					renderer = new android.graphics.pdf.PdfRenderer(new ParcelFileDescriptor(ParcelFileDescriptor.open(new java.io.File(pdfFile), ParcelFileDescriptor.MODE_READ_ONLY)));
					 pageCount = renderer.getPageCount();
					_displaypage(page);
					textview4.setText("/".concat(String.valueOf((long)(pageCount))));
				} catch (Exception e){ }
			}
			else {
				
			}
			break;
			default:
			break;
		}
	}
	
	public void _extra() {
	} private android.graphics.pdf.PdfRenderer renderer;
	ZoomableImageView touch;
	public class ZoomableImageView extends ImageView {
		
		Matrix matrix = new Matrix();
		static final int NONE = 0;
		static final int DRAG = 1;
		static final int ZOOM = 2;
		static final int CLICK = 3;
		int mode = NONE;
		PointF last = new PointF();
		PointF start = new PointF();
		float minScale = 1f;
		float maxScale = 4f;
		float[] m;
		float redundantXSpace, redundantYSpace;
		float width, height;
		float saveScale = 1f;
		float right, bottom, origWidth, origHeight, bmWidth, bmHeight;
		ScaleGestureDetector mScaleDetector;
		Context context;
		
		
		public ZoomableImageView(Context context) {
			super(context);
			super.setClickable(true);
			this.context = context;
			mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
			matrix.setTranslate(1f, 1f);
			m = new float[9];
			setImageMatrix(matrix); setScaleType(ScaleType.MATRIX);
			
			
			setOnTouchListener(new OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					mScaleDetector.onTouchEvent(event);
					matrix.getValues(m);
					float x = m[Matrix.MTRANS_X];
					float y = m[Matrix.MTRANS_Y];
					PointF curr = new PointF(event.getX(), event.getY());
					switch (event.getAction()) {
						case MotionEvent.ACTION_DOWN: last.set(event.getX(), event.getY()); start.set(last); mode = DRAG;
						break;
						case MotionEvent.ACTION_POINTER_DOWN: last.set(event.getX(), event.getY()); start.set(last);
						mode = ZOOM;
						break;
						case MotionEvent.ACTION_MOVE:
						if (mode == ZOOM || (mode == DRAG && saveScale > minScale)) {
							float deltaX = curr.x - last.x;
							float deltaY = curr.y - last.y;
							float scaleWidth = Math.round(origWidth * saveScale);
							float scaleHeight = Math.round(origHeight * saveScale);
							if (scaleWidth < width) {
								deltaX = 0;
								if (y + deltaY > 0) deltaY = -y;
								else if (y + deltaY < -bottom) deltaY = -(y + bottom);
							} else if (scaleHeight < height) {
								deltaY = 0;
								if (x + deltaX > 0) deltaX = -x;
								else if (x + deltaX < -right) deltaX = -(x + right);
							} else {
								if (x + deltaX > 0) deltaX = -x;
								else if (x + deltaX < -right) deltaX = -(x + right);
								if (y + deltaY > 0) deltaY = -y;
								else if (y + deltaY < -bottom) deltaY = -(y + bottom);
							}
							matrix.postTranslate(deltaX, deltaY);
							last.set(curr.x, curr.y);
						}
						break;
						case MotionEvent.ACTION_UP:
						mode = NONE;
						int xDiff = (int) Math.abs(curr.x - start.x);
						int yDiff = (int) Math.abs(curr.y - start.y);
						if (xDiff < CLICK && yDiff < CLICK) performClick();
						break;
						case MotionEvent.ACTION_POINTER_UP:
						mode = NONE;
						break;
					}
					setImageMatrix(matrix);
					invalidate();
					return true;
				}
			});
		}
		
		
		@Override
		public void setImageBitmap(Bitmap bm) {
			super.setImageBitmap(bm);
			bmWidth = bm.getWidth();
			bmHeight = bm.getHeight(); }
		
		public void setMaxZoom(float x) {
			maxScale = x; }
		
		
		private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
			@Override
			public boolean onScaleBegin(ScaleGestureDetector detector) {
				mode = ZOOM;
				return true;}
			@Override
			public boolean onScale(ScaleGestureDetector detector) {
				float mScaleFactor = detector.getScaleFactor();
				float origScale = saveScale;
				saveScale *= mScaleFactor;
				if (saveScale > maxScale){
					saveScale = maxScale;
					mScaleFactor = maxScale / origScale;
				} else if (saveScale < minScale) {
					saveScale = minScale;
					mScaleFactor = minScale / origScale;}
				right = width * saveScale - width - (2 * redundantXSpace * saveScale);
				bottom = height * saveScale - height - (2 * redundantYSpace * saveScale);
				if (origWidth * saveScale <= width || origHeight * saveScale <= height) {
					matrix.postScale(mScaleFactor, mScaleFactor, width / 2, height / 2);
					if (mScaleFactor < 1) {
						matrix.getValues(m);
						float x = m[Matrix.MTRANS_X];
						float y = m[Matrix.MTRANS_Y];
						if (mScaleFactor < 1) {
							if (Math.round(origWidth * saveScale) < width) {
								if (y < -bottom) matrix.postTranslate(0, -(y + bottom));
								else if (y > 0) matrix.postTranslate(0, -y);
							} else {
								if (x < -right) matrix.postTranslate(-(x + right), 0);
								else if (x > 0) matrix.postTranslate(-x, 0);}
						}
					}
				} else {
					matrix.postScale(mScaleFactor, mScaleFactor, detector.getFocusX(), detector.getFocusY()); matrix.getValues(m);
					float x = m[Matrix.MTRANS_X];
					float y = m[Matrix.MTRANS_Y];
					if (mScaleFactor < 1) {
						if (x < -right) matrix.postTranslate(-(x + right), 0);
						else if (x > 0) matrix.postTranslate(-x, 0);
						if (y < -bottom) matrix.postTranslate(0, -(y + bottom));
						else if (y > 0) matrix.postTranslate(0, -y);}
				}
				return true;
			}
		}
		
		
		@Override
		protected void onMeasure (int widthMeasureSpec, int heightMeasureSpec) {
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
			width = MeasureSpec.getSize(widthMeasureSpec);
			height = MeasureSpec.getSize(heightMeasureSpec);
			float scale;
			float scaleX = width / bmWidth;
			float scaleY = height / bmHeight;
			scale = Math.min(scaleX, scaleY); matrix.setScale(scale, scale); setImageMatrix(matrix);
			saveScale = 1f;
			redundantYSpace = height - (scale * bmHeight) ;
			redundantXSpace = width - (scale * bmWidth);
			redundantYSpace /= 2;
			redundantXSpace /= 2; matrix.postTranslate(redundantXSpace, redundantYSpace);
			origWidth = width - 2 * redundantXSpace;
			origHeight = height - 2 * redundantYSpace;
			right = width * saveScale - width - (2 * redundantXSpace * saveScale);
			bottom = height * saveScale - height - (2 * redundantYSpace * saveScale); setImageMatrix(matrix);}
	}
	
	{
	}
	
	
	public void _displaypage(final double _i) {
		android.graphics.pdf.PdfRenderer.Page page = renderer.openPage((int)_i);
		Bitmap mBitmap = Bitmap.createBitmap((int)getDip(page.getWidth()), (int)getDip(page.getHeight()), Bitmap.Config.ARGB_8888);
		page.render(mBitmap, null, null, android.graphics.pdf.PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
		touch.setImageBitmap(mBitmap);
		page.close();
		edittext1.setText(String.valueOf((long)(_i + 1)));
	}
	
	
	public void _RoundAndBorder(final View _view, final String _color1, final double _border, final String _color2, final double _round) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		gd.setColor(Color.parseColor(_color1));
		gd.setCornerRadius((int) _round);
		gd.setStroke((int) _border, Color.parseColor(_color2));
		_view.setBackground(gd);
	}
	
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}