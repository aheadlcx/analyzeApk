package com.huawei.hms.update.provider;

import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import java.io.File;
import java.io.IOException;

class a {
    private Context a;
    private String b;

    a() {
    }

    public void a(Context context) {
        com.huawei.hms.c.a.a(context, "context nust not be null.");
        this.a = context;
    }

    public File a(String str) {
        String a = a();
        if (a == null) {
            return null;
        }
        return b(new File(a, str));
    }

    private String a() {
        String str;
        Context context = (Context) com.huawei.hms.c.a.b(this.a, "mContext is null, call setContext first.");
        synchronized (this) {
            if (this.b == null) {
                if (context.getExternalCacheDir() != null) {
                    this.b = a(context.getExternalCacheDir());
                } else {
                    this.b = a(context.getFilesDir());
                }
            }
            str = this.b;
        }
        return str;
    }

    public Uri a(File file, String str) {
        String a = a(file);
        if (a == null) {
            return null;
        }
        a = b(a);
        if (a != null) {
            return new Builder().scheme("content").authority(str).encodedPath(a).build();
        }
        return null;
    }

    private String b(String str) {
        String a = a();
        if (a == null || !str.startsWith(a)) {
            return null;
        }
        int length;
        if (a.endsWith("/")) {
            length = a.length();
        } else {
            length = a.length() + 1;
        }
        return Uri.encode("ContentUriHelper") + '/' + str.substring(length);
    }

    public File a(Uri uri) {
        String encodedPath = uri.getEncodedPath();
        if (encodedPath == null) {
            return null;
        }
        encodedPath = c(encodedPath);
        if (encodedPath != null) {
            return b(new File(encodedPath));
        }
        return null;
    }

    private String c(String str) {
        String a = a();
        if (a == null) {
            return null;
        }
        int indexOf = str.indexOf(47, 1);
        if (indexOf < 0) {
            return null;
        }
        if (!"ContentUriHelper".equals(Uri.decode(str.substring(1, indexOf)))) {
            return null;
        }
        String a2 = a(new File(a, Uri.decode(str.substring(indexOf + 1))));
        if (a2 == null || !a2.startsWith(a)) {
            return null;
        }
        return a2;
    }

    private static String a(File file) {
        String str = null;
        if (file != null) {
            try {
                str = file.getCanonicalPath();
            } catch (IOException e) {
            }
        }
        return str;
    }

    private static File b(File file) {
        File file2 = null;
        if (file != null) {
            try {
                file2 = file.getCanonicalFile();
            } catch (IOException e) {
            }
        }
        return file2;
    }
}
