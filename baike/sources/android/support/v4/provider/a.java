package android.support.v4.provider;

import android.content.Context;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Log;

@RequiresApi(19)
class a {
    public static boolean isDocumentUri(Context context, Uri uri) {
        return DocumentsContract.isDocumentUri(context, uri);
    }

    public static boolean isVirtual(Context context, Uri uri) {
        if (isDocumentUri(context, uri) && (getFlags(context, uri) & 512) != 0) {
            return true;
        }
        return false;
    }

    public static String getName(Context context, Uri uri) {
        return a(context, uri, "_display_name", null);
    }

    private static String a(Context context, Uri uri) {
        return a(context, uri, "mime_type", null);
    }

    public static String getType(Context context, Uri uri) {
        String a = a(context, uri);
        if ("vnd.android.document/directory".equals(a)) {
            return null;
        }
        return a;
    }

    public static long getFlags(Context context, Uri uri) {
        return a(context, uri, "flags", 0);
    }

    public static boolean isDirectory(Context context, Uri uri) {
        return "vnd.android.document/directory".equals(a(context, uri));
    }

    public static boolean isFile(Context context, Uri uri) {
        CharSequence a = a(context, uri);
        if ("vnd.android.document/directory".equals(a) || TextUtils.isEmpty(a)) {
            return false;
        }
        return true;
    }

    public static long lastModified(Context context, Uri uri) {
        return a(context, uri, "last_modified", 0);
    }

    public static long length(Context context, Uri uri) {
        return a(context, uri, "_size", 0);
    }

    public static boolean canRead(Context context, Uri uri) {
        if (context.checkCallingOrSelfUriPermission(uri, 1) == 0 && !TextUtils.isEmpty(a(context, uri))) {
            return true;
        }
        return false;
    }

    public static boolean canWrite(Context context, Uri uri) {
        if (context.checkCallingOrSelfUriPermission(uri, 2) != 0) {
            return false;
        }
        CharSequence a = a(context, uri);
        int a2 = a(context, uri, "flags", 0);
        if (TextUtils.isEmpty(a)) {
            return false;
        }
        if ((a2 & 4) != 0) {
            return true;
        }
        if ("vnd.android.document/directory".equals(a) && (a2 & 8) != 0) {
            return true;
        }
        if (TextUtils.isEmpty(a) || (a2 & 2) == 0) {
            return false;
        }
        return true;
    }

    public static boolean exists(Context context, Uri uri) {
        AutoCloseable query;
        Object e;
        Throwable th;
        try {
            query = context.getContentResolver().query(uri, new String[]{"document_id"}, null, null, null);
            try {
                boolean z = query.getCount() > 0;
                a(query);
                return z;
            } catch (Exception e2) {
                e = e2;
                try {
                    Log.w("DocumentFile", "Failed query: " + e);
                    a(query);
                    return false;
                } catch (Throwable th2) {
                    th = th2;
                    a(query);
                    throw th;
                }
            }
        } catch (Exception e3) {
            e = e3;
            query = null;
            Log.w("DocumentFile", "Failed query: " + e);
            a(query);
            return false;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            a(query);
            throw th;
        }
    }

    private static String a(Context context, Uri uri, String str, String str2) {
        Object e;
        Throwable th;
        AutoCloseable query;
        try {
            query = context.getContentResolver().query(uri, new String[]{str}, null, null, null);
            try {
                if (!query.moveToFirst() || query.isNull(0)) {
                    a(query);
                    return str2;
                }
                str2 = query.getString(0);
                a(query);
                return str2;
            } catch (Exception e2) {
                e = e2;
                try {
                    Log.w("DocumentFile", "Failed query: " + e);
                    a(query);
                    return str2;
                } catch (Throwable th2) {
                    th = th2;
                    a(query);
                    throw th;
                }
            }
        } catch (Exception e3) {
            e = e3;
            query = null;
            Log.w("DocumentFile", "Failed query: " + e);
            a(query);
            return str2;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            a(query);
            throw th;
        }
    }

    private static int a(Context context, Uri uri, String str, int i) {
        return (int) a(context, uri, str, (long) i);
    }

    private static long a(Context context, Uri uri, String str, long j) {
        AutoCloseable query;
        Object e;
        Throwable th;
        try {
            query = context.getContentResolver().query(uri, new String[]{str}, null, null, null);
            try {
                if (!query.moveToFirst() || query.isNull(0)) {
                    a(query);
                    return j;
                }
                j = query.getLong(0);
                a(query);
                return j;
            } catch (Exception e2) {
                e = e2;
                try {
                    Log.w("DocumentFile", "Failed query: " + e);
                    a(query);
                    return j;
                } catch (Throwable th2) {
                    th = th2;
                    a(query);
                    throw th;
                }
            }
        } catch (Exception e3) {
            e = e3;
            query = null;
            Log.w("DocumentFile", "Failed query: " + e);
            a(query);
            return j;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            a(query);
            throw th;
        }
    }

    private static void a(AutoCloseable autoCloseable) {
        if (autoCloseable != null) {
            try {
                autoCloseable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
            }
        }
    }
}
