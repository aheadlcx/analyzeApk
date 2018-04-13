package com.qiniu.android.storage;

import com.qiniu.android.http.Client;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration.Builder;
import com.qiniu.android.utils.AsyncRun;
import java.io.File;

public final class UploadManager {
    private final Configuration a;
    private final Client b;

    public UploadManager() {
        this(new Builder().build());
    }

    public UploadManager(Configuration configuration) {
        this.a = configuration;
        this.b = new Client(configuration.proxy, configuration.connectTimeout, configuration.responseTimeout, configuration.urlConverter, configuration.dns);
    }

    public UploadManager(Recorder recorder, KeyGenerator keyGenerator) {
        this(new Builder().recorder(recorder, keyGenerator).build());
    }

    public UploadManager(Recorder recorder) {
        this(recorder, null);
    }

    private static boolean a(String str, byte[] bArr, File file, String str2, UpToken upToken, UpCompletionHandler upCompletionHandler) {
        ResponseInfo responseInfo = null;
        if (upCompletionHandler == null) {
            throw new IllegalArgumentException("no UpCompletionHandler");
        }
        String str3;
        if (file == null && bArr == null) {
            str3 = "no input data";
        } else if (str2 == null || str2.equals("")) {
            str3 = "no token";
        } else {
            str3 = null;
        }
        if (str3 != null) {
            responseInfo = ResponseInfo.invalidArgument(str3, upToken);
        } else if (upToken == UpToken.NULL || upToken == null) {
            responseInfo = ResponseInfo.invalidToken("invalid token");
        } else if ((file != null && file.length() == 0) || (bArr != null && bArr.length == 0)) {
            responseInfo = ResponseInfo.zeroSize(upToken);
        }
        if (responseInfo == null) {
            return false;
        }
        AsyncRun.runInMain(new k(upCompletionHandler, str, responseInfo));
        return true;
    }

    public void put(byte[] bArr, String str, String str2, UpCompletionHandler upCompletionHandler, UploadOptions uploadOptions) {
        UpToken parse = UpToken.parse(str2);
        if (!a(str, bArr, null, str2, parse, upCompletionHandler)) {
            this.a.zone.preQuery(str2, new n(this, bArr, str, parse, new l(this, upCompletionHandler), uploadOptions));
        }
    }

    public void put(String str, String str2, String str3, UpCompletionHandler upCompletionHandler, UploadOptions uploadOptions) {
        put(new File(str), str2, str3, upCompletionHandler, uploadOptions);
    }

    public void put(File file, String str, String str2, UpCompletionHandler upCompletionHandler, UploadOptions uploadOptions) {
        UpToken parse = UpToken.parse(str2);
        if (!a(str, null, file, str2, parse, upCompletionHandler)) {
            this.a.zone.preQuery(str2, new r(this, file, str, parse, new p(this, upCompletionHandler), uploadOptions));
        }
    }

    public ResponseInfo syncPut(byte[] bArr, String str, String str2, UploadOptions uploadOptions) {
        UpToken parse = UpToken.parse(str2);
        ResponseInfo a = a(str, bArr, null, str2, parse);
        return a != null ? a : b.syncUpload(this.b, this.a, bArr, str, parse, uploadOptions);
    }

    public ResponseInfo syncPut(File file, String str, String str2, UploadOptions uploadOptions) {
        UpToken parse = UpToken.parse(str2);
        ResponseInfo a = a(str, null, file, str2, parse);
        return a != null ? a : b.syncUpload(this.b, this.a, file, str, parse, uploadOptions);
    }

    public ResponseInfo syncPut(String str, String str2, String str3, UploadOptions uploadOptions) {
        return syncPut(new File(str), str2, str3, uploadOptions);
    }

    private static ResponseInfo a(String str, byte[] bArr, File file, String str2, UpToken upToken) {
        String str3;
        if (file == null && bArr == null) {
            str3 = "no input data";
        } else if (str2 == null || str2.equals("")) {
            str3 = "no token";
        } else {
            str3 = null;
        }
        if (str3 != null) {
            return ResponseInfo.invalidArgument(str3, upToken);
        }
        if (upToken == UpToken.NULL || upToken == null) {
            return ResponseInfo.invalidToken("invalid token");
        }
        if ((file == null || file.length() != 0) && (bArr == null || bArr.length != 0)) {
            return null;
        }
        return ResponseInfo.zeroSize(upToken);
    }
}
