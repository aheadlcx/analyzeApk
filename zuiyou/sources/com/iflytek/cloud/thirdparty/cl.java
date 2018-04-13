package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.iflytek.cloud.EvaluatorListener;
import com.iflytek.cloud.EvaluatorResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;

public class cl extends bi {
    private boolean g = false;

    private final class a implements EvaluatorListener {
        final /* synthetic */ cl a;
        private EvaluatorListener b = null;
        private Handler c = new Handler(this, Looper.getMainLooper()) {
            final /* synthetic */ a a;

            public void handleMessage(Message message) {
                boolean z = true;
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
                            EvaluatorListener a = this.a.b;
                            EvaluatorResult evaluatorResult = (EvaluatorResult) message.obj;
                            if (message.arg1 != 1) {
                                z = false;
                            }
                            a.onResult(evaluatorResult, z);
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

        public a(cl clVar, EvaluatorListener evaluatorListener) {
            this.a = clVar;
            this.b = evaluatorListener;
        }

        public void onBeginOfSpeech() {
            cb.a("onBeginOfSpeech");
            this.c.sendMessage(this.c.obtainMessage(2, 0, 0, null));
        }

        public void onEndOfSpeech() {
            this.c.sendMessage(this.c.obtainMessage(3, 0, 0, null));
        }

        public void onError(SpeechError speechError) {
            this.a.f();
            this.c.sendMessage(this.c.obtainMessage(0, speechError));
        }

        public void onEvent(int i, int i2, int i3, Bundle bundle) {
            Message message = new Message();
            message.what = i;
            message.arg1 = i2;
            message.arg2 = i3;
            message.obj = bundle;
            Message.obtain(this.c, 6, message).sendToTarget();
        }

        public void onResult(EvaluatorResult evaluatorResult, boolean z) {
            int i = 1;
            if (z) {
                this.a.f();
            }
            Handler handler = this.c;
            if (!z) {
                i = 0;
            }
            this.c.sendMessage(handler.obtainMessage(4, i, 0, evaluatorResult));
        }

        public void onVolumeChanged(int i, byte[] bArr) {
            this.c.sendMessage(this.c.obtainMessage(1, i, 0, bArr));
        }
    }

    public cl(Context context) {
        super(context);
    }

    public int a(String str, String str2, EvaluatorListener evaluatorListener) {
        int i;
        synchronized (this.d) {
            try {
                this.g = this.c.a(SpeechConstant.KEY_REQUEST_FOCUS, true);
                if (this.e != null && this.e.v()) {
                    this.e.b(this.c.a(SpeechConstant.ISE_INTERRUPT_ERROR, false));
                }
                this.e = new av(this.a, this.c, a(SpeechConstant.ENG_EVA));
                bw.a(this.a, Boolean.valueOf(this.g), null);
                ((av) this.e).a(str, str2, new a(this, evaluatorListener));
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

    public int a(byte[] bArr, String str, EvaluatorListener evaluatorListener) {
        int i;
        synchronized (this.d) {
            try {
                this.g = this.c.a(SpeechConstant.KEY_REQUEST_FOCUS, true);
                if (this.e != null && this.e.v()) {
                    this.e.b(this.c.a(SpeechConstant.ISE_INTERRUPT_ERROR, false));
                }
                this.e = new av(this.a, this.c, a(SpeechConstant.ENG_EVA));
                bw.a(this.a, Boolean.valueOf(this.g), null);
                ((av) this.e).a(bArr, str, new a(this, evaluatorListener));
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

    public boolean a(byte[] bArr, int i, int i2) {
        boolean z = false;
        synchronized (this.d) {
            if (this.e == null) {
                cb.a("writeAudio error, no active session.");
            } else if (bArr == null || bArr.length <= 0) {
                cb.a("writeAudio error,buffer is null.");
            } else if (bArr.length < i2 + i) {
                cb.a("writeAudio error,buffer length < length.");
            } else {
                ((av) this.e).onRecordBuffer(bArr, i, i2);
                z = true;
            }
        }
        return z;
    }

    protected boolean b_() {
        return super.b_();
    }

    public void cancel(boolean z) {
        synchronized (this.d) {
            f();
        }
        super.cancel(z);
    }

    public void e() {
        synchronized (this.d) {
            if (this.e != null) {
                ((av) this.e).a(true);
            }
        }
    }

    protected void f() {
        if (this.e != null) {
            String e = this.e.x().e(SpeechConstant.ISE_AUDIO_PATH);
            if (!TextUtils.isEmpty(e) && bv.a(((av) this.e).d(), e)) {
                bv.a(this.e.x().b(SpeechConstant.AUDIO_FORMAT, null), e, this.e.x().a("sample_rate", this.e.s));
            }
        }
        bw.b(this.a, Boolean.valueOf(this.g), null);
    }

    public boolean g() {
        return d();
    }
}
