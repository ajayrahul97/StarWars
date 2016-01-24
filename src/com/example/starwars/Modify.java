package com.example.starwars;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class Modify extends Activity
		implements OnClickListener, OnCheckedChangeListener, android.widget.RadioGroup.OnCheckedChangeListener {

	Button b, img, changeDate;
	SQLiteDatabase db,dbimg;
	TextView Output;
	private String[] arspinner1, arspinner2;
	String side1, species1, gender1, lastdate, lastplace, mobno,imageuri;
	ImageView imgview;
	EditText name, number;
	Spinner s1, s2;
	
	private int year;
	private int month;
	private int day;

	static final int DATE_PICKER_ID = 1111;
	protected static final int REQUEST_CAMERA = 0;
	protected static final int SELECT_FILE = 1;
	// DatePicker date;
	RadioGroup r1, r2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add);

	
		setitup();

		// Get current date by calender

		final Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);

		// Show current date

		Output.setText(new StringBuilder()
				// Month is 0 based, just add 1
				.append(month + 1).append("-").append(day).append("-").append(year).append(" "));

		// Button listener to show date picker dialog

		changeDate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				showDialog(DATE_PICKER_ID);
			}

		});

		r1.setOnCheckedChangeListener(this);
		r2.setOnCheckedChangeListener(this);


		db = openOrCreateDatabase("WarriorsDB", Context.MODE_PRIVATE, null);
		dbimg = openOrCreateDatabase("WarriorsDBIMAGE", Context.MODE_PRIVATE, null);

		/*db.execSQL(
				"CREATE TABLE IF NOT EXISTS warrior(name VARCHAR,side VARCHAR,species VARCHAR,gender VARCHAR,dt VARCHAR,place VARCHAR,num VARCHAR);");
		dbimg.execSQL("CREATE TABLE IF NOT EXISTS warriorimg(a VARCHAR,name VARCHAR)");*/
		b.setOnClickListener(this);
		img.setOnClickListener(this);

	}

	@Override
	public void onClick(View arg0) {
		species1 = s1.getSelectedItem().toString();
		lastplace = s2.getSelectedItem().toString();
		Bundle bundle = getIntent().getExtras();
		String message1 = bundle.getString("message");

		if (arg0 == b) {
			if (name.getText().toString().trim().length() == 0 ) {
				showMessage("Error", "Please Enter all details");
				return;

			}
			if (side1==null ) {
				showMessage("Error", "Please Enter all details");
				return;

			}
			if (gender1==null ) {
				showMessage("Error", "Please Enter all details");
				return;

			}
			if (lastplace=="Select" ) {
				showMessage("Error", "Please Enter all details");
				return;

			}
			if (species1=="Select" ) {
				showMessage("Error", "Please Enter all details");
				return;

			}
			
			if (number.getText().toString().trim().length() == 0) {
				showMessage("Error", "Please Enter all details");
				return;

			}
			
			db.execSQL("UPDATE warrior SET name='"+name.getText()+"',side='"+side1+"',species='"+species1+"',gender='"+gender1+"',dt='"+Output.getText().toString()+"',place='"+lastplace+"',num='"+number.getText()+"' WHERE name='"+message1+"'");
			
			showMessage("Success", "Record modified");
			Intent opensp = new Intent(Modify.this,Options.class);
			startActivity(opensp);
			clearText();
		}
		if (arg0 == img) {
			selectImage();

		}

	}

	private void clearText() {
		// TODO Auto-generated method stub
		name.setText("");
	}

	private void showMessage(String title, String message) {
		// TODO Auto-generated method stub
		Builder builder = new Builder(this);
		builder.setCancelable(true);
		builder.setTitle(title);
		builder.setMessage(message);
		builder.show();

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			if (requestCode == REQUEST_CAMERA) {
				Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
				ByteArrayOutputStream bytes = new ByteArrayOutputStream();

				thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
				/*byte[] img = bytes.toByteArray();
				ContentValues values = new ContentValues();
				values.put("a", img); // values.put("image", img);
				dbimg.insert("warriorimg",null,values);*/

				//thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
				File destination = new File(Environment.getExternalStorageDirectory(),
						System.currentTimeMillis() + ".jpg");
				FileOutputStream fo;
				imageuri=destination.getAbsolutePath();
				Bundle bundle = getIntent().getExtras();
				String message1 = bundle.getString("message");
				//dbimg.execSQL("INSERT INTO warriorimg VALUES('" + imageuri +"','" + name.getText() + "');");
				dbimg.execSQL("UPDATE warrior SET a='"+imageuri+"' WHERE name='"+message1+"'");

				try {
					destination.createNewFile();
					fo = new FileOutputStream(destination);
					fo.write(bytes.toByteArray());
					fo.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				imgview.setImageBitmap(thumbnail);
			} else if (requestCode == SELECT_FILE) {
				Uri selectedImageUri = data.getData();
				String[] projection = { MediaColumns.DATA };
				CursorLoader cursorLoader = new CursorLoader(this, selectedImageUri, projection, null, null, null);
				Cursor cursor = cursorLoader.loadInBackground();
				int column_index = cursor.getColumnIndexOrThrow(MediaColumns.DATA);
				cursor.moveToFirst();
				String selectedImagePath = cursor.getString(column_index);
				Bitmap bm;
				BitmapFactory.Options options = new BitmapFactory.Options();
				options.inJustDecodeBounds = true;
				BitmapFactory.decodeFile(selectedImagePath, options);
				final int REQUIRED_SIZE = 200;
				int scale = 1;
				while (options.outWidth / scale / 2 >= REQUIRED_SIZE && options.outHeight / scale / 2 >= REQUIRED_SIZE)
					scale = 2;
				options.inSampleSize = scale;
				options.inJustDecodeBounds = false;
				bm = BitmapFactory.decodeFile(selectedImagePath, options);
				imgview.setImageBitmap(bm);
			}
		}
	}

	/*
	 * protected void onActivityResult(int requestCode, int resultCode, Intent
	 * data) { // TODO Auto-generated method stub
	 * super.onActivityResult(requestCode, resultCode, data); if (resultCode ==
	 * RESULT_OK) { Bundle extras = data.getExtras(); Bitmap bmp = (Bitmap)
	 * extras.get("data"); imgview.setImageBitmap(bmp);
	 * 
	 * ByteArrayOutputStream bytes = new ByteArrayOutputStream();
	 * 
	 * bmp.compress(Bitmap.CompressFormat.JPEG, 100, bytes); byte[] img =
	 * bytes.toByteArray();
	 * 
	 * values.put("yo", img);
	 * 
	 * }
	 * 
	 * }
	 */

	private void selectImage() {
		final CharSequence[] items = { "Take Photo", "Choose from Library", "Cancel" };
		AlertDialog.Builder builder = new AlertDialog.Builder(Modify.this);
		builder.setTitle("Add Photo!");
		builder.setItems(items, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int item) {
				if (items[item].equals("Take Photo")) {
					Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					startActivityForResult(intent, REQUEST_CAMERA);
				} else if (items[item].equals("Choose from Library")) {
					Intent intent = new Intent(Intent.ACTION_PICK,
							android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
					intent.setType("image/*");
					startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
				} else if (items[item].equals("Cancel")) {
					dialog.dismiss();
				}
			}
		});
		builder.show();
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_PICKER_ID:

			// open datepicker dialog.
			// set date picker for current date
			// add pickerListener listner to date picker
			return new DatePickerDialog(this, pickerListener, year, month, day);
		}
		return null;
	}

	private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {

		// when dialog box is closed, below method will be called.
		@Override
		public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {

			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;

			// Show selected date
			Output.setText(
					new StringBuilder().append(month + 1).append("-").append(day).append("-").append(year).append(" "));

		}
	};

	private void setitup() {
		// TODO Auto-generated method stub
		b = (Button) findViewById(R.id.bsave);
		img = (Button) findViewById(R.id.bupload);
		number = (EditText) findViewById(R.id.number);
		imgview = (ImageView) findViewById(R.id.imgv);
		name = (EditText) findViewById(R.id.name);
		s1 = (Spinner) findViewById(R.id.spinner1);
		s2 = (Spinner) findViewById(R.id.spinner2);
		Output = (TextView) findViewById(R.id.Output);
		changeDate = (Button) findViewById(R.id.changeDate);
		// date= (DatePicker) findViewById(R.id.datep);
		r1 = (RadioGroup) findViewById(R.id.rAns);
		r2 = (RadioGroup) findViewById(R.id.rAns1);
		this.arspinner1 = new String[] { "Select","Human", "Mammalian", "Reptilian", "Avian", "Craniopod", "Droid", "Humanoid" };
		this.arspinner2 = new String[] {"Select", "Alderaan", "Bespin", "Coruscant", "Dagobah", "Endor", "Geonosis", "Hoth",
				"Kamino", "Mustafar", "Naboo", };

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arspinner1);
		s1.setAdapter(adapter);

		ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
				arspinner2);
		s2.setAdapter(adapter1);

	}

	@Override
	public void onCheckedChanged(RadioGroup arg0, int arg1) {
		// TODO Auto-generated method stub
		switch (arg1) {
		case R.id.r1:
			side1 = "Light";
			break;

		case R.id.r2:
			side1 = "Dark";
			break;

		case R.id.rb1:
			gender1 = "Male";
			break;

		case R.id.rb2:
			gender1 = "Female";
			break;
		case R.id.rb3:
			gender1 = "Machine";
			break;

		}

	}
	
	@Override
	public void onBackPressed()
	{
	   
	 finish();
	}

	@Override
	public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
		// TODO Auto-generated method stub

	}

}
