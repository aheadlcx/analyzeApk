package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.inspector.console.RuntimeRepl;
import com.facebook.stetho.inspector.console.RuntimeReplFactory;

class Runtime$1 implements RuntimeReplFactory {
    Runtime$1() {
    }

    public RuntimeRepl newInstance() {
        return new RuntimeRepl() {
            public Object evaluate(String str) throws Throwable {
                return "Not supported with legacy Runtime module";
            }
        };
    }
}
