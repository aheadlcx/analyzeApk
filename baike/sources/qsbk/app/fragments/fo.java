package qsbk.app.fragments;

class fo implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ ManangeMyContentsFragment b;

    fo(ManangeMyContentsFragment manangeMyContentsFragment, int i) {
        this.b = manangeMyContentsFragment;
        this.a = i;
    }

    public void run() {
        this.b.m.requestFocus();
        this.b.m.setSelection(this.a);
    }
}
