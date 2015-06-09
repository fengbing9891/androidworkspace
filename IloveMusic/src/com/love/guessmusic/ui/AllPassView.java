package com.love.guessmusic.ui;

import com.love.guessmusic.R;
import com.love.guessmusic.util.Util;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * ͨ�ؽ���
 * 
 * @author wb
 *
 */
public class AllPassView extends Activity{
	
	//��ʼ�����ذ�ť
	private ImageButton back_button_icon;
	
	//���View
	private TextView mCurrentCoins;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.all_pass_view);
		
		//�������ϽǵĽ�Ұ�ť
		FrameLayout view = (FrameLayout) findViewById(R.id.layout_bar_coin);
		view.setVisibility(View.INVISIBLE);
		
		mCurrentCoins = (TextView) findViewById(R.id.txt_bar_coins);
		
		//������ذ�ť
		back_button_icon = (ImageButton) findViewById(R.id.btn_bar_back);
		back_button_icon.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//���ص���һ�أ���������һ��ͨ�ص�����
				Util.saveData(AllPassView.this, -1, Integer.parseInt(mCurrentCoins.getText().toString()));
				
				Util.startAcivity(AllPassView.this, MainActivity.class);
			}
		});
	}
}
