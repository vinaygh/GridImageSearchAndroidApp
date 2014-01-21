package com.example.gridimagesearch;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class ImageResult {

    private String fullURL;
    private String thumbURL;
	
    public ImageResult(JSONObject jsonObject){
		
    try {
		this.fullURL = jsonObject.getString("url");
		this.thumbURL = jsonObject.getString("tbUrl");
				
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		this.fullURL = null;
		this.thumbURL = null;
	}
    
	}
	public String getFullURL() {
		return fullURL;
	}

	public String getThumbURL() {
		return thumbURL;
	}
	public static ArrayList<ImageResult> fromJsonArray(
			JSONArray imageJsonResults) {
		
		ArrayList<ImageResult> imageResults = new ArrayList<ImageResult>();
		
		for(int i=0;i<imageJsonResults.length();i++){
			try {
				imageResults.add(new ImageResult(imageJsonResults.getJSONObject(i)));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				Log.d("DEBUG", "Ha ha ha ...10");
				e.printStackTrace();
			}
		}
		
		return imageResults;
	}
	
	
}
