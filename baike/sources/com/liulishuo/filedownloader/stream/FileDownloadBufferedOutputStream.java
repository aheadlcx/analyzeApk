package com.liulishuo.filedownloader.stream;

import com.liulishuo.filedownloader.util.FileDownloadHelper.OutputStreamCreator;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileDownloadBufferedOutputStream implements FileDownloadOutputStream {
    private final BufferedOutputStream a;

    public static class Creator implements OutputStreamCreator {
        public FileDownloadOutputStream create(File file) throws FileNotFoundException {
            return new FileDownloadBufferedOutputStream(file);
        }

        public boolean supportSeek() {
            return false;
        }
    }

    FileDownloadBufferedOutputStream(File file) throws FileNotFoundException {
        this.a = new BufferedOutputStream(new FileOutputStream(file, true));
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.a.write(bArr, i, i2);
    }

    public void sync() throws IOException {
        this.a.flush();
    }

    public void close() throws IOException {
        this.a.close();
    }

    public void seek(long j) throws IOException, IllegalAccessException {
        throw new IllegalAccessException("Can't support 'seek' in BufferedOutputStream.");
    }

    public void setLength(long j) throws IOException, IllegalAccessException {
        throw new IllegalAccessException("Can't support 'setLength' in BufferedOutputStream.");
    }
}
