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
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.widget.LinearLayout;
import java.util.ArrayList;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.Intent;
import android.net.Uri;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.AdapterView;
import android.webkit.WebViewClient;

public class HomeActivity extends AppCompatActivity {
	
	
	private Toolbar _toolbar;
	private FloatingActionButton _fab;
	private DrawerLayout _drawer;
	private String name = "";
	private String path = "";
	private String filename = "";
	
	private ArrayList<String> mode = new ArrayList<>();
	
	private LinearLayout go_linear;
	private WebView webview1;
	private LinearLayout linear2;
	private Spinner spinner;
	private EditText type;
	private Button gobutton;
	private HorizontalScrollView hscroll1;
	private LinearLayout linear1;
	private ImageView arrow_back;
	private ImageView arrow_forward;
	private ImageView reload;
	private ImageView bookmark;
	private LinearLayout _drawer_user_linear;
	private Button _drawer_button29;
	private Button _drawer_button145;
	private Button _drawer_button144;
	private LinearLayout _drawer_linear1;
	private ImageView _drawer_imageview1;
	private TextView _drawer_textview2;
	
	private AlertDialog.Builder exit;
	private AlertDialog.Builder asktype;
	private SharedPreferences lasturl;
	private Intent c = new Intent();
	private SharedPreferences History;
	private TextToSpeech tts;
	private SharedPreferences types;
	private SharedPreferences ui;
	private Intent adddddd = new Intent();
	private AlertDialog.Builder stt;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.home);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		_toolbar = (Toolbar) findViewById(R.id._toolbar);
		setSupportActionBar(_toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) {
				onBackPressed();
			}
		});
		_fab = (FloatingActionButton) findViewById(R.id._fab);
		
		_drawer = (DrawerLayout) findViewById(R.id._drawer);ActionBarDrawerToggle _toggle = new ActionBarDrawerToggle(HomeActivity.this, _drawer, _toolbar, R.string.app_name, R.string.app_name);
		_drawer.addDrawerListener(_toggle);
		_toggle.syncState();
		
		LinearLayout _nav_view = (LinearLayout) findViewById(R.id._nav_view);
		
		go_linear = (LinearLayout) findViewById(R.id.go_linear);
		webview1 = (WebView) findViewById(R.id.webview1);
		webview1.getSettings().setJavaScriptEnabled(true);
		webview1.getSettings().setSupportZoom(true);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		spinner = (Spinner) findViewById(R.id.spinner);
		type = (EditText) findViewById(R.id.type);
		gobutton = (Button) findViewById(R.id.gobutton);
		hscroll1 = (HorizontalScrollView) findViewById(R.id.hscroll1);
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		arrow_back = (ImageView) findViewById(R.id.arrow_back);
		arrow_forward = (ImageView) findViewById(R.id.arrow_forward);
		reload = (ImageView) findViewById(R.id.reload);
		bookmark = (ImageView) findViewById(R.id.bookmark);
		_drawer_user_linear = (LinearLayout) _nav_view.findViewById(R.id.user_linear);
		_drawer_button29 = (Button) _nav_view.findViewById(R.id.button29);
		_drawer_button145 = (Button) _nav_view.findViewById(R.id.button145);
		_drawer_button144 = (Button) _nav_view.findViewById(R.id.button144);
		_drawer_linear1 = (LinearLayout) _nav_view.findViewById(R.id.linear1);
		_drawer_imageview1 = (ImageView) _nav_view.findViewById(R.id.imageview1);
		_drawer_textview2 = (TextView) _nav_view.findViewById(R.id.textview2);
		exit = new AlertDialog.Builder(this);
		asktype = new AlertDialog.Builder(this);
		lasturl = getSharedPreferences("lasturl", Activity.MODE_PRIVATE);
		History = getSharedPreferences("History", Activity.MODE_PRIVATE);
		tts = new TextToSpeech(getApplicationContext(), null);
		types = getSharedPreferences("types", Activity.MODE_PRIVATE);
		ui = getSharedPreferences("UI.ui", Activity.MODE_PRIVATE);
		stt = new AlertDialog.Builder(this);
		
		webview1.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageStarted(WebView _param1, String _param2, Bitmap _param3) {
				final String _url = _param2;
				setTitle("Loading...");
				super.onPageStarted(_param1, _param2, _param3);
			}
			
			@Override
			public void onPageFinished(WebView _param1, String _param2) {
				final String _url = _param2;
				setTitle("MAlone");
				super.onPageFinished(_param1, _param2);
			}
		});
		
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				if (mode.get((int)(_position)).equals("Link")) {
					types.edit().putString("type", "0").commit();
				}
				else {
					if (mode.get((int)(_position)).equals("Google Search")) {
						types.edit().putString("type", "1").commit();
					}
					else {
						if (mode.get((int)(_position)).equals("TTS")) {
							types.edit().putString("type", "2").commit();
						}
						else {
							if (mode.get((int)(_position)).equals("Yahoo Search")) {
								types.edit().putString("type", "3").commit();
							}
							else {
								if (mode.get((int)(_position)).equals("DuckDuckGo Search")) {
									types.edit().putString("type", "4").commit();
								}
								else {
									if (mode.get((int)(_position)).equals("JavaScript")) {
										types.edit().putString("type", "6").commit();
									}
									else {
										
									}
								}
							}
						}
					}
				}
				if (mode.get((int)(_position)).equals("STT")) {
					type.setVisibility(View.INVISIBLE);
				}
				else {
					type.setVisibility(View.VISIBLE);
				}
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> _param1) {
				
			}
		});
		
		gobutton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (types.getString("type", "").equals("0")) {
					if (!type.getText().toString().contains("http://") || !type.getText().toString().contains("https://")) {
						if (SketchwareUtil.getRandom((int)(1), (int)(2)) == 1) {
							webview1.loadUrl("http://".concat(type.getText().toString()));
						}
						else {
							webview1.loadUrl("https://".concat(type.getText().toString()));
						}
					}
					else {
						webview1.loadUrl(type.getText().toString());
					}
				}
				else {
					if (types.getString("type", "").equals("1")) {
						webview1.loadUrl("https://www.google.com/search?=".concat(type.getText().toString()));
					}
					else {
						if (types.getString("type", "").equals("2")) {
							if (tts.isSpeaking()) {
								tts.stop();
							}
							else {
								tts.speak(type.getText().toString(), TextToSpeech.QUEUE_ADD, null);
							}
						}
						else {
							if (type.getText().toString().equals("")) {
								type.setError("Please type something!");
							}
							else {
								if (types.getString("type", "").equals("3")) {
									webview1.loadUrl("https://hk.search.yahoo.com/search?p=".concat(type.getText().toString()));
								}
								else {
									if (types.getString("type", "").equals("4")) {
										webview1.loadUrl("https://duckduckgo.com/?q=".concat(type.getText().toString()));
									}
									else {
										if (types.getString("type", "").equals("6")) {
											_load_javascript(type.getText().toString());
										}
										else {
											
										}
									}
								}
							}
						}
					}
				}
				type.setText("");
			}
		});
		
		arrow_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (webview1.canGoBack()) {
					webview1.goBack();
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), "There is no page for you to go back!");
				}
			}
		});
		
		arrow_forward.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (webview1.canGoForward()) {
					webview1.goForward();
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), "There is no page for you to go forward!");
				}
			}
		});
		
		reload.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				webview1.loadUrl(webview1.getUrl());
			}
		});
		
		bookmark.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				c.setClass(getApplicationContext(), BookmarkActivity.class);
				c.putExtra("site_view", webview1.getUrl());
				startActivity(c);
			}
		});
		
		_fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (ui.getString("ui", "").equals("0")) {
					ui.edit().putString("ui", "1").commit();
					linear1.setVisibility(View.GONE);
					linear2.setVisibility(View.GONE);
					
				}
				else {
					ui.edit().putString("ui", "0").commit();
					linear1.setVisibility(View.VISIBLE);
					linear2.setVisibility(View.VISIBLE);
					
				}
			}
		});
		
		_drawer_button29.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				c.setClass(getApplicationContext(), CreditsActivity.class);
				startActivity(c);
			}
		});
		
		_drawer_button145.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				asktype.setTitle("ChangeLog");
				asktype.setMessage("v0.0.1.3.stable2 | 2019-07-19 13:26 GMT+8\n- Fixed not showing current site view bug [BUG #2]\n- Make start ChangeLog's OK button to Close text");
				asktype.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				asktype.create().show();
			}
		});
		
		_drawer_button144.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				exit.setTitle("Exit?");
				exit.setMessage("Do you wanted to exit?\n[Yes] for exit.\n[No] to enjoy more broswering.");
				exit.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						finish();
					}
				});
				exit.setNegativeButton("No", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				exit.create().show();
			}
		});
	}
	private void initializeLogic() {
		setTitle("MAlone");
		mode.add("Link");
		mode.add("TTS");
		mode.add("JavaScript");
		mode.add("Google Search");
		mode.add("Yahoo Search");
		mode.add("DuckDuckGo Search");
		spinner.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, mode));
		webview1.loadUrl(getIntent().getStringExtra("b"));
		webview1.setDownloadListener(new DownloadListener() {
			public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse(url));
				startActivity (intent);
				
			}
		});
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
	public void onBackPressed() {
		exit.setTitle("Exit?");
		exit.setMessage("Do you wanted to exit?\n[Yes] for exit.\n[No] to enjoy more broswering.");
		exit.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface _dialog, int _which) {
				finish();
			}
		});
		exit.setNegativeButton("No", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface _dialog, int _which) {
				
			}
		});
		exit.create().show();
	}
	private void _load_javascript (final String _script) {
		webview1.loadUrl("javascript:(function(){".concat(_script).concat("})();"));
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
