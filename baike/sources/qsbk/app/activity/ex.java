package qsbk.app.activity;

import android.support.v7.app.AlertDialog.Builder;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.InterceptDateClickListener;
import java.util.Calendar;
import qsbk.app.R;
import qsbk.app.utils.ToastAndDialog;

class ex implements InterceptDateClickListener {
    final /* synthetic */ h a;

    ex(h hVar) {
        this.a = hVar;
    }

    public boolean intercept(CalendarDay calendarDay, boolean z) {
        if (calendarDay.getCalendar().after(Calendar.getInstance())) {
            return true;
        }
        if (!this.a.g.f.contains(this.a.g.c.format(calendarDay.getCalendar().getTime()))) {
            ToastAndDialog.makeText(this.a.g, "暂无数据，请稍后再试").show();
            return true;
        } else if (this.a.g.g.contains(this.a.g.b.format(calendarDay.getDate()))) {
            ToastAndDialog.makeText(this.a.g, "这天你已经签过到咯，点击空白日期补签～").show();
            return true;
        } else if (this.a.g.h.size() < this.a.g.S || !z) {
            return false;
        } else {
            new Builder(this.a.g, R.style.MyDialogStyleNormal).setTitle(this.a.g.S > 0 ? "你的补签卡不够哦，请先购买补签卡" : "你还没有补签卡, 请先购买补签卡").setPositiveButton("购买补签卡", new ey(this)).setNegativeButton("取消", null).create().show();
            return true;
        }
    }
}
