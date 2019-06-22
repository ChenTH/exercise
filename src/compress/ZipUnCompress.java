package compress;


import com.google.common.primitives.Bytes;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.*;

public class ZipUnCompress {

    private ZipArchiveInputStream zipArchiveInputStream;      // 目的地Zip文件
    private String namePattern;
    private Map<String, Integer> namePatternMap;
    private List<String> failedFileNameList = new ArrayList<>();
    private String NAME = "name";
    private String SEX = "sex";
    private String ID = "id";
    private String albumName;

    public ZipUnCompress(ZipArchiveInputStream zipArchiveInputStream, String namePattern) {
        this.zipArchiveInputStream = zipArchiveInputStream;
        this.namePattern = namePattern;
        this.namePatternMap = getNamePatternMap(namePattern);
    }

    public void uncompress() throws IOException {
        ZipArchiveEntry entry = null;
        while ((entry = zipArchiveInputStream.getNextZipEntry()) != null) {

            if (entry.isDirectory()) {
                continue;//如果是文件夹则忽略
            }

            String fileFullPath = entry.getName();
            String[] floders = fileFullPath.split("/");
            if (floders.length >= 2) {
                continue;//如果不在根目录则忽略
            }

            String fileName = floders[floders.length - 1];
            String[] name = fileName.split("\\.");
            if (name.length <= 1) {
                continue;//如果没有扩展名称则忽略
            }
            String extendName = name[name.length - 1];
            String[] extendNames = {"jpg", "jpeg"};
            List<String> extendNameList = Arrays.asList(extendNames);
            if (!extendNameList.contains(extendName)) {
                continue;//如果不为要求的图片则记录后忽略
            }

//            Person person = getPerson(fileName);
//            if (person == null) {
//                addFailedFileName(fileName);
//                continue;//如果文件名称无法解析出人员信息则记录后忽略
//            }
            long size = entry.getSize();
            if (size > Integer.MAX_VALUE) {
                addFailedFileName(fileName);
                continue;//todo 如果容量过大则忽略
            }
//            byte[] picture = new byte[(int) entry.getSize()];
//            int len = zipArchiveInputStream.read(picture, 0, (int) entry.getSize());
//            System.out.println(len + ":" + entry.getSize());
            int len;
            List<Byte> byteList = new LinkedList<>();
            while ((len = zipArchiveInputStream.read()) != -1) {
                byteList.add((byte) len);
            }
            byte[] picture = Bytes.toArray(byteList);
//            try (InputStream in = zipArchiveInputStream.(entry)){
//
//                int i = 0;
//                for (; i < entry.getSize(); i++) {
//                    int c = in.read();
//                    if (c == -1) {
//                        break;
//                    }
//                    picture[i] = (byte) c;
//                }
//                if (i != entry.getSize()) {
//                    addFailedFileName(fileName);
//                    continue;//如果文件读取大小与文件原大小不同则记录后忽略
//                }
//            } catch(Exception e){
//                addFailedFileName(fileName);
//                continue;//如果用户添加失败则记录后忽略
//            }

            byte[] imgBase64 = Base64.getEncoder().encode(picture);
            String imggBase64Str = new String(imgBase64);
            String prefix = "data:image/" + extendName + ";base64,";
            System.out.println(prefix + imggBase64Str);
        }
        try {
            zipArchiveInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            ZipArchiveInputStream zipArchiveInputStream = new ZipArchiveInputStream(new FileInputStream(new File("/Users/sherlockthao/MyDocuments/纪念相册.zip")), "utf-8");
            ZipUnCompress zipUnCompress = new ZipUnCompress(zipArchiveInputStream, "name_sex_id");
            zipUnCompress.uncompress();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addFailedFileName(String failedFileName) {
        failedFileNameList.add(failedFileName);
    }

    private Map<String, Integer> getNamePatternMap(String namePattern) {
        String[] partName = namePattern.split("_");
        HashMap<String, Integer> nameMap = new HashMap<>();
        addNameElement(nameMap, partName, NAME);
        addNameElement(nameMap, partName, SEX);
        addNameElement(nameMap, partName, ID);
        return nameMap;
    }

    private void addNameElement(Map<String, Integer> nameMap, String[] partName, String elementName) {
        int index = Arrays.asList(partName).indexOf(NAME);
        if (index != -1) {
            nameMap.put(elementName, index);
        }
    }

    public List<String> getFailedFileNameList() {
        return failedFileNameList;
    }

}
