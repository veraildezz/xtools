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
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;

public class LocahostActivity extends AppCompatActivity {
	
	private String alias = "";
	private String keyPassword = "";
	private String storePassword = "";
	private String commonName = "";
	private String OrganizationUnit = "";
	private String OrganizationName = "";
	private String stateName = "";
	private String CountryCode = "";
	private String city = "";
	private double expire = 0;
	private String save = "";
	private HashMap<String, Object> mp = new HashMap<>();
	private HashMap<String, Object> map = new HashMap<>();
	private String app_directory = "";
	private String current_time = "";
	private String currentTime = "";
	
	private LinearLayout linear1;
	private TextView textview1;
	private LinearLayout linear2;
	private Button button1;
	private WebView webview1;
	private EditText edittext1;
	private ImageView imageview1;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.locahost);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		textview1 = findViewById(R.id.textview1);
		linear2 = findViewById(R.id.linear2);
		button1 = findViewById(R.id.button1);
		webview1 = findViewById(R.id.webview1);
		webview1.getSettings().setJavaScriptEnabled(true);
		webview1.getSettings().setSupportZoom(true);
		edittext1 = findViewById(R.id.edittext1);
		imageview1 = findViewById(R.id.imageview1);
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
					
					PrintTheWebPage(webview1);
					
				} else { 
					
					showMessage("LOLLİPOP Cihazınızda Mevcut Değil");
					
				}
			}
		});
		
		webview1.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageStarted(WebView _param1, String _param2, Bitmap _param3) {
				final String _url = _param2;
				
				super.onPageStarted(_param1, _param2, _param3);
			}
			
			@Override
			public void onPageFinished(WebView _param1, String _param2) {
				final String _url = _param2;
				
				super.onPageFinished(_param1, _param2);
			}
		});
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (edittext1.getText().toString().equals("")) {
					SketchwareUtil.showMessage(getApplicationContext(), "Lütfen geçerli url giriniz");
				}
				else {
					webview1.loadUrl(edittext1.getText().toString());
				}
			}
		});
	}
	
	private void initializeLogic() {
		webview1.loadUrl("https://ata.msb.gov.tr/");
		_RoundAndBorder(button1, "#696BE6", 0, "#ffffff", 30);
		_RoundAndBorder(edittext1, "#696BE6", 0, "#ffffff", 30);
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		button1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		edittext1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
	}
	
	public void _RoundAndBorder(final View _view, final String _color1, final double _border, final String _color2, final double _round) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		gd.setColor(Color.parseColor(_color1));
		gd.setCornerRadius((int) _round);
		gd.setStroke((int) _border, Color.parseColor(_color2));
		_view.setBackground(gd);
	}
	
	
	public void _extra() {
	}
	
	android.print.PrintJob printJob;
	
	
	
	@androidx.annotation.RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	
	private void PrintTheWebPage(WebView webView) {
		
		android.print.PrintManager printManager = (android.print.PrintManager) this.getSystemService(Context.PRINT_SERVICE);
		
		String jobName = "My_webpage" + webView.getUrl();
		
		android.print.PrintDocumentAdapter printAdapter = webView.createPrintDocumentAdapter(jobName);
		
		assert printManager != null;
		
		printJob = printManager.print(jobName, printAdapter, new android.print.PrintAttributes.Builder().build());
		
	}
	
	{
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