package antistatic.spinnerwheel;

public class ItemsRange {
    private int a;
    private int b;

    public ItemsRange() {
        this(0, 0);
    }

    public ItemsRange(int i, int i2) {
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
