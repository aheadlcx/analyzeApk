package com.liulishuo.filedownloader;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import android.util.SparseArray;
import com.liulishuo.filedownloader.BaseDownloadTask.FinishListener;
import com.liulishuo.filedownloader.BaseDownloadTask.IRunningTask;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import java.lang.ref.WeakReference;
import java.util.List;

class p implements IQueuesHandler {
    private final SparseArray<Handler> a = new SparseArray();

    private static class a implements FinishListener {
        private final WeakReference<b> a;
        private int b;

        private a(WeakReference<b> weakReference) {
            this.a = weakReference;
        }

        public FinishListener setNextIndex(int i) {
            this.b = i;
            return this;
        }

        public void over(BaseDownloadTask baseDownloadTask) {
            if (this.a != null && this.a.get() != null) {
                ((b) this.a.get()).a(this.b);
            }
        }
    }

    private class b implements Callback {
        final /* synthetic */ p a;
        private Handler b;
        private List<IRunningTask> c;
        private int d = 0;
        private a e = new a(new WeakReference(this));

        b(p pVar) {
            this.a = pVar;
        }

        public void setHandler(Handler handler) {
            this.b = handler;
        }

        public void setList(List<IRunningTask> list) {
            this.c = list;
        }

        public boolean handleMessage(Message message) {
            if (message.what == 1) {
                if (message.arg1 >= this.c.size()) {
                    synchronized (this.a.a) {
                        this.a.a.remove(((IRunningTask) this.c.get(0)).getAttachKey());
                    }
                    if (!(this.b == null || this.b.getLooper() == null)) {
                        this.b.getLooper().quit();
                        this.b = null;
                        this.c = null;
                        this.e = null;
                    }
                    if (FileDownloadLog.NEED_LOG) {
                        FileDownloadListener fileDownloadListener;
                        Class cls = b.class;
                        String str = "final serial %s %d";
                        Object[] objArr = new Object[2];
                        if (this.c == null) {
                            fileDownloadListener = null;
                        } else if (this.c.get(0) == null) {
                            fileDownloadListener = null;
                        } else {
                            fileDownloadListener = ((IRunningTask) this.c.get(0)).getOrigin().getListener();
                        }
                        objArr[0] = fileDownloadListener;
                        objArr[1] = Integer.valueOf(message.arg1);
                        FileDownloadLog.d(cls, str, objArr);
                    }
                } else {
                    this.d = message.arg1;
                    IRunningTask iRunningTask = (IRunningTask) this.c.get(this.d);
                    synchronized (iRunningTask.getPauseLock()) {
                        if (iRunningTask.getOrigin().getStatus() != (byte) 0 || FileDownloadList.getImpl().a(iRunningTask)) {
                            if (FileDownloadLog.NEED_LOG) {
                                FileDownloadLog.d(b.class, "direct go next by not contains %s %d", iRunningTask, Integer.valueOf(message.arg1));
                            }
                            a(message.arg1 + 1);
                        } else {
                            iRunningTask.getOrigin().addFinishListener(this.e.setNextIndex(this.d + 1));
                            iRunningTask.startTaskByQueue();
                        }
                    }
                }
            } else if (message.what == 2) {
                freeze();
            } else if (message.what == 3) {
                unfreeze();
            }
            return true;
        }

        public void freeze() {
            ((IRunningTask) this.c.get(this.d)).getOrigin().removeFinishListener(this.e);
            this.b.removeCallbacksAndMessages(null);
        }

        public void unfreeze() {
            a(this.d);
        }

        private void a(int i) {
            FileDownloadListener fileDownloadListener = null;
            if (this.b == null || this.c == null) {
                FileDownloadLog.w(this, "need go next %d, but params is not ready %s %s", Integer.valueOf(i), this.b, this.c);
                return;
            }
            Message obtainMessage = this.b.obtainMessage();
            obtainMessage.what = 1;
            obtainMessage.arg1 = i;
            if (FileDownloadLog.NEED_LOG) {
                Class cls = b.class;
                String str = "start next %s %s";
                Object[] objArr = new Object[2];
                if (!(this.c == null || this.c.get(0) == null)) {
                    fileDownloadListener = ((IRunningTask) this.c.get(0)).getOrigin().getListener();
                }
                objArr[0] = fileDownloadListener;
                objArr[1] = Integer.valueOf(obtainMessage.arg1);
                FileDownloadLog.d(cls, str, objArr);
            }
            this.b.sendMessage(obtainMessage);
        }
    }

    public boolean startQueueParallel(FileDownloadListener fileDownloadListener) {
        int hashCode = fileDownloadListener.hashCode();
        List<IRunningTask> a = FileDownloadList.getImpl().a(hashCode, fileDownloadListener);
        if (a(hashCode, a, fileDownloadListener, false)) {
            return false;
        }
        for (IRunningTask startTaskByQueue : a) {
            startTaskByQueue.startTaskByQueue();
        }
        return true;
    }

    public boolean startQueueSerial(FileDownloadListener fileDownloadListener) {
        b bVar = new b(this);
        int hashCode = bVar.hashCode();
        List a = FileDownloadList.getImpl().a(hashCode, fileDownloadListener);
        if (a(hashCode, a, fileDownloadListener, true)) {
            return false;
        }
        HandlerThread handlerThread = new HandlerThread(FileDownloadUtils.formatString("filedownloader serial thread %s-%d", fileDownloadListener, Integer.valueOf(hashCode)));
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper(), bVar);
        bVar.setHandler(handler);
        bVar.setList(a);
        bVar.a(0);
        synchronized (this.a) {
            this.a.put(hashCode, handler);
        }
        return true;
    }

    public void freezeAllSerialQueues() {
        for (int i = 0; i < this.a.size(); i++) {
            a((Handler) this.a.get(this.a.keyAt(i)));
        }
    }

    public void unFreezeSerialQueues(List<Integer> list) {
        for (Integer intValue : list) {
            b((Handler) this.a.get(intValue.intValue()));
        }
    }

    public int serialQueueSize() {
        return this.a.size();
    }

    public boolean contain(int i) {
        return this.a.get(i) != null;
    }

    private boolean a(int i, List<IRunningTask> list, FileDownloadListener fileDownloadListener, boolean z) {
        if (FileDownloadMonitor.isValid()) {
            FileDownloadMonitor.getMonitor().onRequestStart(list.size(), true, fileDownloadListener);
        }
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.v(FileDownloader.class, "start list attachKey[%d] size[%d] listener[%s] isSerial[%B]", Integer.valueOf(i), Integer.valueOf(list.size()), fileDownloadListener, Boolean.valueOf(z));
        }
        if (list != null && !list.isEmpty()) {
            return false;
        }
        FileDownloadLog.w(FileDownloader.class, "Tasks with the listener can't start, because can't find any task with the provided listener, maybe tasks instance has been started in the past, so they are all are inUsing, if in this case, you can use [BaseDownloadTask#reuse] to reuse theme first then start again: [%s, %B]", fileDownloadListener, Boolean.valueOf(z));
        return true;
    }

    private void a(Handler handler) {
        handler.sendEmptyMessage(2);
    }

    private void b(Handler handler) {
        handler.sendEmptyMessage(3);
    }
}
