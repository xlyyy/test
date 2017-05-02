<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript"  src="scripts/boot.js"></script>
<script type="text/javascript"  src="scripts/jquery-1.6.2.min.js"></script>
<script type="text/javascript"  src="scripts/miniui/miniui.js"></script>
<script type="text/css"  src="scripts/miniui.css"></script>
<link href="scripts/demo.css" rel="stylesheet" type="text/css" />
<title>Insert title here</title>
</head>
<body>
    <div>
	    <form action="login.do" method="post">
                    账号：<input type="text" name="name"/><br/>
                    密码：<input type="password" name="password"/><br/>
        <input type="submit" value="登陆" />
	    </form>
    </div>  
    <!-- <form action="login.do" method="post" id="form">
                    账号：<input class="mini-textbox" id="name"/><br/>
                    密码：<input class="mini-password" id="password"/><br/>
        <a class="mini-button" onclick="login()">登陆</a>
    </form> -->
     <script type="text/javascript">
      /*  mini.parse();
       var grid=mini.get("form");
       function login(){
    	   var namee = mini.get("name").getValue();//获取用户名
    	   var pwd = mini.get("password").getValue();//获取密码
    	   //序列化成json数据类型
    	   var name = mini.encode(namee);
    	   var password = mini.encode(pwd);
    	   $.ajax({
               url: "login.do",
               type: "post",
               data: { name:name,password:password },
               dataType:"json",
               success:function(result){
            	   console.log(result);
            	   if(result.state==0){
				       window.location.href="error1.jsp";//用户不存在
				   }else if(result.state==1){
					   window.location.href="error2.jsp";//密码错误
				   }else if(result.state==2){
					   window.location.href="success.jsp";//登陆成功
				   }
               }
               
           });
       }  */
       
       
    </script>
    </script>
</body>
</html>