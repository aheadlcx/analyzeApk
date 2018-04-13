package qsbk.app.activity;

import android.util.Pair;
import android.view.View;
import android.view.View.OnClickListener;

class adi implements OnClickListener {
    final /* synthetic */ TemporaryNoteActivity a;

    adi(TemporaryNoteActivity temporaryNoteActivity) {
        this.a = temporaryNoteActivity;
    }

    public void onClick(View view) {
        int i = 0;
        if (this.a.f.isChecked()) {
            if (this.a.q != null && this.a.q.size() > 0) {
                while (i < this.a.q.size()) {
                    this.a.q.set(i, new Pair((Integer) ((Pair) this.a.q.get(i)).first, Integer.valueOf(1)));
                    i++;
                }
            }
        } else if (this.a.q != null && this.a.q.size() > 0) {
            for (int i2 = 0; i2 < this.a.q.size(); i2++) {
                this.a.q.set(i2, new Pair((Integer) ((Pair) this.a.q.get(i2)).first, Integer.valueOf(0)));
            }
        }
        this.a.n.notifyDataSetChanged();
    }
}
