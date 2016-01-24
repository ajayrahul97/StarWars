package com.example.starwars;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Compare2 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.compare);
		Button b1 = (Button) findViewById(R.id.tv1);
		Button b2 = (Button) findViewById(R.id.tv1);
		TextView t1 = (TextView)findViewById(R.id.et1);
		TextView t2 = (TextView)findViewById(R.id.editText1);

		

		b1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				Intent opensp = new Intent(Compare2.this, Listcompare.class);
				startActivity(opensp);

			}

		});

		b2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				Intent opensp = new Intent(Compare2.this, Listmod2.class);
				startActivity(opensp);
				

			}

		});

		Bundle bundle = getIntent().getExtras();
		final String message = bundle.getString("message");
		final String mess = bundle.getString("mess");
		t1.setText(message);
		t2.setText(mess);
		
		b2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				Intent myintent= new Intent(Compare2.this, Discom.class);
				myintent.putExtra("warrior1",message);
				myintent.putExtra("warrior2", mess);		
				startActivity(myintent);

			}

		});
		
		

	}

}
