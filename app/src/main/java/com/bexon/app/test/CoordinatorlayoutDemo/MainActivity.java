package com.bexon.app.test.CoordinatorlayoutDemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import com.android.volley.*;
import com.android.volley.toolbox.*;
import android.graphics.*;
import android.text.format.*;
import android.os.Build;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar toolbar;
	private Time t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
		mCollapsingToolbarLayout();
		mToolbar();
		mImageView();
    }

	private void mImageView()
	{
		iv = (ImageView) findViewById(R.id.iv);
		String url="https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_160x56dp.png";
		RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
		ImageRequest request = new ImageRequest(url, new Response.Listener<Bitmap>() {    
				@Override    
				public void onResponse(Bitmap response) {  
					//给imageView设置图片      
					iv.setImageBitmap(response);
					iv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
				}
			}, 0, 0, ImageView.ScaleType.CENTER, Bitmap.Config.RGB_565, new Response.ErrorListener() {    
				@Override    
				public void onErrorResponse(VolleyError error) { 
					//设置一张错误的图片，临时用ic_launcher代替          
					iv.setImageResource(R.mipmap.ic_bg);    
				}
			}); 
		requestQueue.add(request);
		
		//iv.setImageResource(R.mipmap.ic_bg);
	}

	private void mToolbar()
	{
		toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
	}

	private void mCollapsingToolbarLayout()
	{
		String title;
		t = new Time();
		t.setToNow();
		int time = t.hour;
		if(time>=6&&time<9){
			title = "Morning";
		} else if(time>=9&&time<12){
			title = "Forenoon";
		} else if(time>=12&&time<15){
			title = "Noon";
		} else if(time>=15&&time<19){
			title = "Afternoon";
		} else{
			title = "Evening";
		}
		collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
		collapsingToolbarLayout.setTitle(title);
		collapsingToolbarLayout.setEnabled(true);
		collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        collapsingToolbarLayout.setExpandedTitleColor(Color.BLACK);
	}

}
