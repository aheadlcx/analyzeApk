package qsbk.app.model;

import java.io.Serializable;
import org.json.JSONArray;

public class ImageSize implements Serializable {
    public static final int UNKNOW_SIZE = 0;
    private int a = 0;
    private int b = 0;
    private int c = 0;

    private ImageSize() {
    }

    public ImageSize(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    public static ImageSize newImageSize(JSONArray jSONArray) {
        ImageSize imageSize = new ImageSize();
        if (jSONArray != null) {
            imageSize.a = jSONArray.optInt(0);
            imageSize.b = jSONArray.optInt(1);
            imageSize.c = jSONArray.optInt(2);
        }
        return imageSize;
    }

    public static final JSONArray toJSONArray(ImageSize imageSize) {
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(imageSize.a);
        jSONArray.put(imageSize.b);
        jSONArray.put(imageSize.c);
        return jSONArray;
    }

    public int getHeight() {
        return this.b;
    }

    public void setHeight(int i) {
        this.b = i;
    }

    public int getSize() {
        return this.c;
    }

    public void setSize(int i) {
        this.c = i;
    }

    public int getWidth() {
        return this.a;
    }

    public void setWidth(int i) {
        this.a = i;
    }

    public float getAspectRatio() {
        if (this.b <= 0 || this.a <= 0) {
            return 1.0f;
        }
        return (1.0f * ((float) this.a)) / ((float) this.b);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ImageSize)) {
            return false;
        }
        ImageSize imageSize = (ImageSize) obj;
        if (imageSize.c == this.c && imageSize.b == this.b && imageSize.a == this.a) {
            return true;
        }
        return false;
    }

    public String toString() {
        return String.format("[%s,%s,%s]", new Object[]{Integer.valueOf(this.a), Integer.valueOf(this.b), Integer.valueOf(this.c)});
    }
}
