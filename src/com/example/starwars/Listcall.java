package com.example.starwars;



import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Listcall extends ListActivity{
	
	SQLiteDatabase db;
	
	 
	 String array[];
	
	//String classes[]={"Startingpoint","TextPlay","Email","Photo","Data","lass"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		db=openOrCreateDatabase("WarriorsDB", Context.MODE_PRIVATE, null);
		Cursor crs = db.rawQuery("SELECT * FROM warrior", null);
		 array= new String[crs.getCount()];
		int i = 0;
		while(crs.moveToNext()){
		    String uname = crs.getString(crs.getColumnIndex("name"));
		    array[i] = uname;
		    
		    i++;
		}
		
		setListAdapter(new ArrayAdapter<String>(Listcall.this, android.R.layout.simple_list_item_1, array));
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		
		String abcd= array[position];
		super.onListItemClick(l, v, position, id);
		Intent myintent= new Intent(Listcall.this, Discall.class);
		myintent.putExtra("message", abcd);
		
		startActivity(myintent);
		
		
	}

	@Override
	public void onBackPressed()
	{
	   
	 finish();
	}
	

}

