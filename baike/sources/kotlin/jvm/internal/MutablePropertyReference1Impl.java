package kotlin.jvm.internal;

import kotlin.reflect.KDeclarationContainer;

public class MutablePropertyReference1Impl extends MutablePropertyReference1 {
    private final KDeclarationContainer b;
    private final String c;
    private final String d;

    public MutablePropertyReference1Impl(KDeclarationContainer kDeclarationContainer, String str, String str2) {
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

    public Object get(Object obj) {
        return getGetter().call(new Object[]{obj});
    }

    public void set(Object obj, Object obj2) {
        getSetter().call(new Object[]{obj, obj2});
    }
}
