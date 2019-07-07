package com.course.testng.Groups;

import org.testng.annotations.Test;

@Test(groups = "stu")
public class GroupsOnClass1 {

    public void test1(){
        System.out.println("test1 in class1");
    }

    public void test2(){
        System.out.println("test2 in class1");
    }
}
