package cn.htjyb.b;

import android.graphics.Bitmap;
import java.io.File;

public interface a {

    public interface a {
        void a(a aVar, boolean z, int i, String str);
    }

    String cachePath();

    boolean canDownload();

    void cancelDownload();

    void download(boolean z);

    void download(boolean z, cn.htjyb.netlib.a.a aVar);

    String downloadUrl();

    Bitmap getActualBitmap();

    File getLocalFile();

    long getPictureID();

    Bitmap getPlaceholderBitmap();

    Enum getType();

    String getUrl();

    boolean hasLocalFile();

    boolean isDownloading();

    boolean isSelect();

    void registerPictureDownloadListener(a aVar);

    void setRotate(int i);

    void setSelect(boolean z);

    void unregisterPictureDownloadListener(a aVar);

    String webpDownloadUrl();
}
