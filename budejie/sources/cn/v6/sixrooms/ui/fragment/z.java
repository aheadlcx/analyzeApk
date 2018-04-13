package cn.v6.sixrooms.ui.fragment;

final class z implements Runnable {
    final /* synthetic */ MsgVerifyFragment a;

    z(MsgVerifyFragment msgVerifyFragment) {
        this.a = msgVerifyFragment;
    }

    public final void run() {
        MsgVerifyFragment.a(this.a).toggleSoftInput(0, 2);
    }
}
