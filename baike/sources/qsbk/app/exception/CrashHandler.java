package qsbk.app.exception;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Looper;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.util.Log;
import com.baidu.mobstat.Config;
import com.crashlytics.android.Crashlytics;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.bugly.crashreport.CrashReport.UserStrategy;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.util.Properties;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.json.JSONObject;
import qsbk.app.ConfigManager;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.utils.FileUtils;
import qsbk.app.utils.SharePreferenceUtils;

public class CrashHandler implements UncaughtExceptionHandler {
    public static final boolean DEBUG = true;
    public static final String TAG = "CrashHandler";
    public static CrashHandler mInstance = new CrashHandler();
    private Context a;
    private Properties b = new Properties();

    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        return mInstance;
    }

    public void init(Context context) {
        this.a = context;
        Crashlytics.start(this.a);
        UserStrategy userStrategy = new UserStrategy(context);
        userStrategy.setAppChannel(ConfigManager.getInstance().getChannel());
        CrashReport.initCrashReport(context, "0fb431a2aedd4952a86c0582f31a18da", false, userStrategy);
        Crashlytics.setString(Config.PLATFORM_TYPE, Build.FINGERPRINT);
        Crashlytics.setString("uuid", DeviceUtils.getAndroidId());
        CrashReport.setUserId(DeviceUtils.getAndroidId());
        Thread.setDefaultUncaughtExceptionHandler(this);
        Thread.currentThread().setUncaughtExceptionHandler(this);
        a(context);
    }

    private void a(Context context) {
    }

    public void logException(Throwable th) {
        Crashlytics.logException(th);
        CrashReport.postCatchedException(th);
    }

    public void uncaughtException(Thread thread, Throwable th) {
        logException(th);
        a(thread, th, 0, false);
        if (thread.getName().equals("main")) {
            Log.e("crash-res", "FATAL EXCEPT, perhapse need to die, but now try to come back");
            int i = 1;
            while (i <= 10) {
                try {
                    Looper.loop();
                    return;
                } catch (Throwable th2) {
                    if (10 == i) {
                        Log.e("crash-close", "recover exception in main loop too many times, to die now");
                        a(thread, th2, i, true);
                        System.exit(1);
                    } else {
                        Log.e("crash-res", String.format("caught exception in main loop and try to recover: %s", new Object[]{th.toString()}));
                        a(thread, th2, i, false);
                    }
                    i++;
                }
            }
            return;
        }
        Log.e("crash-res", String.format("ignore exception outside of main thread: %s", new Object[]{th.toString()}));
    }

    private String a(Throwable th) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        th.printStackTrace(printStream);
        for (Throwable cause = th.getCause(); cause != null; cause = cause.getCause()) {
            cause.printStackTrace(printStream);
        }
        String byteArrayOutputStream2 = byteArrayOutputStream.toString();
        Log.e("crash-stack", byteArrayOutputStream2);
        return byteArrayOutputStream2;
    }

    private String a(Thread thread) {
        String name;
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        Thread[] threadArr = new Thread[140];
        printStream.printf("Crash occures in thread: %s[%d] and is processed in thread [%d]\n", new Object[]{thread.getName(), Long.valueOf(thread.getId()), Long.valueOf(Thread.currentThread().getId())});
        int enumerate = Thread.enumerate(threadArr);
        for (int i = 0; i < enumerate; i++) {
            ThreadGroup threadGroup = threadArr[i].getThreadGroup();
            name = threadGroup == null ? "null" : threadGroup.getName();
            printStream.printf("\tthread %d: %s[%d]@%s\n", new Object[]{Integer.valueOf(i), threadArr[i].getName(), Long.valueOf(threadArr[i].getId()), name});
        }
        name = byteArrayOutputStream.toString();
        Log.i("crash-runtime", name);
        return name;
    }

    private int a(Thread thread, Throwable th, int i, boolean z) {
        if (th == null) {
            return 0;
        }
        collectCrashDeviceInfo(this.a);
        int b = b(thread, th, i, z);
        b(this.a);
        return b;
    }

    public void reportGuessException(Thread thread, Throwable th, int i) {
        collectCrashDeviceInfo(this.a);
        a(thread, th, i, null);
        b(this.a);
    }

    public void reportGuessException(Thread thread, Throwable th, int i, String str) {
        collectCrashDeviceInfo(this.a);
        a(thread, th, i, str);
        b(this.a);
    }

    private void a(Thread thread, Throwable th, int i, String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("client", "android-qsbk-" + this.b.getProperty("versionName"));
            if (str == null) {
                jSONObject.put("stack", a(th));
            } else {
                jSONObject.put("stack", str + a(th));
            }
            jSONObject.put("runtime", a(thread));
            jSONObject.put("env", this.b.toString());
            Object sharePreferencesValue = SharePreferenceUtils.getSharePreferencesValue("appStartTime");
            if (TextUtils.isEmpty(sharePreferencesValue)) {
                jSONObject.put("life", 999999999);
            } else {
                jSONObject.put("life", System.currentTimeMillis() - Long.valueOf(sharePreferencesValue).longValue());
            }
            jSONObject.put("kind", i);
            FileOutputStream openFileOutput = this.a.openFileOutput("crash-" + System.currentTimeMillis() + ".cr.qbk", 1);
            openFileOutput.write(jSONObject.toString().getBytes());
            openFileOutput.flush();
            openFileOutput.close();
        } catch (Throwable e) {
            Log.e(TAG, "error occures when writing custom crash file", e);
        }
    }

    public void sendPreviousReportsToServer() {
        b(this.a);
    }

    private void b(Context context) {
        File[] c = c(context);
        if (c != null && c.length > 0) {
            for (File a : c) {
                a(a);
            }
        }
    }

    private void a(File file) {
        BufferedReader bufferedReader;
        Exception e;
        Throwable th;
        if (file.getName().endsWith(".cr")) {
            try {
                bufferedReader = new BufferedReader(new FileReader(file));
                try {
                    StringBuffer stringBuffer = new StringBuffer();
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        } else if (readLine.contains("STACK_TRACE")) {
                            stringBuffer.append(readLine + "\n");
                        }
                    }
                    Build.MODEL + MqttTopic.TOPIC_LEVEL_SEPARATOR + Build.PRODUCT + MqttTopic.TOPIC_LEVEL_SEPARATOR + VERSION.RELEASE;
                    file.delete();
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e2) {
                        }
                    }
                } catch (Exception e3) {
                    e = e3;
                    try {
                        e.printStackTrace();
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e4) {
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e5) {
                            }
                        }
                        throw th;
                    }
                }
            } catch (Exception e6) {
                e = e6;
                bufferedReader = null;
                e.printStackTrace();
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (Throwable th3) {
                th = th3;
                bufferedReader = null;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                throw th;
            }
        }
    }

    private File[] c(Context context) {
        File[] fileArr;
        int length;
        String str;
        String[] strArr;
        Object obj;
        File filesDir = context.getFilesDir();
        FilenameFilter aVar = new a(this);
        String[] list = filesDir.list(aVar);
        Object sDPath = DeviceUtils.getSDPath();
        if (list == null || list.length <= 0) {
            fileArr = null;
        } else {
            fileArr = new File[list.length];
            length = list.length;
            for (int i = 0; i < length; i++) {
                fileArr[i] = new File(context.getFilesDir(), list[i]);
            }
        }
        if (TextUtils.isEmpty(sDPath)) {
            str = null;
            strArr = null;
        } else {
            String str2 = sDPath + File.separator + this.a.getPackageName() + File.separator + "crash";
            File file = new File(str2);
            if (file.exists()) {
                String[] list2 = file.list(aVar);
                str = str2;
                strArr = list2;
            } else {
                str = str2;
                strArr = null;
            }
        }
        if (strArr == null || strArr.length <= 0) {
            obj = null;
        } else {
            obj = new File[strArr.length];
            length = strArr.length;
            for (int i2 = 0; i2 < length; i2++) {
                obj[i2] = new File(str + File.separator + strArr[i2]);
            }
        }
        if (fileArr != null && obj != null) {
            Object obj2 = new File[(fileArr.length + obj.length)];
            System.arraycopy(fileArr, 0, obj2, 0, fileArr.length);
            System.arraycopy(obj, 0, obj2, fileArr.length, obj.length);
            return obj2;
        } else if (fileArr != null && fileArr.length > 0) {
            return fileArr;
        } else {
            if (obj != null) {
                return obj;
            }
            return null;
        }
    }

    private int b(Thread thread, Throwable th, int i, boolean z) {
        int i2 = 1;
        Writer stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        for (Throwable cause = th.getCause(); cause != null; cause = cause.getCause()) {
            cause.printStackTrace(printWriter);
        }
        String obj = stringWriter.toString();
        printWriter.close();
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("client", "android-qsbk-" + this.b.getProperty("versionName"));
            String a = a(th);
            jSONObject.put("stack", a);
            jSONObject.put("runtime", a(thread));
            jSONObject.put("env", this.b.toString());
            Object sharePreferencesValue = SharePreferenceUtils.getSharePreferencesValue("appStartTime");
            if (TextUtils.isEmpty(sharePreferencesValue)) {
                jSONObject.put("life", 999999999);
            } else {
                jSONObject.put("life", System.currentTimeMillis() - Long.valueOf(sharePreferencesValue).longValue());
            }
            if (thread.getName().equals("main")) {
                if (a.indexOf("qsb") >= 0) {
                    i2 = i + 9;
                } else {
                    i2 = 8;
                }
            } else if (a.indexOf("qsb") >= 0) {
                i2 = 7;
            } else {
                i2 = 5;
            }
            jSONObject.put("kind", i2);
            a("crash-" + System.currentTimeMillis() + ".cr.qbk", jSONObject.toString());
        } catch (Throwable cause2) {
            Log.e(TAG, "error occures when writing custom crash file", cause2);
        }
        if (i2 >= 6 && z) {
            this.b.put("STACK_TRACE", obj);
            try {
                OutputStream openFileOutput = this.a.openFileOutput("crash-" + System.currentTimeMillis() + ".cr", 1);
                this.b.store(openFileOutput, "");
                openFileOutput.flush();
                openFileOutput.close();
            } catch (Throwable cause22) {
                Log.e(TAG, "an error occured while writing report file...", cause22);
            }
        }
        return i2;
    }

    public void collectCrashDeviceInfo(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 1);
            if (packageInfo != null) {
                Object obj;
                Properties properties = this.b;
                String str = "versionName";
                if (packageInfo.versionName == null) {
                    obj = "not set";
                } else {
                    obj = String.valueOf(packageInfo.versionName);
                }
                properties.put(str, obj);
                this.b.put("versionCode", String.valueOf(packageInfo.versionCode));
            }
        } catch (Throwable e) {
            Log.e(TAG, "Error while collect package info", e);
        }
        for (Field field : Build.class.getDeclaredFields()) {
            try {
                field.setAccessible(true);
                this.b.put(field.getName(), String.valueOf(field.get(null)));
                Log.d(TAG, field.getName() + " : " + field.get(null));
            } catch (Throwable e2) {
                Log.e(TAG, "Error while collect crash info", e2);
            }
        }
    }

    private void a(String str, String str2) throws IOException {
        Object sDPath = DeviceUtils.getSDPath();
        if (TextUtils.isEmpty(sDPath)) {
            FileOutputStream openFileOutput = this.a.openFileOutput(str, 1);
            openFileOutput.write(str2.getBytes());
            openFileOutput.flush();
            openFileOutput.close();
            return;
        }
        File file = new File(sDPath + File.separator + this.a.getPackageName() + File.separator + "crash");
        if (file.exists()) {
            FileUtils.removeOldFiles(file, new b(this), PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED);
        } else {
            file.mkdirs();
        }
        FileWriter fileWriter = new FileWriter(new File(file, str));
        fileWriter.write(str2);
        fileWriter.flush();
        fileWriter.close();
    }
}
