package com.love.guessmusic.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.love.guessmusic.R;
import com.love.guessmusic.data.Const;
import com.love.guessmusic.model.IAlertDialogButtonListener;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView.FindListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class Util {
	
	private static AlertDialog mAlertDialog;
	
	//静态方法，不需要new类对象，直接调用此方法即可。一般作为工具类方法
	public static View getView(Context context,int layoutId){
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		
		View layout = inflater.inflate(layoutId, null);
		return layout;
	}
	
	/**
	 * 界面跳转
	 * @param context
	 * @param Desti
	 */
	public static void startAcivity(Context context, Class Desti){
		Intent intent = new Intent();
		intent.setClass(context, Desti);
		context.startActivity(intent);
		
		//关闭当前的Activity
		((Activity) context).finish();
	}
	
	/**
	 * 显示自定义对话框
	 * @param context
	 * @param message
	 * @param listener
	 */
	public static void showDialog(final Context context, String message, 
			final IAlertDialogButtonListener listener){
		
		View dialogView = null;
		
		AlertDialog.Builder builder = new AlertDialog.Builder(context,
				R.style.Theme_Transparent);
		dialogView = getView(context, R.layout.dialog_view);
	
		ImageButton btnOkView = (ImageButton) dialogView.findViewById(R.id.btn_dialog_ok);
		ImageButton btnCancelView = (ImageButton) dialogView.findViewById(R.id.btn_dialog_cancel);
		TextView txtMessageView = (TextView) dialogView.findViewById(R.id.text_dialog_message);
	
		txtMessageView.setText(message);
		btnOkView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// 关闭对话框
				if(mAlertDialog != null){
					mAlertDialog.cancel();
				}
				
				//事件回调
				if(listener != null){
					listener.onClick();
				}
				
				//播放音效
				MyPlayer.playTone(context, MyPlayer.INEDX_STONE_ENTER);
			}
		});
		
		btnCancelView.setOnClickListener(new View.OnClickListener() {
					
			@Override
			public void onClick(View arg0) {
				// 关闭对话框
				if(mAlertDialog != null){
					mAlertDialog.cancel();
				}
				
				//播放音效
				MyPlayer.playTone(context, MyPlayer.INEDX_STONE_CANCEL);
			}
		});
		
		//为dialog设置view
		builder.setView(dialogView);
		mAlertDialog = builder.create();
		
		//显示对话框
		mAlertDialog.show();
	}
	
	/**
	 * 游戏数据保存
	 * @param context
	 * @param stageIndex
	 * @param coins
	 */
	public static void saveData(Context context, int stageIndex,
			int coins){
		FileOutputStream fos = null;
		
		try {
			fos = context.openFileOutput(Const.FILE_NAME_SAVE_DATA, Context.MODE_PRIVATE);
			DataOutputStream dos = new DataOutputStream(fos);
			
			dos.writeInt(stageIndex);
			dos.writeInt(coins);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(fos != null){
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 读取游戏数据
	 * @param context
	 * @return
	 */
	public static int[] loadData(Context context){
		FileInputStream fis = null;
		int[] datas = {-1, Const.TOTAL_COINS};
		
		try {
			fis = context.openFileInput(Const.FILE_NAME_SAVE_DATA);
		
			DataInputStream dis = new DataInputStream(fis);
			
			datas[Const.INDEX_LOAD_DATA_STAGE] = dis.readInt();
			datas[Const.INDEX_LOAD_DATA_COINS] = dis.readInt();
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(fis != null){
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return datas;
	}

}

