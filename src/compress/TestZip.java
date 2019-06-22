package compress;

    public class TestZip {

        public static void main(String[] args) {
            ZipCompress zipCom = new ZipCompress("D:\\电影.zip", "F:\\电影");
            try {
                zipCom.zip();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
