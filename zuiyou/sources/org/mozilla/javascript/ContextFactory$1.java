package org.mozilla.javascript;

import java.security.PrivilegedAction;

class ContextFactory$1 implements PrivilegedAction<DefiningClassLoader> {
    final /* synthetic */ ContextFactory this$0;
    final /* synthetic */ ClassLoader val$parent;

    ContextFactory$1(ContextFactory contextFactory, ClassLoader classLoader) {
        this.this$0 = contextFactory;
        this.val$parent = classLoader;
    }

    public DefiningClassLoader run() {
        return new DefiningClassLoader(this.val$parent);
    }
}
