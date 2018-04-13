package cn.xiaochuankeji.tieba.ui.voice.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.json.recommend.ServerImageBean;
import cn.xiaochuankeji.tieba.ui.comment.soundnewvisual.SoundWaveViewV2;
import cn.xiaochuankeji.tieba.ui.hollow.util.c;
import cn.xiaochuankeji.tieba.ui.topic.data.PostDataBean;
import cn.xiaochuankeji.tieba.ui.topic.holder.HolderCreator;
import cn.xiaochuankeji.tieba.ui.topic.holder.HolderCreator.PostFromType;
import cn.xiaochuankeji.tieba.ui.voice.b.b;
import cn.xiaochuankeji.tieba.ui.voice.b.d;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;

public class VoicePostItemView extends FrameLayout implements b {
    Context a;
    a b;
    private PostDataBean c;
    private PostFromType d;
    private View e;
    @BindView
    WebImageView iv_album;
    @BindView
    WebImageView iv_cover;
    @BindView
    ImageView iv_play;
    @BindView
    SoundWaveViewV2 mSoundWaveView;
    @BindView
    TextView tv_text;
    @BindView
    TextView tv_time;
    @BindView
    VoiceListenerView voiceListenerView;

    public interface a {
        void a();
    }

    public VoicePostItemView(Context context) {
        super(context);
        a(context);
    }

    public VoicePostItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public VoicePostItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        this.a = context;
        this.e = LayoutInflater.from(context).inflate(R.layout.layout_voice_post_item_view, this);
        ButterKnife.a(this, this.e);
    }

    public void a() {
        d.a().f();
        b();
    }

    public void a(final PostDataBean postDataBean, PostFromType postFromType, final boolean z) {
        this.c = postDataBean;
        this.d = postFromType;
        this.voiceListenerView.a((b) this);
        this.tv_text.setText(postDataBean.content);
        if (postDataBean.audio != null && !TextUtils.isEmpty(postDataBean.audio.url)) {
            if (!(this.c.images == null || this.c.images.isEmpty())) {
                this.iv_cover.a(cn.xiaochuankeji.tieba.background.f.b.a(((ServerImageBean) this.c.images.get(0)).id), 1, 30);
                this.iv_album.setWebImage(cn.xiaochuankeji.tieba.background.f.b.a(((ServerImageBean) this.c.images.get(0)).id));
            }
            if (postDataBean.postId != d.a().b().a) {
                c();
            } else {
                b();
            }
            this.iv_album.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ VoicePostItemView c;

                public void onClick(View view) {
                    if (postDataBean.postId != d.a().b().a) {
                        if (d.a().b().c == 1) {
                            d.a().b().c = 2;
                            d.a().g();
                        }
                        if (z) {
                            cn.xiaochuankeji.tieba.ui.voice.b.a.a().a(d.a().b().a, d.a().b().f);
                        }
                        d.a().b().b = d.a().b().a;
                        d.a().b().a = postDataBean.postId;
                        d.a().b().d = postDataBean.audio.url;
                        d.a().b().e = (long) postDataBean.audio.dur;
                        d.a().d();
                        if (postDataBean.member != null && z) {
                            cn.xiaochuankeji.tieba.ui.voice.b.a.a().a(postDataBean.getId(), postDataBean.member.getId(), postDataBean.audio.url, (long) postDataBean.audio.dur, this.c.getVoiceFrom());
                        }
                    } else {
                        if (d.a().b().c == 0) {
                            d.a().b().f = 0;
                        }
                        if (d.a().b().c == 1) {
                            d.a().f();
                            if (z) {
                                cn.xiaochuankeji.tieba.ui.voice.b.a.a().a(d.a().b().a, d.a().b().f);
                            }
                        } else if (d.a().b().c != 3) {
                            d.a().c();
                            if (postDataBean.member != null && z) {
                                cn.xiaochuankeji.tieba.ui.voice.b.a.a().a(postDataBean.getId(), postDataBean.member.getId(), postDataBean.audio.url, (long) postDataBean.audio.dur, this.c.getVoiceFrom());
                            }
                        }
                    }
                    if (postDataBean.member != null) {
                        cn.xiaochuankeji.tieba.a.d.a(postDataBean);
                    }
                }
            });
        }
    }

    public void setLocalImage(String str) {
        this.iv_cover.a(str, 1, 30);
        this.iv_album.setImageURI(str);
    }

    public void setLocalImage(cn.xiaochuankeji.tieba.ui.widget.image.a aVar) {
        this.iv_cover.a(aVar, 1, 30);
        this.iv_album.setWebImage(aVar);
    }

    private void b() {
        if (this.c.audio != null) {
            if (d.a().b().c == 1) {
                this.iv_play.setImageResource(R.drawable.voice_pause_v2);
                this.mSoundWaveView.a(this.c.audio.dur, d.a().b().f);
                this.tv_time.setText(c.a((int) (((float) (((long) this.c.audio.dur) - d.a().b().f)) / 1000.0f)));
            } else if (d.a().b().c == 0) {
                c();
            } else {
                this.iv_play.setImageResource(R.drawable.voice_play_v2);
                this.mSoundWaveView.a();
                this.tv_time.setText(c.a((int) (((float) (((long) this.c.audio.dur) - d.a().b().f)) / 1000.0f)));
            }
        }
    }

    @SuppressLint({"SetTextI18n"})
    private void c() {
        if (this.c.audio != null) {
            this.mSoundWaveView.b();
            this.iv_play.setImageResource(R.drawable.voice_play_v2);
            this.tv_time.setText(c.a((int) Math.floor((double) (((float) this.c.audio.dur) / 1000.0f))));
        }
    }

    public void a(cn.xiaochuankeji.tieba.ui.voice.b.c cVar) {
        if (cVar != null && cVar.a == this.c.postId) {
            if (cVar.c == 0) {
                cn.xiaochuankeji.tieba.ui.voice.b.a.a().a(cVar.a, cVar.f);
            }
            b();
        }
    }

    private String getVoiceFrom() {
        switch (this.d) {
            case FROM_TOPIC:
                return "topic";
            case FROM_USER_POST:
                return "other";
            default:
                return HolderCreator.c(this.d);
        }
    }

    @OnClick
    public void onChangeOver() {
        if (this.b != null) {
            this.b.a();
        }
    }

    public void setOnVoicePostItemViewListener(a aVar) {
        this.b = aVar;
    }
}
