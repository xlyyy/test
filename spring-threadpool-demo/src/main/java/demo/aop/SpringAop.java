package demo.aop;


import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 定义代理类
 * @author XuLiangYuan
 *
 */
@Aspect 
public class SpringAop{
	
	 public SpringAop(){  
         
	    }  
	    
	    //在find()处定义切面
	    @Pointcut("execution(* *.find(..))")  
	    public void point(){}  
	    
	    //在findByName()处定义切面
	    @Pointcut("execution(* demo.dao.SpringDao.findByName(String))")
	    public void point2(){} 

	    //前置通知
	    @Before("point()")  
	    public void beforeService(){  
	        System.out.println("BeforeService");  
	    }  
	    //后置 
	    @After("point()")  
	    public void afterService(){  
	        System.out.println("AfterService");  
	    }
	    
	
	    @Before("point2()")  
	    public void beforeDao(){  
	        System.out.println("BeforeDao");  
	    }  
	    
	    @After("point()2")  
	    public void afterDao(){  
	        System.out.println("AfterDao");  
	    }
	    
	   /* @Around("point()")  
	    public void aroundDao(String name){  
	        try{
	        	name="xll";
	        }catch(Exception e){
	        	System.out.println("处理异常中");
	        }
	    }*/
}
