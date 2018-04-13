package com.iflytek.msc;

import android.graphics.Bitmap;
import com.iflytek.cloud.thirdparty.cb;
import java.io.FileDescriptor;

public class MSC {
    private static boolean a = false;

    public static native int AIUIClear(char[] cArr);

    public static native int AIUIDataWrite(char[] cArr, byte[] bArr, byte[] bArr2, byte[] bArr3, int i, MSCSessionInfo mSCSessionInfo);

    public static native int AIUIGetParam(char[] cArr, byte[] bArr, MSCSessionInfo mSCSessionInfo);

    public static native int AIUIRegisterNotify(char[] cArr, String str, String str2, String str3, String str4, String str5, Object obj);

    public static native int AIUISendLog(char[] cArr, byte[] bArr, byte[] bArr2, int i, MSCSessionInfo mSCSessionInfo);

    public static final native char[] AIUISessionBegin(byte[] bArr, MSCSessionInfo mSCSessionInfo);

    public static native int AIUISessionEnd(char[] cArr, byte[] bArr);

    public static native int AIUISetParam(char[] cArr, byte[] bArr, byte[] bArr2);

    public static native int AIUISyncData(char[] cArr, byte[] bArr, byte[] bArr2, int i, int i2, MSCSessionInfo mSCSessionInfo);

    public static final native int DebugLog(boolean z);

    public static final native int QHCRDataWrite(char[] cArr, byte[] bArr, byte[] bArr2, int i, int i2);

    public static final native int QHCRFini();

    public static final native byte[] QHCRGetResult(char[] cArr, byte[] bArr, MSCSessionInfo mSCSessionInfo);

    public static final native int QHCRInit(byte[] bArr);

    public static final native int QHCRLogEvent(char[] cArr, byte[] bArr, byte[] bArr2);

    public static final native char[] QHCRSessionBegin(byte[] bArr, MSCSessionInfo mSCSessionInfo);

    public static final native int QHCRSessionEnd(char[] cArr, byte[] bArr);

    public static native byte[] QIFDFacedetect(Bitmap bitmap, int i, Object obj);

    public static native int QIFDFini();

    public static native void QIFDInit(byte[] bArr, Object obj);

    public static native byte[] QIFDMultitracker(byte[] bArr, int i, int i2, int i3, int i4, int i5, Object obj);

    public static final native int QISEAudioWrite(char[] cArr, byte[] bArr, int i, int i2, MSCSessionInfo mSCSessionInfo);

    public static final native int QISEFini();

    public static final native int QISEGetParam(char[] cArr, byte[] bArr, MSCSessionInfo mSCSessionInfo);

    public static final native byte[] QISEGetResult(char[] cArr, MSCSessionInfo mSCSessionInfo);

    public static final native int QISEInit(byte[] bArr);

    public static final native char[] QISESessionBegin(byte[] bArr, byte[] bArr2, MSCSessionInfo mSCSessionInfo);

    public static final native int QISESessionEnd(char[] cArr, byte[] bArr);

    public static final native int QISETextPut(char[] cArr, byte[] bArr, byte[] bArr2);

    public static final native int QISRAudioWrite(char[] cArr, byte[] bArr, int i, int i2, MSCSessionInfo mSCSessionInfo);

    public static final native int QISRBuildGrammar(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, String str, Object obj);

    public static final native int QISRFini();

    public static final native int QISRGetParam(char[] cArr, byte[] bArr, MSCSessionInfo mSCSessionInfo);

    public static final native byte[] QISRGetResult(char[] cArr, MSCSessionInfo mSCSessionInfo);

    public static final native int QISRGrammarActivate(char[] cArr, byte[] bArr, byte[] bArr2);

    public static final native int QISRInit(byte[] bArr);

    public static final native int QISRLogEvent(char[] cArr, byte[] bArr, byte[] bArr2);

    public static final native int QISRRegisterNotify(char[] cArr, String str, String str2, String str3, Object obj);

    public static final native char[] QISRSessionBegin(byte[] bArr, byte[] bArr2, MSCSessionInfo mSCSessionInfo);

    public static final native int QISRSessionEnd(char[] cArr, byte[] bArr);

    public static final native int QISRSetParam(char[] cArr, byte[] bArr, byte[] bArr2);

    public static final native int QISRUpdateLexicon(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, String str, Object obj);

    public static final native byte[] QISRUploadData(char[] cArr, byte[] bArr, byte[] bArr2, int i, byte[] bArr3, MSCSessionInfo mSCSessionInfo);

    public static final native int QISVAudioWrite(char[] cArr, char[] cArr2, byte[] bArr, int i, int i2, MSCSessionInfo mSCSessionInfo);

    public static final native int QISVFini();

