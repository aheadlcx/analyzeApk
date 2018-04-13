package com.liulishuo.filedownloader;

import com.liulishuo.filedownloader.BaseDownloadTask.IRunningTask;
import com.liulishuo.filedownloader.message.MessageSnapshot;
import com.liulishuo.filedownloader.message.MessageSnapshotTaker;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileDownloadList {
    private final ArrayList<IRunningTask> a;

    private static final class a {
        private static final FileDownloadList a = new FileDownloadList();
    }

    public static FileDownloadList getImpl() {
        return a.a;
    }

    private FileDownloadList() {
        this.a = new ArrayList();
    }

    boolean a() {
        return this.a.isEmpty();
    }

    int b() {
        return this.a.size();
    }

    int a(int i) {
        int i2 = 0;
        synchronized (this.a) {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                int i3;
                if (((IRunningTask) it.next()).is(i)) {
                    i3 = i2 + 1;
                } else {
                    i3 = i2;
                }
                i2 = i3;
            }
        }
        return i2;
    }

    public IRunningTask get(int i) {
        synchronized (this.a) {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                IRunningTask iRunningTask = (IRunningTask) it.next();
                if (iRunningTask.is(i)) {
                    return iRunningTask;
                }
            }
            return null;
        }
    }

    List<IRunningTask> b(int i) {
        List<IRunningTask> arrayList = new ArrayList();
        synchronized (this.a) {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                IRunningTask iRunningTask = (IRunningTask) it.next();
                if (iRunningTask.is(i) && !iRunningTask.isOver()) {
                    byte status = iRunningTask.getOrigin().getStatus();
                    if (!(status == (byte) 0 || status == (byte) 10)) {
                        arrayList.add(iRunningTask);
                    }
                }
            }
        }
        return arrayList;
    }

    List<IRunningTask> c(int i) {
        List<IRunningTask> arrayList = new ArrayList();
        synchronized (this.a) {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                IRunningTask iRunningTask = (IRunningTask) it.next();
                if (iRunningTask.is(i) && !iRunningTask.isOver()) {
                    arrayList.add(iRunningTask);
                }
            }
        }
        return arrayList;
    }

    boolean a(IRunningTask iRunningTask) {
        return this.a.isEmpty() || !this.a.contains(iRunningTask);
    }

    List<IRunningTask> a(FileDownloadListener fileDownloadListener) {
        List<IRunningTask> arrayList = new ArrayList();
        synchronized (this.a) {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                IRunningTask iRunningTask = (IRunningTask) it.next();
                if (iRunningTask.is(fileDownloadListener)) {
                    arrayList.add(iRunningTask);
                }
            }
        }
        return arrayList;
    }

    List<IRunningTask> a(int i, FileDownloadListener fileDownloadListener) {
        List<IRunningTask> arrayList = new ArrayList();
        synchronized (this.a) {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                IRunningTask iRunningTask = (IRunningTask) it.next();
                if (iRunningTask.getOrigin().getListener() == fileDownloadListener && !iRunningTask.getOrigin().isAttached()) {
                    iRunningTask.setAttachKeyByQueue(i);
                    arrayList.add(iRunningTask);
                }
            }
        }
        return arrayList;
    }

    IRunningTask[] c() {
        IRunningTask[] iRunningTaskArr;
        synchronized (this.a) {
            iRunningTaskArr = (IRunningTask[]) this.a.toArray(new IRunningTask[this.a.size()]);
        }
        return iRunningTaskArr;
    }

    void a(List<IRunningTask> list) {
        synchronized (this.a) {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                IRunningTask iRunningTask = (IRunningTask) it.next();
                if (!list.contains(iRunningTask)) {
                    list.add(iRunningTask);
                }
            }
            this.a.clear();
        }
    }

    public boolean remove(IRunningTask iRunningTask, MessageSnapshot messageSnapshot) {
        boolean remove;
        byte status = messageSnapshot.getStatus();
        synchronized (this.a) {
            remove = this.a.remove(iRunningTask);
        }
        if (FileDownloadLog.NEED_LOG && this.a.size() == 0) {
            FileDownloadLog.v(this, "remove %s left %d %d", new Object[]{iRunningTask, Byte.valueOf(status), Integer.valueOf(this.a.size())});
        }
        if (remove) {
            o messenger = iRunningTask.getMessageHandler().getMessenger();
            switch (status) {
                case (byte) -4:
                    messenger.notifyWarn(messageSnapshot);
                    break;
                case (byte) -3:
                    messenger.notifyBlockComplete(MessageSnapshotTaker.takeBlockCompleted(messageSnapshot));
                    break;
                case (byte) -2:
                    messenger.notifyPaused(messageSnapshot);
                    break;
                case (byte) -1:
                    messenger.notifyError(messageSnapshot);
                    break;
            }
        }
        FileDownloadLog.e(this, "remove error, not exist: %s %d", new Object[]{iRunningTask, Byte.valueOf(status)});
        return remove;
    }

    void b(IRunningTask iRunningTask) {
        if (!iRunningTask.getOrigin().isAttached()) {
            iRunningTask.setAttachKeyDefault();
        }
        if (iRunningTask.getMessageHandler().getMessenger().notifyBegin()) {
            c(iRunningTask);
        }
    }

    void c(IRunningTask iRunningTask) {
        if (!iRunningTask.isMarkedAdded2List()) {
            synchronized (this.a) {
                if (this.a.contains(iRunningTask)) {
                    FileDownloadLog.w(this, "already has %s", new Object[]{iRunningTask});
                } else {
                    iRunningTask.markAdded2List();
                    this.a.add(iRunningTask);
                    if (FileDownloadLog.NEED_LOG) {
                        FileDownloadLog.v(this, "add list in all %s %d %d", new Object[]{iRunningTask, Byte.valueOf(iRunningTask.getOrigin().getStatus()), Integer.valueOf(this.a.size())});
                    }
                }
            }
        }
    }
}
