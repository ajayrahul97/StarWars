package com.example.starwars;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Dbgame extends Activity {
	Button b, b2, fin;
	TextView t1, t2, result;
	String message, mess;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dbgame);
		b = (Button) findViewById(R.id.tv1);
		b2 = (Button) findViewById(R.id.t1);
		fin = (Button) findViewById(R.id.t);
		t1 = (TextView) findViewById(R.id.et1);
		t2 = (TextView) findViewById(R.id.editText1);
		result = (TextView) findViewById(R.id.bsendmail);
		b.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// Intent next= new Intent(getApplicationContext(),Menu.class);
				// startActivity(next);

				String a1 = "S";
				Intent opensp = new Intent(Dbgame.this, Game.class);
				opensp.putExtra("messge", a1);
				b.setEnabled(false);
				b2.setEnabled(true);
				startActivityForResult(opensp, 0);

			}
		});
		b2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// Intent next= new Intent(getApplicationContext(),Menu.class);
				// startActivity(next);
				String a2 = "Jedi";
				Intent opensp1 = new Intent(Dbgame.this, Game.class);
				opensp1.putExtra("messge", a2);
				b2.setEnabled(false);
				fin.setVisibility(View.VISIBLE);

				startActivityForResult(opensp1, 1);

			}
		});

		fin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String s1 = t1.getText().toString();
				String s2 = t2.getText().toString();
				int f = Integer.parseInt(s1);
				int f2 = Integer.parseInt(s2);
				if (f > f2) {
					result.setText("Sith Warrior Wins!");
				} else if (f == f2) {
					result.setText("Its A Tie");
				} else {
					result.setText("Jedi Warrior Wins!");
				}
				result.setVisibility(View.VISIBLE);

			}
		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1) {

			if (resultCode == 10) {
				Bundle basket = data.getExtras();
				message = basket.getString("message");
				t2.setText(message);

			}
		}

		if (requestCode == 0) {
			if (resultCode == 12) {
				Bundle basket = data.getExtras();
				mess = basket.getString("mess");
				t1.setText(mess);
			}
		}
	}
	@Override
	public void onBackPressed() {

		finish();
	}
}
