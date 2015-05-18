package yixing.activity;

import yixing.fruitrun.Settings;
import yixing.highscore.HighscoreAdapter;
import yixing.util.ShareSimpleData;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;


public class Menu extends Activity {
	final String USED_COIN_TAG = "usedCoin";
	MediaPlayer menuLoop;
	private Toast loadMessage;
	private Runnable gameLauncher;
	private Intent gameIntent;
	private Handler mHandler;
	private android.widget.Button mPlayButton;
	private TextView scoreTotal;
    @Override
    public void onCreate(Bundle savedInstanceState) {

    	requestWindowFeature(Window.FEATURE_NO_TITLE);  
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.menu); 
        
		loadMessage = Toast.makeText(getApplicationContext(), "Loading Game...", Toast.LENGTH_SHORT );
		loadMessage.setGravity(Gravity.CENTER|Gravity.CENTER, 0, 0);
        
		gameIntent = new Intent (this, main.class);
		mPlayButton = (android.widget.Button)findViewById(R.id.startButton);
		mPlayButton.setClickable(true);
		mPlayButton.setEnabled(true);
		gameLauncher = new Runnable() {
			
			public void run() {
				mPlayButton.setClickable(false);
		    	mPlayButton.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
				startActivityForResult(gameIntent, 0);
			}
		};
		
		mHandler = new Handler();
		
		
		
        /*
        menuLoop = MediaPlayer.create(getApplicationContext(), R.raw.menu);  
        menuLoop.setLooping(true);
        menuLoop.seekTo(0);
        menuLoop.setVolume(0.5f, 0.5f);
        menuLoop.start();
        */
    }
    
    public void showScore(){
    	final Handler handler = new Handler();

		handler.postDelayed(new Runnable() {
			
			public void run() {
				HighscoreAdapter score = new HighscoreAdapter(Menu.this);
		        score.open();
		        
		    	scoreTotal = (TextView) findViewById(R.id.score_total);
		        int scores = score.getScoreTotal() - new ShareSimpleData(Menu.this).getInt(USED_COIN_TAG);
		        scoreTotal.setText(scores + "");
		        scoreTotal.setOnClickListener(new OnClickListener(){
					public void onClick(View v) {
						Intent intent = new Intent(Menu.this, 
								StoreActivity.class);
				    	startActivity(intent);
					}
		        });
		        score.close();
		        Log.d("Menu", scores + "");
			}
		}, 500);
    }
    
    public void store(View view){
    	Intent intent = new Intent(Menu.this, 
				StoreActivity.class);
    	startActivity(intent);
    }
    public void coinClick(View view){
    	Intent intent = new Intent(Menu.this, 
				StoreActivity.class);
    	startActivity(intent);
    }
    public void help(View view){
    	Intent intent = new Intent(this, HelpActivity.class);
    	startActivity(intent);
    }
    public void playGame(View view) {

		// Loading Toast
		loadMessage.show();
    	Settings.SHOW_FPS = false;
    	mHandler.post(gameLauncher);
    }
    
    public void playGameWithFPS(View view) {

		// Loading Toast
		loadMessage.show();
    	Settings.SHOW_FPS = true;
    	mHandler.post(gameLauncher);
    }
    
    public void showScore(View view) {
    	Intent myIntent = new Intent (this, HighScoreActivity.class);
    	startActivity (myIntent);
    }

    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
    	if (resultCode == 1) {
    		showDialog(1);
    		mHandler.postDelayed(new Runnable() {
				
				public void run() {
					mPlayButton.setClickable(true);
					mPlayButton.getBackground().clearColorFilter();
				}
			}, 10000);
    	} else {
    		mPlayButton.setClickable(true);
    		mPlayButton.getBackground().clearColorFilter();
    	}
    }
    
    protected Dialog onCreateDialog(int id) {
    	return new AlertDialog.Builder(this)
		  .setTitle("Error while changing view")
		  .setMessage("System needs some time to free memory. Please try again in 10 seconds.")
		  .setCancelable(true)
		  .create();
    }

    @Override
    public void onResume(){
    	super.onResume();
    	Log.d("Menu", "resume");
    	showScore();
    }
    @Override
    public void onDestroy(){
    	super.onDestroy();
    }
}
