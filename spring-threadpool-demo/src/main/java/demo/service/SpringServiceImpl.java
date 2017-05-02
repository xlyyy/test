package demo.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import demo.dao.SpringDao;
import demo.entity.User;

/**
 * 业务层
 * @author XuLiangYuan
 *
 */

@Service
public class SpringServiceImpl implements SpringService {
	
	
	@Resource(name = "DAO")
	private SpringDao dao;
	
	public User find(String name) {
		
        System.out.println("service");
        
		User user = dao.findByName(name);
		
		return user;
    }
	
	
}
