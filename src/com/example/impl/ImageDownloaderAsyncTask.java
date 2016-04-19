package com.example.impl;

import java.io.InputStream;

import com.example.model.ImageModel;
import com.example.network.ConnectionManager;
import com.example.util.ImageCaching;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

/**
 * @author Bhupal_Kinkiri
 * ImageDownloaderAsyncTask will be used to download the images 
 */
public class ImageDownloaderAsyncTask extends AsyncTask<ImageModel, Void, Bitmap> {

	private ImageView imageView;
	private ProgressBar progressBar;
	private Context mContext;
	ImageCaching mCachingObj;
	

	public ImageDownloaderAsyncTask(Context con,ImageCaching mCachingObj){
		mContext = con;
		this.mCachingObj = mCachingObj;
	}
	@Override
	protected Bitmap doInBackground(ImageModel... params) {
		imageView = (ImageView) params[0].getImageView();
		progressBar = (ProgressBar) params[0].getProgressBar();
		imageView.setVisibility(View.GONE);
		progressBar.setVisibility(View.VISIBLE);
		return downLoadImage((String) imageView.getTag());
	}

	@Override
	protected void onPostExecute(Bitmap result) {
		progressBar.setVisibility(View.GONE); // hide the progressbar after downloading the image.
		//cache the image with URL as the key
		if(result != null){
			System.out.println("Downloaded " + imageView.getTag());
			imageView.setVisibility(View.VISIBLE);
			mCachingObj.addBitmapToMemoryCache(imageView.getTag().toString(), result);										
			imageView.setImageBitmap(result); // set the bitmap to the imageview.
		}
	}

	protected Bitmap downLoadImage(String imageURL) {
		ConnectionManager connectionManager = new ConnectionManager();
		InputStream is = connectionManager.getResponse(imageURL);
		Bitmap bmImg = null;
		if(is != null){
		 bmImg = BitmapFactory.decodeStream(is);
		}
		
		return bmImg;
	}

}
