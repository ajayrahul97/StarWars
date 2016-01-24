package com.example.starwars;



import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Options extends Activity{
	Button b1,b2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.opt);
Button b1= (Button) findViewById(R.id.bAdd);
		
		b1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//Intent next= new Intent(getApplicationContext(),Menu.class);
				//startActivity(next);
				Intent opensp = new Intent(Options.this,Add.class);
				startActivity(opensp);
				
			} 
			
					
		});
Button b2= (Button) findViewById(R.id.bDisplay);
		
		b2.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//Intent next= new Intent(getApplicationContext(),Menu.class);
				//startActivity(next);
				Intent opensp = new Intent(Options.this,Listdisp.class);
				startActivity(opensp);
				
			} 
			
					
		});
		
Button b3= (Button) findViewById(R.id.bDelete);
		
		b3.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//Intent next= new Intent(getApplicationContext(),Menu.class);
				//startActivity(next);
				Intent opensp = new Intent(Options.this,Listdel.class);
				startActivity(opensp);
				
			} 
			
			
					
		});
Button b6= (Button) findViewById(R.id.bModify);
		
		b6.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//Intent next= new Intent(getApplicationContext(),Menu.class);
				//startActivity(next);
				Intent opensp = new Intent(Options.this,Listmod.class);
				startActivity(opensp);
				
			} 
			
			
					
		});
		
Button b7= (Button) findViewById(R.id.bCom);
		
		b7.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//Intent next= new Intent(getApplicationContext(),Menu.class);
				//startActivity(next);
				Intent opensp = new Intent(Options.this,Compare.class);
				startActivity(opensp);
				
			} 
			
			
					
		});
		
Button b4= (Button) findViewById(R.id.bwhatsapp);
		
		b4.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//Intent next= new Intent(getApplicationContext(),Menu.class);
				//startActivity(next);
				Intent opensp = new Intent(Options.this,Listwhatsapp.class);
				startActivity(opensp);
				
			} 
			
			
					
		});
		
Button b9= (Button) findViewById(R.id.bCall);
		
		b9.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//Intent next= new Intent(getApplicationContext(),Menu.class);
				//startActivity(next);
				Intent opensp = new Intent(Options.this,Listcall.class);
				startActivity(opensp);
				
			} 
			
			
					
		});
Button b19= (Button) findViewById(R.id.bgame);
		
		b19.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//Intent next= new Intent(getApplicationContext(),Menu.class);
				//startActivity(next);
				Intent opensp = new Intent(Options.this,Dbgame.class);
				startActivity(opensp);
				
			} 
			
			
					
		});
		
Button b5= (Button) findViewById(R.id.bfb);
		
		b5.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//Intent next= new Intent(getApplicationContext(),Menu.class);
				//startActivity(next);
				PackageManager pm=getPackageManager();
			    try {

			        Intent waIntent = new Intent(Intent.ACTION_SEND);
			        waIntent.setType("text/plain");
			        //String text = "YOUR TEXT HERE"
					String text= "Hey Starwar Fan...check out this awesome app ......i foyu want it on your smartphone ,it only costs $2.99....contact Ajay!";
;

			        PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
			        //Check if package exists or not. If not then code 
			        //in catch block will be called
			        waIntent.setPackage("com.whatsapp");

			        waIntent.putExtra(Intent.EXTRA_TEXT, text);
			        startActivity(Intent.createChooser(waIntent, "Share with"));

			   } catch (NameNotFoundException e) {
			        
			   }  

				
			} 
			
			
					
		});
		
		
		
	}

	@Override
	public void onBackPressed() {
	    //your code when back button pressed
		Intent opensp = new Intent(Options.this,Mainpage.class);
		startActivity(opensp);
		finish();
		
	}
	
	
	
}
