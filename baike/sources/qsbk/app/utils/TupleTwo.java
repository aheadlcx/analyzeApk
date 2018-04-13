package qsbk.app.utils;

public class TupleTwo<A, B> {
    private final A a;
    private final B b;

    public TupleTwo(A a, B b) {
        this.a = a;
        this.b = b;
    }

    public A getFirst() {
        return this.a;
    }

    public B getSecond() {
        return this.b;
    }

    public String toString() {
        return this.a.toString() + " - " + this.b.toString();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof TupleTwo)) {
            return false;
        }
        TupleTwo tupleTwo = (TupleTwo) obj;
        if (tupleTwo.getFirst().equals(this.a) && tupleTwo.getSecond().equals(this.b)) {
            return true;
        }
        return false;
    }
}
