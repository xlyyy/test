package com.capinfo.demo.transaction_message;

import java.util.Random;

/**
 * 用PV原语模拟实现进程的同步
 * 桌子上有一个水果盘，每一次可以往里面放入一个水果。爸爸专向盘子中放苹果，儿子专等吃盘子中的苹果。
 * 把爸爸、儿子看作二个进程，试用P、V操作使这两个进程能正确地并发执行。
 * 分析：爸爸和儿子两个进程相互制约，爸爸进程执行完即往盘中放入苹果后，儿子进程才能执行即吃苹果。因此该问题为进程间的同步问题。
 * @author XiLiangYuan
 *
 */
public class PVTest2 {
	
	static Random random = new Random();
	private static int Plate = 1; //盘子容量，初始值为1
	private static int Apple = 0; //盘中的苹果数量，初始值为0	

	/**
	 * 请求盘子容量
	 */
	void PlateP(){
		if(Plate>0){
			System.out.println("盘子为空，可以放入苹果");
		}else{
			System.out.println("盘子中已有苹果，进程堵塞");
			return;
		}
	}
	
	/**
	 * 儿子请求苹果
	 */
	void AppleP(){
		if(Plate>0){
			System.out.println("盘子为空，可以放入苹果");
		}else{
			System.out.println("盘子中已有苹果，进程堵塞");
			return;
		}
	}
	
	/**
	 * 放入苹果
	 */
	void AppleV(){
		if(Apple>0){
			System.out.println("盘中已有苹果，进程堵塞");
			return;
		}else{
			System.out.println("父亲往盘中放入一个苹果");
		}
	}

	/**
	 * 儿子拿走苹果哦
	 */
	void PlateV(){
		if(Apple>0){
			System.out.println("盘中已有苹果，进程堵塞");
			return;
		}else{
			System.out.println("父亲往盘中放入一个苹果");
		}
	}
	/**
	 * 父亲进程
	 */
	void  father( )
	{
	    while(true)
	    {
	    	//请求盘子容量
	    	PlateP();
	        //往盘子中放入一个苹果;
	    	AppleV();
	    } 
	}
	
	/**
	 * 儿子进程
	 */
	void  son( ) 
	{
	    while(true)
	    {   
	    	//请求苹果
	    	AppleP();
	        //从盘中取出苹果;
	    	PlateV();
	        // 吃苹果；
	    } 
	}
  
    public static void main(String[] args) {  
    	
    }
        
}  

