package Microapps.MAlone;

import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.graphics.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.util.*;
import java.text.*;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.AdRequest;
import android.content.Intent;
import android.net.Uri;
import android.app.Activity;
import android.content.SharedPreferences;
import android.animation.ObjectAnimator;
import android.view.animation.LinearInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;

public class MainActivity extends AppCompatActivity {
	
	
	private LinearLayout start_back_linear;
	private ImageView not_finish_logo_sat;
	private TextView startmalone;
	private Button letsgo;
	private TextView copyright;
	private AdView adview1;
	private TextView ver;
	
	private Intent ToHomeIntent = new Intent();
	private SharedPreferences FirstStart;
	private Intent skip = new Intent();
	private ObjectAnimator hihi = new ObjectAnimator();
	private AlertDialog.Builder change;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		start_back_linear = (LinearLayout) findViewById(R.id.start_back_linear);
		not_finish_logo_sat = (ImageView) findViewById(R.id.not_finish_logo_sat);
		startmalone = (TextView) findViewById(R.id.startmalone);
		letsgo = (Button) findViewById(R.id.letsgo);
		copyright = (TextView) findViewById(R.id.copyright);
		adview1 = (AdView) findViewById(R.id.adview1);
		ver = (TextView) findViewById(R.id.ver);
		FirstStart = getSharedPreferences("FirstStart.save", Activity.MODE_PRIVATE);
		change = new AlertDialog.Builder(this);
		
		not_finish_logo_sat.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				hihi.setTarget(not_finish_logo_sat);
				hihi.setPropertyName("rotation");
				hihi.setFloatValues((float)(0), (float)(360));
				hihi.setDuration((int)(1000));
				hihi.start();
			}
		});
		
		letsgo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_ToHome();
				SketchwareUtil.showMessage(getApplicationContext(), "Welcome to MAlone!");
			}
		});
	}
	private void initializeLogic() {
		setTitle("microapps.MAlone.startScreen");
		adview1.loadAd(new AdRequest.Builder().addTestDevice("6ABCEA954070B5B77E17A06B2AA17C6F")
		.build());
		if (FirstStart.getString("first", "").equals("1")) {
			skip.setClass(getApplicationContext(), HomeActivity.class);
			startActivity(skip);
			finish();
		}
		else {
			FirstStart.edit().putString("first", "1").commit();
		}
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	@Override
	public void onStart() {
		super.onStart();
		change.setTitle("ChangeLog");
		change.setMessage("v0.0.1.3.stable2 | 2019-07-19 13:26 GMT+8\n- Fixed not showing current site view bug [BUG #2]\n- Make start ChangeLog's OK button to Close text");
		change.setPositiveButton("Close", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface _dialog, int _which) {
				
			}
		});
		change.create().show();
	}
	private void _ToHome () {
		ToHomeIntent.setClass(getApplicationContext(), HomeActivity.class);
		ToHomeIntent.putExtra("show", "1");
		startActivity(ToHomeIntent);
		finish();
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
	public float getDip(int _input){
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels(){
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels(){
		return getResources().getDisplayMetrics().heightPixels;
	}
	
}
