package demo.thread;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration  
@ComponentScan(basePackages="demo.thread")  
public class Config {

	/*当线程数小于核心线程数时，创建线程。
	     当线程数大于等于核心线程数，且等待队列未满时，将任务放入等待队列。
	     当线程数大于等于核心线程数，且等待队列已满时：
	          1：若线程数小于最大线程数，创建线程
	          2：若线程数等于最大线程数，抛出异常，拒绝任务*/
	@Bean  
    public ThreadPoolTaskExecutor taskExecutor(){  
            ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();  
            //核心线程数
            pool.setCorePoolSize(5);  
            //最大线程数
            pool.setMaxPoolSize(10);
            //等待队列容量
            pool.setQueueCapacity(10);
            return pool;  
    }  
}
