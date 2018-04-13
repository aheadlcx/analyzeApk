package org.mozilla.javascript;

class SecurityController$1 implements Script {
    final /* synthetic */ SecurityController this$0;
    final /* synthetic */ Object[] val$args;
    final /* synthetic */ Callable val$callable;
    final /* synthetic */ Scriptable val$thisObj;

    SecurityController$1(SecurityController securityController, Callable callable, Scriptable scriptable, Object[] objArr) {
        this.this$0 = securityController;
        this.val$callable = callable;
        this.val$thisObj = scriptable;
        this.val$args = objArr;
    }

    public Object exec(Context context, Scriptable scriptable) {
        return this.val$callable.call(context, scriptable, this.val$thisObj, this.val$args);
    }
}
