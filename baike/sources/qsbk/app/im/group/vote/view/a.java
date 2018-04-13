package qsbk.app.im.group.vote.view;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import qsbk.app.im.group.vote.bean.GroupManagerCandidate;

class a implements OnItemClickListener {
    final /* synthetic */ GroupManagerVoteView a;

    a(GroupManagerVoteView groupManagerVoteView) {
        this.a = groupManagerVoteView;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (this.a.h) {
            this.a.setVoteButtonStyle(1);
            this.a.g = ((GroupManagerCandidate) this.a.e.get(i)).uid;
            this.a.d.setItemSelection(i);
            this.a.d.notifyDataSetChanged();
        }
    }
}
