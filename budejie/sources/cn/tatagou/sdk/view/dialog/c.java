package cn.tatagou.sdk.view.dialog;

public class c {
    private int a;
    private int b;

    public c() {
        this(0, 0);
    }

    public c(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    public int getFirst() {
        return this.a;
    }

    public int getLast() {
        return (getFirst() + getCount()) - 1;
    }

    public int getCount() {
        return this.b;
    }

    public boolean contains(int i) {
        return i >= getFirst() && i <= getLast();
    }
}
