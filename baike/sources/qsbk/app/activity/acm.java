package qsbk.app.activity;

class acm implements Runnable {
    final /* synthetic */ SettingPersonalBigCoverBaseActivity a;

    acm(SettingPersonalBigCoverBaseActivity settingPersonalBigCoverBaseActivity) {
        this.a = settingPersonalBigCoverBaseActivity;
    }

    public void run() {
        this.a.d();
        this.a.e.show();
    }
}
