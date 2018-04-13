package qsbk.app.fragments;

import android.app.AlertDialog.Builder;
import android.view.View;
import android.view.View.OnClickListener;

class es implements OnClickListener {
    final /* synthetic */ LiveTabsFragment a;

    es(LiveTabsFragment liveTabsFragment) {
        this.a = liveTabsFragment;
    }

    public void onClick(View view) {
        new Builder(this.a.getActivity()).setTitle("").setItems(new String[]{"创建家族", "发起直播"}, new et(this)).show();
    }
}
