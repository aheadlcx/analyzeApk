package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class ti implements OnClickListener {
    final /* synthetic */ MemberChooseActivity a;

    ti(MemberChooseActivity memberChooseActivity) {
        this.a = memberChooseActivity;
    }

    public void onClick(View view) {
        this.a.n.setText("");
    }
}
