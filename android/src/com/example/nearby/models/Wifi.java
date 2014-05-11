package com.example.nearby.models;

public class Wifi {
	private String name;
	private String BSSID;
	
	public Wifi(String name, String BSSID) {
		this.setName(name);
		this.setBSSID(BSSID);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBSSID() {
		return BSSID;
	}

	public void setBSSID(String bSSID) {
		BSSID = bSSID;
	}

}
