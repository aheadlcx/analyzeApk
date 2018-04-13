package com.budejie.www.widget.parsetagview;

import android.content.Context;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.util.Log;
import java.util.regex.Pattern;

public class b {
    private static final String a = b.class.getSimpleName();
    private static final Pattern b = Pattern.compile("#[\\p{Print}&&[^#]&&[^@]]+#");
    private static final Pattern c = Pattern.compile("@[\\p{Print}&&[^@]&&[^#]&&[^:]&&[^：]&&[^ ]]{1,20}");

    public static SpannableString a(Context context, String str, boolean z) {
        int i = 0;
        if (TextUtils.isEmpty(str)) {
            return SpannableString.valueOf("");
        }
        CharSequence spannableStringBuilder = new SpannableStringBuilder(str.replace("\\n", "\n").replace("<br>", "\n"));
        Linkify.addLinks(spannableStringBuilder, c, "budejie.tag.user://");
        Linkify.addLinks(spannableStringBuilder, b, "budejie.tag.topic://");
        URLSpan[] uRLSpanArr = (URLSpan[]) spannableStringBuilder.getSpans(0, spannableStringBuilder.length(), URLSpan.class);
        int length = uRLSpanArr.length;
        while (i < length) {
            URLSpan uRLSpan = uRLSpanArr[i];
            Log.d(a, "span.getURL()=" + uRLSpan.getURL());
            c cVar = new c(context, uRLSpan.getURL(), z);
            int spanStart = spannableStringBuilder.getSpanStart(uRLSpan);
            int spanEnd = spannableStringBuilder.getSpanEnd(uRLSpan);
            spannableStringBuilder.removeSpan(uRLSpan);
            spannableStringBuilder.setSpan(cVar, spanStart, spanEnd, 33);
            i++;
        }
        return SpannableString.valueOf(spannableStringBuilder);
    }
}
