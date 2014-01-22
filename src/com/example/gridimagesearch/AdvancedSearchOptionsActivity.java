package com.example.gridimagesearch;

import java.io.Serializable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class AdvancedSearchOptionsActivity extends Activity implements Serializable{

	
	Spinner sizeOfImage;
    Spinner colorFilter;
    Spinner typeOfImages;
    EditText siteFilter;

    String imageSizeSelected = "extra-large";
    String colorSelected = "none";
    String imageTypeSelected = "none";

    Bundle outState = new Bundle();

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.outState.putBundle("state", outState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        outState = savedInstanceState.getBundle("state");
        assert outState != null;
        sizeOfImage.setSelection(outState.getInt("imageSize"));
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_search_options);
        sizeOfImage = (Spinner) findViewById(R.id.imageSizeSpinner);
        colorFilter = (Spinner) findViewById(R.id.colorSelectSpinner);
        typeOfImages = (Spinner) findViewById(R.id.imageSelectSpinner);
        siteFilter = (EditText) findViewById(R.id.siteFilter);


        ArrayAdapter<CharSequence> imageAdapter = null;
        ArrayAdapter<CharSequence> colorAdapter = null;
        ArrayAdapter<CharSequence> typeAdapter = null;
        
        /*imageAdapter = ArrayAdapter.createFromResource(this, R.array.sizeArray, R.layout.spinner_layout);
        colorAdapter = ArrayAdapter.createFromResource(this, R.array.colorArray, R.layout.spinner_layout);
        typeAdapter = ArrayAdapter.createFromResource(this, R.array.typeArray, R.layout.spinner_layout);
*/

        sizeOfImage.setAdapter(imageAdapter);
        colorFilter.setAdapter(colorAdapter);
        typeOfImages.setAdapter(typeAdapter);

        sizeOfImage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                imageSizeSelected = adapterView.getItemAtPosition(i).toString();
                outState.putInt("imageSize", i);
                sizeOfImage.setSelection(i);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        }

        );

        colorFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                colorSelected = adapterView.getItemAtPosition(i).toString();
                outState.putInt("colorSelected", i);
                colorFilter.setSelection(i);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        }

        );


        typeOfImages.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                imageTypeSelected = adapterView.getItemAtPosition(i).toString();
                outState.putInt("imageType", i);
                typeOfImages.setSelection(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        }

        );


    }

    public void getCongifuration(View view) {
        Intent i = new Intent();
        i.putExtra("imageSizeSelected", imageSizeSelected);
        i.putExtra("colorSelected", colorSelected);
        i.putExtra("imageTypeSelected", imageTypeSelected);
        i.putExtra("siteFilter", siteFilter.getText());
        setResult(1, i);
        this.finish();
    }
	/*
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_advanced_search_options);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.advanced_search_options, menu);
		return true;
	}*/

}
