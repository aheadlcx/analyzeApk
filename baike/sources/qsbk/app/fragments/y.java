package qsbk.app.fragments;

import android.animation.Animator;
import android.view.View;
import qsbk.app.widget.TransitionDraweeView.SimpleAnimationListener;

class y extends SimpleAnimationListener {
    final /* synthetic */ View a;
    final /* synthetic */ BrowseGIFVideoFragment b;

    y(BrowseGIFVideoFragment browseGIFVideoFragment, View view) {
        this.b = browseGIFVideoFragment;
        this.a = view;
    }

    public void onAnimationEnd(Animator animator) {
        super.onAnimationEnd(animator);
        if (this.a != null) {
            this.a.setVisibility(8);
        }
    }
}
