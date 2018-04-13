package com.facebook.common.util;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.net.Uri.Builder;
import android.provider.ContactsContract;
import android.provider.MediaStore.Images.Media;
import javax.annotation.Nullable;

public class d {
    private static final String a = Uri.withAppendedPath(ContactsContract.AUTHORITY_URI, "display_photo").getPath();

    public static boolean a(@Nullable Uri uri) {
        String j = j(uri);
        return "https".equals(j) || "http".equals(j);
    }

    public static boolean b(@Nullable Uri uri) {
        return "file".equals(j(uri));
    }

    public static boolean c(@Nullable Uri uri) {
        return "content".equals(j(uri));
    }

    public static boolean d(Uri uri) {
        return c(uri) && "com.android.contacts".equals(uri.getAuthority()) && !uri.getPath().startsWith(a);
    }

    public static boolean e(Uri uri) {
        String uri2 = uri.toString();
        return uri2.startsWith(Media.EXTERNAL_CONTENT_URI.toString()) || uri2.startsWith(Media.INTERNAL_CONTENT_URI.toString());
    }

    public static boolean f(@Nullable Uri uri) {
        return "asset".equals(j(uri));
    }

    public static boolean g(@Nullable Uri uri) {
        return "res".equals(j(uri));
    }

    public static boolean h(@Nullable Uri uri) {
        return "android.resource".equals(j(uri));
    }

    public static boolean i(@Nullable Uri uri) {
        return "data".equals(j(uri));
    }

    @Nullable
    public static String j(@Nullable Uri uri) {
        return uri == null ? null : uri.getScheme();
    }

    @Nullable
    public static String a(ContentResolver contentResolver, Uri uri) {
        Throwable th;
        Cursor cursor = null;
        if (c(uri)) {
            try {
                String string;
                Cursor query = contentResolver.query(uri, null, null, null, null);
                if (query != null) {
                    try {
                        if (query.moveToFirst()) {
                            int columnIndex = query.getColumnIndex("_data");
                            if (columnIndex != -1) {
                                string = query.getString(columnIndex);
                                if (query != null) {
                                    return string;
                                }
                                query.close();
                                return string;
                            }
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
                string = null;
                if (query != null) {
                    return string;
                }
                query.close();
                return string;
            } catch (Throwable th3) {
                th = th3;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        } else if (b(uri)) {
            return uri.getPath();
        } else {
            return null;
        }
    }

    public static Uri a(int i) {
        return new Builder().scheme("res").path(String.valueOf(i)).build();
    }
}
