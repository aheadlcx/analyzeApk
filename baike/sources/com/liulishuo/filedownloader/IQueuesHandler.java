package com.liulishuo.filedownloader;

import java.util.List;

public interface IQueuesHandler {
    boolean contain(int i);

    void freezeAllSerialQueues();

    int serialQueueSize();

    boolean startQueueParallel(FileDownloadListener fileDownloadListener);

    boolean startQueueSerial(FileDownloadListener fileDownloadListener);

    void unFreezeSerialQueues(List<Integer> list);
}
