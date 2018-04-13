package kotlin.io;

import java.io.BufferedReader;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.PropertyReference0Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u0005\n\u0002\u0010\f\n\u0002\u0010\u0019\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u001a\u0013\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\b\u001a\u0011\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\nH\b\u001a\u0011\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u000bH\b\u001a\u0011\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\fH\b\u001a\u0011\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\rH\b\u001a\u0011\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u000eH\b\u001a\u0011\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u000fH\b\u001a\u0011\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0010H\b\u001a\u0011\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0011H\b\u001a\u0011\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0012H\b\u001a\t\u0010\u0013\u001a\u00020\u0007H\b\u001a\u0013\u0010\u0013\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\b\u001a\u0011\u0010\u0013\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\nH\b\u001a\u0011\u0010\u0013\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u000bH\b\u001a\u0011\u0010\u0013\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\fH\b\u001a\u0011\u0010\u0013\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\rH\b\u001a\u0011\u0010\u0013\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u000eH\b\u001a\u0011\u0010\u0013\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u000fH\b\u001a\u0011\u0010\u0013\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0010H\b\u001a\u0011\u0010\u0013\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0011H\b\u001a\u0011\u0010\u0013\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0012H\b\u001a\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015\"\u001b\u0010\u0000\u001a\u00020\u00018BX\u0002¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u0016"}, d2 = {"stdin", "Ljava/io/BufferedReader;", "getStdin", "()Ljava/io/BufferedReader;", "stdin$delegate", "Lkotlin/Lazy;", "print", "", "message", "", "", "", "", "", "", "", "", "", "", "println", "readLine", "", "kotlin-stdlib"}, k = 2, mv = {1, 1, 6})
@JvmName(name = "ConsoleKt")
public final class ConsoleKt {
    static final /* synthetic */ KProperty[] a = new KProperty[]{Reflection.property0(new PropertyReference0Impl(Reflection.getOrCreateKotlinPackage(ConsoleKt.class, "kotlin-stdlib"), "stdin", "getStdin()Ljava/io/BufferedReader;"))};
    private static final Lazy b = LazyKt.lazy(a.INSTANCE);

    private static final BufferedReader a() {
        Lazy lazy = b;
        KProperty kProperty = a[0];
        return (BufferedReader) lazy.getValue();
    }

    @Nullable
    public static final String readLine() {
        return a().readLine();
    }
}
