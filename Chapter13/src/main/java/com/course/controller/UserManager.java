package com.course.controller;

import com.course.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

@RestController
@Log4j
@Api(value="v1",description = "user manager")
@RequestMapping("v1")
public class UserManager {
    @Autowired
    private SqlSessionTemplate template;
    @ApiOperation(value="login",httpMethod = "POST")
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public boolean login(HttpServletResponse response, @RequestBody User user){
      int i = template.selectOne("login",user);
      Cookie cookie = new Cookie("login","true");
      response.addCookie(cookie);
      log.info("login post result: "+i);
      if(i == 1){
          log.info("usr name:" + user.getUserName());
          return true;
      }
      return false;
    }


    @ApiOperation(value ="add user api",httpMethod ="POST")
    @RequestMapping(value="/addUser",method = RequestMethod.POST)
    public boolean addUser(HttpServletRequest request,@RequestBody User user){
        Boolean x = VerifyCookies(request);
        int result = 0;
        if(x != null){
            result= template.insert("addUser",user);
        }
        if(result > 0){
            log.info("add user number:"+result);
            return true;
        }
        return false;
    }

    @ApiOperation(value="obtenir user api",httpMethod = "POST")
    @RequestMapping(value ="/getUserInfo",method = RequestMethod.POST)
    public List<User> getUserInfo(HttpServletRequest request,@RequestBody User user){
       Boolean x = VerifyCookies(request);
       if(x == true){
           List<User> users = template.selectList("getUserInfo",user);
           log.info("getUserInfo's number are: "+ users.size());
           return users;
       }else{
           return null;
       }
    }

    @ApiOperation(value = "update usesr",httpMethod = "POST")
    @RequestMapping(value="/updateUserInfo",method= RequestMethod.POST)
    public int updateUser(HttpServletRequest request,@RequestBody User user){
       Boolean x = VerifyCookies(request);
       int i = 0;
       if( x == true){
           i = template.update("updateUserInfo",user);
       }
       log.info("update user number:"+i);
       return  i;
    }

    public Boolean VerifyCookies(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(Objects.isNull(cookies)){
            log.info("cookies are empty");
            return false;
        }

        for(Cookie cookie: cookies){
            if(cookie.getName().equals("login")&&
                    cookie.getValue().equals("true")){
                log.info("cookie is right");
                return true;
            }
        }
        return false;
    }
}
