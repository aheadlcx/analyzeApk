package com.iflytek.speech;

import android.content.Context;
import android.content.Intent;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.thirdparty.cb;
import com.iflytek.speech.aidl.ISpeechUnderstander;

public class SpeechUnderstanderAidl extends SpeechModuleAidl<ISpeechUnderstander> {
    public SpeechUnderstanderAidl(Context context, InitListener initListener) {
        super(context, initListener, UtilityConfig.ACTION_SPEECH_UNDERSTANDER);
    }

    public int cancel(SpeechUnderstanderListener speechUnderstanderListener) {
        if (this.mService == null) {
            return 21003;
        }
        if (speechUnderstanderListener == null) {
            return 20012;
        }
        try {
            ((ISpeechUnderstander) this.mService).cancel(speechUnderstanderListener);
            return 0;
        } catch (Throwable e) {
            cb.a(e);
            return 21004;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 21004;
        }
    }

    public /* bridge */ /* synthetic */ boolean destory() {
        return super.destory();
    }

    public /* bridge */ /* synthetic */ Intent getIntent() {
        return super.getIntent();
    }

    public String getParameter(String str) {
        return super.getParameter(str);
    }

    public /* bridge */ /* synthetic */ boolean isActionInstalled(Context context, String str) {
        return super.isActionInstalled(context, str);
    }

    public /* bridge */ /* synthetic */ boolean isAvailable() {
        return super.isAvailable();
    }

    public boolean isUnderstanding() {
        try {
            return this.mService != null ? ((ISpeechUnderstander) this.mService).isUnderstanding() : false;
        } catch (Throwable e) {
            cb.a(e);
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public int setParameter(String str, String str2) {
        return super.setParameter(str, str2);
    }

    public int startUnderstanding(SpeechUnderstanderListener speechUnderstanderListener) {
        if (this.mService == null) {
            return 21003;
        }
        if (speechUnderstanderListener == null) {
            return 20012;
        }
        try {
            ((ISpeechUnderstander) this.mService).startUnderstanding(getIntent(), speechUnderstanderListener);
            return 0;
        } catch (Throwable e) {
            cb.a(e);
            return 21004;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 21004;
        }
    }

    public int stopUnderstanding(SpeechUnderstanderListener speechUnderstanderListener) {
        if (this.mService == null) {
            return 21003;
        }
        if (speechUnderstanderListener == null) {
            return 20012;
        }
        try {
            ((ISpeechUnderstander) this.mService).stopUnderstanding(speechUnderstanderListener);
            return 0;
        } catch (Throwable e) {
            cb.a(e);
            return 21004;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 21004;
        }
    }

    public int writeAudio(byte[] bArr, int i, int i2) {
        if (SpeechUtility.getUtility().getServiceVersion() < 44) {
            return 20018;
        }
        if (this.mService == null) {
            return 21003;
        }
        try {
            ((ISpeechUnderstander) this.mService).writeAudio(getIntent(), bArr, i, i2);
            return 0;
        } catch (Throwable e) {
            cb.a(e);
            return 0;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }
}
