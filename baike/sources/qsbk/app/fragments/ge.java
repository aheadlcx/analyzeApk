package qsbk.app.fragments;

class ge implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ MyHighLightQiushiFragment b;

    ge(MyHighLightQiushiFragment myHighLightQiushiFragment, int i) {
        this.b = myHighLightQiushiFragment;
        this.a = i;
    }

    public void run() {
        this.b.m.requestFocus();
        this.b.m.setSelection(this.a);
    }
}
