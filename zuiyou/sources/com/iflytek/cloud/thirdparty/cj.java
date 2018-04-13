package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.iflytek.cloud.IdentityListener;
import com.iflytek.cloud.IdentityResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;

public class cj extends bi {
    private boolean g = false;

    private final class a implements IdentityListener {
        final /* synthetic */ cj a;
        private IdentityListener b = null;
        private boolean c = false;
        private Handler d = new Handler(this, Looper.getMainLooper()) {
            final /* synthetic */ a a;

            public void handleMessage(Message message) {
                if (this.a.b != null) {
                    switch (message.what) {
                        case 0:
                            this.a.b.onError((SpeechError) message.obj);
                            break;
                        case 4:
                            this.a.b.onResult((IdentityResult) message.obj, message.arg1 == 1);
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

        public a(cj cjVar, IdentityListener identityListener) {
            this.a = cjVar;
            this.b = identityListener;
        }

        protected void a() {
            ((bd) this.a.e).a().a();
            bw.b(this.a.a, Boolean.valueOf(this.a.g), null);
        }

        public void onError(SpeechError speechError) {
            a();
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

        public void onResult(IdentityResult identityResult, boolean z) {
            int i = 1;
            if (z) {
                a();
            }
            Handler handler = this.d;
            if (!z) {
                i = 0;
            }
            this.d.sendMessage(handler.obtainMessage(4, i, 0, identityResult));
        }
    }

    public cj(Context context) {
        super(context);
    }

    public int a(IdentityListener identityListener) {
        int i;
        synchronized (this.d) {
            try {
                this.g = this.c.a(SpeechConstant.KEY_REQUEST_FOCUS, true);
                if (this.e != null && this.e.v()) {
                    this.e.b(this.c.a(SpeechConstant.MFV_INTERRUPT_ERROR, false));
                }
                this.e = new bd(this.a, this.c, a("mfv"));
                bw.a(this.a, Boolean.valueOf(this.g), null);
                ((bd) this.e).a(new a(this, identityListener));
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

    public int a(String str, String str2, String str3, IdentityListener identityListener) {
        setParameter("sst", str2);
        int a = a(identityListener);
        if (a != 0) {
            return a;
        }
        a = a(str, str3, null, 0, 0);
        c(str);
        return a;
    }

    public int a(String str, String str2, byte[] bArr, int i, int i2) {
        int i3 = 10109;
        synchronized (this.d) {
            if (this.e == null) {
                cb.c("writeAudio error, no active session.");
                i3 = 21004;
            } else if (i2 < 0) {
                cb.c("writeAudio error, length < 0.");
            } else {
                if (bArr != null) {
                    if (bArr.length < i2 + i) {
                        cb.c("writeAudio error, buffer length < offset + length.");
                    }
                }
                ((bd) this.e).a(str, str2, bArr, i, i2);
                i3 = 0;
            }
        }
        return i3;
    }

    public void b(String str) {
        synchronized (this.d) {
            if (this.e != null) {
                ((bd) this.e).k().a(str);
            }
        }
    }

    public void c(String str) {
        synchronized (this.d) {
            if (this.e != null) {
                ((bd) this.e).a(str, true);
            }
        }
    }

    public void cancel(boolean z) {
        bw.b(this.a, Boolean.valueOf(this.g), null);
        super.cancel(z);
    }

    public boolean e() {
        return d();
    }
}
