package kotlin.ranges;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 1}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u00152\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002:\u0001\u0015B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\u0011\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0003H\u0002J\u0013\u0010\r\u001a\u00020\u000b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u000bH\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016R\u0014\u0010\u0005\u001a\u00020\u00038VX\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0004\u001a\u00020\u00038VX\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\b¨\u0006\u0016"}, d2 = {"Lkotlin/ranges/CharRange;", "Lkotlin/ranges/CharProgression;", "Lkotlin/ranges/ClosedRange;", "", "start", "endInclusive", "(CC)V", "getEndInclusive", "()Ljava/lang/Character;", "getStart", "contains", "", "value", "equals", "other", "", "hashCode", "", "isEmpty", "toString", "", "Companion", "kotlin-runtime"}, k = 1, mv = {1, 1, 6})
public final class CharRange extends CharProgression implements ClosedRange<Character> {
    public static final Companion Companion = new Companion();
    @NotNull
    private static final CharRange a = new CharRange((char) 1, (char) 0);

    @Metadata(bv = {1, 0, 1}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lkotlin/ranges/CharRange$Companion;", "", "()V", "EMPTY", "Lkotlin/ranges/CharRange;", "getEMPTY", "()Lkotlin/ranges/CharRange;", "kotlin-runtime"}, k = 1, mv = {1, 1, 6})
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final CharRange getEMPTY() {
            return CharRange.a;
        }
    }

    public CharRange(char c, char c2) {
        super(c, c2, 1);
    }

    public /* synthetic */ boolean contains(Comparable comparable) {
        return contains(((Character) comparable).charValue());
    }

    @NotNull
    public Character getStart() {
        return Character.valueOf(getFirst());
    }

    @NotNull
    public Character getEndInclusive() {
        return Character.valueOf(getLast());
    }

    public boolean contains(char c) {
        return getFirst() <= c && c <= getLast();
    }

    public boolean isEmpty() {
        return getFirst() > getLast();
    }

    public boolean equals(@Nullable Object obj) {
        return (obj instanceof CharRange) && ((isEmpty() && ((CharRange) obj).isEmpty()) || (getFirst() == ((CharRange) obj).getFirst() && getLast() == ((CharRange) obj).getLast()));
    }

    public int hashCode() {
        return isEmpty() ? -1 : (getFirst() * 31) + getLast();
    }

    @NotNull
    public String toString() {
        return getFirst() + ".." + getLast();
    }
}
