package qsbk.app.fragments;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import qsbk.app.R;
import qsbk.app.adapter.ArticleAdapter.ViewHolder;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.QsjxCell;

class kx implements Runnable {
    final /* synthetic */ SubscribeFragment a;

    kx(SubscribeFragment subscribeFragment) {
        this.a = subscribeFragment;
    }

    public void run() {
        View view = null;
        View childAt;
        if (SubscribeFragment.d >= 0 && this.a.j.size() > SubscribeFragment.d && this.a.m != null) {
            this.a.m.setSelection(SubscribeFragment.d);
            if (SubscribeFragment.d > 0) {
                childAt = this.a.m.getChildAt(SubscribeFragment.d - 1);
            } else {
                childAt = null;
            }
            if (childAt == null || !(childAt.getTag() instanceof QsjxCell)) {
                childAt = this.a.m.getChildAt(SubscribeFragment.d);
            }
            if (childAt != null && (childAt.getTag() instanceof QsjxCell)) {
                view = childAt.findViewById(R.id.layerMask);
            }
        } else if (SubscribeFragment.c >= 0 && this.a.j.size() > SubscribeFragment.c && this.a.m != null) {
            this.a.m.setSelection(SubscribeFragment.c);
            if (SubscribeFragment.c > 0) {
                childAt = this.a.m.getChildAt(SubscribeFragment.c - 1);
            } else {
                childAt = null;
            }
            if (childAt == null || !(childAt.getTag() instanceof ViewHolder)) {
                childAt = this.a.m.getChildAt(SubscribeFragment.c);
            }
            if (childAt != null && (childAt.getTag() instanceof ViewHolder)) {
                view = childAt.findViewById(R.id.layerMask);
            }
        }
        if (view != null) {
            view.setVisibility(0);
            ValueAnimator ofInt = ObjectAnimator.ofInt(view, "backgroundColor", new int[]{UIHelper.getColor(R.color.transparent), 1291827968});
            ofInt.setDuration(500);
            ofInt.setEvaluator(new ArgbEvaluator());
            ofInt.setRepeatCount(3);
            ofInt.setRepeatMode(2);
            ofInt.start();
        }
    }
}
