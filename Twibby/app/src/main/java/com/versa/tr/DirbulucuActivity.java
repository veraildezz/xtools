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
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;

public class DirbulucuActivity extends AppCompatActivity {
	
	private LinearLayout linear1;
	private TextView textview1;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private Button button1;
	private LinearLayout linear4;
	private EditText edittext1;
	private TextView textview2;
	
	private RequestNetwork rqe;
	private RequestNetwork.RequestListener _rqe_request_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.dirbulucu);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		textview1 = findViewById(R.id.textview1);
		linear2 = findViewById(R.id.linear2);
		linear3 = findViewById(R.id.linear3);
		button1 = findViewById(R.id.button1);
		linear4 = findViewById(R.id.linear4);
		edittext1 = findViewById(R.id.edittext1);
		textview2 = findViewById(R.id.textview2);
		rqe = new RequestNetwork(this);
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				// 30 second timeout (default).
				final String urlText = edittext1.getText().toString();
				final String urlList = "https://raw.githubusercontent.com/v0re/dirb/master/wordlists%2Fcommon.txt";
				final int timeoutMillis = 30000; // Tempo limite em milissegundos (30 segundos)
				
				new Thread(new Runnable() {
					    @Override
					    public void run() {
						        try {
							            java.net.URL url = new java.net.URL(urlList);
							            java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
							            conn.setRequestMethod("GET");
							            conn.setConnectTimeout(timeoutMillis); // Define o tempo limite de conexão
							
							            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
							            List<String> directoryList = new ArrayList<>();
							            String line;
							            while ((line = reader.readLine()) != null) {
								                directoryList.add(line);
								            }
							            reader.close();
							
							            final String[] directories = directoryList.toArray(new String[0]);
							
							            final StringBuilder resultBuilder = new StringBuilder();
							
							            runOnUiThread(new Runnable() {
								                @Override
								                public void run() {
									                    textview2.setText(""); // Limpa o TextView antes de começar a adicionar os diretórios testados
									                }
								            });
							
							            for (final String directory : directories) {
								                String finalUrl = urlText + "/" + directory;
								                java.net.URL directoryUrl = new java.net.URL(finalUrl);
								                java.net.HttpURLConnection directoryConn = (java.net.HttpURLConnection) directoryUrl.openConnection();
								                directoryConn.setRequestMethod("HEAD");
								                directoryConn.setConnectTimeout(timeoutMillis); // Define o tempo limite de conexão
								
								                final int responseCode = directoryConn.getResponseCode();
								                if (responseCode == java.net.HttpURLConnection.HTTP_OK) {
									                    final String directoryResult = finalUrl + " (Kod: " + responseCode + ")\n";
									                    runOnUiThread(new Runnable() {
										                        @Override
										                        public void run() {
											                            resultBuilder.append(directoryResult);
											                            textview2.append(directoryResult); // Adiciona o diretório testado ao TextView
											                        }
										                    });
									                }
								            }
							        } catch (final Exception e) {
							            e.printStackTrace();
							            runOnUiThread(new Runnable() {
								                @Override
								                public void run() {
									                    textview2.setText("Erro: " + e.getMessage());
									                }
								            });
							        }
						    }
				}).start();
				// Advanced search without timeout and dynamic
				/*

final String urlText = edittext1.getText().toString();
final String urlList = "https://raw.githubusercontent.com/v0re/dirb/master/wordlists%2Fcommon.txt";

new Thread(new Runnable() {
    @Override
    public void run() {
        try {
            java.net.URL url = new java.net.URL(urlList);
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            List<String> directoryList = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                directoryList.add(line);
            }
            reader.close();

            final String[] directories = directoryList.toArray(new String[0]);

            final StringBuilder resultBuilder = new StringBuilder();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textview1.setText(""); // Limpa o TextView antes de começar a adicionar os diretórios testados
                }
            });

            for (final String directory : directories) {
                String finalUrl = urlText + "/" + directory;
                java.net.URL directoryUrl = new java.net.URL(finalUrl);
                java.net.HttpURLConnection directoryConn = (java.net.HttpURLConnection) directoryUrl.openConnection();
                directoryConn.setRequestMethod("HEAD");

                final int responseCode = directoryConn.getResponseCode();
                final String directoryResult = finalUrl + " -  (Código: " + responseCode + ")\n";

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        resultBuilder.append(directoryResult);
                        textview2.append(directoryResult); // Adiciona o diretório testado ao TextView
                    }
                });
            }
        } catch (final Exception e) {
            e.printStackTrace();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textview1.setText("Erro: " + e.getMessage());
                }
            });
        }
    }
}).start();

*/
			}
		});
		
		_rqe_request_listener = new RequestNetwork.RequestListener() {
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
		_RoundAndBorder(linear2, "#ffffff", 5, "#696BE6", 40);
		_RoundAndBorder(linear3, "#ffffff", 5, "#696BE6", 25);
		_RoundAndBorder(linear4, "#ffffff", 5, "#696BE6", 35);
		_RoundAndBorder(button1, "#696BE6", 0, "#000000", 25);
		edittext1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		button1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
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