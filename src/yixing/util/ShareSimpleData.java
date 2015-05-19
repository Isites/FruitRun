package yixing.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class ShareSimpleData {
	
	final String PREFERENCES_TAG = "WENT_PREFERENCES";
	private SharedPreferences.Editor editor;
	private SharedPreferences preferences;
	
	public ShareSimpleData(Context context){
		preferences = context
				.getSharedPreferences(PREFERENCES_TAG, Activity.MODE_PRIVATE);
		
		editor = preferences.edit();
	}
	
	public void putBoolean(String tag, boolean data){
		editor.putBoolean(tag, data);
		editor.apply();
	}
	
	public boolean getBoolean(String tag){
		return preferences.getBoolean(tag, false);
	}
	
	public void putString(String tag, String data){
		editor.putString(tag, data);
		editor.apply();
	}
	
	public String getString(String tag){
		return preferences.getString(tag, "");
	}
	
	public void putInt(String tag, int data){
		editor.putInt(tag, data);
		editor.apply();
	}
	public int getInt(String tag){
		return preferences.getInt(tag, 0);
	}
	
	public void putFloat(String tag, float data){
		editor.putFloat(tag, data);
		editor.apply();
	}
	
	public float getFloat(String tag){
		return preferences.getFloat(tag, 0f);
	}
}
