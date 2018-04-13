package cn.xiaochuankeji.tieba.ui.chat;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.a.a;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.widget.TBViewPager;
import cn.xiaochuankeji.tieba.ui.widget.indicator.MagicIndicator;

public class MessageTabActivity_ViewBinding implements Unbinder {
    private MessageTabActivity b;
    private View c;
    private View d;

    @UiThread
    public MessageTabActivity_ViewBinding(final MessageTabActivity messageTabActivity, View view) {
        this.b = messageTabActivity;
        messageTabActivity.header = b.a(view, R.id.header, "field 'header'");
        View a = b.a(view, R.id.tips, "field 'tips' and method 'goSetting'");
        messageTabActivity.tips = a;
        this.c = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ MessageTabActivity_ViewBinding c;

            public void a(View view) {
                messageTabActivity.goSetting();
            }
        });
        messageTabActivity.magicIndicator = (MagicIndicator) b.b(view, R.id.indicator, "field 'magicIndicator'", MagicIndicator.class);
        messageTabActivity.viewpager = (TBViewPager) b.b(view, R.id.viewpager, "field 'viewpager'", TBViewPager.class);
        a = b.a(view, R.id.tips_close, "method 'tipsClose'");
        this.d = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ MessageTabActivity_ViewBinding c;

            public void a(View view) {
                messageTabActivity.tipsClose();
            }
        });
    }

    @CallSuper
    public void a() {
        MessageTabActivity messageTabActivity = this.b;
        if (messageTabActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        messageTabActivity.header = null;
        messageTabActivity.tips = null;
        messageTabActivity.magicIndicator = null;
        messageTabActivity.viewpager = null;
        this.c.setOnClickListener(null);
        this.c = null;
        this.d.setOnClickListener(null);
        this.d = null;
    }
}
