package com.example.starwars;



import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Listmod2 extends ListActivity{
	
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
		
		setListAdapter(new ArrayAdapter<String>(Listmod2.this, android.R.layout.simple_list_item_1, array));
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, final int position, long id) {
		// TODO Auto-generated method stub
		
		super.onListItemClick(l, v, position, id);
		final CharSequence[] items = { "Yes", "Cancel" };
		AlertDialog.Builder builder = new AlertDialog.Builder(Listmod2.this);
		builder.setTitle("Are You Sure You Wanna Select?");
		builder.setItems(items, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int item) {
				String abcd= array[position];

				if (items[item].equals("Yes")) {		
						
					Intent person = new Intent();
					Bundle bag= new Bundle();
					bag.putString("mess",abcd);
					person.putExtras(bag);
					setResult(12,person);
					finish();
					
				} else if (items[item].equals("Cancel")) {
					dialog.dismiss();
				}
			}
		});
		builder.show();
		
		
		
		
	}

	@Override
	public void onBackPressed()
	{
	   
	 finish();
	}	
	

}

