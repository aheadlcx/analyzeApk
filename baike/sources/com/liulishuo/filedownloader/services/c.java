package com.liulishuo.filedownloader.services;

import com.liulishuo.filedownloader.IThreadPoolMonitor;
import com.liulishuo.filedownloader.model.FileDownloadHeader;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import com.liulishuo.filedownloader.model.FileDownloadStatus;
import com.liulishuo.filedownloader.util.FileDownloadHelper;
import com.liulishuo.filedownloader.util.FileDownloadHelper.ConnectionCreator;
import com.liulishuo.filedownloader.util.FileDownloadHelper.OutputStreamCreator;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import java.io.File;
import java.util.List;

class c implements IThreadPoolMonitor {
    private final FileDownloadDatabase a;
    private final d b;
    private final OutputStreamCreator c;
    private final ConnectionCreator d;

    public c(DownloadMgrInitialParams downloadMgrInitialParams) {
        this.a = downloadMgrInitialParams.b();
        this.b = new d(downloadMgrInitialParams.a());
        this.c = downloadMgrInitialParams.c();
        this.d = downloadMgrInitialParams.d();
    }

    public synchronized void start(String str, String str2, boolean z, int i, int i2, int i3, boolean z2, FileDownloadHeader fileDownloadHeader, boolean z3) {
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "request start the task with url(%s) path(%s) isDirectory(%B)", str, str2, Boolean.valueOf(z));
        }
        int generateId = FileDownloadUtils.generateId(str, str2, z);
        FileDownloadModel find = this.a.find(generateId);
        if (!z && find == null) {
            find = this.a.find(FileDownloadUtils.generateId(str, FileDownloadUtils.getParent(str2), true));
            if (find != null) {
                if (str2.equals(find.getTargetFilePath()) && FileDownloadLog.NEED_LOG) {
                    FileDownloadLog.d(this, "task[%d] find model by dirCaseId[%d]", Integer.valueOf(generateId), Integer.valueOf(r2));
                }
            }
        }
        if (!FileDownloadHelper.inspectAndInflowDownloading(generateId, find, this, true)) {
            String targetFilePath;
            if (find != null) {
                targetFilePath = find.getTargetFilePath();
            } else {
                targetFilePath = FileDownloadUtils.getTargetFilePath(str2, z, null);
            }
            if (!FileDownloadHelper.inspectAndInflowDownloaded(generateId, targetFilePath, z2, true)) {
                String tempFilePath;
                long soFar = find != null ? find.getSoFar() : 0;
                if (find != null) {
                    tempFilePath = find.getTempFilePath();
                } else {
                    tempFilePath = FileDownloadUtils.getTempPath(targetFilePath);
                }
                if (!FileDownloadHelper.inspectAndInflowConflictPath(generateId, soFar, tempFilePath, targetFilePath, this)) {
                    FileDownloadModel fileDownloadModel;
                    Object obj;
                    if (find == null || !(find.getStatus() == (byte) -2 || find.getStatus() == (byte) -1)) {
                        if (find == null) {
                            fileDownloadModel = new FileDownloadModel();
                        } else {
                            fileDownloadModel = find;
                        }
                        fileDownloadModel.setUrl(str);
                        fileDownloadModel.setPath(str2, z);
                        fileDownloadModel.setId(generateId);
                        fileDownloadModel.setSoFar(0);
                        fileDownloadModel.setTotal(0);
                        fileDownloadModel.setStatus((byte) 1);
                        obj = 1;
                    } else if (find.getId() != generateId) {
                        this.a.remove(find.getId());
                        find.setId(generateId);
                        find.setPath(str2, z);
                        obj = 1;
                        fileDownloadModel = find;
                    } else {
                        obj = null;
                        fileDownloadModel = find;
                    }
                    if (obj != null) {
                        this.a.update(fileDownloadModel);
                    }
                    this.b.execute(new FileDownloadRunnable(this, this.c, this.d, fileDownloadModel, this.a, i3, fileDownloadHeader, i2, i, z2, z3));
                } else if (FileDownloadLog.NEED_LOG) {
                    FileDownloadLog.d(this, "there is an another task with the same target-file-path %d %s", Integer.valueOf(generateId), targetFilePath);
                    if (find != null) {
                        this.a.remove(generateId);
                    }
                }
            } else if (FileDownloadLog.NEED_LOG) {
                FileDownloadLog.d(this, "has already completed downloading %d", Integer.valueOf(generateId));
            }
        } else if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "has already started download %d", Integer.valueOf(generateId));
        }
    }

    public boolean isDownloading(String str, String str2) {
        return isDownloading(FileDownloadUtils.generateId(str, str2));
    }

    public boolean isDownloading(int i) {
        return isDownloading(this.a.find(i));
    }

    public static boolean isBreakpointAvailable(int i, FileDownloadModel fileDownloadModel) {
        return isBreakpointAvailable(i, fileDownloadModel, null);
    }

    public static boolean isBreakpointAvailable(int i, FileDownloadModel fileDownloadModel, Boolean bool) {
        if (fileDownloadModel == null) {
            if (!FileDownloadLog.NEED_LOG) {
                return false;
            }
            FileDownloadLog.d(c.class, "can't continue %d model == null", Integer.valueOf(i));
            return false;
        } else if (fileDownloadModel.getTempFilePath() != null) {
            return isBreakpointAvailable(i, fileDownloadModel, fileDownloadModel.getTempFilePath(), bool);
        } else {
            if (!FileDownloadLog.NEED_LOG) {
                return false;
            }
            FileDownloadLog.d(c.class, "can't continue %d temp path == null", Integer.valueOf(i));
            return false;
        }
    }

    public static boolean isBreakpointAvailable(int i, FileDownloadModel fileDownloadModel, String str, Boolean bool) {
        if (str != null) {
            File file = new File(str);
            boolean exists = file.exists();
            boolean isDirectory = file.isDirectory();
            if (exists && !isDirectory) {
                long length = file.length();
                if (fileDownloadModel.getSoFar() == 0) {
                    if (!FileDownloadLog.NEED_LOG) {
                        return false;
                    }
                    FileDownloadLog.d(c.class, "can't continue %d the downloaded-record is zero.", Integer.valueOf(i));
                    return false;
                } else if (length < fileDownloadModel.getSoFar() || (fileDownloadModel.getTotal() != -1 && (length > fileDownloadModel.getTotal() || fileDownloadModel.getSoFar() >= fileDownloadModel.getTotal()))) {
                    if (!FileDownloadLog.NEED_LOG) {
                        return false;
                    }
                    FileDownloadLog.d(c.class, "can't continue %d dirty data fileLength[%d] sofar[%d] total[%d]", Integer.valueOf(i), Long.valueOf(length), Long.valueOf(fileDownloadModel.getSoFar()), Long.valueOf(fileDownloadModel.getTotal()));
                    return false;
                } else if (bool == null || bool.booleanValue() || fileDownloadModel.getTotal() != length) {
                    return true;
                } else {
                    if (!FileDownloadLog.NEED_LOG) {
                        return false;
                    }
                    FileDownloadLog.d(c.class, "can't continue %d, because of the output stream doesn't support seek, but the task has already pre-allocated, so we only can download it from the very beginning.", Integer.valueOf(i));
                    return false;
                }
            } else if (!FileDownloadLog.NEED_LOG) {
                return false;
            } else {
                FileDownloadLog.d(c.class, "can't continue %d file not suit, exists[%B], directory[%B]", Integer.valueOf(i), Boolean.valueOf(exists), Boolean.valueOf(isDirectory));
                return false;
            }
        } else if (!FileDownloadLog.NEED_LOG) {
            return false;
        } else {
            FileDownloadLog.d(c.class, "can't continue %d path = null", Integer.valueOf(i));
            return false;
        }
    }

    public boolean pause(int i) {
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "request pause the task %d", Integer.valueOf(i));
        }
        if (this.a.find(i) == null) {
            return false;
        }
        this.b.cancel(i);
        return true;
    }

    public void pauseAll() {
        List<Integer> allExactRunningDownloadIds = this.b.getAllExactRunningDownloadIds();
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "pause all tasks %d", Integer.valueOf(allExactRunningDownloadIds.size()));
        }
        for (Integer intValue : allExactRunningDownloadIds) {
            pause(intValue.intValue());
        }
    }

    public long getSoFar(int i) {
        FileDownloadModel find = this.a.find(i);
        if (find == null) {
            return 0;
        }
        return find.getSoFar();
    }

    public long getTotal(int i) {
        FileDownloadModel find = this.a.find(i);
        if (find == null) {
            return 0;
        }
        return find.getTotal();
    }

    public byte getStatus(int i) {
        FileDownloadModel find = this.a.find(i);
        if (find == null) {
            return (byte) 0;
        }
        return find.getStatus();
    }

    public boolean isIdle() {
        return this.b.exactSize() <= 0;
    }

    public synchronized boolean setMaxNetworkThreadCount(int i) {
        return this.b.setMaxNetworkThreadCount(i);
    }

    public boolean isDownloading(FileDownloadModel fileDownloadModel) {
        boolean z = true;
        if (fileDownloadModel == null) {
            return false;
        }
        boolean isInThreadPool = this.b.isInThreadPool(fileDownloadModel.getId());
        if (FileDownloadStatus.isOver(fileDownloadModel.getStatus())) {
            if (!isInThreadPool) {
                z = false;
            }
        } else if (!isInThreadPool) {
            FileDownloadLog.e(this, "%d status is[%s](not finish) & but not in the pool", Integer.valueOf(fileDownloadModel.getId()), Byte.valueOf(fileDownloadModel.getStatus()));
            z = false;
        }
        return z;
    }

    public int findRunningTaskIdBySameTempPath(String str, int i) {
        return this.b.findRunningTaskIdBySameTempPath(str, i);
    }

    public boolean clearTaskData(int i) {
        if (i == 0) {
            FileDownloadLog.w(this, "The task[%d] id is invalid, can't clear it.", Integer.valueOf(i));
            return false;
        } else if (isDownloading(i)) {
            FileDownloadLog.w(this, "The task[%d] is downloading, can't clear it.", Integer.valueOf(i));
            return false;
        } else {
            this.a.remove(i);
            return true;
        }
    }

    public void clearAllTaskData() {
        this.a.clear();
    }
}
