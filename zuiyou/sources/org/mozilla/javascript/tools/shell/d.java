package org.mozilla.javascript.tools.shell;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextAction;
import org.mozilla.javascript.ContextFactory;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.Scriptable;

class d implements Runnable, ContextAction {
    ContextFactory a;
    private Scriptable b;
    private Function c;
    private Script d;
    private Object[] e;

    d(Scriptable scriptable, Function function, Object[] objArr) {
        this.b = scriptable;
        this.c = function;
        this.e = objArr;
    }

    d(Scriptable scriptable, Script script) {
        this.b = scriptable;
        this.d = script;
    }

    public void run() {
        this.a.call(this);
    }

    public Object run(Context context) {
        if (this.c != null) {
            return this.c.call(context, this.b, this.b, this.e);
        }
        return this.d.exec(context, this.b);
    }
}
