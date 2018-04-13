package cn.tatagou.sdk.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.GradientDrawable;
import android.net.ConnectivityManager;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.Settings.System;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import cn.tatagou.sdk.b.a;
import cn.tatagou.sdk.pojo.Special;
import com.alibaba.fastjson.JSON;
import com.baidu.mobads.interfaces.utils.IXAdIOUtils;
import com.bumptech.glide.i;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

public class p {
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }

    public static String getString(Context context, int i) {
        return context == null ? "" : context.getResources().getString(i);
    }

    public static <T> T json2Obj(String str, Class<T> cls) {
        return str != null ? JSON.parseObject(str, (Class) cls) : null;
    }

    public static int[] getWindowWidthAndHeight(Context context) {
        int[] iArr = new int[2];
        try {
            WindowManager windowManager = (WindowManager) context.getSystemService("window");
            DisplayMetrics displayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            iArr[0] = displayMetrics.widthPixels;
            iArr[1] = displayMetrics.heightPixels;
        } catch (Throwable e) {
            Log.e("TTG", "getWindowWidthAndHeight: " + e.getMessage(), e);
        }
        return iArr;
    }

    public static int getWindowWidth(Context context) {
        int i;
        if (context != null) {
            try {
                WindowManager windowManager = (WindowManager) context.getSystemService("window");
                if (!(windowManager == null || windowManager.getDefaultDisplay() == null)) {
                    DisplayMetrics displayMetrics = new DisplayMetrics();
                    windowManager.getDefaultDisplay().getMetrics(displayMetrics);
                    i = displayMetrics.widthPixels;
                    return i;
                }
            } catch (Throwable e) {
                Log.e("TTG", "getWindowWidth: " + e.getMessage(), e);
                return -1;
            }
        }
        i = -1;
        return i;
    }

    public static void showNetImg(Fragment fragment, String str, ImageView imageView) {
        if (fragment != null) {
            i.a(fragment).a(onImgUrlJpg(str)).a(DiskCacheStrategy.SOURCE).b().c().a(imageView);
        }
    }

    public static void showNetImg(Activity activity, String str, ImageView imageView) {
        if (activity != null) {
            i.a(activity).a(onImgUrlJpg(str)).a(DiskCacheStrategy.SOURCE).b().c().a(imageView);
        }
    }

    public static String onImgUrlJpg(String str) {
        if (isEmpty(str)) {
            return null;
        }
        try {
            if (VERSION.SDK_INT >= 18 || !str.contains("image/format,webp")) {
                return str;
            }
            if (str.contains(".png") || str.contains(".gif")) {
                return str.substring(0, str.indexOf("?"));
            }
            return str;
        } catch (Exception e) {
            return str;
        }
    }

    public static void onLoadBannerImage(Activity activity, ImageView imageView, Special special, int i) {
        if (activity != null) {
            i.a(activity).a(onImgUrlJpg(special.getCoverImg())).j().a(DiskCacheStrategy.SOURCE).b().a(new p$1(imageView, special, i));
        }
    }

    public static boolean isNetworkOpen(Context context) {
        if (context != null) {
            try {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                if (!(connectivityManager == null || connectivityManager.getActiveNetworkInfo() == null)) {
                    return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isAvailable();
                }
            } catch (Exception e) {
            }
        }
        return false;
    }

    public static int getAppVersionCode(Context context) {
        int i = 0;
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return i;
        }
    }

    public static String getAppVersionName(Context context) {
        if (context == null) {
            return "0";
        }
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return "0";
        }
    }

    public static String deleteDir(Context context, String str) {
        File file = new File(str);
        if (file == null || !file.exists() || !file.isDirectory()) {
            return "暂无内容清空";
        }
        if (file.listFiles().length <= 0) {
            return "暂无内容清空";
        }
        for (File file2 : file.listFiles()) {
            if (file2.isFile()) {
                file2.delete();
            } else if (file2.isDirectory()) {
                deleteDir(context, file2.getPath());
            }
        }
        return "成功清理缓存";
    }

    public static String phoneImei(Context context) {
        String deviceId;
        try {
            deviceId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
            if (Long.parseLong(deviceId) == 0) {
                deviceId = null;
            }
        } catch (Exception e) {
            deviceId = getAndroidID(context);
        }
        if (!isEmpty(deviceId)) {
            return deviceId;
        }
        deviceId = a.getStr("uuid");
        if (!isEmpty(deviceId)) {
            return deviceId;
        }
        deviceId = UUID.randomUUID().toString();
        if (isEmpty(deviceId)) {
            return "0";
        }
        deviceId = deviceId.replace("-", "");
        a.saveStr("uuid", deviceId);
        return deviceId;
    }

    public static String getAndroidID(Context context) {
        try {
            return System.getString(context.getContentResolver(), "android_id");
        } catch (Exception e) {
            return "";
        }
    }

    public static boolean hideKeyboard(Activity activity, View view, InputMethodManager inputMethodManager, View view2) {
        try {
            if (inputMethodManager.isActive(view2) && activity.getCurrentFocus() != null) {
                view.requestFocus();
                inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 2);
                return true;
            }
        } catch (Throwable e) {
            Log.e("TTG", "hideKeyboard: " + e.getMessage(), e);
        }
        return false;
    }

    public static int dip2px(Context context, float f) {
        try {
            return (int) ((context.getResources().getDisplayMetrics().density * f) + 0.5f);
        } catch (Exception e) {
            return (int) f;
        }
    }

    public static int px2dip(Context context, int i) {
        return (int) ((((float) i) / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static String canPath(Context context, String str) {
        try {
            if (!Environment.getExternalStorageState().equals("mounted")) {
                return IXAdIOUtils.DEFAULT_SD_CARD_PATH + str;
            }
            if (new File(Environment.getExternalStorageDirectory().getPath()).canWrite()) {
                return Environment.getExternalStorageDirectory().getPath() + str;
            }
            if (isEmpty(context.getExternalFilesDir(null).getPath())) {
                return null;
            }
            return context.getExternalFilesDir(null).getPath() + str;
        } catch (Throwable e) {
            Log.e("utilcanPath ", "util canPath Exception: ", e);
            return null;
        }
    }

    public static Bitmap readBitMap(Context context, int i) {
        Bitmap bitmap = null;
        try {
            Options options = new Options();
            options.inPreferredConfig = Config.RGB_565;
            options.inJustDecodeBounds = false;
            options.inSampleSize = 2;
            options.inPurgeable = true;
            options.inInputShareable = true;
            bitmap = BitmapFactory.decodeStream(context.getResources().openRawResource(i), null, options);
        } catch (Exception e) {
        }
        return bitmap;
    }

    public static String inputStream2String(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        byte[] bArr = new byte[4096];
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                return stringBuffer.toString();
            }
            stringBuffer.append(new String(bArr, 0, read));
        }
    }

    public static GradientDrawable getShapeDrawable(int i, int i2, int i3, int i4, int i5) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(i);
        gradientDrawable.setCornerRadius((float) i2);
        gradientDrawable.setColor(i3);
        gradientDrawable.setStroke(i4, i5);
        return gradientDrawable;
    }

    public static GradientDrawable getCircularDrawable(int i, int i2, int i3) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(1);
        gradientDrawable.setUseLevel(false);
        gradientDrawable.setSize(1, 1);
        gradientDrawable.setColor(i);
        gradientDrawable.setStroke(i2, i3);
        return gradientDrawable;
    }

    public static float str2Float(String str) {
        float f = 0.0f;
        if (str != null) {
            try {
                f = Float.parseFloat(str);
            } catch (Throwable e) {
                Log.d("TTG", "Str2Float: " + e.getMessage(), e);
            }
        }
        return f;
    }

    public static int str2Int(String str) {
        int i = 0;
        if (str != null) {
            try {
                i = Integer.parseInt(str);
            } catch (Throwable e) {
                Log.d("TTG", "str2Int: " + e.getMessage(), e);
            }
        }
        return i;
    }

    public static long str2Long(String str) {
        long j = 0;
        if (str != null) {
            try {
                j = Long.parseLong(str);
            } catch (Throwable e) {
                Log.d("TTG", "str2Long: " + e.getMessage(), e);
            }
        }
        return j;
    }

    public static Bitmap getStorePic(Context context, String str) {
        FileInputStream openFileInput;
        Throwable th;
        Bitmap bitmap = null;
        if (!TextUtils.isEmpty(str)) {
            try {
                openFileInput = context.openFileInput(str);
                try {
                    bitmap = BitmapFactory.decodeStream(openFileInput);
                    if (openFileInput != null) {
                        try {
                            openFileInput.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (FileNotFoundException e2) {
                    if (openFileInput != null) {
                        try {
                            openFileInput.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    return bitmap;
                } catch (Throwable th2) {
                    th = th2;
                    if (openFileInput != null) {
                        try {
                            openFileInput.close();
                        } catch (IOException e32) {
                            e32.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (FileNotFoundException e4) {
                openFileInput = bitmap;
                if (openFileInput != null) {
                    openFileInput.close();
                }
                return bitmap;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                openFileInput = bitmap;
                th = th4;
                if (openFileInput != null) {
                    openFileInput.close();
                }
                throw th;
            }
        }
        return bitmap;
    }

    public static boolean checkPackage(Context context, String str) {
        if (str == null || "".equals(str)) {
            return false;
        }
        try {
            context.getPackageManager().getApplicationInfo(str, 8192);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }
}
