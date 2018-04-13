package qsbk.app.live.widget;

class gn implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ LevelGiftBoxDialog b;

    gn(LevelGiftBoxDialog levelGiftBoxDialog, int i) {
        this.b = levelGiftBoxDialog;
        this.a = i;
    }

    public void run() {
        this.b.b(this.a + 1);
    }
}
