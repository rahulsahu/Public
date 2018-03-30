package com.avirah.cache;

public class Cache<K extends Object, V extends Object> {

	private CacheLoader<K, V> cacheLoader;

	private Cache(CacheLoader<K, V> cacheLoader) {
		this.cacheLoader = cacheLoader;
	}

	public V get(K k) {
		return cacheLoader.loader(k);
	}
	
	public void refresh() {
		cacheLoader.refresh();
	}

	public static class CacheBuilder {
		private CacheBuilder() {

		}

		public static CacheBuilder newBuilder() {
			return new CacheBuilder();

		}

		public <K, V> Cache<K, V> build(CacheLoader<K, V> cacheLoader) {
			return new Cache<K, V>(cacheLoader);
		}
	}

}
