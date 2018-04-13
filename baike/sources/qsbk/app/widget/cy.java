package qsbk.app.widget;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.widget.PersonalInfoView.OnReloadingInfoClicekListener;

class cy implements OnClickListener {
    final /* synthetic */ OnReloadingInfoClicekListener a;
    final /* synthetic */ PersonalInfoView b;

    cy(PersonalInfoView personalInfoView, OnReloadingInfoClicekListener onReloadingInfoClicekListener) {
        this.b = personalInfoView;
        this.a = onReloadingInfoClicekListener;
    }

    public void onClick(View view) {
        PersonalInfoView.c(this.b).setVisibility(8);
        this.a.onReloadingInfoClickListener();
    }
}
