package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class og implements OnClickListener {
    final /* synthetic */ HighLightQiushiActivity a;

    og(HighLightQiushiActivity highLightQiushiActivity) {
        this.a = highLightQiushiActivity;
    }

    public void onClick(View view) {
        view.setVisibility(8);
        this.a.i();
        if (this.a.h != null) {
            this.a.h.onRefresh();
        }
    }
}
