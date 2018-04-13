package org.mozilla.javascript.optimizer;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextAction;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;

class OptRuntime$1 implements ContextAction {
    final /* synthetic */ String[] val$args;
    final /* synthetic */ Script val$script;

    OptRuntime$1(String[] strArr, Script script) {
        this.val$args = strArr;
        this.val$script = script;
    }

    public Object run(Context context) {
        Scriptable global = ScriptRuntime.getGlobal(context);
        Object obj = new Object[this.val$args.length];
        System.arraycopy(this.val$args, 0, obj, 0, this.val$args.length);
        global.defineProperty("arguments", context.newArray(global, obj), 2);
        this.val$script.exec(context, global);
        return null;
    }
}
