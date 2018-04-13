package qsbk.app.im.group.vote;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import qsbk.app.utils.DebugUtil;

class b implements OnItemSelectedListener {
    final /* synthetic */ GroupManagerVoteActivity a;

    b(GroupManagerVoteActivity groupManagerVoteActivity) {
        this.a = groupManagerVoteActivity;
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        DebugUtil.debug(GroupManagerVoteActivity.b, "mAvatarGalley, position=" + i);
        if (i <= this.a.o && i < this.a.o) {
        }
        this.a.o = i;
        this.a.e.setItemSelection(i);
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
        DebugUtil.debug(GroupManagerVoteActivity.b, "onNothingSelected ");
    }
}
