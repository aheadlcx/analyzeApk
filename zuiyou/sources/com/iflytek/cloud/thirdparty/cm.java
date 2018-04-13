package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.iflytek.cloud.GrammarListener;
import com.iflytek.cloud.LexiconListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.util.AudioDetector;

public class cm extends bi {
    private boolean g = false;

    private final class a implements GrammarListener {
        final /* synthetic */ cm a;
        private GrammarListener b = null;
        private Handler c = new Handler(this, Looper.getMainLooper()) {
            final /* synthetic */ a a;

            public void handleMessage(Message message) {
                if (this.a.b != null) {
                    switch (message.what) {
                        case 0:
                            this.a.b.onBuildFinish(null, (SpeechError) message.obj);
                            break;
                        case 1:
                            this.a.b.onBuildFinish((String) message.obj, null);
                            break;
                    }
                    super.handleMessage(message);
                }
            }
        };

        public a(cm cmVar, GrammarListener grammarListener) {
            this.a = cmVar;
            this.b = grammarListener;
        }

        public void onBuildFinish(String str, SpeechError speechError) {
            if (speechError != null) {
                this.c.sendMessage(this.c.obtainMessage(0, speechError));
                return;
            }
            this.c.sendMessage(this.c.obtainMessage(1, str));
        }
    }

    private final class b implements LexiconListener {
        final /* synthetic */ cm a;
        private LexiconListener b = null;
        private Handler c = new Handler(this, Looper.getMainLooper()) {
            final /* synthetic */ b a;

            public void handleMessage(Message message) {
                if (this.a.b != null) {
                    switch (message.what) {
                        case 0:
                            this.a.b.onLexiconUpdated(null, (SpeechError) message.obj);
                            break;
                        case 1:
                            this.a.b.onLexiconUpdated((String) message.obj, null);
                            break;
                    }
                    super.handleMessage(message);
                }
            }
        };

        public b(cm cmVar, LexiconListener lexiconListener) {
            this.a = cmVar;
            this.b = lexiconListener;
        }

        public void onLexiconUpdated(String str, SpeechError speechError) {
            if (speechError != null) {
                this.c.sendMessage(this.c.obtainMessage(0, speechError));
                return;
            }
            this.c.sendMessage(this.c.obtainMessage(1, str));
        }
    }

    private final class c implements RecognizerListener {
        final /* synthetic */ cm a;
        private RecognizerListener b = null;
        private boolean c = false;
        private Handler d = new Handler(this, Looper.getMainLooper()) {
            final /* synthetic */ c a;

            public void handleMessage(Message message) {
                if (this.a.b != null) {
                    switch (message.what) {
                        case 0:
                            this.a.b.onError((SpeechError) message.obj);
                            break;
                        case 1:
                            this.a.b.onVolumeChanged(message.arg1, (byte[]) message.obj);
                            break;
                        case 2:
                            this.a.b.onBeginOfSpeech();
                            break;
                        case 3:
                            this.a.b.onEndOfSpeech();
                            break;
                        case 4:
                            this.a.b.onResult((RecognizerResult) message.obj, message.arg1 == 1);
                            if (!this.a.c) {
                                this.a.a.b("ui_frs");
                                this.a.c = true;
                            }
                            if (1 == message.arg1) {
                                this.a.a.b("ui_lrs");
                                break;
                            }
                            break;
                        case 6:
                            Message message2 = (Message) message.obj;
                            this.a.b.onEvent(message2.what, message2.arg1, message2.arg2, (Bundle) message2.obj);
                            break;
                    }
                    super.handleMessage(message);
                }
            }
        };

        public c(cm cmVar, RecognizerListener recognizerListener) {
            this.a = cmVar;
            this.b = recognizerListener;
        }

        public void onBeginOfSpeech() {
            cb.a("onBeginOfSpeech");
            this.d.sendMessage(this.d.obtainMessage(2, 0, 0, null));
        }

        public void onEndOfSpeech() {
            this.d.sendMessage(this.d.obtainMessage(3, 0, 0, null));
        }

        public void onError(SpeechError speechError) {
            this.a.f();
            this.d.sendMessage(this.d.obtainMessage(0, speechError));
        }

        public void onEvent(int i, int i2, int i3, Bundle bundle) {
            Message message = new Message();
            message.what = i;
            message.arg1 = i2;
            message.arg2 = i3;
            message.obj = bundle;
            this.d.sendMessage(this.d.obtainMessage(6, 0, 0, message));
        }

