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
 * 通关界面
 * 
 * @author wb
 *
 */
public class AllPassView extends Activity{
	
	//初始化返回按钮
	private ImageButton back_button_icon;
	
	//金币View
	private TextView mCurrentCoins;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.all_pass_view);
		
		//隐藏右上角的金币按钮
		FrameLayout view = (FrameLayout) findViewById(R.id.layout_bar_coin);
		view.setVisibility(View.INVISIBLE);
		
		mCurrentCoins = (TextView) findViewById(R.id.txt_bar_coins);
		
		//点击返回按钮
		back_button_icon = (ImageButton) findViewById(R.id.btn_bar_back);
		back_button_icon.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//返回到第一关，并保存这一次通关的数据
				Util.saveData(AllPassView.this, -1, Integer.parseInt(mCurrentCoins.getText().toString()));
				
				Util.startAcivity(AllPassView.this, MainActivity.class);
			}
		});
	}
}
