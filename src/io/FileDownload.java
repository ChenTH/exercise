package io;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileDownload {
    /**
     * 从网络Url中下载文件
     *
     * @param urlStr
     * @param fileName
     * @param savePath
     * @throws IOException
     */
    public static void downLoadFromUrl(String urlStr, String fileName, String savePath) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //设置超时间为3秒
        conn.setConnectTimeout(3 * 1000);
        //防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        //文件保存位置
        File saveDir = new File(savePath);
        if (!saveDir.exists()) {
            saveDir.mkdir();
        }
        File file = new File(saveDir + File.separator + fileName);

        //得到输入流
        try (InputStream inputStream = conn.getInputStream(); FileOutputStream fos = new FileOutputStream(file);) {
            //获取自己数组
            byte[] getData = readInputStream(inputStream);
            fos.write(getData);
        } catch (Exception e) {
            throw e;
        }
        System.out.println("info:" + url + " download success");
    }

    /**
     * 从输入流中获取字节数组
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            while ((len = inputStream.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        } catch (Exception e) {
            throw e;
        }
    }

    public static void main(String[] args) {
        try {
            downLoadFromUrl("http://192.168.1.225:8080/v4/photos/bweed---899-_1962440d17f31e/data",
                    "百度.jpg", "/Users/sherlockthao/MyDocuments/");
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
