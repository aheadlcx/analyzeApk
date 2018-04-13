package qsbk.app.utils.image.issue;

import android.text.TextUtils;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import qsbk.app.utils.DeviceUtils;

public class Logger {
    public static final String LOG_DIRECTORY = "log";
    public static final String LOG_SUFIXX = ".log";
    public static final String PACKAGE_NAME = "qsbk.app";
    private static final TaskExecutor a = TaskExecutor.getInstance();
    private static String b;
    private static Logger c = null;
    private SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat e = new SimpleDateFormat("HH:mm:ss.SSS");
    private boolean f = false;

    private Logger() {
        b = new StringBuffer(this.d.format(new Date())).append(".log").toString();
    }

    public static synchronized Logger getInstance() {
        Logger logger;
        synchronized (Logger.class) {
            if (c == null) {
                c = new Logger();
            }
            logger = c;
        }
        return logger;
    }

    public static void closeSilently(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }

    public void debug(String str, String str2, String str3) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str3) && this.f) {
            Object sDPath = DeviceUtils.getSDPath();
            if (!TextUtils.isEmpty(sDPath)) {
                File file = new File(new StringBuffer(sDPath).append(File.separator).append("qsbk.app").append(File.separator).append("log").toString());
                if (!file.exists()) {
                    file.mkdirs();
                }
                File file2 = new File(file, b);
                Date date = new Date();
                writeFileAsync(file2, String.format("Time:%s, Tag:%s, Function:%s, Text:%s", new Object[]{this.e.format(date), str, str2, str3}) + "\n\n", true);
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
        a.addTask(new b(this, file, str, z));
    }

    public void setDebug(boolean z) {
        this.f = z;
    }
}
