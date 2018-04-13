package com.liulishuo.filedownloader.message;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.message.BlockCompleteMessage.BlockCompleteMessageImpl;
import com.liulishuo.filedownloader.message.LargeMessageSnapshot.CompletedFlowDirectlySnapshot;
import com.liulishuo.filedownloader.message.LargeMessageSnapshot.CompletedSnapshot;
import com.liulishuo.filedownloader.message.LargeMessageSnapshot.ConnectedMessageSnapshot;
import com.liulishuo.filedownloader.message.LargeMessageSnapshot.ErrorMessageSnapshot;
import com.liulishuo.filedownloader.message.LargeMessageSnapshot.PausedSnapshot;
import com.liulishuo.filedownloader.message.LargeMessageSnapshot.PendingMessageSnapshot;
import com.liulishuo.filedownloader.message.LargeMessageSnapshot.ProgressMessageSnapshot;
import com.liulishuo.filedownloader.message.LargeMessageSnapshot.RetryMessageSnapshot;
import com.liulishuo.filedownloader.message.LargeMessageSnapshot.WarnFlowDirectlySnapshot;
import com.liulishuo.filedownloader.message.LargeMessageSnapshot.WarnMessageSnapshot;
import com.liulishuo.filedownloader.message.MessageSnapshot.StartedMessageSnapshot;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import com.liulishuo.filedownloader.services.FileDownloadRunnable;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import java.io.File;

public class MessageSnapshotTaker {
    public static MessageSnapshot take(byte b, FileDownloadModel fileDownloadModel) {
        return take(b, fileDownloadModel, null);
    }

    public static MessageSnapshot catchCanReusedOldFile(int i, File file, boolean z) {
        long length = file.length();
        if (length > 2147483647L) {
            if (z) {
                return new CompletedFlowDirectlySnapshot(i, true, length);
            }
            return new CompletedSnapshot(i, true, length);
        } else if (z) {
            return new SmallMessageSnapshot.CompletedFlowDirectlySnapshot(i, true, (int) length);
        } else {
            return new SmallMessageSnapshot.CompletedSnapshot(i, true, (int) length);
        }
    }

    public static MessageSnapshot catchWarn(int i, long j, long j2, boolean z) {
        if (j2 > 2147483647L) {
            if (z) {
                return new WarnFlowDirectlySnapshot(i, j, j2);
            }
            return new WarnMessageSnapshot(i, j, j2);
        } else if (z) {
            return new SmallMessageSnapshot.WarnFlowDirectlySnapshot(i, (int) j, (int) j2);
        } else {
            return new SmallMessageSnapshot.WarnMessageSnapshot(i, (int) j, (int) j2);
        }
    }

    public static MessageSnapshot catchException(int i, long j, Throwable th) {
        if (j > 2147483647L) {
            return new ErrorMessageSnapshot(i, j, th);
        }
        return new SmallMessageSnapshot.ErrorMessageSnapshot(i, (int) j, th);
    }

    public static MessageSnapshot catchPause(BaseDownloadTask baseDownloadTask) {
        if (baseDownloadTask.isLargeFile()) {
            return new PausedSnapshot(baseDownloadTask.getId(), baseDownloadTask.getLargeFileSoFarBytes(), baseDownloadTask.getLargeFileTotalBytes());
        }
        return new SmallMessageSnapshot.PausedSnapshot(baseDownloadTask.getId(), baseDownloadTask.getSmallFileSoFarBytes(), baseDownloadTask.getSmallFileTotalBytes());
    }

    public static MessageSnapshot takeBlockCompleted(MessageSnapshot messageSnapshot) {
        if (messageSnapshot.getStatus() == (byte) -3) {
            return new BlockCompleteMessageImpl(messageSnapshot);
        }
        throw new IllegalStateException(FileDownloadUtils.formatString("take block completed snapshot, must has already be completed. %d %d", Integer.valueOf(messageSnapshot.getId()), Byte.valueOf(messageSnapshot.getStatus())));
    }

