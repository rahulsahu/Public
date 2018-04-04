package com.avirah.demo;

import java.util.concurrent.TimeUnit;

import com.avirah.cache.Cache;
import com.avirah.cache.CacheLoader;
import com.avirah.cache.Cache.CacheBuilder;

public class AppTest {

	private static Cache<Long, String> cache;

	static {
		cache  = CacheBuilder.newBuilder().loader(new CacheLoader<Long, String>() {
			@Override
			protected String load(Long k) {
				// write your own loading mechanism
				String data = k + " - > Data";
				return data;
			}
		}).expireAfter(10, TimeUnit.MINUTES).build();
	}

	public static void main(String[] args) throws InterruptedException {

		System.out.println(cache.get(1L));
		System.out.println(cache.get(2L));
		System.out.println(cache.get(3L));
		System.out.println(cache.get(3L));
		System.out.println(cache.get(3L));
		System.out.println(cache.get(1L));
		System.out.println(cache.get(1L));
		System.out.println(cache.get(2L));
		cache.refresh();
	}
}
