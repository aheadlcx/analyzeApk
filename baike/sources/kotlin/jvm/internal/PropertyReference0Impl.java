package kotlin.jvm.internal;

import kotlin.reflect.KDeclarationContainer;

public class PropertyReference0Impl extends PropertyReference0 {
    private final KDeclarationContainer b;
    private final String c;
    private final String d;

    public PropertyReference0Impl(KDeclarationContainer kDeclarationContainer, String str, String str2) {
        this.b = kDeclarationContainer;
        this.c = str;
        this.d = str2;
    }

    public KDeclarationContainer getOwner() {
        return this.b;
    }

    public String getName() {
        return this.c;
    }

    public String getSignature() {
        return this.d;
    }

    public Object get() {
        return getGetter().call(new Object[0]);
    }
}
