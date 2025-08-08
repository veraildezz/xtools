package com.versa.tr;

import android.animation.*;
import android.app.*;
import android.app.AlertDialog;
import android.content.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.*;
import android.graphics.*;
import android.graphics.Typeface;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.os.Bundle;
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
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.firebase.FirebaseApp;
import java.io.*;
import java.io.InputStream;
import java.text.*;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;

public class TgbotActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	
	private double no = 0;
	
	private LinearLayout linear1;
	private TextView textview1;
	private LinearLayout linear2;
	private EditText bot_token;
	private LinearLayout linear3;
	private EditText Chat_id;
	private TextView textview2;
	private LinearLayout linear4;
	private EditText message;
	private LinearLayout linear5;
	private TextView textview3;
	private Button send;
	private LinearLayout linear6;
	private TextView status;
	
	private Intent intent = new Intent();
	private TimerTask timer;
	private ProgressDialog progress;
	private AlertDialog.Builder dialog;
	private RequestNetwork rn;
	private RequestNetwork.RequestListener _rn_request_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.tgbot);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		textview1 = findViewById(R.id.textview1);
		linear2 = findViewById(R.id.linear2);
		bot_token = findViewById(R.id.bot_token);
		linear3 = findViewById(R.id.linear3);
		Chat_id = findViewById(R.id.Chat_id);
		textview2 = findViewById(R.id.textview2);
		linear4 = findViewById(R.id.linear4);
		message = findViewById(R.id.message);
		linear5 = findViewById(R.id.linear5);
		textview3 = findViewById(R.id.textview3);
		send = findViewById(R.id.send);
		linear6 = findViewById(R.id.linear6);
		status = findViewById(R.id.status);
		dialog = new AlertDialog.Builder(this);
		rn = new RequestNetwork(this);
		
		send.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				rn.startRequestNetwork(RequestNetworkController.POST, "https://api.telegram.org/bot".concat(bot_token.getText().toString().concat("/sendMessage?chat_id=".concat(Chat_id.getText().toString().concat("&text=".concat(message.getText().toString()))))), "a", _rn_request_listener);
			}
		});
		
		_rn_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				no = 0;
				progress = new ProgressDialog(TgbotActivity.this);
				progress.setTitle("Mesaj Bota Gönderiliyor");
				progress.setMessage("İnstagramdan Beni Takip Edin");
				progress.setMax((int)100);
				progress.setCancelable(false);
				progress.setCanceledOnTouchOutside(false);
				progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
				timer = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								no++;
								no++;
								progress.setProgress((int)no);
								if (no == 100) {
									timer.cancel();
									progress.dismiss();
									status.setText(_response);
									status.setVisibility(View.INVISIBLE);
									SketchwareUtil.CustomToast(getApplicationContext(), "Mesaj Başarıyla Gönderildi", 0xFF000000, 18, 0xFF76FF03, 16, SketchwareUtil.BOTTOM);
								}
							}
						});
					}
				};
				_timer.scheduleAtFixedRate(timer, (int)(0), (int)(50));
				progress.show();
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
	}
	
	private void initializeLogic() {
		_RoundAndBorder(send, "#696BE6", 0, "#ffffff", 30);
		_RoundAndBorder(linear2, "#ffffff", 5, "#000000", 35);
		send.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		status.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		bot_token.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		Chat_id.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		message.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
	}
	
	public void _telegram() {
		intent.setAction(Intent.ACTION_VIEW);
		intent.setData(Uri.parse("https://t.me/yakındaçılacak"));
		startActivity(intent);
	}
	
	
	public void _instagram() {
		intent.setAction(Intent.ACTION_VIEW);
		intent.setData(Uri.parse("https://instagram.com/erolm10/"));
		startActivity(intent);
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