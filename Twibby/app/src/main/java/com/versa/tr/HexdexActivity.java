package com.versa.tr;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.*;
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
import java.util.regex.*;
import org.json.*;

public class HexdexActivity extends AppCompatActivity {
	
	private String html = "";
	
	private LinearLayout linear1;
	private WebView webview1;
	private TextView textview1;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.hexdex);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		webview1 = findViewById(R.id.webview1);
		webview1.getSettings().setJavaScriptEnabled(true);
		webview1.getSettings().setSupportZoom(true);
		textview1 = findViewById(R.id.textview1);
		
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
	}
	
	private void initializeLogic() {
		html = "<!DOCTYPE html>\n<html lang=\"tr\">\n<head>\n    <meta charset=\"UTF-8\">\n    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n    <title>Sayı Sistemi Dönüştürücü</title>\n    <style>\n        /* CSS Stilleri */\n        body {\n            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\n            display: flex;\n            justify-content: center;\n            align-items: center;\n            min-height: 100vh;\n            margin: 0;\n            background: linear-gradient(to right, #ffbe76, #f0932b); /* Turuncumsu gradient */\n            color: #333;\n        }\n\n        .converter-container {\n            background-color: #fff;\n            padding: 30px;\n            border-radius: 15px;\n            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.2);\n            text-align: center;\n            width: 90%;\n            max-width: 450px;\n        }\n\n        h1 {\n            color: #e67e22; /* Daha koyu turuncu */\n            margin-bottom: 30px;\n            font-size: 1.8em;\n        }\n\n        .input-group {\n            margin-bottom: 20px;\n            text-align: left;\n        }\n\n        .input-group label {\n            display: block;\n            margin-bottom: 8px;\n            font-weight: bold;\n            color: #696BE6; /* Turuncu */\n        }\n\n        .input-group input[type=\"text\"] {\n            width: calc(100% - 20px);\n            padding: 12px 10px;\n            border: 1px solid #fed330; /* Açık turuncu */\n            border-radius: 8px;\n            font-size: 1.1em;\n            outline: none;\n            transition: border-color 0.3s ease, box-shadow 0.3s ease;\n        }\n\n        .input-group input[type=\"text\"]:focus {\n            border-color: #696BE6; /* Koyu turuncu odaklanma */\n            box-shadow: 0 0 8px rgba(230, 126, 34, 0.3);\n        }\n\n        button {\n            background-color: #696BE6; /* Koyu turuncu buton */\n            color: white;\n            padding: 12px 25px;\n            border: none;\n            border-radius: 8px;\n            font-size: 1.1em;\n            cursor: pointer;\n            transition: background-color 0.3s ease, transform 0.2s ease;\n            margin-top: 15px;\n        }\n\n        button:hover {\n            background-color: #696BE6; /* Daha koyu turuncu hover */\n            transform: translateY(-2px);\n        }\n\n        button:active {\n            transform: translateY(0);\n        }\n    </style>\n</head>\n<body>\n    <div class=\"converter-container\">\n        <h1>Sayı Sistemi Dönüştürücü</h1>\n        <div class=\"input-group\">\n            <label for=\"hexInput\">Hexadecimal:</label>\n            <input type=\"text\" id=\"hexInput\" placeholder=\"Hex değer girin\">\n        </div>\n        <div class=\"input-group\">\n            <label for=\"decInput\">Decimal:</label>\n            <input type=\"text\" id=\"decInput\" placeholder=\"Decimal değer girin\">\n        </div>\n        <div class=\"input-group\">\n            <label for=\"binInput\">Binary:</label>\n            <input type=\"text\" id=\"binInput\" placeholder=\"Binary değer girin\">\n        </div>\n        <button id=\"clearButton\">Temizle</button>\n    </div>\n\n    <script>\n        // JavaScript Fonksiyonelliği\n        document.addEventListener('DOMContentLoaded', () => {\n            const hexInput = document.getElementById('hexInput');\n            const decInput = document.getElementById('decInput');\n            const binInput = document.getElementById('binInput');\n            const clearButton = document.getElementById('clearButton');\n\n            function convert(value, fromType) {\n                let decValue;\n                let hexValue = '';\n                let binValue = '';\n\n                if (value === '') {\n                    return { hex: '', dec: '', bin: '' };\n                }\n\n                try {\n                    switch (fromType) {\n                        case 'hex':\n                            decValue = parseInt(value, 16);\n                            if (isNaN(decValue)) throw new Error('Geçersiz Hexadecimal değer');\n                            hexValue = value.toUpperCase();\n                            binValue = decValue.toString(2);\n                            break;\n                        case 'dec':\n                            decValue = parseInt(value, 10);\n                            if (isNaN(decValue)) throw new Error('Geçersiz Decimal değer');\n                            hexValue = decValue.toString(16).toUpperCase();\n                            binValue = decValue.toString(2);\n                            break;\n                        case 'bin':\n                            // Binary için sadece 0 ve 1 kontrolü\n                            if (!/^[01]+$/.test(value)) throw new Error('Geçersiz Binary değer (sadece 0 ve 1)');\n                            decValue = parseInt(value, 2);\n                            if (isNaN(decValue)) throw new Error('Geçersiz Binary değer');\n                            hexValue = decValue.toString(16).toUpperCase();\n                            binValue = value;\n                            break;\n                        default:\n                            return { hex: '', dec: '', bin: '' };\n                    }\n\n                    return {\n                        hex: hexValue,\n                        dec: decValue.toString(),\n                        bin: binValue\n                    };\n                } catch (error) {\n                    console.error(error.message);\n                    return { hex: 'Hata!', dec: 'Hata!', bin: 'Hata!' };\n                }\n            }\n\n            function updateInputs(sourceInputId) {\n                let value;\n                let result;\n\n                if (sourceInputId === 'hexInput') {\n                    value = hexInput.value.trim();\n                    result = convert(value, 'hex');\n                    decInput.value = result.dec;\n                    binInput.value = result.bin;\n                } else if (sourceInputId === 'decInput') {\n                    value = decInput.value.trim();\n                    result = convert(value, 'dec');\n                    hexInput.value = result.hex;\n                    binInput.value = result.bin;\n                } else if (sourceInputId === 'binInput') {\n                    value = binInput.value.trim();\n                    result = convert(value, 'bin');\n                    hexInput.value = result.hex;\n                    decInput.value = result.dec;\n                }\n            }\n\n            hexInput.addEventListener('input', () => updateInputs('hexInput'));\n            decInput.addEventListener('input', () => updateInputs('decInput'));\n            binInput.addEventListener('input', () => updateInputs('binInput'));\n\n            clearButton.addEventListener('click', () => {\n                hexInput.value = '';\n                decInput.value = '';\n                binInput.value = '';\n            });\n        });\n    </script>\n</body>\n</html>\n";
		webview1.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
		webview1.loadData(html, "text/html; charset=utf-8", " UTF-8") ;
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