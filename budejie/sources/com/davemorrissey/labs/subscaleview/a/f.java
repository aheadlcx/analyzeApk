package com.davemorrissey.labs.subscaleview.a;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.text.TextUtils;
import java.io.InputStream;
import java.util.List;

public class f implements d {
    private BitmapRegionDecoder a;
    private final Object b = new Object();

    public Point a(Context context, Uri uri) throws Exception {
        String uri2 = uri.toString();
        if (uri2.startsWith("android.resource://")) {
            Resources resources;
            int identifier;
            String authority = uri.getAuthority();
            if (context.getPackageName().equals(authority)) {
                resources = context.getResources();
            } else {
                resources = context.getPackageManager().getResourcesForApplication(authority);
            }
            List pathSegments = uri.getPathSegments();
            int size = pathSegments.size();
            if (size == 2 && ((String) pathSegments.get(0)).equals("drawable")) {
                identifier = resources.getIdentifier((String) pathSegments.get(1), "drawable", authority);
            } else if (size == 1 && TextUtils.isDigitsOnly((CharSequence) pathSegments.get(0))) {
                try {
                    identifier = Integer.parseInt((String) pathSegments.get(0));
                } catch (NumberFormatException e) {
                    identifier = 0;
                }
            } else {
                identifier = 0;
            }
            this.a = BitmapRegionDecoder.newInstance(context.getResources().openRawResource(identifier), false);
        } else if (uri2.startsWith("file:///android_asset/")) {
            this.a = BitmapRegionDecoder.newInstance(context.getAssets().open(uri2.substring("file:///android_asset/".length()), 1), false);
        } else if (uri2.startsWith("file://")) {
            this.a = BitmapRegionDecoder.newInstance(uri2.substring("file://".length()), false);
        } else {
            InputStream inputStream = null;
            try {
                inputStream = context.getContentResolver().openInputStream(uri);
                this.a = BitmapRegionDecoder.newInstance(inputStream, false);
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception e2) {
                    }
                }
            } catch (Throwable th) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception e3) {
                    }
                }
            }
        }
        return new Point(this.a.getWidth(), this.a.getHeight());
    }

    public Bitmap a(Rect rect, int i) {
        Bitmap decodeRegion;
        synchronized (this.b) {
            Options options = new Options();
            options.inSampleSize = i;
            options.inPreferredConfig = Config.RGB_565;
            decodeRegion = this.a.decodeRegion(rect, options);
            if (decodeRegion == null) {
                throw new RuntimeException("Skia image decoder returned null bitmap - image format may not be supported");
            }
        }
        return decodeRegion;
    }

    public boolean a() {
        return (this.a == null || this.a.isRecycled()) ? false : true;
    }

    public void b() {
        this.a.recycle();
    }
}
