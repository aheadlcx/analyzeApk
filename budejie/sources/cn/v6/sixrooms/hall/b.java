package cn.v6.sixrooms.hall;

import android.view.View;
import android.view.View.OnClickListener;

final class b implements OnClickListener {
    final /* synthetic */ AttentionFragment a;

    b(AttentionFragment attentionFragment) {
        this.a = attentionFragment;
    }

    public final void onClick(View view) {
        this.a.getActivity().finish();
    }
}
