package com.example.starwars;

import java.util.Random;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Game extends Activity {
	TextView num, res, time;
	Button submit;
	private View press;
	ImageView im;
	String a;
	String score1, score2;
	int ctr;

	Random r = new Random();
	int i1;

	CountDownTimer count = new CountDownTimer(20000, 1000) {

		public void onTick(long millisUntilFinished) {
			time.setText("Time remaining : " + millisUntilFinished / 1000 + " seconds!");
		}

		public void onFinish() {
			time.setText("Time Up!!");
			a = num.getText().toString();

			res.setText("Your Total Score Is " + a);
			press.setEnabled(false);
			submit.setEnabled(true);
		}
	};

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);
		ctr = 0;
		press = (Button) findViewById(R.id.bgame);
		submit = (Button) findViewById(R.id.dsubmit);
		num = (TextView) findViewById(R.id.dcount);
		res = (TextView) findViewById(R.id.dresult);
		time = (TextView) findViewById(R.id.dtime);
		im = (ImageView) findViewById(R.id.im);
		final ViewGroup.LayoutParams vg_lp = press.getLayoutParams();
		Bundle bundle = getIntent().getExtras();
		final String type = bundle.getString("messge");
		submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// Intent next= new Intent(getApplicationContext(),Menu.class);
				// startActivity(next);
				/*
				 * Intent opensp = new Intent(Game.this,Dbgame.class);
				 * opensp.putExtra("sith", a); startActivityForResult(opensp,
				 * 1); finish();
				 */
				if (type.equals("S")) {
					Intent person = new Intent();
					Bundle bag = new Bundle();
					bag.putString("mess", a);
					person.putExtras(bag);
					setResult(12, person);
					finish();
				}
				else
				{
					Intent person = new Intent();
					Bundle bag = new Bundle();
					bag.putString("message", a);
					person.putExtras(bag);
					setResult(10, person);
					finish();
					
				}

			}

		});
		press.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				ctr++;
				if (ctr == 1) {
					count.start();
				}

				num.setText(String.valueOf(ctr));
				i1 = r.nextInt(4 - 1) + 1;
				if (ctr % i1 == 0) {
					if (i1 == 1) {
						if (vg_lp instanceof RelativeLayout.LayoutParams) {
							RelativeLayout.LayoutParams rl_lp = new RelativeLayout.LayoutParams(vg_lp);
							rl_lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
							rl_lp.setMargins(20, 400, 200, 100);
							press.setLayoutParams(rl_lp);
						}

					}
					if (i1 == 2) {
						if (vg_lp instanceof RelativeLayout.LayoutParams) {
							RelativeLayout.LayoutParams rl_lp = new RelativeLayout.LayoutParams(vg_lp);
							rl_lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
							rl_lp.setMargins(230, 1000, 20, 130);
							press.setLayoutParams(rl_lp);
						}

					}
					if (i1 == 3) {
						if (vg_lp instanceof RelativeLayout.LayoutParams) {
							RelativeLayout.LayoutParams rl_lp = new RelativeLayout.LayoutParams(
									LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
							// rl_lp.addRule(RelativeLayout.BELOW);
							rl_lp.setMargins(20, 1000, 20, 100);
							press.setLayoutParams(rl_lp);

						}

					}

				}

				if (type.contentEquals("S")) {

					if (ctr % 2 == 0) {
						// im.setImageResource(R.drawable.j1);
						im.setImageResource(R.drawable.s1);

					} else {
						im.setImageResource(R.drawable.s2);
					}
				} else {

					if (ctr % 2 == 0) {
						// im.setImageResource(R.drawable.j1);
						im.setImageResource(R.drawable.j1);

					} else {
						im.setImageResource(R.drawable.j2);
					}
				}

			}

		});

	}

	@Override
	public void onBackPressed() {

		finish();
	}

}
