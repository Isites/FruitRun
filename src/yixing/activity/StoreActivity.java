package yixing.activity;

import yixing.highscore.HighscoreAdapter;
import yixing.util.ShareSimpleData;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.Toast;

public class StoreActivity extends Activity{

	CheckBox one, two, three, four, 
		fly_one, fly_two, double_fly_one, double_fly_two,
		resurrection_one, resurrection_two, double_coin_one,
		double_coin_two;
	@Override
    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);  
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store); 
        
        fly_one = (CheckBox) findViewById(R.id.fly_first);
        fly_two = (CheckBox) findViewById(R.id.fly_second);
        double_fly_one = (CheckBox) findViewById(R.id.double_fly_first);
        double_fly_two = (CheckBox) findViewById(R.id.double_fly_second);
        resurrection_one = (CheckBox) findViewById(R.id.resurrection_first);
        resurrection_two = (CheckBox) findViewById(R.id.resurrection_second);
        double_coin_one = (CheckBox) findViewById(R.id.double_coin_first);
        double_coin_two = (CheckBox) findViewById(R.id.double_coin_second);
		
		one = (CheckBox) findViewById(R.id.first);
		two = (CheckBox) findViewById(R.id.second);
		three = (CheckBox) findViewById(R.id.third);
		four = (CheckBox) findViewById(R.id.fourth);
	}

	public void back(View view){
		this.finish();
	}
	
	float coin = 0;
	double coinBuy = 0;
	Float flyNumber = 0f;
	int resurrectionNumber = 0, doubleCoinNumber = 0, 
			coinNumber = 0, doubleFlyNumber = 0;
		int flyNumberBuy = 0, resurrectionNumberBuy = 0, doubleCoinNumberBuy = 0, 
				doubleFlyNumberBuy = 0;
	public void yes(View view){
		if(!fly_one.isChecked() && !fly_two.isChecked() &&
				!double_fly_one.isChecked() && !double_fly_two.isChecked()
				&& !resurrection_one.isChecked() && !resurrection_two.isChecked()
				&& !double_coin_one.isChecked() &&!double_coin_two.isChecked() &&
				!one.isChecked() && !two.isChecked()
				&& !three.isChecked() && !four.isChecked()){
			Toast.makeText(getApplicationContext(), 
					"请在要购买的任一方框中打钩！", Toast.LENGTH_SHORT).show();
		}else{
			
			if(fly_one.isChecked())
				flyNumber = (float) 0.01;
			if(fly_two.isChecked())
				flyNumberBuy = 5000;
			
			if(resurrection_one.isChecked())
				resurrectionNumber = 2;
			if(resurrection_two.isChecked())
				resurrectionNumberBuy = 5000;
			
			if(double_coin_one.isChecked())
				doubleCoinNumber = 4;
			if(double_coin_two.isChecked())
				doubleCoinNumberBuy = 5000;

			if(double_fly_one.isChecked())
				doubleFlyNumber = 1;
			if(double_fly_two.isChecked())
				doubleFlyNumberBuy = 5000;
			
			if(one.isChecked())
				coinNumber = 1;
			if(two.isChecked())
				coinNumber = coinNumber + 6;
			if(three.isChecked())
				coinNumber = coinNumber + 8;
			if(four.isChecked())
				coinNumber = coinNumber + 10;
					
			coin = flyNumber + resurrectionNumber +
					doubleCoinNumber  + doubleFlyNumber + coinNumber;
			coinBuy = flyNumberBuy + resurrectionNumberBuy +
					doubleCoinNumberBuy + doubleFlyNumberBuy;
			popWindow(coin, flyNumber, resurrectionNumber ,
					doubleCoinNumber, doubleFlyNumber, coinNumber);
			
		}
	}
	
	private void popWindow(final float coin, final double fly, 
			final int resurrection, final int doubleCoin, final int doubleFly
			, final int coinNumber){
		final Context contxt = StoreActivity.this;
		HighscoreAdapter score = new HighscoreAdapter(StoreActivity.this);
        score.open();

        score.close();
		final AlertDialog.Builder builder = new AlertDialog.Builder(contxt);
		
		builder.setTitle("确定花费" + coin + "元购买？");
	
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				//调用支付SDK
				
				
				popWindow(StoreActivity.this.coinBuy, 
						flyNumberBuy, resurrectionNumberBuy, 
						doubleCoinNumberBuy, doubleFlyNumberBuy);
			}
		});
		
		builder.setNegativeButton("返回", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(getApplicationContext(), "取消购买！", Toast.LENGTH_SHORT).show();
			
				popWindow(StoreActivity.this.coinBuy, 
						flyNumberBuy, resurrectionNumberBuy, 
						doubleCoinNumberBuy, doubleFlyNumberBuy);
				StoreActivity.this.coin = 0;
				StoreActivity.this.flyNumber = 0f;
				StoreActivity.this.resurrectionNumber = 0;
				StoreActivity.this.doubleCoinNumber = 0;
				StoreActivity.this.coinNumber = 0;
				StoreActivity.this.doubleFlyNumber = 0;
				
			}
		});
		
		final AlertDialog dialogs = builder.create();

		dialogs.show();
		dialogs.setCanceledOnTouchOutside(false);
	}
	final String USED_COIN_TAG = "usedCoin";
	private final String FLY_TAG = "fly";
	private final String DOUBLE_FLY_TAG = "doubleFly";
	private final String RESURRECTION_TAG = "resurrection";
	private final String DOUBLE_TAG = "doubleCoin";
	ShareSimpleData share;
	private void popWindow(final double coin, final int fly, 
			final int resurrection, final int doubleCoin, final int doubleFly){
		final Context contxt = StoreActivity.this;
		HighscoreAdapter score = new HighscoreAdapter(StoreActivity.this);
        score.open();
        share = new ShareSimpleData(StoreActivity.this);
        final int scores = score.getScoreTotal() - 
        		share.getInt(USED_COIN_TAG);

        score.close();
		final AlertDialog.Builder builder = new AlertDialog.Builder(contxt);
		
		if(scores < coin)
			builder.setTitle("金币不够，请到菜单界面点击金币购买！");
		else
			builder.setTitle("确定花费" + coin + "个金币购买？");
	
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				if(scores >= coin){
					share.putFloat(USED_COIN_TAG, (float) (StoreActivity.this.coin + 
							share.getFloat(USED_COIN_TAG)));
					
					share.putInt(FLY_TAG, fly);
					share.putInt(DOUBLE_TAG, doubleCoin);
					share.putInt(RESURRECTION_TAG, resurrection);
					share.putInt(DOUBLE_FLY_TAG, doubleFly);
				}
			}
		});
		
		builder.setNegativeButton("返回", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(getApplicationContext(), "取消购买！", Toast.LENGTH_SHORT).show();
				StoreActivity.this.coinBuy = 0;
				StoreActivity.this.flyNumberBuy = 0;
				StoreActivity.this.resurrectionNumberBuy = 0;
				StoreActivity.this.doubleCoinNumberBuy = 0;
				StoreActivity.this.doubleFlyNumberBuy = 0;
			}
		});
		
		final AlertDialog dialogs = builder.create();

		dialogs.show();
		dialogs.setCanceledOnTouchOutside(false);
	}
}
