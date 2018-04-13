package com.liulishuo.filedownloader.model;

import com.liulishuo.filedownloader.BaseDownloadTask;

public class FileDownloadStatus {
    public static final byte INVALID_STATUS = (byte) 0;
    public static final byte blockComplete = (byte) 4;
    public static final byte completed = (byte) -3;
    public static final byte connected = (byte) 2;
    public static final byte error = (byte) -1;
    public static final byte paused = (byte) -2;
    public static final byte pending = (byte) 1;
    public static final byte progress = (byte) 3;
    public static final byte retry = (byte) 5;
    public static final byte started = (byte) 6;
    public static final byte toFileDownloadService = (byte) 11;
    public static final byte toLaunchPool = (byte) 10;
    public static final byte warn = (byte) -4;

    public static boolean isOver(int i) {
        return i < 0;
    }

    public static boolean isIng(int i) {
        return i > 0;
    }

    public static boolean isKeepAhead(int i, int i2) {
        if ((i != 3 && i != 5 && i == i2) || isOver(i)) {
            return false;
        }
        if (i >= 1 && i <= 6 && i2 >= 10 && i2 <= 11) {
            return false;
        }
        switch (i) {
            case 1:
                switch (i2) {
                    case 0:
                        return false;
                    default:
                        return true;
                }
            case 2:
                switch (i2) {
                    case 0:
                    case 1:
                    case 6:
                        return false;
                    default:
                        return true;
                }
            case 3:
                switch (i2) {
                    case 0:
                    case 1:
                    case 2:
                    case 6:
                        return false;
                    default:
                        return true;
                }
            case 5:
                switch (i2) {
                    case 1:
                    case 6:
                        return false;
                    default:
                        return true;
                }
            case 6:
                switch (i2) {
                    case 0:
                    case 1:
                        return false;
                    default:
                        return true;
                }
            default:
                return true;
        }
    }

    public static boolean isKeepFlow(int i, int i2) {
        if ((i != 3 && i != 5 && i == i2) || isOver(i)) {
            return false;
        }
        if (i2 == -2) {
            return true;
        }
        if (i2 == -1) {
            return true;
        }
        switch (i) {
            case 0:
                switch (i2) {
                    case 10:
                        return true;
                    default:
                        return false;
                }
            case 1:
                switch (i2) {
                    case 6:
                        return true;
                    default:
                        return false;
                }
            case 2:
            case 3:
                switch (i2) {
                    case -3:
                    case 3:
                    case 5:
                        return true;
                    default:
                        return false;
                }
            case 5:
            case 6:
                switch (i2) {
                    case 2:
                    case 5:
                        return true;
                    default:
                        return false;
                }
            case 10:
                switch (i2) {
                    case 11:
                        return true;
                    default:
                        return false;
                }
            case 11:
                switch (i2) {
                    case -4:
                    case -3:
                    case 1:
                        return true;
                    default:
                        return false;
                }
            default:
                return false;
        }
    }

    public static boolean isMoreLikelyCompleted(BaseDownloadTask baseDownloadTask) {
        return baseDownloadTask.getStatus() == (byte) 0 || baseDownloadTask.getStatus() == (byte) 3;
    }
}
