package android.support.v7.util;

import android.support.v7.util.ThreadUtil.BackgroundCallback;
import android.support.v7.util.TileList.Tile;
import android.util.SparseBooleanArray;

class b implements BackgroundCallback<T> {
    final SparseBooleanArray a = new SparseBooleanArray();
    final /* synthetic */ AsyncListUtil b;
    private Tile<T> c;
    private int d;
    private int e;
    private int f;
    private int g;

    b(AsyncListUtil asyncListUtil) {
        this.b = asyncListUtil;
    }

    public void refresh(int i) {
        this.d = i;
        this.a.clear();
        this.e = this.b.c.refreshData();
        this.b.f.updateItemCount(this.d, this.e);
    }

    public void updateRange(int i, int i2, int i3, int i4, int i5) {
        if (i <= i2) {
            int a = a(i);
            int a2 = a(i2);
            this.f = a(i3);
            this.g = a(i4);
            if (i5 == 1) {
                a(this.f, a2, i5, true);
                a(this.b.b + a2, this.g, i5, false);
                return;
            }
            a(a, this.g, i5, false);
            a(this.f, a - this.b.b, i5, true);
        }
    }

    private int a(int i) {
        return i - (i % this.b.b);
    }

    private void a(int i, int i2, int i3, boolean z) {
        int i4 = i;
        while (i4 <= i2) {
            int i5;
            if (z) {
                i5 = (i2 + i) - i4;
            } else {
                i5 = i4;
            }
            this.b.g.loadTile(i5, i3);
            i4 += this.b.b;
        }
    }

    public void loadTile(int i, int i2) {
        if (!b(i)) {
            Tile a = a();
            a.mStartPosition = i;
            a.mItemCount = Math.min(this.b.b, this.e - a.mStartPosition);
            this.b.c.fillData(a.mItems, a.mStartPosition, a.mItemCount);
            d(i2);
            a(a);
        }
    }

    public void recycleTile(Tile<T> tile) {
        this.b.c.recycleData(tile.mItems, tile.mItemCount);
        tile.a = this.c;
        this.c = tile;
    }

    private Tile<T> a() {
        if (this.c == null) {
            return new Tile(this.b.a, this.b.b);
        }
        Tile<T> tile = this.c;
        this.c = this.c.a;
        return tile;
    }

    private boolean b(int i) {
        return this.a.get(i);
    }

    private void a(Tile<T> tile) {
        this.a.put(tile.mStartPosition, true);
        this.b.f.addTile(this.d, tile);
    }

    private void c(int i) {
        this.a.delete(i);
        this.b.f.removeTile(this.d, i);
    }

    private void d(int i) {
        int maxCachedTiles = this.b.c.getMaxCachedTiles();
        while (this.a.size() >= maxCachedTiles) {
            int keyAt = this.a.keyAt(0);
            int keyAt2 = this.a.keyAt(this.a.size() - 1);
            int i2 = this.f - keyAt;
            int i3 = keyAt2 - this.g;
            if (i2 > 0 && (i2 >= i3 || i == 2)) {
                c(keyAt);
            } else if (i3 <= 0) {
                return;
            } else {
                if (i2 < i3 || i == 1) {
                    c(keyAt2);
                } else {
                    return;
                }
            }
        }
    }
}
