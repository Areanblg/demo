package com;


import org.junit.Test;

import java.io.File;

public class FileTest {

    @Test
    public void test1(){
        //绝对路径和相对路径
        //在IDEA中，如果在单元测试中生成相对路径，相对的是module的根目录，而不是项目根目录
        //如果是在main方法中生成相对路径，相对的是project的根目录
        File file = new File("aaa");
        System.out.println(file.getAbsoluteFile());
    }

    @Test
    public void test2(){
        /*

         */
    }
}
