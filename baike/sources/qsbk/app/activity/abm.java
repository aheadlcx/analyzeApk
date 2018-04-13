package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class abm implements OnClickListener {
    final /* synthetic */ SearchGroupActivity a;

    abm(SearchGroupActivity searchGroupActivity) {
        this.a = searchGroupActivity;
    }

    public void onClick(View view) {
        this.a.a.setText("");
    }
}
