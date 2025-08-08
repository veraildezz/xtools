package com.versa.tr;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.ClipData;
import android.content.ClipboardManager;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
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
import java.util.regex.*;
import org.json.*;

public class EncryptdecryptActivity extends AppCompatActivity {
	
	private ScrollView vscroll1;
	private LinearLayout linear1;
	private TextView textview2;
	private LinearLayout linear2;
	private ImageView imageview1;
	private LinearLayout linear4;
	private LinearLayout linear3;
	private LinearLayout linear5;
	private LinearLayout linear6;
	private EditText edittext1;
	private Button button1;
	private TextView textview1;
	private EditText edittext2;
	private Button button2;
	
	private Intent i = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.encryptdecrypt);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		vscroll1 = findViewById(R.id.vscroll1);
		linear1 = findViewById(R.id.linear1);
		textview2 = findViewById(R.id.textview2);
		linear2 = findViewById(R.id.linear2);
		imageview1 = findViewById(R.id.imageview1);
		linear4 = findViewById(R.id.linear4);
		linear3 = findViewById(R.id.linear3);
		linear5 = findViewById(R.id.linear5);
		linear6 = findViewById(R.id.linear6);
		edittext1 = findViewById(R.id.edittext1);
		button1 = findViewById(R.id.button1);
		textview1 = findViewById(R.id.textview1);
		edittext2 = findViewById(R.id.edittext2);
		button2 = findViewById(R.id.button2);
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", textview1.getText().toString()));
				SketchwareUtil.showMessage(getApplicationContext(), "Kopyalandı");
			}
		});
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				linear3.setVisibility(View.VISIBLE);
				try {
					keyGenerator = javax.crypto.KeyGenerator.getInstance("AES");
					keyGenerator.init(256);
					secretKey = keyGenerator.generateKey();
					secretKeyen=secretKey.getEncoded();
					cipherText = encrypt(edittext1.getText().toString().getBytes(), secretKey, IV);
					textview1.setText(android.util.Base64.encodeToString(cipherText, android.util.Base64.DEFAULT));
				} catch ( java.security.NoSuchAlgorithmException e){
					showMessage(e.toString());
				} catch (Exception e){
					showMessage(e.toString());
				}
			}
		});
		
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				try {
					javax.crypto.SecretKey originalSecretKey = new javax.crypto.spec.SecretKeySpec(secretKeyen, 0, secretKeyen.length, "AES");
					String decryptedText = decrypt(cipherText, originalSecretKey, IV); textview1.setText(decryptedText);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void initializeLogic() {
		_RoundAndBorder(button1, "#696BE6", 0, "#ffffff", 60);
		_RoundAndBorder(button2, "#696BE6", 0, "#ffffff", 60);
		_RoundAndBorder(linear4, "#ffffff", 0, "#000000", 40);
		_RoundAndBorder(linear3, "#ffffff", 0, "#000000", 20);
		_RoundAndBorder(linear2, "#ffffff", 0, "#000000", 20);
		textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		edittext1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		button1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		edittext2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		button2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
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
	
	javax.crypto.KeyGenerator keyGenerator;
	javax.crypto.SecretKey secretKey;
	byte[] secretKeyen;
	String strSecretKey;
	byte[] IV = new byte[16];
	byte[] cipherText;
	
	public static byte[] encrypt(byte[] plaintext, javax.crypto.SecretKey key, byte[] IV) throws Exception{
		javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("AES");
		javax.crypto.spec.SecretKeySpec keySpec = new javax.crypto.spec.SecretKeySpec(key.getEncoded(), "AES");
		javax.crypto.spec.IvParameterSpec ivSpec = new javax.crypto.spec.IvParameterSpec(IV);
		cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, keySpec, ivSpec);
		byte[] cipherText = cipher.doFinal(plaintext);
		return cipherText;
	}
	
	public static String decrypt(byte[] cipherText, javax.crypto.SecretKey key, byte[] IV){
		try {
			javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("AES");
			javax.crypto.spec.SecretKeySpec keySpec = new javax.crypto.spec.SecretKeySpec(key.getEncoded(), "AES");
			javax.crypto.spec.IvParameterSpec ivSpec = new javax.crypto.spec.IvParameterSpec(IV);
			cipher.init(javax.crypto.Cipher.DECRYPT_MODE, keySpec, ivSpec);
			byte[] decryptedText = cipher.doFinal(cipherText);
			return new String(decryptedText);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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