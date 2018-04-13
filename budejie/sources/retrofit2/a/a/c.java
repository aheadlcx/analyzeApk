package retrofit2.a.a;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import java.io.IOException;
import okhttp3.ab;
import retrofit2.e;

final class c<T> implements e<ab, T> {
    private final Gson a;
    private final TypeAdapter<T> b;

    c(Gson gson, TypeAdapter<T> typeAdapter) {
        this.a = gson;
        this.b = typeAdapter;
    }

    public T a(ab abVar) throws IOException {
        try {
            T read = this.b.read(this.a.newJsonReader(abVar.e()));
            return read;
        } finally {
            abVar.close();
        }
    }
}
