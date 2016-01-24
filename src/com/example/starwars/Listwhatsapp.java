package com.example.starwars;



import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Listwhatsapp extends ListActivity{
	
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
		
		setListAdapter(new ArrayAdapter<String>(Listwhatsapp.this, android.R.layout.simple_list_item_1, array));
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		
		String abcd= array[position];
		String text = null;
		Cursor c=db.rawQuery("SELECT * FROM warrior WHERE name='"+abcd+"'", null);
		if(c.moveToFirst())
		{
			
			text="Hey Fellow Warrior Here are the details of "+c.getString(0)+"\n"+"Affiliation:"+ c.getString(1)+" Side"+"\n"+"Species :"+c.getString(2)+"\n"+"Gender"+c.getString(3)+"\n"+"Last Spotted On : "+c.getString(4)+"\n"+"Recent Location:"+c.getString(5)+"\n"+"Mobile No :"+c.getString(6);
			/*T1.setText(c.getString(0));
			T2.setText(c.getString(1)+" Side");
			T3.setText(c.getString(2));
			T4.setText(c.getString(3));
			T5.setText(c.getString(4));
			T6.setText(c.getString(5));
			T7.setText(c.getString(6));
			*/
		}
		super.onListItemClick(l, v, position, id);
	
		
		 PackageManager pm=getPackageManager();
		    try {

		        Intent waIntent = new Intent(Intent.ACTION_SEND);
		        waIntent.setType("text/plain");
		        //String text = "YOUR TEXT HERE";

		        PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
		        //Check if package exists or not. If not then code 
		        //in catch block will be called
		        waIntent.setPackage("com.whatsapp");

		        waIntent.putExtra(Intent.EXTRA_TEXT, text);
		        startActivity(Intent.createChooser(waIntent, "Share with"));

		   } catch (NameNotFoundException e) {
		        Toast.makeText(this, "WhatsApp not Installed", Toast.LENGTH_SHORT)
		                .show();
		   }  

		
	}

	@Override
	public void onBackPressed()
	{
	   
	 finish();
	}
	

}

