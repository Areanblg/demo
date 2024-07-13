package com;


import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;

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
    public void test2() throws IOException {
        File file = new File("../file/123.txt");
       // public String getAbsolutePath()：获取绝对路径
        System.out.println(file.getAbsoluteFile());
        // public String getPath()：获取路径
        System.out.println(file.getPath());
        // public String getName()：获取名称
        System.out.println(file.getName());
        // public String getParent()：获取上层文件目录路径。若无，返回null
        System.out.println(file.getParent());
        // public long length()：获取文件长度（即：字节数）。不能获取目录的长度。
        System.out.println(file.length());
        System.out.println("==========================================");
        // publicString[]list()：获取指定目录下的所有文件或者文件目录的名称数组
        File dir = new File("E:\\study\\java_io_web");


        String[] list = dir.list();
        for (String string : list) {
            System.out.println(string);
        }
        System.out.println("==========================================");
        // publicFile[]listFiles()：获取指定目录下的所有文件或者文件目录的File数组
        File[] files = dir.listFiles();
        for (File file1 : files) {
            System.out.println(file1.getAbsolutePath());


        }

        System.out.println("==========================================");
        //public boolean renameTo(Filedest):文件重命名为一个指定的路径
        File f1 = new File("../file/123.txt");
        File f2 = new File("E:\\study\\file\\22.txt");
        System.out.println(f1.renameTo(f2));

    }
    @Test
    public void test3(){
        System.out.println("小练习题，将一个目录下的所有的文件的前缀都带上序号，例如z01、z02..");
        File file = new File("../file");
        File[] files = file.listFiles();
        int index = 0;
        for (File oldf : files) {
            String name = oldf.getName();
            name = "z"+(index < 10?("0"+index):index)+"_"+name;
            index += 1;
            File newFile = new File(oldf.getParent() + "/" + name);
            System.out.println(oldf.renameTo(newFile));
        }
    }

    @Test
    public void test4(){
        System.out.println("判断指定目录下是否有后缀名为.jpg的文件，如果有，就输出该文件名称");
        File file = new File("E:\\j2ee");
        String[] list = file.list();
        for (String fileName : list) {
            System.out.println(fileName.endsWith(".jpg")?fileName:"");
        }
        System.out.println("==================================================");
        System.out.println("遍历指定目录所有文件名称,包括子文件目录中的文件");
//        File file = new File("E:\\j2ee");
        printFileName(file);
    }
    public void printFileName(File file){
        if (file.isFile()){
            System.out.println(file.getName());
        }else{
            File[] files = file.listFiles();
            for (File file1 : files) {
                printFileName(file1);
            }
        }
    }

    @Test
    public void test5(){
        File file = new File("E:\\j2ee");
        printFileName(file);
    }


}
