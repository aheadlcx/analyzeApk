package org.mozilla.javascript.tools.shell;

import java.io.InputStream;
import java.io.OutputStream;
import org.mozilla.javascript.Context;

class b extends Thread {
    private boolean a;
    private InputStream b;
    private OutputStream c;

    b(boolean z, InputStream inputStream, OutputStream outputStream) {
        setDaemon(true);
        this.a = z;
        this.b = inputStream;
        this.c = outputStream;
    }

    public void run() {
        try {
            Global.pipe(this.a, this.b, this.c);
        } catch (Throwable e) {
            throw Context.throwAsScriptRuntimeEx(e);
        }
    }
}
