package qsbk.app.activity;

import com.bruce.pickerview.popwindow.DatePickerPopWin;
import com.bruce.pickerview.popwindow.DatePickerPopWin.OnDatePickedListener;
import java.util.Calendar;
import qsbk.app.utils.ToastAndDialog;

class ea implements OnDatePickedListener {
    final /* synthetic */ CheckInActivity a;

    ea(CheckInActivity checkInActivity) {
        this.a = checkInActivity;
    }

    public void onDatePickCompleted(DatePickerPopWin datePickerPopWin, int i, int i2, int i3, String str) {
        Calendar instance = Calendar.getInstance();
        instance.set(1, i);
        instance.set(2, i2);
        instance.set(5, 1);
        if (instance.before(this.a.d)) {
            instance.set(5, 11);
        }
        if (instance.before(this.a.d)) {
            ToastAndDialog.makeText(this.a, "请选择2015年9月及之后的时间").show();
        } else if (instance.after(Calendar.getInstance())) {
            ToastAndDialog.makeText(this.a, "请选择今天之前的时间").show();
        } else {
            this.a.G.setDate(instance);
            datePickerPopWin.dismissPopWin();
        }
    }
}
