package com.lfrj.diancan.http;

public class RequestClient {

	private static AsyncHttpClient mClient = new AsyncHttpClient();
	static {
		mClient.setTimeout(10*1000);
		mClient.setConnectTimeout(10*1000);
	}
	public static void post(String url, AsyncHttpResponseHandler handler) {
		post(url, null, handler);
	}

	public static void post(String url, RequestParams params,AsyncHttpResponseHandler handler) {
		mClient.post(url, params, handler);
	}
	public static void postWithToken(String token ,String url, RequestParams params,AsyncHttpResponseHandler handler) {
		mClient.post(url, params, handler);
		mClient.addHeader("token", token);
	}

	public static void get(String url, AsyncHttpResponseHandler handler) {
		mClient.get(url, null, handler);
	}
	public static void getWithToken(String token ,String url, AsyncHttpResponseHandler handler) {
		mClient.addHeader("token", token);
		mClient.get(url, null, handler);
	}
	public static void getWithToken(String token ,RequestParams params,String url, AsyncHttpResponseHandler handler) {
		mClient.addHeader("token", token);
		mClient.get(url, params, handler);
	}
	public static void get(String url, RequestParams params,AsyncHttpResponseHandler handler) {
		mClient.get(url, params, handler);
	}
}
