package android.support.v7.util;

import android.os.AsyncTask;
import android.support.v7.util.ThreadUtil.BackgroundCallback;
import android.support.v7.util.TileList.Tile;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

class h implements BackgroundCallback<T> {
    final a a = new a();
    AtomicBoolean b = new AtomicBoolean(false);
    final /* synthetic */ BackgroundCallback c;
    final /* synthetic */ e d;
    private final Executor e = AsyncTask.THREAD_POOL_EXECUTOR;
    private Runnable f = new i(this);

    h(e eVar, BackgroundCallback backgroundCallback) {
        this.d = eVar;
        this.c = backgroundCallback;
    }

    public void refresh(int i) {
        b(b.a(1, i, null));
    }

    public void updateRange(int i, int i2, int i3, int i4, int i5) {
        b(b.a(2, i, i2, i3, i4, i5, null));
    }

    public void loadTile(int i, int i2) {
        a(b.a(3, i, i2));
    }

    public void recycleTile(Tile<T> tile) {
        a(b.a(4, 0, (Object) tile));
    }

    private void a(b bVar) {
        this.a.b(bVar);
        a();
    }

    private void b(b bVar) {
        this.a.a(bVar);
        a();
    }

    private void a() {
        if (this.b.compareAndSet(false, true)) {
            this.e.execute(this.f);
        }
    }
}
