package cn.xiaochuankeji.tieba.ui.post.postdetail;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.arch.lifecycle.q;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.NestedScrollView.OnScrollChangeListener;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.a;
import cn.xiaochuankeji.tieba.background.net.XCError;
import cn.xiaochuankeji.tieba.background.post.d;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.background.utils.h;
import cn.xiaochuankeji.tieba.json.EmptyJson;
import cn.xiaochuankeji.tieba.json.MemberInfoBean;
import cn.xiaochuankeji.tieba.json.recommend.ServerImageBean;
import cn.xiaochuankeji.tieba.network.custom.exception.ClientErrorException;
import cn.xiaochuankeji.tieba.ui.comment.soundnewvisual.SoundWaveViewV2Detail;
import cn.xiaochuankeji.tieba.ui.member.MemberDetailActivity;
import cn.xiaochuankeji.tieba.ui.post.LikedUsersActivity;
import cn.xiaochuankeji.tieba.ui.post.PostAllegeActivity;
import cn.xiaochuankeji.tieba.ui.topic.TopicDetailActivity;
import cn.xiaochuankeji.tieba.ui.topic.data.PostDataBean;
import cn.xiaochuankeji.tieba.ui.topic.holder.HolderCreator;
import cn.xiaochuankeji.tieba.ui.topic.holder.HolderOperator;
import cn.xiaochuankeji.tieba.ui.topic.weight.PostMemberView;
import cn.xiaochuankeji.tieba.ui.topic.weight.PostMemberView.ViewType;
import cn.xiaochuankeji.tieba.ui.topic.weight.PostOperateView;
import cn.xiaochuankeji.tieba.ui.voice.b.b;
import cn.xiaochuankeji.tieba.ui.voice.b.c;
import cn.xiaochuankeji.tieba.ui.widget.SDBottomSheet;
import cn.xiaochuankeji.tieba.ui.widget.SDCheckSheet;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import cn.xiaochuankeji.tieba.ui.widget.updown.PostItemUpDownView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class VoiceDetailController implements b {
    ObjectAnimator a;
    ObjectAnimator b;
    private PostDataBean c;
    private View d = LayoutInflater.from(this.e).inflate(R.layout.view_voice_header, null);
    @BindView
    ImageView deleteIcon;
    @BindView
    ImageView downArrowImageView;
    private Activity e;
    private HolderOperator f;
    private d g;
    @BindView
    WebImageView iv_album;
    @BindView
    ImageView iv_album_bg;
    @BindView
    View iv_album_mask;
    @BindView
    WebImageView iv_cover;
    @BindView
    ImageView iv_play;
    @BindView
    View ll_album;
    @BindView
    LinearLayout nestedScrollContentView;
    @BindView
    NestedScrollView nestedScrollView;
    @BindView
    PostOperateView operateView;
    @BindView
    PostMemberView postMemberView;
    @BindView
    SoundWaveViewV2Detail soundWaveViewV2;
    @BindView
    TextView topicName;
    @BindView
    TextView tv_text;
    @BindView
    TextView tv_time;
    @BindView
    ImageView upArrowImageView;
    @BindView
    View voiceContent;

    public VoiceDetailController(PostDataBean postDataBean, Activity activity) {
        this.e = activity;
        ButterKnife.a(this, this.d);
        this.g = a.j();
        a(postDataBean);
        this.tv_text.setMovementMethod(ScrollingMovementMethod.getInstance());
        cn.xiaochuankeji.tieba.ui.voice.b.d.a().a((b) this);
        this.f = (HolderOperator) q.a((FragmentActivity) this.e).a(HolderOperator.class);
        f();
        if (activity instanceof PostAllegeActivity) {
            this.deleteIcon.setVisibility(0);
        }
        this.nestedScrollView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener(this) {
            final /* synthetic */ VoiceDetailController a;

            {
                this.a = r1;
            }

            public void onGlobalLayout() {
                if (this.a.nestedScrollView.canScrollVertically(1)) {
                    this.a.d();
                }
                this.a.nestedScrollView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        this.nestedScrollView.setOnScrollChangeListener(new OnScrollChangeListener(this) {
            final /* synthetic */ VoiceDetailController a;

            {
                this.a = r1;
            }

            public void onScrollChange(NestedScrollView nestedScrollView, int i, int i2, int i3, int i4) {
                if (i2 == 0) {
                    this.a.d();
                } else if (this.a.nestedScrollView.getHeight() + i2 == this.a.nestedScrollContentView.getHeight()) {
                    this.a.e();
                    this.a.nestedScrollView.stopNestedScroll();
                }
                int height = this.a.ll_album.getHeight();
                if (i2 < height) {
                    height = ((height - i2) * 255) / height;
                    this.a.iv_album.setImageAlpha(height);
                    this.a.iv_album_mask.setAlpha((float) height);
                    this.a.iv_album_bg.setImageAlpha(height);
                    return;
                }
                this.a.iv_album.setImageAlpha(0);
                this.a.iv_album_mask.setAlpha(0.0f);
                this.a.iv_album_bg.setImageAlpha(0);
            }
        });
        this.a = ObjectAnimator.ofFloat(this.upArrowImageView, "translationY", new float[]{this.upArrowImageView.getTranslationY() - 10.0f, this.upArrowImageView.getTranslationY() + 10.0f});
        this.a.setDuration(500).setRepeatMode(2);
        this.a.setRepeatCount(-1);
        this.b = ObjectAnimator.ofFloat(this.downArrowImageView, "translationY", new float[]{this.downArrowImageView.getTranslationY() - 10.0f, this.downArrowImageView.getTranslationY() + 10.0f});
        this.b.setDuration(500).setRepeatMode(2);
        this.b.setRepeatCount(-1);
    }

    private void d() {
        this.upArrowImageView.setVisibility(0);
        this.downArrowImageView.setVisibility(8);
        this.a.start();
        this.b.cancel();
    }

    private void e() {
        this.upArrowImageView.setVisibility(8);
        this.downArrowImageView.setVisibility(0);
        this.b.start();
        this.a.cancel();
    }

    private void f() {
        OnLongClickListener anonymousClass10 = new OnLongClickListener(this) {
            final /* synthetic */ VoiceDetailController a;

            {
                this.a = r1;
            }

            public boolean onLongClick(View view) {
                this.a.a(this.a.c, false, true);
                return true;
            }
        };
        this.d.setOnLongClickListener(anonymousClass10);
        this.topicName.setOnLongClickListener(anonymousClass10);
        this.operateView.setOnLongClickListener(anonymousClass10);
        this.voiceContent.setOnLongClickListener(anonymousClass10);
        this.tv_text.setOnLongClickListener(anonymousClass10);
        this.iv_play.setOnLongClickListener(anonymousClass10);
    }

    public void a(c cVar) {
        if (cVar != null && cVar.a == this.c.postId) {
            if (cVar.c == 0) {
                cn.xiaochuankeji.tieba.ui.voice.b.a.a().a(cVar.a, cVar.f);
            }
            a();
        }
    }

    public void a(final PostDataBean postDataBean) {
        this.c = postDataBean;
        c(postDataBean);
        b(postDataBean);
        d(postDataBean);
        this.topicName.setText(postDataBean.topic.topicName);
        this.topicName.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ VoiceDetailController b;

            public void onClick(View view) {
                TopicDetailActivity.a(this.b.e, PostDataBean.getPostFromPostDataBean(postDataBean)._topic, "postdetail", postDataBean.postId);
            }
        });
    }

    public void a() {
        if (this.c.audio != null) {
            if (cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().c == 1) {
                this.iv_play.setImageResource(R.drawable.voice_pause_detail_v2);
                this.soundWaveViewV2.a((int) cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().e, cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().f);
                this.tv_time.setText(cn.xiaochuankeji.tieba.ui.hollow.util.c.a((int) (((float) (cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().e - cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().f)) / 1000.0f)));
            } else if (cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().c == 0) {
                this.iv_play.setImageResource(R.drawable.voice_play_detail_v2);
                this.tv_time.setText(cn.xiaochuankeji.tieba.ui.hollow.util.c.a(0));
                this.soundWaveViewV2.b();
            } else {
                this.iv_play.setImageResource(R.drawable.voice_play_detail_v2);
                this.soundWaveViewV2.b((int) cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().e, cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().f);
                this.tv_time.setText(cn.xiaochuankeji.tieba.ui.hollow.util.c.a((int) (((float) (cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().e - cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().f)) / 1000.0f)));
            }
        }
    }

    @SuppressLint({"SetTextI18n"})
    private void b(final PostDataBean postDataBean) {
        this.tv_text.setText(postDataBean.content);
        if (postDataBean.audio != null && !TextUtils.isEmpty(postDataBean.audio.url)) {
            if (!(this.c.images == null || this.c.images.isEmpty())) {
                this.iv_cover.a(cn.xiaochuankeji.tieba.background.f.b.a(((ServerImageBean) this.c.images.get(0)).id), 1, 30);
                this.iv_album.setWebImage(cn.xiaochuankeji.tieba.background.f.b.a(((ServerImageBean) this.c.images.get(0)).id));
            }
            if (postDataBean.postId == cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().a && postDataBean.audio.url.equals(cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().d)) {
                a();
            } else {
                this.iv_play.setImageResource(R.drawable.voice_play_detail_v2);
                this.tv_time.setText(cn.xiaochuankeji.tieba.ui.hollow.util.c.a(postDataBean.audio.dur / 1000));
            }
            this.iv_play.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ VoiceDetailController b;

                public void onClick(View view) {
                    if (postDataBean.postId != cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().a) {
                        cn.xiaochuankeji.tieba.ui.voice.b.a.a().a(cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().a, cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().f);
                        cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().b = cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().a;
                        cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().a = postDataBean.postId;
                        cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().d = postDataBean.audio.url;
                        cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().e = (long) postDataBean.audio.dur;
                        cn.xiaochuankeji.tieba.ui.voice.b.d.a().d();
                        cn.xiaochuankeji.tieba.ui.voice.b.a.a().a(postDataBean.getId(), postDataBean.member.getId(), postDataBean.audio.url, (long) postDataBean.audio.dur);
                        return;
                    }
                    if (cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().c == 0) {
                        cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().f = 0;
                    }
                    if (cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().c == 1) {
                        cn.xiaochuankeji.tieba.ui.voice.b.d.a().f();
                        cn.xiaochuankeji.tieba.ui.voice.b.a.a().a(cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().a, cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().f);
                    } else if (cn.xiaochuankeji.tieba.ui.voice.b.d.a().b().c != 3) {
                        cn.xiaochuankeji.tieba.ui.voice.b.d.a().c();
                        cn.xiaochuankeji.tieba.ui.voice.b.a.a().a(postDataBean.getId(), postDataBean.member.getId(), postDataBean.audio.url, (long) postDataBean.audio.dur);
                    }
                }
            });
        }
    }

    private void c(final PostDataBean postDataBean) {
        PostMemberView postMemberView = this.postMemberView;
        MemberInfoBean memberInfoBean = postDataBean.member;
        long j = postDataBean.createTime;
        boolean z = postDataBean.status == 3;
        ViewType[] viewTypeArr = new ViewType[1];
        ViewType viewType = postDataBean.member.getId() == a.g().c() ? null : postDataBean.member.getFollowStatus() == 0 ? ViewType.FOLLOW : ViewType.CANCEL_FOLLOW;
        viewTypeArr[0] = viewType;
        postMemberView.a(memberInfoBean, j, z, viewTypeArr);
        this.postMemberView.setOnMemberViewClickListener(new PostMemberView.a(this) {
            final /* synthetic */ VoiceDetailController b;

            public void a(ViewType viewType) {
                switch (viewType) {
                    case FOLLOW:
                        new cn.xiaochuankeji.tieba.background.e.b(postDataBean.member.getId(), null, null, new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
                            final /* synthetic */ AnonymousClass13 a;

                            {
                                this.a = r1;
                            }

                            public /* synthetic */ void onResponse(Object obj, Object obj2) {
                                a((JSONObject) obj, obj2);
                            }

                            public void a(JSONObject jSONObject, Object obj) {
                                this.a.b.postMemberView.a(postDataBean.member, postDataBean.createTime, postDataBean.status == 3, ViewType.CANCEL_FOLLOW);
                                org.greenrobot.eventbus.c.a().d(new cn.xiaochuankeji.tieba.ui.topic.data.b(postDataBean.member.getId(), true));
                            }
                        }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
                            final /* synthetic */ AnonymousClass13 a;

                            {
                                this.a = r1;
                            }

                            public void onErrorResponse(XCError xCError, Object obj) {
                                g.a(xCError.getMessage());
                            }
                        }).execute();
                        h.a(this.b.e, "zy_event_postdetail_page", "关注用户");
                        return;
                    case CANCEL_FOLLOW:
                        new cn.xiaochuankeji.tieba.background.e.a(postDataBean.member.getId(), null, new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
                            final /* synthetic */ AnonymousClass13 a;

                            {
                                this.a = r1;
                            }

                            public /* synthetic */ void onResponse(Object obj, Object obj2) {
                                a((JSONObject) obj, obj2);
                            }

                            public void a(JSONObject jSONObject, Object obj) {
                                this.a.b.postMemberView.a(postDataBean.member, postDataBean.createTime, postDataBean.status == 3, ViewType.FOLLOW);
                                org.greenrobot.eventbus.c.a().d(new cn.xiaochuankeji.tieba.ui.topic.data.b(postDataBean.member.getId(), false));
                            }
                        }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
                            final /* synthetic */ AnonymousClass13 a;

                            {
                                this.a = r1;
                            }

                            public void onErrorResponse(XCError xCError, Object obj) {
                                g.a(xCError.getMessage());
                            }
                        }).execute();
                        h.a(this.b.e, "zy_event_postdetail_page", "取消关注用户");
                        return;
                    default:
                        return;
                }
            }

            public void a() {
                this.b.a(postDataBean.member.getId(), postDataBean.postId);
            }

            public void b() {
                this.b.a(postDataBean, false, true);
            }

            public void c() {
            }

            public void d() {
            }
        });
    }

    private void a(long j, long j2) {
        MemberDetailActivity.a(this.e, j, j2, 1, 0);
    }

    private void d(final PostDataBean postDataBean) {
        this.operateView.a(postDataBean, new OnClickListener(this) {
            final /* synthetic */ VoiceDetailController b;

            public void onClick(View view) {
                this.b.a(postDataBean, true, false);
            }
        }, new OnClickListener(this) {
            final /* synthetic */ VoiceDetailController a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
            }
        }, new PostItemUpDownView.a(this) {
            final /* synthetic */ VoiceDetailController b;

            public void a(boolean z) {
                LikedUsersActivity.a(this.b.e, postDataBean.postId, z, 1);
            }

            public void a(int i, int i2, boolean z) {
                if (z) {
                    if (1 == i) {
                        this.b.g.a(postDataBean.postId, "postdetail", new d.b(this) {
                            final /* synthetic */ AnonymousClass16 a;

                            {
                                this.a = r1;
                            }

                            public void a(boolean z, String str) {
                                if (!z) {
                                    g.a(str);
                                }
                            }
                        });
                    } else if (-1 == i) {
                        this.b.g.a(postDataBean.postId, 0, "postdetail", new d.a(this) {
                            final /* synthetic */ AnonymousClass16 a;

                            {
                                this.a = r1;
                            }

                            public void a(boolean z, boolean z2, String str) {
                                if (!z) {
                                    g.a(str);
                                }
                            }
                        });
                    }
                }
                postDataBean.likeCount = i2;
                postDataBean.isLiked = i;
                if (1 == i) {
                    h.a(this.b.e, "zy_event_postdetail_page", "帖子顶");
                } else {
                    h.a(this.b.e, "zy_event_postdetail_page", "帖子踩");
                }
            }
        });
    }

    public void a(final PostDataBean postDataBean, boolean z, boolean z2) {
        ArrayList arrayList;
        SDBottomSheet sDBottomSheet = new SDBottomSheet(this.e, new SDBottomSheet.b(this) {
            final /* synthetic */ VoiceDetailController b;

            @SuppressLint({"SwitchIntDef"})
            public void a(int i) {
                switch (i) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                        this.b.f.a(this.b.e, postDataBean, i, "postdetail");
                        PostDataBean postDataBean = postDataBean;
                        postDataBean.shareCount++;
                        this.b.operateView.a(postDataBean);
                        return;
                    case 6:
                        cn.xiaochuankeji.tieba.ui.utils.d.a(postDataBean.content);
                        g.a("已复制");
                        return;
                    case 7:
                        this.b.f(postDataBean);
                        return;
                    case 8:
                        this.b.e(postDataBean);
                        return;
                    case 9:
                        this.b.a(postDataBean.postId);
                        return;
                    case 12:
                        this.b.b(postDataBean.postId);
                        return;
                    case 18:
                        cn.xiaochuankeji.tieba.ui.utils.d.a(HolderCreator.a(postDataBean));
                        g.a("已复制链接");
                        return;
                    default:
                        return;
                }
            }
        });
        ArrayList c = sDBottomSheet.c();
        if (z) {
            arrayList = null;
        } else {
            arrayList = a(postDataBean, z2);
        }
        sDBottomSheet.a(c, arrayList);
        sDBottomSheet.b();
    }

    private ArrayList<SDBottomSheet.c> a(PostDataBean postDataBean, boolean z) {
        ArrayList<SDBottomSheet.c> arrayList = new ArrayList();
        if (z && !TextUtils.isEmpty(postDataBean.content)) {
            arrayList.add(new SDBottomSheet.c(R.drawable.icon_option_copy, "复制文字", 6));
        }
        if (postDataBean.favored == 1) {
            arrayList.add(new SDBottomSheet.c(R.drawable.icon_option_favorite, "取消收藏", 8));
        } else {
            arrayList.add(new SDBottomSheet.c(R.drawable.icon_option_favorite, "收藏", 7));
        }
        if (a.g().c() == postDataBean.member.getId()) {
            arrayList.add(new SDBottomSheet.c(R.drawable.icon_option_delete, "删除", 9));
        } else {
            arrayList.add(new SDBottomSheet.c(R.drawable.icon_option_report, "举报", 12));
        }
        return arrayList;
    }

    private void e(final PostDataBean postDataBean) {
        this.f.b(postDataBean.postId).a(new rx.b.b<EmptyJson>(this) {
            final /* synthetic */ VoiceDetailController b;

            public /* synthetic */ void call(Object obj) {
                a((EmptyJson) obj);
            }

            public void a(EmptyJson emptyJson) {
                g.a("取消收藏成功");
                postDataBean.favored = 0;
            }
        }, new rx.b.b<Throwable>(this) {
            final /* synthetic */ VoiceDetailController a;

            {
                this.a = r1;
            }

            public /* synthetic */ void call(Object obj) {
                a((Throwable) obj);
            }

            public void a(Throwable th) {
                if (th instanceof ClientErrorException) {
                    g.a(th.getMessage());
                } else {
                    g.a("网络错误");
                }
            }
        });
    }

    private void a(final long j) {
        new cn.xiaochuankeji.tieba.ui.widget.b.a.a(this.e, "提示", "确定删除帖子吗？").a("取消", null).b("确定", new OnClickListener(this) {
            final /* synthetic */ VoiceDetailController b;

            public void onClick(View view) {
                this.b.f.a(j);
            }
        }).a();
    }

    private void f(final PostDataBean postDataBean) {
        if (cn.xiaochuankeji.tieba.ui.auth.a.a(this.e, "home_tab", 11, 0)) {
            cn.xiaochuankeji.tieba.ui.post.postitem.a aVar = new cn.xiaochuankeji.tieba.ui.post.postitem.a(this.e, new cn.xiaochuankeji.tieba.ui.post.postitem.a.a(this) {
                final /* synthetic */ VoiceDetailController b;

                public void a(boolean z) {
                    if (z) {
                        g.a("收藏成功");
                        postDataBean.favored = 1;
                        return;
                    }
                    g.a("收藏失败");
                }
            });
            aVar.a(postDataBean.postId);
            aVar.e();
        }
    }

    private void b(final long j) {
        Map m = cn.xiaochuankeji.tieba.background.utils.c.a.c().m();
        if (m.size() != 0) {
            SDCheckSheet sDCheckSheet = new SDCheckSheet(this.e, new SDCheckSheet.a(this) {
                final /* synthetic */ VoiceDetailController b;

                public void a(int i) {
                    this.b.f.a(j, i, "post");
                }
            });
            List arrayList = new ArrayList(m.keySet());
            for (int i = 0; i < arrayList.size(); i++) {
                boolean z;
                String str = (String) m.get(arrayList.get(i));
                int parseInt = Integer.parseInt((String) arrayList.get(i));
                if (i == arrayList.size() - 1) {
                    z = true;
                } else {
                    z = false;
                }
                sDCheckSheet.a(str, parseInt, z);
            }
            sDCheckSheet.b();
        }
    }

    public View b() {
        return this.d;
    }

    public void c() {
        cn.xiaochuankeji.tieba.ui.voice.b.d.a().b((b) this);
        this.a.cancel();
        this.b.cancel();
    }
}
