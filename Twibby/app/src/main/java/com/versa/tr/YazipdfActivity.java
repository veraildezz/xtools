package com.versa.tr;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.content.*;
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
import android.widget.ScrollView;
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

public class YazipdfActivity extends AppCompatActivity {
	
	private String path = "";
	
	private LinearLayout linear1;
	private TextView textview1;
	private LinearLayout linear2;
	private TextView textview2;
	private LinearLayout linear3;
	private Button button1;
	private TextView textview3;
	private ScrollView vscroll2;
	private EditText edittext1;
	private LinearLayout linear5;
	private EditText edittext3;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.yazipdf);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
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
		linear1 = findViewById(R.id.linear1);
		textview1 = findViewById(R.id.textview1);
		linear2 = findViewById(R.id.linear2);
		textview2 = findViewById(R.id.textview2);
		linear3 = findViewById(R.id.linear3);
		button1 = findViewById(R.id.button1);
		textview3 = findViewById(R.id.textview3);
		vscroll2 = findViewById(R.id.vscroll2);
		edittext1 = findViewById(R.id.edittext1);
		linear5 = findViewById(R.id.linear5);
		edittext3 = findViewById(R.id.edittext3);
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				path = FileUtil.getExternalStorageDir().concat("/versapdfdoc/".concat(edittext1.getText().toString().concat(".pdf")));
				try {
					
					android.graphics.pdf.PdfDocument document = new android.graphics.pdf.PdfDocument();
					
					android.graphics.pdf.PdfDocument.PageInfo pageInfo = new android.graphics.pdf.PdfDocument.PageInfo.Builder(linear2.getWidth(), linear5.getHeight(), 1).create();
					
					
					android.graphics.pdf.PdfDocument.Page page = document.startPage(pageInfo);
					
					
					Canvas canvas = page.getCanvas();
					
					Paint paint = new Paint();
					
					canvas.drawPaint(paint);
					
					linear5.draw(canvas);
					
					document.finishPage(page);
					
					FileUtil.writeFile(path, "");
					java.io.File myFile = new java.io.File(path);
					
					myFile.createNewFile();
					
					java.io.FileOutputStream fOut = new java.io.FileOutputStream(myFile);
					
					java.io.OutputStreamWriter myOutWriter = new java.io.OutputStreamWriter(fOut);
					
					document.writeTo(fOut);
					
					document.close();
					
					myOutWriter.close();
					
					fOut.close();
					
					
					Toast.makeText(getBaseContext(), "Dosya Kaydedildi", Toast.LENGTH_LONG).show();
					
					
				}
				
				catch (Exception e) {
					
					Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
					
				}
				
			}
		});
	}
	
	private void initializeLogic() {
		_RoundAndBorder(linear3, "#ffffff", 3, "#EDB754", 30);
		_RoundAndBorder(linear5, "#ffffff", 3, "#EDB754", 45);
		_RoundAndBorder(button1, "#696BE6", 0, "#ffffff", 25);
		edittext1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		edittext3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		button1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
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