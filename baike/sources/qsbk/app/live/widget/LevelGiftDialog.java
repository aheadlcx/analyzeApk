package qsbk.app.live.widget;

import android.app.Activity;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.Locale;
import qsbk.app.core.net.NetRequest;
import qsbk.app.core.net.UrlConstants;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.widget.BaseDialog;
import qsbk.app.live.R;
import qsbk.app.live.model.LiveLevelBox;

public class LevelGiftDialog extends BaseDialog {
    private TextView c;
    private TextView d;
    private TextView e;
    private TextView f;
    private TextView g;
    private SimpleDraweeView h;
    private Activity i;
    private LiveLevelBox j;
    private CountDownTimer k;

    public LevelGiftDialog(Activity activity, LiveLevelBox liveLevelBox) {
        super(activity);
        this.i = activity;
        this.j = liveLevelBox;
    }

    protected int c() {
        return R.layout.level_gift_dialog;
    }

    protected void d() {
        i();
        Window window = getWindow();
        if (window != null) {
            window.getDecorView().setPadding(0, 0, 0, 0);
            LayoutParams attributes = window.getAttributes();
            attributes.width = -1;
            attributes.height = -1;
            window.setAttributes(attributes);
        }
        this.c = (TextView) a(R.id.level_gift_title);
        this.d = (TextView) a(R.id.level_gift_heart);
        this.e = (TextView) a(R.id.level_gift_diamond);
        this.f = (TextView) a(R.id.level_gift_price);
        this.g = (TextView) a(R.id.level_gift_time_limit);
        this.h = (SimpleDraweeView) a(R.id.level_gift_label);
        findViewById(R.id.level_gift_btn_buy).setOnClickListener(new go(this));
    }

    protected void e() {
        if (this.j != null) {
            this.c.setText(this.j.title);
            this.d.setText(String.valueOf(this.j.loveheart));
            this.e.setText(String.valueOf(this.j.coin));
            this.f.setText(String.format(Locale.getDefault(), "¥%.2f", new Object[]{Double.valueOf(this.j.price)}));
            switch (this.j.rate) {
                case 2:
                    this.h.setActualImageResource(R.drawable.level_gift_label_2);
                    break;
                case 3:
                    this.h.setActualImageResource(R.drawable.level_gift_label_3);
                    break;
                case 4:
                    this.h.setActualImageResource(R.drawable.level_gift_label_4);
                    break;
                default:
                    if (!TextUtils.isEmpty(this.j.rate_url)) {
                        AppUtils.getInstance().getImageProvider().loadGift(this.h, this.j.rate_url, false);
                        break;
                    }
                    break;
            }
            NetRequest.getInstance().get(UrlConstants.LIVE_LEVEL_GIFT_CHECK, new gp(this), "live_level_gift", true);
            a(this.j.left_seconds);
            return;
        }
        dismiss();
    }

    private void a(long j) {
        if (this.k != null) {
            this.k.cancel();
        }
        this.k = new gq(this, j * 1000, 1000);
        this.k.start();
    }

    protected int a() {
        return 48;
    }

    public void dismiss() {
        if (this.k != null) {
            this.k.cancel();
        }
        super.dismiss();
    }
}
