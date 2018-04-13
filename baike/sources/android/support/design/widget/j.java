package android.support.design.widget;

class j implements a {
    final /* synthetic */ BaseTransientBottomBar a;

    j(BaseTransientBottomBar baseTransientBottomBar) {
        this.a = baseTransientBottomBar;
    }

    public void show() {
        BaseTransientBottomBar.a.sendMessage(BaseTransientBottomBar.a.obtainMessage(0, this.a));
    }

    public void dismiss(int i) {
        BaseTransientBottomBar.a.sendMessage(BaseTransientBottomBar.a.obtainMessage(1, i, 0, this.a));
    }
}
