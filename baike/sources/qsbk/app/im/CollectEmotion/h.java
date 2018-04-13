package qsbk.app.im.CollectEmotion;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.im.LatestUsedCollectionData;

class h implements OnClickListener {
    final /* synthetic */ ManageCollectActivity a;

    h(ManageCollectActivity manageCollectActivity) {
        this.a = manageCollectActivity;
    }

    public void onClick(View view) {
        this.a.d.clear();
        for (int i = 0; i < this.a.b.size(); i++) {
            this.a.d.add((LatestUsedCollectionData) this.a.b.get(i));
        }
        this.a.c.notifyDataSetChanged();
        this.a.g.setText(this.a.d.size() + "");
        if (this.a.d.size() == 0) {
            this.a.i.setClickable(false);
        } else {
            this.a.i.setClickable(true);
        }
    }
}
