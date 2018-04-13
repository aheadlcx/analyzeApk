package cn.v6.sixrooms.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

final class j implements OnClickListener {
    final /* synthetic */ UsernameListAdapter a;

    j(UsernameListAdapter usernameListAdapter) {
        this.a = usernameListAdapter;
    }

    public final void onClick(View view) {
        UsernameListAdapter.a(this.a, ((TextView) view).getText().toString());
    }
}
