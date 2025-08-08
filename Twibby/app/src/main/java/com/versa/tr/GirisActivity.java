package com.versa.tr;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import java.io.*;
import java.io.InputStream;
import java.text.*;
import java.util.*;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;

public class GirisActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private HashMap<String, Object> user = new HashMap<>();
	private boolean b = false;
	
	private LinearLayout linear3;
	private LinearLayout linear19;
	private LinearLayout linear16;
	private LinearLayout linear4;
	private TextView textview1;
	private LinearLayout linear17;
	private LinearLayout linear5;
	private LinearLayout linear6;
	private LinearLayout linear7;
	private LinearLayout linear18;
	private LinearLayout yukleniyor;
	private Button button1;
	private LinearLayout linear9;
	private Button button2;
	private Button button4;
	private Button button3;
	private ImageView imageview2;
	private EditText edittext1;
	private ImageView imageview3;
	private EditText edittext2;
	private ImageView imageview4;
	private EditText edittext3;
	private ImageView imageview5;
	private ProgressBar progressbar1;
	private TextView textview8;
	
	private FirebaseAuth fauth;
	private OnCompleteListener<AuthResult> _fauth_create_user_listener;
	private OnCompleteListener<AuthResult> _fauth_sign_in_listener;
	private OnCompleteListener<Void> _fauth_reset_password_listener;
	private OnCompleteListener<Void> fauth_updateEmailListener;
	private OnCompleteListener<Void> fauth_updatePasswordListener;
	private OnCompleteListener<Void> fauth_emailVerificationSentListener;
	private OnCompleteListener<Void> fauth_deleteUserListener;
	private OnCompleteListener<Void> fauth_updateProfileListener;
	private OnCompleteListener<AuthResult> fauth_phoneAuthListener;
	private OnCompleteListener<AuthResult> fauth_googleSignInListener;
	
	private RequestNetwork rqe;
	private RequestNetwork.RequestListener _rqe_request_listener;
	private SharedPreferences users;
	private AlertDialog.Builder dialog;
	private DatabaseReference fdb = _firebase.getReference("users/");
	private ChildEventListener _fdb_child_listener;
	private TimerTask t;
	private Intent i = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.giris);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear3 = findViewById(R.id.linear3);
		linear19 = findViewById(R.id.linear19);
		linear16 = findViewById(R.id.linear16);
		linear4 = findViewById(R.id.linear4);
		textview1 = findViewById(R.id.textview1);
		linear17 = findViewById(R.id.linear17);
		linear5 = findViewById(R.id.linear5);
		linear6 = findViewById(R.id.linear6);
		linear7 = findViewById(R.id.linear7);
		linear18 = findViewById(R.id.linear18);
		yukleniyor = findViewById(R.id.yukleniyor);
		button1 = findViewById(R.id.button1);
		linear9 = findViewById(R.id.linear9);
		button2 = findViewById(R.id.button2);
		button4 = findViewById(R.id.button4);
		button3 = findViewById(R.id.button3);
		imageview2 = findViewById(R.id.imageview2);
		edittext1 = findViewById(R.id.edittext1);
		imageview3 = findViewById(R.id.imageview3);
		edittext2 = findViewById(R.id.edittext2);
		imageview4 = findViewById(R.id.imageview4);
		edittext3 = findViewById(R.id.edittext3);
		imageview5 = findViewById(R.id.imageview5);
		progressbar1 = findViewById(R.id.progressbar1);
		textview8 = findViewById(R.id.textview8);
		fauth = FirebaseAuth.getInstance();
		rqe = new RequestNetwork(this);
		users = getSharedPreferences("users", Activity.MODE_PRIVATE);
		dialog = new AlertDialog.Builder(this);
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (SketchwareUtil.isConnected(getApplicationContext())) {
					if (edittext2.getText().toString().equals("")) {
						((EditText)edittext2).setError("Lütfen Email Adresi Giriniz");
					}
					else {
						if (edittext3.getText().toString().equals("")) {
							((EditText)edittext3).setError("Lütfen Şifrenizi Giriniz");
						}
						else {
							yukleniyor.setVisibility(View.VISIBLE);
							fauth.signInWithEmailAndPassword(edittext2.getText().toString(), edittext3.getText().toString()).addOnCompleteListener(GirisActivity.this, _fauth_sign_in_listener);
						}
					}
				}
				else {
					yukleniyor.setVisibility(View.GONE);
					SketchwareUtil.showMessage(getApplicationContext(), "Lütfen İnternet Bağlantınızı Kontrol Ediniz");
				}
			}
		});
		
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (SketchwareUtil.isConnected(getApplicationContext())) {
					if (edittext1.getText().toString().equals("")) {
						((EditText)edittext1).setError("Lütfen Kullanıcı Adı Oluşturunuz");
					}
					else {
						if (edittext2.getText().toString().equals("")) {
							((EditText)edittext2).setError("Lütfen Mail Adresinizi Giriniz");
						}
						else {
							if (edittext3.getText().toString().equals("")) {
								((EditText)edittext3).setError("Lütfen Şifrenizi Giriniz");
							}
							else {
								yukleniyor.setVisibility(View.VISIBLE);
								fauth.createUserWithEmailAndPassword(edittext2.getText().toString(), edittext3.getText().toString()).addOnCompleteListener(GirisActivity.this, _fauth_create_user_listener);
							}
						}
					}
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), "Lütfen İnternet Bağlantınızı Kontrol Edip Tekrar Deneyin");
				}
			}
		});
		
		button4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				edittext1.setText("");
				edittext2.setText("");
				edittext3.setText("");
				textview1.setText("Giriş ");
				button1.setVisibility(View.VISIBLE);
				button2.setVisibility(View.GONE);
				linear5.setVisibility(View.GONE);
				yukleniyor.setVisibility(View.GONE);
			}
		});
		
		button3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				edittext1.setText("");
				edittext2.setText("");
				edittext3.setText("");
				textview1.setText("Kayıt Ol");
				button1.setVisibility(View.GONE);
				button2.setVisibility(View.VISIBLE);
				linear5.setVisibility(View.VISIBLE);
				yukleniyor.setVisibility(View.GONE);
				button3.setVisibility(View.GONE);
				button4.setVisibility(View.VISIBLE);
			}
		});
		
		imageview5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (b) {
					edittext3.setTransformationMethod(android.text.method.PasswordTransformationMethod.getInstance());
					b = false;
					imageview5.setImageResource(R.drawable.ic_visibility_off_black);
				}
				else {
					edittext3.setTransformationMethod(android.text.method.HideReturnsTransformationMethod.getInstance());
					b = true;
					imageview5.setImageResource(R.drawable.ic_remove_red_eye_black);
				}
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
				dialog.setTitle("Bağlanılamadı");
				dialog.setMessage("Lütfen İnternetinizi Kontrol Edip Tekrar Deneyiniz.");
				dialog.setCancelable(false);
				dialog.setPositiveButton("Tekrar Dene", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						finish();
					}
				});
				dialog.setNegativeButton("Kapat", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						finish();
					}
				});
				dialog.create().show();
			}
		};
		
		_fdb_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		fdb.addChildEventListener(_fdb_child_listener);
		
		fauth_updateEmailListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		fauth_updatePasswordListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		fauth_emailVerificationSentListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		fauth_deleteUserListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		fauth_phoneAuthListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		fauth_updateProfileListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		fauth_googleSignInListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		_fauth_create_user_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				if (_success) {
					user = new HashMap<>();
					user.put("isim", edittext1.getText().toString());
					user.put("email", FirebaseAuth.getInstance().getCurrentUser().getEmail());
					user.put("sifre", edittext3.getText().toString());
					fdb.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(user);
					user.clear();
					users.edit().putString("isim", edittext1.getText().toString()).commit();
					users.edit().putString("email", FirebaseAuth.getInstance().getCurrentUser().getEmail()).commit();
					users.edit().putString("sifre", edittext3.getText().toString()).commit();
					t = new TimerTask() {
						@Override
						public void run() {
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									edittext1.setText("");
									linear5.setVisibility(View.GONE);
									button1.setVisibility(View.VISIBLE);
									button3.setVisibility(View.VISIBLE);
									button4.setVisibility(View.GONE);
									button2.setVisibility(View.GONE);
									yukleniyor.setVisibility(View.GONE);
								}
							});
						}
					};
					_timer.schedule(t, (int)(2000));
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), _errorMessage);
				}
			}
		};
		
		_fauth_sign_in_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				if (_success) {
					users.edit().putString("email", edittext2.getText().toString()).commit();
					users.edit().putString("şifre", edittext3.getText().toString()).commit();
					fdb.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(user);
					SketchwareUtil.showMessage(getApplicationContext(), "Giriş Başarılı ");
					i.setClass(getApplicationContext(), MenuActivity.class);
					startActivity(i);
					overridePendingTransition(android.
					R.anim.fade_in,
					android.R.anim.fade_out);
					finish();
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), _errorMessage);
				}
			}
		};
		
		_fauth_reset_password_listener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				
			}
		};
	}
	
	private void initializeLogic() {
		_RoundAndBorder(linear5, "#ffffff", 0, "#ffffff", 30);
		_RoundAndBorder(linear6, "#ffffff", 0, "#ffffff", 30);
		_RoundAndBorder(linear7, "#ffffff", 0, "#ffffff", 30);
		_RoundAndBorder(button1, "#696BE6", 0, "#ffffff", 40);
		_RoundAndBorder(button2, "#696BE6", 0, "#ffffff", 40);
		_RoundAndBorder(button3, "#696BE6", 0, "#ffffff", 35);
		_RoundAndBorder(button4, "#696BE6", 0, "#ffffff", 35);
		_RoundAndBorder(linear16, "#ffffff", 0, "#ffffff", 40);
		button4.setVisibility(View.GONE);
		linear5.setVisibility(View.GONE);
		yukleniyor.setVisibility(View.GONE);
		button2.setVisibility(View.GONE);
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview8.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		button1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		button2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		button3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		button4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		edittext1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		edittext2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		edittext3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
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