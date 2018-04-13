package android.support.v7.util;

import android.support.v7.util.TileList.Tile;
import android.util.Log;

class g implements Runnable {
    final /* synthetic */ f a;

    g(f fVar) {
        this.a = fVar;
    }

    public void run() {
        b a = this.a.a.a();
        while (a != null) {
            switch (a.what) {
                case 1:
                    this.a.b.updateItemCount(a.arg1, a.arg2);
                    break;
                case 2:
                    this.a.b.addTile(a.arg1, (Tile) a.data);
                    break;
                case 3:
                    this.a.b.removeTile(a.arg1, a.arg2);
                    break;
                default:
                    Log.e("ThreadUtil", "Unsupported message, what=" + a.what);
                    break;
            }
            a = this.a.a.a();
        }
    }
}
