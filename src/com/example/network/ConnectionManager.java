package com.example.network;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.util.Log;

/**
 * @author Bhupal_Kinkiri
 * using the HttoURLConnection frtch the data from server
 * used to read the list data and image downloading 
 */
public class ConnectionManager {

	public ConnectionManager() {

	}

	public InputStream getResponse(String strURL) {
		URL myFileUrl = null;
		InputStream is = null;
		int responseCode = -1;
		Log.d("NEWSFeed", "URL :: " + strURL);
		try {
			myFileUrl = new URL(strURL);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
			conn.setDoInput(true);
			conn.connect();
			responseCode = conn.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				is = conn.getInputStream();
			}
		} catch (IOException ioexception) {
			Log.e("NEWSFeed", "Exception while reading the response :" + ioexception.getLocalizedMessage());
			return null;
		} catch (Exception exception) {
			Log.e("NEWSFeed", "Exception while reading the response :" + exception.getLocalizedMessage());
			return null;
		}
		return is;
	}
}
