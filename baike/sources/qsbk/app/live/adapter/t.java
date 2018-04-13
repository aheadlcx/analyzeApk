package qsbk.app.live.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.text.SpannableStringBuilder;
import qsbk.app.live.adapter.LiveMessageAdapter.ViewHolder;

class t extends AnimatorListenerAdapter {
    final /* synthetic */ MutableForegroundColorSpan a;
    final /* synthetic */ int b;
    final /* synthetic */ ViewHolder c;
    final /* synthetic */ SpannableStringBuilder d;
    final /* synthetic */ LiveMessageAdapter e;

    t(LiveMessageAdapter liveMessageAdapter, MutableForegroundColorSpan mutableForegroundColorSpan, int i, ViewHolder viewHolder, SpannableStringBuilder spannableStringBuilder) {
        this.e = liveMessageAdapter;
        this.a = mutableForegroundColorSpan;
        this.b = i;
        this.c = viewHolder;
        this.d = spannableStringBuilder;
    }

    public void onAnimationEnd(Animator animator) {
        this.a.setForegroundColor(this.b);
        this.c.tvComment.setText(this.d);
    }
}
