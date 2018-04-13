package com.slidingmenu.lib;

import android.util.Log;

class d implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ SlidingMenu b;

    d(SlidingMenu slidingMenu, int i) {
        this.b = slidingMenu;
        this.a = i;
    }

    public void run() {
        Log.v("SlidingMenu", "changing layerType. hardware? " + (this.a == 2));
        this.b.getContent().setLayerType(this.a, null);
        this.b.getMenu().setLayerType(this.a, null);
        if (this.b.getSecondaryMenu() != null) {
            this.b.getSecondaryMenu().setLayerType(this.a, null);
        }
    }
}
