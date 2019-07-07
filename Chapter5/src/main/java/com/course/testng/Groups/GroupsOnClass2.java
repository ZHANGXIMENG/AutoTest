package com.course.testng.Groups;

import org.testng.annotations.Test;

@Test(groups = "stu")
public class GroupsOnClass2 {

    public void test1(){
        System.out.println("test1 in class2");
    }

    public void test2(){
        System.out.println("test2 in class2");
    }
}
