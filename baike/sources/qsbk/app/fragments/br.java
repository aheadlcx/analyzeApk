package qsbk.app.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.utils.CircleTopicManager;

class br implements OnClickListener {
    final /* synthetic */ bp a;

    br(bp bpVar) {
        this.a = bpVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        CircleTopicManager.getInstance().clearLruTopics();
        CircleTopicsFragment.u(this.a.a.a).notifyDataSetChanged();
        CircleTopicsFragment.i(this.a.a.a);
    }
}
