package com.versa.tr;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.*;
import android.graphics.*;
import android.graphics.Typeface;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.os.Build;
import android.provider.MediaStore;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.firebase.FirebaseApp;
import java.io.*;
import java.io.File;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraAccessException;

public class FlashlightActivity extends AppCompatActivity {
	
	public final int REQ_CD_CAM = 101;
	
	private boolean hasCameraFlash = false;
	private boolean flashLightStatus = false;
	private CameraManager cameraManager;
	private String cameraId = "";
	
	private LinearLayout linear1;
	private TextView textview1;
	private LinearLayout linear6;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private ImageView imageview1;
	
	private Intent cam = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	private File _file_cam;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.flashlight);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
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
		linear6 = findViewById(R.id.linear6);
		linear2 = findViewById(R.id.linear2);
		linear3 = findViewById(R.id.linear3);
		imageview1 = findViewById(R.id.imageview1);
		_file_cam = FileUtil.createNewPictureFile(getApplicationContext());
		Uri _uri_cam;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			_uri_cam = FileProvider.getUriForFile(getApplicationContext(), getApplicationContext().getPackageName() + ".provider", _file_cam);
		} else {
			_uri_cam = Uri.fromFile(_file_cam);
		}
		cam.putExtra(MediaStore.EXTRA_OUTPUT, _uri_cam);
		cam.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (hasCameraFlash) {
					if (flashLightStatus) {
						_flashLightOff();
					}
					else {
						_flashLightOn();
					}
				}
				else {
					
				}
			}
		});
	}
	
	private void initializeLogic() {
		
		// Check if the device has a camera flash feature
		hasCameraFlash = getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
		
		// Get the CameraManager system service, used to access camera devices and control torch mode
		cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
		
		try {
			    // Get the ID of the first available camera (usually the rear-facing one)
			    cameraId = cameraManager.getCameraIdList()[0];
		} catch (CameraAccessException e) {
			    // Handle exception if the camera cannot be accessed
			    e.printStackTrace();
		}
		
		// Create a TorchCallback to listen for changes in the torch (flashlight) mode
		CameraManager.TorchCallback torchCallback = new CameraManager.TorchCallback() {
			    
			    @Override
			    public void onTorchModeChanged(String cameraId, boolean enabled) {
				        super.onTorchModeChanged(cameraId, enabled);
				
				        // Update your flashlight status variable based on the system callback
				        flashLightStatus = enabled;
				
				        // Change the icon in the UI based on the flashlight state
				        if (enabled) {
					            imageview1.setImageResource(R.drawable.ic_flash_on_black);  // Flash ON icon
					        } else {
					            imageview1.setImageResource(R.drawable.ic_flash_off_black); // Flash OFF icon
					        }
				    }
			
			    @Override
			    public void onTorchModeUnavailable(String cameraId) {
				        super.onTorchModeUnavailable(cameraId);
				
				        // Optional: handle the case where the torch becomes unavailable (e.g., in use by another app)
				        // You can show a toast or disable the flash toggle button here
				    }
		};
		
		// Register the TorchCallback with the CameraManager
		// The second parameter (null) means the callback will be run on the main thread
		cameraManager.registerTorchCallback(torchCallback, null);
		
		
		
		_RoundAndBorder(linear2, "#696BE6", 5, "#ffffff", 30);
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 1);
	}
	
	public void _flashLightOn() {
		
		try {
			cameraManager.setTorchMode(cameraId, true);
		} catch (CameraAccessException e) { e.printStackTrace();
		}
		
	}
	
	
	public void _flashLightOff() {
		
		try {
			cameraManager.setTorchMode(cameraId, false);
		} catch (CameraAccessException e) { e.printStackTrace();
		}
		
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