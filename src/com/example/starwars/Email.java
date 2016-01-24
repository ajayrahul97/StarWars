package com.example.starwars;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class Email extends Activity implements  View.OnClickListener{

	EditText sub,text;;
	String  subj,mes;
	Button bsend;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView (R.layout.email);
		setitup();
		bsend.setOnClickListener(this);
		
	}
	private void setitup() {
		// TODO Auto-generated method stub
		sub= (EditText) findViewById(R.id.et1);
		text= (EditText) findViewById(R.id.editText1);
	
		bsend= (Button) findViewById(R.id.bsendmail);
		
		
	}
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
	
		String emailaddress[] = {"ajayrahul97@gmail.com"};
		String message = text.getText().toString();
		Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
		emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, emailaddress);
		emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, sub.getText().toString());
		emailIntent.setType("plain/text");
		emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, message);
		startActivity(emailIntent);
		


		
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
	
	@Override
	public void onBackPressed()
	{
	   
	 finish();
	}
	
	
}
