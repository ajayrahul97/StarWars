package com.example.starwars;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore.MediaColumns;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Compare extends Activity {
	String message, mess;
	TextView t1,t2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.compare);
		Button b1 = (Button) findViewById(R.id.tv1);
		Button b2 = (Button) findViewById(R.id.tv2);
		Button b3 = (Button) findViewById(R.id.bsendmail);
		 t1 = (TextView) findViewById(R.id.et1);
		 t2 = (TextView) findViewById(R.id.editText1);

		b1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				Intent opensp = new Intent(Compare.this, Listcompare.class);
				startActivityForResult(opensp, 0);

			}

		});

		b2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				Intent opensp = new Intent(Compare.this, Listmod2.class);
				startActivityForResult(opensp, 1);

			}

		});

		/*
		 * @Override protected void onActivityResult(int requestCode, int
		 * resultCode, Intent data) { super.onActivityResult(requestCode,
		 * resultCode, data); if (resultCode == RESULT_OK) { if (requestCode ==
		 * 1) {
		 * 
		 * 
		 * } } }
		 */

		b3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				Intent myintent = new Intent(Compare.this, Discom.class);
				myintent.putExtra("warrior1", message);
				myintent.putExtra("warrior2", mess);
				startActivity(myintent);

			}

		});
		
	

	}

	@Override
	public void onBackPressed()
	{
	   
	 finish();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode==RESULT_OK){
			Bundle basket = data.getExtras();
			message= basket.getString("message");
			t1.setText(message);
		
		}
		if(resultCode==12){
			Bundle basket = data.getExtras();
		
			 mess = basket.getString("mess");
			t2.setText(mess);
		}
		/*if (resultCode == RESULT_OK) {
			if (requestCode == 1) {
				
				Bundle bundle = getIntent().getExtras();
				message = bundle.getString("message");
				t1.setText(message);
				

			}
			if (requestCode == 2) {
				Bundle bundle = getIntent().getExtras();

				mess = bundle.getString("mess");
				t2.setText(mess);

			}
		}*/

	}

}
