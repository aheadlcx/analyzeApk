package cn.xiaochuankeji.tieba.ui.chat.holder;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;

public class SelfVoiceHolder_ViewBinding implements Unbinder {
    private SelfVoiceHolder b;

    @UiThread
    public SelfVoiceHolder_ViewBinding(SelfVoiceHolder selfVoiceHolder, View view) {
        this.b = selfVoiceHolder;
        selfVoiceHolder.avatar = (WebImageView) b.b(view, R.id.avatar, "field 'avatar'", WebImageView.class);
        selfVoiceHolder.container = b.a(view, R.id.container, "field 'container'");
        selfVoiceHolder.progress = b.a(view, R.id.progress, "field 'progress'");
        selfVoiceHolder.resend = b.a(view, R.id.resend, "field 'resend'");
        selfVoiceHolder.duration = (AppCompatTextView) b.b(view, R.id.duration, "field 'duration'", AppCompatTextView.class);
        selfVoiceHolder.voice_container = b.a(view, R.id.voice_container, "field 'voice_container'");
        selfVoiceHolder.voice_status = (AppCompatImageView) b.b(view, R.id.voice_status, "field 'voice_status'", AppCompatImageView.class);
        selfVoiceHolder.voice_buffering = b.a(view, R.id.voice_buffering, "field 'voice_buffering'");
    }

    @CallSuper
    public void a() {
        SelfVoiceHolder selfVoiceHolder = this.b;
        if (selfVoiceHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        selfVoiceHolder.avatar = null;
        selfVoiceHolder.container = null;
        selfVoiceHolder.progress = null;
        selfVoiceHolder.resend = null;
        selfVoiceHolder.duration = null;
        selfVoiceHolder.voice_container = null;
        selfVoiceHolder.voice_status = null;
        selfVoiceHolder.voice_buffering = null;
    }
}
