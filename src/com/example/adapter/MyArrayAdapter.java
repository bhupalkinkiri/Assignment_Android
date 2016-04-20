package com.example.adapter;

import java.util.ArrayList;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.app.AppController;
import com.example.assignment.R;
//import com.example.model.ImageModel;
import com.example.model.NEWSModel;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * @author Bhupal_Kinkiri 
 * Adapter class which will be used to render the list
 *         items
 */
public class MyArrayAdapter extends ArrayAdapter<NEWSModel> {

	private final Activity context;
	private ArrayList<NEWSModel> list;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();

	public MyArrayAdapter(Activity context, ArrayList<NEWSModel> list) {
		super(context, R.layout.list_item, list);
		this.context = context;
		this.list = list;
	}

	public void updateTheList(ArrayList<NEWSModel> list) {
		this.list = list;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {

			LayoutInflater inflator = context.getLayoutInflater();
			convertView = inflator.inflate(R.layout.list_item, null);
			viewHolder = new ViewHolder();
			convertView.setTag(viewHolder);

		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		viewHolder.image = (NetworkImageView) convertView.findViewById(R.id.imageView);
		viewHolder.title = (TextView) convertView.findViewById(R.id.title);
		viewHolder.description = (TextView) convertView.findViewById(R.id.description);
		// setting the data to View
		viewHolder.title.setText(list.get(position).getTitle());
		viewHolder.description.setText(list.get(position).getDescription());
		viewHolder.image.setImageUrl(list.get(position).getImageURLRef(), imageLoader);

		return convertView;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	class ViewHolder {
		protected TextView title;
		protected TextView description;
		protected NetworkImageView image;
	}
}
