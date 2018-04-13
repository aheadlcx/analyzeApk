package kotlin.text;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lkotlin/text/ScreenFloatValueRegEx;", "", "()V", "value", "Lkotlin/text/Regex;", "kotlin-stdlib"}, k = 1, mv = {1, 1, 6})
final class k {
    public static final k INSTANCE = null;
    @NotNull
    @JvmField
    public static final Regex value = null;

    static {
        k kVar = new k();
    }

    private k() {
        INSTANCE = this;
        this = this;
        String str = "(\\p{Digit}+)";
        String str2 = "(\\p{XDigit}+)";
        String str3 = "[eE][+-]?" + str;
        value = new Regex("[" + "\\" + "x00-" + "\\" + "x20]*[+-]?(NaN|Infinity|((" + (("(" + str + "(" + "\\" + ".)?(" + str + "?)(" + str3 + ")?)|") + ("(" + "\\" + ".(" + str + ")(" + str3 + ")?)|") + ("((" + (("(0[xX]" + str2 + "(" + "\\" + ".)?)|") + ("(0[xX]" + str2 + "?(" + "\\" + ".)" + str2 + ")")) + ")[pP][+-]?" + str + ")")) + ")[fFdD]?))[" + "\\" + "x00-" + "\\" + "x20]*");
    }
}
