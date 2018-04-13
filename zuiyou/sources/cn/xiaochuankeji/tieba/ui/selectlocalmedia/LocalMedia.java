package cn.xiaochuankeji.tieba.ui.selectlocalmedia;

import java.io.Serializable;

public class LocalMedia implements Serializable {
    int bucketID;
    long dateAdded;
    public long duration;
    public String fmt;
    public int height;
    public long id;
    public String md5;
    public int mediaID;
    public String mimeType;
    public String ossKey;
    public String path;
    public String resId;
    public String resType;
    public int rotate;
    public String serverUrl;
    public long size;
    public int type;
    public String uri;
    public String videoThumbUrl;
    public int width;

    public String toString() {
        return "LocalMedia{path='" + this.path + '\'' + ", type=" + this.type + ", mimeType='" + this.mimeType + '\'' + ", width=" + this.width + ", height=" + this.height + ", resId='" + this.resId + '\'' + ", fmt='" + this.fmt + '\'' + ", id=" + this.id + '}';
    }
}
