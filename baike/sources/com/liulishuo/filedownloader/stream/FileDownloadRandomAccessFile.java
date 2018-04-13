package com.liulishuo.filedownloader.stream;

import com.liulishuo.filedownloader.util.FileDownloadHelper.OutputStreamCreator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileDownloadRandomAccessFile implements FileDownloadOutputStream {
    private final RandomAccessFile a;

    public static class Creator implements OutputStreamCreator {
        public FileDownloadOutputStream create(File file) throws FileNotFoundException {
            return new FileDownloadRandomAccessFile(file);
        }

        public boolean supportSeek() {
            return true;
        }
    }

    FileDownloadRandomAccessFile(File file) throws FileNotFoundException {
        this.a = new RandomAccessFile(file, "rw");
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.a.write(bArr, i, i2);
    }

    public void sync() throws IOException {
        this.a.getFD().sync();
    }

    public void close() throws IOException {
        this.a.close();
    }

    public void seek(long j) throws IOException {
        this.a.seek(j);
    }

    public void setLength(long j) throws IOException {
        this.a.setLength(j);
    }
}
