package qsbk.app.model.media;

import android.text.TextUtils;
import com.tencent.open.SocialConstants;
import qsbk.app.R;

public enum MediaFormat {
    UNKNOW("unknow", "unknow", 0),
    IMAGE_LONG("img_long", "image/", 1),
    IMAGE_STATIC(SocialConstants.PARAM_IMG_URL, "image/", 2),
    IMAGE_GIF_VIDEO("gif", "gif", 3),
    IMAGE_GIF("gif_image", "image/gif", 4),
    VIDEO("video", "video/", 5),
    IMAGE_ORIGIN("origin", "origin/", 2);
    
    public String mimeType;
    public String name;
    public int upload;

    private MediaFormat(String str, String str2, int i) {
        this.name = str;
        this.mimeType = str2;
        this.upload = i;
    }

    public static MediaFormat getMediaFormatFromMimeType(String str) {
        if (TextUtils.equals(IMAGE_GIF.mimeType, str)) {
            return IMAGE_GIF;
        }
        if (TextUtils.equals(IMAGE_GIF_VIDEO.mimeType, str)) {
            return IMAGE_GIF_VIDEO;
        }
        if (!TextUtils.isEmpty(str) && str.contains(VIDEO.mimeType)) {
            return VIDEO;
        }
        if (TextUtils.equals(str, IMAGE_ORIGIN.mimeType)) {
            return IMAGE_ORIGIN;
        }
        return IMAGE_STATIC;
    }

    public static MediaFormat getMediaFormatFromNetwork(String str) {
        if ("gif".equals(str)) {
            return IMAGE_GIF_VIDEO;
        }
        if ("image".equals(str)) {
            return IMAGE_STATIC;
        }
        if ("video".equals(str)) {
            return VIDEO;
        }
        if ("gif_image".equals(str)) {
            return IMAGE_GIF;
        }
        if ("origin".equals(str)) {
            return IMAGE_ORIGIN;
        }
        return IMAGE_STATIC;
    }

    public static String getNetAlias(MediaFormat mediaFormat) {
        switch (a.a[mediaFormat.ordinal()]) {
            case 1:
            case 2:
                return "image";
            case 3:
                return "video";
            case 4:
                return "gif";
            default:
                return "image";
        }
    }

    public static int getFormatTagImage(MediaFormat mediaFormat) {
        switch (a.a[mediaFormat.ordinal()]) {
            case 2:
                return R.drawable.ic_long_tag;
            case 4:
            case 5:
                return R.drawable.ic_gif_tag;
            default:
                return 0;
        }
    }
}
