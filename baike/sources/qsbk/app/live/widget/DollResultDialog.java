package qsbk.app.live.widget;

import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import java.util.Locale;
import qsbk.app.core.utils.WindowUtils;
import qsbk.app.core.widget.BaseDialog;
import qsbk.app.live.R;
import qsbk.app.live.model.LiveDollMessage;
import qsbk.app.live.ui.LiveBaseActivity;

public class DollResultDialog extends BaseDialog implements OnTouchListener {
    private int c;
    private ImageView d;
    private ImageView e;
    private ImageView f;
    private TextView g;
    private TextView h;
    private TextView i;
    private TextView j;
    private LinearLayout k;
    private TextView l;
    private LiveBaseActivity m;
    private CountDownTimer n;

    public DollResultDialog(LiveBaseActivity liveBaseActivity, int i) {
        super(liveBaseActivity);
        this.m = liveBaseActivity;
        this.c = i;
    }

    protected int c() {
        return R.layout.live_doll_result_dialog;
    }

    protected void d() {
        i();
        this.d = (ImageView) a(R.id.dialog_bg);
        this.e = (ImageView) a(R.id.iv_up);
        this.f = (ImageView) a(R.id.iv_result);
        this.g = (TextView) a(R.id.tips_success);
        this.h = (TextView) a(R.id.tips_fail);
        this.i = (TextView) a(R.id.diamond_win);
        this.j = (TextView) a(R.id.time_count);
        this.k = (LinearLayout) a(R.id.btn_again);
        this.l = (TextView) a(R.id.tv_again);
        this.k.setOnTouchListener(this);
    }

    protected void e() {
        if (this.c == 1) {
            this.k.setBackgroundResource(R.drawable.live_doll_dialog_btn_bg_selector);
            this.d.setImageResource(R.drawable.live_doll_dialog_bg);
            this.e.setImageResource(R.drawable.live_doll_dialog_close);
            this.f.setImageResource(R.drawable.live_doll_bear_success);
            this.h.setVisibility(4);
            this.g.setVisibility(0);
            this.i.setVisibility(0);
            if (LiveDollMessage.mBackDiamondNum > 0) {
                this.i.setText(String.format(Locale.getDefault(), "x%d", new Object[]{Integer.valueOf(LiveDollMessage.mBackDiamondNum)}));
            }
        } else if (this.c == 2) {
            this.k.setBackgroundResource(R.drawable.live_doll_dialog_btn_bg_selector2);
            this.d.setImageResource(R.drawable.live_doll_dialog_bg2);
            this.e.setImageResource(R.drawable.live_doll_dialog_close2);
            this.f.setImageResource(R.drawable.live_doll_bear_fail);
            this.h.setVisibility(0);
            this.g.setVisibility(4);
            this.i.setVisibility(4);
        }
        this.n = new v(this, 5100, 500);
        this.n.start();
        this.k.setOnClickListener(new w(this));
    }

    protected int a() {
        return 48;
    }

    protected boolean g() {
        return false;
    }

    public void dismiss() {
        if (this.m != null && !this.m.isFinishing() && this.m.isOnResume) {
            if (this.n != null) {
                this.n.cancel();
            }
            super.dismiss();
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        LayoutParams layoutParams;
        switch (motionEvent.getAction()) {
            case 0:
                if (view.getId() == R.id.btn_again) {
                    layoutParams = (LayoutParams) this.l.getLayoutParams();
                    layoutParams.topMargin = WindowUtils.dp2Px(12);
                    this.l.setLayoutParams(layoutParams);
                    break;
                }
                break;
            case 1:
            case 3:
                if (view.getId() == R.id.btn_again) {
                    layoutParams = (LayoutParams) this.l.getLayoutParams();
                    layoutParams.topMargin = WindowUtils.dp2Px(8);
                    this.l.setLayoutParams(layoutParams);
                    break;
                }
                break;
        }
        return false;
    }
}
