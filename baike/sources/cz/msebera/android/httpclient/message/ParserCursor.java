package cz.msebera.android.httpclient.message;

import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import kotlin.text.Typography;

@NotThreadSafe
public class ParserCursor {
    private final int a;
    private final int b;
    private int c;

    public ParserCursor(int i, int i2) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("Lower bound cannot be negative");
        } else if (i > i2) {
            throw new IndexOutOfBoundsException("Lower bound cannot be greater then upper bound");
        } else {
            this.a = i;
            this.b = i2;
            this.c = i;
        }
    }

    public int getLowerBound() {
        return this.a;
    }

    public int getUpperBound() {
        return this.b;
    }

    public int getPos() {
        return this.c;
    }

    public void updatePos(int i) {
        if (i < this.a) {
            throw new IndexOutOfBoundsException("pos: " + i + " < lowerBound: " + this.a);
        } else if (i > this.b) {
            throw new IndexOutOfBoundsException("pos: " + i + " > upperBound: " + this.b);
        } else {
            this.c = i;
        }
    }

    public boolean atEnd() {
        return this.c >= this.b;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');
        stringBuilder.append(Integer.toString(this.a));
        stringBuilder.append(Typography.greater);
        stringBuilder.append(Integer.toString(this.c));
        stringBuilder.append(Typography.greater);
        stringBuilder.append(Integer.toString(this.b));
        stringBuilder.append(']');
        return stringBuilder.toString();
    }
}
