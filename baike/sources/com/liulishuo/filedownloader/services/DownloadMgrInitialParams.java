package com.liulishuo.filedownloader.services;

import com.liulishuo.filedownloader.connection.FileDownloadUrlConnection;
import com.liulishuo.filedownloader.stream.FileDownloadRandomAccessFile.Creator;
import com.liulishuo.filedownloader.util.FileDownloadHelper.ConnectionCreator;
import com.liulishuo.filedownloader.util.FileDownloadHelper.DatabaseCustomMaker;
import com.liulishuo.filedownloader.util.FileDownloadHelper.OutputStreamCreator;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import com.liulishuo.filedownloader.util.FileDownloadProperties;

public class DownloadMgrInitialParams {
    private final InitCustomMaker a;

    public static class InitCustomMaker {
        DatabaseCustomMaker a;
        Integer b;
        OutputStreamCreator c;
        ConnectionCreator d;

        public InitCustomMaker database(DatabaseCustomMaker databaseCustomMaker) {
            this.a = databaseCustomMaker;
            return this;
        }

        public InitCustomMaker maxNetworkThreadCount(int i) {
            if (i > 0) {
                this.b = Integer.valueOf(i);
            }
            return this;
        }

        public InitCustomMaker outputStreamCreator(OutputStreamCreator outputStreamCreator) {
            this.c = outputStreamCreator;
            return this;
        }

        public InitCustomMaker connectionCreator(ConnectionCreator connectionCreator) {
            this.d = connectionCreator;
            return this;
        }

        private void a() {
            if (this.c != null && !this.c.supportSeek() && !FileDownloadProperties.getImpl().FILE_NON_PRE_ALLOCATION) {
                throw new IllegalArgumentException("Since the provided FileDownloadOutputStream does not support the seek function, if FileDownloader pre-allocates file size at the beginning of the download, it will can not be resumed from the breakpoint. If you need to ensure that the resumption is available, please add and set the value of 'file.non-pre-allocation' field to 'true' in the 'filedownloader.properties' file which is in your application assets folder manually for resolving this problem.");
            }
        }
    }

    public DownloadMgrInitialParams(InitCustomMaker initCustomMaker) {
        this.a = initCustomMaker;
        if (initCustomMaker != null) {
            initCustomMaker.a();
        }
    }

    int a() {
        if (this.a == null) {
            return e();
        }
        Integer num = this.a.b;
        if (num == null) {
            return e();
        }
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "initial FileDownloader manager with the customize maxNetworkThreadCount: %d", num);
        }
        return FileDownloadProperties.getValidNetworkThreadCount(num.intValue());
    }

    FileDownloadDatabase b() {
        if (this.a == null || this.a.a == null) {
            return f();
        }
        FileDownloadDatabase customMake = this.a.a.customMake();
        if (customMake == null) {
            return f();
        }
        if (!FileDownloadLog.NEED_LOG) {
            return customMake;
        }
        FileDownloadLog.d(this, "initial FileDownloader manager with the customize database: %s", customMake);
        return customMake;
    }

    OutputStreamCreator c() {
        if (this.a == null) {
            return g();
        }
        OutputStreamCreator outputStreamCreator = this.a.c;
        if (outputStreamCreator == null) {
            return g();
        }
        if (!FileDownloadLog.NEED_LOG) {
            return outputStreamCreator;
        }
        FileDownloadLog.d(this, "initial FileDownloader manager with the customize output stream: %s", outputStreamCreator);
        return outputStreamCreator;
    }

    ConnectionCreator d() {
        if (this.a == null) {
            return h();
        }
        ConnectionCreator connectionCreator = this.a.d;
        if (connectionCreator == null) {
            return h();
        }
        if (!FileDownloadLog.NEED_LOG) {
            return connectionCreator;
        }
        FileDownloadLog.d(this, "initial FileDownloader manager with the customize connection creator: %s", connectionCreator);
        return connectionCreator;
    }

    private int e() {
        return FileDownloadProperties.getImpl().DOWNLOAD_MAX_NETWORK_THREAD_COUNT;
    }

    private FileDownloadDatabase f() {
        return new a();
    }

    private OutputStreamCreator g() {
        return new Creator();
    }

    private ConnectionCreator h() {
        return new FileDownloadUrlConnection.Creator();
    }
}
