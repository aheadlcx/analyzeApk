package rx.f;

public final class b<T> {
    private final long a;
    private final T b;

    public b(long j, T t) {
        this.b = t;
        this.a = j;
    }

    public long a() {
        return this.a;
    }

    public T b() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        if (this.a == bVar.a) {
            if (this.b == bVar.b) {
                return true;
            }
            if (this.b != null && this.b.equals(bVar.b)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return (this.b == null ? 0 : this.b.hashCode()) + ((((int) (this.a ^ (this.a >>> 32))) + 31) * 31);
    }

    public String toString() {
        return String.format("Timestamped(timestampMillis = %d, value = %s)", new Object[]{Long.valueOf(this.a), this.b.toString()});
    }
}
