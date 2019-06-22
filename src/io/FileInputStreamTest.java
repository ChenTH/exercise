package io;

import java.io.FileInputStream;
import java.io.IOException;

public class FileInputStreamTest {
    public static void main(String args[]) throws IOException {
        try {
            FileInputStream rf = new FileInputStream("/Users/sherlockthao/MyDocuments/InputFromFile.java");
            read1(rf);
            rf.close();
        } catch (Exception e) {

        }

    }

    public static void read1(FileInputStream rf) throws IOException {
        int n = 512;
        byte buffer[] = new byte[n];
        while ((rf.read(buffer, 0, n) != -1)) {
            System.out.println("rf.available():" + rf.available());
            System.out.println(new String(buffer));
            buffer = new byte[n];
        }
        System.out.println();

    }
}
