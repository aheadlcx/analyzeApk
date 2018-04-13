package cn.xiaochuankeji.tieba.ui.voice;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.a.a;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.hollow.widget.TouchListenerLayout;
import cn.xiaochuankeji.tieba.ui.voice.widget.AudioWaveView;

public class VoiceCreateActivity_ViewBinding implements Unbinder {
    private VoiceCreateActivity b;
    private View c;
    private View d;
    private View e;
    private View f;
    private View g;
    private View h;

    @UiThread
    public VoiceCreateActivity_ViewBinding(final VoiceCreateActivity voiceCreateActivity, View view) {
        this.b = voiceCreateActivity;
        View a = b.a(view, R.id.iv_delete, "field 'iv_delete' and method 'onClick'");
        voiceCreateActivity.iv_delete = (ImageView) b.c(a, R.id.iv_delete, "field 'iv_delete'", ImageView.class);
        this.c = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ VoiceCreateActivity_ViewBinding c;

            public void a(View view) {
                voiceCreateActivity.onClick(view);
            }
        });
        a = b.a(view, R.id.iv_record, "field 'iv_record' and method 'onClick'");
        voiceCreateActivity.iv_record = (ImageView) b.c(a, R.id.iv_record, "field 'iv_record'", ImageView.class);
        this.d = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ VoiceCreateActivity_ViewBinding c;

            public void a(View view) {
                voiceCreateActivity.onClick(view);
            }
        });
        a = b.a(view, R.id.iv_play, "field 'iv_play' and method 'onClick'");
        voiceCreateActivity.iv_play = (ImageView) b.c(a, R.id.iv_play, "field 'iv_play'", ImageView.class);
        this.e = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ VoiceCreateActivity_ViewBinding c;

            public void a(View view) {
                voiceCreateActivity.onClick(view);
            }
        });
        voiceCreateActivity.et_content = (EditText) b.b(view, R.id.et_content, "field 'et_content'", EditText.class);
        a = b.a(view, R.id.tv_time, "field 'tv_time' and method 'onClick'");
        voiceCreateActivity.tv_time = (TextView) b.c(a, R.id.tv_time, "field 'tv_time'", TextView.class);
        this.f = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ VoiceCreateActivity_ViewBinding c;

            public void a(View view) {
                voiceCreateActivity.onClick(view);
            }
        });
        voiceCreateActivity.tv_tip = (TextView) b.b(view, R.id.tv_tip, "field 'tv_tip'", TextView.class);
        a = b.a(view, R.id.tv_next, "field 'tv_next' and method 'onClick'");
        voiceCreateActivity.tv_next = (TextView) b.c(a, R.id.tv_next, "field 'tv_next'", TextView.class);
        this.g = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ VoiceCreateActivity_ViewBinding c;

            public void a(View view) {
                voiceCreateActivity.onClick(view);
            }
        });
        voiceCreateActivity.touchListenerLayout = (TouchListenerLayout) b.b(view, R.id.layout_touch, "field 'touchListenerLayout'", TouchListenerLayout.class);
        voiceCreateActivity.mWaveView = (AudioWaveView) b.b(view, R.id.awv, "field 'mWaveView'", AudioWaveView.class);
        View a2 = b.a(view, R.id.back, "method 'onClick'");
        this.h = a2;
        a2.setOnClickListener(new a(this) {
            final /* synthetic */ VoiceCreateActivity_ViewBinding c;

            public void a(View view) {
                voiceCreateActivity.onClick(view);
            }
        });
    }

    @CallSuper
    public void a() {
        VoiceCreateActivity voiceCreateActivity = this.b;
        if (voiceCreateActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        voiceCreateActivity.iv_delete = null;
        voiceCreateActivity.iv_record = null;
        voiceCreateActivity.iv_play = null;
        voiceCreateActivity.et_content = null;
        voiceCreateActivity.tv_time = null;
        voiceCreateActivity.tv_tip = null;
        voiceCreateActivity.tv_next = null;
        voiceCreateActivity.touchListenerLayout = null;
        voiceCreateActivity.mWaveView = null;
        this.c.setOnClickListener(null);
        this.c = null;
        this.d.setOnClickListener(null);
        this.d = null;
        this.e.setOnClickListener(null);
        this.e = null;
        this.f.setOnClickListener(null);
        this.f = null;
        this.g.setOnClickListener(null);
        this.g = null;
        this.h.setOnClickListener(null);
        this.h = null;
    }
}
