package qsbk.app.im.CollectEmotion;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.im.CollectEmotion.CollectionGridView.CollectionGridViewAdapter;
import qsbk.app.im.LatestUsedCollectionData;
import qsbk.app.utils.image.issue.Logger;

class a implements OnClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ LatestUsedCollectionData b;
    final /* synthetic */ CollectionGridViewAdapter c;

    a(CollectionGridViewAdapter collectionGridViewAdapter, int i, LatestUsedCollectionData latestUsedCollectionData) {
        this.c = collectionGridViewAdapter;
        this.a = i;
        this.b = latestUsedCollectionData;
    }

    public void onClick(View view) {
        Logger.getInstance().debug(CollectionGridView.a, "onItemClick", String.format("onItemClick position: %d, id: %d, data: %s", new Object[]{Integer.valueOf(this.a), Integer.valueOf(this.c.a.getId()), this.b}));
        if (this.c.a.e != null) {
            this.c.a.e.onCollectItemClick(this.a, this.b);
        }
    }
}
