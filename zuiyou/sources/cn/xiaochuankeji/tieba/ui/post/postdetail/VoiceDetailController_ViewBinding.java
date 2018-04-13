package cn.xiaochuankeji.tieba.ui.post.postdetail;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.comment.soundnewvisual.SoundWaveViewV2Detail;
import cn.xiaochuankeji.tieba.ui.topic.weight.PostMemberView;
import cn.xiaochuankeji.tieba.ui.topic.weight.PostOperateView;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;

public class VoiceDetailController_ViewBinding implements Unbinder {
    private VoiceDetailController b;

    @UiThread
    public VoiceDetailController_ViewBinding(VoiceDetailController voiceDetailController, View view) {
        this.b = voiceDetailController;
        voiceDetailController.postMemberView = (PostMemberView) b.b(view, R.id.post_member_view, "field 'postMemberView'", PostMemberView.class);
        voiceDetailController.iv_cover = (WebImageView) b.b(view, R.id.iv_cover, "field 'iv_cover'", WebImageView.class);
        voiceDetailController.iv_album = (WebImageView) b.b(view, R.id.iv_album, "field 'iv_album'", WebImageView.class);
        voiceDetailController.ll_album = b.a(view, R.id.ll_album, "field 'll_album'");
        voiceDetailController.iv_album_mask = b.a(view, R.id.iv_album_mask, "field 'iv_album_mask'");
        voiceDetailController.iv_album_bg = (ImageView) b.b(view, R.id.iv_album_bg, "field 'iv_album_bg'", ImageView.class);
        voiceDetailController.tv_text = (TextView) b.b(view, R.id.tv_text, "field 'tv_text'", TextView.class);
        voiceDetailController.tv_time = (TextView) b.b(view, R.id.tv_time, "field 'tv_time'", TextView.class);
        voiceDetailController.iv_play = (ImageView) b.b(view, R.id.iv_play, "field 'iv_play'", ImageView.class);
        voiceDetailController.deleteIcon = (ImageView) b.b(view, R.id.voice_delete_in_topic, "field 'deleteIcon'", ImageView.class);
        voiceDetailController.nestedScrollView = (NestedScrollView) b.b(view, R.id.nestedScrollView, "field 'nestedScrollView'", NestedScrollView.class);
        voiceDetailController.nestedScrollContentView = (LinearLayout) b.b(view, R.id.nestedScrollContentView, "field 'nestedScrollContentView'", LinearLayout.class);
        voiceDetailController.topicName = (TextView) b.b(view, R.id.voice_topic, "field 'topicName'", TextView.class);
        voiceDetailController.operateView = (PostOperateView) b.b(view, R.id.post_operate_view, "field 'operateView'", PostOperateView.class);
        voiceDetailController.voiceContent = b.a(view, R.id.voice_post_content, "field 'voiceContent'");
        voiceDetailController.soundWaveViewV2 = (SoundWaveViewV2Detail) b.b(view, R.id.soundWaveView, "field 'soundWaveViewV2'", SoundWaveViewV2Detail.class);
        voiceDetailController.upArrowImageView = (ImageView) b.b(view, R.id.up_arrow, "field 'upArrowImageView'", ImageView.class);
        voiceDetailController.downArrowImageView = (ImageView) b.b(view, R.id.down_arrow, "field 'downArrowImageView'", ImageView.class);
    }

    @CallSuper
    public void a() {
        VoiceDetailController voiceDetailController = this.b;
        if (voiceDetailController == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        voiceDetailController.postMemberView = null;
        voiceDetailController.iv_cover = null;
        voiceDetailController.iv_album = null;
        voiceDetailController.ll_album = null;
        voiceDetailController.iv_album_mask = null;
        voiceDetailController.iv_album_bg = null;
        voiceDetailController.tv_text = null;
        voiceDetailController.tv_time = null;
        voiceDetailController.iv_play = null;
        voiceDetailController.deleteIcon = null;
        voiceDetailController.nestedScrollView = null;
        voiceDetailController.nestedScrollContentView = null;
        voiceDetailController.topicName = null;
        voiceDetailController.operateView = null;
        voiceDetailController.voiceContent = null;
        voiceDetailController.soundWaveViewV2 = null;
        voiceDetailController.upArrowImageView = null;
        voiceDetailController.downArrowImageView = null;
    }
}
