package com.example.nearby.adapters;

import java.util.ArrayList;
import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nearby.R;
import com.example.nearby.models.Wifi;

public class WifiAdapter extends BaseAdapter {

	private List<Wifi> mWifis = new ArrayList<Wifi>();

	public WifiAdapter() {

	}

	@Override
	public int getCount() {
		return mWifis.size();
	}

	@Override
	public Object getItem(int position) {
		return mWifis.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewGroup view = (convertView instanceof ViewGroup) ? (ViewGroup) convertView
				: (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(
						R.layout.item_wifi, null);
		
		Wifi wifi = mWifis.get(position);
		TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
		TextView tv_bssid = (TextView) view.findViewById(R.id.tv_bssid);
		tv_name.setText(wifi.getName());
		tv_bssid.setText(wifi.getBSSID());

		return view;
	}

	public void add(Wifi wifi) {
		for (Wifi w : mWifis) {
			if (w.getBSSID().equals(wifi.getBSSID())) {
				return;
			}
		}
		mWifis.add(wifi);
		notifyDataSetChanged();
	}

}
