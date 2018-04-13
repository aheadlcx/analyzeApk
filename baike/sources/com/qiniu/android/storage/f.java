package com.qiniu.android.storage;

import com.qiniu.android.http.Client;
import com.qiniu.android.http.CompletionHandler;
import com.qiniu.android.http.ProgressHandler;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.utils.Crc32;
import com.qiniu.android.utils.StringMap;
import com.qiniu.android.utils.StringUtils;
import com.qiniu.android.utils.UrlSafeBase64;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.xiaomi.mipush.sdk.Constants;
import java.io.File;
import java.io.RandomAccessFile;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Locale;
import java.util.Map.Entry;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.core.model.CustomButton;

final class f implements Runnable {
    private final long a;
    private final String b;
    private final UpCompletionHandler c;
    private final UploadOptions d;
    private final Client e;
    private final Configuration f;
    private final byte[] g;
    private final String[] h;
    private final StringMap i;
    private final long j;
    private final String k;
    private RandomAccessFile l = null;
    private File m;
    private long n;
    private UpToken o;

    f(Client client, Configuration configuration, File file, String str, UpToken upToken, UpCompletionHandler upCompletionHandler, UploadOptions uploadOptions, String str2) {
        this.e = client;
        this.f = configuration;
        this.m = file;
        this.k = str2;
        this.a = file.length();
        this.b = str;
        this.i = new StringMap().put("Authorization", "UpToken " + upToken.token);
        this.c = new g(this, upCompletionHandler);
        if (uploadOptions == null) {
            uploadOptions = UploadOptions.a();
        }
        this.d = uploadOptions;
        this.g = new byte[configuration.chunkSize];
        this.h = new String[((int) (((this.a + 4194304) - 1) / 4194304))];
        this.j = file.lastModified();
        this.o = upToken;
    }

    private static boolean c(ResponseInfo responseInfo, JSONObject jSONObject) {
        return responseInfo.statusCode == 200 && responseInfo.error == null && (responseInfo.hasReqId() || a(jSONObject));
    }

