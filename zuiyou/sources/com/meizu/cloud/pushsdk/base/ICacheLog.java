package com.meizu.cloud.pushsdk.base;

public interface ICacheLog {
    void d(String str, String str2);

    void e(String str, String str2);

    void e(String str, String str2, Throwable th);

    void flush(boolean z);

    void i(String str, String str2);

    boolean isDebugMode();

    void setCacheCount(int i);

    void setCacheDuration(long j);

    void setDebugMode(boolean z);

    void setFilePath(String str);

    void w(String str, String str2);
}
