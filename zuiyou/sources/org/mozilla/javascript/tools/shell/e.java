package org.mozilla.javascript.tools.shell;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.SecurityController;

public abstract class e extends SecurityController {
    protected abstract void a(Context context, Scriptable scriptable, String str);
}
