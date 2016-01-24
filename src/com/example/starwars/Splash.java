package com.example.starwars;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

public class Splash extends Activity{
	MediaPlayer sng;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		sng=MediaPlayer.create(Splash.this, R.raw.stwars);
		sng.start();
		
		
		Thread timer= new Thread(){
			
			public void run(){
				
				try{
					sleep(5000);
				}
				catch(InterruptedException e){
					e.printStackTrace();
				}
				finally{
					Intent opensp = new Intent(Splash.this,Mainpage.class);
					startActivity(opensp);
					
				}
			
		}
		
		
		
		};
	
		timer.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		sng.release();
		finish();
	}
	
}
