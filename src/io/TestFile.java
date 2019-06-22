package io;

import java.io.*;

public class TestFile {
    public static void main(String args[]) throws IOException {
        File dir = new File("/Users/sherlockthao/MyDocuments");
        File f1 = new File(dir, "fileOne.txt");
        File f2 = new File(dir, "fileTwo.java");
        File f3 = new File(dir, "InputFromFile.java");
        // 文件对象创建后，指定的文件或目录不一定物理上存在
        if (!dir.exists())
            dir.mkdir();
        if (!f1.exists())
            f1.createNewFile();
        if (!f2.exists())
            f2.createNewFile();
        if (!f3.exists())
            f3.createNewFile();
        System.out.println("f1's AbsolutePath=  " + f1.getAbsolutePath());
        System.out.println("f1 Canread=" + f1.canRead());
        System.out.println("f1's len= " + f1.length());
        String[] FL;
        int count = 0;
        FL = dir.list();
        for (int i = 0; i < FL.length; i++) {
            count++;
            System.out.println(FL[i] + "is in /Users/sherlockthao/MyDocuments");
        }
        System.out.println("there are" + count + "file in /Users/sherlockthao/MyDocuments");
        InputStream is = new FileInputStream(f1);
    }
}
