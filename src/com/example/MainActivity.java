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
import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

/**
 * @author Bhupal_Kinkiri
 * This is the Mian Activity
 */
public class MainActivity extends Activity {
	// Log tag
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String url = "https://dl.dropboxusercontent.com/u/746330/facts.json";
    private ProgressDialog progressDialog;
    private ArrayList<NEWSModel> newsModel = new ArrayList<NEWSModel>();
    private NEWSFeedModel newsFeedModel;// = new NEWSFeedModel();
    private ListView listView;
    private MyArrayAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}	
		
				//creating an adapter to populate the values in the List
				adapter = new MyArrayAdapter(this,newsModel); //intiating Adapter with null list
				ListView listView = (ListView)findViewById(R.id.listView);
				listView.setAdapter(adapter);
		
				progressDialog = new ProgressDialog(this);
		        // Showing progress dialog before making http request
				progressDialog.setMessage("Loading...");
				progressDialog.show();
		
				
				//creating volley requesting object
				JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

				    @Override
				    public void onResponse(JSONObject response) {
				    	//TODO needs to use GSon 
				    	newsFeedModel = JSONParser.parseJSONResponse(response);
				    	adapter.updateTheList(newsFeedModel.getRows());
				    	adapter.notifyDataSetChanged();
				    	if(progressDialog.isShowing())
				    		progressDialog.dismiss();
				    }
				}, new Response.ErrorListener() {

				    @Override
				    public void onErrorResponse(VolleyError error) {
				    	if(progressDialog.isShowing())
				    		progressDialog.dismiss();
				    	 Toast.makeText(MainActivity.this, "Unable to fetch the response", Toast.LENGTH_LONG).show();
				    }
				});
						 
		
		//making a server call to fetch the response
		//NEWSFeedAsyncTask newsFeedrunner  = new NEWSFeedAsyncTask(MainActivity.this,actionbar);
		//newsFeedrunner.execute();
		AppController.getInstance().addToRequestQueue(request);
		
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

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}
}
