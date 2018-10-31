 package com.shopping.manage.admin.tools;
 
 import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
// import org.springframework.security.concurrent.SessionRegistry;
 import org.springframework.stereotype.Component;

import com.shopping.core.tools.CommUtil;
import com.shopping.foundation.domain.User;
import com.shopping.foundation.service.IUserService;
 
 @Component
 public class UserTools
 {
 
   @Autowired
   private SessionRegistry sessionRegistry;
 
   @Autowired
   private IUserService userSerivce;
 
   public List<User> query_user()
   {
     List users = new ArrayList();
     List<Object> objs = this.sessionRegistry.getAllPrincipals();
     for (int i = 0; i < objs.size(); i++) {
       User user = this.userSerivce.getObjByProperty("userName", 
         CommUtil.null2String(objs.get(i)));
 
       users.add(user);
     }
 
     return users;
   }
 
   public boolean userOnLine(String userName)
   {
     boolean ret = false;
     List<User> users = query_user();
     for (User user : users) {
       if ((user != null) && (user.getUsername().equals(userName.trim()))) {
         ret = true;
       }
     }
     return ret;
   }
 }


 
 
 