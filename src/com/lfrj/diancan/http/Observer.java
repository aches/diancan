package com.lfrj.diancan.http;

public interface Observer 
{
	public void register(String tag,Command command);
	public void removeObserver(String tag);
}
