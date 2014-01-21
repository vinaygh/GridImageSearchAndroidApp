package com.example.gridimagesearch;

import java.util.List;

import com.loopj.android.image.SmartImageView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class ImageResultArrayAdapter extends ArrayAdapter<ImageResult> {

	public ImageResultArrayAdapter(Context context, List<ImageResult> images) {
		super(context, R.layout.item_image_search, images);
		 
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ImageResult imageResult = this.getItem(position);
		SmartImageView smartImageView = null;
		if(convertView == null){
			LayoutInflater inflater = LayoutInflater.from(this.getContext());
			smartImageView = (SmartImageView) inflater.inflate(R.layout.item_image_search,parent,false);
			
		}else{
			smartImageView = (SmartImageView) convertView;
			smartImageView.setImageResource(android.R.color.transparent);
		}
		
		smartImageView.setImageUrl(imageResult.getThumbURL() );
		return smartImageView; 
		
		
	}

	
}
