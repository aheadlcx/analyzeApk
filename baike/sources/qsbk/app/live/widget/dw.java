package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import qsbk.app.core.model.GiftData;
import qsbk.app.live.widget.GiftGridView.OnGiftItemClickListener;

class dw implements OnClickListener {
    final /* synthetic */ GiftData a;
    final /* synthetic */ b b;
    final /* synthetic */ int c;
    final /* synthetic */ a d;

    dw(a aVar, GiftData giftData, b bVar, int i) {
        this.d = aVar;
        this.a = giftData;
        this.b = bVar;
        this.c = i;
    }

    public void onClick(View view) {
        int i;
        if (this.a.s != 1) {
            this.a.selected = !this.a.selected;
        }
        this.b.b.setSelected(this.a.selected);
        ImageView imageView = this.b.b;
        if (this.a.selected || this.a.cb) {
            i = 0;
        } else {
            i = 8;
        }
        imageView.setVisibility(i);
        for (int i2 = 0; i2 < this.d.a.c.size(); i2++) {
            if (this.d.a.c.get(i2) != null) {
                ((OnGiftItemClickListener) this.d.a.c.get(i2)).onGiftItemClick(this.c, this.a);
            }
        }
        if (this.d.a.d != this.c) {
            if (this.d.a.d >= 0) {
                ((GiftData) this.d.a.a.get(this.d.a.d)).selected = false;
                if (((GiftData) this.d.a.a.get(this.d.a.d)).mAnimator != null) {
                    ((GiftData) this.d.a.a.get(this.d.a.d)).mAnimator = null;
                }
            }
            this.d.a.d = this.c;
            this.d.notifyDataSetChanged();
        }
        if (this.a.selected) {
            if (this.a.mAnimator == null) {
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.b.a, View.SCALE_X, new float[]{1.0f, 1.15f});
                ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.b.a, View.SCALE_Y, new float[]{1.0f, 1.15f});
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
                animatorSet.setDuration(400);
                ofFloat = ObjectAnimator.ofFloat(this.b.a, View.SCALE_X, new float[]{1.15f, 1.0f});
                ofFloat2 = ObjectAnimator.ofFloat(this.b.a, View.SCALE_Y, new float[]{1.15f, 1.0f});
                AnimatorSet animatorSet2 = new AnimatorSet();
                animatorSet2.playTogether(new Animator[]{ofFloat, ofFloat2});
                animatorSet2.setDuration(600);
                this.a.mAnimator = new AnimatorSet();
                this.a.mAnimator.playSequentially(new Animator[]{animatorSet, animatorSet2});
                this.a.mAnimator.addListener(new dx(this));
            }
            this.a.mAnimator.start();
        } else if (this.a.mAnimator != null) {
            this.a.mAnimator = null;
        }
    }
}
