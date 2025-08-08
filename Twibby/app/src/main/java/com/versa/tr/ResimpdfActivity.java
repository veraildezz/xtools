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
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;

public class ResimpdfActivity extends AppCompatActivity {
	
	public final int REQ_CD_FP = 101;
	
	private double n = 0;
	private String save_path = "";
	private String path1 = "";
	
	private ArrayList<String> list = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> imagelist = new ArrayList<>();
	
	private LinearLayout linear1;
	private TextView textview1;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private LinearLayout linear4;
	private ListView listview1;
	private EditText edittext1;
	private Button button_open;
	private Button button_save;
	
	private Intent fp = new Intent(Intent.ACTION_GET_CONTENT);
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.resimpdf);
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
		linear3 = findViewById(R.id.linear3);
		linear4 = findViewById(R.id.linear4);
		listview1 = findViewById(R.id.listview1);
		edittext1 = findViewById(R.id.edittext1);
		button_open = findViewById(R.id.button_open);
		button_save = findViewById(R.id.button_save);
		fp.setType("image/*");
		fp.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		
		button_open.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				startActivityForResult(fp, REQ_CD_FP);
			}
		});
		
		button_save.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				try {
					android.graphics.pdf.PdfDocument document = new android.graphics.pdf.PdfDocument();
					int max = 0;
					int[] allwidth = new int[imagelist.size()];
					n = 0;
					for(int _repeat13 = 0; _repeat13 < (int)(imagelist.size()); _repeat13++) {
						path1 = imagelist.get((int)n).get("pic").toString();
						Bitmap mBitmap = FileUtil.decodeSampleBitmapFromPath(path1, 1024, 1024);
						
						int width = mBitmap.getWidth();
						allwidth[(int)n] = width;
						if (width > max){
							max = width;
						}
						n++;
					}
					n = 0;
					for(int _repeat23 = 0; _repeat23 < (int)(imagelist.size()); _repeat23++) {
						Bitmap mBitmap = FileUtil.decodeSampleBitmapFromPath(imagelist.get((int)n).get("pic").toString(), 1024, 1024);
						
						android.graphics.pdf.PdfDocument.PageInfo pageInfo = new android.graphics.pdf.PdfDocument.PageInfo.Builder(max, mBitmap.getHeight(), (int)n + 1).create();
						
						android.graphics.pdf.PdfDocument.Page page = document.startPage(pageInfo);
						
						Canvas canvas = page.getCanvas();
						canvas.drawColor(Color.WHITE);
						canvas.drawBitmap(mBitmap, (max - allwidth[(int)n])/2 , 0, null);
						
						document.finishPage(page);
						
						n++;
					}
					if (edittext1.getText().toString().length() > 0) {
						save_path = FileUtil.getPublicDir(Environment.DIRECTORY_DOWNLOADS).concat("/".concat(edittext1.getText().toString()).concat(".pdf"));
					}
					else {
						SketchwareUtil.showMessage(getApplicationContext(), "Dosya Adını Girin");
					}
					
					java.io.FileOutputStream fout = new java.io.FileOutputStream(new java.io.File(save_path));
					
					document.writeTo(fout);
					document.close();
					fout.close();
					showMessage("Dosya Kaydedildi");
				} catch ( Exception e){
					showMessage(e.toString());
				}
				
			}
		});
	}
	
	private void initializeLogic() {
		_RoundAndBorder(linear2, "#ffffff", 5, "#696BE6", 40);
		_RoundAndBorder(linear3, "#ffffff", 5, "#696BE6", 25);
		_RoundAndBorder(linear4, "#ffffff", 5, "#696BE6", 25);
		_RoundAndBorder(button_open, "#696BE6", 0, "#EDB754", 20);
		_RoundAndBorder(button_save, "#696BE6", 0, "#EDB754", 20);
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		edittext1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		button_open.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		button_save.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			case REQ_CD_FP:
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
				n = 0;
				for(int _repeat11 = 0; _repeat11 < (int)(_filePath.size()); _repeat11++) {
					{
						HashMap<String, Object> _item = new HashMap<>();
						_item.put("pic", _filePath.get((int)(n)));
						imagelist.add(_item);
					}
					
					n++;
				}
				listview1.setAdapter(new Listview1Adapter(imagelist));
				((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
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
				_view = _inflater.inflate(R.layout.itemview, null);
			}
			
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			
			imageview1.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(imagelist.get((int)_position).get("pic").toString(), 1024, 1024));
			
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