    public static final native int QISVGetParam(char[] cArr, byte[] bArr, MSCSessionInfo mSCSessionInfo);

    public static final native byte[] QISVGetResult(char[] cArr, char[] cArr2, MSCSessionInfo mSCSessionInfo);

    public static final native int QISVInit(byte[] bArr);

    public static final native char[] QISVQueDelModel(byte[] bArr, byte[] bArr2, MSCSessionInfo mSCSessionInfo);

    public static final native int QISVQueDelModelRelease(char[] cArr);

    public static final native char[] QISVSessionBegin(byte[] bArr, byte[] bArr2, MSCSessionInfo mSCSessionInfo);

    public static final native int QISVSessionEnd(char[] cArr, byte[] bArr);

    public static final native int QIVWAudioWrite(char[] cArr, byte[] bArr, int i, int i2, MSCSessionInfo mSCSessionInfo);

    public static final native int QIVWGetResInfo(byte[] bArr, byte[] bArr2, MSCSessionInfo mSCSessionInfo);

    public static final native int QIVWRegisterNotify(char[] cArr, String str, Object obj);

    public static final native int QIVWResMerge(byte[] bArr, byte[] bArr2, byte[] bArr3);

    public static final native char[] QIVWSessionBegin(byte[] bArr, byte[] bArr2, MSCSessionInfo mSCSessionInfo);

    public static final native int QIVWSessionEnd(char[] cArr, byte[] bArr);

    public static final native int QMFVDataWrite(char[] cArr, byte[] bArr, byte[] bArr2, int i, MSCSessionInfo mSCSessionInfo);

    public static final native int QMFVGetParam(char[] cArr, byte[] bArr, MSCSessionInfo mSCSessionInfo);

    public static final native byte[] QMFVGetResult(char[] cArr, MSCSessionInfo mSCSessionInfo);

    public static final native int QMFVRegisterNotify(char[] cArr, String str, String str2, String str3, Object obj);

    public static final native char[] QMFVSessionBegin(byte[] bArr, MSCSessionInfo mSCSessionInfo);

    public static final native int QMFVSessionEnd(char[] cArr, byte[] bArr);

    public static final native int QMFVSetParam(char[] cArr, byte[] bArr, byte[] bArr2);

    public static final native int QMSPDownload(byte[] bArr, byte[] bArr2, Object obj);

    public static final native byte[] QMSPDownloadData(byte[] bArr, MSCSessionInfo mSCSessionInfo);

    public static final native int QMSPGetParam(byte[] bArr, MSCSessionInfo mSCSessionInfo);

    public static final native byte[] QMSPGetVersion(byte[] bArr, MSCSessionInfo mSCSessionInfo);

    public static final native int QMSPLogOut();

    public static final native int QMSPLogin(byte[] bArr, byte[] bArr2, byte[] bArr3);

    public static final native int QMSPRegisterNotify(String str, String str2);

    public static final native byte[] QMSPSearch(byte[] bArr, byte[] bArr2, MSCSessionInfo mSCSessionInfo);

    public static final native int QMSPSetParam(byte[] bArr, byte[] bArr2);

    public static final native byte[] QMSPUploadData(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, MSCSessionInfo mSCSessionInfo);

    public static final native byte[] QTTSAudioGet(char[] cArr, MSCSessionInfo mSCSessionInfo);

    public static final native char[] QTTSAudioInfo(char[] cArr);

    public static final native int QTTSFini();

    public static final native int QTTSGetParam(char[] cArr, byte[] bArr, MSCSessionInfo mSCSessionInfo);

    public static final native int QTTSInit(byte[] bArr);

    public static final native char[] QTTSSessionBegin(byte[] bArr, MSCSessionInfo mSCSessionInfo);

    public static final native int QTTSSessionEnd(char[] cArr, byte[] bArr);

    public static final native int QTTSTextPut(char[] cArr, byte[] bArr);

    public static final native int SetLogLevel(int i);

    public static final native int UMSPLogin(byte[] bArr, byte[] bArr2, byte[] bArr3, Object obj);

    public static native boolean doARGB2Gray(Bitmap bitmap, Bitmap bitmap2);

    public static final native int getFileDescriptorFD(FileDescriptor fileDescriptor);

    public static boolean isIflyVersion() {
        cb.a("MSC ifly ver: true");
        return true;
    }

    public static boolean isLoaded() {
        return a;
    }

    public static boolean loadLibrary(String str) {
        if (a) {
            return a;
        }
        try {
            System.loadLibrary(str);
            a = true;
            cb.a("loadLibrary " + str + " success");
        } catch (UnsatisfiedLinkError e) {
            cb.c("loadLibrary " + str + " error:" + e);
            a = false;
        }
        return a;
    }
}
