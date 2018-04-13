package com.liulishuo.filedownloader;

import com.liulishuo.filedownloader.BaseDownloadTask.IRunningTask;
import com.liulishuo.filedownloader.message.MessageSnapshot;
import com.liulishuo.filedownloader.message.MessageSnapshotFlow.MessageReceiver;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import java.util.List;

public class MessageSnapshotGate implements MessageReceiver {
    private boolean a(List<IRunningTask> list, MessageSnapshot messageSnapshot) {
        if (list.size() > 1 && messageSnapshot.getStatus() == (byte) -3) {
            for (IRunningTask messageHandler : list) {
                if (messageHandler.getMessageHandler().updateMoreLikelyCompleted(messageSnapshot)) {
                    return true;
                }
            }
        }
        for (IRunningTask messageHandler2 : list) {
            if (messageHandler2.getMessageHandler().updateKeepFlow(messageSnapshot)) {
                return true;
            }
        }
        if ((byte) -4 == messageSnapshot.getStatus()) {
            for (IRunningTask messageHandler22 : list) {
                if (messageHandler22.getMessageHandler().updateSameFilePathTaskRunning(messageSnapshot)) {
                    return true;
                }
            }
        }
        return list.size() == 1 ? ((IRunningTask) list.get(0)).getMessageHandler().updateKeepAhead(messageSnapshot) : false;
    }

    public void receive(MessageSnapshot messageSnapshot) {
        synchronized (Integer.toString(messageSnapshot.getId()).intern()) {
            List<IRunningTask> b = FileDownloadList.getImpl().b(messageSnapshot.getId());
            if (b.size() > 0) {
                BaseDownloadTask origin = ((IRunningTask) b.get(0)).getOrigin();
                if (FileDownloadLog.NEED_LOG) {
                    FileDownloadLog.d(this, "~~~callback %s old[%s] new[%s] %d", new Object[]{Integer.valueOf(messageSnapshot.getId()), Byte.valueOf(origin.getStatus()), Byte.valueOf(messageSnapshot.getStatus()), Integer.valueOf(b.size())});
                }
                if (!a(b, messageSnapshot)) {
                    String str = "The event isn't consumed, id:" + messageSnapshot.getId() + " status:" + messageSnapshot.getStatus() + " task-count:" + b.size();
                    String str2 = str;
                    for (IRunningTask origin2 : b) {
                        str2 = str2 + " | " + origin2.getOrigin().getStatus();
                    }
                    FileDownloadLog.i(this, str2, new Object[0]);
                }
            } else {
                FileDownloadLog.i(this, "Receive the event %d, but there isn't any running task in the upper layer", new Object[]{Byte.valueOf(messageSnapshot.getStatus())});
            }
        }
    }
}
