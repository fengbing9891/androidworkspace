package com.love.guessmusic.util;

import java.io.FileDescriptor;
import java.io.IOException;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;

/**
 * ���ֲ�����
 * @author wb
 *
 */
public class MyPlayer {

	//����
	public final static int INEDX_STONE_ENTER = 0;
	public final static int INEDX_STONE_CANCEL = 1;
	public final static int INEDX_STONE_COIN = 2;
	
	//��Ч���ļ���
	private final static String[] SONG_NAMES = 
		{"enter.mp3","cancel.mp3","coin.mp3"};
	//��Ч
	private static MediaPlayer[] mToneMediaPlayer = new MediaPlayer[SONG_NAMES.length];
	
	//��������     ����ɵ���ģʽ������ʼ����һ������
	private static MediaPlayer mMusicMediaPlayer;
	
	/**
	 * ��������Ч��
	 * @param context
	 * @param index
	 */
	public static void playTone(Context context, int index){
		//��������
		AssetManager assetManager = context.getAssets();
		
		if(mToneMediaPlayer[index] == null){
			mToneMediaPlayer[index] = new MediaPlayer();
			
			try {
				AssetFileDescriptor fileDescriptor = assetManager.openFd(SONG_NAMES[index]);
				mToneMediaPlayer[index].setDataSource(fileDescriptor.getFileDescriptor(), 
						fileDescriptor.getStartOffset(), fileDescriptor.getLength());
				
				mToneMediaPlayer[index].prepare();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//��Ч����
		mToneMediaPlayer[index].start();
		
	}
	
	public static void stopTheTone(Context context, int index){
		if(mToneMediaPlayer[index] != null){
			mToneMediaPlayer[index].stop();
		}
	}
	
	public static void playSong(Context context, String fileName){
		if(mMusicMediaPlayer == null){
			//��ʱΪidle״̬
			mMusicMediaPlayer = new MediaPlayer();
		}
		
		//ǿ������
		mMusicMediaPlayer.reset();
		
		// ���������ļ�
		AssetManager assetManager = context.getAssets();
		try {
			AssetFileDescriptor fileDescriptor = assetManager.openFd(fileName);
			mMusicMediaPlayer.setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(),
					fileDescriptor.getLength());
			
			mMusicMediaPlayer.prepare();
			
			//��������
			mMusicMediaPlayer.start();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void stopTheSong(Context context){
		if(mMusicMediaPlayer != null){
			mMusicMediaPlayer.stop();
		}
	}
	
}
