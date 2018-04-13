package qsbk.app.im.CollectEmotion;

import qsbk.app.im.LatestUsedCollectionData;
import qsbk.app.widget.CheckedImageView;
import qsbk.app.widget.CheckedImageView.OnCheckedChangeListener;

class j implements OnCheckedChangeListener {
    final /* synthetic */ a a;
    final /* synthetic */ a b;

    j(a aVar, a aVar2) {
        this.b = aVar;
        this.a = aVar2;
    }

    public void onCheckedChange(CheckedImageView checkedImageView, boolean z, boolean z2) {
        if (z2 && this.b.a.c.getItemViewType(this.a.a) == 0) {
            LatestUsedCollectionData latestUsedCollectionData = (LatestUsedCollectionData) this.b.a.c.getItem(((a) checkedImageView.getTag()).a);
            if (checkedImageView.isChecked()) {
                this.b.a.d.add(latestUsedCollectionData);
            } else {
                this.b.a.d.remove(latestUsedCollectionData);
            }
            this.b.a.g.setText(this.b.a.d.size() + "");
            if (this.b.a.d.size() == 0) {
                this.b.a.i.setClickable(false);
            } else {
                this.b.a.i.setClickable(true);
            }
        }
    }
}
