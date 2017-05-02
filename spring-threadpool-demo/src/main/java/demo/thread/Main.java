package demo.thread;

import java.util.Random;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class Main {
	static ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);  
	static ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor)ctx.getBean("taskExecutor");  
	public static void main(String[] args) throws InterruptedException {
		//随机产生一个线程
		Random r = new Random();
		for(int i=0;i<1000;i++){
			int n = r.nextInt(3000);
			Thread.sleep(n);
			//产生一个线程
			PrintTask printTask = (PrintTask)ctx.getBean("printTask");
			printTask.setName("Thread "+i);
			//开启线程池中的任务
			taskExecutor.execute(printTask); 
		} 
	}
}
