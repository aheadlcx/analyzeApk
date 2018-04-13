package com.liulishuo.filedownloader;

import com.liulishuo.filedownloader.BaseDownloadTask.IRunningTask;
import com.liulishuo.filedownloader.BaseDownloadTask.LifeCycleCallback;
import com.liulishuo.filedownloader.ITaskHunter.IMessageHandler;
import com.liulishuo.filedownloader.message.BlockCompleteMessage;
import com.liulishuo.filedownloader.message.MessageSnapshot;
import com.liulishuo.filedownloader.model.FileDownloadStatus;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import junit.framework.Assert;

class j implements o {
    private IRunningTask a;
    private LifeCycleCallback b;
    private Queue<MessageSnapshot> c;
    private boolean d = false;

    j(IRunningTask iRunningTask, LifeCycleCallback lifeCycleCallback) {
        a(iRunningTask, lifeCycleCallback);
    }

    private void a(IRunningTask iRunningTask, LifeCycleCallback lifeCycleCallback) {
        this.a = iRunningTask;
        this.b = lifeCycleCallback;
        this.c = new LinkedBlockingQueue();
    }

    public boolean notifyBegin() {
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "notify begin %s", this.a);
        }
        if (this.a == null) {
            FileDownloadLog.w(this, "can't begin the task, the holder fo the messenger is nil, %d", Integer.valueOf(this.c.size()));
            return false;
        }
        this.b.onBegin();
        return true;
    }

    public void notifyPending(MessageSnapshot messageSnapshot) {
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "notify pending %s", this.a);
        }
        this.b.onIng();
        a(messageSnapshot);
    }

    public void notifyStarted(MessageSnapshot messageSnapshot) {
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "notify started %s", this.a);
        }
        this.b.onIng();
        a(messageSnapshot);
    }

    public void notifyConnected(MessageSnapshot messageSnapshot) {
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "notify connected %s", this.a);
        }
        this.b.onIng();
        a(messageSnapshot);
    }

    public void notifyProgress(MessageSnapshot messageSnapshot) {
        BaseDownloadTask origin = this.a.getOrigin();
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "notify progress %s %d %d", origin, Long.valueOf(origin.getLargeFileSoFarBytes()), Long.valueOf(origin.getLargeFileTotalBytes()));
        }
        if (origin.getCallbackProgressTimes() > 0) {
            this.b.onIng();
            a(messageSnapshot);
        } else if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "notify progress but client not request notify %s", this.a);
        }
    }

    public void notifyBlockComplete(MessageSnapshot messageSnapshot) {
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "notify block completed %s %s", this.a, Thread.currentThread().getName());
        }
        this.b.onIng();
        a(messageSnapshot);
    }

    public void notifyRetry(MessageSnapshot messageSnapshot) {
        if (FileDownloadLog.NEED_LOG) {
            BaseDownloadTask origin = this.a.getOrigin();
            FileDownloadLog.d(this, "notify retry %s %d %d %s", this.a, Integer.valueOf(origin.getAutoRetryTimes()), Integer.valueOf(origin.getRetryingTimes()), origin.getErrorCause());
        }
        this.b.onIng();
        a(messageSnapshot);
    }

    public void notifyWarn(MessageSnapshot messageSnapshot) {
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "notify warn %s", this.a);
        }
        this.b.onOver();
        a(messageSnapshot);
    }

    public void notifyError(MessageSnapshot messageSnapshot) {
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "notify error %s %s", this.a, this.a.getOrigin().getErrorCause());
        }
        this.b.onOver();
        a(messageSnapshot);
    }

    public void notifyPaused(MessageSnapshot messageSnapshot) {
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "notify paused %s", this.a);
        }
        this.b.onOver();
        a(messageSnapshot);
    }

    public void notifyCompleted(MessageSnapshot messageSnapshot) {
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "notify completed %s", this.a);
        }
        this.b.onOver();
        a(messageSnapshot);
    }

    private void a(MessageSnapshot messageSnapshot) {
        if (this.a == null) {
            if (FileDownloadLog.NEED_LOG) {
                FileDownloadLog.d(this, "occur this case, it would be the host task of this messenger has been over(paused/warn/completed/error) on the other thread before receiving the snapshot(id[%d], status[%d])", Integer.valueOf(messageSnapshot.getId()), Byte.valueOf(messageSnapshot.getStatus()));
            }
        } else if (this.d || this.a.getOrigin().getListener() == null) {
            if (FileDownloadMonitor.isValid() && messageSnapshot.getStatus() == (byte) 4) {
                this.b.onOver();
            }
            a(messageSnapshot.getStatus());
        } else {
            this.c.offer(messageSnapshot);
            FileDownloadMessageStation.getImpl().a(this);
        }
    }

    private void a(int i) {
        if (!FileDownloadStatus.isOver(i)) {
            return;
        }
        if (this.c.isEmpty()) {
            this.a = null;
        } else {
            throw new IllegalStateException(FileDownloadUtils.formatString("the messenger[%s] has already accomplished all his job, but there still are some messages in parcel queue[%d]", this, Integer.valueOf(this.c.size())));
        }
    }

    public void handoverMessage() {
        if (!this.d) {
            MessageSnapshot messageSnapshot = (MessageSnapshot) this.c.poll();
            byte status = messageSnapshot.getStatus();
            IRunningTask iRunningTask = this.a;
            Assert.assertTrue(FileDownloadUtils.formatString("can't handover the message, no master to receive this message(status[%d]) size[%d]", Integer.valueOf(status), Integer.valueOf(this.c.size())), iRunningTask != null);
            BaseDownloadTask origin = iRunningTask.getOrigin();
            FileDownloadListener listener = origin.getListener();
            IMessageHandler messageHandler = iRunningTask.getMessageHandler();
            a((int) status);
            if (listener != null && !listener.a()) {
                if (status == (byte) 4) {
                    try {
                        listener.b(origin);
                        notifyCompleted(((BlockCompleteMessage) messageSnapshot).transmitToCompleted());
                        return;
                    } catch (Throwable th) {
                        notifyError(messageHandler.prepareErrorMessage(th));
                        return;
                    }
                }
                FileDownloadLargeFileListener fileDownloadLargeFileListener = null;
                if (listener instanceof FileDownloadLargeFileListener) {
                    fileDownloadLargeFileListener = (FileDownloadLargeFileListener) listener;
                }
                switch (status) {
                    case (byte) -4:
                        listener.d(origin);
                        return;
                    case (byte) -3:
                        listener.c(origin);
                        return;
                    case (byte) -2:
                        if (fileDownloadLargeFileListener != null) {
                            fileDownloadLargeFileListener.c(origin, messageSnapshot.getLargeSofarBytes(), messageSnapshot.getLargeTotalBytes());
                            return;
                        } else {
                            listener.c(origin, messageSnapshot.getSmallSofarBytes(), messageSnapshot.getSmallTotalBytes());
                            return;
                        }
                    case (byte) -1:
                        listener.a(origin, messageSnapshot.getThrowable());
                        return;
                    case (byte) 1:
                        if (fileDownloadLargeFileListener != null) {
                            fileDownloadLargeFileListener.a(origin, messageSnapshot.getLargeSofarBytes(), messageSnapshot.getLargeTotalBytes());
                            return;
                        } else {
                            listener.a(origin, messageSnapshot.getSmallSofarBytes(), messageSnapshot.getSmallTotalBytes());
                            return;
                        }
                    case (byte) 2:
                        if (fileDownloadLargeFileListener != null) {
                            fileDownloadLargeFileListener.a(origin, messageSnapshot.getEtag(), messageSnapshot.isResuming(), origin.getLargeFileSoFarBytes(), messageSnapshot.getLargeTotalBytes());
                            return;
                        } else {
                            listener.a(origin, messageSnapshot.getEtag(), messageSnapshot.isResuming(), origin.getSmallFileSoFarBytes(), messageSnapshot.getSmallTotalBytes());
                            return;
                        }
                    case (byte) 3:
                        if (fileDownloadLargeFileListener != null) {
                            fileDownloadLargeFileListener.b(origin, messageSnapshot.getLargeSofarBytes(), origin.getLargeFileTotalBytes());
                            return;
                        } else {
                            listener.b(origin, messageSnapshot.getSmallSofarBytes(), origin.getSmallFileTotalBytes());
                            return;
                        }
                    case (byte) 5:
                        if (fileDownloadLargeFileListener != null) {
                            fileDownloadLargeFileListener.a(origin, messageSnapshot.getThrowable(), messageSnapshot.getRetryingTimes(), messageSnapshot.getLargeSofarBytes());
                            return;
                        } else {
                            listener.a(origin, messageSnapshot.getThrowable(), messageSnapshot.getRetryingTimes(), messageSnapshot.getSmallSofarBytes());
                            return;
                        }
                    case (byte) 6:
                        listener.a(origin);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public boolean handoverDirectly() {
        return this.a.getOrigin().isSyncCallback();
    }

    public void reAppointment(IRunningTask iRunningTask, LifeCycleCallback lifeCycleCallback) {
        if (this.a != null) {
            throw new IllegalStateException(FileDownloadUtils.formatString("the messenger is working, can't re-appointment for %s", iRunningTask));
        } else {
            a(iRunningTask, lifeCycleCallback);
        }
    }

    public boolean isBlockingCompleted() {
        return ((MessageSnapshot) this.c.peek()).getStatus() == (byte) 4;
    }

    public void discard() {
        this.d = true;
    }

    public String toString() {
        return FileDownloadUtils.formatString("%d:%s", Integer.valueOf(this.a.getOrigin().getId()), super.toString());
    }
}
