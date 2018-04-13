package kotlin.text;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Lkotlin/text/MatchResult;", "p1", "invoke"}, k = 3, mv = {1, 1, 6})
final class j extends FunctionReference implements Function1<MatchResult, MatchResult> {
    public static final j INSTANCE = new j();

    j() {
        super(1);
    }

    public final String getName() {
        return "next";
    }

    public final KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(MatchResult.class);
    }

    public final String getSignature() {
        return "next()Lkotlin/text/MatchResult;";
    }

    @Nullable
    public final MatchResult invoke(@NotNull MatchResult matchResult) {
        Intrinsics.checkParameterIsNotNull(matchResult, "p1");
        return matchResult.next();
    }
}
