package qsbk.app.live.adapter;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.text.SpannableStringBuilder;
import qsbk.app.live.adapter.LiveMessageAdapter.ViewHolder;

class s implements AnimatorUpdateListener {
    final /* synthetic */ ViewHolder a;
    final /* synthetic */ SpannableStringBuilder b;
    final /* synthetic */ LiveMessageAdapter c;

    s(LiveMessageAdapter liveMessageAdapter, ViewHolder viewHolder, SpannableStringBuilder spannableStringBuilder) {
        this.c = liveMessageAdapter;
        this.a = viewHolder;
        this.b = spannableStringBuilder;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.a.tvComment.setText(this.b);
    }
}
