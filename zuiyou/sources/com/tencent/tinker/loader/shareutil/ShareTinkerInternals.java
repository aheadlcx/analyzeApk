package com.tencent.tinker.loader.shareutil;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Process;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ShareTinkerInternals {
    private static final String PATCH_PROCESS_NAME = ":patch";
    private static final String TAG = "Tinker.TinkerInternals";
    private static final boolean VM_IS_ART = isVmArt(System.getProperty("java.vm.version"));
    private static final boolean VM_IS_JIT = isVmJitInternal();
    private static String currentInstructionSet = null;
    private static Boolean isPatchProcess = null;
    private static String processName = null;
    private static String tinkerID = null;

    public static boolean isVmArt() {
        return VM_IS_ART || VERSION.SDK_INT >= 21;
    }

    public static boolean isVmJit() {
        return VM_IS_JIT && VERSION.SDK_INT < 24;
    }

    public static boolean isAfterAndroidO() {
        return VERSION.SDK_INT > 25;
    }

    public static String getCurrentInstructionSet() throws Exception {
        if (currentInstructionSet != null) {
            return currentInstructionSet;
        }
        currentInstructionSet = (String) Class.forName("dalvik.system.VMRuntime").getDeclaredMethod("getCurrentInstructionSet", new Class[0]).invoke(null, new Object[0]);
        Log.d(TAG, "getCurrentInstructionSet:" + currentInstructionSet);
        return currentInstructionSet;
    }

    public static boolean isSystemOTA(String str) {
        String str2 = Build.FINGERPRINT;
        if (str == null || str.equals("") || str2 == null || str2.equals("")) {
            Log.d(TAG, "fingerprint empty:" + str + ",current:" + str2);
            return false;
        } else if (str.equals(str2)) {
            Log.d(TAG, "same fingerprint:" + str2);
            return false;
        } else {
            Log.d(TAG, "system OTA,fingerprint not equal:" + str + "," + str2);
            return true;
        }
    }

    public static ShareDexDiffPatchInfo changeTestDexToClassN(ShareDexDiffPatchInfo shareDexDiffPatchInfo, int i) {
        if (!shareDexDiffPatchInfo.rawName.startsWith(ShareConstants.TEST_DEX_NAME)) {
            return null;
        }
        String str;
        if (i != 1) {
            str = "classes" + i + ShareConstants.DEX_SUFFIX;
        } else {
            str = "classes.dex";
        }
        return new ShareDexDiffPatchInfo(str, shareDexDiffPatchInfo.path, shareDexDiffPatchInfo.destMd5InDvm, shareDexDiffPatchInfo.destMd5InArt, shareDexDiffPatchInfo.dexDiffMd5, shareDexDiffPatchInfo.oldDexCrC, shareDexDiffPatchInfo.newDexCrC, shareDexDiffPatchInfo.dexMode);
    }

    public static boolean isNullOrNil(String str) {
        if (str == null || str.length() <= 0) {
            return true;
        }
        return false;
    }

    public static int checkTinkerPackage(Context context, int i, File file, ShareSecurityCheck shareSecurityCheck) {
        int checkSignatureAndTinkerID = checkSignatureAndTinkerID(context, file, shareSecurityCheck);
        if (checkSignatureAndTinkerID == 0) {
            return checkPackageAndTinkerFlag(shareSecurityCheck, i);
        }
        return checkSignatureAndTinkerID;
    }

    public static int checkSignatureAndTinkerID(Context context, File file, ShareSecurityCheck shareSecurityCheck) {
        if (!shareSecurityCheck.verifyPatchMetaSignature(file)) {
            return -1;
        }
        String manifestTinkerID = getManifestTinkerID(context);
        if (manifestTinkerID == null) {
            return -5;
        }
        HashMap packagePropertiesIfPresent = shareSecurityCheck.getPackagePropertiesIfPresent();
        if (packagePropertiesIfPresent == null) {
            return -2;
        }
        String str = (String) packagePropertiesIfPresent.get(ShareConstants.TINKER_ID);
        if (str == null) {
            return -6;
        }
        if (manifestTinkerID.equals(str)) {
            return 0;
        }
        Log.e(TAG, "tinkerId is not equal, base is " + manifestTinkerID + ", but patch is " + str);
        return -7;
    }

    public static int checkPackageAndTinkerFlag(ShareSecurityCheck shareSecurityCheck, int i) {
        if (isTinkerEnabledAll(i)) {
            return 0;
        }
        HashMap metaContentMap = shareSecurityCheck.getMetaContentMap();
        if (!isTinkerEnabledForDex(i) && metaContentMap.containsKey(ShareConstants.DEX_META_FILE)) {
            return -9;
        }
        if (!isTinkerEnabledForNativeLib(i) && metaContentMap.containsKey(ShareConstants.SO_META_FILE)) {
            return -9;
        }
        if (isTinkerEnabledForResource(i) || !metaContentMap.containsKey(ShareConstants.RES_META_FILE)) {
            return 0;
        }
        return -9;
    }

    public static Properties fastGetPatchPackageMeta(File file) {
        Closeable inputStream;
        Throwable th;
        IOException e;
        ZipFile zipFile;
        Throwable th2;
        if (file == null || !file.isFile() || file.length() == 0) {
            Log.e(TAG, "patchFile is illegal");
            return null;
        }
        try {
            ZipFile zipFile2 = new ZipFile(file);
            try {
                ZipEntry entry = zipFile2.getEntry(ShareConstants.PACKAGE_META_FILE);
                if (entry == null) {
                    Log.e(TAG, "patch meta entry not found");
                    SharePatchFileUtil.closeZip(zipFile2);
                    return null;
                }
                try {
                    inputStream = zipFile2.getInputStream(entry);
                    try {
                        Properties properties = new Properties();
                        properties.load(inputStream);
                        SharePatchFileUtil.closeQuietly(inputStream);
                        SharePatchFileUtil.closeZip(zipFile2);
                        return properties;
                    } catch (Throwable th3) {
                        th = th3;
                        SharePatchFileUtil.closeQuietly(inputStream);
                        throw th;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    inputStream = null;
                    SharePatchFileUtil.closeQuietly(inputStream);
                    throw th;
                }
            } catch (IOException e2) {
                e = e2;
                zipFile = zipFile2;
                try {
                    Log.e(TAG, "fastGetPatchPackageMeta exception:" + e.getMessage());
                    SharePatchFileUtil.closeZip(zipFile);
                    return null;
                } catch (Throwable th5) {
                    th2 = th5;
                    SharePatchFileUtil.closeZip(zipFile);
                    throw th2;
                }
            } catch (Throwable th6) {
                th2 = th6;
                zipFile = zipFile2;
                SharePatchFileUtil.closeZip(zipFile);
                throw th2;
            }
        } catch (IOException e3) {
            e = e3;
            zipFile = null;
            Log.e(TAG, "fastGetPatchPackageMeta exception:" + e.getMessage());
            SharePatchFileUtil.closeZip(zipFile);
            return null;
        } catch (Throwable th7) {
            zipFile = null;
            th2 = th7;
            SharePatchFileUtil.closeZip(zipFile);
            throw th2;
        }
    }

    public static String getManifestTinkerID(Context context) {
        if (tinkerID != null) {
            return tinkerID;
        }
        try {
            Object obj = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.get(ShareConstants.TINKER_ID);
            if (obj != null) {
                tinkerID = String.valueOf(obj);
            } else {
                tinkerID = null;
            }
            return tinkerID;
        } catch (Exception e) {
            Log.e(TAG, "getManifestTinkerID exception:" + e.getMessage());
            return null;
        }
    }

    public static boolean isTinkerEnabledForDex(int i) {
        return (i & 1) != 0;
    }

    public static boolean isTinkerEnabledForNativeLib(int i) {
        return (i & 2) != 0;
    }

    public static boolean isTinkerEnabledForResource(int i) {
        return (i & 4) != 0;
    }

    public static String getTypeString(int i) {
        switch (i) {
            case 1:
                return "patch_file";
            case 2:
                return "patch_info";
            case 3:
                return "dex";
            case 4:
                return "dex_opt";
            case 5:
                return ShareConstants.SO_PATH;
            case 6:
                return "resource";
            default:
                return "unknown";
        }
    }

    public static void setTinkerDisableWithSharedPreferences(Context context) {
        context.getSharedPreferences(ShareConstants.TINKER_SHARE_PREFERENCE_CONFIG, 4).edit().putBoolean(getTinkerSharedPreferencesName(), false).commit();
    }

    public static boolean isTinkerEnableWithSharedPreferences(Context context) {
        if (context == null) {
            return false;
        }
        return context.getSharedPreferences(ShareConstants.TINKER_SHARE_PREFERENCE_CONFIG, 4).getBoolean(getTinkerSharedPreferencesName(), true);
    }

    private static String getTinkerSharedPreferencesName() {
        return "tinker_enable_1.9.1";
    }

    public static boolean isTinkerEnabled(int i) {
        return i != 0;
    }

    public static boolean isTinkerEnabledAll(int i) {
        return i == 7;
    }

    public static boolean isInMainProcess(Context context) {
        String str = null;
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        if (applicationInfo != null) {
            str = applicationInfo.processName;
        }
        if (isNullOrNil(str)) {
            str = context.getPackageName();
        }
        Object processName = getProcessName(context);
        if (processName == null || processName.length() == 0) {
            processName = "";
        }
        return str.equals(processName);
    }

    public static boolean isInPatchProcess(Context context) {
        if (isPatchProcess != null) {
            return isPatchProcess.booleanValue();
        }
        isPatchProcess = Boolean.valueOf(getProcessName(context).endsWith(PATCH_PROCESS_NAME));
        return isPatchProcess.booleanValue();
    }

    public static String getCurrentOatMode(Context context, String str) {
        if (!str.equals(ShareConstants.CHANING_DEX_OPTIMIZE_PATH)) {
            return str;
        }
        if (isInMainProcess(context)) {
            return "odex";
        }
        return ShareConstants.INTERPRET_DEX_OPTIMIZE_PATH;
    }

    public static void killAllOtherProcess(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager != null) {
            List<RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
            if (runningAppProcesses != null) {
                for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                    if (runningAppProcessInfo.uid == Process.myUid() && runningAppProcessInfo.pid != Process.myPid()) {
                        Process.killProcess(runningAppProcessInfo.pid);
                    }
                }
            }
        }
    }

    public static String getProcessName(Context context) {
        if (processName != null) {
            return processName;
        }
        processName = getProcessNameInternal(context);
        return processName;
    }

    private static String getProcessNameInternal(Context context) {
        Throwable th;
        FileInputStream fileInputStream = null;
        int myPid = Process.myPid();
        if (context == null || myPid <= 0) {
            return "";
        }
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager != null) {
            List<RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
            if (runningAppProcesses != null) {
                try {
                    for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                        if (runningAppProcessInfo.pid == myPid) {
                            break;
                        }
                    }
                } catch (Exception e) {
                    Exception e2;
                    Log.e(TAG, "getProcessNameInternal exception:" + e2.getMessage());
                }
                RunningAppProcessInfo runningAppProcessInfo2 = null;
                if (runningAppProcessInfo2 != null) {
                    return runningAppProcessInfo2.processName;
                }
            }
        }
        byte[] bArr = new byte[128];
        try {
            FileInputStream fileInputStream2 = new FileInputStream("/proc/" + myPid + "/cmdline");
            try {
                int read = fileInputStream2.read(bArr);
                if (read > 0) {
                    int i = 0;
                    while (i < read) {
                        if (bArr[i] > Byte.MIN_VALUE || bArr[i] <= (byte) 0) {
                            read = i;
                            break;
                        }
                        i++;
                    }
                    String str = new String(bArr, 0, read);
                    if (fileInputStream2 == null) {
                        return str;
                    }
                    try {
                        fileInputStream2.close();
                        return str;
                    } catch (Exception e3) {
                        return str;
                    }
                }
                if (fileInputStream2 != null) {
                    try {
                        fileInputStream2.close();
                    } catch (Exception e4) {
                    }
                }
                return "";
            } catch (Exception e5) {
                e2 = e5;
                fileInputStream = fileInputStream2;
                try {
                    Log.e(TAG, "getProcessNameInternal exception:" + e2.getMessage());
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Exception e6) {
                        }
                    }
                    return "";
                } catch (Throwable th2) {
                    th = th2;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Exception e7) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                fileInputStream = fileInputStream2;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                throw th;
            }
        } catch (Exception e8) {
            e2 = e8;
            Log.e(TAG, "getProcessNameInternal exception:" + e2.getMessage());
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return "";
        }
    }

    private static boolean isVmArt(String str) {
        if (str == null) {
            return false;
        }
        Matcher matcher = Pattern.compile("(\\d+)\\.(\\d+)(\\.\\d+)?").matcher(str);
        if (!matcher.matches()) {
            return false;
        }
        try {
            int parseInt = Integer.parseInt(matcher.group(1));
            int parseInt2 = Integer.parseInt(matcher.group(2));
            if (parseInt > 2 || (parseInt == 2 && parseInt2 >= 1)) {
                return true;
            }
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isVmJitInternal() {
        try {
            Method declaredMethod = Class.forName("android.os.SystemProperties").getDeclaredMethod("get", new Class[]{String.class});
            String str = (String) declaredMethod.invoke(null, new Object[]{"dalvik.vm.usejit"});
            String str2 = (String) declaredMethod.invoke(null, new Object[]{"dalvik.vm.usejitprofiles"});
            if (!isNullOrNil(str) && isNullOrNil(str2) && str.equals("true")) {
                return true;
            }
        } catch (Throwable th) {
            Log.e(TAG, "isVmJitInternal ex:" + th);
        }
        return false;
    }

    public static String getExceptionCauseString(Throwable th) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        while (th.getCause() != null) {
            try {
                th = th.getCause();
            } finally {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                }
            }
        }
        th.printStackTrace(printStream);
        String toVisualString = toVisualString(byteArrayOutputStream.toString());
        return toVisualString;
    }

    public static String toVisualString(String str) {
        if (str == null) {
            return null;
        }
        char[] toCharArray = str.toCharArray();
        if (toCharArray == null) {
            return null;
        }
        char c;
        int i = 0;
        while (i < toCharArray.length) {
            if (toCharArray[i] > '') {
                toCharArray[i] = '\u0000';
                c = '\u0001';
                break;
            }
            i++;
        }
        c = '\u0000';
        if (c != '\u0000') {
            return new String(toCharArray, 0, i);
        }
        return str;
    }
}
