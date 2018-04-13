package qsbk.app.im.CollectEmotion;

import android.view.View;
import android.view.View.OnLongClickListener;
import qsbk.app.im.CollectEmotion.CollectionGridView.CollectionGridViewAdapter;
import qsbk.app.im.LatestUsedCollectionData;

class b implements OnLongClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ LatestUsedCollectionData b;
    final /* synthetic */ CollectionGridViewAdapter c;

    b(CollectionGridViewAdapter collectionGridViewAdapter, int i, LatestUsedCollectionData latestUsedCollectionData) {
        this.c = collectionGridViewAdapter;
        this.a = i;
        this.b = latestUsedCollectionData;
    }

    public boolean onLongClick(View view) {
        if (this.c.a.e != null) {
            this.c.a.e.onCollectItemLongClick(this.a, this.b);
        }
        return true;
    }
}
