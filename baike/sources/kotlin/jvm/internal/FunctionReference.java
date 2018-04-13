package kotlin.jvm.internal;

import kotlin.SinceKotlin;
import kotlin.reflect.KCallable;
import kotlin.reflect.KFunction;

public class FunctionReference extends CallableReference implements FunctionBase, KFunction {
    private final int b;

    @SinceKotlin(version = "1.1")
    protected /* synthetic */ KCallable b() {
        return c();
    }

    public FunctionReference(int i) {
        this.b = i;
    }

    @SinceKotlin(version = "1.1")
    public FunctionReference(int i, Object obj) {
        super(obj);
        this.b = i;
    }

    public int getArity() {
        return this.b;
    }

    @SinceKotlin(version = "1.1")
    protected KFunction c() {
        return (KFunction) super.b();
    }

    @SinceKotlin(version = "1.1")
    protected KCallable a() {
        return Reflection.function(this);
    }

    @SinceKotlin(version = "1.1")
    public boolean isInline() {
        return c().isInline();
    }

    @SinceKotlin(version = "1.1")
    public boolean isExternal() {
        return c().isExternal();
    }

    @SinceKotlin(version = "1.1")
    public boolean isOperator() {
        return c().isOperator();
    }

    @SinceKotlin(version = "1.1")
    public boolean isInfix() {
        return c().isInfix();
    }

    @SinceKotlin(version = "1.1")
    public boolean isSuspend() {
        return c().isSuspend();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FunctionReference)) {
            return obj instanceof KFunction ? obj.equals(compute()) : false;
        } else {
            FunctionReference functionReference = (FunctionReference) obj;
            if (getOwner().equals(functionReference.getOwner()) && getName().equals(functionReference.getName()) && getSignature().equals(functionReference.getSignature()) && Intrinsics.areEqual(getBoundReceiver(), functionReference.getBoundReceiver())) {
                return true;
            }
            return false;
        }
    }

    public int hashCode() {
        return (((getOwner().hashCode() * 31) + getName().hashCode()) * 31) + getSignature().hashCode();
    }

    public String toString() {
        FunctionReference compute = compute();
        if (compute != this) {
            return compute.toString();
        }
        if ("<init>".equals(getName())) {
            return "constructor (Kotlin reflection is not available)";
        }
        return "function " + getName() + " (Kotlin reflection is not available)";
    }
}
