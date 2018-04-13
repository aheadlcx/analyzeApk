package com.davemorrissey.labs.subscaleview;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public final class ImageSource {
    private final Uri a;
    private final Bitmap b;
    private final Integer c;
    private boolean d;
    private int e;
    private int f;
    private Rect g;
    private boolean h;

    private ImageSource(Bitmap bitmap, boolean z) {
        this.b = bitmap;
        this.a = null;
        this.c = null;
        this.d = false;
        this.e = bitmap.getWidth();
        this.f = bitmap.getHeight();
        this.h = z;
    }

    private ImageSource(Uri uri) {
        String uri2 = uri.toString();
        if (uri2.startsWith("file:///") && !new File(uri2.substring("file:///".length() - 1)).exists()) {
            try {
                uri = Uri.parse(URLDecoder.decode(uri2, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
            }
        }
        this.b = null;
        this.a = uri;
        this.c = null;
        this.d = true;
    }

    private ImageSource(int i) {
        this.b = null;
        this.a = null;
        this.c = Integer.valueOf(i);
        this.d = true;
    }

    public static ImageSource resource(int i) {
        return new ImageSource(i);
    }

    public static ImageSource asset(String str) {
        if (str != null) {
            return uri("file:///android_asset/" + str);
        }
        throw new NullPointerException("Asset name must not be null");
    }

    public static ImageSource uri(String str) {
        if (str == null) {
            throw new NullPointerException("Uri must not be null");
        }
        if (!str.contains("://")) {
            if (str.startsWith(MqttTopic.TOPIC_LEVEL_SEPARATOR)) {
                str = str.substring(1);
            }
            str = "file:///" + str;
        }
        return new ImageSource(Uri.parse(str));
    }

    public static ImageSource uri(Uri uri) {
        if (uri != null) {
            return new ImageSource(uri);
        }
        throw new NullPointerException("Uri must not be null");
    }

    public static ImageSource bitmap(Bitmap bitmap) {
        if (bitmap != null) {
            return new ImageSource(bitmap, false);
        }
        throw new NullPointerException("Bitmap must not be null");
    }

    public static ImageSource cachedBitmap(Bitmap bitmap) {
        if (bitmap != null) {
            return new ImageSource(bitmap, true);
        }
        throw new NullPointerException("Bitmap must not be null");
    }

    public ImageSource tilingEnabled() {
        return tiling(true);
    }

    public ImageSource tilingDisabled() {
        return tiling(false);
    }

    public ImageSource tiling(boolean z) {
        this.d = z;
        return this;
    }

    public ImageSource region(Rect rect) {
        this.g = rect;
        i();
        return this;
    }

    public ImageSource dimensions(int i, int i2) {
        if (this.b == null) {
            this.e = i;
            this.f = i2;
        }
        i();
        return this;
    }

    private void i() {
        if (this.g != null) {
            this.d = true;
            this.e = this.g.width();
            this.f = this.g.height();
        }
    }

    protected final Uri a() {
        return this.a;
    }

    protected final Bitmap b() {
        return this.b;
    }

    protected final Integer c() {
        return this.c;
    }

    protected final boolean d() {
        return this.d;
    }

    protected final int e() {
        return this.e;
    }

    protected final int f() {
        return this.f;
    }

    protected final Rect g() {
        return this.g;
    }

    protected final boolean h() {
        return this.h;
    }
}
