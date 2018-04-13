package cn.xiaochuankeji.tieba.ui.topic.holder;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.a.d;
import cn.xiaochuankeji.tieba.json.recommend.ServerImageBean;
import cn.xiaochuankeji.tieba.ui.comment.soundnewvisual.SoundWaveViewV2;
import cn.xiaochuankeji.tieba.ui.mediabrowse.EntranceType;
import cn.xiaochuankeji.tieba.ui.post.postdetail.PostDetailActivity;
import cn.xiaochuankeji.tieba.ui.topic.TopicDetailActivity;
import cn.xiaochuankeji.tieba.ui.topic.data.PostDataBean;
import cn.xiaochuankeji.tieba.ui.topic.data.c;
import cn.xiaochuankeji.tieba.ui.topic.holder.HolderCreator.PostFromType;
import cn.xiaochuankeji.tieba.ui.topic.weight.PostGodReview;
import cn.xiaochuankeji.tieba.ui.topic.weight.PostMemberView;
import cn.xiaochuankeji.tieba.ui.topic.weight.PostOperateView;
import cn.xiaochuankeji.tieba.ui.voice.b.a;
import cn.xiaochuankeji.tieba.ui.voice.b.b;
import cn.xiaochuankeji.tieba.ui.voice.widget.VoiceListenerView;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;

public class VoiceViewHolder extends BaseViewHolder implements b {
    private PostDataBean e;
    private boolean f;
    @BindView
    WebImageView iv_album;
    @BindView
    WebImageView iv_cover;
    @BindView
    ImageView iv_play;
    @BindView
    SoundWaveViewV2 mSoundWaveView;
    @BindView
    PostOperateView operateView;
    @BindView
    PostGodReview postGodReview;
    @BindView
    PostMemberView postMemberView;
    @BindView
    TextView topicName;
    @BindView
    TextView tv_text;
    @BindView
    TextView tv_time;
    @BindView
    VoiceListenerView voiceListenerView;

    public VoiceViewHolder(View view, Activity activity, PostFromType postFromType) {
        super(view, activity, postFromType);
        ButterKnife.a(this, view);
        this.voiceListenerView.a((b) this);
    }

