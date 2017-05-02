package demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import demo.entity.User;

/**
 * 持久层
 * @author XuLiangYuan
 *
 */
@Repository("DAO")
public class SpringDao {

	@Resource(name="jdbcTemplate")
    private JdbcTemplate jt;

	 public User findByName(String name) {
		 
		    System.out.println("DAO");
	        User user= null;
			String sql = "select * from test2 where name=?";
			Object[] args = {name};
			List<User> list = jt.query(sql,args,new UserRowMapper());
			if(list!=null &&list.size()>0){
				   user = list.get(0);
				  }else {
				   user = null;
				  }
			return user;

	}
	    
	    	class UserRowMapper implements RowMapper<User>{
	    		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
	    			User user=new User();  
	                user.setName(rs.getString("name"));  
	                user.setPassword(rs.getString("password")); 
	    			return user;
	    		}	
	    	}
	}
