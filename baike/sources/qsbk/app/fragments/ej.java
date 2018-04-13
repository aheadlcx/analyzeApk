package qsbk.app.fragments;

import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.support.design.widget.TabLayout.Tab;
import java.util.List;

class ej implements OnTabSelectedListener {
    final /* synthetic */ LaiseeVoiceSendFragment a;

    ej(LaiseeVoiceSendFragment laiseeVoiceSendFragment) {
        this.a = laiseeVoiceSendFragment;
    }

    public void onTabSelected(Tab tab) {
        if (this.a.c.size() > tab.getPosition()) {
            this.a.P = (String) this.a.c.get(tab.getPosition());
            List list = (List) this.a.b.get(this.a.P);
            if (list != null && list.size() > 0) {
                this.a.Q = (b) list.get(0);
                this.a.d.setText(this.a.Q.b);
            }
        }
    }

    public void onTabUnselected(Tab tab) {
    }

    public void onTabReselected(Tab tab) {
    }
}
