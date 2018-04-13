package com.liulishuo.filedownloader.services;

import android.database.sqlite.SQLiteFullException;
import android.os.Build.VERSION;
import android.os.Process;
import android.os.SystemClock;
import android.text.TextUtils;
import com.liulishuo.filedownloader.IThreadPoolMonitor;
import com.liulishuo.filedownloader.connection.FileDownloadConnection;
import com.liulishuo.filedownloader.exception.FileDownloadGiveUpRetryException;
import com.liulishuo.filedownloader.exception.FileDownloadHttpException;
import com.liulishuo.filedownloader.exception.FileDownloadNetworkPolicyException;
import com.liulishuo.filedownloader.exception.FileDownloadOutOfSpaceException;
import com.liulishuo.filedownloader.model.FileDownloadHeader;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import com.liulishuo.filedownloader.stream.FileDownloadOutputStream;
import com.liulishuo.filedownloader.util.FileDownloadHelper;
import com.liulishuo.filedownloader.util.FileDownloadHelper.ConnectionCreator;
import com.liulishuo.filedownloader.util.FileDownloadHelper.OutputStreamCreator;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import com.liulishuo.filedownloader.util.FileDownloadProperties;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import cz.msebera.android.httpclient.HttpStatus;
import cz.msebera.android.httpclient.entity.mime.MIME;
import cz.msebera.android.httpclient.protocol.HTTP;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class FileDownloadRunnable implements Runnable {
    private int a = 0;
    private final boolean b;
    private boolean c;
    private boolean d;
    private Throwable e;
    private int f;
    private FileDownloadModel g;
    private volatile boolean h = false;
    private volatile boolean i = false;
    private final FileDownloadDatabase j;
    private final int k;
    private final FileDownloadHeader l;
    private volatile boolean m = false;
    private final int n;
    private long o;
    private final IThreadPoolMonitor p;
    private final boolean q;
    private final int r;
    private final OutputStreamCreator s;
    private final ConnectionCreator t;
    private long u = 0;
    private long v = 0;
    private long w = 0;
    private long x = 0;
    private final Object y = new Object();

    public FileDownloadRunnable(IThreadPoolMonitor iThreadPoolMonitor, OutputStreamCreator outputStreamCreator, ConnectionCreator connectionCreator, FileDownloadModel fileDownloadModel, FileDownloadDatabase fileDownloadDatabase, int i, FileDownloadHeader fileDownloadHeader, int i2, int i3, boolean z, boolean z2) {
        this.r = fileDownloadModel.getId();
        this.q = z2;
        this.i = true;
        this.h = false;
        this.p = iThreadPoolMonitor;
        this.s = outputStreamCreator;
        this.j = fileDownloadDatabase;
        this.l = fileDownloadHeader;
        if (i2 < 5) {
            i2 = 5;
        }
        this.n = i2;
        this.a = i3;
        this.b = z;
        this.c = false;
        this.g = fileDownloadModel;
        this.k = i;
        this.t = connectionCreator;
    }

    public int getId() {
        return this.r;
    }

    public boolean isExist() {
        return this.i || this.h;
    }

    public String getTempFilePath() {
        return this.g.getTempFilePath();
    }

    public boolean isResuming() {
        return this.d;
    }

    public Throwable getThrowable() {
        return this.e;
    }

    public int getRetryingTimes() {
        return this.f;
    }

    public void run() {
        Process.setThreadPriority(10);
        this.i = false;
        this.h = true;
        if (this.g == null) {
            FileDownloadLog.e(this, "start runnable but model == null?? %s", Integer.valueOf(this.r));
            this.g = this.j.find(this.r);
            if (this.g == null) {
                FileDownloadLog.e(this, "start runnable but downloadMode == null?? %s", Integer.valueOf(this.r));
                return;
            }
        }
        try {
            if (this.g.getStatus() != (byte) 1) {
                if (this.g.getStatus() != (byte) -2) {
                    a(new RuntimeException(FileDownloadUtils.formatString("Task[%d] can't start the download runnable, because its status is %d not %d", Integer.valueOf(this.r), Byte.valueOf(this.g.getStatus()), Byte.valueOf((byte) 1))));
                } else if (FileDownloadLog.NEED_LOG) {
                    FileDownloadLog.d(this, "High concurrent cause, start runnable but already paused %d", Integer.valueOf(this.r));
                }
                this.h = false;
            } else if (!this.q || FileDownloadUtils.checkPermission("android.permission.ACCESS_NETWORK_STATE")) {
                c();
                a(this.g);
                this.h = false;
            } else {
                a(new FileDownloadGiveUpRetryException(FileDownloadUtils.formatString("Task[%d] can't start the download runnable, because this task require wifi, but user application nor current process has %s, so we can't check whether the network type connection.", Integer.valueOf(this.r), "android.permission.ACCESS_NETWORK_STATE")));
                this.h = false;
            }
        } finally {
            this.h = false;
        }
    }

    private void a(FileDownloadModel fileDownloadModel) {
        Throwable fileDownloadHttpException;
        FileDownloadConnection fileDownloadConnection = null;
        Object obj = null;
        int i = 0;
        while (true) {
            FileDownloadConnection create;
            int i2 = this.r;
            Object obj2;
            int i3;
            try {
                if (d()) {
                    if (FileDownloadLog.NEED_LOG) {
                        FileDownloadLog.d(this, "already canceled %d %d", Integer.valueOf(i2), Byte.valueOf(fileDownloadModel.getStatus()));
                    }
                    b();
                    if (fileDownloadConnection != null) {
                        fileDownloadConnection.ending();
                        return;
                    }
                    return;
                }
                if (FileDownloadLog.NEED_LOG) {
                    FileDownloadLog.d(FileDownloadRunnable.class, "start download %s %s", Integer.valueOf(i2), fileDownloadModel.getUrl());
                }
                e();
                create = this.t.create(fileDownloadModel.getUrl());
                try {
                    a(create);
                    Map requestHeaderFields = create.getRequestHeaderFields();
                    if (FileDownloadLog.NEED_LOG) {
                        FileDownloadLog.d(this, "%s request header %s", Integer.valueOf(i2), requestHeaderFields);
                    }
                    create.execute();
                    int responseCode = create.getResponseCode();
                    Object obj3 = (responseCode == 200 || responseCode == 0) ? 1 : null;
                    boolean z = (responseCode == HttpStatus.SC_PARTIAL_CONTENT || responseCode == 1) && this.c;
                    if (this.c && !z) {
                        FileDownloadLog.d(this, "want to resume from the breakpoint[%d], but the response status code is[%d]", Long.valueOf(fileDownloadModel.getSoFar()), Integer.valueOf(responseCode));
                    }
                    if (obj3 != null || z) {
                        long soFar;
                        long total = fileDownloadModel.getTotal();
                        String responseHeaderField = create.getResponseHeaderField("Transfer-Encoding");
                        if (obj3 != null || total <= 0) {
                            if (responseHeaderField == null) {
                                total = FileDownloadUtils.convertContentLengthString(create.getResponseHeaderField("Content-Length"));
                            } else {
                                total = -1;
                            }
                        }
                        if (total < 0) {
                            obj3 = (responseHeaderField == null || !responseHeaderField.equals(HTTP.CHUNK_CODING)) ? null : 1;
                            if (obj3 == null) {
                                if (FileDownloadProperties.getImpl().HTTP_LENIENT) {
                                    total = -1;
                                    if (FileDownloadLog.NEED_LOG) {
                                        FileDownloadLog.d(this, "%d response header is not legal but HTTP lenient is true, so handle as the case of transfer encoding chunk", Integer.valueOf(i2));
                                    }
                                } else {
                                    throw new FileDownloadGiveUpRetryException("can't know the size of the download file, and its Transfer-Encoding is not Chunked either.\nyou can ignore such exception by add http.lenient=true to the filedownloader.properties");
                                }
                            }
                        }
                        if (z) {
                            soFar = fileDownloadModel.getSoFar();
                        } else {
                            soFar = 0;
                        }
                        a(z, total, b(create), c(create));
                        if (fileDownloadModel.isPathAsDirectory()) {
                            String targetFilePath = fileDownloadModel.getTargetFilePath();
                            int generateId = FileDownloadUtils.generateId(fileDownloadModel.getUrl(), targetFilePath);
                            if (FileDownloadHelper.inspectAndInflowDownloaded(i2, targetFilePath, this.b, false)) {
                                this.j.remove(i2);
                                if (create != null) {
                                    create.ending();
                                    return;
                                }
                                return;
                            }
                            FileDownloadModel find = this.j.find(generateId);
                            if (find != null) {
                                if (FileDownloadHelper.inspectAndInflowDownloading(i2, find, this.p, false)) {
                                    this.j.remove(i2);
                                    if (create != null) {
                                        create.ending();
                                        return;
                                    }
                                    return;
                                }
                                this.j.remove(generateId);
                                h();
                                if (c.isBreakpointAvailable(generateId, find)) {
                                    fileDownloadModel.setSoFar(find.getSoFar());
                                    fileDownloadModel.setTotal(find.getTotal());
                                    fileDownloadModel.setETag(find.getETag());
                                    this.j.update(fileDownloadModel);
                                    if (create != null) {
                                        create.ending();
                                        fileDownloadConnection = create;
                                    } else {
                                        fileDownloadConnection = create;
                                    }
                                }
                            }
                            if (FileDownloadHelper.inspectAndInflowConflictPath(i2, fileDownloadModel.getSoFar(), getTempFilePath(), targetFilePath, this.p)) {
                                this.j.remove(i2);
                                if (create != null) {
                                    create.ending();
                                    return;
                                }
                                return;
                            }
                        }
                        if (!a(create, z, soFar, total)) {
                            obj2 = obj;
                            i3 = i;
                        } else if (create != null) {
                            create.ending();
                            return;
                        } else {
                            return;
                        }
                    }
                    fileDownloadHttpException = new FileDownloadHttpException(responseCode, requestHeaderFields, create.getResponseHeaderFields());
                    if (obj != null) {
                        throw fileDownloadHttpException;
                    }
                    obj = 1;
                    switch (responseCode) {
                        case HttpStatus.SC_REQUESTED_RANGE_NOT_SATISFIABLE /*416*/:
                            f();
                            FileDownloadLog.w(FileDownloadRunnable.class, "%d response code %d, range[%d] isn't make sense, so delete the dirty file[%s], and try to redownload it from byte-0.", Integer.valueOf(i2), Integer.valueOf(responseCode), Long.valueOf(fileDownloadModel.getSoFar()), fileDownloadModel.getTempFilePath());
                            int i4 = i + 1;
                            try {
                                a(fileDownloadHttpException, i);
                                i3 = i4;
                                i4 = 1;
                                break;
                            } catch (Throwable th) {
                                fileDownloadHttpException = th;
                                i = i4;
                                i4 = 1;
                                try {
                                    i3 = i + 1;
                                    if (this.k > i) {
                                        break;
                                    }
                                    a(fileDownloadHttpException);
                                    if (create != null) {
                                        create.ending();
                                        return;
                                    }
                                    return;
                                } catch (Throwable th2) {
                                    fileDownloadHttpException = th2;
                                    break;
                                }
                            }
                        default:
                            throw fileDownloadHttpException;
                    }
                    if (create != null) {
                        create.ending();
                        fileDownloadConnection = create;
                        obj = obj2;
                        i = i3;
                    }
                } catch (Throwable th3) {
                    fileDownloadHttpException = th3;
                    obj2 = obj;
                }
                fileDownloadConnection = create;
                obj = obj2;
                i = i3;
            } catch (Throwable th4) {
                create = fileDownloadConnection;
                fileDownloadHttpException = th4;
            }
        }
        if (create != null) {
            create.ending();
        }
        throw fileDownloadHttpException;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean a(com.liulishuo.filedownloader.connection.FileDownloadConnection r10, boolean r11, long r12, long r14) throws java.lang.Throwable {
        /*
        r9 = this;
        r1 = 0;
        r6 = r9.a(r11, r14);
        r7 = r10.getInputStream();	 Catch:{ all -> 0x00b2 }
        r0 = 4096; // 0x1000 float:5.74E-42 double:2.0237E-320;
        r8 = new byte[r0];	 Catch:{ all -> 0x0099 }
        r0 = r9.a;	 Catch:{ all -> 0x0099 }
        r0 = (long) r0;	 Catch:{ all -> 0x0099 }
        r0 = r9.a(r14, r0);	 Catch:{ all -> 0x0099 }
        r9.o = r0;	 Catch:{ all -> 0x0099 }
        r0 = r12;
    L_0x0017:
        r2 = r7.read(r8);	 Catch:{ all -> 0x0099 }
        r3 = -1;
        if (r2 != r3) goto L_0x0047;
    L_0x001e:
        r2 = -1;
        r2 = (r14 > r2 ? 1 : (r14 == r2 ? 0 : -1));
        if (r2 != 0) goto L_0x0025;
    L_0x0024:
        r14 = r0;
    L_0x0025:
        r2 = (r0 > r14 ? 1 : (r0 == r14 ? 0 : -1));
        if (r2 != 0) goto L_0x007c;
    L_0x0029:
        r9.a();	 Catch:{ all -> 0x0099 }
        r0 = r9.j;	 Catch:{ all -> 0x0099 }
        r1 = r9.r;	 Catch:{ all -> 0x0099 }
        r0.remove(r1);	 Catch:{ all -> 0x0099 }
        r9.a(r14);	 Catch:{ all -> 0x0099 }
        r0 = 1;
        if (r7 == 0) goto L_0x003c;
    L_0x0039:
        r7.close();
    L_0x003c:
        if (r6 == 0) goto L_0x0041;
    L_0x003e:
        r6.sync();	 Catch:{ all -> 0x0075 }
    L_0x0041:
        if (r6 == 0) goto L_0x0046;
    L_0x0043:
        r6.close();
    L_0x0046:
        return r0;
    L_0x0047:
        r3 = 0;
        r6.write(r8, r3, r2);	 Catch:{ all -> 0x0099 }
        r2 = (long) r2;	 Catch:{ all -> 0x0099 }
        r2 = r2 + r0;
        r1 = r9;
        r4 = r14;
        r1.a(r2, r4, r6);	 Catch:{ all -> 0x0099 }
        r0 = r9.d();	 Catch:{ all -> 0x0099 }
        if (r0 == 0) goto L_0x0073;
    L_0x0058:
        r9.b();	 Catch:{ all -> 0x0099 }
        r0 = 1;
        if (r7 == 0) goto L_0x0061;
    L_0x005e:
        r7.close();
    L_0x0061:
        if (r6 == 0) goto L_0x0066;
    L_0x0063:
        r6.sync();	 Catch:{ all -> 0x006c }
    L_0x0066:
        if (r6 == 0) goto L_0x0046;
    L_0x0068:
        r6.close();
        goto L_0x0046;
    L_0x006c:
        r0 = move-exception;
        if (r6 == 0) goto L_0x0072;
    L_0x006f:
        r6.close();
    L_0x0072:
        throw r0;
    L_0x0073:
        r0 = r2;
        goto L_0x0017;
    L_0x0075:
        r0 = move-exception;
        if (r6 == 0) goto L_0x007b;
    L_0x0078:
        r6.close();
    L_0x007b:
        throw r0;
    L_0x007c:
        r2 = new java.lang.RuntimeException;	 Catch:{ all -> 0x0099 }
        r3 = "sofar[%d] not equal total[%d]";
        r4 = 2;
        r4 = new java.lang.Object[r4];	 Catch:{ all -> 0x0099 }
        r5 = 0;
        r0 = java.lang.Long.valueOf(r0);	 Catch:{ all -> 0x0099 }
        r4[r5] = r0;	 Catch:{ all -> 0x0099 }
        r0 = 1;
        r1 = java.lang.Long.valueOf(r14);	 Catch:{ all -> 0x0099 }
        r4[r0] = r1;	 Catch:{ all -> 0x0099 }
        r0 = com.liulishuo.filedownloader.util.FileDownloadUtils.formatString(r3, r4);	 Catch:{ all -> 0x0099 }
        r2.<init>(r0);	 Catch:{ all -> 0x0099 }
        throw r2;	 Catch:{ all -> 0x0099 }
    L_0x0099:
        r0 = move-exception;
        r1 = r7;
    L_0x009b:
        if (r1 == 0) goto L_0x00a0;
    L_0x009d:
        r1.close();
    L_0x00a0:
        if (r6 == 0) goto L_0x00a5;
    L_0x00a2:
        r6.sync();	 Catch:{ all -> 0x00ab }
    L_0x00a5:
        if (r6 == 0) goto L_0x00aa;
    L_0x00a7:
        r6.close();
    L_0x00aa:
        throw r0;
    L_0x00ab:
        r0 = move-exception;
        if (r6 == 0) goto L_0x00b1;
    L_0x00ae:
        r6.close();
    L_0x00b1:
        throw r0;
    L_0x00b2:
        r0 = move-exception;
        goto L_0x009b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liulishuo.filedownloader.services.FileDownloadRunnable.a(com.liulishuo.filedownloader.connection.FileDownloadConnection, boolean, long, long):boolean");
    }

    private void a() {
        String tempFilePath = this.g.getTempFilePath();
        String targetFilePath = this.g.getTargetFilePath();
        File file = new File(tempFilePath);
        try {
            File file2 = new File(targetFilePath);
            if (file2.exists()) {
                long length = file2.length();
                if (file2.delete()) {
                    FileDownloadLog.w(this, "The target file([%s], [%d]) will be replaced with the new downloaded file[%d]", targetFilePath, Long.valueOf(length), Long.valueOf(file.length()));
                } else {
                    throw new IllegalStateException(FileDownloadUtils.formatString("Can't delete the old file([%s], [%d]), so can't replace it with the new downloaded one.", targetFilePath, Long.valueOf(length)));
                }
            }
            if (!file.renameTo(file2)) {
                throw new IllegalStateException(FileDownloadUtils.formatString("Can't rename the  temp downloaded file(%s) to the target file(%s)", tempFilePath, targetFilePath));
            } else if (file.exists() && !file.delete()) {
                FileDownloadLog.w(this, "delete the temp file(%s) failed, on completed downloading.", tempFilePath);
            }
        } catch (Throwable th) {
            if (file.exists() && !file.delete()) {
                FileDownloadLog.w(this, "delete the temp file(%s) failed, on completed downloading.", tempFilePath);
            }
        }
    }

    private long a(long j, long j2) {
        if (j2 <= 0) {
            return -1;
        }
        if (j == -1) {
            return 1;
        }
        long j3 = j / (j2 + 1);
        if (j3 > 0) {
            return j3;
        }
        return 1;
    }

    private void a(FileDownloadConnection fileDownloadConnection) {
        if (this.l != null) {
            HashMap headers = this.l.getHeaders();
            if (headers != null) {
                if (FileDownloadLog.NEED_LOG) {
                    FileDownloadLog.v(this, "%d add outside header: %s", Integer.valueOf(this.r), headers);
                }
                for (Entry entry : headers.entrySet()) {
                    String str = (String) entry.getKey();
                    List<String> list = (List) entry.getValue();
                    if (list != null) {
                        for (String addHeader : list) {
                            fileDownloadConnection.addHeader(str, addHeader);
                        }
                    }
                }
            }
        }
        Object eTag = this.g.getETag();
        long soFar = this.g.getSoFar();
        if (this.c && !fileDownloadConnection.dispatchAddResumeOffset(eTag, soFar)) {
            if (!TextUtils.isEmpty(eTag)) {
                fileDownloadConnection.addHeader("If-Match", eTag);
            }
            fileDownloadConnection.addHeader("Range", FileDownloadUtils.formatString("bytes=%d-", Long.valueOf(soFar)));
        }
    }

    private String b(FileDownloadConnection fileDownloadConnection) {
        if (fileDownloadConnection == null) {
            throw new RuntimeException("connection is null when findEtag");
        }
        String responseHeaderField = fileDownloadConnection.getResponseHeaderField("Etag");
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "etag find by header %d %s", Integer.valueOf(this.r), responseHeaderField);
        }
        return responseHeaderField;
    }

    private String c(FileDownloadConnection fileDownloadConnection) {
        if (!this.g.isPathAsDirectory() || this.g.getFilename() != null) {
            return null;
        }
        String parseContentDisposition = FileDownloadUtils.parseContentDisposition(fileDownloadConnection.getResponseHeaderField(MIME.CONTENT_DISPOSITION));
        if (TextUtils.isEmpty(parseContentDisposition)) {
            return FileDownloadUtils.generateFileName(this.g.getUrl());
        }
        return parseContentDisposition;
    }

    public void cancelRunnable() {
        this.m = true;
        b();
    }

    private void a(boolean z, long j, String str, String str2) {
        this.j.updateConnected(this.g, j, str, str2);
        this.d = z;
        a(this.g.getStatus());
    }

    private void a(long j, long j2, FileDownloadOutputStream fileDownloadOutputStream) {
        if (j != j2) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            long j3 = elapsedRealtime - this.x;
            if (j - this.w <= ((long) FileDownloadUtils.getMinProgressStep()) || j3 <= FileDownloadUtils.getMinProgressTime()) {
                if (this.g.getStatus() != (byte) 3) {
                    this.g.setStatus((byte) 3);
                }
                this.g.setSoFar(j);
            } else {
                try {
                    fileDownloadOutputStream.sync();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                this.j.updateProgress(this.g, j);
                this.w = j;
                this.x = elapsedRealtime;
            }
            long j4 = j - this.u;
            j3 = elapsedRealtime - this.v;
            if (this.o != -1 && j4 >= this.o && j3 >= ((long) this.n)) {
                this.v = elapsedRealtime;
                this.u = j;
                if (FileDownloadLog.NEED_LOG) {
                    FileDownloadLog.d(this, "On progress %d %d %d", Integer.valueOf(this.r), Long.valueOf(j), Long.valueOf(j2));
                }
                a(this.g.getStatus());
            }
        }
    }

    private void a(Throwable th, int i) {
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "On retry %d %s %d %d", Integer.valueOf(this.r), th, Integer.valueOf(i), Integer.valueOf(this.k));
        }
        Throwable b = b(th);
        this.j.updateRetry(this.g, b);
        this.e = b;
        this.f = i;
        a(this.g.getStatus());
    }

    private void a(Throwable th) {
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "On error %d %s", Integer.valueOf(this.r), th);
        }
        Throwable b = b(th);
        if (b instanceof SQLiteFullException) {
            a((SQLiteFullException) b);
        } else {
            try {
                this.j.updateError(this.g, b, this.g.getSoFar());
                b = th;
            } catch (SQLiteFullException e) {
                b = e;
                a((SQLiteFullException) b);
            }
        }
        this.e = b;
        a(this.g.getStatus());
    }

    private void a(SQLiteFullException sQLiteFullException) {
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "the data of the task[%d] is dirty, because the SQLite full exception[%s], so remove it from the database directly.", Integer.valueOf(this.r), sQLiteFullException.toString());
        }
        this.g.setErrMsg(sQLiteFullException.toString());
        this.g.setStatus((byte) -1);
        this.j.remove(this.r);
    }

    private void a(long j) {
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "On completed %d %d %B", Integer.valueOf(this.r), Long.valueOf(j), Boolean.valueOf(this.m));
        }
        this.j.updateComplete(this.g, j);
        a(this.g.getStatus());
    }

    private void b() {
        this.h = false;
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "On paused %d %d %d", Integer.valueOf(this.r), Long.valueOf(this.g.getSoFar()), Long.valueOf(this.g.getTotal()));
        }
        this.j.updatePause(this.g, this.g.getSoFar());
        a(this.g.getStatus());
    }

    private void c() {
        this.g.setStatus((byte) 6);
        a(this.g.getStatus());
    }

    public void onPending() {
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "On resume %d", Integer.valueOf(this.r));
        }
        this.i = true;
        this.j.updatePending(this.g);
        a(this.g.getStatus());
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(byte r6) {
        /*
        r5 = this;
        r1 = r5.y;
        monitor-enter(r1);
        r0 = r5.g;	 Catch:{ all -> 0x0032 }
        r0 = r0.getStatus();	 Catch:{ all -> 0x0032 }
        r2 = -2;
        if (r0 != r2) goto L_0x0023;
    L_0x000c:
        r0 = com.liulishuo.filedownloader.util.FileDownloadLog.NEED_LOG;	 Catch:{ all -> 0x0032 }
        if (r0 == 0) goto L_0x0021;
    L_0x0010:
        r0 = "High concurrent cause, Already paused and we don't need to call-back to Task in here, %d";
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ all -> 0x0032 }
        r3 = 0;
        r4 = r5.r;	 Catch:{ all -> 0x0032 }
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ all -> 0x0032 }
        r2[r3] = r4;	 Catch:{ all -> 0x0032 }
        com.liulishuo.filedownloader.util.FileDownloadLog.d(r5, r0, r2);	 Catch:{ all -> 0x0032 }
    L_0x0021:
        monitor-exit(r1);	 Catch:{ all -> 0x0032 }
    L_0x0022:
        return;
    L_0x0023:
        r0 = com.liulishuo.filedownloader.message.MessageSnapshotFlow.getImpl();	 Catch:{ all -> 0x0032 }
        r2 = r5.g;	 Catch:{ all -> 0x0032 }
        r2 = com.liulishuo.filedownloader.message.MessageSnapshotTaker.take(r6, r2, r5);	 Catch:{ all -> 0x0032 }
        r0.inflow(r2);	 Catch:{ all -> 0x0032 }
        monitor-exit(r1);	 Catch:{ all -> 0x0032 }
        goto L_0x0022;
    L_0x0032:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0032 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liulishuo.filedownloader.services.FileDownloadRunnable.a(byte):void");
    }

    private boolean d() {
        if (this.m) {
            return true;
        }
        if (!this.q || FileDownloadUtils.isNetworkOnWifiType()) {
            return false;
        }
        throw new FileDownloadNetworkPolicyException();
    }

    private FileDownloadOutputStream a(boolean z, long j) throws IOException, IllegalAccessException {
        String tempFilePath = this.g.getTempFilePath();
        if (TextUtils.isEmpty(tempFilePath)) {
            throw new RuntimeException("found invalid internal destination path, empty");
        } else if (FileDownloadUtils.isFilenameValid(tempFilePath)) {
            File file = new File(tempFilePath);
            if (file.exists() && file.isDirectory()) {
                throw new RuntimeException(FileDownloadUtils.formatString("found invalid internal destination path[%s], & path is directory[%B]", tempFilePath, Boolean.valueOf(file.isDirectory())));
            } else if (file.exists() || file.createNewFile()) {
                FileDownloadOutputStream create = this.s.create(file);
                if (j > 0) {
                    long length = file.length();
                    long j2 = j - length;
                    long freeSpaceBytes = FileDownloadUtils.getFreeSpaceBytes(tempFilePath);
                    if (freeSpaceBytes < j2) {
                        create.close();
                        throw new FileDownloadOutOfSpaceException(freeSpaceBytes, j2, length);
                    } else if (!FileDownloadProperties.getImpl().FILE_NON_PRE_ALLOCATION) {
                        create.setLength(j);
                    }
                }
                if (z && this.s.supportSeek()) {
                    create.seek(this.g.getSoFar());
                }
                return create;
            } else {
                throw new IOException(FileDownloadUtils.formatString("create new file error  %s", file.getAbsolutePath()));
            }
        } else {
            throw new RuntimeException(FileDownloadUtils.formatString("found invalid internal destination filename %s", tempFilePath));
        }
    }

    private void e() {
        boolean supportSeek = this.s.supportSeek();
        if (c.isBreakpointAvailable(this.r, this.g, Boolean.valueOf(supportSeek))) {
            this.c = true;
            if (!supportSeek) {
                this.g.setSoFar(new File(this.g.getTempFilePath()).length());
                return;
            }
            return;
        }
        this.c = false;
        f();
    }

    private void f() {
        g();
        h();
    }

    private void g() {
        String tempFilePath = this.g.getTempFilePath();
        if (tempFilePath != null) {
            File file = new File(tempFilePath);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    private void h() {
        String targetFilePath = this.g.getTargetFilePath();
        if (targetFilePath != null) {
            File file = new File(targetFilePath);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    private Throwable b(Throwable th) {
        String tempFilePath = this.g.getTempFilePath();
        if ((this.g.getTotal() != -1 && !FileDownloadProperties.getImpl().FILE_NON_PRE_ALLOCATION) || !(th instanceof IOException) || !new File(tempFilePath).exists()) {
            return th;
        }
        long freeSpaceBytes = FileDownloadUtils.getFreeSpaceBytes(tempFilePath);
        if (freeSpaceBytes > 4096) {
            return th;
        }
        long j = 0;
        File file = new File(tempFilePath);
        if (file.exists()) {
            j = file.length();
        } else {
            FileDownloadLog.e(FileDownloadRunnable.class, th, "Exception with: free space isn't enough, and the target file not exist.", new Object[0]);
        }
        if (VERSION.SDK_INT >= 9) {
            return new FileDownloadOutOfSpaceException(freeSpaceBytes, 4096, j, th);
        }
        return new FileDownloadOutOfSpaceException(freeSpaceBytes, 4096, j);
    }
}
