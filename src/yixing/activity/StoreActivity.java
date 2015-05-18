package yixing.activity;

import yixing.highscore.HighscoreAdapter;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

public class StoreActivity extends Activity{

	@Override
    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);  
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store); 
        
        flyEdit = (EditText) findViewById(R.id.fly);
		resurrectionEdit = (EditText) findViewById(R.id.resurrection);
		doubleCoinEdit = (EditText) findViewById(R.id.double_coin);
		buyCoin = (EditText)findViewById(R.id.buy_coin);
	}

	public void back(View view){
		this.finish();
	}
	
	public void yes(View view){
		if(flyEdit.length() == 0 && resurrectionEdit.length() == 0
				&& doubleCoinEdit.length() == 0 && buyCoin.length() == 0){
			Toast.makeText(getApplicationContext(), 
					"请输入购买道具或金币数量！", Toast.LENGTH_SHORT).show();
		}else{
			int flyNumber, resurrectionNumber, doubleCoinNumber, coinNumber;
			if(flyEdit.length() == 0)
				flyNumber = 0;
			else
				flyNumber = Integer.parseInt(
					flyEdit.getText().toString());
			
			if(resurrectionEdit.length() == 0)
				resurrectionNumber = 0;
			else
				resurrectionNumber = Integer.parseInt(
					resurrectionEdit.getText().toString());
			
			if(doubleCoinEdit.length() == 0)
				doubleCoinNumber = 0;
			else
				doubleCoinNumber = Integer.parseInt(
					doubleCoinEdit.getText().toString());
			
			if(buyCoin.length() == 0)
				coinNumber = 0;
			else
				coinNumber = Integer.parseInt(
						buyCoin.getText().toString());
						
			double coin = flyNumber * 0.5 + resurrectionNumber +
					doubleCoinNumber * 0.5 + coinNumber;
			popWindow(coin, flyNumber, resurrectionNumber, 
					doubleCoinNumber);
		}
	}
	
	private void popWindow(final double coin, final int fly, final int resurrection, final int doubleCoin){
		final Context contxt = StoreActivity.this;
		HighscoreAdapter score = new HighscoreAdapter(StoreActivity.this);
        score.open();

        score.close();
		final AlertDialog.Builder builder = new AlertDialog.Builder(contxt);
		
		builder.setTitle("确定花费" + coin + "元购买？");
	
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				//调用支付SDK
			}
		});
		
		builder.setNegativeButton("返回", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(getApplicationContext(), "取消购买！", Toast.LENGTH_SHORT).show();
			}
		});
		
		final AlertDialog dialogs = builder.create();

		dialogs.show();
		dialogs.setCanceledOnTouchOutside(false);
	}
	EditText buyCoin, doubleCoinEdit, flyEdit, resurrectionEdit;
   
}
