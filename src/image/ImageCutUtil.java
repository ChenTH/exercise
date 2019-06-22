package image;

import image.faceanalyze.FaceAnalyzeResult;
import image.faceanalyze.Faces;
import image.faceanalyze.Rect;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImageCutUtil {

    public List<String> getSubImgBase64s(String fullImgBase64, FaceAnalyzeResult faceAnalyzeResult) {
        List<Rect> rects = new ArrayList<>();
        for (Faces f : faceAnalyzeResult.getFaces()) {
            rects.add(f.getLocator().getRect());
        }
        List<String> faceImgBase64s = new ArrayList<>(rects.size());
        for (Rect r : rects) {
            faceImgBase64s.add(getSubImgBase64(fullImgBase64, r));
        }
        return faceImgBase64s;
    }

    private String getSubImgBase64(String fullImgBase64, Rect rect) {
        String res = "";
        String[] codes = fullImgBase64.split(",");
        if (codes.length != 2) {
            throw new RuntimeException("please check the legitimacy of the ImgBase64");
        }
        String base64 = codes[1];
        byte[] imageData = Base64.getDecoder().decode(base64);
        try (InputStream is = new ByteArrayInputStream(imageData); ByteArrayOutputStream bos = new ByteArrayOutputStream();) {
            BufferedImage bufferedImage = ImageIO.read(is);
            rect = resizeRect(bufferedImage.getHeight(), bufferedImage.getWidth(), rect);
            BufferedImage subBufferedImage = bufferedImage.getSubimage(rect.getLeftPixels(), rect.getTopPixels(), rect.getWidthPixels(), rect.getHeightPixels());
            Pattern pattern = Pattern.compile("/(.*?);");
            Matcher m = pattern.matcher(codes[0]);
            String formatName = "jpeg";
            if (m.find()) {
                m.group(0);
            }
            ImageIO.write(subBufferedImage, formatName, bos);
            byte[] subImageData = bos.toByteArray();
            BASE64Encoder encoder = new BASE64Encoder();
            String subImgBase64 = encoder.encode(subImageData);
            res = codes[0] + ',' + subImgBase64;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    private Rect resizeRect(int maxHeight, int maxWidth, Rect rect) {
        Rect newRect = new Rect();
        int adjust = rect.getWidthPixels() / 2;//调整值
        //调整左坐标
        if ((rect.getLeftPixels() - adjust) >= 0) {
            newRect.setLeftPixels(rect.getLeftPixels() - adjust);
        } else {
            newRect.setLeftPixels(0);
        }
        //调整顶坐标
        if ((rect.getTopPixels() - adjust) >= 0) {
            newRect.setTopPixels(rect.getTopPixels() - adjust);
        } else {
            newRect.setTopPixels(0);
        }
        //调整宽度
        if (newRect.getLeftPixels() + rect.getWidthPixels() * 2 < maxWidth) {
            newRect.setWidthPixels(rect.getWidthPixels() * 2);
        } else {
            newRect.setWidthPixels(maxWidth - newRect.getLeftPixels());
        }
        //调整高度
        if (newRect.getTopPixels() + rect.getHeightPixels() * 2 < maxHeight) {
            newRect.setHeightPixels(rect.getHeightPixels() * 2);
        } else {
            newRect.setHeightPixels(maxHeight - newRect.getTopPixels());
        }
        return newRect;
    }
}
