package qsbk.app.fragments;

import qsbk.app.fragments.BaseLiveTabFragment.LiveRoomCell;

class h implements Runnable {
    final /* synthetic */ LiveRoomCell a;

    h(LiveRoomCell liveRoomCell) {
        this.a = liveRoomCell;
    }

    public void run() {
        this.a.getCellView().setEnabled(true);
    }
}
