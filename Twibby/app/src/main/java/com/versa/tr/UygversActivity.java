package com.versa.tr;

import android.animation.*;
import android.app.*;
import android.content.*;
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
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;

public class UygversActivity extends AppCompatActivity {
	
	private ArrayList<String> list11 = new ArrayList<>();
	private ArrayList<String> list12 = new ArrayList<>();
	private ArrayList<String> list13 = new ArrayList<>();
	private ArrayList<String> list14 = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> list1 = new ArrayList<>();
	
	private LinearLayout linear1;
	private TextView textview1;
	private LinearLayout linear2;
	private ListView listview1;
	
	private Intent i = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.uygvers);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		textview1 = findViewById(R.id.textview1);
		linear2 = findViewById(R.id.linear2);
		listview1 = findViewById(R.id.listview1);
		
		listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				i.setAction(Intent.ACTION_VIEW);
				i.setData(Uri.parse("android-app://".concat(list12.get((int)(_position)))));
				startActivity(i);
			}
		});
		
		listview1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				
				return true;
			}
		});
	}
	
	private void initializeLogic() {
		List<android.content.pm.PackageInfo> listn = getApplicationContext().getPackageManager().getInstalledPackages(0);
		
		for (android.content.pm.PackageInfo packageInfo : listn) {
			list11.add(packageInfo.applicationInfo.loadLabel(getPackageManager()).toString());
			list12.add(packageInfo.packageName);
			list13.add(packageInfo.versionName);
			
			list14.add(String.valueOf(packageInfo.versionCode));
			
			{
				HashMap<String, Object> _item = new HashMap<>();
				_item.put("x", "x");
				list1.add(_item);
			}
			
			{
				HashMap<String, android.graphics.drawable.Drawable> _item = new HashMap<String, android.graphics.drawable.Drawable>();
				_item.put("itm",packageInfo.applicationInfo.loadIcon(getPackageManager()));
				list6.add(_item);
			}
			
		}
		listview1.setAdapter(new Listview1Adapter(list1));
	}
	private ArrayList < HashMap < String, android.graphics.drawable.Drawable> > list6 = new ArrayList<>(); private void nothing() {
		_RoundAndBorder(listview1, "#ffffff", 0, "#000000", 25);
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
	}
	
	public void _RoundAndBorder(final View _view, final String _color1, final double _border, final String _color2, final double _round) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		gd.setColor(Color.parseColor(_color1));
		gd.setCornerRadius((int) _round);
		gd.setStroke((int) _border, Color.parseColor(_color2));
		_view.setBackground(gd);
	}
	
	public class Listview1Adapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = getLayoutInflater();
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.uygverss, null);
			}
			
			final LinearLayout linear4 = _view.findViewById(R.id.linear4);
			final LinearLayout linear5 = _view.findViewById(R.id.linear5);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			final LinearLayout linear3 = _view.findViewById(R.id.linear3);
			final TextView textview1 = _view.findViewById(R.id.textview1);
			final TextView textview2 = _view.findViewById(R.id.textview2);
			final TextView textview3 = _view.findViewById(R.id.textview3);
			final TextView textview4 = _view.findViewById(R.id.textview4);
			
			textview1.setText(list11.get((int)(_position)));
			textview2.setText(list12.get((int)(_position)));
			textview3.setText(list13.get((int)(_position)));
			textview4.setText(list14.get((int)(_position)));
			imageview1.setImageDrawable(list6.get((int)_position).get("itm"));
			if ((_position % 2) == 1) {
				linear4.setBackgroundColor(0xFFFFFFFF);
			}
			else {
				linear4.setBackgroundColor(0xFFFFFFFF);
			}
			_RoundAndBorder(linear4, "#696BE6", 0, "#000000", 45);
			textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
			textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
			textview3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
			textview4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
			
			return _view;
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