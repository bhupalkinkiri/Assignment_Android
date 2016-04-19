package com.example.model;

import android.widget.ImageView;
import android.widget.ProgressBar;

/**
 * @author Bhupal_Kinkiri
 * ImageModel class is used to share the image view to AsyncTask 
 */
public class ImageModel {

	private ImageView imageView;
	private ProgressBar progressBar;
	public ImageView getImageView() {
		return imageView;
	}
	public void setImageView(ImageView imageView) {
		this.imageView = imageView;
	}
	public ProgressBar getProgressBar() {
		return progressBar;
	}
	public void setProgressBar(ProgressBar progressBar) {
		this.progressBar = progressBar;
	}
	
	
}
