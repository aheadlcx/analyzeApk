package com.liulishuo.filedownloader;

import com.liulishuo.filedownloader.ITaskHunter.IStarter;
import com.liulishuo.filedownloader.message.MessageSnapshotFlow;
import com.liulishuo.filedownloader.util.FileDownloadExecutors;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

class m {
    private final b a = new b();

    private static class a {
        private static final m a = new m();

        static {
            MessageSnapshotFlow.getImpl().setReceiver(new MessageSnapshotGate());
        }
    }

    private static class b {
        private ThreadPoolExecutor a;
        private LinkedBlockingQueue<Runnable> b;

        public b() {
            a();
        }

        public void asyncExecute(IStarter iStarter) {
            this.a.execute(new c(iStarter));
        }

        public void expire(IStarter iStarter) {
            this.b.remove(iStarter);
        }

        public void expire(FileDownloadListener fileDownloadListener) {
            if (fileDownloadListener == null) {
                FileDownloadLog.w(this, "want to expire by listener, but the listener provided is null", new Object[0]);
                return;
            }
            List<Runnable> arrayList = new ArrayList();
            Iterator it = this.b.iterator();
            while (it.hasNext()) {
                Runnable runnable = (Runnable) it.next();
                c cVar = (c) runnable;
                if (cVar.isSameListener(fileDownloadListener)) {
                    cVar.expire();
                    arrayList.add(runnable);
                }
            }
            if (!arrayList.isEmpty()) {
                if (FileDownloadLog.NEED_LOG) {
                    FileDownloadLog.d(this, "expire %d tasks with listener[%s]", Integer.valueOf(arrayList.size()), fileDownloadListener);
                }
                for (Runnable runnable2 : arrayList) {
                    this.a.remove(runnable2);
                }
            }
        }

        public void expireAll() {
            if (FileDownloadLog.NEED_LOG) {
                FileDownloadLog.d(this, "expire %d tasks", Integer.valueOf(this.b.size()));
            }
            this.a.shutdownNow();
            a();
        }

        private void a() {
            this.b = new LinkedBlockingQueue();
            this.a = FileDownloadExecutors.newDefaultThreadPool(3, this.b, "LauncherTask");
        }
    }

    private static class c implements Runnable {
        private final IStarter a;
        private boolean b = false;

        c(IStarter iStarter) {
            this.a = iStarter;
        }

        public void run() {
            if (!this.b) {
                this.a.start();
            }
        }

        public boolean isSameListener(FileDownloadListener fileDownloadListener) {
            return this.a != null && this.a.equalListener(fileDownloadListener);
        }

        public boolean equals(Object obj) {
            return super.equals(obj) || obj == this.a;
        }

        public void expire() {
            this.b = true;
        }
    }

    m() {
    }

    public static m getImpl() {
        return a.a;
    }

    synchronized void a(IStarter iStarter) {
        this.a.asyncExecute(iStarter);
    }

    synchronized void a() {
        this.a.expireAll();
    }

    synchronized void b(IStarter iStarter) {
        this.a.expire(iStarter);
    }

    synchronized void a(FileDownloadListener fileDownloadListener) {
        this.a.expire(fileDownloadListener);
    }
}
