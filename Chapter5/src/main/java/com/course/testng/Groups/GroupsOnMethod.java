package com.course.testng.Groups;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class GroupsOnMethod {
    @Test(groups="server")
    public void test1(){
        System.out.println("server test");
    }

    @Test(groups="client")
    public void test2(){
        System.out.println("client test");
    }

    @BeforeGroups(groups="client")
    public void beforeGroups(){
        System.out.println("before groups client");
    }

    @AfterGroups(groups="client")
    public void afterGroups(){
        System.out.println("after groups client");
    }
}
