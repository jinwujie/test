package com.test;

import java.util.Date;
import java.util.Random;
import java.util.Scanner;

import redis.clients.jedis.Jedis;

public class Redis {

	public static void main(String[] args) {
		Jedis jedis = new Jedis("localhost");
		System.out.println("redis本地服务连接成功！");
		Scanner scanner = new Scanner(System.in);
		while (true) {
			int num = scanner.nextInt();
			switch (num) {
			case 1:
				print(jedis);
				break;
			default:
				scanner.close();
				return;
			}
		}
	}

	public static void print(Jedis jedis) {
		long current = new Date().getTime();
		long length = jedis.llen("Code");
		if (length < 3) {
			random(current,jedis);
		} else {
			String time = jedis.lpop("Code");
			if(Long.parseLong(time)-current>1000*60*30) {
				random(current,jedis);
			}else {
				System.out.println("时间过短，无法再次获取");
			}
		}
	}
	
	public static void random(long current,Jedis jedis) {
		Random random = new Random();
		String result = "";
		for (int i = 0; i < 6; i++) {
			result += random.nextInt(10);
		}
		System.out.println(result);
		String time = current+"";
		jedis.lpush("Code", time);
	}

}
