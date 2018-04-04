package com.avirah.cache;

import java.util.concurrent.TimeUnit;

public class Cache<K extends Object, V extends Object> {

	private CacheLoader<K, V> cacheLoader;
	@SuppressWarnings("unused")
	private TimeUnit timeUnit;
	@SuppressWarnings("unused")
	private int expireAfter;

	@SuppressWarnings("unchecked")
	private Cache(CacheBuilder builder) {
		cacheLoader = builder.cacheLoader;
		expireAfter = builder.expireAfter;
		timeUnit = builder.timeUnit;
	}

	public V get(K k) {
		return cacheLoader.loader(k);
	}
	
	public void refresh() {
		cacheLoader.refresh();
	}

	
	
	public static class CacheBuilder {
		
		@SuppressWarnings("rawtypes")
		private CacheLoader cacheLoader;
		private TimeUnit timeUnit;
		private int expireAfter;
		
		private CacheBuilder() {

		}

		public static CacheBuilder newBuilder() {
			return new CacheBuilder();

		}
		
		public CacheBuilder expireAfter(int expireAfter,TimeUnit timeUnit) {
			this.expireAfter = expireAfter;
			this.timeUnit = timeUnit;
			return this;
			
		}

		public <K, V> CacheBuilder loader(CacheLoader<K, V> cacheLoader) {
			
			this.cacheLoader = cacheLoader;
			return this;
			
		}
		
		public <K, V> Cache<K, V> build() {
			return new Cache<K, V>(this);
		}
	}

}
