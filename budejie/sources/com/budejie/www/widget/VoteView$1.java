package com.budejie.www.widget;

import android.view.View;
import android.view.View.OnClickListener;

class VoteView$1 implements OnClickListener {
    final /* synthetic */ VoteView a;

    VoteView$1(VoteView voteView) {
        this.a = voteView;
    }

    public void onClick(View view) {
        this.a.setVisibility(8);
        if (VoteView.a(this.a) != null) {
            VoteView.a(this.a).a();
        }
    }
}
