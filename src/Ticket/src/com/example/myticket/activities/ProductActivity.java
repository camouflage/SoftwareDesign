package com.example.myticket.activities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.R.integer;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.myticket.R;
import com.example.myticket.activities.ContentFragment.OnFragmentInteractionListener;
import com.example.myticket.db.DataBaseHelper;
import com.example.myticket.db.MyApplication;
import com.example.myticket.entities.Cinema;
import com.example.myticket.entities.Location;
import com.example.myticket.entities.Movie;
import com.example.myticket.entities.ProductDescription;
import com.example.myticket.entities.ScreeningRoom;

public class ProductActivity extends Activity implements OnFragmentInteractionListener{
	private SimpleAdapter simpleAdapter;
	private List<Map<String, Object>> _listItems0, _listItems1, _listItems2;
	private ContentFragment[] fragments = new ContentFragment[3];
	//private ListView _pd_lv;
	private MyApplication _application;
	private DataBaseHelper dbHandler;
	private Movie movie;
	private ImageView _back;
	private TextView[] _tvs = new TextView[3];
	private ArrayList<ProductDescription> _products;
	private int _currency_fragment;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product);
		getData();
		initFragment();
		initView();
		initEvent();
	}
	private void initFragment() {
		fragments[0] = new ContentFragment(_listItems0);
		fragments[1] = new ContentFragment(_listItems1);
		fragments[2] = new ContentFragment(_listItems2);
		FragmentManager fm = getFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		transaction.add(R.id.product_content, fragments[0]);
		transaction.add(R.id.product_content, fragments[1]);
		transaction.add(R.id.product_content, fragments[2]);
		transaction.show(fragments[0]);
		transaction.hide(fragments[1]);
		transaction.hide(fragments[2]);
		transaction.commit();
		_currency_fragment = 0;
	}
	private void replaceFragment(int index) {
		FragmentManager fm = getFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		transaction.hide(fragments[_currency_fragment]);
		transaction.show(fragments[index]);
		transaction.commit();
		_currency_fragment = index;
	}
	private void getData() {
		_application = (MyApplication)getApplication();
		dbHandler = DataBaseHelper.getInstance(this);
		movie = (Movie)getIntent().getSerializableExtra("movie");
		if (movie == null) return;
		_products = dbHandler.queryProductDescriptionsByMovie(movie.getMovie_id());
		_listItems0 = new ArrayList<Map<String,Object>>();
		_listItems1 = new ArrayList<Map<String,Object>>();
		_listItems2 = new ArrayList<Map<String,Object>>();
		if (_products == null) return;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");

		for (ProductDescription i : _products) {
			Cinema cinema = dbHandler.queryCinema(i.getCinema_id());
			ScreeningRoom screeningRoom = dbHandler.queryScreeningRoom(i.getScreening_room_id());

			if (cinema != null && screeningRoom != null) {
				Location location = dbHandler.queryLocation(cinema.getDist_code());

				if (location != null) {
					Map<String,Object> item = new HashMap<String, Object>();
					item.put("product_index", _products.indexOf(i));
					item.put("cinema_name", cinema.getCinema_name());
					item.put("cinema_address", location.getAddress_name());
					item.put("type", i.getType());
					item.put("screening_room", screeningRoom.getScreening_room_name());
					Date start = i.getStartTime();
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(start);
					calendar.add(Calendar.MINUTE, movie.getDuration());
					Date end = calendar.getTime();

					item.put("start_time", sdf1.format(start));
					item.put("end_time", sdf1.format(end));
					item.put("price", Float.toString(i.getPrice()));
					calendar.setTime(start);
//					Log.i("test", sdf.format(start)); 
//					Log.i("test", Integer.toString(calendar.get(Calendar.YEAR)));
//					Log.i("test", Integer.toString(calendar.get(Calendar.MONTH)));
//					Log.i("test", Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)));
					Calendar calendar2 = Calendar.getInstance();
					if (calendar.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH) &&
							calendar.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH)) {
						_listItems0.add(item);
					}
					calendar2.add(Calendar.DAY_OF_MONTH, 1);
					if (calendar.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH) &&
							calendar.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH)) {
						_listItems1.add(item);
					}
					calendar2.add(Calendar.DAY_OF_MONTH, 1);
					if (calendar.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH) &&
							calendar.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH)) {
						_listItems2.add(item);
					}
				}
			}
		}
	}
	// init variables
	private void initView() {
		_back = (ImageView)findViewById(R.id.product_back);
		_tvs[0] = (TextView)findViewById(R.id.product_today);
		_tvs[0].setSelected(true);
		_tvs[1] = (TextView)findViewById(R.id.product_tomorrow);
		_tvs[2] = (TextView)findViewById(R.id.product_after_tomorrow);
	}

	// set listeners
	private void initEvent() {
		_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		_tvs[0].setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (_tvs[0].isSelected()) return;
				_tvs[0].setSelected(true);
				_tvs[_currency_fragment].setSelected(false);
				replaceFragment(0);
			}
		});
		_tvs[1].setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (_tvs[1].isSelected()) return;
				_tvs[1].setSelected(true);
				_tvs[_currency_fragment].setSelected(false);
				replaceFragment(1);
			}
		});
		_tvs[2].setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (_tvs[2].isSelected()) return;
				_tvs[2].setSelected(true);
				_tvs[_currency_fragment].setSelected(false);
				replaceFragment(2);
			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 111 && resultCode == 112) {
			setResult(112);
			finish();
		}
	}

	@Override
	public void onFragmentInteraction(String commend, int arg) {
		// TODO Auto-generated method stub
		if (commend.equals("itemclick")) {
			Intent intent = new Intent(ProductActivity.this, ReserveActivity.class);
			intent.putExtra("product", _products.get(arg));
			startActivityForResult(intent, 111);
		}
	}
}
