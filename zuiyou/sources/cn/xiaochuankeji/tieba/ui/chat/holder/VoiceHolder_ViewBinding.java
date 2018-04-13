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
import cn.xiaochuankeji.tieba.ui.widget.text.BadgeTextView;

public class VoiceHolder_ViewBinding implements Unbinder {
    private VoiceHolder b;

    @UiThread
    public VoiceHolder_ViewBinding(VoiceHolder voiceHolder, View view) {
        this.b = voiceHolder;
        voiceHolder.avatar = (WebImageView) b.b(view, R.id.avatar, "field 'avatar'", WebImageView.class);
        voiceHolder.container = b.a(view, R.id.container, "field 'container'");
        voiceHolder.duration = (AppCompatTextView) b.b(view, R.id.duration, "field 'duration'", AppCompatTextView.class);
        voiceHolder.voice_status = (AppCompatImageView) b.b(view, R.id.voice_status, "field 'voice_status'", AppCompatImageView.class);
        voiceHolder.voice_buffering = b.a(view, R.id.voice_buffering, "field 'voice_buffering'");
        voiceHolder.badge_new = (BadgeTextView) b.b(view, R.id.badge_new, "field 'badge_new'", BadgeTextView.class);
    }

    @CallSuper
    public void a() {
        VoiceHolder voiceHolder = this.b;
        if (voiceHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        voiceHolder.avatar = null;
        voiceHolder.container = null;
        voiceHolder.duration = null;
        voiceHolder.voice_status = null;
        voiceHolder.voice_buffering = null;
        voiceHolder.badge_new = null;
    }
}
