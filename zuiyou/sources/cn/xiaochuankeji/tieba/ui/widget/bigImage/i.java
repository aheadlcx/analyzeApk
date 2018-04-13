package cn.xiaochuankeji.tieba.ui.widget.bigImage;

abstract class i implements Runnable {
    protected int b = -1;

    i() {
    }

    public boolean a(int i) {
        boolean z = this.b == -1;
        this.b = i;
        return z;
    }

    public void a() {
        this.b = -1;
    }
}
