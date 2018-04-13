package com.budejie.www.activity.view;

import android.view.View;
import android.view.View.OnClickListener;
import com.budejie.www.activity.video.k;
import com.budejie.www.busevent.DetailAction;
import de.greenrobot.event.EventBus;

class AudioLayout$2 implements OnClickListener {
    final /* synthetic */ AudioLayout a;

    AudioLayout$2(AudioLayout audioLayout) {
        this.a = audioLayout;
    }

    public void onClick(View view) {
        EventBus.getDefault().post(DetailAction.PUB_VIDEO_CLOSE);
        k.a(AudioLayout.d(this.a)).k();
        if (AudioLayout.e(this.a) == null) {
            return;
        }
        if (AudioLayout.e(this.a).l() != this.a.getPlayPath()) {
            if (AudioLayout.e(this.a) != null) {
                AudioLayout.e(this.a).f();
            }
            if (AudioLayout.a(this.a) != null) {
                AudioLayout.a(this.a).setVisibility(0);
            }
            if (AudioLayout.b(this.a) != null) {
                AudioLayout.b(this.a).setVisibility(8);
            }
            if (AudioLayout.c(this.a) != null) {
                AudioLayout.c(this.a).setVisibility(8);
            }
            if (AudioLayout.e(this.a) != null) {
                AudioLayout.e(this.a).c(this.a.getPlayPath());
                AudioLayout.e(this.a).a(this.a.getPlayPath());
                AudioLayout.e(this.a).a(this.a);
            }
        } else if (!AudioLayout.e(this.a).c()) {
            AudioLayout.a(this.a).setVisibility(0);
            AudioLayout.b(this.a).setVisibility(8);
            AudioLayout.c(this.a).setVisibility(8);
            AudioLayout.e(this.a).c(this.a.getPlayPath());
            AudioLayout.e(this.a).a(this.a.getPlayPath());
            AudioLayout.e(this.a).a(this.a);
        } else if (AudioLayout.e(this.a) != null) {
            AudioLayout.e(this.a).i();
            this.a.c();
        }
    }
}
