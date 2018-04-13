package cn.htjyb.c;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;
import android.content.Context;
import android.media.AudioManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.PowerManager;
import android.os.Process;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class a {
    public static AudioManager a;

    public static boolean a(int i) {
        return VERSION.SDK_INT >= i;
    }

    public static String a(Context context) {
        String b = b(context);
        String c = c(context);
        if (TextUtils.isEmpty(c)) {
            c = b;
        } else if (!(TextUtils.isEmpty(b) || b.equals("0"))) {
            if (c.length() > 8) {
                c = c.substring(0, 8);
            }
            c = b + "_" + c;
        }
        return (TextUtils.isEmpty(c) || c.length() < 8) ? b() : c;
    }

    private static String b() {
        UUID randomUUID = UUID.randomUUID();
        if (randomUUID != null) {
            return randomUUID.toString();
        }
        return "unknow-" + new Random().nextInt();
    }

    public static String b(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager == null) {
                return null;
            }
            return telephonyManager.getDeviceId();
        } catch (Exception e) {
            return null;
        }
    }

    public static String c(Context context) {
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (wifiManager == null) {
                return null;
            }
            WifiInfo connectionInfo = wifiManager.getConnectionInfo();
            if (connectionInfo == null) {
                return null;
            }
            return connectionInfo.getMacAddress();
        } catch (Exception e) {
            return null;
        }
    }

    public static int a(float f, Context context) {
        return (int) (context.getResources().getDisplayMetrics().density * f);
    }

    public static int b(float f, Context context) {
        return (int) (context.getResources().getDisplayMetrics().scaledDensity * f);
    }

    public static int d(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        return i > i2 ? i2 : i;
    }

    public static int e(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        return i < i2 ? i2 : i;
    }

    public static float f(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    public static int g(Context context) {
        return context.getResources().getDisplayMetrics().densityDpi;
    }

    public static void a(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService("input_method");
        View currentFocus = activity.getCurrentFocus();
        if (inputMethodManager != null && currentFocus != null) {
            inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
    }

    public static void a(Context context, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void a(View view, Context context) {
        view.requestFocus();
        view.setEnabled(true);
        ((InputMethodManager) context.getSystemService("input_method")).showSoftInput(view, 1);
    }

    public static void a(View view) {
        view.requestFocus();
        view.setEnabled(true);
        ((InputMethodManager) view.getContext().getSystemService("input_method")).toggleSoftInput(2, 1);
    }

    public static void a(View... viewArr) {
        InputMethodManager inputMethodManager = null;
        if (viewArr != null && viewArr.length != 0) {
            for (View view : viewArr) {
                if (view != null && view.hasFocus()) {
                    view.requestFocus();
                    view.setEnabled(true);
                    if (inputMethodManager == null) {
                        inputMethodManager = (InputMethodManager) view.getContext().getSystemService("input_method");
                    }
                    inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        }
    }

    public static boolean a(Application application) {
        ActivityManager activityManager = (ActivityManager) application.getSystemService("activity");
        if (activityManager == null) {
            return false;
        }
        List runningAppProcesses = activityManager.getRunningAppProcesses();
        int myPid = Process.myPid();
        String packageName = application.getPackageName();
        if (runningAppProcesses == null) {
            return false;
        }
        for (int i = 0; i < runningAppProcesses.size(); i++) {
            if (((RunningAppProcessInfo) runningAppProcesses.get(i)).pid == myPid) {
                String str = ((RunningAppProcessInfo) runningAppProcesses.get(i)).processName;
                if (str == null || !str.equalsIgnoreCase(packageName)) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    public static boolean h(Context context) {
        return context.getResources().getConfiguration().orientation == 1;
    }

    public static boolean a() {
        String str = Build.CPU_ABI;
        String str2 = "none";
        if (VERSION.SDK_INT >= 8) {
            try {
                str2 = (String) Build.class.getDeclaredField("CPU_ABI2").get(null);
            } catch (Exception e) {
                return false;
            }
        }
        if (str.toLowerCase().contains("armeabi") || r0.toLowerCase().contains("armeabi")) {
            return true;
        }
        try {
            Reader fileReader = new FileReader("/proc/cpuinfo");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String readLine;
            do {
                readLine = bufferedReader.readLine();
                if (readLine == null) {
                    fileReader.close();
                    return false;
                }
            } while (!readLine.toLowerCase().contains("arm"));
            return true;
        } catch (IOException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean i(Context context) {
        boolean z = true;
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        if (powerManager == null) {
            return false;
        }
        if (VERSION.SDK_INT >= 20) {
            return !powerManager.isInteractive();
        } else {
            if (powerManager.isScreenOn()) {
                z = false;
            }
            return z;
        }
    }

    public static int j(Context context) {
        if (a == null) {
            a = (AudioManager) context.getSystemService("audio");
        }
        return a.getStreamMaxVolume(3);
    }

    public static int k(Context context) {
        if (a == null) {
            a = (AudioManager) context.getSystemService("audio");
        }
        return a.getStreamVolume(3);
    }

    public static void a(Context context, int i) {
        if (a == null) {
            a = (AudioManager) context.getSystemService("audio");
        }
        a.setStreamVolume(3, i, 0);
    }
}
