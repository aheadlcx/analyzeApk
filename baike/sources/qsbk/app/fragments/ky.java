package qsbk.app.fragments;

import qsbk.app.nearby.api.NearbyEngine;

class ky implements Runnable {
    final /* synthetic */ SubscribeFragment a;

    ky(SubscribeFragment subscribeFragment) {
        this.a = subscribeFragment;
    }

    public void run() {
        if (this.a.V == null && this.a.S == null) {
            this.a.V = NearbyEngine.instance().getLastLocationManager();
            this.a.V.getLocation(this.a);
        }
    }
}
