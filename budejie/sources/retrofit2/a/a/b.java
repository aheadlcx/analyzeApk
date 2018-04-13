package retrofit2.a.a;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import okhttp3.u;
import okhttp3.z;
import okio.c;
import retrofit2.e;

final class b<T> implements e<T, z> {
    private static final u a = u.a("application/json; charset=UTF-8");
    private static final Charset b = Charset.forName("UTF-8");
    private final Gson c;
    private final TypeAdapter<T> d;

    public /* synthetic */ Object a(Object obj) throws IOException {
        return b(obj);
    }

    b(Gson gson, TypeAdapter<T> typeAdapter) {
        this.c = gson;
        this.d = typeAdapter;
    }

    public z b(T t) throws IOException {
        c cVar = new c();
        JsonWriter newJsonWriter = this.c.newJsonWriter(new OutputStreamWriter(cVar.d(), b));
        this.d.write(newJsonWriter, t);
        newJsonWriter.close();
        return z.a(a, cVar.p());
    }
}
