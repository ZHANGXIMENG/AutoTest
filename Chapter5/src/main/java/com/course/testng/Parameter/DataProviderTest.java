package com.course.testng.Parameter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class DataProviderTest {
    @Test(dataProvider = "data")
    public void testDataProvider(String name, int age){
        System.out.println("testDataProvider"+"name: "+ name+" "+"age: "+age);
    }

    @DataProvider(name="data")
    public Object[][] providerData(){
        Object[][] o =new Object[][]{
                {"apple",10},
                {"peach",9}
        };
        return o;
    }

    @Test(dataProvider = "methodData")
    public void test1(String name,int age){
        System.out.println("test1"+"name: "+ name+" "+"age: "+age);
    }

    @Test(dataProvider = "methodData")
    public void test2(String name,int age){
        System.out.println("test2"+"name: "+ name+" "+"age: "+age);
    }

    @DataProvider(name="methodData")
    public Object[][] methodDataTest(Method method) {
        Object[][] result = null;
        if (method.getName().equals("test1")) {
            result = new Object[][]{
                    {"apple", 100},
                    {"peach", 90}
            };
        } else if (method.getName().equals("test2")) {
            result = new Object[][]{
                    {"apple", 1000},
                    {"peach", 900}
            };
        }
        return result;
    }
}
