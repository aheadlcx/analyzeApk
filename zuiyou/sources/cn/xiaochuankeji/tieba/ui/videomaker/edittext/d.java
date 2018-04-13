package cn.xiaochuankeji.tieba.ui.videomaker.edittext;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;

public class d {
    public static SpannableStringBuilder a(Spanned spanned, Class<?>... clsArr) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(spanned.toString());
        for (Class spans : clsArr) {
            Object[] spans2 = spanned.getSpans(0, spanned.length(), spans);
            if (spans2 != null && spans2.length > 0) {
                for (Object obj : spans2) {
                    spannableStringBuilder.setSpan(obj, spanned.getSpanStart(obj), spanned.getSpanEnd(obj), spanned.getSpanFlags(obj));
                }
            }
        }
        return spannableStringBuilder;
    }

    public static void a(Spannable spannable, Class<?>... clsArr) {
        for (Class spans : clsArr) {
            Object[] spans2 = spannable.getSpans(0, spannable.length(), spans);
            if (spans2 != null && spans2.length > 0) {
                for (Object removeSpan : spans2) {
                    spannable.removeSpan(removeSpan);
                }
            }
        }
    }
}
