package cn.xiaochuankeji.tieba.ui.topic.holder;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.comment.soundnewvisual.SoundWaveViewV2;
import cn.xiaochuankeji.tieba.ui.topic.weight.PostGodReview;
import cn.xiaochuankeji.tieba.ui.topic.weight.PostMemberView;
import cn.xiaochuankeji.tieba.ui.topic.weight.PostOperateView;
import cn.xiaochuankeji.tieba.ui.voice.widget.VoiceListenerView;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;

public class VoiceViewHolder_ViewBinding extends BaseViewHolder_ViewBinding {
    private VoiceViewHolder b;

    @UiThread
    public VoiceViewHolder_ViewBinding(VoiceViewHolder voiceViewHolder, View view) {
        super(voiceViewHolder, view);
        this.b = voiceViewHolder;
        voiceViewHolder.postMemberView = (PostMemberView) b.b(view, R.id.post_member_view, "field 'postMemberView'", PostMemberView.class);
        voiceViewHolder.iv_cover = (WebImageView) b.b(view, R.id.iv_cover, "field 'iv_cover'", WebImageView.class);
        voiceViewHolder.tv_text = (TextView) b.b(view, R.id.tv_text, "field 'tv_text'", TextView.class);
        voiceViewHolder.tv_time = (TextView) b.b(view, R.id.tv_time, "field 'tv_time'", TextView.class);
        voiceViewHolder.iv_album = (WebImageView) b.b(view, R.id.iv_album, "field 'iv_album'", WebImageView.class);
        voiceViewHolder.iv_play = (ImageView) b.b(view, R.id.iv_play, "field 'iv_play'", ImageView.class);
        voiceViewHolder.mSoundWaveView = (SoundWaveViewV2) b.b(view, R.id.soundWaveView, "field 'mSoundWaveView'", SoundWaveViewV2.class);
        voiceViewHolder.postGodReview = (PostGodReview) b.b(view, R.id.post_god_review, "field 'postGodReview'", PostGodReview.class);
        voiceViewHolder.topicName = (TextView) b.b(view, R.id.voice_topic, "field 'topicName'", TextView.class);
        voiceViewHolder.operateView = (PostOperateView) b.b(view, R.id.post_operate_view, "field 'operateView'", PostOperateView.class);
        voiceViewHolder.voiceListenerView = (VoiceListenerView) b.b(view, R.id.voice_listener_view, "field 'voiceListenerView'", VoiceListenerView.class);
    }

    public void a() {
        VoiceViewHolder voiceViewHolder = this.b;
        if (voiceViewHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        voiceViewHolder.postMemberView = null;
        voiceViewHolder.iv_cover = null;
        voiceViewHolder.tv_text = null;
        voiceViewHolder.tv_time = null;
        voiceViewHolder.iv_album = null;
        voiceViewHolder.iv_play = null;
        voiceViewHolder.mSoundWaveView = null;
        voiceViewHolder.postGodReview = null;
        voiceViewHolder.topicName = null;
        voiceViewHolder.operateView = null;
        voiceViewHolder.voiceListenerView = null;
        super.a();
    }
}
