package kotlin.jvm.internal;

import kotlin.reflect.KDeclarationContainer;

public class FunctionReferenceImpl extends FunctionReference {
    private final KDeclarationContainer b;
    private final String c;
    private final String d;

    public FunctionReferenceImpl(int i, KDeclarationContainer kDeclarationContainer, String str, String str2) {
        super(i);
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
}
