package com.course.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Api(value = "/",description = "here are all the methods of get")
public class MyGetMethod {
    @RequestMapping(value="/getCookies",method= RequestMethod.GET)
    @ApiOperation(value="getCookies",httpMethod = "GET")
    public String getCookies(HttpServletResponse response){
        Cookie cookie = new Cookie("login","true");
        response.addCookie(cookie);
        return "have cookies by using get method";
    }

    @RequestMapping(value="/get/withme/cookies",method = RequestMethod.GET)
    @ApiOperation(value="getWithCookies",httpMethod = "GET")
    public String getWithCookies(HttpServletRequest request){
      Cookie[] cookies = request.getCookies();
      if(Objects.isNull(cookies)){
         return "must bring with cookies";
      }
      for(Cookie cookie :cookies){
          if(cookie.getName().equals("login")&&
                  cookie.getValue().equals("true"))
              return "this is the cookie that we want";
      }
      return "must bring with cookies";
    }

    @RequestMapping(value="/get/with/param",method = RequestMethod.GET)
    @ApiOperation(value="getList",httpMethod = "GET")
    public Map<String,Integer> getList(@RequestParam Integer start,
                                       @RequestParam Integer end){
        Map<String,Integer> myList = new HashMap<>();
        myList.put("shoes",400);
        myList.put("noodles",300);
        myList.put("shirt",200);
        return myList;
    }

    @RequestMapping(value="get/with/param/{start}/{end}")
    @ApiOperation(value="mygetList",httpMethod = "GET")
    public Map mygetList(@PathVariable Integer start,
                         @PathVariable Integer end){
        Map<String,Integer> myList = new HashMap<String,Integer>();
        myList.put("shoes",400);
        myList.put("noodles",300);
        myList.put("shirt",200);
        return myList;
    }
}
