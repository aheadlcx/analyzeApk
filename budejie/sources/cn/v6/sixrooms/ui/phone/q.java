package cn.v6.sixrooms.ui.phone;

final class q implements Runnable {
    final /* synthetic */ ChangeUserVisibilityActivity a;

    q(ChangeUserVisibilityActivity changeUserVisibilityActivity) {
        this.a = changeUserVisibilityActivity;
    }

    public final void run() {
        this.a.b.sendEmptyMessage(0);
    }
}
