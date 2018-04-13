package android.support.v7.util;

import android.support.v7.util.TileList.Tile;
import android.util.Log;

class i implements Runnable {
    final /* synthetic */ h a;

    i(h hVar) {
        this.a = hVar;
    }

    public void run() {
        while (true) {
            b a = this.a.a.a();
            if (a != null) {
                switch (a.what) {
                    case 1:
                        this.a.a.a(1);
                        this.a.c.refresh(a.arg1);
                        break;
                    case 2:
                        this.a.a.a(2);
                        this.a.a.a(3);
                        this.a.c.updateRange(a.arg1, a.arg2, a.arg3, a.arg4, a.arg5);
                        break;
                    case 3:
                        this.a.c.loadTile(a.arg1, a.arg2);
                        break;
                    case 4:
                        this.a.c.recycleTile((Tile) a.data);
                        break;
                    default:
                        Log.e("ThreadUtil", "Unsupported message, what=" + a.what);
                        break;
                }
            }
            this.a.b.set(false);
            return;
        }
    }
}
