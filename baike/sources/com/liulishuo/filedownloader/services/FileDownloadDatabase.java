package com.liulishuo.filedownloader.services;

import com.liulishuo.filedownloader.model.FileDownloadModel;
import java.util.List;

public interface FileDownloadDatabase {
    void clear();

    FileDownloadModel find(int i);

    void insert(FileDownloadModel fileDownloadModel);

    boolean remove(int i);

    void update(FileDownloadModel fileDownloadModel);

    void update(List<FileDownloadModel> list);

    void updateComplete(FileDownloadModel fileDownloadModel, long j);

    void updateConnected(FileDownloadModel fileDownloadModel, long j, String str, String str2);

    void updateError(FileDownloadModel fileDownloadModel, Throwable th, long j);

    void updatePause(FileDownloadModel fileDownloadModel, long j);

    void updatePending(FileDownloadModel fileDownloadModel);

    void updateProgress(FileDownloadModel fileDownloadModel, long j);

    void updateRetry(FileDownloadModel fileDownloadModel, Throwable th);
}
