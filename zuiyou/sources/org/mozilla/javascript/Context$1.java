package org.mozilla.javascript;

class Context$1 implements ContextAction {
    final /* synthetic */ Object[] val$args;
    final /* synthetic */ Callable val$callable;
    final /* synthetic */ Scriptable val$scope;
    final /* synthetic */ Scriptable val$thisObj;

    Context$1(Callable callable, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        this.val$callable = callable;
        this.val$scope = scriptable;
        this.val$thisObj = scriptable2;
        this.val$args = objArr;
    }

    public Object run(Context context) {
        return this.val$callable.call(context, this.val$scope, this.val$thisObj, this.val$args);
    }
}
