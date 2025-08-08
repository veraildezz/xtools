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

public class IpsorguActivity extends AppCompatActivity {
	
	private String html = "";
	
	private LinearLayout linear1;
	private TextView textview1;
	private WebView webview1;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.ipsorgu);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		textview1 = findViewById(R.id.textview1);
		webview1 = findViewById(R.id.webview1);
		webview1.getSettings().setJavaScriptEnabled(true);
		webview1.getSettings().setSupportZoom(true);
		
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
		html = "<!DOCTYPE html>\n<html lang=\"tr\">\n<head>\n    <meta charset=\"UTF-8\">\n    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n    <title>Turuncu IP Sorgu Aracı</title>\n    <style>\n        body {\n            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\n            display: flex;\n            justify-content: center;\n            align-items: center;\n            min-height: 100vh;\n            margin: 0;\n            background-color: #f7f7f7; /* Hafif gri arka plan */\n            color: #333;\n        }\n\n        .container {\n            background-color: #fff;\n            padding: 30px;\n            border-radius: 12px;\n            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);\n            text-align: center;\n            width: 90%;\n            max-width: 500px;\n            box-sizing: border-box;\n        }\n\n        h1 {\n            color: #696BE6; /* Canlı turuncu */\n            margin-bottom: 30px;\n            font-size: 2.2em;\n            font-weight: 700;\n        }\n\n        .input-section {\n            display: flex;\n            justify-content: center;\n            margin-bottom: 25px;\n            gap: 10px; /* Input ve buton arasına boşluk */\n        }\n\n        #ipInput {\n            flex-grow: 1;\n            padding: 12px 18px;\n            border: 2px solid #696BE6; /* Açık turuncu kenarlık */\n            border-radius: 8px;\n            font-size: 1.1em;\n            transition: border-color 0.3s ease;\n        }\n\n        #ipInput:focus {\n            outline: none;\n            border-color: #696BE6; /* Odaklandığında canlı turuncu */\n            box-shadow: 0 0 8px rgba(255, 127, 80, 0.3);\n        }\n\n        #searchButton {\n            background-color: #696BE6; /* Canlı turuncu */\n            color: white;\n            border: none;\n            padding: 12px 25px;\n            border-radius: 8px;\n            cursor: pointer;\n            font-size: 1.1em;\n            font-weight: 600;\n            transition: background-color 0.3s ease, transform 0.2s ease;\n        }\n\n        #searchButton:hover {\n            background-color: #696BE6; /* Daha koyu turuncu */\n            transform: translateY(-2px);\n        }\n\n        .results-section {\n            background-color: #696BE6; /* Çok açık turuncu */\n            border-radius: 8px;\n            padding: 20px;\n            text-align: left;\n            min-height: 100px; /* Sonuçlar için minimum yükseklik */\n            display: flex;\n            flex-direction: column;\n            justify-content: center;\n            align-items: flex-start;\n            box-shadow: inset 0 2px 4px rgba(0, 0, 0, 0.05);\n        }\n\n        .results-section p {\n            margin: 8px 0;\n            font-size: 1.05em;\n            color: #555;\n        }\n\n        .results-section p strong {\n            color: #696BE6; /* Canlı turuncu ile önemli bilgileri vurgula */\n        }\n\n        /* Duyarlı tasarım için */\n        @media (max-width: 600px) {\n            .input-section {\n                flex-direction: column;\n                gap: 15px;\n            }\n\n            #searchButton {\n                width: 100%;\n            }\n        }\n    </style>\n</head>\n<body>\n    <div class=\"container\">\n        <h1>IP Sorgu</h1>\n        <div class=\"input-section\">\n            <input type=\"text\" id=\"ipInput\" placeholder=\"IP Adresi Girin (örn: 8.8.8.8)\">\n            <button id=\"searchButton\">Sorgula</button>\n        </div>\n        <div id=\"results\" class=\"results-section\">\n            </div>\n    </div>\n\n    <script>\n        document.addEventListener('DOMContentLoaded', () => {\n            const ipInput = document.getElementById('ipInput');\n            const searchButton = document.getElementById('searchButton');\n            const resultsDiv = document.getElementById('results');\n\n            searchButton.addEventListener('click', () => {\n                const ipAddress = ipInput.value.trim();\n                if (ipAddress) {\n                    getIpInfo(ipAddress);\n                } else {\n                    resultsDiv.innerHTML = '<p style=\"color: #696BE6;\">Lütfen bir IP adresi girin.</p>';\n                }\n            });\n\n            async function getIpInfo(ip) {\n                resultsDiv.innerHTML = '<p>Bilgiler yükleniyor...</p>'; // Yükleniyor mesajı\n                try {\n                    // ip-api.com ücretsiz bir API'dir. Detaylar için dökümantasyonunu inceleyebilirsiniz.\n                    const response = await fetch(`http://ip-api.com/json/${ip}?fields=status,message,country,countryCode,regionName,city,zip,lat,lon,timezone,isp,org,as,query`);\n                    const data = await response.json();\n\n                    if (data.status === 'success') {\n                        resultsDiv.innerHTML = `\n                            <p><strong>IP Adresi:</strong> ${data.query}</p>\n                            <p><strong>Ülke:</strong> ${data.country} (${data.countryCode})</p>\n                            <p><strong>Bölge:</strong> ${data.regionName}</p>\n                            <p><strong>Şehir:</strong> ${data.city}</p>\n                            <p><strong>Posta Kodu:</strong> ${data.zip}</p>\n                            <p><strong>Zaman Dilimi:</strong> ${data.timezone}</p>\n                            <p><strong>ISS:</strong> ${data.isp}</p>\n                            <p><strong>Kuruluş:</strong> ${data.org}</p>\n                            <p><strong>Koordinatlar:</strong> ${data.lat}, ${data.lon}</p>\n                        `;\n                    } else {\n                        resultsDiv.innerHTML = `<p style=\"color: #696BE6;\">Hata: ${data.message || 'IP bilgileri alınamadı.'}</p>`;\n                    }\n                } catch (error) {\n                    console.error('IP bilgisi alınırken hata oluştu:', error);\n                    resultsDiv.innerHTML = '<p style=\"color: #696BE6;\">Bir hata oluştu. Lütfen tekrar deneyin.</p>';\n                }\n            }\n        });\n    </script>\n</body>\n</html>\n";
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