    private static boolean a(JSONObject jSONObject) {
        try {
            jSONObject.getString("ctx");
            jSONObject.getLong("crc32");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean d(ResponseInfo responseInfo, JSONObject jSONObject) {
        return responseInfo.statusCode < 500 && responseInfo.statusCode >= 200 && !responseInfo.hasReqId() && !a(jSONObject);
    }

    public void run() {
        long b = b();
        try {
            this.l = new RandomAccessFile(this.m, CustomButton.POSITION_RIGHT);
            a(b, 0, this.f.zone.upHost(this.o.token).address);
        } catch (Exception e) {
            e.printStackTrace();
            this.c.complete(this.b, ResponseInfo.fileError(e, this.o), null);
        }
    }

    private void a(URI uri, long j, int i, int i2, ProgressHandler progressHandler, CompletionHandler completionHandler, UpCancellationSignal upCancellationSignal) {
        String format = String.format(Locale.ENGLISH, "/mkblk/%d", new Object[]{Integer.valueOf(i)});
        try {
            this.l.seek(j);
            this.l.read(this.g, 0, i2);
            this.n = Crc32.bytes(this.g, 0, i2);
            a(a(uri, format), this.g, 0, i2, progressHandler, completionHandler, upCancellationSignal);
        } catch (Exception e) {
            this.c.complete(this.b, ResponseInfo.fileError(e, this.o), null);
        }
    }

    private void a(URI uri, long j, int i, String str, ProgressHandler progressHandler, CompletionHandler completionHandler, UpCancellationSignal upCancellationSignal) {
        int i2 = (int) (j % 4194304);
        String format = String.format(Locale.ENGLISH, "/bput/%s/%d", new Object[]{str, Integer.valueOf(i2)});
        try {
            this.l.seek(j);
            this.l.read(this.g, 0, i);
            this.n = Crc32.bytes(this.g, 0, i);
            a(a(uri, format), this.g, 0, i, progressHandler, completionHandler, upCancellationSignal);
        } catch (Exception e) {
            this.c.complete(this.b, ResponseInfo.fileError(e, this.o), null);
        }
    }

    private void a(URI uri, CompletionHandler completionHandler, UpCancellationSignal upCancellationSignal) {
        URI uri2;
        String format = String.format(Locale.ENGLISH, "/mimeType/%s/fname/%s", new Object[]{UrlSafeBase64.encodeToString(this.d.b), UrlSafeBase64.encodeToString(this.m.getName())});
        String str = "";
        if (this.b != null) {
            Object format2 = String.format("/key/%s", new Object[]{UrlSafeBase64.encodeToString(this.b)});
        } else {
            String str2 = str;
        }
        str = "";
        if (this.d.a.size() != 0) {
            String[] strArr = new String[this.d.a.size()];
            int i = 0;
            for (Entry entry : this.d.a.entrySet()) {
                int i2 = i + 1;
                strArr[i] = String.format(Locale.ENGLISH, "%s/%s", new Object[]{entry.getKey(), UrlSafeBase64.encodeToString((String) entry.getValue())});
                i = i2;
            }
            str = MqttTopic.TOPIC_LEVEL_SEPARATOR + StringUtils.join(strArr, MqttTopic.TOPIC_LEVEL_SEPARATOR);
        }
        try {
            uri2 = new URI(uri.getScheme(), uri.getHost(), String.format(Locale.ENGLISH, "/mkfile/%d%s%s%s", new Object[]{Long.valueOf(this.a), format, format2, str}), null);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            uri2 = uri;
        }
        byte[] bytes = StringUtils.join(this.h, Constants.ACCEPT_TIME_SEPARATOR_SP).getBytes();
        a(uri2, bytes, 0, bytes.length, null, completionHandler, upCancellationSignal);
    }

    private void a(URI uri, byte[] bArr, int i, int i2, ProgressHandler progressHandler, CompletionHandler completionHandler, UpCancellationSignal upCancellationSignal) {
        this.e.asyncPost(uri.toString(), bArr, i, i2, this.i, this.o, progressHandler, completionHandler, upCancellationSignal);
    }

    private long a(long j) {
        long j2 = this.a - j;
        return j2 < ((long) this.f.chunkSize) ? j2 : (long) this.f.chunkSize;
    }

    private long b(long j) {
        long j2 = this.a - j;
        return j2 < 4194304 ? j2 : 4194304;
    }

    private boolean a() {
        return this.d.e.isCancelled();
    }

    private void a(long j, int i, URI uri) {
        if (a()) {
            this.c.complete(this.b, ResponseInfo.cancelled(this.o), null);
        } else if (j == this.a) {
            a(uri, new h(this, i, j), this.d.e);
        } else {
            int a = (int) a(j);
            ProgressHandler iVar = new i(this, j);
            j jVar = new j(this, i, j, uri, a);
            if (j % 4194304 == 0) {
                a(uri, j, (int) b(j), a, iVar, (CompletionHandler) jVar, this.d.e);
                return;
            }
            a(uri, j, a, this.h[(int) (j / 4194304)], iVar, (CompletionHandler) jVar, this.d.e);
        }
    }

    private long b() {
        if (this.f.recorder == null) {
            return 0;
        }
        byte[] bArr = this.f.recorder.get(this.k);
        if (bArr == null) {
            return 0;
        }
        try {
            JSONObject jSONObject = new JSONObject(new String(bArr));
            long optLong = jSONObject.optLong(ParamKey.OFFSET, 0);
            long optLong2 = jSONObject.optLong("modify_time", 0);
            long optLong3 = jSONObject.optLong("size", 0);
            JSONArray optJSONArray = jSONObject.optJSONArray("contexts");
            if (optLong == 0 || optLong2 != this.j || optLong3 != this.a || optJSONArray == null || optJSONArray.length() == 0) {
                return 0;
            }
            for (int i = 0; i < optJSONArray.length(); i++) {
                this.h[i] = optJSONArray.optString(i);
            }
            return optLong;
        } catch (JSONException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private void c() {
        if (this.f.recorder != null) {
            this.f.recorder.del(this.k);
        }
    }

    private void c(long j) {
        if (this.f.recorder != null && j != 0) {
            this.f.recorder.set(this.k, String.format(Locale.ENGLISH, "{\"size\":%d,\"offset\":%d, \"modify_time\":%d, \"contexts\":[%s]}", new Object[]{Long.valueOf(this.a), Long.valueOf(j), Long.valueOf(this.j), StringUtils.jsonJoin(this.h)}).getBytes());
        }
    }

    private URI a(URI uri, String str) {
        try {
            return new URI(uri.getScheme(), null, uri.getHost(), uri.getPort(), str, null, null);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return uri;
        }
    }
}
