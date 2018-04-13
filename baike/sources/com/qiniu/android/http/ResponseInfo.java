package com.qiniu.android.http;

import com.qiniu.android.collect.Config;
import com.qiniu.android.collect.UploadInfoCollector;
import com.qiniu.android.common.Constants;
import com.qiniu.android.storage.UpToken;
import java.util.Locale;
import org.json.JSONObject;

public final class ResponseInfo {
    public static final int Cancelled = -2;
    public static final int CannotConnectToHost = -1004;
    public static final int InvalidArgument = -4;
    public static final int InvalidFile = -3;
    public static final int InvalidToken = -5;
    public static final int NetworkConnectionLost = -1005;
    public static final int NetworkError = -1;
    public static final int TimedOut = -1001;
    public static final int UnknownError = 0;
    public static final int UnknownHost = -1003;
    public static final int ZeroSizeFile = -6;
    public final double duration;
    public final String error;
    public final String host;
    public final String id = UserAgent.instance().id;
    public final String ip;
    public final String path;
    public final int port;
    public final String reqId;
    public final JSONObject response;
    public final long sent;
    public final int statusCode;
    public final long timeStamp = (System.currentTimeMillis() / 1000);
    public final UpToken upToken;
    public final String xlog;
    public final String xvia;

    private ResponseInfo(JSONObject jSONObject, int i, String str, String str2, String str3, String str4, String str5, String str6, int i2, double d, long j, String str7, UpToken upToken) {
        this.response = jSONObject;
        this.statusCode = i;
        this.reqId = str;
        this.xlog = str2;
        this.xvia = str3;
        this.host = str4;
        this.path = str5;
        this.duration = d;
        this.error = str7;
        this.ip = str6;
        this.port = i2;
        this.sent = j;
        this.upToken = upToken;
    }

    public static ResponseInfo create(JSONObject jSONObject, int i, String str, String str2, String str3, String str4, String str5, String str6, int i2, double d, long j, String str7, UpToken upToken) {
        ResponseInfo responseInfo = new ResponseInfo(jSONObject, i, str, str2, str3, str4, str5, str6, i2, d, j, str7, upToken);
        if (Config.isRecord) {
            String str8 = responseInfo.id;
            str7 + "";
            UpToken upToken2 = upToken;
            UploadInfoCollector.handle(upToken2, new l(str6, i, str, str4, i2, d, responseInfo.timeStamp + "", j));
        }
        return responseInfo;
    }

    public static ResponseInfo zeroSize(UpToken upToken) {
        return create(null, -6, "", "", "", "", "", "", 80, 0.0d, 0, "file or data size is zero", upToken);
    }

    public static ResponseInfo cancelled(UpToken upToken) {
        return create(null, -2, "", "", "", "", "", "", 80, -1.0d, -1, "cancelled by user", upToken);
    }

    public static ResponseInfo invalidArgument(String str, UpToken upToken) {
        return create(null, -4, "", "", "", "", "", "", 80, 0.0d, 0, str, upToken);
    }

    public static ResponseInfo invalidToken(String str) {
        return create(null, -5, "", "", "", "", "", "", 80, 0.0d, 0, str, null);
    }

    public static ResponseInfo fileError(Exception exception, UpToken upToken) {
        return create(null, -3, "", "", "", "", "", "", 80, 0.0d, 0, exception.getMessage(), upToken);
    }

    public boolean isCancelled() {
        return this.statusCode == -2;
    }

    public boolean isOK() {
        return this.statusCode == 200 && this.error == null && (hasReqId() || this.response != null);
    }

    public boolean isNetworkBroken() {
        return this.statusCode == -1 || this.statusCode == UnknownHost || this.statusCode == CannotConnectToHost || this.statusCode == TimedOut || this.statusCode == NetworkConnectionLost;
    }

    public boolean isServerError() {
        return (this.statusCode >= 500 && this.statusCode < 600 && this.statusCode != 579) || this.statusCode == 996;
    }

    public boolean needSwitchServer() {
        return isNetworkBroken() || isServerError();
    }

    public boolean needRetry() {
        return !isCancelled() && (needSwitchServer() || this.statusCode == 406 || (this.statusCode == 200 && this.error != null));
    }

    public boolean isNotQiniu() {
        return this.statusCode < 500 && this.statusCode >= 200 && !hasReqId() && this.response == null;
    }

    public String toString() {
        return String.format(Locale.ENGLISH, "{ver:%s,ResponseInfo:%s,status:%d, reqId:%s, xlog:%s, xvia:%s, host:%s, path:%s, ip:%s, port:%d, duration:%f s, time:%d, sent:%d,error:%s}", new Object[]{Constants.VERSION, this.id, Integer.valueOf(this.statusCode), this.reqId, this.xlog, this.xvia, this.host, this.path, this.ip, Integer.valueOf(this.port), Double.valueOf(this.duration), Long.valueOf(this.timeStamp), Long.valueOf(this.sent), this.error});
    }

    public boolean hasReqId() {
        return this.reqId != null;
    }
}
