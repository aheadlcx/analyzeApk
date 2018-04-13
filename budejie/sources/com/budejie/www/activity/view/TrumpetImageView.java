package com.budejie.www.activity.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.budejie.www.R;

public class TrumpetImageView extends ImageView {
    AnimationDrawable a;

    public TrumpetImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void a(boolean z) {
        if (this.a != null) {
            this.a.stop();
        }
        if (z) {
            setBackgroundResource(R.anim.audio_play_anim);
            this.a = (AnimationDrawable) getBackground();
        } else {
            setBackgroundResource(R.anim.audio_play_anim);
            this.a = (AnimationDrawable) getBackground();
        }
        if (this.a != null) {
            this.a.setOneShot(false);
            this.a.start();
        }
    }

    public void b(boolean z) {
        if (this.a != null) {
            this.a.stop();
        }
        if (z) {
            setBackgroundResource(R.drawable.ic_trumpet_1);
        } else {
            setBackgroundResource(R.drawable.ic_trumpet_1);
        }
    }
}
