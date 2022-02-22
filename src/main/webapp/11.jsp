<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>DWR测试</title>

    <script type="text/javascript" src="dwr/interface/userService.js"></script>
    <script type="text/javascript" src="dwr/engine.js"></script>
    <script type="text/javascript" src="dwr/util.js"></script>

    <script type="text/javascript">
        //回调函数
        function saveFun(data){
            if(data){
                alert("注册成功！！");
            }else{
                alert("登录id已经存在！！");
            }
        }

        /* 保存用户 */
        function save(){
            var userMap = {};
            userMap.id = regForm.id.value;
            userMap.password = regForm.password.value;
            userMap.name = regForm.name.value;
            userMap.email = regForm.email.value;
            userService.save(userMap, saveFun);
        }

        //回调函数
        function findFun(data){
            if(data == null){
                alert('无法找到用户：' + queryForm.id.value);
                return;
            }

            alert("找到用户，id："+data.id+"，npassword："+data.password+"，nname："+data.name+"，nemail："+data.email);
        }

        /* 查询用户  */
        function find(){
            userService.find(queryForm.id.value, findFun);
        }
    </script>
</head>
<body>
<h2>用户注册</h2>
<hr>
<br>

<form name="regForm">
    登录id：<input type="text" name="id"><br>
    登录口令：<input type="password" name="password"><br>
    登录姓名：<input type="text" name="name"><br>
    电子邮箱：<input type="text" name="email"><br>

    <input type="button" name="submitBtn" value="提交" onclick="save();"><br>
</form>
<br>
<br>

<h2>用户查询</h2>
<hr>
<br>

<form name="queryForm">
    登录id：<input type="text" name="id"><br>
    <input type="button" name="submitBtn" value="提交" onclick="find();"><br>
</form>
</body>
</html>
