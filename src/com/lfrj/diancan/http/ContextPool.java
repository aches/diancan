package com.lfrj.diancan.http;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;

/**
 * Context context 是全局的不要传Activity的引用过来，否则会内存泄露
 *
 */
public class ContextPool 
{

	static Context applicationContext;
	public static void initialize(Context context)
	{
		applicationContext = context;
	}
	
	public static void saveUserToDisk(String token) {
		FileOutputStream outStream=null;
		try {
			outStream = applicationContext.openFileOutput("currentUser",Context.MODE_PRIVATE);
			outStream.write(token.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(outStream!=null)
				try {
					outStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	
	/**
	 * 
	 * @param context
	 * @return
	 */
	public static String getUserFromDisk() {

		FileInputStream inStream=null;
		try {
			inStream = applicationContext.openFileInput("currentUser");
			return readInStream(inStream);
		} catch (FileNotFoundException e) {
			return null;
		}
		finally{
			if(inStream!=null)
				try {
					inStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

	}
	/**
	 * 
	 * @param inStream
	 * @return
	 */
	private static String readInStream(FileInputStream inStream) {
		ByteArrayOutputStream outStream=null;
		try {
		    outStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int length = -1;
			while ((length = inStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, length);
			}
			outStream.close();
			inStream.close();
			return outStream.toString();
		} catch (IOException e) {
		}finally{
			if(outStream!=null)try {outStream.close();} catch (IOException e) {
					e.printStackTrace();
				}
			if(inStream!=null)try {inStream.close();} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static  void removeContext()
	{
		applicationContext=null;
	}
}
