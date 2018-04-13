package com.qiniu.android.storage;

import com.qiniu.android.http.Client;
import com.qiniu.android.http.PostArgs;
import com.qiniu.android.http.ProgressHandler;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.utils.AndroidNetwork;
import com.qiniu.android.utils.Crc32;
import com.qiniu.android.utils.StringMap;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import qsbk.app.activity.pay.PayPWDUniversalActivity;

final class b {
    static void a(Client client, Configuration configuration, byte[] bArr, String str, UpToken upToken, UpCompletionHandler upCompletionHandler, UploadOptions uploadOptions) {
        a(bArr, null, str, upToken, upCompletionHandler, uploadOptions, client, configuration);
    }

    static void a(Client client, Configuration configuration, File file, String str, UpToken upToken, UpCompletionHandler upCompletionHandler, UploadOptions uploadOptions) {
        a(null, file, str, upToken, upCompletionHandler, uploadOptions, client, configuration);
    }

    private static void a(byte[] bArr, File file, String str, UpToken upToken, UpCompletionHandler upCompletionHandler, UploadOptions uploadOptions, Client client, Configuration configuration) {
        StringMap stringMap = new StringMap();
        PostArgs postArgs = new PostArgs();
        if (str != null) {
            stringMap.put(PayPWDUniversalActivity.KEY, str);
            postArgs.fileName = str;
        } else {
            postArgs.fileName = "?";
        }
        if (file != null) {
            postArgs.fileName = file.getName();
        }
        stringMap.put("token", upToken.token);
        UploadOptions a = uploadOptions != null ? uploadOptions : UploadOptions.a();
        stringMap.putFileds(a.a);
        if (a.c) {
            long j = 0;
            if (file != null) {
                try {
                    j = Crc32.file(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                j = Crc32.bytes(bArr);
            }
            stringMap.put("crc32", "" + j);
        }
        ProgressHandler cVar = new c(a, str);
        postArgs.data = bArr;
        postArgs.file = file;
        postArgs.mimeType = a.b;
        postArgs.params = stringMap;
        client.asyncMultipartPost(configuration.zone.upHost(upToken.token).address.toString(), postArgs, upToken, cVar, new d(a, upCompletionHandler, str, upToken, configuration, client, postArgs, cVar), a.e);
    }

    public static ResponseInfo syncUpload(Client client, Configuration configuration, byte[] bArr, String str, UpToken upToken, UploadOptions uploadOptions) {
        try {
            return a(client, configuration, bArr, null, str, upToken, uploadOptions);
        } catch (Exception e) {
            return ResponseInfo.create(null, 0, "", "", "", "", "", "", 0, 0.0d, 0, e.getMessage(), upToken);
        }
    }

    public static ResponseInfo syncUpload(Client client, Configuration configuration, File file, String str, UpToken upToken, UploadOptions uploadOptions) {
        try {
            return a(client, configuration, null, file, str, upToken, uploadOptions);
        } catch (Exception e) {
            return ResponseInfo.create(null, 0, "", "", "", "", "", "", 0, 0.0d, 0, e.getMessage(), upToken);
        }
    }

    private static ResponseInfo a(Client client, Configuration configuration, byte[] bArr, File file, String str, UpToken upToken, UploadOptions uploadOptions) {
        StringMap stringMap = new StringMap();
        PostArgs postArgs = new PostArgs();
        if (str != null) {
            stringMap.put(PayPWDUniversalActivity.KEY, str);
            postArgs.fileName = str;
        } else {
            postArgs.fileName = "?";
        }
        if (file != null) {
            postArgs.fileName = file.getName();
        }
        stringMap.put("token", upToken.token);
        if (uploadOptions == null) {
            uploadOptions = UploadOptions.a();
        }
        stringMap.putFileds(uploadOptions.a);
        if (uploadOptions.c) {
            long j = 0;
            if (file != null) {
                try {
                    j = Crc32.file(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                j = Crc32.bytes(bArr);
            }
            stringMap.put("crc32", "" + j);
        }
        postArgs.data = bArr;
        postArgs.file = file;
        postArgs.mimeType = uploadOptions.b;
        postArgs.params = stringMap;
        ResponseInfo syncMultipartPost = client.syncMultipartPost(configuration.zone.upHost(upToken.token).address.toString(), postArgs, upToken);
        if (syncMultipartPost.isOK()) {
            return syncMultipartPost;
        }
        if (!syncMultipartPost.needRetry() && (!syncMultipartPost.isNotQiniu() || upToken.hasReturnUrl())) {
            return syncMultipartPost;
        }
        if (syncMultipartPost.isNetworkBroken() && !AndroidNetwork.isNetWorkReady()) {
            uploadOptions.f.waitReady();
            if (!AndroidNetwork.isNetWorkReady()) {
                return syncMultipartPost;
            }
        }
        URI uri = configuration.zone.upHost(upToken.token).address;
        if (configuration.zone.upHostBackup(upToken.token) != null && (syncMultipartPost.needSwitchServer() || syncMultipartPost.isNotQiniu())) {
            uri = configuration.zone.upHostBackup(upToken.token).address;
        }
        return client.syncMultipartPost(uri.toString(), postArgs, upToken);
    }
}
