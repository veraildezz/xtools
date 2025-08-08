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
import android.widget.CheckBox;
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
import com.google.android.material.textfield.*;
import com.google.firebase.FirebaseApp;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;
import com.android.apksig.apk.ApkFormatException;
import java.security.GeneralSecurityException;
import java.io.IOException;

public class QrkodActivity extends AppCompatActivity {
	
	public final int REQ_CD_PİCKER = 101;
	
	private LinearLayout linear2;
	private TextView textview2;
	private LinearLayout linear3;
	private LinearLayout linear4;
	private LinearLayout linear5;
	private LinearLayout linear1;
	private TextInputLayout ti_input;
	private TextInputLayout textinputlayout2;
	private CheckBox checkbox1;
	private CheckBox checkbox2;
	private CheckBox checkbox3;
	private CheckBox checkbox4;
	private Button button1;
	private TextView tv_log;
	private EditText input;
	private EditText output;
	
	private Intent picker = new Intent(Intent.ACTION_GET_CONTENT);
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.qrkod);
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
		linear2 = findViewById(R.id.linear2);
		textview2 = findViewById(R.id.textview2);
		linear3 = findViewById(R.id.linear3);
		linear4 = findViewById(R.id.linear4);
		linear5 = findViewById(R.id.linear5);
		linear1 = findViewById(R.id.linear1);
		ti_input = findViewById(R.id.ti_input);
		textinputlayout2 = findViewById(R.id.textinputlayout2);
		checkbox1 = findViewById(R.id.checkbox1);
		checkbox2 = findViewById(R.id.checkbox2);
		checkbox3 = findViewById(R.id.checkbox3);
		checkbox4 = findViewById(R.id.checkbox4);
		button1 = findViewById(R.id.button1);
		tv_log = findViewById(R.id.tv_log);
		input = findViewById(R.id.input);
		output = findViewById(R.id.output);
		picker.setType("application/vnd.android.package-archive");
		picker.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		
		checkbox4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				SketchwareUtil.showMessage(getApplicationContext(), "Mevcut Değil");
			}
		});
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_sign(input.getText().toString(), output.getText().toString(), checkbox1.isChecked(), checkbox2.isChecked(), checkbox3.isChecked(), checkbox4.isChecked());
			}
		});
	}
	
	private void initializeLogic() {
		
		button1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)8, 0xFF696BE6));
		ti_input.setEndIconOnClickListener(new View.OnClickListener() {
			    @Override
			    public void onClick(View v) {
				startActivityForResult(picker, REQ_CD_PİCKER);
				    }
		});
		textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		checkbox1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		output.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		checkbox2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		checkbox3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		checkbox4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		button1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		tv_log.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		input.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			case REQ_CD_PİCKER:
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
				input.setText(_filePath.get((int)(0)));
				output.setText(_filePath.get((int)(0)).replace(_filePath.get((int)(0)).substring((int)(_filePath.get((int)(0)).lastIndexOf(".apk")), (int)(_filePath.get((int)(0)).length())), "_signed.apk"));
			}
			else {
				
			}
			break;
			default:
			break;
		}
	}
	
	public void _RoundAndBorder(final View _view, final String _color1, final double _border, final String _color2, final double _round) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		gd.setColor(Color.parseColor(_color1));
		gd.setCornerRadius((int) _round);
		gd.setStroke((int) _border, Color.parseColor(_color2));
		_view.setBackground(gd);
	}
	
	
	public void _sign(final String _input, final String _output, final boolean _v1, final boolean _v2, final boolean _v3, final boolean _v4) {
		try {
				new Signer().calculateSignature(_input, _output, _v1, _v2, _v3, _v4);
				tv_log.setText("İmzalama Başarılı");
		} catch (ApkFormatException e) {
				tv_log.setText(e.toString());
		} catch (GeneralSecurityException e) {
				tv_log.setText(e.toString());
		} catch (IOException e) {
				tv_log.setText(e.toString());
		}
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