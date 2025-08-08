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

public class ViewsourceActivity extends AppCompatActivity {
	
	private String code = "";
	private String html = "";
	
	private LinearLayout linear7;
	private WebView webview1;
	
	private RequestNetwork rne;
	private RequestNetwork.RequestListener _rne_request_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.viewsource);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear7 = findViewById(R.id.linear7);
		webview1 = findViewById(R.id.webview1);
		webview1.getSettings().setJavaScriptEnabled(true);
		webview1.getSettings().setSupportZoom(true);
		rne = new RequestNetwork(this);
		
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
		
		_rne_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
	}
	
	private void initializeLogic() {
		html = "<!DOCTYPE html>\n<html lang=\"tr\">\n<head>\n    <meta charset=\"UTF-8\">\n    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n    <title>Web Kaynak Kodu Görüntüleyici</title>\n    <link href=\"https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap\" rel=\"stylesheet\">\n    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css\">\n    <style>\n        body {\n            font-family: 'Poppins', sans-serif;\n            display: flex;\n            justify-content: center;\n            align-items: center;\n            min-height: 100vh;\n            background-color: #f0f2f5;\n            margin: 0;\n            padding: 20px;\n            box-sizing: border-box;\n        }\n\n        .container {\n            background-color: #fff;\n            border-radius: 15px;\n            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);\n            padding: 30px;\n            width: 100%;\n            max-width: 500px;\n            box-sizing: border-box;\n            text-align: center;\n        }\n\n        .header {\n            font-size: 24px;\n            font-weight: 600;\n            color: #4a4a4a;\n            margin-bottom: 30px;\n        }\n\n        .input-section,\n        .code-section {\n            background-color: #f7f7f7;\n            border: 1px solid #ddd;\n            border-radius: 10px;\n            padding: 20px;\n            margin-bottom: 20px;\n            box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.05);\n            position: relative; /* For copy button positioning */\n        }\n\n        .label {\n            font-size: 14px;\n            color: #666;\n            margin-bottom: 10px;\n            text-align: left;\n        }\n\n        input[type=\"text\"] {\n            width: calc(100% - 20px);\n            padding: 12px 10px;\n            margin-bottom: 15px;\n            border: 1px solid #ccc;\n            border-radius: 8px;\n            font-size: 16px;\n            box-sizing: border-box;\n        }\n\n        button {\n            width: 100%;\n            padding: 12px;\n            border: none;\n            border-radius: 8px;\n            font-size: 16px;\n            font-weight: 600;\n            cursor: pointer;\n            transition: background-color 0.3s ease;\n            color: #fff;\n        }\n\n        .fetch-button {\n            background-color: #696BE6; /* Orange color from image */\n        }\n\n        .fetch-button:hover {\n            background-color: #696BE6;\n        }\n\n        .clear-button {\n            background-color: #696BE6; /* Red color from image */\n            margin-top: 10px; /* Space from the code section */\n        }\n\n        .clear-button:hover {\n            background-color: #696BE6;\n        }\n\n        #sourceCodeDisplay {\n            width: calc(100% - 20px);\n            height: 200px;\n            padding: 10px;\n            border: 1px solid #eee;\n            border-radius: 8px;\n            background-color: #fff;\n            font-family: 'Courier New', monospace;\n            font-size: 13px;\n            line-height: 1.5;\n            resize: vertical;\n            overflow-y: auto;\n            white-space: pre-wrap; /* Preserves whitespace and wraps */\n            word-wrap: break-word; /* Breaks long words */\n            box-sizing: border-box;\n            text-align: left;\n            color: #333;\n        }\n\n        .copy-button {\n            position: absolute;\n            top: 15px;\n            right: 15px;\n            width: auto;\n            padding: 8px 12px;\n            background-color: #e0e0e0;\n            color: #555;\n            border-radius: 5px;\n            font-size: 14px;\n            cursor: pointer;\n            transition: background-color 0.2s ease;\n            z-index: 10; /* Ensure it's above the textarea */\n        }\n\n        .copy-button i {\n            margin-right: 5px;\n        }\n\n        .copy-button:hover {\n            background-color: #d0d0d0;\n        }\n\n        /* Responsive adjustments */\n        @media (max-width: 600px) {\n            .container {\n                padding: 20px;\n                margin: 10px;\n            }\n\n            .header {\n                font-size: 20px;\n            }\n\n            input[type=\"text\"],\n            button {\n                font-size: 15px;\n                padding: 10px;\n            }\n\n            #sourceCodeDisplay {\n                font-size: 12px;\n                height: 150px;\n            }\n        }\n    </style>\n</head>\n<body>\n    <div class=\"container\">\n        <div class=\"header\">\n            Web Kaynak Kodu Görüntüleyici\n        </div>\n\n        <div class=\"input-section\">\n            <div class=\"label\">Web Sitesi URL'si</div>\n            <input type=\"text\" id=\"urlInput\" value=\"https://sketchware.pro\" placeholder=\"Web Sitesi URL'sini Girin\">\n            <button id=\"fetchButton\" class=\"fetch-button\">KAYNAK KODU ÇEK</button>\n        </div>\n\n        <div class=\"code-section\">\n            <div class=\"label\">Kaynak Kodu</div>\n            <button id=\"copyButton\" class=\"copy-button\" title=\"Kopyala\"><i class=\"fas fa-copy\"></i> Kopyala</button>\n            <textarea id=\"sourceCodeDisplay\" readonly>\nWeb sitesi \"https://sketchware.pro\" için kaynak kod çekme denemesi yapılıyor...\n\nTarayıcı güvenlik kısıtlamaları (CORS) nedeniyle, JavaScript doğrudan farklı bir domendeki web sitesinin kaynak kodunu çekemez.\n\nGerçek bir kaynak kodu görüntüleyici için sunucu tarafı bir proxy (örn. PHP, Node.js) gereklidir.\n(Örnek proxy: https://api.allorigins.win/get?url=)\n            </textarea>\n        </div>\n\n        <button id=\"clearButton\" class=\"clear-button\">TEMİZLE</button>\n    </div>\n\n    <script>\n        document.addEventListener('DOMContentLoaded', () => {\n            const urlInput = document.getElementById('urlInput');\n            const fetchButton = document.getElementById('fetchButton');\n            const sourceCodeDisplay = document.getElementById('sourceCodeDisplay');\n            const clearButton = document.getElementById('clearButton');\n            const copyButton = document.getElementById('copyButton');\n\n            // Initial placeholder text for the source code display\n            sourceCodeDisplay.value = `Web sitesi \"https://sketchware.pro\" için kaynak kod çekme denemesi yapılıyor...\n\nTarayıcı güvenlik kısıtlamaları (CORS) nedeniyle, JavaScript doğrudan farklı bir domendeki web sitesinin kaynak kodunu çekemez.\n\nGerçek bir kaynak kodu görüntüleyici için sunucu tarafı bir proxy (örn. PHP, Node.js) gereklidir.\n(Örnek proxy: https://api.allorigins.win/get?url=)`;\n\n\n            fetchButton.addEventListener('click', fetchSourceCode);\n            clearButton.addEventListener('click', clearSourceCode);\n            copyButton.addEventListener('click', copySourceCode);\n\n            async function fetchSourceCode() {\n                const url = urlInput.value.trim();\n                if (!url) {\n                    sourceCodeDisplay.value = 'Lütfen geçerli bir URL girin.';\n                    return;\n                }\n\n                sourceCodeDisplay.value = `\"${url}\" için kaynak kod çekiliyor... Lütfen bekleyin.`;\n\n                // IMPORTANT: You need a CORS proxy for this to work in a browser.\n                // Replace 'YOUR_CORS_PROXY_URL_HERE' with an actual proxy URL.\n                // Examples:\n                // 'https://api.allorigins.win/get?url='\n                // 'https://thingproxy.freeboard.io/fetch/'\n                const proxyUrl = 'https://api.allorigins.win/get?url='; // Example proxy\n\n                try {\n                    const response = await fetch(`${proxyUrl}${encodeURIComponent(url)}`);\n\n                    if (!response.ok) {\n                        throw new Error(`HTTP Hata: ${response.status} ${response.statusText}`);\n                    }\n\n                    const data = await response.json();\n                    // AllOrigins returns the content in a 'contents' property\n                    // Other proxies might return it directly or in a different property\n                    const sourceCode = data.contents || 'Kaynak kod çekilemedi. Proxy yanıt yapısı beklenenden farklı olabilir.';\n\n                    sourceCodeDisplay.value = sourceCode;\n                } catch (error) {\n                    sourceCodeDisplay.value = `Kaynak kod çekilirken bir hata oluştu:\nHata Mesajı: ${error.message}\nTarayıcı güvenlik kısıtlamaları (CORS) veya yanlış URL nedeniyle olabilir.\nBir proxy sunucusu kullanıldığından emin olun ve URL'nin doğru olduğunu kontrol edin.`;\n                    console.error('Kaynak kod çekme hatası:', error);\n                }\n            }\n\n            function clearSourceCode() {\n                urlInput.value = '';\n                sourceCodeDisplay.value = `Web sitesi için kaynak kod çekme denemesi yapılıyor...\n\nTarayıcı güvenlik kısıtlamaları (CORS) nedeniyle, JavaScript doğrudan farklı bir domendeki web sitesinin kaynak kodunu çekemez.\n\nGerçek bir kaynak kodu görüntüleyici için sunucu tarafı bir proxy (örn. PHP, Node.js) gereklidir.\n(Örnek proxy: https://api.allorigins.win/get?url=)`;\n            }\n\n            function copySourceCode() {\n                sourceCodeDisplay.select();\n                sourceCodeDisplay.setSelectionRange(0, 99999); // For mobile devices\n\n                try {\n                    navigator.clipboard.writeText(sourceCodeDisplay.value);\n                    copyButton.textContent = 'Kopyalandı!';\n                    setTimeout(() => {\n                        copyButton.innerHTML = '<i class=\"fas fa-copy\"></i> Kopyala'; // Revert text and icon\n                    }, 1500);\n                } catch (err) {\n                    console.error('Kopyalama başarısız:', err);\n                    alert('Kaynak kodu kopyalanamadı. Lütfen manuel olarak kopyalayın.');\n                }\n            }\n        });\n    </script>\n</body>\n</html>\n";
		webview1.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
		webview1.loadData(html, "text/html; charset=utf-8", " UTF-8") ;
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