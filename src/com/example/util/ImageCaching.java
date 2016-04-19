package com.example.util;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.LruCache;

/**
 * @author Bhupal_Kinkiri
 * caching bitmap images using LruCache
 */
public class ImageCaching {
	private LruCache<String, Bitmap> mMemoryCache;

	public ImageCaching() {

		// Get max available VM memory, exceeding this amount will throw an
		// OutOfMemory exception. Stored in kilobytes as LruCache takes an
		// int in its constructor.
		final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
		// Use 1/8th of the available memory for this memory cache.
		final int cacheSize = maxMemory / 8;
		mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
			@Override
			protected int sizeOf(String key, Bitmap bitmap) {
				// The cache size will be measured in kilobytes rather than
				// number of items.
				return bitmap.getByteCount() / 1024;
			}
		};

	}

	public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
		if (getBitmapFromMemCache(key) == null) {
			mMemoryCache.put(key, bitmap);
		}
	}

	public Bitmap getBitmapFromMemCache(String key) {
		return mMemoryCache.get(key);
	}
	
	public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
	    int width = bm.getWidth();
	    int height = bm.getHeight();
	    float scaleWidth = ((float) newWidth) / width;
	    float scaleHeight = ((float) newHeight) / height;
	    // CREATE A MATRIX FOR THE MANIPULATION
	    Matrix matrix = new Matrix();
	    // RESIZE THE BIT MAP
	    matrix.postScale(scaleWidth, scaleHeight);

	    // "RECREATE" THE NEW BITMAP
	    Bitmap resizedBitmap = Bitmap.createBitmap(
	        bm, 0, 0, width, height, matrix, false);
	    bm.recycle();
	    return resizedBitmap;
	}

}
