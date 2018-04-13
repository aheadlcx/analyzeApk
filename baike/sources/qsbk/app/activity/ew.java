package qsbk.app.activity;

import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.format.TitleFormatter;
import java.text.SimpleDateFormat;

class ew implements TitleFormatter {
    final /* synthetic */ h a;

    ew(h hVar) {
        this.a = hVar;
    }

    public CharSequence format(CalendarDay calendarDay) {
        CharSequence spannableStringBuilder = new SpannableStringBuilder(new SimpleDateFormat("yyyy年 M月").format(calendarDay.getDate()));
        spannableStringBuilder.setSpan(new ForegroundColorSpan(-1), 0, spannableStringBuilder.length(), 33);
        return spannableStringBuilder;
    }
}
