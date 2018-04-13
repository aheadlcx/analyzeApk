package cn.xiaochuan.image;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public enum MimeType {
    JPEG("image/jpeg", new HashSet<String>() {
        {
            add("jpg");
            add("jpeg");
        }
    }),
    PNG("image/png", new HashSet<String>() {
        {
            add("png");
        }
    }),
    GIF("image/gif", new HashSet<String>() {
        {
            add("gif");
        }
    }),
    BMP("image/x-ms-bmp", new HashSet<String>() {
        {
            add("bmp");
        }
    }),
    WEBP("image/webp", new HashSet<String>() {
        {
            add("webp");
        }
    }),
    MPEG("video/mpeg", new HashSet<String>() {
        {
            add("mpeg");
            add("mpg");
        }
    }),
    MP4("video/mp4", new HashSet<String>() {
        {
            add("mp4");
            add("m4v");
        }
    }),
    QUICKTIME("video/quicktime", new HashSet<String>() {
        {
            add("mov");
        }
    }),
    THREEGPP("video/3gpp", new HashSet<String>() {
        {
            add("3gp");
            add("3gpp");
        }
    }),
    THREEGPP2("video/3gpp2", new HashSet<String>() {
        {
            add("3g2");
            add("3gpp2");
        }
    }),
    MKV("video/x-matroska", new HashSet<String>() {
        {
            add("mkv");
        }
    }),
    WEBM("video/webm", new HashSet<String>() {
        {
            add("webm");
        }
    }),
    TS("video/mp2ts", new HashSet<String>() {
        {
            add("ts");
        }
    }),
    AVI("video/avi", new HashSet<String>() {
        {
            add("avi");
        }
    });
    
    private final Set<String> mExtensions;
    private final String mMimeTypeName;

    private MimeType(String str, Set<String> set) {
        this.mMimeTypeName = str;
        this.mExtensions = set;
    }

    public static Set<MimeType> ofAll() {
        return EnumSet.allOf(MimeType.class);
    }

    public static Set<MimeType> of(MimeType mimeType, MimeType... mimeTypeArr) {
        return EnumSet.of(mimeType, mimeTypeArr);
    }

    public static Set<MimeType> ofImage() {
        return EnumSet.of(JPEG, PNG, GIF, BMP, WEBP);
    }

    public static Set<MimeType> ofVideo() {
        return EnumSet.of(MPEG, new MimeType[]{MP4, QUICKTIME, THREEGPP, THREEGPP2, MKV, WEBM, TS, AVI});
    }

    public String toString() {
        return this.mMimeTypeName;
    }
}
