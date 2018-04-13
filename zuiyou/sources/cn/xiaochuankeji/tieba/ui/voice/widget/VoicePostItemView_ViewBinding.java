package cn.xiaochuankeji.tieba.ui.voice.widget;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.a.a;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.comment.soundnewvisual.SoundWaveViewV2;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;

public class VoicePostItemView_ViewBinding implements Unbinder {
    private VoicePostItemView b;
    private View c;

    @UiThread
    public VoicePostItemView_ViewBinding(final VoicePostItemView voicePostItemView, View view) {
        this.b = voicePostItemView;
        voicePostItemView.iv_cover = (WebImageView) b.b(view, R.id.iv_cover, "field 'iv_cover'", WebImageView.class);
        voicePostItemView.tv_text = (TextView) b.b(view, R.id.tv_text, "field 'tv_text'", TextView.class);
        voicePostItemView.tv_time = (TextView) b.b(view, R.id.tv_time, "field 'tv_time'", TextView.class);
        voicePostItemView.iv_album = (WebImageView) b.b(view, R.id.iv_album, "field 'iv_album'", WebImageView.class);
        voicePostItemView.iv_play = (ImageView) b.b(view, R.id.iv_play, "field 'iv_play'", ImageView.class);
        voicePostItemView.mSoundWaveView = (SoundWaveViewV2) b.b(view, R.id.soundWaveView, "field 'mSoundWaveView'", SoundWaveViewV2.class);
        voicePostItemView.voiceListenerView = (VoiceListenerView) b.b(view, R.id.voice_listener_view, "field 'voiceListenerView'", VoiceListenerView.class);
        View a = b.a(view, R.id.tv_change_cover, "method 'onChangeOver'");
        this.c = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ VoicePostItemView_ViewBinding c;

            public void a(View view) {
                voicePostItemView.onChangeOver();
            }
        });
    }

    @CallSuper
    public void a() {
        VoicePostItemView voicePostItemView = this.b;
        if (voicePostItemView == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        voicePostItemView.iv_cover = null;
        voicePostItemView.tv_text = null;
        voicePostItemView.tv_time = null;
        voicePostItemView.iv_album = null;
        voicePostItemView.iv_play = null;
        voicePostItemView.mSoundWaveView = null;
        voicePostItemView.voiceListenerView = null;
        this.c.setOnClickListener(null);
        this.c = null;
    }
}
