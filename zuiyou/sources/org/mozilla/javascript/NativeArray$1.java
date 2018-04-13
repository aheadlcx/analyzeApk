package org.mozilla.javascript;

import java.util.Comparator;

class NativeArray$1 implements Comparator<Object> {
    final /* synthetic */ Object[] val$cmpBuf;
    final /* synthetic */ Context val$cx;
    final /* synthetic */ Scriptable val$funThis;
    final /* synthetic */ Callable val$jsCompareFunction;
    final /* synthetic */ Scriptable val$scope;

    NativeArray$1(Object[] objArr, Callable callable, Context context, Scriptable scriptable, Scriptable scriptable2) {
        this.val$cmpBuf = objArr;
        this.val$jsCompareFunction = callable;
        this.val$cx = context;
        this.val$scope = scriptable;
        this.val$funThis = scriptable2;
    }

    public int compare(Object obj, Object obj2) {
        if (obj == Scriptable.NOT_FOUND) {
            if (obj2 == Scriptable.NOT_FOUND) {
                return 0;
            }
            return 1;
        } else if (obj2 == Scriptable.NOT_FOUND) {
            return -1;
        } else {
            if (obj == Undefined.instance) {
                if (obj2 != Undefined.instance) {
                    return 1;
                }
                return 0;
            } else if (obj2 == Undefined.instance) {
                return -1;
            } else {
                this.val$cmpBuf[0] = obj;
                this.val$cmpBuf[1] = obj2;
                double toNumber = ScriptRuntime.toNumber(this.val$jsCompareFunction.call(this.val$cx, this.val$scope, this.val$funThis, this.val$cmpBuf));
                if (toNumber < 0.0d) {
                    return -1;
                }
                if (toNumber > 0.0d) {
                    return 1;
                }
                return 0;
            }
        }
    }
}
