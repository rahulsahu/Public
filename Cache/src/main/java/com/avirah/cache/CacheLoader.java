package com.avirah.cache;

import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class CacheLoader<K extends Object, V extends Object> {

	private Map<K, V> cache_map = new ConcurrentHashMap<K, V>();
	private Map<K, Long> cache_CreatedTimeMap = new ConcurrentHashMap<K, Long>();

	protected abstract V load(K k);

	V loader(K k) {
		if (cache_map.get(k) == null) {
			fatch(k);
		}
		return cache_map.get(k);
	}

	void refresh() {
		cache_map.forEach((k,v) -> {
			v = load(k);
			updateMaps(k, v);
			System.out.println("Refreshing cache for " + k + " : " + cache_CreatedTimeMap.get(k));
		});
	}

	private V fatch(K k) {
		V v = load(k);
		updateMaps(k, v);
		System.out.println("loading for " + k + " : " + cache_CreatedTimeMap.get(k));
		return v;
	}
	
	private void updateMaps(K k,V v){
		cache_map.put(k, v);
		cache_CreatedTimeMap.put(k, Calendar.getInstance().getTimeInMillis());
	}
}