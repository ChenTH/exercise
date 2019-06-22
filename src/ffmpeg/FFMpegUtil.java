package ffmpeg;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * FFMPEG 的相关操作
 *
 * @author Administrator
 */
public class FFMpegUtil {

    //Linux与mac下  ffmpeg的路径
    private static String ffmpegEXE = "/usr/local/bin/ffmpeg";
    private static String RTSP_URL = "rtsp://admin:wlw123456@172.16.25.6:554/h264/ch1/main/av_stream";
    private static String VIDEO_PATH = "/Users/sherlockthao/MyDocuments/vcenter/hls/";
//    private static String LOG_PATH = "/Users/sherlockthao/MyDocuments/vcenter/logs/live.log";

    /**
     * @throws Exception
     */
    // ffmpeg.exe -ss 00:00:01 -y -i 视频.mp4 -vframes 1 new.jpg
    public static Process convetor(String RTSP_URL, String SUB_VIDEO_PATH) throws Exception {

        List<String> command = new ArrayList<String>();
        command.add(ffmpegEXE);
        command.add("-i");
        command.add(RTSP_URL);
        command.add("-fflags");
        command.add("flush_packets");
        command.add("-max_delay");
        command.add("5");
        command.add("-an");
        command.add("-flags");
        command.add("-global_header");
        command.add("-hls_time");
        command.add("2");
        command.add("-hls_list_size");
        command.add("3");
        command.add("-hls_wrap");
        command.add("3");
        command.add("-vcodec");
        command.add("copy");
        command.add("-y");
        command.add(VIDEO_PATH + SUB_VIDEO_PATH);

        ProcessBuilder builder = new ProcessBuilder(command);
        Process process = null;
        try {
            process = builder.start();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return process;
//        System.out.println(process.isAlive());
//        // 使用这种方式会在瞬间大量消耗CPU和内存等系统资源，所以这里我们需要对流进行处理
//        try (InputStream errorStream = process.getErrorStream();
//             InputStreamReader errorStreamReader = new InputStreamReader(errorStream);
//             BufferedReader errorbr = new BufferedReader(errorStreamReader)) {
//            String line = "";
//            while ((line = errorbr.readLine()) != null) {
//                System.out.println(line);
//            }
//        }
//        System.out.println(process.isAlive());
//        System.out.println("结束了");
    }
}
