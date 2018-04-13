package org.mozilla.javascript;

import org.mozilla.javascript.PolicySecurityController.SecureCaller;

public class RhinoSecurityManager extends SecurityManager {
    protected Class<?> getCurrentScriptClass() {
        for (Class<?> cls : getClassContext()) {
            if ((cls != InterpretedFunction.class && NativeFunction.class.isAssignableFrom(cls)) || SecureCaller.class.isAssignableFrom(cls)) {
                return cls;
            }
        }
        return null;
    }
}
