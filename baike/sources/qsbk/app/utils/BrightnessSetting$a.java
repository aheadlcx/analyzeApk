package qsbk.app.utils;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import qsbk.app.QsbkApp;

class BrightnessSetting$a implements OnCheckedChangeListener {
    final /* synthetic */ BrightnessSetting a;

    BrightnessSetting$a(BrightnessSetting brightnessSetting) {
        this.a = brightnessSetting;
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        int i;
        if (z) {
            BrightnessSetting.b(this.a).setEnabled(false);
            i = QsbkApp.brightness;
        } else {
            BrightnessSetting.b(this.a).setEnabled(true);
            i = BrightnessSetting.c(this.a);
        }
        BrightnessSetting.b(this.a).setProgress(i);
        BrightnessSetting.a(this.a, i);
    }
}
