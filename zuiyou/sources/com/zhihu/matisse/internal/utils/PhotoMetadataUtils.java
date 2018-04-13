package com.zhihu.matisse.internal.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Point;
import android.net.Uri;
import android.support.media.ExifInterface;
import android.util.DisplayMetrics;
import android.util.Log;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.R;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.IncapableCause;
import com.zhihu.matisse.internal.entity.Item;
import com.zhihu.matisse.internal.entity.SelectionSpec;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

public final class PhotoMetadataUtils {
    private static final int MAX_WIDTH = 1600;
    private static final String SCHEME_CONTENT = "content";
    private static final String TAG = PhotoMetadataUtils.class.getSimpleName();

    private PhotoMetadataUtils() {
        throw new AssertionError("oops! the utility class is about to be instantiated...");
    }

    public static int getPixelsCount(ContentResolver contentResolver, Uri uri) {
        Point bitmapBound = getBitmapBound(contentResolver, uri);
        return bitmapBound.y * bitmapBound.x;
    }

    public static Point getBitmapSize(Uri uri, Activity activity) {
        int i;
        ContentResolver contentResolver = activity.getContentResolver();
        Point bitmapBound = getBitmapBound(contentResolver, uri);
        int i2 = bitmapBound.x;
        int i3 = bitmapBound.y;
        if (shouldRotate(contentResolver, uri)) {
            i = bitmapBound.y;
            i2 = bitmapBound.x;
        } else {
            i = i2;
            i2 = i3;
        }
        if (i2 == 0) {
            return new Point(MAX_WIDTH, MAX_WIDTH);
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        float f = ((float) displayMetrics.widthPixels) / ((float) i);
        float f2 = ((float) displayMetrics.heightPixels) / ((float) i2);
        if (f > f2) {
            return new Point((int) (((float) i) * f), (int) (((float) i2) * f2));
        }
        return new Point((int) (((float) i) * f), (int) (((float) i2) * f2));
    }

    public static Point getBitmapBound(ContentResolver contentResolver, Uri uri) {
        Point point;
        Throwable th;
        InputStream openInputStream;
        try {
            Options options = new Options();
            options.inJustDecodeBounds = true;
            openInputStream = contentResolver.openInputStream(uri);
            try {
                BitmapFactory.decodeStream(openInputStream, null, options);
                point = new Point(options.outWidth, options.outHeight);
                if (openInputStream != null) {
                    try {
                        openInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (FileNotFoundException e2) {
                try {
                    point = new Point(0, 0);
                    if (openInputStream != null) {
                        try {
                            openInputStream.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    return point;
                } catch (Throwable th2) {
                    th = th2;
                    if (openInputStream != null) {
                        try {
                            openInputStream.close();
                        } catch (IOException e32) {
                            e32.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        } catch (FileNotFoundException e4) {
            openInputStream = null;
            point = new Point(0, 0);
            if (openInputStream != null) {
                openInputStream.close();
            }
            return point;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            openInputStream = null;
            th = th4;
            if (openInputStream != null) {
                openInputStream.close();
            }
            throw th;
        }
        return point;
    }

    public static String getPath(ContentResolver contentResolver, Uri uri) {
        Throwable th;
        Cursor cursor = null;
        if (uri == null) {
            return null;
        }
        if (!"content".equals(uri.getScheme())) {
            return uri.getPath();
        }
        try {
            Cursor query = contentResolver.query(uri, new String[]{"_data"}, null, null, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        String string = query.getString(query.getColumnIndex("_data"));
                        if (query == null) {
                            return string;
                        }
                        query.close();
                        return string;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    cursor = query;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            if (query != null) {
                query.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public static IncapableCause isAcceptable(Context context, Item item) {
        if (!isSelectableType(context, item)) {
            return new IncapableCause(context.getString(R.string.error_file_type));
        }
        if (SelectionSpec.getInstance().filters != null) {
            for (Filter filter : SelectionSpec.getInstance().filters) {
                IncapableCause filter2 = filter.filter(context, item);
                if (filter2 != null) {
                    return filter2;
                }
            }
        }
        return null;
    }

    private static boolean isSelectableType(Context context, Item item) {
        if (context == null) {
            return false;
        }
        ContentResolver contentResolver = context.getContentResolver();
        for (MimeType checkType : SelectionSpec.getInstance().mimeTypeSet) {
            if (checkType.checkType(contentResolver, item.getContentUri())) {
                return true;
            }
        }
        return false;
    }

    private static boolean shouldRotate(ContentResolver contentResolver, Uri uri) {
        try {
            int attributeInt = ExifInterfaceCompat.newInstance(getPath(contentResolver, uri)).getAttributeInt(ExifInterface.TAG_ORIENTATION, -1);
            if (attributeInt == 6 || attributeInt == 8) {
                return true;
            }
            return false;
        } catch (Exception e) {
            Log.e(TAG, "could not read exif info of the image: " + uri);
            return false;
        }
    }

    public static float getSizeInMB(long j) {
        return Float.valueOf(new DecimalFormat("0.0").format((double) ((((float) j) / 1024.0f) / 1024.0f))).floatValue();
    }

    public static float getLengthInMinute(long j) {
        return Float.valueOf(new DecimalFormat("0.0").format((double) ((((float) j) / 60.0f) / 1000.0f))).floatValue();
    }
}
