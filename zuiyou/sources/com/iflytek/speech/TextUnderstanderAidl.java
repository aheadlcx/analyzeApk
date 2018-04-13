package com.iflytek.speech;

import android.content.Context;
import android.content.Intent;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.thirdparty.cb;
import com.iflytek.speech.aidl.ITextUnderstander;

public class TextUnderstanderAidl extends SpeechModuleAidl<ITextUnderstander> {
    public static final String SCENE = "scene";
    private static final String TEXT = "text";

    public TextUnderstanderAidl(Context context, InitListener initListener) {
        super(context, initListener, UtilityConfig.ACTION_TEXT_UNDERSTANDER);
    }

    public int cancel(TextUnderstanderListener textUnderstanderListener) {
        if (this.mService == null) {
            return 21003;
        }
        if (textUnderstanderListener == null) {
            return 20012;
        }
        try {
            ((ITextUnderstander) this.mService).cancel(textUnderstanderListener);
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
            return this.mService != null ? ((ITextUnderstander) this.mService).isUnderstanding() : false;
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

    public int understandText(String str, TextUnderstanderListener textUnderstanderListener) {
        if (this.mService == null) {
            return 21003;
        }
        if (textUnderstanderListener == null) {
            return 20012;
        }
        try {
            Intent intent = getIntent();
            intent.putExtra(TEXT, str);
            ((ITextUnderstander) this.mService).understandText(intent, textUnderstanderListener);
            return 0;
        } catch (Throwable e) {
            cb.a(e);
            return 21004;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 21004;
        }
    }
}
