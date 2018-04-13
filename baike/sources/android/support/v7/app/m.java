package android.support.v7.app;

import android.content.res.Resources.NotFoundException;
import java.lang.Thread.UncaughtExceptionHandler;

final class m implements UncaughtExceptionHandler {
    final /* synthetic */ UncaughtExceptionHandler a;

    m(UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.a = uncaughtExceptionHandler;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        if (a(th)) {
            Throwable notFoundException = new NotFoundException(th.getMessage() + ". If the resource you are trying to use is a vector resource, you may be referencing it in an unsupported way. See AppCompatDelegate.setCompatVectorFromResourcesEnabled() for more info.");
            notFoundException.initCause(th.getCause());
            notFoundException.setStackTrace(th.getStackTrace());
            this.a.uncaughtException(thread, notFoundException);
            return;
        }
        this.a.uncaughtException(thread, th);
    }

    private boolean a(Throwable th) {
        if (!(th instanceof NotFoundException)) {
            return false;
        }
        String message = th.getMessage();
        if (message == null) {
            return false;
        }
        if (message.contains("drawable") || message.contains("Drawable")) {
            return true;
        }
        return false;
    }
}
