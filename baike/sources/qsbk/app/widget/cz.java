package qsbk.app.widget;

import android.view.View;
import android.view.View.OnClickListener;

class cz implements OnClickListener {
    final /* synthetic */ PersonalInfoView a;

    cz(PersonalInfoView personalInfoView) {
        this.a = personalInfoView;
    }

    public void onClick(View view) {
        PersonalInfoView.d(this.a).setVisibility(8);
        PersonalInfoView.a(this.a, 1);
    }
}
