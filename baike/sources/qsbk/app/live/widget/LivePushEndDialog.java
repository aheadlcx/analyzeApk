package qsbk.app.live.widget;

import android.content.Context;
import android.view.View.OnClickListener;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import qsbk.app.core.net.NetRequest;
import qsbk.app.core.net.UrlConstants;
import qsbk.app.core.utils.WindowUtils;
import qsbk.app.live.R;

public class LivePushEndDialog extends LiveEndDialog {
    private RelativeLayout e;
    private TextView f;
    private TextView g;
    private TextView h;
    private TextView i;
    private long j;
    private long k;
    private long l;
    private long m;
    private long n;
    private String o;

    public LivePushEndDialog(Context context, OnClickListener onClickListener, long j, String str) {
        super(context, onClickListener);
        this.n = j;
        this.o = str;
    }

    protected void d() {
        super.d();
        this.e = (RelativeLayout) findViewById(R.id.layout_live_data);
        this.f = (TextView) findViewById(R.id.tv_live_duration);
        this.g = (TextView) findViewById(R.id.tv_live_user_count);
        this.h = (TextView) findViewById(R.id.tv_live_like_count);
        this.i = (TextView) findViewById(R.id.tv_live_gift_count);
    }

    protected void e() {
        j();
    }

    protected int c() {
        return R.layout.live_push_end_dialog;
    }

    public void showLiveDataLayout() {
        this.e.setVisibility(0);
    }

    public void hideLiveDataLayout() {
        this.e.setVisibility(8);
    }

    public void setLiveData(long j, long j2, long j3, long j4) {
        if (this.j == 0) {
            this.j = j;
            this.k = j2;
            this.l = j3;
            this.m = j4;
        }
        k();
    }

    private String a(long j) {
        int i;
        StringBuilder stringBuilder = new StringBuilder();
        if (j >= 60) {
            i = (int) (j / 60);
            if (i >= 60) {
                int i2 = i / 60;
                if (i2 < 10) {
                    stringBuilder.append("0");
                }
                stringBuilder.append(i2).append(" : ");
                i %= 60;
            }
            if (i < 10) {
                stringBuilder.append("0");
            }
            stringBuilder.append(i).append(" : ");
        } else {
            stringBuilder.append("00").append(" : ");
        }
        i = (int) (j % 60);
        if (i < 10) {
            stringBuilder.append("0");
        }
        stringBuilder.append(i);
        return stringBuilder.toString();
    }

    public void ajustConfirmTopDist(int i) {
        ((LayoutParams) this.c.getLayoutParams()).topMargin = i;
    }

    private void j() {
        NetRequest.getInstance().post(UrlConstants.STREAM_CLOSE, new hl(this), "stream_close", true);
    }

    private void k() {
        int i = 0;
        if (this.j > 0) {
            showLiveDataLayout();
        } else {
            hideLiveDataLayout();
            i = WindowUtils.dp2Px(65);
        }
        ajustConfirmTopDist(i);
        this.f.setText(a(this.j));
        this.g.setText(this.k + "");
        this.h.setText(this.l + "");
        this.i.setText(this.m + "");
    }
}
