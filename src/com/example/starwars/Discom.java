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

public class Discom extends Activity {

	SQLiteDatabase db, dbimg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.display5);
		
		TextView T0 = (TextView) findViewById(R.id.dtitle);
		TextView T1 = (TextView) findViewById(R.id.dname);
		TextView T2 = (TextView) findViewById(R.id.dside);
		TextView T3 = (TextView) findViewById(R.id.dspecies);
		TextView T4 = (TextView) findViewById(R.id.dgender);
		TextView T5 = (TextView) findViewById(R.id.ddate);
		TextView T6 = (TextView) findViewById(R.id.dplace);
		TextView T7 = (TextView) findViewById(R.id.mobno);

		TextView T11 = (TextView) findViewById(R.id.d1);
		TextView T12 = (TextView) findViewById(R.id.d2);
		TextView T13 = (TextView) findViewById(R.id.d3);
		TextView T14 = (TextView) findViewById(R.id.d4);
		TextView T15 = (TextView) findViewById(R.id.d5);
		TextView T16 = (TextView) findViewById(R.id.d7);
		TextView T17 = (TextView) findViewById(R.id.d6);

		ImageView I = (ImageView) findViewById(R.id.im);
		ImageView I2 = (ImageView) findViewById(R.id.im2);

		db = openOrCreateDatabase("WarriorsDB", Context.MODE_PRIVATE, null);
		dbimg = openOrCreateDatabase("WarriorsDBIMAGE", Context.MODE_PRIVATE, null);

		Bundle bundle = getIntent().getExtras();
		String message = bundle.getString("warrior1");
		String mess = bundle.getString("warrior2");
		String x,x2;

		Cursor c = db.rawQuery("SELECT * FROM warrior WHERE name='" + mess + "'", null);
		if (c.moveToNext()) {

			T1.setText(c.getString(0));
			T2.setText(c.getString(1) + " Side");
			T3.setText(c.getString(2));
			T4.setText(c.getString(3));
			T5.setText(c.getString(4));
			T6.setText(c.getString(5));
			T7.setText(c.getString(6));

		}
		Cursor c1 = dbimg.rawQuery("SELECT * FROM warriorimg WHERE name='" + mess + "'", null);
		if (c1.moveToNext()) {
			x = c1.getString(0);

			// byte[] imag =c.getBlob(c.getColumnIndex("a"));
			// Bitmap bmp =BitmapFactory.decodeByteArray(imag,0,imag.length);
			File imgFile = new File(x);

			if (imgFile.exists()) {

				Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

				I2.setImageBitmap(myBitmap);

			}

		}
		
		Cursor c2 = db.rawQuery("SELECT * FROM warrior WHERE name='" + message + "'", null);
		if (c2.moveToNext()) {

			T11.setText(c2.getString(0));
			T12.setText(c2.getString(1) + " Side");
			T13.setText(c2.getString(2));
			T14.setText(c2.getString(3));
			T15.setText(c2.getString(4));
			T16.setText(c2.getString(5));
			T17.setText(c2.getString(6));

		}
		Cursor c3 = dbimg.rawQuery("SELECT * FROM warriorimg WHERE name='" + message + "'", null);
		if (c3.moveToNext()) {
			x2 = c3.getString(0);

			// byte[] imag =c.getBlob(c.getColumnIndex("a"));
			// Bitmap bmp =BitmapFactory.decodeByteArray(imag,0,imag.length);
			File imgFile = new File(x2);

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
