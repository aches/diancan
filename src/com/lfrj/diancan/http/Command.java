package com.lfrj.diancan.http;

/**
 * @author think
 * @param <T>
 *
 */
public interface Command
{
	public <T> void onFailure(int requestCode,int resultCode,T parameter);
	public <T> void onSuccess(int requestCode,int resultCode,T parameter);
}
