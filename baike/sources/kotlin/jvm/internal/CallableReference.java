package kotlin.jvm.internal;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import kotlin.SinceKotlin;
import kotlin.jvm.KotlinReflectionNotSupportedError;
import kotlin.reflect.KCallable;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.KParameter;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeParameter;
import kotlin.reflect.KVisibility;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class CallableReference implements Serializable, KCallable {
    @SinceKotlin(version = "1.1")
    public static final Object NO_RECEIVER = a.a;
    @SinceKotlin(version = "1.1")
    protected final Object a;
    private transient KCallable b;

    @SinceKotlin(version = "1.2")
    private static class a implements Serializable {
        private static final a a = new a();

        private a() {
        }
    }

    protected abstract KCallable a();

    public CallableReference() {
        this(NO_RECEIVER);
    }

    @SinceKotlin(version = "1.1")
    protected CallableReference(Object obj) {
        this.a = obj;
    }

    @SinceKotlin(version = "1.1")
    public Object getBoundReceiver() {
        return this.a;
    }

    @SinceKotlin(version = "1.1")
    public KCallable compute() {
        KCallable kCallable = this.b;
        if (kCallable != null) {
            return kCallable;
        }
        kCallable = a();
        this.b = kCallable;
        return kCallable;
    }

    @SinceKotlin(version = "1.1")
    protected KCallable b() {
        CallableReference compute = compute();
        if (compute != this) {
            return compute;
        }
        throw new KotlinReflectionNotSupportedError();
    }

    public KDeclarationContainer getOwner() {
        throw new AbstractMethodError();
    }

    public String getName() {
        throw new AbstractMethodError();
    }

    public String getSignature() {
        throw new AbstractMethodError();
    }

    public List<KParameter> getParameters() {
        return b().getParameters();
    }

    public KType getReturnType() {
        return b().getReturnType();
    }

    public List<Annotation> getAnnotations() {
        return b().getAnnotations();
    }

    @NotNull
    @SinceKotlin(version = "1.1")
    public List<KTypeParameter> getTypeParameters() {
        return b().getTypeParameters();
    }

    public Object call(@NotNull Object... objArr) {
        return b().call(objArr);
    }

    public Object callBy(@NotNull Map map) {
        return b().callBy(map);
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    public KVisibility getVisibility() {
        return b().getVisibility();
    }

    @SinceKotlin(version = "1.1")
    public boolean isFinal() {
        return b().isFinal();
    }

    @SinceKotlin(version = "1.1")
    public boolean isOpen() {
        return b().isOpen();
    }

    @SinceKotlin(version = "1.1")
    public boolean isAbstract() {
        return b().isAbstract();
    }
}
