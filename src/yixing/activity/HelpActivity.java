package yixing.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class HelpActivity extends Activity{

	@Override
    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);  
    	getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    	
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help); 
	}
	
	public void close(View view){
		this.finish();
	}
}
