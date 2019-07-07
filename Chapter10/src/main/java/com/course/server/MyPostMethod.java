package com.course.server;

import com.course.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value ="/",description= "all the methods of post")
@RequestMapping("/v1")
public class MyPostMethod {
    private static Cookie cookie;

    @RequestMapping(value ="/login",method = RequestMethod.POST)
    @ApiOperation(value="after login, we can have the information of cookies",httpMethod = "POST")
    public String login(HttpServletResponse response,
                        @RequestParam(value ="userName",required = true) String userName,
                        @RequestParam (value="password",required = true) String password ){
     if(userName.equals("florence")&&password.equals("666")){
         cookie =new Cookie("login","true");
         response.addCookie(cookie);
         return "success";
     }
     return "failed";
    }

    User user;
    @RequestMapping(value = "/getUserList",method = RequestMethod.POST)
    @ApiOperation(value="get the lists of users",httpMethod = "POST")
    public String getUserList(HttpServletRequest request,
                            @RequestBody User u){
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie:cookies) {
            if (cookie.getName().equals("login")
                    && cookie.getValue().equals("true")
                    && u.getUserName().equals("florence")
                    && u.getPassWord().equals("666")) {
                user = new User();
                user.setName("Ximeng");
                user.setAge("21");
                user.setSex("woman");
                return user.toString();
            }
        }
          return "user is not correct";

    }


}
