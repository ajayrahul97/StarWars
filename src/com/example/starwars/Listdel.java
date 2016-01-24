package com.example.starwars;



import android.app.AlertDialog.Builder;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Listdel extends ListActivity{
	
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
		
		setListAdapter(new ArrayAdapter<String>(Listdel.this, android.R.layout.simple_list_item_1, array));
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, final int position, long id) {
		// TODO Auto-generated method stub
		
		
		super.onListItemClick(l, v, position, id);
		
		
		final CharSequence[] items = { "Yes", "Cancel" };
		AlertDialog.Builder builder = new AlertDialog.Builder(Listdel.this);
		builder.setTitle("Are You Sure You Wanna Delete?");
		builder.setItems(items, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int item) {
				if (items[item].equals("Yes")) {
					String abcd= array[position];
					Cursor c=db.rawQuery("SELECT * FROM warrior ", null);
					if(c.moveToFirst())
					{
						db.execSQL("DELETE FROM warrior WHERE name='"+abcd+"'");
						array[position]=null;
						
						showMessage("Success", "Record Deleted");
						Intent opensp = new Intent(Listdel.this,Listdel.class);
						startActivity(opensp);
					}
					
				
										
					
				} else if (items[item].equals("Cancel")) {
					dialog.dismiss();
				}
			}
		});
		builder.show();
		
		
		
		
		
		
		
	}

	private void showMessage(String title, String message) {
		// TODO Auto-generated method stub
		Builder builder=new Builder(this);
    	builder.setCancelable(true);
    	builder.setTitle(title);
    	builder.setMessage(message);
    	builder.show();
		
	}


	@Override
	public void onBackPressed() {
	    //your code when back button pressed
		Intent opensp = new Intent(Listdel.this,Options.class);
		startActivity(opensp);
		finish();
	}
	
	

}

