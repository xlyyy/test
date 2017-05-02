package demo.thread;

import java.util.Random;

import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component  
@Scope("prototype")  
public class PrintTask implements Runnable{

	String name;  
	ThreadPoolTaskExecutor taskExecutor = Main.taskExecutor;  
    public void setName(String name) {  
            this.name = name;  
    }  
      
    @Override  
    public void run(){  
    	    //线程开始
    	    int count = taskExecutor.getActiveCount();  
            System.out.println(name + " is running."+"  "+"当前活动线程数 : " + count);  			
		    System.out.println();  
		    //生成一个随机数
    	    int n = new Random().nextInt(5000);
            try{  
            	//阻塞一段时间，模拟线程生命周期
                Thread.sleep(n);  
            }catch(InterruptedException e){  
                e.printStackTrace();  
            }
            //线程结束
            System.out.println(name + " is over.");
    }    
}
