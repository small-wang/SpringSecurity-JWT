# It's a security demo using SpringSecurity Authentication and Authorization and JWT.
## Optimize from the project(https://gitee.com/micai/springboot-springsecurity-jwt-demo).
## You can run this demo with below steps.

1. Create Mysql Table.  
  (1) CREATE TABLE `tb_user` (
  `id` int(22) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
  ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;  
  (2) CREATE TABLE `tb_user_role_group_relationship` (
  `id` int(22) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(45) NOT NULL,
  `role_group_id` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
  ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;  
  (3) CREATE TABLE `tb_role_group` (
  `id` int(22) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
  ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;  
  (4) CREATE TABLE `tb_role_group_relationship` (
  `id` int(22) NOT NULL AUTO_INCREMENT,
  `role_group_id` varchar(45) NOT NULL,
  `role_id` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
  ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;  
  (5) CREATE TABLE `tb_role` (
  `id` int(22) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `remark` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
  ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;  
  (6) INSERT INTO `tb_role`
  (`id`,
  `name`,
  `remark`)
  VALUES
  (1,
  "ROLE_USER",
  "user");  
  (7) INSERT INTO `tb_role_group`
  (`id`,
  `group_name`)
  VALUES
  (1,
  "user_group");  
  (8) INSERT INTO `tb_role_group_relationship`
  (`id`,
  `role_group_id`,
  `role_id`)
  VALUES
  (1,
  1,
  1);

2. Run SecurityApplication.  

3. SignUp.  
  curl -ik -X POST 'http://127.0.0.1:8080/users/signup' -d '{"username":"123","password":"123"}' -H 'Content-Type:application/json'

4. Add Role for User.  
  INSERT INTO `tb_user_role_group_relationship`
  (`id`,
  `user_id`,
  `role_group_id`)
  VALUES
  (1,
  1,
  1);
  
5. Login.  
  curl -ik -X POST 'http://127.0.0.1:8080/login' -d '{"username":"123","password":"123"}' -H 'Content-Type:application/json'

6. Interface Test.  
  curl -ik -X GET 'http://127.0.0.1:8080/users/authorityList' -H 'Authorization: Bearer xxx'
  (Set authorization value from login interface response header. 
   This request will fail without correct authorization info.
   This request will fail after truncate table 'tb_user_role_group_relationship'.)
