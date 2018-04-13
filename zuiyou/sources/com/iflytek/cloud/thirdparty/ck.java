package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.iflytek.cloud.DataDownloader;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechListener;
import com.iflytek.cloud.VerifierListener;
import com.iflytek.cloud.VerifierResult;
import java.util.Random;

public class ck extends bi {
    private boolean g = false;

    private final class a implements VerifierListener {
        final /* synthetic */ ck a;
        private VerifierListener b = null;
        private Handler c = new Handler(this, Looper.getMainLooper()) {
            final /* synthetic */ a a;

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
                            this.a.b.onResult((VerifierResult) message.obj);
                            break;
                        case 5:
                            Message message2 = (Message) message.obj;
                            this.a.b.onEvent(message2.what, message2.arg1, message2.arg2, (Bundle) message2.obj);
                            break;
                    }
                    super.handleMessage(message);
                }
            }
        };

        public a(ck ckVar, VerifierListener verifierListener) {
            this.a = ckVar;
            this.b = verifierListener;
        }

        protected void a() {
            String e = this.a.e.x().e(SpeechConstant.ISV_AUDIO_PATH);
            if (!TextUtils.isEmpty(e) && bv.a(((ba) this.a.e).j(), e)) {
                bv.a(this.a.e.x().b(SpeechConstant.AUDIO_FORMAT, null), e, this.a.e.x().a("sample_rate", this.a.e.s));
            }
            bw.b(this.a.a, Boolean.valueOf(this.a.g), null);
        }

        public void onBeginOfSpeech() {
            this.c.sendMessage(this.c.obtainMessage(2, 0, 0, null));
        }

        public void onEndOfSpeech() {
            this.c.sendMessage(this.c.obtainMessage(3, 0, 0, null));
        }

        public void onError(SpeechError speechError) {
            a();
            this.c.sendMessage(this.c.obtainMessage(0, speechError));
        }

        public void onEvent(int i, int i2, int i3, Bundle bundle) {
            Message obtain = Message.obtain();
            obtain.what = i;
            obtain.arg1 = i2;
            obtain.arg2 = i3;
            obtain.obj = bundle;
            Message.obtain(this.c, 5, obtain).sendToTarget();
        }

        public void onResult(VerifierResult verifierResult) {
            a();
            this.c.sendMessage(this.c.obtainMessage(4, verifierResult));
        }

        public void onVolumeChanged(int i, byte[] bArr) {
            this.c.sendMessage(this.c.obtainMessage(1, i, 0, bArr));
        }
    }

    public ck(Context context) {
        super(context);
    }

    public int a(VerifierListener verifierListener) {
        int i;
        synchronized (this.d) {
            try {
                this.g = this.c.a(SpeechConstant.KEY_REQUEST_FOCUS, true);
                if (this.e != null && this.e.v()) {
                    this.e.b(this.c.a(SpeechConstant.ISV_INTERRUPT_ERROR, false));
                }
                this.e = new ba(this.a, this.c, a("verify"));
                bw.a(this.a, Boolean.valueOf(this.g), null);
                ((ba) this.e).a(new a(this, verifierListener));
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

    public int a(String str, String str2, SpeechListener speechListener) {
        int i = 0;
        synchronized (this.d) {
            try {
                this.c.a(SpeechConstant.ISV_CMD, str);
                this.c.a(SpeechConstant.AUTH_ID, str2);
                new az(this.a, a("manager")).a(this.c, new com.iflytek.cloud.thirdparty.az.a(speechListener));
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
            } else if (((ba) this.e).k() != -1) {
                i3 = 10106;
            } else {
                ((ba) this.e).onRecordBuffer(bArr, i, i2);
                i3 = 0;
            }
        }
        return i3;
    }

    public String a(int i) {
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();
        String str = "023456789".charAt(random.nextInt("023456789".length())) + "";
        stringBuffer.append(str);
        for (int i2 = 0; i2 < i - 1; i2++) {
            Boolean valueOf = Boolean.valueOf(false);
            while (!valueOf.booleanValue()) {
                str = "023456789".charAt(random.nextInt("023456789".length())) + "";
                valueOf = stringBuffer.indexOf(str) >= 0 ? Boolean.valueOf(false) : Integer.parseInt(new StringBuilder().append(stringBuffer.charAt(stringBuffer.length() + -1)).append("").toString()) * Integer.parseInt(str) == 10 ? Boolean.valueOf(false) : Boolean.valueOf(true);
            }
            stringBuffer.append(str);
        }
        return stringBuffer.toString();
    }

    public void a(SpeechListener speechListener) {
        DataDownloader dataDownloader = new DataDownloader(this.a);
        dataDownloader.setParameter(this.c);
        dataDownloader.downloadData(speechListener);
    }

    public void e() {
        synchronized (this.d) {
            if (this.e != null) {
                ((ba) this.e).a();
            }
        }
    }

    public boolean f() {
        return d();
    }
}
