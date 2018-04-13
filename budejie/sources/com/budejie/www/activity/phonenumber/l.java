package com.budejie.www.activity.phonenumber;

import android.os.CountDownTimer;
import android.text.Html;
import android.widget.TextView;
import com.budejie.www.R;
import com.budejie.www.util.i;
import java.lang.ref.SoftReference;

public class l extends CountDownTimer {
    private SoftReference<TextView> a;

    public l(TextView textView, long j, long j2) {
        super(j, j2);
        this.a = new SoftReference(textView);
    }

    public void a(SoftReference<TextView> softReference) {
        this.a = softReference;
    }

    public void onTick(long j) {
        if (this.a.get() != null) {
            ((TextView) this.a.get()).setClickable(false);
            ((TextView) this.a.get()).setText(Html.fromHtml("<u>" + ((TextView) this.a.get()).getContext().getString(R.string.count_down_verify, new Object[]{Long.toString(j / 1000)}) + "</u>"));
        }
    }

    public void onFinish() {
        i.a().a(null);
        if (this.a.get() != null) {
            ((TextView) this.a.get()).setText(R.string.phone_obain_verify_num);
            ((TextView) this.a.get()).setClickable(true);
        }
    }
}
