/* �m�ߤG
 * 
 * ������q������}�l�b�Q �n������m�ߩO ��O½½������Yapp
 * �ݨ�F�X��N�M�w
 * 
 * �ĤG�ӽm�߬O�X��
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
 * �o���FAndroid�ۤv��API���~
 * ���Q�Ψ�Java DecimalFormat���O��API
 * http://docs.oracle.com/javase/7/docs/api/java/text/DecimalFormat.html
 * 
 * DecimalFormat�O�Φb�Q�i��榡�ƪ��γ~
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
//�o�̥D�n�O��@�ӫ��s���ӥΡA�ҥH�|���@�ӥ��L�ȷ�@�������H��	
		
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
//���U���}���s�ᰱ��app���B�@�A�]���|�b�I���~��B�@
		exitBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				android.os.Process.killProcess(android.os.Process.myPid());
			}
		});
	}
//�i��ɶ���s�ϥ�
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