    protected PostDataBean b(c cVar) {
        if (cVar == null || !(cVar instanceof PostDataBean)) {
            return null;
        }
        this.e = (PostDataBean) cVar;
        a(this.e);
        this.topicName.setText(this.e.topic.topicName);
        if (!(this.e.images == null || this.e.images.isEmpty())) {
            this.iv_cover.a(cn.xiaochuankeji.tieba.background.f.b.a(((ServerImageBean) this.e.images.get(0)).id), 1, 30);
            this.iv_album.setWebImage(cn.xiaochuankeji.tieba.background.f.b.a(((ServerImageBean) this.e.images.get(0)).id));
        }
        this.itemView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ VoiceViewHolder a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.a(this.a.e, "post");
            }
        });
        this.itemView.setOnLongClickListener(new OnLongClickListener(this) {
            final /* synthetic */ VoiceViewHolder a;

            {
                this.a = r1;
            }

            public boolean onLongClick(View view) {
                this.a.a(this.a.e, false, true);
                return true;
            }
        });
        if (this.b != PostFromType.FROM_TOPIC) {
            this.topicName.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ VoiceViewHolder a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    TopicDetailActivity.a(this.a.itemView.getContext(), PostDataBean.getPostFromPostDataBean(this.a.e)._topic, "index", this.a.e.getId());
                    d.a(this.a.e);
                }
            });
        }
        this.e.createTime = HolderCreator.a(this.b) ? 0 : this.e.createTime;
        return this.e;
    }

    public void a(PostDataBean postDataBean, String str) {
        a.a().a(postDataBean.getId(), postDataBean.member.getId(), postDataBean.audio.url, (long) postDataBean.audio.dur, c());
        PostDetailActivity.a(this.d, PostDataBean.getPostFromPostDataBean(postDataBean), postDataBean, true, postDataBean.member.getTopicRole(), str, EntranceType.PostDetail);
    }

    public void a() {
        if (this.e.audio != null) {
            if (cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().c == 1) {
                this.iv_play.setImageResource(R.drawable.voice_pause_v2);
                this.mSoundWaveView.a((int) cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().e, cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().f);
            } else {
                this.iv_play.setImageResource(R.drawable.voice_play_v2);
                if (cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().c == 0) {
                    this.mSoundWaveView.b();
                } else {
                    this.mSoundWaveView.b((int) cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().e, cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().f);
                }
            }
            this.tv_time.setText(cn.xiaochuankeji.tieba.ui.hollow.util.c.a((int) (((float) (cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().e - cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().f)) / 1000.0f)));
            if (cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().c == 0) {
                this.tv_time.setText(cn.xiaochuankeji.tieba.ui.hollow.util.c.a((int) (((float) cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().e) / 1000.0f)));
            }
        }
    }

    @SuppressLint({"SetTextI18n"})
    public void b() {
        if (this.e.audio != null) {
            this.tv_time.setText(cn.xiaochuankeji.tieba.ui.hollow.util.c.a((int) Math.floor((double) (((float) this.e.audio.dur) / 1000.0f))));
            this.iv_play.setImageResource(R.drawable.voice_play_v2);
            this.mSoundWaveView.b();
        }
    }

    @SuppressLint({"SetTextI18n"})
    private void a(final PostDataBean postDataBean) {
        this.tv_text.setText(postDataBean.content);
        if (postDataBean.audio != null && !TextUtils.isEmpty(postDataBean.audio.url)) {
            if (postDataBean.postId == cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().a && postDataBean.audio.url.equals(cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().d)) {
                a();
            } else {
                this.iv_play.setImageResource(R.drawable.voice_play_v2);
                this.tv_time.setText(cn.xiaochuankeji.tieba.ui.hollow.util.c.a((int) (((float) postDataBean.audio.dur) / 1000.0f)));
                this.mSoundWaveView.b();
            }
            this.iv_album.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ VoiceViewHolder b;

                public void onClick(View view) {
                    if (postDataBean.postId != cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().a) {
                        if (cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().c == 1) {
                            cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().c = 2;
                            cn.xiaochuankeji.tieba.ui.voice.b.d.a().g();
                        }
                        a.a().a(cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().a, cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().f);
                        cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().b = cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().a;
                        cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().a = postDataBean.postId;
                        cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().d = postDataBean.audio.url;
                        cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().e = (long) postDataBean.audio.dur;
                        cn.xiaochuankeji.tieba.ui.voice.b.d.a().d();
                        a.a().a(postDataBean.getId(), postDataBean.member.getId(), postDataBean.audio.url, (long) postDataBean.audio.dur, this.b.c());
                    } else {
                        if (cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().c == 0) {
                            cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().f = 0;
                        }
                        if (cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().c == 1) {
                            cn.xiaochuankeji.tieba.ui.voice.b.d.a().f();
                            a.a().a(cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().a, cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().f);
                        } else if (cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().c != 3) {
                            cn.xiaochuankeji.tieba.ui.voice.b.d.a().c();
                            a.a().a(postDataBean.getId(), postDataBean.member.getId(), postDataBean.audio.url, (long) postDataBean.audio.dur, this.b.c());
                        }
                    }
                    d.a(postDataBean);
                }
            });
        }
    }

    public void a(cn.xiaochuankeji.tieba.ui.voice.b.c cVar) {
        Object obj = this.e.audio == null ? "" : this.e.audio.url;
        if (cVar != null && cVar.a == this.e.postId && cVar.d.equals(obj)) {
            if (cVar.c == 0) {
                a.a().a(cVar.a, cVar.f);
            }
            this.f = false;
            a();
        } else if (!this.f) {
            this.f = true;
            b();
        }
    }

    private String c() {
        switch (this.b) {
            case FROM_TOPIC:
                return "topic";
            case FROM_USER_POST:
                return "other";
            default:
                return HolderCreator.c(this.b);
        }
    }
}
