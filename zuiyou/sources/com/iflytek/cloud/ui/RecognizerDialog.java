package com.iflytek.cloud.ui;

import android.content.Context;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.resource.Resource;
import com.iflytek.cloud.thirdparty.cu;
import java.util.Locale;

public class RecognizerDialog extends cu {
    public RecognizerDialog(Context context, InitListener initListener) {
        super(context);
        this.a = new a(context, initListener);
    }

    public void dismiss() {
        super.dismiss();
    }

    public void setListener(RecognizerDialogListener recognizerDialogListener) {
        ((a) this.a).setResultListener(recognizerDialogListener);
    }

    public void setParameter(String str, String str2) {
        ((a) this.a).a(str, str2);
    }

    public void setUILanguage(Locale locale) {
        Resource.setUILanguage(locale);
    }

    public void show() {
        super.show();
    }
}
