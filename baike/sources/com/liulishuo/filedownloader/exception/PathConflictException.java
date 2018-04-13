package com.liulishuo.filedownloader.exception;

import com.liulishuo.filedownloader.util.FileDownloadUtils;

public class PathConflictException extends IllegalAccessException {
    private final String a;
    private final String b;
    private final int c;

    public PathConflictException(int i, String str, String str2) {
        super(FileDownloadUtils.formatString("There is an another running task(%d) with the same downloading path(%s), because of they are with the same target-file-path(%s),so if the current task is started, the path of the file is sure to be written by multiple tasks, it is wrong, then you receive this exception to avoid such conflict.", new Object[]{Integer.valueOf(i), str, str2}));
        this.c = i;
        this.a = str;
        this.b = str2;
    }

    public String getDownloadingConflictPath() {
        return this.a;
    }

    public String getTargetFilePath() {
        return this.b;
    }

    public int getAnotherSamePathTaskId() {
        return this.c;
    }
}
