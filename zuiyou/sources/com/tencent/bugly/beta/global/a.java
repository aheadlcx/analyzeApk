package com.tencent.bugly.beta.global;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.view.ViewCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.tencent.bugly.beta.utils.c;
import com.tencent.bugly.proguard.an;
import com.tencent.bugly.proguard.ap;
import com.tencent.bugly.proguard.p;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.tencent.wcdb.database.SQLiteDatabase;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public class a {
    public static int a(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return 0;
            }
            if (activeNetworkInfo.getType() == 1) {
                return 1;
            }
            if (activeNetworkInfo.getType() == 0) {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    switch (telephonyManager.getNetworkType()) {
                        case 1:
                        case 2:
                        case 4:
                        case 7:
                        case 11:
                            return 2;
                        case 3:
                        case 5:
                        case 6:
                        case 8:
                        case 9:
                        case 10:
                        case 12:
                        case 14:
                        case 15:
                            return 3;
                        case 13:
                            return 4;
                        default:
                            return 0;
                    }
                }
            }
            return 0;
        } catch (Throwable e) {
            if (!an.a(e)) {
                e.printStackTrace();
            }
        }
    }

    public static int a(Context context, float f) {
        return (int) ((context.getResources().getDisplayMetrics().density * f) + 0.5f);
    }

    public static BitmapDrawable a(Bitmap bitmap, int i, int i2, float f) {
        int i3 = (int) (((float) (e.E.B.widthPixels * e.E.B.heightPixels)) * 0.8f);
        if (bitmap == null || i * i2 > i3) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, i, i2);
        RectF rectF = new RectF(rect);
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, 3));
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        canvas.drawRoundRect(rectF, f, f, paint);
        canvas.drawRect(0.0f, f, (float) i, (float) i2, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), rect, paint);
        return new BitmapDrawable(createBitmap);
    }

    public static Bitmap a(Context context, int i, Object... objArr) {
        if (objArr == null || objArr.length <= 0 || (i != 0 && i != 1)) {
            return null;
        }
        int i2;
        File file;
        if (i == 0) {
            try {
                if (TextUtils.isEmpty((String) objArr[0])) {
                    return null;
                }
                File file2 = new File((String) objArr[0]);
                if (!file2.exists() || file2.length() > 1048576) {
                    return null;
                }
                i2 = 0;
                file = file2;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (i == 1) {
            i2 = ((Integer) objArr[0]).intValue();
            file = null;
        } else {
            i2 = 0;
            file = null;
        }
        int i3 = e.E.B.widthPixels;
        Options options = null;
        if (i3 > 0) {
            int i4;
            int i5;
            options = new Options();
            options.inJustDecodeBounds = true;
            if (i == 0) {
                BitmapFactory.decodeFile(file.getPath(), options);
            } else if (i == 1) {
                BitmapFactory.decodeResource(context.getResources(), i2, options);
            }
            int i6 = (int) ((((float) options.outHeight) / ((float) options.outWidth)) * ((float) i3));
            int min = Math.min(i3, i6);
            double d = (double) options.outWidth;
            double d2 = (double) options.outHeight;
            if (i3 * i6 == -1) {
                i4 = 1;
            } else {
                i4 = (int) Math.ceil(Math.sqrt(((d * d2) / ((double) i3)) * ((double) i6)));
            }
            if (min == -1) {
                i5 = 128;
            } else {
                i5 = (int) Math.min(Math.floor(d / ((double) min)), Math.floor(d2 / ((double) min)));
            }
            if (i5 < i4) {
            }
            if (i3 * i6 == -1 && min == -1) {
                i5 = 1;
            } else if (min == -1) {
                i5 = i4;
            }
            if (i5 <= 8) {
                i4 = 1;
                while (i4 < i5) {
                    i4 <<= 1;
                }
            } else {
                i4 = ((i5 + 7) / 8) * 8;
            }
            options.inSampleSize = i4;
            options.inJustDecodeBounds = false;
            options.inInputShareable = true;
            options.inPurgeable = true;
        }
        if (i == 0) {
            return BitmapFactory.decodeFile(file.getPath(), options);
        }
        if (i == 1) {
            return BitmapFactory.decodeResource(context.getResources(), i2, options);
        }
        return null;
    }

    public static boolean a(Context context, File file, String str) {
        if (file != null) {
            try {
                if (file.exists() && file.getName().endsWith(ShareConstants.PATCH_SUFFIX)) {
                    String a = ap.a(file, "MD5");
                    if (TextUtils.isEmpty(str) || TextUtils.equals(str.toUpperCase(), a)) {
                        Runtime.getRuntime().exec("chmod 777 " + file.getAbsolutePath());
                        Intent intent = new Intent("android.intent.action.VIEW");
                        String str2 = "application/vnd.android.package-archive";
                        if (VERSION.SDK_INT >= 24) {
                            String str3 = "android.support.v4.content.FileProvider";
                            intent.addFlags(1);
                            if (Class.forName(str3) == null) {
                                an.e("can't find class android.support.v4.content.FileProvider", new Object[0]);
                                return false;
                            }
                            Uri uri = (Uri) ap.a(str3, "getUriForFile", null, new Class[]{Context.class, String.class, File.class}, new Object[]{context, com.tencent.bugly.crashreport.common.info.a.a(context).d + ".fileProvider", file});
                            if (uri == null) {
                                an.e("file location is " + file.toString(), new Object[0]);
                                an.e("install failed, contentUri is null!", new Object[0]);
                                return false;
                            }
                            an.c("contentUri is " + uri, new Object[0]);
                            intent.setDataAndType(uri, str2);
                        } else {
                            intent.setDataAndType(Uri.fromFile(file), str2);
                        }
                        intent.addFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
                        context.startActivity(intent);
                        a("installApkMd5", a);
                        return true;
                    }
                    an.a("md5 error [file md5: %s] [target md5: %s]", a, str);
                    return false;
                }
            } catch (Throwable e) {
                if (!an.b(e)) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public static boolean a(File file, String str, String str2) {
        if (file != null) {
            try {
                if (file.exists()) {
                    CharSequence a = ap.a(file, str2);
                    if (!TextUtils.isEmpty(str) && TextUtils.equals(str.toUpperCase(), a)) {
                        return true;
                    }
                    an.a("checkFileUniqueId failed [file  uniqueId %s] [target uniqueId %s]", a, str);
                    return false;
                }
            } catch (Exception e) {
                an.e("checkFileUniqueId exception", new Object[0]);
            }
        }
        return false;
    }

    public static boolean a(File file, File file2) {
        Exception e;
        FileInputStream fileInputStream;
        Throwable th;
        FileOutputStream fileOutputStream = null;
        boolean z = false;
        FileInputStream fileInputStream2 = null;
        FileOutputStream fileOutputStream2 = null;
        if (file != null) {
            FileInputStream fileInputStream3;
            try {
                if (file.exists() && !file.isDirectory()) {
                    fileInputStream3 = new FileInputStream(file);
                    try {
                        fileOutputStream2 = new FileOutputStream(file2, false);
                        try {
                            byte[] bArr = new byte[1048576];
                            while (true) {
                                int read = fileInputStream3.read(bArr);
                                if (read <= 0) {
                                    break;
                                }
                                fileOutputStream2.write(bArr, 0, read);
                            }
                            z = true;
                            if (fileInputStream3 != null) {
                                try {
                                    fileInputStream3.close();
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }
                            if (fileOutputStream2 != null) {
                                fileOutputStream2.close();
                            }
                        } catch (Exception e3) {
                            e = e3;
                            fileInputStream = fileInputStream3;
                            try {
                                e.printStackTrace();
                                if (fileInputStream != null) {
                                    try {
                                        fileInputStream.close();
                                    } catch (IOException e22) {
                                        e22.printStackTrace();
                                    }
                                }
                                if (fileOutputStream2 != null) {
                                    fileOutputStream2.close();
                                }
                                return z;
                            } catch (Throwable th2) {
                                th = th2;
                                fileInputStream3 = fileInputStream;
                                fileOutputStream = fileOutputStream2;
                                if (fileInputStream3 != null) {
                                    try {
                                        fileInputStream3.close();
                                    } catch (IOException e222) {
                                        e222.printStackTrace();
                                        throw th;
                                    }
                                }
                                if (fileOutputStream != null) {
                                    fileOutputStream.close();
                                }
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            fileOutputStream = fileOutputStream2;
                            if (fileInputStream3 != null) {
                                fileInputStream3.close();
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                            throw th;
                        }
                    } catch (Exception e4) {
                        e = e4;
                        fileOutputStream2 = null;
                        fileInputStream = fileInputStream3;
                        e.printStackTrace();
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        if (fileOutputStream2 != null) {
                            fileOutputStream2.close();
                        }
                        return z;
                    } catch (Throwable th4) {
                        th = th4;
                        if (fileInputStream3 != null) {
                            fileInputStream3.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        throw th;
                    }
                    return z;
                }
            } catch (Exception e5) {
                e = e5;
                fileOutputStream2 = null;
                e.printStackTrace();
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (fileOutputStream2 != null) {
                    fileOutputStream2.close();
                }
                return z;
            } catch (Throwable th5) {
                th = th5;
                fileInputStream3 = null;
                if (fileInputStream3 != null) {
                    fileInputStream3.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw th;
            }
        }
        if (null != null) {
            try {
                fileInputStream2.close();
            } catch (IOException e2222) {
                e2222.printStackTrace();
            }
        }
        if (null != null) {
            fileOutputStream2.close();
        }
        return z;
    }

    public static Bitmap a(Drawable drawable) {
        Bitmap createBitmap;
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }
        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            createBitmap = Bitmap.createBitmap(1, 1, Config.ARGB_8888);
        } else {
            createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return createBitmap;
    }

    public static void a(File file) {
        if (file != null && file.exists() && file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null && listFiles.length > 0) {
                for (File delete : listFiles) {
                    if (!delete.delete()) {
                        an.e("cannot delete file:%s", listFiles[r0].getAbsolutePath());
                    }
                }
            }
        }
    }

    public static synchronized <T extends Parcelable> boolean a(String str, T t) {
        boolean z = false;
        synchronized (a.class) {
            if (t != null) {
                byte[] a = ap.a((Parcelable) t);
                if (a != null && p.a.a(1002, str, a)) {
                    z = true;
                }
            }
        }
        return z;
    }

    public static synchronized <T extends Parcelable> T a(String str, Creator<T> creator) {
        T t;
        synchronized (a.class) {
            Map c = p.a.c();
            if (c == null) {
                t = null;
            } else {
                byte[] bArr = (byte[]) c.get(str);
                if (bArr == null || bArr.length <= 0) {
                    t = null;
                } else {
                    Parcelable parcelable = (Parcelable) ap.a(bArr, (Creator) creator);
                }
            }
        }
        return t;
    }

    public static synchronized boolean a(String str) {
        boolean c;
        synchronized (a.class) {
            c = p.a.c(str);
        }
        return c;
    }

    public static String b(String str) {
        return new c(str).a();
    }

    public static void a(String str, String str2) {
        if (e.E.A != null) {
            e.E.A.edit().putString(str, str2).apply();
        }
    }

    public static void a(String str, boolean z) {
        if (e.E.A != null) {
            e.E.A.edit().putBoolean(str, z).apply();
        }
    }

    public static String b(String str, String str2) {
        if (e.E.A != null) {
            return e.E.A.getString(str, str2);
        }
        return str2;
    }

    public static boolean b(String str, boolean z) {
        if (e.E.A != null) {
            return e.E.A.getBoolean(str, z);
        }
        return z;
    }

    public static String a(Context context, String str) {
        String str2 = null;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (!(applicationInfo == null || applicationInfo.metaData == null)) {
                Object obj = applicationInfo.metaData.get(str);
                if (obj != null) {
                    str2 = String.valueOf(obj);
                }
            }
        } catch (Exception e) {
            an.c(a.class, "getManifestMetaDataValue exception:" + e.getMessage(), new Object[0]);
        }
        return str2;
    }
}
