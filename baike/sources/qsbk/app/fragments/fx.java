package qsbk.app.fragments;

import qsbk.app.cache.FileCache;
import qsbk.app.core.AsyncTask;
import qsbk.app.utils.CollectionUtils;

class fx extends AsyncTask<Void, Void, Void> {
    final /* synthetic */ MyCollectionsFragment a;

    fx(MyCollectionsFragment myCollectionsFragment) {
        this.a = myCollectionsFragment;
    }

    protected Void a(Void... voidArr) {
        FileCache.cacheTextToDiskImmediately(this.a.B, this.a.v, CollectionUtils.artilesToJsonString(this.a.j));
        return null;
    }
}
