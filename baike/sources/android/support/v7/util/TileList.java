package android.support.v7.util;

import android.util.SparseArray;
import java.lang.reflect.Array;

class TileList<T> {
    final int a;
    Tile<T> b;
    private final SparseArray<Tile<T>> c = new SparseArray(10);

    public static class Tile<T> {
        Tile<T> a;
        public int mItemCount;
        public final T[] mItems;
        public int mStartPosition;

        public Tile(Class<T> cls, int i) {
            this.mItems = (Object[]) Array.newInstance(cls, i);
        }

        boolean a(int i) {
            return this.mStartPosition <= i && i < this.mStartPosition + this.mItemCount;
        }

        T b(int i) {
            return this.mItems[i - this.mStartPosition];
        }
    }

    public TileList(int i) {
        this.a = i;
    }

    public T getItemAt(int i) {
        if (this.b == null || !this.b.a(i)) {
            int indexOfKey = this.c.indexOfKey(i - (i % this.a));
            if (indexOfKey < 0) {
                return null;
            }
            this.b = (Tile) this.c.valueAt(indexOfKey);
        }
        return this.b.b(i);
    }

    public int size() {
        return this.c.size();
    }

    public void clear() {
        this.c.clear();
    }

    public Tile<T> getAtIndex(int i) {
        return (Tile) this.c.valueAt(i);
    }

    public Tile<T> addOrReplace(Tile<T> tile) {
        int indexOfKey = this.c.indexOfKey(tile.mStartPosition);
        if (indexOfKey < 0) {
            this.c.put(tile.mStartPosition, tile);
            return null;
        }
        Tile<T> tile2 = (Tile) this.c.valueAt(indexOfKey);
        this.c.setValueAt(indexOfKey, tile);
        if (this.b != tile2) {
            return tile2;
        }
        this.b = tile;
        return tile2;
    }

    public Tile<T> removeAtPos(int i) {
        Tile<T> tile = (Tile) this.c.get(i);
        if (this.b == tile) {
            this.b = null;
        }
        this.c.delete(i);
        return tile;
    }
}
