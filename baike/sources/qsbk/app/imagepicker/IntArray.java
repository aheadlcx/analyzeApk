package qsbk.app.imagepicker;

public class IntArray {
    private int a;
    private IntArray b;

    public IntArray() {
        this(0);
    }

    private IntArray(int i) {
        this.a = i;
    }

    public void put(int i) {
        a(this, i);
    }

    private void a(IntArray intArray, int i) {
        if (this.b == null) {
            this.b = new IntArray(i);
            intArray.a++;
        } else if (this.b.a != i) {
            this.b.a(intArray, i);
        }
    }

    public void del(int i) {
        b(this, i);
    }

    private void b(IntArray intArray, int i) {
        if (this.b == null) {
            return;
        }
        if (this.b.a == i) {
            this.b = this.b.b;
            intArray.a--;
            return;
        }
        this.b.b(intArray, i);
    }

    public boolean has(int i) {
        return this.b != null && (this.b.a == i || this.b.has(i));
    }

    public int[] toIntArray() {
        int[] iArr = new int[this.a];
        IntArray intArray = this.b;
        for (int i = 0; i < this.a; i++) {
            iArr[i] = intArray.a;
            intArray = intArray.b;
        }
        return iArr;
    }

    public int size() {
        return this.a;
    }
}
