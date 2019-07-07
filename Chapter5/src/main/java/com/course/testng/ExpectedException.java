package com.course.testng;

import org.testng.annotations.Test;

public class ExpectedException {
    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeException(){
        System.out.println("success");
        //如果失败，因为没有抛出异常
        throw new RuntimeException();
    }
}
