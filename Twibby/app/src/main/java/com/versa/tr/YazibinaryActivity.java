package com.versa.tr;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.ClipData;
import android.content.ClipboardManager;
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
import android.widget.Button;
import android.widget.EditText;
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
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;

public class YazibinaryActivity extends AppCompatActivity {
	
	private LinearLayout linear1;
	private TextView textview1;
	private LinearLayout linear5;
	private EditText edittext1_text;
	private Button button_generate_text;
	private LinearLayout linear_show_text;
	private ScrollView vscroll1;
	private TextView textview_displaytext;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.yazibinary);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		textview1 = findViewById(R.id.textview1);
		linear5 = findViewById(R.id.linear5);
		edittext1_text = findViewById(R.id.edittext1_text);
		button_generate_text = findViewById(R.id.button_generate_text);
		linear_show_text = findViewById(R.id.linear_show_text);
		vscroll1 = findViewById(R.id.vscroll1);
		textview_displaytext = findViewById(R.id.textview_displaytext);
		
		button_generate_text.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (edittext1_text.getText().toString().equals("")) {
					((EditText)edittext1_text).setError("Yazınızı Girin");
				}
				else {
					String input = edittext1_text.getText().toString().trim(); // Obtém o texto do EditText e remove espaços em branco
					
					// Verifica se o input contém apenas 0s e 1s e espaços
					if (input.matches("[01 ]+")) {
						    String binarioLimpo = input.replace(" ", ""); // Remove quaisquer espaços para verificar se o binário é válido
						    
						    // Verifica se o número de bits é múltiplo de 8 (para ser um binário válido)
						    if (binarioLimpo.length() % 8 == 0) {
							        StringBuilder text = new StringBuilder();
							        
							        // Divide o binário em blocos de 8 bits e converte para caracteres
							        for (int i = 0; i < binarioLimpo.length(); i += 8) {
								            String byteString = binarioLimpo.substring(i, i + 8); // Pega os próximos 8 bits
								            int charCode = Integer.parseInt(byteString, 2); // Converte de binário para decimal
								            text.append((char) charCode); // Converte o decimal para caractere
								        }
							        
							        // Exibe o texto decodificado e define a cor verde
							        textview_displaytext.setText(text.toString());
							        textview_displaytext.setTextColor(Color.parseColor("#00FF00")); // Verde
							    } else {
							        // Se o número de bits não for múltiplo de 8, exibe mensagem de erro
							        textview_displaytext.setText("Invalid binary code: incorrect number of bits.");
							        textview_displaytext.setTextColor(Color.parseColor("#FF0000")); // Vermelho
							    }
					} else {
						    // Se o input não for binário (ou seja, é texto normal), converte o texto para binário
						    StringBuilder binary = new StringBuilder();
						    
						    for (char c : input.toCharArray()) {
							        binary.append(String.format("%8s", Integer.toBinaryString(c)) // Converte cada caractere para binário
							                           .replaceAll(" ", "0")); // Garante que tenha 8 bits
							        binary.append(" "); // Adiciona um espaço entre os binários de cada caractere
							    }
						    
						    // Exibe o código binário e define a cor verde
						    textview_displaytext.setText(binary.toString());
						    textview_displaytext.setTextColor(Color.parseColor("#00FF00")); // Verde
					}
					linear_show_text.setVisibility(View.VISIBLE);
				}
			}
		});
		
		textview_displaytext.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", textview_displaytext.getText().toString()));
				SketchwareUtil.showMessage(getApplicationContext(), "Kopyalandı! ");
			}
		});
	}
	
	private void initializeLogic() {
		_RoundAndBorder(button_generate_text, "#696BE6", 0, "#ffffff", 25);
		_RoundAndBorder(linear_show_text, "#696BE6", 0, "#ffffff", 25);
		_RoundAndBorder(linear5, "#ffffff", 5, "#696BE6", 40);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			    Window window = getWindow();
			    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			
			    // Altera a cor da Status Bar
			    window.setStatusBarColor(Color.parseColor("#FF1F1B24"));
			
			    // Altera a cor da Navigation Bar
			    window.setNavigationBarColor(Color.parseColor("#FF1F1B24"));
		}
		edittext1_text.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)10, 0xFF696BE6));
		android.graphics.drawable.GradientDrawable ZTTGSPHU_SSK = new android.graphics.drawable.GradientDrawable();
		int UYJFIH_SSK[] = new int[]{
			Color.parseColor("#FFE91E63"),
			Color.parseColor("#FF2196F3")};
		ZTTGSPHU_SSK.setColors(UYJFIH_SSK);
		ZTTGSPHU_SSK.setOrientation(android.graphics.drawable.GradientDrawable.Orientation.LEFT_RIGHT);
		ZTTGSPHU_SSK.setCornerRadius(15);
		android.graphics.drawable.RippleDrawable OZPUFB_SSK= new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{
			Color.parseColor("#FFFFFFFF")}), ZTTGSPHU_SSK, null);
		button_generate_text.setBackground(OZPUFB_SSK);
		android.graphics.drawable.GradientDrawable YDVSIIBR_SSK = new android.graphics.drawable.GradientDrawable();
		YDVSIIBR_SSK.setColor(Color.parseColor("#FF1F1B24"));
		YDVSIIBR_SSK.setCornerRadius(15);
		YDVSIIBR_SSK.setStroke(2, Color.parseColor("#FF9E9E9E"));
		linear_show_text.setBackground(YDVSIIBR_SSK);
		linear_show_text.setVisibility(View.GONE);
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		edittext1_text.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		button_generate_text.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview_displaytext.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
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