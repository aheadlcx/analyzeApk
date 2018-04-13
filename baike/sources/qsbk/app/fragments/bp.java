package qsbk.app.fragments;

import android.support.v7.app.AlertDialog.Builder;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.R;

class bp implements OnClickListener {
    final /* synthetic */ CircleTopicsFragment$CircleTopicAdapter a;

    bp(CircleTopicsFragment$CircleTopicAdapter circleTopicsFragment$CircleTopicAdapter) {
        this.a = circleTopicsFragment$CircleTopicAdapter;
    }

    public void onClick(View view) {
        new Builder(this.a.a.getActivity(), R.style.MyDialogStyleNormal).setMessage("清空记录?").setPositiveButton(17039370, new br(this)).setNegativeButton(R.string.cancel, new bq(this)).create().show();
    }
}
