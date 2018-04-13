package cn.xiaochuankeji.tieba.ui.voice;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.a.a;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.voice.widget.VoicePostItemView;

public class VoicePublishV2Activity_ViewBinding implements Unbinder {
    private VoicePublishV2Activity b;
    private View c;
    private View d;
    private View e;

    @UiThread
    public VoicePublishV2Activity_ViewBinding(final VoicePublishV2Activity voicePublishV2Activity, View view) {
        this.b = voicePublishV2Activity;
        View a = b.a(view, R.id.tv_select_topic, "field 'tv_select_topic' and method 'onClick'");
        voicePublishV2Activity.tv_select_topic = (TextView) b.c(a, R.id.tv_select_topic, "field 'tv_select_topic'", TextView.class);
        this.c = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ VoicePublishV2Activity_ViewBinding c;

            public void a(View view) {
                voicePublishV2Activity.onClick(view);
            }
        });
        voicePublishV2Activity.voicePostItemView = (VoicePostItemView) b.b(view, R.id.voicePostItemView, "field 'voicePostItemView'", VoicePostItemView.class);
        View a2 = b.a(view, R.id.back, "method 'onClick'");
        this.d = a2;
        a2.setOnClickListener(new a(this) {
            final /* synthetic */ VoicePublishV2Activity_ViewBinding c;

            public void a(View view) {
                voicePublishV2Activity.onClick(view);
            }
        });
        a2 = b.a(view, R.id.tv_publish, "method 'onClick'");
        this.e = a2;
        a2.setOnClickListener(new a(this) {
            final /* synthetic */ VoicePublishV2Activity_ViewBinding c;

            public void a(View view) {
                voicePublishV2Activity.onClick(view);
            }
        });
    }

    @CallSuper
    public void a() {
        VoicePublishV2Activity voicePublishV2Activity = this.b;
        if (voicePublishV2Activity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        voicePublishV2Activity.tv_select_topic = null;
        voicePublishV2Activity.voicePostItemView = null;
        this.c.setOnClickListener(null);
        this.c = null;
        this.d.setOnClickListener(null);
        this.d = null;
        this.e.setOnClickListener(null);
        this.e = null;
    }
}
