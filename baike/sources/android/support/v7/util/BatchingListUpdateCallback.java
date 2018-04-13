package android.support.v7.util;

public class BatchingListUpdateCallback implements ListUpdateCallback {
    final ListUpdateCallback a;
    int b = 0;
    int c = -1;
    int d = -1;
    Object e = null;

    public BatchingListUpdateCallback(ListUpdateCallback listUpdateCallback) {
        this.a = listUpdateCallback;
    }

    public void dispatchLastEvent() {
        if (this.b != 0) {
            switch (this.b) {
                case 1:
                    this.a.onInserted(this.c, this.d);
                    break;
                case 2:
                    this.a.onRemoved(this.c, this.d);
                    break;
                case 3:
                    this.a.onChanged(this.c, this.d, this.e);
                    break;
            }
            this.e = null;
            this.b = 0;
        }
    }

    public void onInserted(int i, int i2) {
        if (this.b != 1 || i < this.c || i > this.c + this.d) {
            dispatchLastEvent();
            this.c = i;
            this.d = i2;
            this.b = 1;
            return;
        }
        this.d += i2;
        this.c = Math.min(i, this.c);
    }

    public void onRemoved(int i, int i2) {
        if (this.b != 2 || this.c < i || this.c > i + i2) {
            dispatchLastEvent();
            this.c = i;
            this.d = i2;
            this.b = 2;
            return;
        }
        this.d += i2;
        this.c = i;
    }

    public void onMoved(int i, int i2) {
        dispatchLastEvent();
        this.a.onMoved(i, i2);
    }

    public void onChanged(int i, int i2, Object obj) {
        if (this.b != 3 || i > this.c + this.d || i + i2 < this.c || this.e != obj) {
            dispatchLastEvent();
            this.c = i;
            this.d = i2;
            this.e = obj;
            this.b = 3;
            return;
        }
        int i3 = this.c + this.d;
        this.c = Math.min(i, this.c);
        this.d = Math.max(i3, i + i2) - this.c;
    }
}
