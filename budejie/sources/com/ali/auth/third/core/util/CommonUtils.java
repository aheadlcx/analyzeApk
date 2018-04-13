package com.ali.auth.third.core.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Process;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.text.format.Formatter;
import android.util.Log;
import android.widget.Toast;
import com.ali.auth.third.core.broadcast.LoginAction;
import com.ali.auth.third.core.callback.FailureCallback;
import com.ali.auth.third.core.context.KernelContext;
import com.ali.auth.third.core.message.Message;
import com.ali.auth.third.core.model.ResultCode;
import com.ali.auth.third.core.task.InitWaitTask;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;

public class CommonUtils {
    private static String CURRENT_PROCESS_NAME;
    private static String PREFIX = "\\u";
    public static String mAppLabel;
    public static String mAppVersion;

    private static String char2Ascii(char c) {
        if (c <= 'ÿ') {
            return Character.toString(c);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(PREFIX);
        String toHexString = Integer.toHexString(c >> 8);
        if (toHexString.length() == 1) {
            stringBuilder.append("0");
        }
        stringBuilder.append(toHexString);
        toHexString = Integer.toHexString(c & 255);
        if (toHexString.length() == 1) {
            stringBuilder.append("0");
        }
        stringBuilder.append(toHexString);
        return stringBuilder.toString();
    }

    public static void onFailure(final FailureCallback failureCallback, final Message message) {
        KernelContext.executorService.postUITask(new Runnable() {
            public void run() {
                if (failureCallback != null) {
                    failureCallback.onFailure(message.code, message.message);
                }
            }
        });
    }

    public static void onFailure(final FailureCallback failureCallback, final ResultCode resultCode) {
        KernelContext.executorService.postUITask(new Runnable() {
            public void run() {
                if (failureCallback != null) {
                    failureCallback.onFailure(resultCode.code, resultCode.message);
                }
            }
        });
    }

    public static void onFailure(final FailureCallback failureCallback, final int i, final String str) {
        KernelContext.executorService.postUITask(new Runnable() {
            public void run() {
                if (failureCallback != null) {
                    failureCallback.onFailure(i, str);
                }
            }
        });
    }

    public static void toastSystemException() {
        toast("com_taobao_tae_sdk_system_exception");
    }

    public static void toast(final String str) {
        KernelContext.executorService.postUITask(new Runnable() {
            public void run() {
                Toast.makeText(KernelContext.getApplicationContext(), ResourceUtils.getString(str), 0).show();
            }
        });
    }

    public static boolean isNetworkAvailable() {
        if (KernelContext.context == null) {
            return true;
        }
        return isNetworkAvailable(KernelContext.context);
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return false;
        }
        NetworkInfo[] allNetworkInfo = connectivityManager.getAllNetworkInfo();
        if (allNetworkInfo == null) {
            return false;
        }
        boolean z;
        for (NetworkInfo networkInfo : allNetworkInfo) {
            if (networkInfo != null && (networkInfo.getState() == State.CONNECTED || networkInfo.getState() == State.CONNECTING)) {
                z = true;
                break;
            }
        }
        z = false;
        return z;
    }

    public static String getLocalIPAddress() {
        try {
            Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration inetAddresses = ((NetworkInterface) networkInterfaces.nextElement()).getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = (InetAddress) inetAddresses.nextElement();
                    if (!inetAddress.isLoopbackAddress() && (inetAddress instanceof Inet4Address)) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException e) {
            Log.e("Error", e.toString());
        }
        return null;
    }

    public static void startInitWaitTask(Activity activity, FailureCallback failureCallback, Runnable runnable, String str) {
        new InitWaitTask(activity, failureCallback, runnable, str).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public static String toString(Throwable th) {
        Writer stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    public static int isApplicationDefaultProcess() {
        if (KernelContext.context == null) {
            return -1;
        }
        String currentProcessName = getCurrentProcessName();
        if (currentProcessName != null) {
            return currentProcessName.equals(KernelContext.context.getPackageName()) ? 1 : 0;
        } else {
            return -1;
        }
    }

    public static String getCurrentProcessName() {
        if (KernelContext.context == null) {
            return null;
        }
        if (CURRENT_PROCESS_NAME != null) {
            return CURRENT_PROCESS_NAME;
        }
        try {
            List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) KernelContext.context.getSystemService("activity")).getRunningAppProcesses();
            if (runningAppProcesses == null) {
                return null;
            }
            int myPid = Process.myPid();
            for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.pid == myPid) {
                    CURRENT_PROCESS_NAME = runningAppProcessInfo.processName;
                    return runningAppProcessInfo.processName;
                }
            }
            return null;
        } catch (Exception e) {
        }
    }

    public static String getHashString(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bArr) {
            stringBuilder.append(Integer.toHexString((b >> 4) & 15));
            stringBuilder.append(Integer.toHexString(b & 15));
        }
        return stringBuilder.toString();
    }

    public static String getAvailMemory() {
        ActivityManager activityManager = (ActivityManager) KernelContext.getApplicationContext().getSystemService("activity");
        MemoryInfo memoryInfo = new MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return Formatter.formatFileSize(KernelContext.getApplicationContext(), memoryInfo.availMem);
    }

    public static String getTotalMemory() {
        long j = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
            String readLine = bufferedReader.readLine();
            String[] split = readLine.split("\\s+");
            for (String str : split) {
                Log.i(readLine, str + "\t");
            }
            j = (long) Integer.valueOf(split[1]).intValue();
            bufferedReader.close();
        } catch (IOException e) {
        }
        return j + " KB";
    }

    public static String getSDCardSize() {
        if (!"mounted".equals(Environment.getExternalStorageState())) {
            return "0";
        }
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return Formatter.formatFileSize(KernelContext.getApplicationContext(), ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize()));
    }

    public static String getSystemSize() {
        StatFs statFs = new StatFs(Environment.getRootDirectory().getPath());
        return Formatter.formatFileSize(KernelContext.getApplicationContext(), ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize()));
    }

    public static String getAndroidId() {
        return Secure.getString(KernelContext.getApplicationContext().getContentResolver(), "android_id");
    }

    public static void sendBroadcast(LoginAction loginAction) {
        Intent intent = new Intent();
        intent.setAction(loginAction.name());
        intent.setPackage(KernelContext.getApplicationContext().getPackageName());
        KernelContext.getApplicationContext().sendBroadcast(intent);
    }

    public static String getAppVersion() {
        if (mAppVersion == null) {
            try {
                PackageManager packageManager = KernelContext.getApplicationContext().getPackageManager();
                PackageInfo packageInfo = packageManager.getPackageInfo(KernelContext.getApplicationContext().getPackageName(), 0);
                if (packageInfo != null) {
                    mAppVersion = packageInfo.versionName;
                    mAppLabel = "" + packageManager.getApplicationLabel(packageInfo.applicationInfo);
                }
            } catch (NameNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return mAppVersion;
    }

    public static String getAppLabel() {
        if (mAppLabel == null) {
            try {
                PackageManager packageManager = KernelContext.getApplicationContext().getPackageManager();
                PackageInfo packageInfo = packageManager.getPackageInfo(KernelContext.getApplicationContext().getPackageName(), 0);
                if (packageInfo != null) {
                    mAppVersion = packageInfo.versionName;
                    mAppLabel = "" + packageManager.getApplicationLabel(packageInfo.applicationInfo);
                }
            } catch (Exception e) {
            } catch (Throwable th) {
            }
        }
        return mAppLabel;
    }

    public static String getAndroidAppVersion() {
        return "android_" + getAppVersion();
    }
}
