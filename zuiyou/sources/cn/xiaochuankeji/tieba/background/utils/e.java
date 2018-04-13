package cn.xiaochuankeji.tieba.background.utils;

import android.content.Context;
import android.os.Bundle;
import cn.xiaochuan.base.BaseApplication;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.izuiyou.a.a.b;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class e {
    private static e a;
    private static SpeechRecognizer b;
    private boolean c;
    private a d;
    private HashMap<String, String> e = new LinkedHashMap();
    private a f;
    private String g;
    private cn.xiaochuankeji.tieba.background.utils.a.a h = new cn.xiaochuankeji.tieba.background.utils.a.a(this) {
        final /* synthetic */ e a;

        {
            this.a = r1;
        }

        public void a(String str) {
            this.a.g = str;
            this.a.a(str);
        }

        public void b(String str) {
            this.a.d.a(0, str);
        }
    };
    private RecognizerListener i = new RecognizerListener(this) {
        final /* synthetic */ e a;

        {
            this.a = r1;
        }

        public void onBeginOfSpeech() {
        }

        public void onError(SpeechError speechError) {
            this.a.d.a(this.a.g, null);
            b.e("识别失败,errorCode:" + speechError.getErrorCode() + "_msg:" + speechError.getMessage() + "_description:" + speechError.getErrorDescription());
        }

        public void onEndOfSpeech() {
        }

        public void onResult(RecognizerResult recognizerResult, boolean z) {
            String c = e.d(recognizerResult.getResultString());
            Object obj = null;
            try {
                obj = new JSONObject(recognizerResult.getResultString()).optString("sn");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.a.e.put(obj, c);
            if (z) {
                StringBuffer stringBuffer = new StringBuffer();
                for (String str : this.a.e.keySet()) {
                    stringBuffer.append((String) this.a.e.get(str));
                }
                b.b("讯飞识别：" + stringBuffer.toString());
                this.a.d.a(this.a.g, stringBuffer.toString());
            }
        }

        public void onVolumeChanged(int i, byte[] bArr) {
        }

        public void onEvent(int i, int i2, int i3, Bundle bundle) {
        }
    };

    public interface a {
        void a(int i, String str);

        void a(String str, String str2);
    }

    public static void a(Context context) {
        if (a == null) {
            synchronized (e.class) {
                if (a == null) {
                    a = new e(context);
                }
            }
        }
    }

    private e(Context context) {
        try {
            b = SpeechRecognizer.createRecognizer(context, new InitListener(this) {
                final /* synthetic */ e a;

                {
                    this.a = r1;
                }

                public void onInit(int i) {
                    if (i != 0) {
                        this.a.c = true;
                    } else {
                        this.a.d();
                    }
                }
            });
        } catch (Exception e) {
        }
    }

    private void d() {
        if (b != null) {
            b.setParameter(SpeechConstant.PARAMS, null);
            b.setParameter("engine_type", "cloud");
            b.setParameter(SpeechConstant.KEY_SPEECH_TIMEOUT, "60000");
            b.setParameter(SpeechConstant.RESULT_TYPE, "json");
            b.setParameter("language", "zh_cn");
            b.setParameter("accent", "mandarin");
            b.setParameter(SpeechConstant.DOMAIN, "video");
            b.setParameter("vad_bos", "10000");
            b.setParameter("vad_eos", "10000");
            b.setParameter("asr_ptt", "1");
            b.setParameter(SpeechConstant.AUDIO_FORMAT, "wav");
            b.setParameter(SpeechConstant.ASR_DWA, "0");
            b.setParameter(SpeechConstant.AUDIO_SOURCE, WeiboAuthException.DEFAULT_AUTH_ERROR_CODE);
        }
    }

    public static e a() {
        if (a == null || b == null) {
            a(BaseApplication.getAppContext());
        }
        return a;
    }

    public boolean a(a aVar) {
        this.f = a.a();
        this.d = aVar;
        if (this.c) {
            return false;
        }
        this.f.a(this.h);
        return true;
    }

    public void a(String str) {
        if (0 == new File(str).length()) {
            this.d.a(0, "录音文件长度出错");
            return;
        }
        this.e.clear();
        byte[] b = b(str);
        if (b != null) {
            int startListening = b.startListening(this.i);
            if (startListening != 0) {
                this.d.a(str, null);
                b.e("启动讯飞识别失败,errorCode:" + startListening);
                return;
            }
            this.g = str;
            b.writeAudio(b, 0, b.length);
            b.stopListening();
            return;
        }
        b.cancel();
        this.d.a(str, null);
        b.e("从文件读流失败");
    }

    public void b() {
        this.f.b();
    }

    public String c() {
        return this.g;
    }

    public static byte[] b(String str) {
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            byte[] bArr = new byte[fileInputStream.available()];
            fileInputStream.read(bArr);
            fileInputStream.close();
            return bArr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void b(a aVar) {
        if (aVar != null) {
            this.d = aVar;
        }
    }

    private static String d(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            JSONArray jSONArray = new JSONObject(new JSONTokener(str)).getJSONArray("ws");
            for (int i = 0; i < jSONArray.length(); i++) {
                stringBuffer.append(jSONArray.getJSONObject(i).getJSONArray("cw").getJSONObject(0).getString("w"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }
}
