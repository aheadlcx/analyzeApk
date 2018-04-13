package cn.v6.sixrooms.ui.phone;

import android.view.View;
import android.view.View.OnClickListener;
import cn.v6.sixrooms.R;

final class aa implements OnClickListener {
    final /* synthetic */ EventActivity a;

    aa(EventActivity eventActivity) {
        this.a = eventActivity;
    }

    public final void onClick(View view) {
        this.a.finish();
        this.a.overridePendingTransition(R.anim.msg_verify_fragment_in, R.anim.msg_verify_fragment_out);
    }
}
