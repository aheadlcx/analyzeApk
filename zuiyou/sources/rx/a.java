package rx;

import rx.exceptions.MissingBackpressureException;

public final class a {
    public static final d a = c.a;
    public static final d b = a;
    public static final d c = b.a;
    public static final d d = a.a;

    public interface d {
        boolean a() throws MissingBackpressureException;
    }

    static final class a implements d {
        static final a a = new a();

        private a() {
        }

        public boolean a() {
            return false;
        }
    }

    static final class b implements d {
        static final b a = new b();

        private b() {
        }

        public boolean a() {
            return true;
        }
    }

    static final class c implements d {
        static final c a = new c();

        private c() {
        }

        public boolean a() throws MissingBackpressureException {
            throw new MissingBackpressureException("Overflowed buffer");
        }
    }
}
