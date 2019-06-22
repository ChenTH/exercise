package image.faceanalyze;

import java.util.List;

/**
 * Auto-generated: 2018-12-25 15:6:3
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class FaceAnalyzeResult {

    private boolean hasface;
    private boolean multiple;
    private boolean isHighQuality;
    private List<Faces> faces;
    public void setHasface(boolean hasface) {
         this.hasface = hasface;
     }
     public boolean getHasface() {
         return hasface;
     }

    public void setMultiple(boolean multiple) {
         this.multiple = multiple;
     }
     public boolean getMultiple() {
         return multiple;
     }

    public void setIsHighQuality(boolean isHighQuality) {
         this.isHighQuality = isHighQuality;
     }
     public boolean getIsHighQuality() {
         return isHighQuality;
     }

    public void setFaces(List<Faces> faces) {
         this.faces = faces;
     }
     public List<Faces> getFaces() {
         return faces;
     }

}