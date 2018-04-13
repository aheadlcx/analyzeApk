package android.support.v7.util;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.util.ThreadUtil.MainThreadCallback;
import android.support.v7.util.TileList.Tile;

class f implements MainThreadCallback<T> {
    final a a = new a();
    final /* synthetic */ MainThreadCallback b;
    final /* synthetic */ e c;
    private final Handler d = new Handler(Looper.getMainLooper());
    private Runnable e = new g(this);

    f(e eVar, MainThreadCallback mainThreadCallback) {
        this.c = eVar;
        this.b = mainThreadCallback;
    }

    public void updateItemCount(int i, int i2) {
        a(b.a(1, i, i2));
    }

    public void addTile(int i, Tile<T> tile) {
        a(b.a(2, i, (Object) tile));
    }

    public void removeTile(int i, int i2) {
        a(b.a(3, i, i2));
    }

    private void a(b bVar) {
        this.a.b(bVar);
        this.d.post(this.e);
    }
}
