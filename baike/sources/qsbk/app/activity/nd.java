package qsbk.app.activity;

import android.util.Pair;
import android.view.View;
import android.view.View.OnClickListener;

class nd implements OnClickListener {
    final /* synthetic */ GroupMsgActivity a;

    nd(GroupMsgActivity groupMsgActivity) {
        this.a = groupMsgActivity;
    }

    public void onClick(View view) {
        int i = 0;
        if (this.a.e.isChecked()) {
            if (this.a.j != null && this.a.j.size() > 0) {
                while (i < this.a.j.size()) {
                    this.a.j.set(i, new Pair((Integer) ((Pair) this.a.j.get(i)).first, Integer.valueOf(1)));
                    i++;
                }
            }
        } else if (this.a.j != null && this.a.j.size() > 0) {
            for (int i2 = 0; i2 < this.a.j.size(); i2++) {
                this.a.j.set(i2, new Pair((Integer) ((Pair) this.a.j.get(i2)).first, Integer.valueOf(0)));
            }
        }
        this.a.g.notifyDataSetChanged();
    }
}
