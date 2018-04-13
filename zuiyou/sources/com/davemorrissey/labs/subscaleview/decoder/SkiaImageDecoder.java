package com.davemorrissey.labs.subscaleview.decoder;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.support.annotation.Keep;
import android.text.TextUtils;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import java.io.InputStream;
import java.util.List;

public class SkiaImageDecoder implements c {
    private final Config a;

    @Keep
    public SkiaImageDecoder() {
        this(null);
    }

    public SkiaImageDecoder(Config config) {
        Config preferredBitmapConfig = SubsamplingScaleImageView.getPreferredBitmapConfig();
        if (config != null) {
            this.a = config;
        } else if (preferredBitmapConfig != null) {
            this.a = preferredBitmapConfig;
        } else {
            this.a = Config.RGB_565;
        }
    }

    public Bitmap a(Context context, Uri uri) throws Exception {
        Bitmap decodeResource;
        InputStream inputStream = null;
        String uri2 = uri.toString();
        Options options = new Options();
        options.inPreferredConfig = this.a;
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
            decodeResource = BitmapFactory.decodeResource(context.getResources(), identifier, options);
        } else if (uri2.startsWith("file:///android_asset/")) {
            decodeResource = BitmapFactory.decodeStream(context.getAssets().open(uri2.substring("file:///android_asset/".length())), null, options);
        } else if (uri2.startsWith("file://")) {
            decodeResource = BitmapFactory.decodeFile(uri2.substring("file://".length()), options);
        } else {
            try {
                inputStream = context.getContentResolver().openInputStream(uri);
                decodeResource = BitmapFactory.decodeStream(inputStream, null, options);
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
        if (decodeResource != null) {
            return decodeResource;
        }
        throw new RuntimeException("Skia image region decoder returned null bitmap - image format may not be supported");
    }
}
