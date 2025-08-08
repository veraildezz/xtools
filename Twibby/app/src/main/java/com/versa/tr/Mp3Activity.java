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
import java.util.regex.*;
import org.json.*;

public class Mp3Activity extends AppCompatActivity {
	
	public final int REQ_CD_FP = 101;
	
	private boolean mediaplayercreated = false;
	private String currentfile = "";
	private String html = "";
	
	private ArrayList<String> string = new ArrayList<>();
	
	private LinearLayout linear1;
	private TextView textview1;
	private WebView webview1;
	
	private Intent fp = new Intent(Intent.ACTION_GET_CONTENT);
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.mp3);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 1000);
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
		webview1 = findViewById(R.id.webview1);
		webview1.getSettings().setJavaScriptEnabled(true);
		webview1.getSettings().setSupportZoom(true);
		fp.setType("audio/*");
		fp.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		
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
		html = "<!DOCTYPE html>\n<html lang=\"tr\">\n<head>\n    <meta charset=\"UTF-8\">\n    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n    <title>Turuncu Şifre Oluşturucu</title>\n    <style>\n        body {\n            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\n            display: flex;\n            justify-content: center;\n            align-items: center;\n            min-height: 100vh;\n            background-color: #f0f0f0;\n            margin: 0;\n        }\n\n        .container {\n            background-color: #fff;\n            border: 3px solid #696BE6; /* Turuncu border */\n            border-radius: 20px; /* Kenarları bükük */\n            padding: 25px;\n            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.15);\n            text-align: center;\n            width: 380px; /* Genişliği biraz artırdık */\n            box-sizing: border-box;\n            display: flex;\n            flex-direction: column;\n            gap: 20px;\n        }\n\n        h1 {\n            color: #696BE6;\n            margin-bottom: 20px;\n        }\n\n        .input-group {\n            margin-bottom: 15px;\n            text-align: left;\n        }\n\n        .input-group label {\n            display: block;\n            margin-bottom: 8px;\n            color: #555;\n            font-weight: bold;\n        }\n\n        .input-group input[type=\"range\"] {\n            width: 100%;\n            -webkit-appearance: none;\n            height: 8px;\n            background: #ddd;\n            border-radius: 5px;\n            outline: none;\n            opacity: 0.7;\n            transition: opacity .2s;\n        }\n\n        .input-group input[type=\"range\"]::-webkit-slider-thumb {\n            -webkit-appearance: none;\n            appearance: none;\n            width: 20px;\n            height: 20px;\n            border-radius: 50%;\n            background: #FFA500;\n            cursor: pointer;\n        }\n\n        .input-group input[type=\"range\"]::-moz-range-thumb {\n            width: 20px;\n            height: 20px;\n            border-radius: 50%;\n            background: #FFA500;\n            cursor: pointer;\n        }\n\n        .input-group input[type=\"text\"] {\n            width: calc(100% - 22px); /* Padding'i hesaba kattık */\n            padding: 10px;\n            border: 1px solid #ddd;\n            border-radius: 8px;\n            font-size: 1.1em;\n            color: #333;\n            background-color: #fff;\n        }\n        \n        .input-group input[type=\"checkbox\"] {\n            margin-right: 8px;\n            width: 18px;\n            height: 18px;\n            vertical-align: middle;\n            accent-color: #FFA500; /* Checkbox rengi */\n        }\n        \n        .checkbox-group {\n            display: flex;\n            align-items: center;\n            margin-bottom: 10px;\n        }\n\n        button {\n            background-color: #FFA500; /* Turuncu buton */\n            color: white;\n            border: none;\n            border-radius: 10px;\n            padding: 12px 25px;\n            font-size: 1.2em;\n            cursor: pointer;\n            transition: background-color 0.3s ease, transform 0.2s ease;\n            width: 100%;\n            box-sizing: border-box;\n        }\n\n        button:hover {\n            background-color: #FF8C00;\n            transform: translateY(-2px);\n        }\n\n        #password-output {\n            background-color: #FFF3E0; /* Açık turuncu arka plan */\n            border: 1px dashed #FFBF69; /* Kesik turuncu border */\n            border-radius: 8px;\n            padding: 15px;\n            font-size: 1.3em;\n            font-weight: bold;\n            color: #333;\n            word-wrap: break-word; /* Uzun şifreler için */\n            text-align: center;\n            margin-top: 20px;\n            min-height: 40px; /* Minimum yükseklik */\n            display: flex;\n            align-items: center;\n            justify-content: center;\n        }\n\n        #strength-indicator {\n            height: 10px;\n            width: 0%;\n            background-color: #e0e0e0;\n            border-radius: 5px;\n            margin-top: 15px;\n            transition: width 0.3s ease-in-out;\n        }\n\n        #strength-text {\n            margin-top: 5px;\n            font-weight: bold;\n            color: #666;\n        }\n\n        /* Strength colors */\n        .weak { background-color: #FF4D4D; } /* Kırmızı */\n        .medium { background-color: #FFC107; } /* Sarımsı turuncu */\n        .strong { background-color: #4CAF50; } /* Yeşil */\n        .very-strong { background-color: #007bff; } /* Mavi */\n    </style>\n</head>\n<body>\n    <div class=\"container\">\n        <h1>Şifre Generator</h1>\n\n        <div class=\"input-group\">\n            <label for=\"password-length\">Şifre Uzunluğu: <span id=\"length-value\">12</span></label>\n            <input type=\"range\" id=\"password-length\" min=\"6\" max=\"30\" value=\"12\">\n        </div>\n\n        <div class=\"checkbox-group\">\n            <input type=\"checkbox\" id=\"include-uppercase\" checked>\n            <label for=\"include-uppercase\">Büyük Harfler (A-Z)</label>\n        </div>\n        <div class=\"checkbox-group\">\n            <input type=\"checkbox\" id=\"include-lowercase\" checked>\n            <label for=\"include-lowercase\">Küçük Harfler (a-z)</label>\n        </div>\n        <div class=\"checkbox-group\">\n            <input type=\"checkbox\" id=\"include-numbers\" checked>\n            <label for=\"include-numbers\">Rakamlar (0-9)</label>\n        </div>\n        <div class=\"checkbox-group\">\n            <input type=\"checkbox\" id=\"include-symbols\">\n            <label for=\"include-symbols\">Semboller (!@#$)</label>\n        </div>\n\n        <button id=\"generate-button\">Şifre Oluştur</button>\n\n        <div id=\"password-output\">Şifreniz Buraya Gelecek</div>\n\n        <div class=\"input-group\" style=\"margin-top: 25px;\">\n            <label for=\"check-password\">Şifre Gücünü Kontrol Et:</label>\n            <input type=\"text\" id=\"check-password\" placeholder=\"Şifrenizi buraya girin...\">\n        </div>\n        \n        <div class=\"progress-bar-container\">\n            <div id=\"strength-indicator\"></div>\n        </div>\n        <div id=\"strength-text\"></div>\n\n    </div>\n\n    <script>\n        document.addEventListener('DOMContentLoaded', () => {\n            const passwordLengthInput = document.getElementById('password-length');\n            const lengthValueSpan = document.getElementById('length-value');\n            const includeUppercase = document.getElementById('include-uppercase');\n            const includeLowercase = document.getElementById('include-lowercase');\n            const includeNumbers = document.getElementById('include-numbers');\n            const includeSymbols = document.getElementById('include-symbols');\n            const generateButton = document.getElementById('generate-button');\n            const passwordOutput = document.getElementById('password-output');\n            const checkPasswordInput = document.getElementById('check-password');\n            const strengthIndicator = document.getElementById('strength-indicator');\n            const strengthText = document.getElementById('strength-text');\n\n            const uppercaseChars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';\n            const lowercaseChars = 'abcdefghijklmnopqrstuvwxyz';\n            const numberChars = '0123456789';\n            const symbolChars = '!@#$%^&*()_+[]{}|;:,.<>?';\n\n            // --- Şifre Oluşturma ---\n            const generatePassword = () => {\n                const length = parseInt(passwordLengthInput.value);\n                let characters = '';\n                let generatedPassword = '';\n\n                if (includeUppercase.checked) characters += uppercaseChars;\n                if (includeLowercase.checked) characters += lowercaseChars;\n                if (includeNumbers.checked) characters += numberChars;\n                if (includeSymbols.checked) characters += symbolChars;\n\n                // En az bir karakter tipi seçilmezse uyarı\n                if (characters.length === 0) {\n                    alert('Lütfen en az bir karakter tipi seçin!');\n                    return '';\n                }\n\n                // Seçilen karakter tiplerinden en az bir tane garantilemek için\n                if (includeUppercase.checked) generatedPassword += uppercaseChars.charAt(Math.floor(Math.random() * uppercaseChars.length));\n                if (includeLowercase.checked) generatedPassword += lowercaseChars.charAt(Math.floor(Math.random() * lowercaseChars.length));\n                if (includeNumbers.checked) generatedPassword += numberChars.charAt(Math.floor(Math.random() * numberChars.length));\n                if (includeSymbols.checked) generatedPassword += symbolChars.charAt(Math.floor(Math.random() * symbolChars.length));\n\n                // Kalan karakterleri rastgele ekle\n                for (let i = generatedPassword.length; i < length; i++) {\n                    generatedPassword += characters.charAt(Math.floor(Math.random() * characters.length));\n                }\n\n                // Şifreyi karıştır (garanti edilen karakterlerin başında olmaması için)\n                generatedPassword = generatedPassword.split('').sort(() => Math.random() - 0.5).join('');\n\n                passwordOutput.textContent = generatedPassword;\n                checkPasswordInput.value = generatedPassword; // Oluşturulan şifreyi kontrol alanına koy\n                checkPasswordStrength(generatedPassword); // Oluşturulan şifrenin gücünü kontrol et\n            };\n\n            // --- Şifre Gücü Kontrolü ---\n            const checkPasswordStrength = (password) => {\n                let strength = 0;\n                const minLength = 8; // Güçlü şifre için minimum uzunluk\n\n                // Uzunluk puanı\n                if (password.length >= minLength) {\n                    strength += 20;\n                    strength += Math.min(20, (password.length - minLength) * 2); // Uzunluk arttıkça ek puan\n                }\n\n                // Büyük harf puanı\n                if (/[A-Z]/.test(password)) strength += 20;\n                // Küçük harf puanı\n                if (/[a-z]/.test(password)) strength += 20;\n                // Rakam puanı\n                if (/[0-9]/.test(password)) strength += 20;\n                // Sembol puanı\n                if (/[!@#$%^&*()_+\\][{}|;:,.<>?]/.test(password)) strength += 20;\n\n                // Max 100 puanı aşmaması için\n                strength = Math.min(100, strength);\n\n                strengthIndicator.style.width = `${strength}%`;\n                strengthIndicator.className = ''; // Mevcut sınıfları temizle\n\n                if (password.length === 0) {\n                    strengthText.textContent = '';\n                    strengthIndicator.style.width = '0%';\n                } else if (strength < 40) {\n                    strengthText.textContent = 'Zayıf';\n                    strengthText.style.color = '#FF4D4D';\n                    strengthIndicator.classList.add('weak');\n                } else if (strength < 70) {\n                    strengthText.textContent = 'Orta';\n                    strengthText.style.color = '#FFC107';\n                    strengthIndicator.classList.add('medium');\n                } else if (strength < 90) {\n                    strengthText.textContent = 'Güçlü';\n                    strengthText.style.color = '#4CAF50';\n                    strengthIndicator.classList.add('strong');\n                } else {\n                    strengthText.textContent = 'Çok Güçlü';\n                    strengthText.style.color = '#007bff';\n                    strengthIndicator.classList.add('very-strong');\n                }\n            };\n\n            // --- Event Dinleyicileri ---\n            passwordLengthInput.addEventListener('input', () => {\n                lengthValueSpan.textContent = passwordLengthInput.value;\n                // Uzunluk değiştikçe şifre alanındaki şifreyi tekrar kontrol et\n                checkPasswordStrength(checkPasswordInput.value);\n            });\n\n            generateButton.addEventListener('click', generatePassword);\n\n            checkPasswordInput.addEventListener('input', (e) => {\n                checkPasswordStrength(e.target.value);\n            });\n\n            // Sayfa yüklendiğinde varsayılan bir şifre oluştur\n            generatePassword();\n        });\n    </script>\n</body>\n</html>\n";
		webview1.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
		webview1.loadData(html, "text/html; charset=utf-8", " UTF-8") ;
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		
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