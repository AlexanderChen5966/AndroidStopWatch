/* 練習二
 * 
 * 做完手電筒之後開始在想 要做什麼練習呢 於是翻翻手機裡頭app
 * 看到了碼表就決定
 * 
 * 第二個練習是碼表
 * 
 * */

package com.example.stopwatch2;

import java.text.DecimalFormat;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
/*
 * 這除了Android自己的API之外
 * 有利用到Java DecimalFormat類別的API
 * http://docs.oracle.com/javase/7/docs/api/java/text/DecimalFormat.html
 * 
 * DecimalFormat是用在十進位格式化的用途
 * */
public class MainActivity extends Activity {

	private Button startpauseBtn,exitBtn;
	private TextView showTimer;
	private Long intCnt ;
	private Boolean flag = true;
	private Handler handler = new Handler();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		initHandler();
	}

	private void initView() {
		// TODO Auto-generated method stub
		startpauseBtn = (Button)findViewById(R.id.startpauseButton);
		exitBtn = (Button)findViewById(R.id.exitButton);
		showTimer =(TextView)findViewById(R.id.timerTextView);
		startpauseBtn.setText(R.string.strStart);
		startpauseBtn.setBackgroundColor(Color.GREEN);
	}

	private void initHandler() {
		// TODO Auto-generated method stub
//這裡主要是把一個按鈕當兩個用，所以會有一個布林值當作切換的信號	
		
		startpauseBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(flag){
					flag = !flag;
					startpauseBtn.setText(R.string.strPause);
					startpauseBtn.setBackgroundColor(Color.RED);
					handler.removeCallbacks(updateTimer);
					handler.postDelayed(updateTimer, 1000);
				}else{
					flag = !flag;
					startpauseBtn.setText(R.string.strCountinue);
					startpauseBtn.setBackgroundColor(Color.GREEN);
					handler.removeCallbacks(updateTimer);

				}
			}
		});
//按下離開按鈕後停止app的運作，也不會在背景繼續運作
		exitBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				android.os.Process.killProcess(android.os.Process.myPid());
			}
		});
	}
//進行時間更新使用
	private Runnable updateTimer = new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			DecimalFormat nf = new DecimalFormat("00");
			intCnt = intCnt + 1;
			Long min = intCnt / 60;
			Long sec = intCnt % 60;
			//Long ms = intCnt %6000;
			showTimer.setText(nf.format(min)+":"+nf.format(sec)/*+":"+nf.format(ms)*/);
			handler.postDelayed(updateTimer, 1000);
		}
	};
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
