package com.capinfo.xly.demo;

import java.util.Random;

/**
 * 用PV原语模拟实现进程访问同一资源时的互斥
 * 一个资源同时只能被一个进程访问，若进程请求该资源时资源已被占用，则该进程进入等待队列，
 * 等待该资源释放。
 * @author XuLiangYuan
 *
 */
public class PVTest {

	static Random random = new Random();
	private static int s = 10; //资源数量，初始值为10
	private static int maxS = 10; //资源容器容量，10 
	private static int wait = 0; //等待队列，初始为0
	private static int maxWait = 10; //等待队列最大值，10

	/**
	 * 消费者消费资源
	 */
	public static void P(){
		System.out.println(Thread.currentThread().getName()+" 线程：");
		//模拟一个P操作
		if (random.nextInt(12) % 1 == 0) {//模拟每次都产生请求
			if(wait<maxWait){//等待队列没有达到最大值，则产生请求
				System.out.println("模拟一个请求(P操作)");
				if(s>0){
					System.out.println("该请求获得资源,资源量-1");
					s=s-1;
					System.out.println("当前空闲资源总数:"+s+"   "+"等待队列数量:"+wait);
				}else{
					System.out.println("资源不足,请求进入等待队列");
					s=s-1;
					wait=wait+1;
					System.out.println("当前空闲资源总数:"+0+"   "+"等待队列数量:"+wait);
				}
			}else{//否则提示等待队列已满，无法产生请求
				System.out.println("等待队列已满，无法请求资源");
				System.out.println("当前空闲资源总数:"+0+"   "+"等待队列数量:"+wait);
			}
		}
	}
	
	/**
	 * 释放资源
	 */
	private static void V() {
		new Thread(new Runnable() {
			public void run() {
				System.out.println(Thread.currentThread().getName()+"线程：");
				if (random.nextInt(12) % 3 == 0) {//模拟随机释放资源
					if(s<maxS){//若资源容器没满，则释放资源
						System.out.println("释放一个资源(V操作)");
						if(wait>1){//若等待队列有进程，则唤醒其中一个，并把等待队列-1
							System.out.println("唤醒等待队列中一个进程,等待中的进程数-1");
							s=s+1;
							wait=wait-1;
							System.out.println("当前空闲资源总数:"+0+"   "+"等待队列数量:"+wait);
						}else if(s==maxS-1){
							System.out.println("等待队列没有进程,程序继续执行,资源量+1");
							s=s+1;
							System.out.println("资源容器已满"+"                       "+"等待队列数量:"+wait);
						}else{//等待队列没有进程，则程序继续进行
							System.out.println("等待队列没有进程,程序继续执行,资源量+1");
							s=s+1;
							System.out.println("当前空闲资源总数:"+s+"   "+"等待队列数量:"+wait);
						}
					}else{//否则提示资源容器已满
						System.out.println("资源容器已满"+"                       "+"等待队列数量:"+wait);
					}
				}else{
					System.out.println("没有资源释放");
				}
			}
		}).start();
	}
	
	public static void main(String[] args) throws InterruptedException {
		while(true){
			System.out.println("*******************************");
			P();
			V();
			Thread.sleep(1000);
		}
	}
}
