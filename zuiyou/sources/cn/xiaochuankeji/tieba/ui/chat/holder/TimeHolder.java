package cn.xiaochuankeji.tieba.ui.chat.holder;

import android.annotation.SuppressLint;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import cn.xiaochuankeji.tieba.push.data.a;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeHolder extends a {
    @SuppressLint({"SimpleDateFormat"})
    private static SimpleDateFormat d = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
    @SuppressLint({"SimpleDateFormat"})
    private static SimpleDateFormat e = new SimpleDateFormat("MM月dd日 HH:mm");
    @SuppressLint({"SimpleDateFormat"})
    private static SimpleDateFormat f = new SimpleDateFormat("HH:mm");
    @BindView
    TextView content;

    public TimeHolder(ViewGroup viewGroup, int i) {
        super(viewGroup, i);
    }

    public void a(a aVar, int i) {
        this.content.setText(a(aVar.k * 1000));
    }

    public static String a(long j) {
        long currentTimeMillis = System.currentTimeMillis();
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(currentTimeMillis);
        int i = instance.get(1);
        int i2 = instance.get(6);
        Date date = new Date();
        date.setTime(j);
        instance.setTimeInMillis(j);
        int i3 = instance.get(1);
        int i4 = instance.get(6);
        if (i3 < i) {
            return d.format(date);
        }
        if (i4 == i2) {
            return f.format(date);
        }
        if (i4 == i2 - 1) {
            return "昨天 " + f.format(date);
        }
        return e.format(date);
    }
}
