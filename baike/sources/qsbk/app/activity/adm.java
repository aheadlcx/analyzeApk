package qsbk.app.activity;

import android.util.Pair;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.TemporaryNoteActivity.GroupNoteAdapter;
import qsbk.app.utils.LogUtil;

class adm implements OnClickListener {
    final /* synthetic */ a a;
    final /* synthetic */ int b;
    final /* synthetic */ Pair c;
    final /* synthetic */ GroupNoteAdapter d;

    adm(GroupNoteAdapter groupNoteAdapter, a aVar, int i, Pair pair) {
        this.d = groupNoteAdapter;
        this.a = aVar;
        this.b = i;
        this.c = pair;
    }

    public void onClick(View view) {
        if (this.a.joinGroupSwitch.isChecked()) {
            boolean z;
            this.d.a.q.set(this.b, new Pair(this.c.first, Integer.valueOf(1)));
            int i;
            if (this.b == 0) {
                z = true;
                for (i = 1; i < this.d.a.q.size(); i++) {
                    LogUtil.d("((Pair)groupSwitchInfos.get(i)).second ===" + ((Pair) this.d.getItem(i)).second);
                    if (((Integer) ((Pair) this.d.a.q.get(i)).second).intValue() == 0) {
                        z = false;
                    }
                }
            } else if (this.b == this.d.a.q.size() - 1) {
                z = true;
                for (i = 0; i < this.d.a.q.size() - 1; i++) {
                    if (((Integer) ((Pair) this.d.a.q.get(i)).second).intValue() == 0) {
                        z = false;
                    }
                }
            } else {
                z = true;
                for (i = 0; i < this.b; i++) {
                    if (((Integer) ((Pair) this.d.a.q.get(i)).second).intValue() == 0) {
                        z = false;
                    }
                }
                for (i = this.b + 1; i < this.d.a.q.size(); i++) {
                    if (((Integer) ((Pair) this.d.a.q.get(i)).second).intValue() == 0) {
                        z = false;
                    }
                }
            }
            if (z) {
                this.d.a.f.setChecked(true);
                return;
            }
            return;
        }
        this.d.a.q.set(this.b, new Pair(this.c.first, Integer.valueOf(0)));
        this.d.a.f.setChecked(false);
    }
}
