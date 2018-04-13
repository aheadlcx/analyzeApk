package com.liulishuo.filedownloader.util;

import com.tencent.bugly.Bugly;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileDownloadProperties {
    public final int DOWNLOAD_MAX_NETWORK_THREAD_COUNT;
    public final int DOWNLOAD_MIN_PROGRESS_STEP;
    public final long DOWNLOAD_MIN_PROGRESS_TIME;
    public final boolean FILE_NON_PRE_ALLOCATION;
    public final boolean HTTP_LENIENT;
    public final boolean PROCESS_NON_SEPARATE;

    public static class HolderClass {
        private static final FileDownloadProperties a = new FileDownloadProperties();
    }

    public static FileDownloadProperties getImpl() {
        return HolderClass.a;
    }

    private FileDownloadProperties() {
        if (FileDownloadHelper.getAppContext() == null) {
            throw new IllegalStateException("Please invoke the FileDownloader#init in Application#onCreate first.");
        }
        String property;
        long currentTimeMillis = System.currentTimeMillis();
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            inputStream = FileDownloadHelper.getAppContext().getAssets().open("filedownloader.properties");
            if (inputStream != null) {
                properties.load(inputStream);
                str = properties.getProperty("http.lenient");
                str2 = properties.getProperty("process.non-separate");
                str3 = properties.getProperty("download.min-progress-step");
                str4 = properties.getProperty("download.min-progress-time");
                str5 = properties.getProperty("download.max-network-thread-count");
                property = properties.getProperty("file.non-pre-allocation");
            } else {
                property = null;
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e2) {
            if (!(e2 instanceof FileNotFoundException)) {
                e2.printStackTrace();
            } else if (FileDownloadLog.NEED_LOG) {
                FileDownloadLog.d(FileDownloadProperties.class, "not found filedownloader.properties", new Object[0]);
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                    property = null;
                } catch (IOException e22) {
                    e22.printStackTrace();
                    property = null;
                }
            } else {
                property = null;
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
        }
        if (str == null) {
            this.HTTP_LENIENT = false;
        } else if (str.equals("true") || str.equals(Bugly.SDK_IS_DEV)) {
            this.HTTP_LENIENT = str.equals("true");
        } else {
            throw new IllegalStateException(FileDownloadUtils.formatString("the value of '%s' must be '%s' or '%s'", "http.lenient", "true", Bugly.SDK_IS_DEV));
        }
        if (str2 == null) {
            this.PROCESS_NON_SEPARATE = false;
        } else if (str2.equals("true") || str2.equals(Bugly.SDK_IS_DEV)) {
            this.PROCESS_NON_SEPARATE = str2.equals("true");
        } else {
            throw new IllegalStateException(FileDownloadUtils.formatString("the value of '%s' must be '%s' or '%s'", "process.non-separate", "true", Bugly.SDK_IS_DEV));
        }
        if (str3 != null) {
            this.DOWNLOAD_MIN_PROGRESS_STEP = Math.max(0, Integer.valueOf(str3).intValue());
        } else {
            this.DOWNLOAD_MIN_PROGRESS_STEP = 65536;
        }
        if (str4 != null) {
            this.DOWNLOAD_MIN_PROGRESS_TIME = Math.max(0, Long.valueOf(str4).longValue());
        } else {
            this.DOWNLOAD_MIN_PROGRESS_TIME = 2000;
        }
        if (str5 != null) {
            this.DOWNLOAD_MAX_NETWORK_THREAD_COUNT = getValidNetworkThreadCount(Integer.valueOf(str5).intValue());
        } else {
            this.DOWNLOAD_MAX_NETWORK_THREAD_COUNT = 3;
        }
        if (property == null) {
            this.FILE_NON_PRE_ALLOCATION = false;
        } else if (property.equals("true") || property.equals(Bugly.SDK_IS_DEV)) {
            this.FILE_NON_PRE_ALLOCATION = property.equals("true");
        } else {
            throw new IllegalStateException(FileDownloadUtils.formatString("the value of '%s' must be '%s' or '%s'", "file.non-pre-allocation", "true", Bugly.SDK_IS_DEV));
        }
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.i(FileDownloadProperties.class, "init properties %d\n load properties: %s=%B; %s=%B; %s=%d; %s=%d; %s=%d", Long.valueOf(System.currentTimeMillis() - currentTimeMillis), "http.lenient", Boolean.valueOf(this.HTTP_LENIENT), "process.non-separate", Boolean.valueOf(this.PROCESS_NON_SEPARATE), "download.min-progress-step", Integer.valueOf(this.DOWNLOAD_MIN_PROGRESS_STEP), "download.min-progress-time", Long.valueOf(this.DOWNLOAD_MIN_PROGRESS_TIME), "download.max-network-thread-count", Integer.valueOf(this.DOWNLOAD_MAX_NETWORK_THREAD_COUNT));
        }
    }

    public static int getValidNetworkThreadCount(int i) {
        if (i > 12) {
            FileDownloadLog.w(FileDownloadProperties.class, "require the count of network thread  is %d, what is more than the max valid count(%d), so adjust to %d auto", Integer.valueOf(i), Integer.valueOf(12), Integer.valueOf(12));
            return 12;
        } else if (i >= 1) {
            return i;
        } else {
            FileDownloadLog.w(FileDownloadProperties.class, "require the count of network thread  is %d, what is less than the min valid count(%d), so adjust to %d auto", Integer.valueOf(i), Integer.valueOf(1), Integer.valueOf(1));
            return 1;
        }
    }
}
