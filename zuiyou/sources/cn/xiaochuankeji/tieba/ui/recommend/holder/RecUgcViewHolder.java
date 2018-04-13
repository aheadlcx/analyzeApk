package cn.xiaochuankeji.tieba.ui.recommend.holder;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.f.b;
import cn.xiaochuankeji.tieba.background.modules.chat.models.beans.MessageEvent;
import cn.xiaochuankeji.tieba.background.modules.chat.models.beans.MessageEvent.MessageEventType;
import cn.xiaochuankeji.tieba.background.net.XCError;
import cn.xiaochuankeji.tieba.background.post.f;
import cn.xiaochuankeji.tieba.background.post.o;
import cn.xiaochuankeji.tieba.background.topic.Topic;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.background.utils.h;
import cn.xiaochuankeji.tieba.json.UgcVideoInfoBean;
import cn.xiaochuankeji.tieba.ui.homepage.HomePageActivity;
import cn.xiaochuankeji.tieba.ui.member.MemberDetailActivity;
import cn.xiaochuankeji.tieba.ui.my.MyPostActivity;
import cn.xiaochuankeji.tieba.ui.post.LikedUsersActivity;
import cn.xiaochuankeji.tieba.ui.post.postdetail.PostDetailActivity;
import cn.xiaochuankeji.tieba.ui.recommend.c;
import cn.xiaochuankeji.tieba.ui.recommend.data.RecUgcDataBean;
import cn.xiaochuankeji.tieba.ui.topic.TopicDetailActivity;
import cn.xiaochuankeji.tieba.ui.ugcvideodetail.UgcVideoActivity;
import cn.xiaochuankeji.tieba.ui.utils.d;
import cn.xiaochuankeji.tieba.ui.widget.MultipleLineEllipsisTextView;
import cn.xiaochuankeji.tieba.ui.widget.SDBottomSheet;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import cn.xiaochuankeji.tieba.ui.widget.k;
import cn.xiaochuankeji.tieba.ui.widget.updown.PostItemUpDownView;
import cn.xiaochuankeji.tieba.ui.widget.updown.PostItemUpDownView.a;
import com.alibaba.fastjson.JSON;
import com.facebook.drawee.a.a.e;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class RecUgcViewHolder extends a implements OnClickListener {
    private Context d;
    private String e = "index";
    private RecUgcDataBean f;
    private o g;
    @BindView
    ImageView ivMore;
    @BindView
    ImageView ivTediumPost;
    @BindView
    ImageView ivUserLevel;
    @BindView
    MultipleLineEllipsisTextView postContentView;
    @BindView
    PostItemUpDownView postItemUpDownView;
    @BindView
    WebImageView pvAvatar;
    @BindView
    TextView tvDanmuCount;
    @BindView
    TextView tvDur;
    @BindView
    TextView tvFollowCount;
    @BindView
    TextView tvPlayCount;
    @BindView
    TextView tvShare;
    @BindView
    TextView tvTopicName;
    @BindView
    TextView tvUserName;
    @BindView
    WebImageView ugcCover;
    @BindView
    WebImageView ugcCoverBg;
    @BindView
    FrameLayout vTopicContainer;
    @BindView
    FrameLayout videoContainer;

    public RecUgcViewHolder(View view) {
        super(view);
        this.d = view.getContext();
        ButterKnife.a(this, view);
    }

    public void a(a aVar, c cVar) {
        if (cVar != null && (cVar instanceof RecUgcDataBean)) {
            this.f = (RecUgcDataBean) cVar;
            UgcVideoInfoBean ugcVideoInfoBean = (this.f.ugcVideos == null || this.f.ugcVideos.size() == 0) ? null : (UgcVideoInfoBean) this.f.ugcVideos.get(0);
            if (ugcVideoInfoBean != null) {
                CharSequence charSequence;
                this.pvAvatar.setWebImage(b.a(ugcVideoInfoBean.member.getId(), ugcVideoInfoBean.member.getAvatarId()));
                this.tvUserName.setText(ugcVideoInfoBean.member.getNickName());
                this.tvShare.setText(0 == this.f.shareCount ? "分享" : this.f.shareCount + "");
                TextView textView = this.tvFollowCount;
                if (0 == this.f.followCount) {
                    charSequence = "跟拍";
                } else {
                    charSequence = this.f.followCount + "";
                }
                textView.setText(charSequence);
                this.postItemUpDownView.a(ugcVideoInfoBean.liked, ugcVideoInfoBean.likeCount, new a(this) {
                    final /* synthetic */ RecUgcViewHolder a;

                    {
                        this.a = r1;
                    }

                    public void a(boolean z) {
                        if (this.a.f != null) {
                            LikedUsersActivity.a(this.a.d, this.a.f.id, z, 3, this.a.e, 0);
                        }
                    }

                    public void a(int i, int i2, boolean z) {
                        boolean z2 = true;
                        this.a.f.isLiked = i;
                        this.a.f.likeCount = (long) i2;
                        if (z) {
                            if (i != 1) {
                                z2 = false;
                            }
                            this.a.g.a(z2);
                        }
                    }
                });
                String a = cn.xiaochuankeji.tieba.background.utils.d.a.a("/img/view/id/", (long) ugcVideoInfoBean.img.id, "/sz/360");
                this.ugcCoverBg.setController(((e) com.facebook.drawee.a.a.c.a().b(ImageRequestBuilder.a(Uri.parse(a)).a(new com.facebook.imagepipeline.j.a(50)).n())).k());
                this.ugcCover.setImageURI(a);
                this.tvPlayCount.setText(d.b(ugcVideoInfoBean.plays));
                this.tvDanmuCount.setText(d.b((int) this.f.danmakuCount));
                if (ugcVideoInfoBean.videoInfo.duration > 0) {
                    this.tvDur.setVisibility(0);
                    this.tvDur.setText(d.a(ugcVideoInfoBean.videoInfo.duration * 1000));
                } else {
                    this.tvDur.setText(null);
                    this.tvDur.setVisibility(8);
                }
                a();
                charSequence = this.f.topic.topicName;
                if (TextUtils.isEmpty(charSequence)) {
                    this.vTopicContainer.setVisibility(8);
                } else {
                    this.vTopicContainer.setVisibility(0);
                    this.tvTopicName.setText(charSequence);
                }
                if (this.d instanceof TopicDetailActivity) {
                    this.ivMore.setVisibility(0);
                    this.ivMore.setOnClickListener(this);
                }
                this.itemView.setLongClickable(true);
                this.itemView.setOnLongClickListener(new OnLongClickListener(this) {
                    final /* synthetic */ RecUgcViewHolder a;

                    {
                        this.a = r1;
                    }

                    public boolean onLongClick(View view) {
                        this.a.a(false, true);
                        return true;
                    }
                });
                this.videoContainer.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ RecUgcViewHolder a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        this.a.c();
                    }
                });
                b();
                this.g = new o(this.f, this.e, this.d);
            }
        }
    }

    private void a() {
        if (TextUtils.isEmpty(this.f.title)) {
            this.postContentView.setVisibility(8);
            return;
        }
        this.postContentView.setMaxLines(4);
        this.postContentView.setVisibility(0);
        this.postContentView.a(this.f.title, null, this.f.id, c.a.d.a.a.a().a((int) R.color.CT_4), 2);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llMemberInfo:
                c();
                return;
            case R.id.pvAvatar:
            case R.id.open_member:
                e();
                return;
            case R.id.tvShare:
                a(true, false);
                return;
            case R.id.ivTediumPost:
                d();
                return;
            case R.id.tvTopicName:
                f();
                return;
            case R.id.iv_more:
                a(false, false);
                return;
            case R.id.tvFollowCount:
                c();
                return;
            default:
                return;
        }
    }

    private void b() {
        this.itemView.findViewById(R.id.llMemberInfo).setOnClickListener(this);
        this.ivTediumPost.setOnClickListener(this);
        this.tvShare.setOnClickListener(this);
        this.tvFollowCount.setOnClickListener(this);
        this.pvAvatar.setOnClickListener(this);
        this.itemView.findViewById(R.id.open_member).setOnClickListener(this);
        this.tvTopicName.setOnClickListener(this);
        this.postContentView.setOnExpandableTextViewListener(new MultipleLineEllipsisTextView.c(this) {
            final /* synthetic */ RecUgcViewHolder a;

            {
                this.a = r1;
            }

            public void onClick() {
                this.a.c();
            }

            public void a() {
                this.a.a(false, true);
            }
        });
    }

    private void a(boolean z, boolean z2) {
        SDBottomSheet sDBottomSheet = new SDBottomSheet((Activity) this.d, new SDBottomSheet.b(this) {
            final /* synthetic */ RecUgcViewHolder a;

            {
                this.a = r1;
            }

            public void a(int i) {
                if (i == 4 || i == 2 || i == 3 || i == 5 || i == 1) {
                    this.a.g.a(i);
                } else if (i == 6) {
                    d.a(this.a.f.title);
                    g.a("已复制");
                } else if (i == 12) {
                    this.a.g.a();
                } else if (i == 18) {
                    this.a.g.c();
                } else if (i == 9) {
                    this.a.g.b();
                }
            }
        });
        ArrayList arrayList = new ArrayList();
        if (!z) {
            if (z2 && !TextUtils.isEmpty(this.f.title)) {
                arrayList.add(new SDBottomSheet.c(R.drawable.icon_option_copy, "复制文字", 6));
            }
            Object obj = cn.xiaochuankeji.tieba.background.a.g().c() == this.f.member.getId() ? 1 : null;
            if (obj != null) {
                arrayList.add(new SDBottomSheet.c(R.drawable.icon_option_delete, "删除", 9));
            }
            if (obj == null && !(this.d instanceof MyPostActivity)) {
                arrayList.add(new SDBottomSheet.c(R.drawable.icon_option_report, "举报", 12));
            }
        }
        sDBottomSheet.a(sDBottomSheet.c(), arrayList);
        sDBottomSheet.b();
    }

    private void c() {
        if (this.f != null) {
            List arrayList = new ArrayList();
            for (UgcVideoInfoBean ugcVideoInfoBean : this.f.ugcVideos) {
                arrayList.add(Long.valueOf(ugcVideoInfoBean.id));
            }
            UgcVideoActivity.a(this.d, (UgcVideoInfoBean) this.f.ugcVideos.get(0), false, this.e, this.f);
        }
    }

    private void d() {
        k kVar = new k(this.d);
        kVar.a(f.a(), new k.b(this) {
            final /* synthetic */ RecUgcViewHolder a;

            {
                this.a = r1;
            }

            public void a(ArrayList<String> arrayList, String str) {
                MessageEvent messageEvent = new MessageEvent(MessageEventType.MESSAGE_POST_DELETE);
                messageEvent.setData(this.a.f);
                org.greenrobot.eventbus.c.a().d(messageEvent);
                new f(f.a(this.a.f.id, this.a.f.member.getId(), (String[]) arrayList.toArray(new String[arrayList.size()])), new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
                    final /* synthetic */ AnonymousClass6 a;

                    {
                        this.a = r1;
                    }

                    public /* synthetic */ void onResponse(Object obj, Object obj2) {
                        a((JSONObject) obj, obj2);
                    }

                    public void a(JSONObject jSONObject, Object obj) {
                    }
                }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
                    final /* synthetic */ AnonymousClass6 a;

                    {
                        this.a = r1;
                    }

                    public void onErrorResponse(XCError xCError, Object obj) {
                        g.a(xCError.getMessage());
                    }
                }).execute();
            }
        });
        kVar.show();
        h.a(this.d, "zy_event_theme", "tag3");
    }

    private void e() {
        if (this.f.member != null) {
            MemberDetailActivity.a(this.d, this.f.member.getId(), 1, 0);
        }
    }

    private void f() {
        if (!(this.d instanceof TopicDetailActivity)) {
            String str = "";
            if (HomePageActivity.class.isInstance(this.d)) {
                str = "index";
            } else if (PostDetailActivity.class.isInstance(this.d)) {
                str = "postdetail";
            }
            try {
                TopicDetailActivity.a(this.d, new Topic(new JSONObject(JSON.toJSONString(this.f.topic))), str);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
