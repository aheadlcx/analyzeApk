package net.vidageek.a.a;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import net.vidageek.a.b.c;
import net.vidageek.a.h.a.a;
import net.vidageek.a.h.f;

public final class b {
    private final InputStream a;

    public b(InputStream inputStream) {
        this.a = inputStream;
    }

    private static Map<a, String> a(InputStream inputStream) {
        Map<a, String> hashMap = new HashMap();
        hashMap.put(a.REFLECTION_PROVIDER, a.class.getName());
        try {
            Properties properties = new Properties();
            properties.load(inputStream);
            for (a aVar : a.values()) {
                if (properties.containsKey(aVar.a())) {
                    hashMap.put(aVar, properties.getProperty(aVar.a()).trim());
                }
            }
            return hashMap;
        } catch (Throwable e) {
            throw new net.vidageek.a.c.a("could not ready file " + inputStream, e);
        }
    }

    public final f a() {
        if (this.a == null) {
            return new a();
        }
        return (f) new c(new a()).b((String) a(this.a).get(a.REFLECTION_PROVIDER)).a().a().a();
    }
}
