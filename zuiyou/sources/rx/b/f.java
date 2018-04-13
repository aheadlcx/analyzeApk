package rx.b;

import java.util.concurrent.Callable;

public interface f<R> extends Callable<R> {
    R call();
}
