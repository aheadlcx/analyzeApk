package com.iflytek.cloud.thirdparty;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.iflytek.aiui.AIUIConstant;
import com.iflytek.aiui.AIUIEvent;
import com.iflytek.aiui.AIUISetting;
import com.iflytek.cloud.GrammarListener;
import com.iflytek.cloud.LexiconListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.util.ResourceUtil;
import com.iflytek.speech.VoiceWakeuperAidl;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

public class v extends u {
    private static final String d = (AIUISetting.getAIUIPath() + "asr/grammar/");
    private static Pattern e = Pattern.compile("!grammar .+?;");
    private static Pattern f = Pattern.compile("#BNF.+?;");
    private String g = "";
    private String h = "0";
    private String i = "cloud";
    private a j;
    private HandlerThread k;
    private SpeechRecognizer l;
    private String m = "";
    private boolean n = false;
    private GrammarListener o = new GrammarListener(this) {
        final /* synthetic */ v a;

        {
            this.a = r1;
        }

        public void onBuildFinish(String str, SpeechError speechError) {
            if (speechError != null) {
                this.a.a(speechError.getErrorCode(), speechError.getErrorDescription());
                cb.c("AsrUnit", "build grammar, error=" + speechError.getErrorCode());
                return;
            }
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(AIUIConstant.KEY_GRAMMAR_ID, str);
                this.a.a(Message.obtain(this.a.c.b(), 8, new AIUIEvent(8, 16, 0, jSONObject.toString(), null)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };
    private boolean p = false;
    private boolean q = false;
    private boolean r = false;
    private LexiconListener s = new LexiconListener(this) {
        final /* synthetic */ v a;

        {
            this.a = r1;
        }

        public void onLexiconUpdated(String str, SpeechError speechError) {
            if (speechError != null) {
                this.a.a(speechError.getErrorCode(), speechError.getErrorDescription());
                cb.c("AsrUnit", "update asr lexicon, error=" + speechError.getErrorCode());
                return;
            }
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(AIUIConstant.KEY_LEXICON_ID, str);
                this.a.a(Message.obtain(this.a.c.b(), 8, new AIUIEvent(8, 17, 0, jSONObject.toString(), null)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };
    private RecognizerListener t = new RecognizerListener(this) {
        final /* synthetic */ v a;

        {
            this.a = r1;
        }

        public void onBeginOfSpeech() {
        }

        public void onEndOfSpeech() {
        }

        public void onError(SpeechError speechError) {
            synchronized (this.a.v) {
                if (!this.a.p) {
                    this.a.q = true;
                }
                this.a.r = false;
            }
            if (20005 == speechError.getErrorCode() || 23008 == speechError.getErrorCode()) {
                long currentTimeMillis = System.currentTimeMillis();
                String a = am.a();
                JSONObject b = am.b();
                Map hashMap = new HashMap();
                try {
                    hashMap.put("0", b.toString().getBytes("utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                ad adVar = new ad("asr", "", a, this.a.m, hashMap, currentTimeMillis, false);
                this.a.g();
                if ("local".equals(this.a.i)) {
                    adVar.a(true);
                    this.a.u.a(this.a.m, "asr", adVar);
                    this.a.a(a, hashMap, currentTimeMillis);
                } else if ("mixed".equals(this.a.i)) {
                    this.a.u.a(this.a.m, "asr", adVar);
                }
            } else {
                this.a.a(speechError.getErrorCode(), speechError.getErrorDescription());
            }
            cb.c("AsrUnit", "error=" + speechError.getErrorCode());
        }

        public void onEvent(int i, int i2, int i3, Bundle bundle) {
        }

        public void onResult(RecognizerResult recognizerResult, boolean z) {
            long currentTimeMillis = System.currentTimeMillis();
            synchronized (this.a.v) {
                if (!this.a.p) {
                    this.a.q = true;
                }
                this.a.r = false;
            }
            if (!this.a.u.c(this.a.m, "nlp")) {
                Map hashMap = new HashMap();
                try {
                    cb.a("asr_result", recognizerResult.getResultString());
                    JSONObject jSONObject = new JSONObject(recognizerResult.getResultString());
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject.put("rc", 0);
                    jSONObject2.put(AIUIConstant.WORK_MODE_INTENT, jSONObject);
                    hashMap.put("0", jSONObject2.toString().getBytes("utf-8"));
                    String a = am.a();
                    this.a.u.a(this.a.m, "asr", new ad("asr", "", a, this.a.m, hashMap, currentTimeMillis, true));
                    this.a.a(a, hashMap, currentTimeMillis);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e2) {
                    e2.printStackTrace();
                }
            }
        }

        public void onVolumeChanged(int i, byte[] bArr) {
        }
    };
    private ae u;
    private Object v = new Object();

    class a extends Handler {
        final /* synthetic */ v a;

        public a(v vVar, Looper looper) {
            this.a = vVar;
            super(looper);
        }

        private void a() {
            String a = ac.a("global", "scene", "");
            String str = v.d + a;
            this.a.l = SpeechRecognizer.createRecognizer(this.a.c.a(), null);
            this.a.l.setParameter("engine_type", "local");
            this.a.l.setParameter(SpeechConstant.TEXT_ENCODING, "utf-8");
            this.a.l.setParameter(SpeechConstant.RESULT_TYPE, "json");
            this.a.l.setParameter(ResourceUtil.ASR_RES_PATH, this.a.g);
            this.a.l.setParameter(ResourceUtil.GRM_BUILD_PATH, str);
            this.a.l.setParameter(SpeechConstant.LOCAL_GRAMMAR, a);
            this.a.l.setParameter("vad_enable", "0");
            this.a.l.setParameter(SpeechConstant.AUDIO_SOURCE, WeiboAuthException.DEFAULT_AUTH_ERROR_CODE);
            this.a.l.setParameter(SpeechConstant.ASR_THRESHOLD, this.a.h);
            this.a.l.setParameter(SpeechConstant.KEY_REQUEST_FOCUS, "0");
            if (aj.c()) {
                this.a.l.setParameter(SpeechConstant.ASR_AUDIO_PATH, aj.a() + "asr-audio/" + this.a.m + ".pcm");
            }
            this.a.l.startListening(this.a.t);
            this.a.p = false;
            this.a.q = false;
            this.a.r = false;
            cb.a("AsrUnit", "start recognizing.");
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 3:
                    y yVar = (y) message.obj;
                    switch (yVar.d) {
                        case 1:
                            this.a.n = true;
                            String e = yVar.e.e("stream_id");
                            if (TextUtils.isEmpty(this.a.m) || !this.a.m.equals(e)) {
                                this.a.m = e;
                            }
                            a();
                            break;
                        case 2:
                            break;
                        case 4:
                            if (this.a.n) {
                                this.a.n = false;
                                synchronized (this.a.v) {
                                    this.a.p = true;
                                    if (this.a.q) {
                                        this.a.r = false;
                                    } else {
                                        this.a.r = true;
                                    }
                                }
                                if (this.a.l != null) {
                                    this.a.l.stopListening();
                                    break;
                                }
                            }
                            break;
                    }
                    if (this.a.l.isListening()) {
                        this.a.l.writeAudio(yVar.c, 0, yVar.a());
                    }
                    while (this.a.r) {
                        try {
                            Thread.sleep(30);
                        } catch (InterruptedException e2) {
                            e2.printStackTrace();
                        }
                    }
                    return;
                case 4:
                    if (this.a.l != null) {
                        this.a.l.cancel();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public v(t tVar) {
        super("AsrUnit", tVar);
        g();
        this.k = new HandlerThread("AIUI:ASR-HandlerThread");
        this.k.start();
        this.u = ae.a();
    }

    private void a(String str, Map<String, byte[]> map, long j) {
        try {
            this.c.b().f().a("", str, map, "", this.m, j);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    private void h() {
        this.g = ResourceUtil.generateResourcePath(this.c.a(), ac.c(ac.a("asr", AIUIConstant.KEY_RES_TYPE, "")), ac.a("asr", AIUIConstant.KEY_RES_PATH, ""));
        this.h = ac.a("asr", "threshold", "0");
    }

    public void a(y yVar) {
        if (this.a && yVar != null) {
            Message.obtain(this.j, 3, yVar).sendToTarget();
        }
    }

    public void a(String str) {
        h();
        String a = ac.a("global", "scene", "");
        String str2 = d + a;
        a = "!grammar " + a + VoiceWakeuperAidl.PARAMS_SEPARATE;
        Matcher matcher = e.matcher(str);
        if (matcher.find()) {
            str = matcher.replaceAll(a);
        } else {
            matcher = f.matcher(str);
            if (matcher.find()) {
                str = matcher.replaceAll("$0\n" + a);
            }
        }
        cb.a("AsrUnit", "replace grammar " + str);
        this.l = SpeechRecognizer.createRecognizer(this.c.a(), null);
        this.l.setParameter("engine_type", "local");
        this.l.setParameter(SpeechConstant.TEXT_ENCODING, "utf-8");
        this.l.setParameter(SpeechConstant.RESULT_TYPE, "json");
        this.l.setParameter(ResourceUtil.ASR_RES_PATH, this.g);
        this.l.setParameter(ResourceUtil.GRM_BUILD_PATH, str2);
        this.l.buildGrammar("bnf", str, this.o);
        cb.a("AsrUnit", "build grammar.");
    }

    public void b() {
    }

    public void b(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("name");
            String string2 = jSONObject.getString("content");
            String a = ac.a("global", "scene", "");
            this.l = SpeechRecognizer.createRecognizer(this.c.a(), null);
            this.l.setParameter("engine_type", "local");
            this.l.setParameter(SpeechConstant.GRAMMAR_LIST, a);
            this.l.setParameter(SpeechConstant.KEY_REQUEST_FOCUS, "0");
            this.l.updateLexicon(string, string2, this.s);
            cb.a("AsrUnit", "update asr lexicon.");
        } catch (JSONException e) {
            e.printStackTrace();
            a(20012, "Invalid asr lexicon json!");
        }
    }

    public int c() {
        if (this.a) {
            cb.c("AsrUnit", "AsrUnit is already started.");
            return 0;
        }
        h();
        this.j = new a(this, this.k.getLooper());
        this.a = true;
        this.n = false;
        cb.a("AsrUnit", "AsrUnit started");
        return super.c();
    }

    public void d() {
        this.r = false;
        if (this.j != null) {
            this.j.removeMessages(3);
            Message.obtain(this.j, 4).sendToTarget();
        }
        this.a = false;
        cb.a("AsrUnit", "AsrUnit stopped");
    }

    public void f() {
        d();
        if (this.k != null) {
            this.k.quit();
        }
        cb.a("AsrUnit", "destoryed");
    }

    public void g() {
        this.i = ac.a(AIUIConstant.PARAM_SPEECH, AIUIConstant.KEY_INTENT_ENGINE_TYPE, "cloud");
    }
}
