package cn.xiaochuankeji.tieba.ui.voice.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import cn.xiaochuankeji.tieba.ui.voice.b.a;
import cn.xiaochuankeji.tieba.ui.voice.b.b;
import cn.xiaochuankeji.tieba.ui.voice.b.c;
import cn.xiaochuankeji.tieba.ui.voice.b.d;

public class VoiceListenerView extends View implements b {
    private b a;

    public VoiceListenerView(Context context) {
        super(context);
    }

    public VoiceListenerView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public VoiceListenerView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        com.izuiyou.a.a.b.c("onAttachedToWindow");
        d.a().a((b) this);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        com.izuiyou.a.a.b.c("onDetachedFromWindow");
        if (d.a().h()) {
            d.a().f();
            a.a().a(d.a().b().a, d.a().b().f);
        }
        d.a().b((b) this);
    }

    public void a(b bVar) {
        this.a = bVar;
    }

    public void a(c cVar) {
        this.a.a(cVar);
    }
}
