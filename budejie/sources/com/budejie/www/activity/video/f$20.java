package com.budejie.www.activity.video;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import com.budejie.www.busevent.SimpleVideoClickAction;
import de.greenrobot.event.EventBus;

class f$20 implements OnClickListener {
    final /* synthetic */ f a;

    f$20(f fVar) {
        this.a = fVar;
    }

    public void onClick(View view) {
        Log.d("MicroMediaController", "mPlaySimpleView onclick");
        EventBus.getDefault().post(SimpleVideoClickAction.TO_NORMAL);
    }
}
