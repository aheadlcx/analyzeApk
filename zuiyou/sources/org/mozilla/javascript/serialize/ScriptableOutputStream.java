package org.mozilla.javascript.serialize;

import com.alibaba.sdk.android.mns.common.MNSConstants;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.UniqueTag;

public class ScriptableOutputStream extends ObjectOutputStream {
    private Scriptable scope;
    private Map<Object, String> table = new HashMap();

    static class PendingLookup implements Serializable {
        static final long serialVersionUID = -2692990309789917727L;
        private String name;

        PendingLookup(String str) {
            this.name = str;
        }

        String getName() {
            return this.name;
        }
    }

    public ScriptableOutputStream(OutputStream outputStream, Scriptable scriptable) throws IOException {
        super(outputStream);
        this.scope = scriptable;
        this.table.put(scriptable, "");
        enableReplaceObject(true);
        excludeStandardObjectNames();
    }

    public void excludeAllIds(Object[] objArr) {
        for (Object obj : objArr) {
            if ((obj instanceof String) && (this.scope.get((String) obj, this.scope) instanceof Scriptable)) {
                addExcludedName((String) obj);
            }
        }
    }

    public void addOptionalExcludedName(String str) {
        UniqueTag lookupQualifiedName = lookupQualifiedName(this.scope, str);
        if (lookupQualifiedName != null && lookupQualifiedName != UniqueTag.NOT_FOUND) {
            if (lookupQualifiedName instanceof Scriptable) {
                this.table.put(lookupQualifiedName, str);
                return;
            }
            throw new IllegalArgumentException("Object for excluded name " + str + " is not a Scriptable, it is " + lookupQualifiedName.getClass().getName());
        }
    }

    public void addExcludedName(String str) {
        Object lookupQualifiedName = lookupQualifiedName(this.scope, str);
        if (lookupQualifiedName instanceof Scriptable) {
            this.table.put(lookupQualifiedName, str);
            return;
        }
        throw new IllegalArgumentException("Object for excluded name " + str + " not found.");
    }

    public boolean hasExcludedName(String str) {
        return this.table.get(str) != null;
    }

    public void removeExcludedName(String str) {
        this.table.remove(str);
    }

    public void excludeStandardObjectNames() {
        int i = 0;
        String[] strArr = new String[]{"Object", "Object.prototype", "Function", "Function.prototype", "String", "String.prototype", "Math", "Array", "Array.prototype", MNSConstants.ERROR_TAG, "Error.prototype", "Number", "Number.prototype", "Date", "Date.prototype", "RegExp", "RegExp.prototype", "Script", "Script.prototype", "Continuation", "Continuation.prototype"};
        for (String addExcludedName : strArr) {
            addExcludedName(addExcludedName);
        }
        String[] strArr2 = new String[]{MNSConstants.DEFAULT_NOTIFY_CONTENT_TYPE, "XML.prototype", "XMLList", "XMLList.prototype"};
        while (i < strArr2.length) {
            addOptionalExcludedName(strArr2[i]);
            i++;
        }
    }

    static Object lookupQualifiedName(Scriptable scriptable, String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, ".");
        Object obj = scriptable;
        while (stringTokenizer.hasMoreTokens()) {
            obj = ScriptableObject.getProperty((Scriptable) obj, stringTokenizer.nextToken());
            if (obj != null) {
                if (!(obj instanceof Scriptable)) {
                    break;
                }
            }
            break;
        }
        return obj;
    }

    protected Object replaceObject(Object obj) throws IOException {
        String str = (String) this.table.get(obj);
        return str == null ? obj : new PendingLookup(str);
    }
}
