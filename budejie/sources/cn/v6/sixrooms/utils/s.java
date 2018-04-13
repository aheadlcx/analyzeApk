package cn.v6.sixrooms.utils;

final class s implements Runnable {
    final /* synthetic */ KeyboardListener a;

    s(KeyboardListener keyboardListener) {
        this.a = keyboardListener;
    }

    public final void run() {
        this.a.updateDisplaySize();
        KeyboardListener.a(this.a).postDelayed(this.a.a, 100);
    }
}
