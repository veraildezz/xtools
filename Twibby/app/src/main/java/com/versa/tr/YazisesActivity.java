package com.versa.tr;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.Typeface;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.os.*;
import android.speech.tts.TextToSpeech;
import android.text.*;
import android.text.Editable;
import android.text.TextWatcher;
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
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.firebase.FirebaseApp;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;

public class YazisesActivity extends AppCompatActivity {
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private LinearLayout linear4;
	private LinearLayout linear5;
	private LinearLayout linear6;
	private TextView textview4;
	private EditText edittext1;
	private TextView textview1;
	private TextView textview2;
	private SeekBar seekbar1;
	private TextView textview3;
	private SeekBar seekbar2;
	private Button button1;
	
	private TextToSpeech tts;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.yazises);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		linear2 = findViewById(R.id.linear2);
		linear3 = findViewById(R.id.linear3);
		linear4 = findViewById(R.id.linear4);
		linear5 = findViewById(R.id.linear5);
		linear6 = findViewById(R.id.linear6);
		textview4 = findViewById(R.id.textview4);
		edittext1 = findViewById(R.id.edittext1);
		textview1 = findViewById(R.id.textview1);
		textview2 = findViewById(R.id.textview2);
		seekbar1 = findViewById(R.id.seekbar1);
		textview3 = findViewById(R.id.textview3);
		seekbar2 = findViewById(R.id.seekbar2);
		button1 = findViewById(R.id.button1);
		tts = new TextToSpeech(getApplicationContext(), null);
		
		edittext1.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				textview1.setText(String.valueOf((long)(_charSeq.length())).concat("/250"));
				if (_charSeq.length() > 250) {
					button1.setEnabled(false);
				}
				else {
					button1.setEnabled(true);
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		seekbar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar _param1, int _param2, boolean _param3) {
				final int _progressValue = _param2;
				tts.setPitch((float)_progressValue);
				textview2.setText("İncelik: ".concat(String.valueOf((long)(_progressValue)).concat("%")));
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar _param1) {
				
			}
			
			@Override
			public void onStopTrackingTouch(SeekBar _param2) {
				
			}
		});
		
		seekbar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar _param1, int _param2, boolean _param3) {
				final int _progressValue = _param2;
				tts.setSpeechRate((float)_progressValue);
				textview3.setText("Konuşma Hızı: ".concat(String.valueOf((long)(_progressValue)).concat("%")));
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar _param1) {
				
			}
			
			@Override
			public void onStopTrackingTouch(SeekBar _param2) {
				
			}
		});
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (tts.isSpeaking()) {
					tts.stop();
				}
				else {
					tts.speak(edittext1.getText().toString(), TextToSpeech.QUEUE_ADD, null);
				}
			}
		});
	}
	
	private void initializeLogic() {
		_RoundAndBorder(linear3, "#ffffff", 3, "#EDB754", 20);
		_RoundAndBorder(linear4, "#ffffff", 3, "#EDB754", 20);
		_RoundAndBorder(linear5, "#ffffff", 3, "#EDB754", 20);
		_RoundAndBorder(button1, "#696BE6", 0, "#ffffff", 25);
		textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		button1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		edittext1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
	}
	
	public void _Elevation(final View _view, final double _number) {
		
		_view.setElevation((int)_number);
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