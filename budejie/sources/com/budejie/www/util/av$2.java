package com.budejie.www.util;

import android.app.Dialog;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import com.budejie.www.R;
import java.util.Map;

class av$2 extends Handler {
    final /* synthetic */ Map a;
    final /* synthetic */ View b;
    final /* synthetic */ Dialog c;
    final /* synthetic */ av d;

    av$2(av avVar, Map map, View view, Dialog dialog) {
        this.d = avVar;
        this.a = map;
        this.b = view;
        this.c = dialog;
    }

    public void handleMessage(Message message) {
        if (message.what < 10) {
            View view = (View) this.a.get(Integer.valueOf(message.what));
            view.setVisibility(0);
            view.startAnimation(AnimationUtils.loadAnimation(av.a(this.d), R.anim.in_translate_top));
            return;
        }
        view = (View) this.a.get(Integer.valueOf(message.what - 10));
        view.setVisibility(4);
        Animation loadAnimation = AnimationUtils.loadAnimation(av.a(this.d), R.anim.out_translate_top);
        if (view.getId() == R.id.send_text) {
            loadAnimation.setAnimationListener(new AnimationListener(this) {
                final /* synthetic */ av$2 a;

                {
                    this.a = r1;
                }

                public void onAnimationStart(Animation animation) {
                    this.a.b.setClickable(false);
                }

                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    this.a.c.dismiss();
                }
            });
        }
        view.startAnimation(loadAnimation);
    }
}
