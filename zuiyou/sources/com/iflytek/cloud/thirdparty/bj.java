package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.text.TextUtils;
import com.iflytek.cloud.SpeechError;
import com.iflytek.msc.MSC;
import com.iflytek.msc.MSCSessionInfo;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class bj {
    public static Object a = new Object();
    private MSCSessionInfo b = new MSCSessionInfo();

    public static void a(Context context, String str, String str2, be beVar) throws SpeechError, IOException {
        byte[] bArr = null;
        synchronized (a) {
            byte[] bytes;
            String a = cg.a(context, beVar);
            if (!TextUtils.isEmpty(str)) {
                bytes = str.getBytes("utf-8");
            } else if (context != null) {
                Object a2 = cg.a(context);
                bytes = TextUtils.isEmpty(a2) ? null : a2.getBytes(beVar.q());
            } else {
                bytes = null;
            }
            if (!TextUtils.isEmpty(str2)) {
                bArr = str2.getBytes(beVar.q());
            }
            try {
                int QMSPLogin = MSC.QMSPLogin(bytes, bArr, a.getBytes(beVar.q()));
                cb.a("[MSPLogin]ret:" + QMSPLogin);
                if (QMSPLogin != 0) {
                    throw new SpeechError(QMSPLogin);
                }
            } catch (Throwable e) {
                cb.a(e);
                throw new SpeechError(20021);
            }
        }
    }

    public static boolean a() {
        boolean z = false;
        if (be.u()) {
            synchronized (a) {
                if (MSC.QMSPLogOut() == 0) {
                    z = true;
                }
            }
        } else {
            cb.a("MscHandler is not empty while logout.");
        }
        return z;
    }

    public synchronized byte[] a(Context context, be beVar) throws SpeechError, UnsupportedEncodingException {
        byte[] QMSPDownloadData;
        synchronized (a) {
            String c = cg.c(context, beVar);
            cb.a("[MSPSession downloadData]enter time:" + System.currentTimeMillis());
            cc.a("LastDataFlag", null);
            QMSPDownloadData = MSC.QMSPDownloadData(c.getBytes(beVar.q()), this.b);
            cc.a("GetNotifyResult", null);
            cb.a("[MSPSession downloadData]leavel:" + this.b.errorcode + ",data len = " + (QMSPDownloadData == null ? 0 : QMSPDownloadData.length));
            int i = this.b.errorcode;
            if (i != 0 || QMSPDownloadData == null) {
                throw new SpeechError(i);
            }
        }
        return QMSPDownloadData;
    }

    public synchronized byte[] a(Context context, be beVar, String str) throws SpeechError, UnsupportedEncodingException {
        byte[] bytes;
        synchronized (a) {
            String c = cg.c(context, beVar);
            cb.a("[MSPSession searchResult]enter time:" + System.currentTimeMillis());
            bytes = str.getBytes("utf-8");
            cc.a("LastDataFlag", null);
            bytes = MSC.QMSPSearch(c.getBytes(beVar.q()), bytes, this.b);
            cc.a("GetNotifyResult", null);
            cb.a("[QMSPSearch searchResult]leavel:" + this.b.errorcode + ",data len = " + (bytes == null ? 0 : bytes.length));
            int i = this.b.errorcode;
            if (i != 0 || bytes == null) {
                throw new SpeechError(i);
            }
        }
        return bytes;
    }

    public synchronized byte[] a(Context context, String str, byte[] bArr, be beVar) throws SpeechError, UnsupportedEncodingException {
        byte[] QMSPUploadData;
        synchronized (a) {
            String c = cg.c(context, beVar);
            cb.a("[MSPSession uploadData]enter time:" + System.currentTimeMillis());
            QMSPUploadData = MSC.QMSPUploadData(str.getBytes(beVar.q()), bArr, bArr.length, c.getBytes("utf-8"), this.b);
            cb.a("[MSPSession uploaddData]leavel:" + this.b.errorcode + ",data len = " + (QMSPUploadData == null ? 0 : QMSPUploadData.length));
            int i = this.b.errorcode;
            if (i != 0 || QMSPUploadData == null) {
                throw new SpeechError(i);
            }
        }
        return QMSPUploadData;
    }
}
