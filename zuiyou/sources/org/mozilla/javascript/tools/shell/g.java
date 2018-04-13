package org.mozilla.javascript.tools.shell;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextFactory;
import org.mozilla.javascript.ErrorReporter;

public class g extends ContextFactory {
    private boolean a;
    private boolean b;
    private int c = 180;
    private int d;
    private boolean e;
    private boolean f = true;
    private ErrorReporter g;
    private String h;

    protected boolean hasFeature(Context context, int i) {
        switch (i) {
            case 3:
                return this.f;
            case 8:
            case 9:
            case 11:
                return this.a;
            case 10:
                return this.e;
            case 12:
                return this.b;
            default:
                return super.hasFeature(context, i);
        }
    }

    protected void onContextCreated(Context context) {
        context.setLanguageVersion(this.c);
        context.setOptimizationLevel(this.d);
        if (this.g != null) {
            context.setErrorReporter(this.g);
        }
        context.setGeneratingDebug(this.e);
        super.onContextCreated(context);
    }

    public String a() {
        return this.h;
    }
}
