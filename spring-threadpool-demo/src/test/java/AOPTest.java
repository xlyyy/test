
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import demo.entity.User;
import demo.service.SpringService;

public class AOPTest {

	@Test //测试aop和ioc
	public void test(){
        //如果是不web项目，则使用注释的代码加载配置文件
        //ApplicationContext appCtx = new FileSystemXmlApplicationContext("application.xml");  
        ApplicationContext appCtx = new ClassPathXmlApplicationContext("classpath:spring/application.xml");  
        SpringService spring = appCtx.getBean("spring",SpringService.class);  
        User user = spring.find("xxx");
        System.out.println(user);
	}
		
}
