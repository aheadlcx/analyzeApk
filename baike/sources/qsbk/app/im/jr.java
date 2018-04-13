package qsbk.app.im;

import qsbk.app.core.utils.PreferenceUtils;

class jr implements Runnable {
    final /* synthetic */ VoiceUIHelper a;

    jr(VoiceUIHelper voiceUIHelper) {
        this.a = voiceUIHelper;
    }

    public void run() {
        if (this.a.d == 1 && !this.a.w && !PreferenceUtils.instance().getBoolean("show_premission_dialog", false)) {
            this.a.a(3);
            this.a.e().show();
            PreferenceUtils.instance().putBoolean("show_premission_dialog", true);
        }
    }
}
