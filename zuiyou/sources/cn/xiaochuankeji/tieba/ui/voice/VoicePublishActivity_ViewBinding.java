package cn.xiaochuankeji.tieba.ui.voice;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.a.a;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;

public class VoicePublishActivity_ViewBinding implements Unbinder {
    private VoicePublishActivity b;
    private View c;
    private View d;
    private View e;
    private View f;
    private View g;

    @UiThread
    public VoicePublishActivity_ViewBinding(final VoicePublishActivity voicePublishActivity, View view) {
        this.b = voicePublishActivity;
        voicePublishActivity.iv_cover = (WebImageView) b.b(view, R.id.iv_cover, "field 'iv_cover'", WebImageView.class);
        voicePublishActivity.tv_time = (TextView) b.b(view, R.id.tv_time, "field 'tv_time'", TextView.class);
        voicePublishActivity.tv_text = (TextView) b.b(view, R.id.tv_text, "field 'tv_text'", TextView.class);
        View a = b.a(view, R.id.iv_play, "field 'iv_play' and method 'onClick'");
        voicePublishActivity.iv_play = (ImageView) b.c(a, R.id.iv_play, "field 'iv_play'", ImageView.class);
        this.c = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ VoicePublishActivity_ViewBinding c;

            public void a(View view) {
                voicePublishActivity.onClick(view);
            }
        });
        voicePublishActivity.iv_anim = (ImageView) b.b(view, R.id.iv_anim, "field 'iv_anim'", ImageView.class);
        voicePublishActivity.tv_time_total = (TextView) b.b(view, R.id.tv_time_total, "field 'tv_time_total'", TextView.class);
        voicePublishActivity.pb_play = (ProgressBar) b.b(view, R.id.pb_play, "field 'pb_play'", ProgressBar.class);
        a = b.a(view, R.id.tv_select_topic, "field 'tv_select_topic' and method 'onClick'");
        voicePublishActivity.tv_select_topic = (TextView) b.c(a, R.id.tv_select_topic, "field 'tv_select_topic'", TextView.class);
        this.d = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ VoicePublishActivity_ViewBinding c;

            public void a(View view) {
                voicePublishActivity.onClick(view);
            }
        });
        View a2 = b.a(view, R.id.back, "method 'onClick'");
        this.e = a2;
        a2.setOnClickListener(new a(this) {
            final /* synthetic */ VoicePublishActivity_ViewBinding c;

            public void a(View view) {
                voicePublishActivity.onClick(view);
            }
        });
        a2 = b.a(view, R.id.tv_publish, "method 'onClick'");
        this.f = a2;
        a2.setOnClickListener(new a(this) {
            final /* synthetic */ VoicePublishActivity_ViewBinding c;

            public void a(View view) {
                voicePublishActivity.onClick(view);
            }
        });
        a2 = b.a(view, R.id.tv_change_cover, "method 'onClick'");
        this.g = a2;
        a2.setOnClickListener(new a(this) {
            final /* synthetic */ VoicePublishActivity_ViewBinding c;

            public void a(View view) {
                voicePublishActivity.onClick(view);
            }
        });
    }

    @CallSuper
    public void a() {
        VoicePublishActivity voicePublishActivity = this.b;
        if (voicePublishActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        voicePublishActivity.iv_cover = null;
        voicePublishActivity.tv_time = null;
        voicePublishActivity.tv_text = null;
        voicePublishActivity.iv_play = null;
        voicePublishActivity.iv_anim = null;
        voicePublishActivity.tv_time_total = null;
        voicePublishActivity.pb_play = null;
        voicePublishActivity.tv_select_topic = null;
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
    }
}
