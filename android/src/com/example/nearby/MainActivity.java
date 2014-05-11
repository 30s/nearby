package com.example.nearby;

import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.nearby.adapters.WifiAdapter;
import com.example.nearby.models.Wifi;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		private OnClickListener onClickListener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (v.getId() == R.id.btn_scan) {
					srvWifi.startScan();
				}
			}
		};

		private BroadcastReceiver wifiReceiver = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				List<ScanResult> results = srvWifi.getScanResults();
				for (ScanResult sr : results) {
					Wifi wifi = new Wifi(sr.SSID, sr.BSSID);
					adapter.add(wifi);
				}
			}
		};

		private ListView lst_wifi;
		private WifiAdapter adapter;
		private WifiManager srvWifi;

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);

			rootView.findViewById(R.id.btn_scan).setOnClickListener(
					onClickListener);
			lst_wifi = (ListView) rootView.findViewById(R.id.lst_wifi);
			adapter = new WifiAdapter();
			lst_wifi.setAdapter(adapter);

			srvWifi = (WifiManager) getActivity().getSystemService(
					Context.WIFI_SERVICE);

			return rootView;
		}

		@Override
		public void onResume() {
			super.onResume();
			getActivity()
					.registerReceiver(
							wifiReceiver,
							new IntentFilter(
									WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
		}

		@Override
		public void onPause() {
			super.onPause();
			getActivity().unregisterReceiver(wifiReceiver);
		}
	}

}
