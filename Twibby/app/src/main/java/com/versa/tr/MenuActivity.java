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
import android.widget.HorizontalScrollView;
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

public class MenuActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private boolean miss_you_error_404 = false;
	
	private LinearLayout linear134;
	private LinearLayout bg;
	private LinearLayout linear54;
	private LinearLayout linear58;
	private LinearLayout linear7;
	private LinearLayout linear60;
	private HorizontalScrollView hscroll1;
	private LinearLayout linear71;
	private ScrollView vscroll1;
	private TextView textview16;
	private TextView textview71;
	private TextView textview17;
	private LinearLayout linear61;
	private LinearLayout pizaa;
	private LinearLayout linear64;
	private LinearLayout burger;
	private LinearLayout linear66;
	private LinearLayout Dessert;
	private LinearLayout linear68;
	private LinearLayout Drink;
	private ImageView imageview15;
	private TextView textview19;
	private ImageView imageview17;
	private TextView textview21;
	private ImageView imageview18;
	private TextView textview22;
	private ImageView imageview19;
	private TextView textview23;
	private TextView textview25;
	private LinearLayout linear72;
	private LinearLayout pizaa1;
	private LinearLayout linear76;
	private LinearLayout pizza2;
	private LinearLayout linear79;
	private LinearLayout pizza3;
	private LinearLayout linear89;
	private LinearLayout hatirlatici;
	private LinearLayout linear90;
	private LinearLayout viewsorc;
	private LinearLayout linear93;
	private LinearLayout mp3;
	private LinearLayout linear96;
	private LinearLayout uygvers;
	private LinearLayout linear100;
	private LinearLayout yazipdf;
	private LinearLayout linear105;
	private LinearLayout resimpdf;
	private LinearLayout linear108;
	private LinearLayout yazitkrar;
	private LinearLayout linear111;
	private LinearLayout ip;
	private LinearLayout linear114;
	private LinearLayout dirbulucu;
	private LinearLayout linear117;
	private LinearLayout qrkod;
	private LinearLayout linear120;
	private LinearLayout sdkcihaz;
	private LinearLayout linear128;
	private LinearLayout yazibinary;
	private LinearLayout linear131;
	private LinearLayout tgbot;
	private LinearLayout linear138;
	private LinearLayout linear139;
	private ImageView imageview20;
	private LinearLayout linear75;
	private TextView textview26;
	private TextView textview29;
	private ImageView imageview21;
	private LinearLayout linear78;
	private TextView textview30;
	private TextView textview31;
	private ImageView imageview22;
	private LinearLayout linear81;
	private TextView textview33;
	private TextView textview34;
	private ImageView imageview25;
	private LinearLayout linear87;
	private TextView textview40;
	private TextView textview41;
	private ImageView imageview26;
	private LinearLayout linear92;
	private TextView textview42;
	private TextView textview43;
	private ImageView imageview27;
	private LinearLayout linear95;
	private TextView textview44;
	private TextView textview45;
	private ImageView imageview28;
	private LinearLayout linear98;
	private TextView textview46;
	private TextView textview47;
	private ImageView imageview29;
	private LinearLayout linear102;
	private TextView textview48;
	private TextView textview49;
	private ImageView imageview30;
	private LinearLayout linear107;
	private TextView textview50;
	private TextView textview51;
	private ImageView imageview31;
	private LinearLayout linear110;
	private TextView textview52;
	private TextView textview53;
	private ImageView imageview32;
	private LinearLayout linear113;
	private TextView textview54;
	private TextView textview55;
	private ImageView imageview33;
	private LinearLayout linear116;
	private TextView textview56;
	private TextView textview57;
	private ImageView imageview34;
	private LinearLayout linear119;
	private TextView textview58;
	private TextView textview59;
	private ImageView imageview37;
	private LinearLayout linear127;
	private TextView textview64;
	private TextView textview65;
	private ImageView imageview38;
	private LinearLayout linear130;
	private TextView textview66;
	private TextView textview67;
	private ImageView imageview39;
	private LinearLayout linear133;
	private TextView textview68;
	private TextView textview69;
	private ImageView imageview41;
	private LinearLayout linear140;
	private TextView textview74;
	private TextView textview75;
	
	private Intent i = new Intent();
	private TimerTask t;
	private DatabaseReference fdb = _firebase.getReference("notify/");
	private ChildEventListener _fdb_child_listener;
	private SharedPreferences sp;
	private AlertDialog.Builder d;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.menu);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear134 = findViewById(R.id.linear134);
		bg = findViewById(R.id.bg);
		linear54 = findViewById(R.id.linear54);
		linear58 = findViewById(R.id.linear58);
		linear7 = findViewById(R.id.linear7);
		linear60 = findViewById(R.id.linear60);
		hscroll1 = findViewById(R.id.hscroll1);
		linear71 = findViewById(R.id.linear71);
		vscroll1 = findViewById(R.id.vscroll1);
		textview16 = findViewById(R.id.textview16);
		textview71 = findViewById(R.id.textview71);
		textview17 = findViewById(R.id.textview17);
		linear61 = findViewById(R.id.linear61);
		pizaa = findViewById(R.id.pizaa);
		linear64 = findViewById(R.id.linear64);
		burger = findViewById(R.id.burger);
		linear66 = findViewById(R.id.linear66);
		Dessert = findViewById(R.id.Dessert);
		linear68 = findViewById(R.id.linear68);
		Drink = findViewById(R.id.Drink);
		imageview15 = findViewById(R.id.imageview15);
		textview19 = findViewById(R.id.textview19);
		imageview17 = findViewById(R.id.imageview17);
		textview21 = findViewById(R.id.textview21);
		imageview18 = findViewById(R.id.imageview18);
		textview22 = findViewById(R.id.textview22);
		imageview19 = findViewById(R.id.imageview19);
		textview23 = findViewById(R.id.textview23);
		textview25 = findViewById(R.id.textview25);
		linear72 = findViewById(R.id.linear72);
		pizaa1 = findViewById(R.id.pizaa1);
		linear76 = findViewById(R.id.linear76);
		pizza2 = findViewById(R.id.pizza2);
		linear79 = findViewById(R.id.linear79);
		pizza3 = findViewById(R.id.pizza3);
		linear89 = findViewById(R.id.linear89);
		hatirlatici = findViewById(R.id.hatirlatici);
		linear90 = findViewById(R.id.linear90);
		viewsorc = findViewById(R.id.viewsorc);
		linear93 = findViewById(R.id.linear93);
		mp3 = findViewById(R.id.mp3);
		linear96 = findViewById(R.id.linear96);
		uygvers = findViewById(R.id.uygvers);
		linear100 = findViewById(R.id.linear100);
		yazipdf = findViewById(R.id.yazipdf);
		linear105 = findViewById(R.id.linear105);
		resimpdf = findViewById(R.id.resimpdf);
		linear108 = findViewById(R.id.linear108);
		yazitkrar = findViewById(R.id.yazitkrar);
		linear111 = findViewById(R.id.linear111);
		ip = findViewById(R.id.ip);
		linear114 = findViewById(R.id.linear114);
		dirbulucu = findViewById(R.id.dirbulucu);
		linear117 = findViewById(R.id.linear117);
		qrkod = findViewById(R.id.qrkod);
		linear120 = findViewById(R.id.linear120);
		sdkcihaz = findViewById(R.id.sdkcihaz);
		linear128 = findViewById(R.id.linear128);
		yazibinary = findViewById(R.id.yazibinary);
		linear131 = findViewById(R.id.linear131);
		tgbot = findViewById(R.id.tgbot);
		linear138 = findViewById(R.id.linear138);
		linear139 = findViewById(R.id.linear139);
		imageview20 = findViewById(R.id.imageview20);
		linear75 = findViewById(R.id.linear75);
		textview26 = findViewById(R.id.textview26);
		textview29 = findViewById(R.id.textview29);
		imageview21 = findViewById(R.id.imageview21);
		linear78 = findViewById(R.id.linear78);
		textview30 = findViewById(R.id.textview30);
		textview31 = findViewById(R.id.textview31);
		imageview22 = findViewById(R.id.imageview22);
		linear81 = findViewById(R.id.linear81);
		textview33 = findViewById(R.id.textview33);
		textview34 = findViewById(R.id.textview34);
		imageview25 = findViewById(R.id.imageview25);
		linear87 = findViewById(R.id.linear87);
		textview40 = findViewById(R.id.textview40);
		textview41 = findViewById(R.id.textview41);
		imageview26 = findViewById(R.id.imageview26);
		linear92 = findViewById(R.id.linear92);
		textview42 = findViewById(R.id.textview42);
		textview43 = findViewById(R.id.textview43);
		imageview27 = findViewById(R.id.imageview27);
		linear95 = findViewById(R.id.linear95);
		textview44 = findViewById(R.id.textview44);
		textview45 = findViewById(R.id.textview45);
		imageview28 = findViewById(R.id.imageview28);
		linear98 = findViewById(R.id.linear98);
		textview46 = findViewById(R.id.textview46);
		textview47 = findViewById(R.id.textview47);
		imageview29 = findViewById(R.id.imageview29);
		linear102 = findViewById(R.id.linear102);
		textview48 = findViewById(R.id.textview48);
		textview49 = findViewById(R.id.textview49);
		imageview30 = findViewById(R.id.imageview30);
		linear107 = findViewById(R.id.linear107);
		textview50 = findViewById(R.id.textview50);
		textview51 = findViewById(R.id.textview51);
		imageview31 = findViewById(R.id.imageview31);
		linear110 = findViewById(R.id.linear110);
		textview52 = findViewById(R.id.textview52);
		textview53 = findViewById(R.id.textview53);
		imageview32 = findViewById(R.id.imageview32);
		linear113 = findViewById(R.id.linear113);
		textview54 = findViewById(R.id.textview54);
		textview55 = findViewById(R.id.textview55);
		imageview33 = findViewById(R.id.imageview33);
		linear116 = findViewById(R.id.linear116);
		textview56 = findViewById(R.id.textview56);
		textview57 = findViewById(R.id.textview57);
		imageview34 = findViewById(R.id.imageview34);
		linear119 = findViewById(R.id.linear119);
		textview58 = findViewById(R.id.textview58);
		textview59 = findViewById(R.id.textview59);
		imageview37 = findViewById(R.id.imageview37);
		linear127 = findViewById(R.id.linear127);
		textview64 = findViewById(R.id.textview64);
		textview65 = findViewById(R.id.textview65);
		imageview38 = findViewById(R.id.imageview38);
		linear130 = findViewById(R.id.linear130);
		textview66 = findViewById(R.id.textview66);
		textview67 = findViewById(R.id.textview67);
		imageview39 = findViewById(R.id.imageview39);
		linear133 = findViewById(R.id.linear133);
		textview68 = findViewById(R.id.textview68);
		textview69 = findViewById(R.id.textview69);
		imageview41 = findViewById(R.id.imageview41);
		linear140 = findViewById(R.id.linear140);
		textview74 = findViewById(R.id.textview74);
		textview75 = findViewById(R.id.textview75);
		sp = getSharedPreferences("sp", Activity.MODE_PRIVATE);
		d = new AlertDialog.Builder(this);
		
		textview71.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		pizaa.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), EncryptdecryptActivity.class);
				startActivity(i);
			}
		});
		
		burger.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), SeskaydetmeActivity.class);
				startActivity(i);
			}
		});
		
		Dessert.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), HexdexActivity.class);
				startActivity(i);
			}
		});
		
		Drink.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), QrkodActivity.class);
				startActivity(i);
			}
		});
		
		pizaa1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), FlashlightActivity.class);
				startActivity(i);
			}
		});
		
		pizza2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), SesyaziActivity.class);
				startActivity(i);
			}
		});
		
		pizza3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), YazisesActivity.class);
				startActivity(i);
			}
		});
		
		hatirlatici.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), HatirlaticiActivity.class);
				startActivity(i);
			}
		});
		
		viewsorc.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), ViewsourceActivity.class);
				startActivity(i);
			}
		});
		
		mp3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), Mp3Activity.class);
				startActivity(i);
			}
		});
		
		uygvers.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), UygversActivity.class);
				startActivity(i);
			}
		});
		
		yazipdf.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), YazipdfActivity.class);
				startActivity(i);
			}
		});
		
		resimpdf.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), ResimpdfActivity.class);
				startActivity(i);
			}
		});
		
		yazitkrar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), PdfokuyucuActivity.class);
				startActivity(i);
			}
		});
		
		ip.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), IpsorguActivity.class);
				startActivity(i);
			}
		});
		
		dirbulucu.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), DirbulucuActivity.class);
				startActivity(i);
			}
		});
		
		qrkod.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), LocahostActivity.class);
				startActivity(i);
			}
		});
		
		sdkcihaz.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), SdkvecihazActivity.class);
				startActivity(i);
			}
		});
		
		yazibinary.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), YazibinaryActivity.class);
				startActivity(i);
			}
		});
		
		tgbot.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), TgbotActivity.class);
				startActivity(i);
			}
		});
		
		linear139.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), JavalodlariActivity.class);
				startActivity(i);
			}
		});
		
		_fdb_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (sp.getString(_childKey, "").contains("true")) {
					
				}
				else {
					d.setTitle(_childValue.get("başlık").toString());
					d.setMessage(_childValue.get("içerik").toString());
					d.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							sp.edit().putString(_childKey, "true").commit();
						}
					});
					d.create().show();
				}
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
	}
	
	private void initializeLogic() {
		_RoundAndBorder(pizaa, "#ffffff", 0, "#ffffff", 60);
		_RoundAndBorder(burger, "#ffffff", 0, "#ffffff", 60);
		_RoundAndBorder(Dessert, "#ffffff", 0, "#ffffff", 60);
		_RoundAndBorder(Drink, "#ffffff", 0, "#ffffff", 60);
		_RoundAndBorder(pizza3, "#ffffff", 0, "#ffffff", 60);
		_RoundAndBorder(pizza2, "#ffffff", 0, "#ffffff", 60);
		_RoundAndBorder(pizaa1, "#ffffff", 0, "#ffffff", 60);
		_RoundAndBorder(hatirlatici, "#ffffff", 0, "#ffffff", 60);
		_RoundAndBorder(dirbulucu, "#ffffff", 0, "#ffffff", 60);
		_RoundAndBorder(viewsorc, "#ffffff", 0, "#ffffff", 60);
		_RoundAndBorder(mp3, "#ffffff", 0, "#ffffff", 60);
		_RoundAndBorder(uygvers, "#ffffff", 0, "#ffffff", 60);
		_RoundAndBorder(yazipdf, "#ffffff", 0, "#ffffff", 60);
		_RoundAndBorder(resimpdf, "#ffffff", 0, "#ffffff", 60);
		_RoundAndBorder(yazitkrar, "#ffffff", 0, "#ffffff", 60);
		_RoundAndBorder(ip, "#ffffff", 0, "#ffffff", 60);
		_RoundAndBorder(qrkod, "#ffffff", 0, "#ffffff", 60);
		_RoundAndBorder(sdkcihaz, "#ffffff", 0, "#ffffff", 60);
		_RoundAndBorder(yazibinary, "#ffffff", 0, "#ffffff", 60);
		_RoundAndBorder(tgbot, "#ffffff", 0, "#ffffff", 60);
		_RoundAndBorder(linear139, "#ffffff", 0, "#ffffff", 60);
		textview16.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview71.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview17.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview19.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview21.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview22.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview23.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview25.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview26.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview29.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview30.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview31.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview33.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview34.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview40.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview41.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview42.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview43.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview44.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview45.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview46.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview47.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview48.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview49.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview50.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview51.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview52.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview53.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview54.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview55.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview56.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview57.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview58.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview59.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview64.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview65.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview66.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview67.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview68.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview69.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview74.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
		textview75.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
	}
	
	public void _RoundAndBorder(final View _view, final String _color1, final double _border, final String _color2, final double _round) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		gd.setColor(Color.parseColor(_color1));
		gd.setCornerRadius((int) _round);
		gd.setStroke((int) _border, Color.parseColor(_color2));
		_view.setBackground(gd);
	}
	
	
	public void _shape(final double _top1, final double _top2, final double _bottom2, final double _bottom1, final String _inside_color, final String _side_color, final double _side_size, final View _view) {
		Double tlr = _top1;
		Double trr = _top2;
		Double blr = _bottom2;
		Double brr = _bottom1;
		Double sw = _side_size;
		android.graphics.drawable.GradientDrawable s = new android.graphics.drawable.GradientDrawable();
		s.setShape(android.graphics.drawable.GradientDrawable.RECTANGLE);
		s.setCornerRadii(new float[] {tlr.floatValue(),tlr.floatValue(), trr.floatValue(),trr.floatValue(), blr.floatValue(),blr.floatValue(), brr.floatValue(),brr.floatValue()}); 
		
		s.setColor(Color.parseColor(_inside_color));
		s.setStroke(sw.intValue(), Color.parseColor(_side_color));
		_view.setBackground(s);
	}
	
	
	public void _bgbgbg() {
		
		
		
		
		
		
		
		
	}
	
	
	public void _UI_GradientLR(final View _view, final String _left, final String _right, final double _lt, final double _rt, final double _lb, final double _rb, final double _str, final String _str_color, final double _ele, final String _ripple) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		int clrs[] = new int[]{
			Color.parseColor(_left), Color.parseColor(_right)
		};
		gd.setColors(clrs);
		gd.setOrientation(android.graphics.drawable.GradientDrawable.Orientation.TL_BR);
		gd.setStroke((int)_str, Color.parseColor(_str_color));
		gd.setCornerRadii(new float[] {(float)_lt, (float)_lt, (float)_rt, (float)_rt, (float)_rb, (float)_rb, (float)_lb, (float)_lb});
		_view.setElevation((int)_ele);
		android.content.res.ColorStateList clrbs = new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{Color.parseColor(_ripple)});
		android.graphics.drawable.RippleDrawable ripdrb = new android.graphics.drawable.RippleDrawable(clrbs , gd, null);
		_view.setBackground(ripdrb);
	}
	
	
	public void _Animator(final View _view, final String _propertyName, final double _value, final double _duration) {
		ObjectAnimator anim = new ObjectAnimator();
		anim.setTarget(_view);
		anim.setPropertyName(_propertyName);
		anim.setFloatValues((float)_value);
		anim.setDuration((long)_duration);
		anim.setInterpolator(new android.view.animation.AccelerateDecelerateInterpolator());
		anim.start();
	}
	
	
	public void _TransitionManager(final View _view, final double _duration) {
		LinearLayout viewgroup =(LinearLayout) _view;
		
		android.transition.AutoTransition autoTransition = new android.transition.AutoTransition(); autoTransition.setDuration((long)_duration); android.transition.TransitionManager.beginDelayedTransition(viewgroup, autoTransition);
	}
	
	
	public void _setHeight(final View _view, final double _heightValue, final double _widthValue) {
		_view.getLayoutParams().height = (int)_heightValue;
		_view.requestLayout();
		_view.getLayoutParams().width = (int)_widthValue;
		_view.requestLayout();
	}
	
	
	public void _FadeOut(final View _view, final double _duration) {
		_Animator(_view, "scaleX", 0, 200);
		_Animator(_view, "scaleY", 0, 200);
		t = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						_Animator(_view, "scaleX", 1, 200);
						_Animator(_view, "scaleY", 1, 200);
					}
				});
			}
		};
		_timer.schedule(t, (int)(_duration));
	}
	
	
	public void _ICC(final ImageView _img, final String _c1, final String _c2) {
		_img.setImageTintList(new android.content.res.ColorStateList(new int[][] {{-android.R.attr.state_pressed},{android.R.attr.state_pressed}},new int[]{Color.parseColor(_c1), Color.parseColor(_c2)}));
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