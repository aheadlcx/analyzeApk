package com.iflytek.cloud.util;

import android.content.Context;
import android.text.TextUtils;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.thirdparty.cb;
import com.iflytek.cloud.thirdparty.ce;
import com.iflytek.cloud.thirdparty.cx;
import com.iflytek.cloud.thirdparty.cy;
import java.util.LinkedHashMap;

public abstract class AudioDetector {
    public static final int DEF_BOS = 2000;
    public static final String DEF_ENGINE_TYPE = "fixfront";
    public static final int DEF_EOS = 700;
    public static final int DEF_RATE = 16000;
    public static final String EARLY_START = "early_start";
    public static final int MAX_BUF_LEN = 32768;
    public static final String REDUCE_FLOW = "vad_reduce_flow";
    public static final String RESET_SENTENCE = "reset_sentence";
    public static final String RES_PATH = "vad_res_path";
    public static final String SUB_TIMEOUT = "sub_timeout";
    public static final String THRESHOLD = "threshold";
    public static final String TYPE_FIXFRONT = "fixfront";
    public static final String TYPE_META = "meta";
    public static final String VAD_ENGINE = "vad_engine";
    protected static AudioDetector a = null;
    protected static Object b = new Object();

    public static class DetectorResult {
        public static final int STATUS_BOS = 3;
        public static final int STATUS_EOS = 2;
        public static final int STATUS_OK = 0;
        public static final int STATUS_START = 1;
        public static final int STATUS_TIMEOUT = 4;
        public static final int SUB_END = 2;
        public static final int SUB_OK = 0;
        public static final int SUB_START = 1;
        public static final int SUB_STARTEND = 3;
        public byte[] buffer = null;
        public float confidence = 1.0f;
        public int end = 0;
        public int error = 0;
        public int length = 0;
        public int offset = 0;
        public int quality = 0;
        public int start = 0;
        public int status = 0;
        public int sub = 0;
        public final LinkedHashMap<Integer, Integer> subs = new LinkedHashMap();
        public boolean voice = false;
        public int volume = 0;
    }

    protected AudioDetector(Context context, String str) {
    }

    private static AudioDetector a(Context context, String str) {
        ce ceVar = new ce();
        ceVar.a(str);
        String e = ceVar.e(SpeechConstant.LIB_NAME);
        if (TextUtils.isEmpty(e) || a(e)) {
            String b = ceVar.b(VAD_ENGINE, "fixfront");
            if ("fixfront".equalsIgnoreCase(b)) {
                return new cx(context, str);
            }
            if (TYPE_META.equalsIgnoreCase(b)) {
                return new cy(context, str);
            }
            cb.c("detector factory unmatched engine type: " + b);
            return null;
        }
        cb.c("detector factory load library failed: " + e);
        return null;
    }

    private static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        try {
            System.loadLibrary(str);
            return true;
        } catch (Throwable th) {
            cb.c("Load library failed.");
            th.printStackTrace();
            return false;
        }
    }

    public static AudioDetector createDetector(Context context, String str) {
        cb.a("createDetector enter, context: " + context + ", param: " + str);
        synchronized (b) {
            if (a == null) {
                a = a(context, str);
            }
        }
        cb.a("createDetector leave");
        return a;
    }

    public static AudioDetector getDetector() {
        synchronized (b) {
            cb.a("getDetector enter");
        }
        return a;
    }

    public abstract boolean destroy();

    public abstract DetectorResult detect(byte[] bArr, int i, int i2, boolean z);

    public abstract void reset();

    public abstract void setParameter(String str, String str2);
}
