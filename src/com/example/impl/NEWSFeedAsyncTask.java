package com.example.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.example.adapter.MyArrayAdapter;
import com.example.assignment.R;
import com.example.model.NEWSFeedModel;
import com.example.model.NEWSModel;
import com.example.network.ConnectionManager;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.widget.ListView;

/**
 * @author Bhupal_Kinkiri
 * NEWSFeedAsyncTask used to fetch the response
 */
/**
 * @author bhupal_kinkiri
 *
 */
public class NEWSFeedAsyncTask extends AsyncTask<Void, Void, NEWSFeedModel> {

	ProgressDialog mprogressDialog = null;
	Context mcontext = null;
	ActionBar actionbar;
	String reqURL = "https://dl.dropboxusercontent.com/u/746330/facts.json";
	
	public NEWSFeedAsyncTask(Context con, ActionBar actionbar){
		mcontext = con;
		this.actionbar = actionbar;
		mprogressDialog = new ProgressDialog(mcontext);
	}
	
	@Override
	protected NEWSFeedModel doInBackground(Void... params) {
		String response = getResponse();
		NEWSFeedModel newsFeedModel = JSONParser.parseJSONResponse(response);
		return newsFeedModel;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		//showing the loading dialog
		mprogressDialog.setTitle("Loading..");
		mprogressDialog.setCancelable(false);
		mprogressDialog.show();
	}

	@Override
	protected void onPostExecute(NEWSFeedModel newsFeedModel) {
		super.onPostExecute(newsFeedModel);
		if(mprogressDialog.isShowing())
			mprogressDialog.dismiss();
		//setting the title of the ActionBar
		actionbar.setTitle(newsFeedModel.getTitle());
		//creating an adapter to populate the values in the List
		MyArrayAdapter adapter = new MyArrayAdapter(((Activity) mcontext),newsFeedModel.getRows()); //intiating Adapter with null list
		ListView listView = (ListView)((Activity) mcontext).findViewById(R.id.listView);
		listView.setAdapter(adapter);
	
	}

	/**
	 * make the server call and fetch the data
	 * @return response
	 */
	public String getResponse() {

		StringBuffer result = new StringBuffer();
		ConnectionManager connectionManager = new ConnectionManager();

		InputStream is = connectionManager.getResponse(reqURL);
		if(is != null){
		String line;
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			while ((line = reader.readLine()) != null) {
				result.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result.toString();
		}else{ //when there is no response then read it locally
			return getLocalResponse();
		}
		
	
	}
	
	/**
	 * utility method used to read the response from Assets
	 * @return response
	 */
	public String getLocalResponse(){
		byte[] buffer = null;
		InputStream is;
		AssetManager assetManager = mcontext.getAssets();
		try{
			is = assetManager.open("telstra.json");
			int size = is.available();
			buffer = new byte[size];
			is.read(buffer);
			is.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		String response = new String(buffer);
		return response;
	}
		
	}

