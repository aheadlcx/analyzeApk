package com.liulishuo.filedownloader;

import android.app.Notification;
import android.os.Looper;
import java.io.File;

public class FileDownloadLine {

    static class a implements Runnable {
        private boolean a = false;
        private final b b;

        a(b bVar) {
            this.b = bVar;
        }

        public boolean isFinished() {
            return this.a;
        }

        public void run() {
            synchronized (this) {
                this.b.connected();
                this.a = true;
                notifyAll();
            }
        }
    }

    interface b {
        void connected();

        Object getValue();
    }

    public void startForeground(int i, Notification notification) {
        if (FileDownloader.getImpl().isServiceConnected()) {
            FileDownloader.getImpl().startForeground(i, notification);
        } else {
            a(new c(this, i, notification));
        }
    }

    public long getSoFar(int i) {
        if (FileDownloader.getImpl().isServiceConnected()) {
            return FileDownloader.getImpl().getSoFar(i);
        }
        b dVar = new d(this, i);
        a(dVar);
        return ((Long) dVar.getValue()).longValue();
    }

    public long getTotal(int i) {
        if (FileDownloader.getImpl().isServiceConnected()) {
            return FileDownloader.getImpl().getTotal(i);
        }
        b eVar = new e(this, i);
        a(eVar);
        return ((Long) eVar.getValue()).longValue();
    }

    public byte getStatus(int i, String str) {
        if (FileDownloader.getImpl().isServiceConnected()) {
            return FileDownloader.getImpl().getStatus(i, str);
        }
        if (str != null && new File(str).exists()) {
            return (byte) -3;
        }
        b fVar = new f(this, i, str);
        a(fVar);
        return ((Byte) fVar.getValue()).byteValue();
    }

    private void a(b bVar) {
        Object aVar = new a(bVar);
        synchronized (aVar) {
            FileDownloader.getImpl().bindService(aVar);
            if (!aVar.isFinished()) {
                if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                    throw new IllegalThreadStateException("Sorry, FileDownloader can not block the main thread, because the system is also  callbacks ServiceConnection#onServiceConnected method in the main thread.");
                }
                try {
                    aVar.wait(200000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
