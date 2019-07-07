package com.course.controller;

import com.course.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Log4j
@RestController
@Api(value ="v1",description = "SpringBoot with mysql")
@RequestMapping("v1")
public class Demo {

    @Autowired
    private SqlSessionTemplate template;

    @RequestMapping(value="/getUserCount",method = RequestMethod.GET)
    @ApiOperation(value="we can have the number of users",httpMethod = "GET")
    public int getUserCount(){
        return template.selectOne("getUserCount");
    }

    @RequestMapping(value="/addUser",method = RequestMethod.POST)
    @ApiOperation(value = "we use this method to add user",httpMethod = "POST")
    public int addUser(@RequestBody User user){
        //这个和mysql.xml里面相对应
        int result =template.insert("addUser",user);
        return result;
    }

    @RequestMapping(value="/updateUser",method=RequestMethod.POST)
    @ApiOperation(value ="we use this method to update",httpMethod="POST")
    public int updateUser(@RequestBody User user){
       return template.update("updateUser",user);
    }


    @RequestMapping(value="/deleteUser",method = RequestMethod.GET)
    @ApiOperation(value = "we use this to delete the user",httpMethod = "GET")
    public int deleteUser(@RequestParam int id){
        return template.delete("deleteUser",id);
    }
}
