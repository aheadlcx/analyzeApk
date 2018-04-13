package qsbk.app.im.CollectEmotion;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.im.LatestUsedCollectionData;

class k implements OnClickListener {
    final /* synthetic */ a a;
    final /* synthetic */ LatestUsedCollectionData b;
    final /* synthetic */ a c;

    k(a aVar, a aVar2, LatestUsedCollectionData latestUsedCollectionData) {
        this.c = aVar;
        this.a = aVar2;
        this.b = latestUsedCollectionData;
    }

    public void onClick(View view) {
        if (this.a.c.isChecked()) {
            this.a.c.setChecked(false);
            this.c.a.d.remove(this.b);
        } else {
            this.a.c.setChecked(true);
            this.c.a.d.add(this.b);
        }
        this.c.a.g.setText(this.c.a.d.size() + "");
        if (this.c.a.d.size() == 0) {
            this.c.a.i.setClickable(false);
        } else {
            this.c.a.i.setClickable(true);
        }
    }
}