    public static MessageSnapshot take(byte b, FileDownloadModel fileDownloadModel, FileDownloadRunnable fileDownloadRunnable) {
        int id = fileDownloadModel.getId();
        if (b == (byte) -4) {
            throw new IllegalStateException(FileDownloadUtils.formatString("please use #catchWarn instead %d", Integer.valueOf(id)));
        }
        switch (b) {
            case (byte) -3:
                if (fileDownloadModel.isLargeFile()) {
                    return new CompletedSnapshot(id, false, fileDownloadModel.getTotal());
                }
                return new SmallMessageSnapshot.CompletedSnapshot(id, false, (int) fileDownloadModel.getTotal());
            case (byte) -1:
                if (fileDownloadModel.isLargeFile()) {
                    return new ErrorMessageSnapshot(id, fileDownloadModel.getSoFar(), fileDownloadRunnable.getThrowable());
                }
                return new SmallMessageSnapshot.ErrorMessageSnapshot(id, (int) fileDownloadModel.getSoFar(), fileDownloadRunnable.getThrowable());
            case (byte) 1:
                if (fileDownloadModel.isLargeFile()) {
                    return new PendingMessageSnapshot(id, fileDownloadModel.getSoFar(), fileDownloadModel.getTotal());
                }
                return new SmallMessageSnapshot.PendingMessageSnapshot(id, (int) fileDownloadModel.getSoFar(), (int) fileDownloadModel.getTotal());
            case (byte) 2:
                String filename = fileDownloadModel.isPathAsDirectory() ? fileDownloadModel.getFilename() : null;
                if (fileDownloadModel.isLargeFile()) {
                    return new ConnectedMessageSnapshot(id, fileDownloadRunnable.isResuming(), fileDownloadModel.getTotal(), fileDownloadModel.getETag(), filename);
                }
                return new SmallMessageSnapshot.ConnectedMessageSnapshot(id, fileDownloadRunnable.isResuming(), (int) fileDownloadModel.getTotal(), fileDownloadModel.getETag(), filename);
            case (byte) 3:
                if (fileDownloadModel.isLargeFile()) {
                    return new ProgressMessageSnapshot(id, fileDownloadModel.getSoFar());
                }
                return new SmallMessageSnapshot.ProgressMessageSnapshot(id, (int) fileDownloadModel.getSoFar());
            case (byte) 5:
                if (fileDownloadModel.isLargeFile()) {
                    return new RetryMessageSnapshot(id, fileDownloadModel.getSoFar(), fileDownloadRunnable.getThrowable(), fileDownloadRunnable.getRetryingTimes());
                }
                return new SmallMessageSnapshot.RetryMessageSnapshot(id, (int) fileDownloadModel.getSoFar(), fileDownloadRunnable.getThrowable(), fileDownloadRunnable.getRetryingTimes());
            case (byte) 6:
                return new StartedMessageSnapshot(id);
            default:
                Throwable illegalStateException;
                String formatString = FileDownloadUtils.formatString("it can't takes a snapshot for the task(%s) when its status is %d,", fileDownloadModel, Byte.valueOf(b));
                FileDownloadLog.w(MessageSnapshotTaker.class, formatString, new Object[0]);
                if (fileDownloadRunnable.getThrowable() != null) {
                    illegalStateException = new IllegalStateException(formatString, fileDownloadRunnable.getThrowable());
                } else {
                    illegalStateException = new IllegalStateException(formatString);
                }
                if (fileDownloadModel.isLargeFile()) {
                    return new ErrorMessageSnapshot(id, fileDownloadModel.getSoFar(), illegalStateException);
                }
                return new SmallMessageSnapshot.ErrorMessageSnapshot(id, (int) fileDownloadModel.getSoFar(), illegalStateException);
        }
    }
}
