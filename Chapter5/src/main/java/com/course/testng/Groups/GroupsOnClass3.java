package com.course.testng.Groups;

import org.testng.annotations.Test;

@Test(groups = "teachers")
public class GroupsOnClass3 {
    public void test1(){
        System.out.println("test1 in class3");
    }

    public void test2(){
        System.out.println("test2 in class3");
    }
}
