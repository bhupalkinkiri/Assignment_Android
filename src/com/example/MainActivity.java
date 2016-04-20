package com.example;

import java.util.ArrayList;

import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.adapter.MyArrayAdapter;
import com.example.app.AppController;
import com.example.assignment.R;
import com.example.impl.JSONParser;
import com.example.model.NEWSFeedModel;
import com.example.model.NEWSModel;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

/**
 * @author Bhupal_Kinkiri This is the Mian Activity
 */
public class MainActivity extends Activity {
	// Log tag
	private static final String TAG = MainActivity.class.getSimpleName();
	private static final String url = "https://dl.dropboxusercontent.com/u/746330/facts.json";
	private ProgressDialog progressDialog;
	private ArrayList<NEWSModel> newsModel = new ArrayList<NEWSModel>();
	private MyArrayAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// creating an adapter to populate the values in the List
		adapter = new MyArrayAdapter(this, newsModel); // intiating Adapter with
														// null list
		ListView listView = (ListView) findViewById(R.id.listView);
		listView.setAdapter(adapter);

		progressDialog = new ProgressDialog(this);
		// Showing progress dialog before making the request
		progressDialog.setMessage(getString(R.string.loading));
		progressDialog.setCancelable(false);
		progressDialog.show();

		// creating volley requesting object
		JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						NEWSFeedModel newsFeedModel = JSONParser.parseJSONResponse(response);
						if (progressDialog.isShowing())
							progressDialog.dismiss();
						
						// updating the list with parsed results
						adapter.updateTheList(newsFeedModel.getRows());
						adapter.notifyDataSetChanged();
						//setting the actionbar title
						getActionBar().setTitle(newsFeedModel.getTitle());
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						if (progressDialog.isShowing())
							progressDialog.dismiss();
						Toast.makeText(MainActivity.this, MainActivity.this.getString(R.string.noresponse),
								Toast.LENGTH_LONG).show();
					}
				});
		// adding the request to queue
		AppController.getInstance().addToRequestQueue(request, TAG);	
	}

	@Override
	protected void onStop() {
		super.onStop();
		// canceling all the pending network threads
		AppController.getInstance().cancelPendingRequests(TAG);
	}

}
