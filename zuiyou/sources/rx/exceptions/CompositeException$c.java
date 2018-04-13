package rx.exceptions;

import java.io.PrintWriter;

final class CompositeException$c extends CompositeException$a {
    private final PrintWriter a;

    CompositeException$c(PrintWriter printWriter) {
        this.a = printWriter;
    }

    Object a() {
        return this.a;
    }

    void a(Object obj) {
        this.a.println(obj);
    }
}
