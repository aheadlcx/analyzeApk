package org.mozilla.javascript.serialize;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Undefined;
import org.mozilla.javascript.UniqueTag;

public class ScriptableInputStream extends ObjectInputStream {
    private ClassLoader classLoader;
    private Scriptable scope;

    public ScriptableInputStream(InputStream inputStream, Scriptable scriptable) throws IOException {
        super(inputStream);
        this.scope = scriptable;
        enableResolveObject(true);
        Context currentContext = Context.getCurrentContext();
        if (currentContext != null) {
            this.classLoader = currentContext.getApplicationClassLoader();
        }
    }

    protected Class<?> resolveClass(ObjectStreamClass objectStreamClass) throws IOException, ClassNotFoundException {
        String name = objectStreamClass.getName();
        if (this.classLoader != null) {
            try {
                return this.classLoader.loadClass(name);
            } catch (ClassNotFoundException e) {
            }
        }
        return super.resolveClass(objectStreamClass);
    }

    protected Object resolveObject(Object obj) throws IOException {
        if (obj instanceof PendingLookup) {
            String name = ((PendingLookup) obj).getName();
            obj = ScriptableOutputStream.lookupQualifiedName(this.scope, name);
            if (obj != Scriptable.NOT_FOUND) {
                return obj;
            }
            throw new IOException("Object " + name + " not found upon " + "deserialization.");
        } else if (obj instanceof UniqueTag) {
            return ((UniqueTag) obj).readResolve();
        } else {
            if (obj instanceof Undefined) {
                return ((Undefined) obj).readResolve();
            }
            return obj;
        }
    }
}
