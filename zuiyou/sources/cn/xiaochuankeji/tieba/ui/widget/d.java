package cn.xiaochuankeji.tieba.ui.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.beans.Medal;
import cn.xiaochuankeji.tieba.webview.WebActivity;
import com.flyco.dialog.c.b.b;
import com.flyco.dialog.c.c.a;

public class d extends a {
    Medal a;

    public /* synthetic */ com.flyco.dialog.c.c.a.a b(View view) {
        return a(view);
    }

    public /* synthetic */ b c(View view) {
        return a(view);
    }

    public d(Context context, Medal medal) {
        super(context, View.inflate(context, R.layout.dialog_medal_popup, null));
        this.a = medal;
        b(c.a.d.a.a.a().a((int) R.color.CB));
        e(30.0f);
        f(12.0f);
        a(13.0f, 43.0f);
        getWindow().clearFlags(67108864);
        getWindow().addFlags(2048);
        getWindow().setBackgroundDrawable(new ColorDrawable(c.a.d.a.a.a().a((int) R.color.popup_bg)));
    }

    public void b() {
        a(null);
        b(null);
        super.b();
        findViewById(R.id.talent_layout).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (!TextUtils.isEmpty(this.a.a.click_url)) {
                    this.a.dismiss();
                    WebActivity.a(this.a.getContext(), cn.xiaochuan.jsbridge.b.a(null, cn.xiaochuankeji.tieba.background.utils.d.a.d("https://$$" + this.a.a.click_url)));
                }
            }
        });
        ImageView imageView = (ImageView) findViewById(R.id.talent_icon);
        if (this.a.original == 1) {
            imageView.setImageResource(c.a.d.a.a.a().d(R.drawable.talent_original_big));
        } else if (this.a.original == 2) {
            imageView.setImageResource(c.a.d.a.a.a().d(R.drawable.talent_big));
        } else if (this.a.original == 3) {
            imageView.setImageResource(c.a.d.a.a.a().d(R.drawable.topic_talent_big_icon));
        } else {
            imageView.setVisibility(8);
        }
        ((TextView) findViewById(R.id.talent_text)).setText(this.a.name);
    }

    public a a(View view) {
        if (view != null) {
            this.l = view;
            int[] iArr = new int[2];
            this.l.getLocationOnScreen(iArr);
            if (iArr[1] < c(130.0f)) {
                this.o = 80;
            } else {
                this.o = 48;
            }
            super.b(view);
        }
        return this;
    }
}
