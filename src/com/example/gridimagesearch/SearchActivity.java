package com.example.gridimagesearch;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

public class SearchActivity extends Activity {

	
	private EditText etQuery;
	private GridView gvResults;
	private Button btnSearch;
	
	private List<ImageResult> imageResults = new ArrayList<ImageResult>();
	private ImageResultArrayAdapter imageResultArrayAdapter = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		setupViews();
		this.imageResultArrayAdapter = new ImageResultArrayAdapter(this,this.imageResults);
		this.gvResults.setAdapter(imageResultArrayAdapter);

		
		this.gvResults.setOnItemClickListener(new OnItemClickListener(){
			

			@Override
			public void onItemClick(AdapterView<?> adapter, View parent, int position,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), ImageDisplayActivity.class);
				ImageResult imageResult = imageResults.get(position);
				intent.putExtra("url", imageResult.getFullURL());
				startActivity(intent);
			}
			
			
		});
		
		
		gvResults.setOnScrollListener(new EndlessScrollListener() {
			    @Override
			    public void onLoadMore(int page, int totalItemsCount) {
			        loadMoreData(page); 
 
			    }
		        });
		
		
	}

	
	public void onClickingAdvancedSearchOptionsBar(MenuItem mi) {
	     // handle click here
		Toast.makeText(this, "Clicked!", Toast.LENGTH_SHORT).show();
		Intent i = new Intent (this, AdvancedSearchOptionsActivity.class);
		i.putExtra("rsz", 2); // To get the size from the user
		i.putExtra("color", 1); // To get the color from user
		startActivity(i);
	}

	 // Append more data into the adapter
    public void loadMoreData(int offset) {
        this.makeGoogleImageAPICall();
    }
    
    
    private void setupViews() {
        this.etQuery = (EditText) this.findViewById(R.id.etQuery);
        this.btnSearch = (Button) this.findViewById(R.id.btnSearch);
        this.gvResults = (GridView) this.findViewById(R.id.gvResults);
    }
    
    public void makeGoogleImageAPICall(){
    	String query = this.etQuery.getText().toString();
    	
    	Toast.makeText(this,"Going to search for " + query, Toast.LENGTH_SHORT).show();
    
    	//https://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=android
    	AsyncHttpClient client = new AsyncHttpClient();
    	String url = "https://ajax.googleapis.com/ajax/services/search/images?rsz=8&"
    			+ "start=" + 0 + "&v=1.0&q=" + Uri.encode(query);
    	Log.d("DEBUG",url);
    	client.get(url, 
    			new JsonHttpResponseHandler(){
    				public void onSuccess(JSONObject response){
    
    					JSONArray imageJsonResults = null;
    					try{

    						imageJsonResults = response.getJSONObject("responseData").
    								getJSONArray("results");
    						imageResults.clear();
    						//imageResults.addAll(ImageResult.fromJsonArray(imageJsonResults));
    						imageResultArrayAdapter.addAll(ImageResult.fromJsonArray(imageJsonResults));

    					}catch(Exception e){
    						e.printStackTrace();

    					}
    				}
    			});
	
    }
    
    public void onImageSearch(View v){
        this.makeGoogleImageAPICall();
    		
    }
    
    

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}

}
