package android.arch.lifecycle;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

public class p {
    private final a a;
    private final r b;

    public interface a {
        @NonNull
        <T extends o> T a(@NonNull Class<T> cls);
    }

    public static class b implements a {
        @NonNull
        public <T extends o> T a(@NonNull Class<T> cls) {
            try {
                return (o) cls.newInstance();
            } catch (Throwable e) {
                throw new RuntimeException("Cannot create an instance of " + cls, e);
            } catch (Throwable e2) {
                throw new RuntimeException("Cannot create an instance of " + cls, e2);
            }
        }
    }

    public p(@NonNull r rVar, @NonNull a aVar) {
        this.a = aVar;
        this.b = rVar;
    }

    @NonNull
    public <T extends o> T a(@NonNull Class<T> cls) {
        String canonicalName = cls.getCanonicalName();
        if (canonicalName != null) {
            return a("android.arch.lifecycle.ViewModelProvider.DefaultKey:" + canonicalName, cls);
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }

    @MainThread
    @NonNull
    public <T extends o> T a(@NonNull String str, @NonNull Class<T> cls) {
        T a = this.b.a(str);
        if (cls.isInstance(a)) {
            return a;
        }
        if (a != null) {
            a = this.a.a(cls);
            this.b.a(str, a);
        } else {
            a = this.a.a(cls);
            this.b.a(str, a);
        }
        return a;
    }
}
