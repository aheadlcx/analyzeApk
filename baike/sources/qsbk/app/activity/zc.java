package qsbk.app.activity;

import qsbk.app.Constants;
import qsbk.app.widget.PersonalInfoView.OnReloadingInfoClicekListener;

class zc implements OnReloadingInfoClicekListener {
    final /* synthetic */ zb a;

    zc(zb zbVar) {
        this.a = zbVar;
    }

    public void onReloadingInfoClickListener() {
        OtherInfoActivity.a(this.a.a, Constants.URL_USER_INFO, String.format(Constants.URL_USER_INFO, new Object[]{OtherInfoActivity.d(this.a.a)}), null);
    }
}
