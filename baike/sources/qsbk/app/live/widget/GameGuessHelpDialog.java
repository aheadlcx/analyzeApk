package qsbk.app.live.widget;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.widget.ImageView;
import qsbk.app.live.R;
import qsbk.app.live.model.LiveMessage;

public class GameGuessHelpDialog extends GameHelpDialog {
    private ImageView e;
    private ImageView f;
    private ImageView g;
    private ImageView h;
    private ImageView i;
    private String j;
    private LiveMessage k;
    public ClickListenner listenner;

    public interface ClickListenner {
        public static final int TYPE_FRIEND = 0;
        public static final int TYPE_QQ = 2;
        public static final int TYPE_QZONE = 3;
        public static final int TYPE_WECHAT = 1;

        void clickListenner(int i, LiveMessage liveMessage);
    }

    public GameGuessHelpDialog(Context context) {
        super(context);
    }

    public GameGuessHelpDialog(Context context, long j, String str, ClickListenner clickListenner, LiveMessage liveMessage) {
        this(context, j);
        this.j = str;
        this.listenner = clickListenner;
        this.k = liveMessage;
    }

    public GameGuessHelpDialog(Context context, long j) {
        super(context, j);
    }

    protected void d() {
        super.d();
        this.e = (ImageView) findViewById(R.id.title_image);
        if (this.d == 1999) {
            this.f = (ImageView) findViewById(R.id.share_friend);
            this.g = (ImageView) findViewById(R.id.share_wechat);
            this.f.setOnClickListener(new br(this));
            this.g.setOnClickListener(new bs(this));
        } else if (this.d == 1998 || this.d == 1997) {
            this.f = (ImageView) findViewById(R.id.share_friend);
            this.g = (ImageView) findViewById(R.id.share_wechat);
            this.h = (ImageView) findViewById(R.id.share_qq);
            this.i = (ImageView) findViewById(R.id.share_qzone);
            this.f.setOnClickListener(new bt(this));
            this.g.setOnClickListener(new bu(this));
            this.h.setOnClickListener(new bv(this));
            this.i.setOnClickListener(new bw(this));
        }
    }

    protected void e() {
        super.e();
        if (this.d == 1998) {
            this.e.setImageResource(R.drawable.guess_over_title);
        } else if (this.d == 1997) {
            this.e.setImageResource(R.drawable.guess_congralation_title);
        }
        if (!TextUtils.isEmpty(this.j)) {
            this.c.setText(Html.fromHtml(this.j));
        }
    }
}
