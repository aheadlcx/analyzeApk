package com.meizu.cloud.pushsdk.base;

import java.io.IOException;

interface ILogWriter {
    void close() throws IOException;

    void open(String str) throws IOException;

    void write(String str, String str2, String str3) throws IOException;
}
