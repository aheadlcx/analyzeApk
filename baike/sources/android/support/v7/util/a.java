package android.support.v7.util;

import android.support.v7.util.ThreadUtil.MainThreadCallback;
import android.support.v7.util.TileList.Tile;
import android.util.Log;

class a implements MainThreadCallback<T> {
    final /* synthetic */ AsyncListUtil a;

    a(AsyncListUtil asyncListUtil) {
        this.a = asyncListUtil;
    }

    public void updateItemCount(int i, int i2) {
        if (a(i)) {
            this.a.l = i2;
            this.a.d.onDataRefresh();
            this.a.m = this.a.n;
            a();
            this.a.k = false;
            this.a.a();
        }
    }

    public void addTile(int i, Tile<T> tile) {
        if (a(i)) {
            Tile addOrReplace = this.a.e.addOrReplace(tile);
            if (addOrReplace != null) {
                Log.e("AsyncListUtil", "duplicate tile @" + addOrReplace.mStartPosition);
                this.a.g.recycleTile(addOrReplace);
            }
            int i2 = tile.mItemCount + tile.mStartPosition;
            int i3 = 0;
            while (i3 < this.a.o.size()) {
                int keyAt = this.a.o.keyAt(i3);
                if (tile.mStartPosition > keyAt || keyAt >= i2) {
                    i3++;
                } else {
                    this.a.o.removeAt(i3);
                    this.a.d.onItemLoaded(keyAt);
                }
            }
            return;
        }
        this.a.g.recycleTile(tile);
    }

    public void removeTile(int i, int i2) {
        if (a(i)) {
            Tile removeAtPos = this.a.e.removeAtPos(i2);
            if (removeAtPos == null) {
                Log.e("AsyncListUtil", "tile not found @" + i2);
            } else {
                this.a.g.recycleTile(removeAtPos);
            }
        }
    }

    private void a() {
        for (int i = 0; i < this.a.e.size(); i++) {
            this.a.g.recycleTile(this.a.e.getAtIndex(i));
        }
        this.a.e.clear();
    }

    private boolean a(int i) {
        return i == this.a.n;
    }
}
