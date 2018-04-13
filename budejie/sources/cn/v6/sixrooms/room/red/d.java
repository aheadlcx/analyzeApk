package cn.v6.sixrooms.room.red;

final class d implements Runnable {
    final /* synthetic */ c a;

    d(c cVar) {
        this.a = cVar;
    }

    public final void run() {
        this.a.a.getContentView().startAnimation(DragRedPackagePopupWindow.m(this.a.a));
    }
}
