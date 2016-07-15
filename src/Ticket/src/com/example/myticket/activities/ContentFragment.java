package com.example.myticket.activities;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.myticket.R;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment
 * must implement the {@link ContentFragment.OnFragmentInteractionListener}
 * interface to handle interaction events. Use the
 * {@link ContentFragment#newInstance} factory method to create an instance of
 * this fragment.
 *
 */
public class ContentFragment extends Fragment {
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	// TODO: Rename and change types of parameters
	private OnFragmentInteractionListener mListener = null;
	private List<Map<String, Object>> _listItems = null;
	private ListView _listView;
	private Activity activity;
	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 *
	 * @param param1
	 *            Parameter 1.
	 * @param param2
	 *            Parameter 2.
	 * @return A new instance of fragment ContentFragment.
	 */

	public ContentFragment(List<Map<String, Object>> data) {
		// TODO Auto-generated constructor stub
		this._listItems = data;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_product, container, false);
		_listView = (ListView)view.findViewById(R.id.product_list);
		//Log.i("test", "create");
		if (!_listItems.isEmpty()) {
			//Log.i("test", "set adapter");
			SimpleAdapter simpleAdapter = new SimpleAdapter(activity, _listItems, R.layout.products_list_item, 
					new String[]{"cinema_name", "cinema_address", "type", "screening_room", "start_time", "end_time", "price"}, 
					new int[]{R.id.product_list_cinema_name, R.id.product_list_address, R.id.product_list_type, R.id.product_list_screening_room, R.id.product_list_start_time, R.id.product_list_end_time, R.id.product_list_price});
			
			_listView.setAdapter(simpleAdapter);
		}
		_listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				if (mListener != null) {
					int arg = (Integer) _listItems.get(arg2).get("product_index");
					mListener.onFragmentInteraction("itemclick", arg);
				}
			}
			
		});
		return view;
	}


	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.activity = activity;
		try {
			mListener = (OnFragmentInteractionListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnFragmentInteractionListener");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
		activity = null;
	}

	/**
	 * This interface must be implemented by activities that contain this
	 * fragment to allow an interaction in this fragment to be communicated to
	 * the activity and potentially other fragments contained in that activity.
	 */
	public interface OnFragmentInteractionListener {
		// TODO: Update argument type and name
		public void onFragmentInteraction(String commend, int arg);
	}

}
