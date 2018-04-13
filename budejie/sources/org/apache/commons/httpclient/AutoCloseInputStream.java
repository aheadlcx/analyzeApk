package org.apache.commons.httpclient;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

class AutoCloseInputStream extends FilterInputStream {
    private boolean selfClosed = false;
    private boolean streamOpen = true;
    private ResponseConsumedWatcher watcher = null;

    public AutoCloseInputStream(InputStream inputStream, ResponseConsumedWatcher responseConsumedWatcher) {
        super(inputStream);
        this.watcher = responseConsumedWatcher;
    }

    public int read() throws IOException {
        if (!isReadAllowed()) {
            return -1;
        }
        int read = super.read();
        checkClose(read);
        return read;
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (!isReadAllowed()) {
            return -1;
        }
        int read = super.read(bArr, i, i2);
        checkClose(read);
        return read;
    }

    public int read(byte[] bArr) throws IOException {
        if (!isReadAllowed()) {
            return -1;
        }
        int read = super.read(bArr);
        checkClose(read);
        return read;
    }

    public void close() throws IOException {
        if (!this.selfClosed) {
            this.selfClosed = true;
            notifyWatcher();
        }
    }

    private void checkClose(int i) throws IOException {
        if (i == -1) {
            notifyWatcher();
        }
    }

    private boolean isReadAllowed() throws IOException {
        if (this.streamOpen || !this.selfClosed) {
            return this.streamOpen;
        }
        throw new IOException("Attempted read on closed stream.");
    }

    private void notifyWatcher() throws IOException {
        if (this.streamOpen) {
            super.close();
            this.streamOpen = false;
            if (this.watcher != null) {
                this.watcher.responseConsumed();
            }
        }
    }
}
