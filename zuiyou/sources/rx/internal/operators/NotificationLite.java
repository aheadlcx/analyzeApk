package rx.internal.operators;

import java.io.Serializable;
import rx.e;

public final class NotificationLite {
    private static final Object a = new Serializable() {
        private static final long serialVersionUID = 1;

        public String toString() {
            return "Notification=>Completed";
        }
    };
    private static final Object b = new Serializable() {
        private static final long serialVersionUID = 2;

        public String toString() {
            return "Notification=>NULL";
        }
    };

    static final class OnErrorSentinel implements Serializable {
        private static final long serialVersionUID = 3;
        final Throwable e;

        public OnErrorSentinel(Throwable th) {
            this.e = th;
        }

        public String toString() {
            return "Notification=>Error:" + this.e;
        }
    }

    public static <T> Object a(T t) {
        if (t == null) {
            return b;
        }
        return t;
    }

    public static Object a() {
        return a;
    }

    public static Object a(Throwable th) {
        return new OnErrorSentinel(th);
    }

    public static <T> boolean a(e<? super T> eVar, Object obj) {
        if (obj == a) {
            eVar.onCompleted();
            return true;
        } else if (obj == b) {
            eVar.onNext(null);
            return false;
        } else if (obj == null) {
            throw new IllegalArgumentException("The lite notification can not be null");
        } else if (obj.getClass() == OnErrorSentinel.class) {
            eVar.onError(((OnErrorSentinel) obj).e);
            return true;
        } else {
            eVar.onNext(obj);
            return false;
        }
    }

    public static boolean b(Object obj) {
        return obj == a;
    }

    public static boolean c(Object obj) {
        return obj instanceof OnErrorSentinel;
    }

    public static <T> T d(Object obj) {
        return obj == b ? null : obj;
    }
}
