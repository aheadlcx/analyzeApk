package com.zhihu.matisse.internal.utils;

import android.media.ExifInterface;
import android.text.TextUtils;
import android.util.Log;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

final class ExifInterfaceCompat {
    private static final int EXIF_DEGREE_FALLBACK_VALUE = -1;
    private static final String TAG = ExifInterfaceCompat.class.getSimpleName();

    private ExifInterfaceCompat() {
    }

    public static ExifInterface newInstance(String str) throws IOException {
        if (str != null) {
            return new ExifInterface(str);
        }
        throw new NullPointerException("filename should not be null");
    }

    private static Date getExifDateTime(String str) {
        Date date = null;
        try {
            Object attribute = newInstance(str).getAttribute(android.support.media.ExifInterface.TAG_DATETIME);
            if (!TextUtils.isEmpty(attribute)) {
                try {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
                    simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                    date = simpleDateFormat.parse(attribute);
                } catch (Throwable e) {
                    Log.d(TAG, "failed to parse date taken", e);
                }
            }
        } catch (Throwable e2) {
            Log.e(TAG, "cannot read exif", e2);
        }
        return date;
    }

    public static long getExifDateTimeInMillis(String str) {
        Date exifDateTime = getExifDateTime(str);
        if (exifDateTime == null) {
            return -1;
        }
        return exifDateTime.getTime();
    }

    public static int getExifOrientation(String str) {
        try {
            int attributeInt = newInstance(str).getAttributeInt(android.support.media.ExifInterface.TAG_ORIENTATION, -1);
            if (attributeInt == -1) {
                return 0;
            }
            switch (attributeInt) {
                case 3:
                    return 180;
                case 6:
                    return 90;
                case 8:
                    return 270;
                default:
                    return 0;
            }
        } catch (Throwable e) {
            Log.e(TAG, "cannot read exif", e);
            return -1;
        }
    }
}
