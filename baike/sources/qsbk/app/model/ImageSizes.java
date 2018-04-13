package qsbk.app.model;

import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class ImageSizes implements Serializable {
    private ImageSize a;
    private ImageSize b;

    private ImageSizes() {
    }

    public static ImageSizes newImageSizes(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        ImageSizes imageSizes = new ImageSizes();
        imageSizes.a = ImageSize.newImageSize(jSONObject.optJSONArray("s"));
        imageSizes.b = ImageSize.newImageSize(jSONObject.optJSONArray("m"));
        return imageSizes;
    }

    public static JSONObject toJSONObject(ImageSizes imageSizes) {
        if (imageSizes == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("s", ImageSize.toJSONArray(imageSizes.a));
            jSONObject.put("m", ImageSize.toJSONArray(imageSizes.b));
            return jSONObject;
        } catch (JSONException e) {
            return jSONObject;
        }
    }

    public ImageSize smallSize() {
        return this.a;
    }

    public ImageSize mediumSize() {
        return this.b;
    }

    public void setSmall(ImageSize imageSize) {
        this.a = imageSize;
    }

    public void setMedium(ImageSize imageSize) {
        this.b = imageSize;
    }

    public String toString() {
        return String.format("s:%s, m:%s", new Object[]{this.a, this.b});
    }
}
