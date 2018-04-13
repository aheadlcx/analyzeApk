package com.bumptech.glide.load.b;

import android.net.Uri;
import com.facebook.common.util.UriUtil;

final class a {
    private static final int a = "file:///android_asset/".length();

    public static boolean a(Uri uri) {
        return UriUtil.LOCAL_FILE_SCHEME.equals(uri.getScheme()) && !uri.getPathSegments().isEmpty() && "android_asset".equals(uri.getPathSegments().get(0));
    }

    public static String b(Uri uri) {
        return uri.toString().substring(a);
    }
}
