package qsbk.app.utils;

import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameLengthFilter implements InputFilter {
    static String a = "[\\u4e00-\\u9fa5]";
    int b;
    Context c;
    String d;

    public NameLengthFilter(int i, Context context, String str) {
        this.b = i;
        this.c = context;
        this.d = str;
    }

    public static int getChineseCount(String str) {
        Matcher matcher = Pattern.compile(a).matcher(str);
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

    public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
        if ((spanned.toString().length() + getChineseCount(spanned.toString())) + (charSequence.toString().length() + getChineseCount(charSequence.toString())) <= this.b) {
            return charSequence;
        }
        ToastAndDialog.makeNegativeToast(this.c, this.d, Integer.valueOf(0)).show();
        return "";
    }
}
