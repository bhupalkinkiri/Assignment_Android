package com.example.impl;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.model.NEWSFeedModel;
import com.example.model.NEWSModel;

import android.util.Log;

/**
 * @author Bhupal_Kinkiri 
 * this class is used to parse the json response
 */
public class JSONParser {
	private static final String TAG_LIST_TITLE = "title";
	private static final String TAG_ARRAY = "rows";
	private static final String TAG_TITLE = "title";
	private static final String TAG_DESCRIPTION = "description";
	private static final String TAG_IMG_URL = "imageHref";

	public static NEWSFeedModel parseJSONResponse(String response) {
		NEWSFeedModel newsFeedModel = new NEWSFeedModel();
		try {

			JSONObject jsonObject = new JSONObject(response);
			String list_title = jsonObject.getString(TAG_LIST_TITLE);
			newsFeedModel.setTitle(list_title);
			JSONArray newsItemsJSON = jsonObject.getJSONArray(TAG_ARRAY);
			String parsedResponse = "";
			ArrayList<NEWSModel> rows = new ArrayList<NEWSModel>();
			// reading the response
			for (int i = 0; i < newsItemsJSON.length(); i++) {

				JSONObject itemJSON = newsItemsJSON.getJSONObject(i);
				NEWSModel newsModel = new NEWSModel();

				if (itemJSON.isNull(TAG_TITLE)) {
					newsModel.setTitle(null);
				} else {
					newsModel.setTitle(itemJSON.getString(TAG_TITLE));
				}

				if (itemJSON.isNull(TAG_DESCRIPTION))
					newsModel.setDescription(null);
				else
					newsModel.setDescription(itemJSON.getString(TAG_DESCRIPTION));

				if (itemJSON.isNull(TAG_IMG_URL))
					newsModel.setImageURLRef(null);
				else
					newsModel.setImageURLRef(itemJSON.getString(TAG_IMG_URL));

				// add the item to the list only data available
				if (newsModel.getTitle() != null || newsModel.getDescription() != null
						|| newsModel.getImageURLRef() != null) {
					rows.add(newsModel);
					parsedResponse = parsedResponse + newsModel.getString();
				}

			}
			Log.d("Response", "Response :: " + parsedResponse);
			newsFeedModel.setRows(rows);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return newsFeedModel;
	}

}
