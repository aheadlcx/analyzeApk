package qsbk.app.core.utils;

import android.text.TextUtils;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    public static final String LOG_DIRECTORY = "log";
    public static final String LOG_SUFIXX = ".log";
    public static final String PACKAGE_NAME = "qsbk.app";
    private static String d;
    private static final TaskExecutor e = TaskExecutor.getInstance();
    private static Logger f = null;
    private SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat b = new SimpleDateFormat("HH:mm:ss.SSS");
    private boolean c = false;

    private Logger() {
        d = new StringBuffer(this.a.format(new Date())).append(".log").toString();
    }

    public static synchronized Logger getInstance() {
        Logger logger;
        synchronized (Logger.class) {
            if (f == null) {
                f = new Logger();
            }
            logger = f;
        }
        return logger;
    }

    public void debug(String str, String str2, String str3) {
        LogUtils.d(str, str2 + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + str3 + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str3) && this.c) {
            Object sDPath = DeviceUtils.getSDPath();
            if (!TextUtils.isEmpty(sDPath)) {
                File file = new File(new StringBuffer(sDPath).append(File.separator).append("qsbk.app").append(File.separator).append("log").toString());
                if (!file.exists()) {
                    file.mkdirs();
                }
                File file2 = new File(file, d);
                Date date = new Date();
                writeFileAsync(file2, String.format("Time:%s, Tag:%s, Function:%s, Text:%s", new Object[]{this.b.format(date), str, str2, str3}) + "\n\n", true);
            }
        }
    }

    public static void closeSilently(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }

    public void writeFile(File file, String str, boolean z) throws IOException {
        Closeable fileOutputStream;
        FileNotFoundException e;
        Throwable th;
        IOException e2;
        if (file != null && !TextUtils.isEmpty(str)) {
            try {
                fileOutputStream = new FileOutputStream(file, z);
                try {
                    fileOutputStream.write(str.getBytes());
                    fileOutputStream.flush();
                    closeSilently(fileOutputStream);
                } catch (FileNotFoundException e3) {
                    e = e3;
                    try {
                        e.printStackTrace();
                        throw e;
                    } catch (Throwable th2) {
                        th = th2;
                        closeSilently(fileOutputStream);
                        throw th;
                    }
                } catch (IOException e4) {
                    e2 = e4;
                    e2.printStackTrace();
                    throw e2;
                }
            } catch (FileNotFoundException e5) {
                e = e5;
                fileOutputStream = null;
                e.printStackTrace();
                throw e;
            } catch (IOException e6) {
                e2 = e6;
                fileOutputStream = null;
                e2.printStackTrace();
                throw e2;
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = null;
                closeSilently(fileOutputStream);
                throw th;
            }
        }
    }

    public void writeFileAsync(File file, String str, boolean z) {
        e.addTask(new p(this, file, str, z));
    }

    public void setDebug(boolean z) {
        this.c = z;
    }

    public boolean isDebug() {
        return this.c;
    }
}
