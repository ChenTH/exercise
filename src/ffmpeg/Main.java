package ffmpeg;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws Exception {
        HashMap<String, Process> hashMap = new HashMap<>();
        Process process1 = FFMpegUtil.convetor("rtsp://admin:wlw123456@172.16.25.6:554/h264/ch1/main/av_stream", "1.m3u8");
        Process process2 = FFMpegUtil.convetor("rtsp://admin:wlw123456@172.16.25.6:554/h264/ch1/main/av_stream", "2.m3u8");
        hashMap.put("m3u81", process1);
        hashMap.put("m3u82", process2);
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            System.out.println(hashMap.get("m3u81").isAlive());
            System.out.println(hashMap.get("m3u82").isAlive());
        }
        process1.destroy();
        process2.destroy();
        process1.waitFor();
        process2.waitFor();
        System.out.println(process1.isAlive());
        System.out.println(process2.isAlive());
    }
}
