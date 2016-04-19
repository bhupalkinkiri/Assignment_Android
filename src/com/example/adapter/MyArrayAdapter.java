package com.example.adapter;

import java.util.ArrayList;

import com.example.assignment.R;
import com.example.impl.ImageDownloaderAsyncTask;
import com.example.model.ImageModel;
import com.example.model.NEWSModel;
import com.example.util.ImageCaching;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * @author Bhupal_Kinkiri Adapter class which will be used to render the list
 *         items
 */
public class MyArrayAdapter extends ArrayAdapter<NEWSModel> {

	private final Activity context;
	private ArrayList<NEWSModel> list;
	ImageCaching mCachingObj;
	// private HashSet<String> mImageRefSet ;

	public MyArrayAdapter(Activity context, ArrayList<NEWSModel> list) {
		super(context, R.layout.list_item, list);
		this.context = context;
		this.list = list;
		// creating an object for memory caching
		mCachingObj = new ImageCaching();
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

		viewHolder.image = (ImageView) convertView.findViewById(R.id.imageView);
		viewHolder.progressBar = (ProgressBar) convertView.findViewById(R.id.progressBar);
		viewHolder.title = (TextView) convertView.findViewById(R.id.title);
		viewHolder.description = (TextView) convertView.findViewById(R.id.description);

		// setting the data to View
		viewHolder.title.setText(list.get(position).getTitle());
		viewHolder.description.setText(list.get(position).getDescription());

		viewHolder.image.setTag(list.get(position).getImageURLRef());
		ImageModel imgModel = new ImageModel();
		imgModel.setImageView(viewHolder.image);
		imgModel.setProgressBar(viewHolder.progressBar);

		// check for imageURL and fetch from server
		if (list.get(position).getImageURLRef() != null && !list.get(position).getImageURLRef().equals("")) {
			imageLoadingAndCaching(viewHolder.image.getContext(), imgModel);
		} else {
			// image URL is not valid
			viewHolder.image.setVisibility(View.GONE);
			viewHolder.progressBar.setVisibility(View.GONE);
		}

		return convertView;
	}

	/**
	 * This method will check the if the requested image in cache if not it will
	 * make the call
	 * 
	 * @param mcontext
	 * @param imgModel
	 */
	private void imageLoadingAndCaching(Context mcontext, ImageModel imgModel) {

		final String imageKey = (String) imgModel.getImageView().getTag();
		final Bitmap bitmap = mCachingObj.getBitmapFromMemCache(imageKey);
		// before making the call first check in the cache
		if (bitmap != null) {
			imgModel.getImageView().setVisibility(View.VISIBLE);
			imgModel.getImageView().setImageBitmap(bitmap);
		} else {
			new ImageDownloaderAsyncTask(mcontext, mCachingObj).execute(imgModel);
		}
	}

	class ViewHolder {
		protected TextView title;
		protected TextView description;
		protected ImageView image;
		protected ProgressBar progressBar;
	}
}