        public void onResult(RecognizerResult recognizerResult, boolean z) {
            int i = 1;
            if (z) {
                this.a.f();
            }
            Handler handler = this.d;
            if (!z) {
                i = 0;
            }
            this.d.sendMessage(handler.obtainMessage(4, i, 0, recognizerResult));
        }

        public void onVolumeChanged(int i, byte[] bArr) {
            this.d.sendMessage(this.d.obtainMessage(1, i, 0, bArr));
        }
    }

    public cm(Context context) {
        super(context);
    }

    public int a(RecognizerListener recognizerListener) {
        int i;
        synchronized (this.d) {
            try {
                this.g = this.c.a(SpeechConstant.KEY_REQUEST_FOCUS, true);
                if (this.e != null && this.e.v()) {
                    this.e.b(this.c.a(SpeechConstant.ASR_INTERRUPT_ERROR, false));
                }
                if (h()) {
                    this.e = new ay(this.a, this.c, a("iat"));
                } else {
                    this.e = new ax(this.a, this.c, a("iat"));
                }
                bw.a(this.a, Boolean.valueOf(this.g), null);
                ((ax) this.e).a(new c(this, recognizerListener));
                i = 0;
            } catch (Throwable e) {
                int errorCode = e.getErrorCode();
                cb.a(e);
                i = errorCode;
            } catch (Throwable e2) {
                cb.a(e2);
                i = 20999;
            }
        }
        return i;
    }

    public int a(String str, String str2, GrammarListener grammarListener) {
        if (TextUtils.isEmpty(str2)) {
            return 20009;
        }
        if (TextUtils.isEmpty(str) || grammarListener == null) {
            return 20012;
        }
        new aw().a(str, str2, new a(this, grammarListener), this.c);
        return 0;
    }

    public int a(String str, String str2, LexiconListener lexiconListener) {
        if (TextUtils.isEmpty(str2)) {
            return 20009;
        }
        if (TextUtils.isEmpty(str) || lexiconListener == null) {
            return 20012;
        }
        aw awVar = new aw();
        this.c.a(SpeechConstant.SUBJECT, "uup", false);
        String parameter = getParameter(SpeechConstant.LEXICON_TYPE);
        if (TextUtils.isEmpty(parameter)) {
            parameter = str;
        }
        this.c.a("data_type", parameter, false);
        awVar.a(str, str2, new b(this, lexiconListener), this.c);
        return 0;
    }

    public int a(byte[] bArr, int i, int i2) {
        int i3 = 10109;
        synchronized (this.d) {
            if (this.e == null) {
                cb.a("writeAudio error, no active session.");
                i3 = 21004;
            } else if (bArr == null || bArr.length <= 0) {
                cb.a("writeAudio error,buffer is null.");
            } else if (bArr.length < i2 + i) {
                cb.a("writeAudio error,buffer length < length.");
            } else if (((ax) this.e).a() != -1) {
                i3 = 10106;
            } else {
                i3 = ((ax) this.e).a(bArr, i, i2);
            }
        }
        return i3;
    }

    public void b(String str) {
        synchronized (this.d) {
            if (this.e != null) {
                ((ax) this.e).o().a(str);
            }
        }
    }

    public void cancel(boolean z) {
        synchronized (this.d) {
            f();
            super.cancel(z);
        }
    }

    public void e() {
        synchronized (this.d) {
            if (this.e != null) {
                ((ax) this.e).a(true);
            }
        }
    }

    protected void f() {
        if (this.e != null) {
            String e = this.e.x().e(SpeechConstant.ASR_AUDIO_PATH);
            if (!TextUtils.isEmpty(e) && bv.a(((ax) this.e).b(), e)) {
                bv.a(this.e.x().b(SpeechConstant.AUDIO_FORMAT, null), e, this.e.x().a("sample_rate", this.e.s));
            }
        }
        bw.b(this.a, Boolean.valueOf(this.g), null);
    }

    public boolean g() {
        return d();
    }

    protected boolean h() {
        return TextUtils.isEmpty(this.c.e("bos_dispose")) ? AudioDetector.TYPE_META.equalsIgnoreCase(this.c.e(AudioDetector.VAD_ENGINE)) : this.c.a("bos_dispose", false);
    }
}
