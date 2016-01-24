package com.example.starwars;

import java.io.BufferedInputStream;
import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Display extends Activity {

	SQLiteDatabase db, dbimg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.display);
		RelativeLayout root = (RelativeLayout) findViewById(R.id.disp);
		TextView T0 = (TextView) findViewById(R.id.dtitle);
		TextView T1 = (TextView) findViewById(R.id.dname);
		TextView T2 = (TextView) findViewById(R.id.dside);
		TextView T3 = (TextView) findViewById(R.id.dspecies);
		TextView T4 = (TextView) findViewById(R.id.dgender);
		TextView T5 = (TextView) findViewById(R.id.ddate);
		TextView T6 = (TextView) findViewById(R.id.dplace);
		TextView T7 = (TextView) findViewById(R.id.mobno);

		ImageView I = (ImageView) findViewById(R.id.im);

		db = openOrCreateDatabase("WarriorsDB", Context.MODE_PRIVATE, null);
		dbimg = openOrCreateDatabase("WarriorsDBIMAGE", Context.MODE_PRIVATE, null);

		Bundle bundle = getIntent().getExtras();
		String message = bundle.getString("message");
		String x;

		Cursor c = db.rawQuery("SELECT * FROM warrior WHERE name='" + message + "'", null);
		if (c.moveToFirst()) {

			T1.setText(c.getString(0));
			T2.setText(c.getString(1) + " Side");
			T3.setText(c.getString(2));
			T4.setText(c.getString(3));
			T5.setText(c.getString(4));
			T6.setText(c.getString(5));
			T7.setText(c.getString(6));

			if (T2.getText().toString().contentEquals("Light Side")) {
				root.setBackgroundResource(R.drawable.jedi1);
				T0.setText("Peace.Knowledge.The Force");
			} else {
				root.setBackgroundResource(R.drawable.sith3);
				T0.setText("Power.Passion.Order");

			}
		}
		Cursor c1 = dbimg.rawQuery("SELECT * FROM warriorimg WHERE name='" + message + "'", null);
		if (c1.moveToFirst()) {
			x = c1.getString(0);

			// byte[] imag =c.getBlob(c.getColumnIndex("a"));
			// Bitmap bmp =BitmapFactory.decodeByteArray(imag,0,imag.length);
			File imgFile = new File(x);
		

			if (imgFile.exists()) {

				Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

				I.setImageBitmap(myBitmap);

			}

		
		}

	}

	@Override
	public void onBackPressed()
	{
	   
	 finish();
	}
}
