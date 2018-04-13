package qsbk.app.utils;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.tencent.bugly.Bugly;

class b implements OnClickListener {
    final /* synthetic */ BrightnessSetting a;

    b(BrightnessSetting brightnessSetting) {
        this.a = brightnessSetting;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (BrightnessSetting.a(this.a).isChecked()) {
            if (UIHelper.isNightTheme()) {
                SharePreferenceUtils.setSharePreferencesValue("isFlollowSystem_night", "true");
            } else {
                SharePreferenceUtils.setSharePreferencesValue("isFlollowSystem", "true");
            }
        } else if (UIHelper.isNightTheme()) {
            SharePreferenceUtils.setSharePreferencesValue("isFlollowSystem_night", Bugly.SDK_IS_DEV);
            SharePreferenceUtils.setSharePreferencesValue("brightness_night", String.valueOf(BrightnessSetting.b(this.a).getProgress()));
        } else {
            SharePreferenceUtils.setSharePreferencesValue("isFlollowSystem", Bugly.SDK_IS_DEV);
            SharePreferenceUtils.setSharePreferencesValue("brightness", String.valueOf(BrightnessSetting.b(this.a).getProgress()));
        }
    }
}
