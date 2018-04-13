package okhttp3.internal.b;

import java.net.Proxy.Type;
import okhttp3.s;
import okhttp3.y;

public final class i {
    public static String a(y yVar, Type type) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(yVar.b());
        stringBuilder.append(' ');
        if (b(yVar, type)) {
            stringBuilder.append(yVar.a());
        } else {
            stringBuilder.append(a(yVar.a()));
        }
        stringBuilder.append(" HTTP/1.1");
        return stringBuilder.toString();
    }

    private static boolean b(y yVar, Type type) {
        return !yVar.g() && type == Type.HTTP;
    }

    public static String a(s sVar) {
        String h = sVar.h();
        String k = sVar.k();
        return k != null ? h + '?' + k : h;
    }
}
