package com.lfrj.diancan.http;

import java.util.HashMap;

/**
 * 使用了强引用，调用完register后，一定要在Command 实例结束是释放引用，否则会有内存泄露
 *
 */
public class ObserverImp implements Observer {
	private static HashMap<String, Command> map = new HashMap<String, Command>();

	private static ObserverImp instance;

	public static ObserverImp getInstance() {
		if (instance == null) {
			instance = new ObserverImp();
		}
		return instance;
	}

	@Override
	public void register(String tag, Command command) {
		map.put(tag, command);
	}

	synchronized public <T> void onFailure(int requestCode, int resultCode,
			String tag, T str) {
		if (tag != null) {
			if (map.containsKey(tag)) {
				if(map.get(tag)==null)
				{
					str = null;
					return;
				}
				map.get(tag).onFailure(requestCode, resultCode, str);
			} else {
				
			}
		}

	}

	synchronized public <T> void onSuccess(int requestCode, int resultCode,
			String tag, T str) {
		if (tag != null) {
			if (map.containsKey(tag)) {
				if(map.get(tag)==null)
				{
					str = null;
					return;
				}
				map.get(tag).onSuccess(requestCode, resultCode, str);
			} else {
				
			}
		}

	}
	
//	synchronized public <T> void onProgress(int requestCode, int resultCode,
//			String tag, T str) {
//		if (tag != null) {
//			if (map.containsKey(tag)) {
//				map.get(tag).onSuccess(requestCode, resultCode, str);
//			} else {
//				
//			}
//		}
//
//	}

	@Override
	public void removeObserver(String tag) {

		if (map.containsKey(tag)) {
			map.remove(tag);
			map.put(tag, null);

		} else {
			return;
		}
	}

	public void clear() {
		map.clear();
		map = null;
	}
}
