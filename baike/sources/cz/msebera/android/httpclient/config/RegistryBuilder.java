package cz.msebera.android.httpclient.config;

import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.util.Args;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@NotThreadSafe
public final class RegistryBuilder<I> {
    private final Map<String, I> a = new HashMap();

    public static <I> RegistryBuilder<I> create() {
        return new RegistryBuilder();
    }

    RegistryBuilder() {
    }

    public RegistryBuilder<I> register(String str, I i) {
        Args.notEmpty((CharSequence) str, "ID");
        Args.notNull(i, "Item");
        this.a.put(str.toLowerCase(Locale.ROOT), i);
        return this;
    }

    public Registry<I> build() {
        return new Registry(this.a);
    }

    public String toString() {
        return this.a.toString();
    }
}
