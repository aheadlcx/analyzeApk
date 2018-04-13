package qsbk.app.core.utils;

import android.text.InputFilter;
import android.text.Spanned;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextLengthFilter implements InputFilter {
    private static String b = "[一-龥]|[︰-ﾠ]|[、。《》【】｛｝￥＜＞]";
    private int a;
    private int c;

    public TextLengthFilter(int i, int i2) {
        this.a = i;
        this.c = i2;
    }

    public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
        if ((spanned.toString().length() + getChineseCount(spanned.toString())) + (charSequence.toString().length() + getChineseCount(charSequence.toString())) <= this.a) {
            return charSequence;
        }
        ToastUtil.Short(this.c);
        return "";
    }

    public static int getChineseCount(String str) {
        Matcher matcher = Pattern.compile(b).matcher(str);
        int i = 0;
        while (matcher.find()) {
            int i2 = 0;
            while (i2 <= matcher.groupCount()) {
                i2++;
                i++;
            }
        }
        return i;
    }
}
