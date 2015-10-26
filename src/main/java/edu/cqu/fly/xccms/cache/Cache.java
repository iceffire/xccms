package edu.cqu.fly.xccms.cache;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * <p>Title: Cache.java</p>
 * <p>Description: 缓存类</p>
 * @author miaoxiaolong
 *
 */
public class Cache {
	/**
	 * 线程安全hashmap
	 */
	public  static  ConcurrentHashMap<String, Object> concurrenthashmap ;
	/**
	 * 初始化hashmap
	 * @return
	 */
	public static synchronized ConcurrentHashMap<String, Object> getInstance(){
		if(null==concurrenthashmap){
			concurrenthashmap =  new ConcurrentHashMap<String, Object>();
		}
		return concurrenthashmap;
	}
	/**
	 * 存放缓存
	 * @param key
	 * @param value
	 */
	public static synchronized void putCache(String key ,Object value){
		concurrenthashmap.put(key, value);
	}
	
	/**
	 * 得到缓存
	 * @return
	 */
	public static synchronized Object getCache(String key){
		Object value =Cache.getInstance().get(key);
		return value;
	}
}
