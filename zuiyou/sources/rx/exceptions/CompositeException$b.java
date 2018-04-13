package rx.exceptions;

import java.io.PrintStream;

final class CompositeException$b extends CompositeException$a {
    private final PrintStream a;

    CompositeException$b(PrintStream printStream) {
        this.a = printStream;
    }

    Object a() {
        return this.a;
    }

    void a(Object obj) {
        this.a.println(obj);
    }
}
