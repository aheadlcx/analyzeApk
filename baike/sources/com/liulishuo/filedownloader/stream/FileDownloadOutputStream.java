package com.liulishuo.filedownloader.stream;

import java.io.IOException;

public interface FileDownloadOutputStream {
    void close() throws IOException;

    void seek(long j) throws IOException, IllegalAccessException;

    void setLength(long j) throws IOException, IllegalAccessException;

    void sync() throws IOException;

    void write(byte[] bArr, int i, int i2) throws IOException;
}
