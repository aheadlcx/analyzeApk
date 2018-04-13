package org.eclipse.paho.client.mqttv3.internal;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileLock {
    private File a;
    private RandomAccessFile b;
    private Object c;

    public FileLock(File file, String str) throws Exception {
        this.a = new File(file, str);
        if (ExceptionHelper.isClassAvailable("java.nio.channels.FileLock")) {
            try {
                this.b = new RandomAccessFile(this.a, "rw");
                Object invoke = this.b.getClass().getMethod("getChannel", new Class[0]).invoke(this.b, new Object[0]);
                this.c = invoke.getClass().getMethod("tryLock", new Class[0]).invoke(invoke, new Object[0]);
            } catch (NoSuchMethodException e) {
                this.c = null;
            } catch (IllegalArgumentException e2) {
                this.c = null;
            } catch (IllegalAccessException e3) {
                this.c = null;
            }
            if (this.c == null) {
                release();
                throw new Exception("Problem obtaining file lock");
            }
        }
    }

    public void release() {
        try {
            if (this.c != null) {
                this.c.getClass().getMethod("release", new Class[0]).invoke(this.c, new Object[0]);
                this.c = null;
            }
        } catch (Exception e) {
        }
        if (this.b != null) {
            try {
                this.b.close();
            } catch (IOException e2) {
            }
            this.b = null;
        }
        if (this.a != null && this.a.exists()) {
            this.a.delete();
        }
        this.a = null;
    }
}
