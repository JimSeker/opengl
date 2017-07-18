package com.androidbook.opengl;


import java.util.SortedMap;
import java.util.TreeMap;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public abstract class Menu extends ListActivity {
	private SortedMap<String, Object> actions = new TreeMap<String, Object>();

	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		String key = (String) l.getItemAtPosition(position);
		startActivity((Intent) actions.get(key));
	}
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		prepareMenu();

		String[] keys = actions.keySet().toArray(
				new String[actions.keySet().size()]);

		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, keys));
	}
	
	public void addMenuItem(String label, Class<?> cls){
		actions.put(label, new Intent(this, cls	));
	}
	
	abstract void prepareMenu();
}
