package com.redis.demo;

import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.Jedis;

public class RedisDemo {
	
	@Test
	public void redisDemo1() {
		Jedis jedis = new Jedis("10.1.40.61", 6379);
		System.out.println("服务正在运行: "+jedis.ping());
	}
	
	@Test
	public void redisCmd_KEYS() {
		Jedis jedis = new Jedis();
		Set<String> keys = jedis.keys("*");
		Iterator<String> iterator = keys.iterator();
		while(iterator.hasNext()) {
			String next = iterator.next();
			System.out.println(next);
		}
	}
}
