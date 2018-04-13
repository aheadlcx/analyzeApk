package cn.xiaochuankeji.tieba.ui.recommend.holder;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.a;
import cn.xiaochuankeji.tieba.background.beans.Medal;
import cn.xiaochuankeji.tieba.background.data.Comment;
import cn.xiaochuankeji.tieba.background.data.ServerImage;
import cn.xiaochuankeji.tieba.background.data.post.Post;
import cn.xiaochuankeji.tieba.background.data.post.Post.PostVote;
import cn.xiaochuankeji.tieba.background.data.tag.NavigatorTag;
import cn.xiaochuankeji.tieba.background.favorite.Favorite;
import cn.xiaochuankeji.tieba.background.modules.chat.models.beans.MessageEvent;
import cn.xiaochuankeji.tieba.background.modules.chat.models.beans.MessageEvent.MessageEventType;
import cn.xiaochuankeji.tieba.background.net.XCError;
import cn.xiaochuankeji.tieba.background.picture.PictureImpl.Type;
import cn.xiaochuankeji.tieba.background.post.d;
import cn.xiaochuankeji.tieba.background.post.k;
import cn.xiaochuankeji.tieba.background.post.n;
import cn.xiaochuankeji.tieba.background.post.r;
import cn.xiaochuankeji.tieba.background.topic.BlockTopicActionRequest;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.background.utils.h;
import cn.xiaochuankeji.tieba.background.utils.newshare.PostShareDataModel;
import cn.xiaochuankeji.tieba.background.utils.newshare.ShareDataModel;
import cn.xiaochuankeji.tieba.json.recommend.CommentBean;
import cn.xiaochuankeji.tieba.json.recommend.ServerImageBean;
import cn.xiaochuankeji.tieba.json.recommend.ServerVideoBean;
import cn.xiaochuankeji.tieba.json.recommend.WebPageBean;
import cn.xiaochuankeji.tieba.ui.homepage.HomePageActivity;
import cn.xiaochuankeji.tieba.ui.mediabrowse.MediaBrowseActivity;
import cn.xiaochuankeji.tieba.ui.member.MemberDetailActivity;
import cn.xiaochuankeji.tieba.ui.my.assessor.UserAssessActivity;
import cn.xiaochuankeji.tieba.ui.post.LikedUsersActivity;
import cn.xiaochuankeji.tieba.ui.post.PostVoteDetailActivity;
import cn.xiaochuankeji.tieba.ui.post.postdetail.PostDetailActivity;
import cn.xiaochuankeji.tieba.ui.post.postitem.ViewGodReviewIndicators;
import cn.xiaochuankeji.tieba.ui.post.postitem.i;
import cn.xiaochuankeji.tieba.ui.recommend.c;
import cn.xiaochuankeji.tieba.ui.recommend.data.RecPostDataBean;
import cn.xiaochuankeji.tieba.ui.recommend.widget.ResizeMultiDraweeView;
import cn.xiaochuankeji.tieba.ui.topic.TopicDetailActivity;
import cn.xiaochuankeji.tieba.ui.topic.TopicPostTopActivity;
import cn.xiaochuankeji.tieba.ui.utils.e;
import cn.xiaochuankeji.tieba.ui.widget.MultipleLineEllipsisTextView;
import cn.xiaochuankeji.tieba.ui.widget.SDBottomSheet;
import cn.xiaochuankeji.tieba.ui.widget.SDBottomSheet.b;
import cn.xiaochuankeji.tieba.ui.widget.customtv.ExpandableTextView.f;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import cn.xiaochuankeji.tieba.ui.widget.updown.PostItemUpDownView;
import com.iflytek.aiui.AIUIConstant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONObject;

public class RecPostViewHolder extends a implements b {
    @BindView
    WebImageView avatarView;
    @BindView
    RelativeLayout commonLinkLayout;
    private Context d;
    private final HashMap<Long, cn.xiaochuankeji.tieba.ui.post.postitem.b> e = new HashMap();
    private RecPostDataBean f;
    private d g = a.j();
    @BindView
    RelativeLayout godReviewContainer;
    @BindView
    ViewGodReviewIndicators godReviewIndicators;
    private String h;
    @BindView
    View hotFlag;
    private cn.xiaochuankeji.tieba.ui.post.postitem.a i;
    @BindView
    WebImageView ivCommonLink;
    @BindView
    ImageView ivMore;
    @BindView
    ResizeMultiDraweeView ivPostImages;
    @BindView
    WebImageView ivWechatLink;
    @BindView
    WebImageView ivWyLink;
    @BindView
    ImageView iv_official;
    private k j;
    private cn.xiaochuankeji.tieba.ui.comment.b k;
    private NavigatorTag l;
    @BindView
    RelativeLayout linkLayout;
    @BindView
    ViewGroup memberContainer;
    @BindView
    PostItemUpDownView postItemUpDownView;
    @BindView
    ImageView tediumView;
    @BindView
    ImageView telentFlag;
    @BindView
    FrameLayout topicFrameLayout;
    @BindView
    TextView tvCancelFavor;
    @BindView
    TextView tvComment;
    @BindView
    TextView tvCommonLink;
    @BindView
    TextView tvFollow;
    @BindView
    TextView tvNickname;
    @BindView
    MultipleLineEllipsisTextView tvPost;
    @BindView
    TextView tvShare;
    @BindView
    TextView tvTime;
    @BindView
    TextView tvTopicName;
    @BindView
    TextView tvWechatDesc;
    @BindView
    TextView tvWechatTitle;
    @BindView
    TextView tvWyAuthor;
    @BindView
    TextView tvWyTitle;
    @BindView
    ImageView userLevelFlag;
    @BindView
    ViewGroup voteLayout;
    @BindView
    RelativeLayout wechatLinkLayout;
    @BindView
    RelativeLayout wyLinkLayout;

    public RecPostViewHolder(View view, NavigatorTag navigatorTag) {
        super(view);
        this.d = view.getContext();
        ButterKnife.a(this, view);
        this.l = navigatorTag;
        if (MemberDetailActivity.class.isInstance(this.d)) {
            this.h = AIUIConstant.USER;
        } else if (PostDetailActivity.class.isInstance(this.d)) {
            this.h = "postdetail";
        } else if (TopicDetailActivity.class.isInstance(this.d)) {
            this.h = "topicdetail";
        } else if (HomePageActivity.class.isInstance(this.d)) {
            String str = (this.l == null || TextUtils.isEmpty(this.l.ename)) ? "index" : this.l.ename;
            this.h = str;
        } else {
            this.h = "other";
        }
    }

    public void a(a aVar, c cVar) {
        long j = 0;
        int i = 0;
        if (cVar != null && (cVar instanceof RecPostDataBean)) {
            long j2;
            this.f = (RecPostDataBean) cVar;
            int i2 = ((this.d instanceof HomePageActivity) && this.f.status == 3) ? 1 : 0;
            View view = this.hotFlag;
            if (i2 == 0) {
                i = 8;
            }
            view.setVisibility(i);
            d();
            e();
            c();
            b();
            a();
            Post post = this.f.oldPostData;
            long j3 = this.f.oldPostData._ID;
            if (this.f.oldPostData._topic != null) {
                j2 = this.f.oldPostData._topic._topicID;
            } else {
                j2 = 0;
            }
            if (this.f.oldPostData._member != null) {
                j = this.f.oldPostData._member.getId();
            }
            this.j = new k(post, j3, j2, j, this.d);
            aVar.itemView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ RecPostViewHolder a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.a("post");
                }
            });
            a(aVar.itemView, new rx.b.b<Void>(this) {
                final /* synthetic */ RecPostViewHolder a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void call(Object obj) {
                    a((Void) obj);
                }

                public void a(Void voidR) {
                    this.a.a(this.a.f, true);
                }
            });
        }
    }

    public void a(int i) {
        if (i == 4 || i == 2 || i == 3 || i == 5 || i == 1) {
            c(i);
            g();
        } else if (i == 6) {
            cn.xiaochuankeji.tieba.ui.utils.d.a(this.tvPost.getFullText());
            g.a("已复制");
        } else if (i == 7) {
            h();
        } else if (i == 8) {
            i();
        } else if (i == 9) {
            this.j.b(0);
        } else if (i == 10) {
            this.j.a(true);
        } else if (i == 11) {
            a(this.f.oldPostData._topic._topicID);
        } else if (i == 12) {
            this.j.a();
        } else if (i == 13) {
            TopicPostTopActivity.a(this.d, this.f.oldPostData, -1, "");
        } else if (i == 17) {
            this.j.a(false);
        } else if (i == 18) {
            cn.xiaochuankeji.tieba.ui.utils.d.a("#最右#分享一条有趣的内容给你，不好看算我输。请戳链接>>" + cn.xiaochuankeji.tieba.background.utils.d.a.a(RecPostDataBean.getOriginPostData(this.f)) + "?zy_to=applink&to=applink");
            g.a("已复制链接");
        }
    }

    private void a() {
        if (this.f.voteInfo == null || this.f.oldPostData.postVote == null || this.f.oldPostData.postVote.getVoteItems().size() == 0) {
            this.voteLayout.setVisibility(8);
            return;
        }
        this.voteLayout.setVisibility(0);
        i iVar = new i(this.itemView.getContext(), this.voteLayout, this.f.oldPostData, null);
        iVar.a(new i.b(this) {
            final /* synthetic */ RecPostViewHolder a;

            {
                this.a = r1;
            }

            public void a(PostVote postVote) {
                this.a.f.oldPostData.postVote = postVote;
            }
        });
        iVar.a(new i.a(this) {
            final /* synthetic */ RecPostViewHolder a;

            {
                this.a = r1;
            }

            public void a() {
                PostVoteDetailActivity.a(this.a.itemView.getContext(), this.a.f.oldPostData._ID, (long) this.a.f.oldPostData.postVote.getId(), 0);
            }
        });
        iVar.a();
        LayoutParams layoutParams = (LayoutParams) this.voteLayout.getLayoutParams();
        if (this.f.oldPostData.hasImage()) {
            layoutParams.topMargin = cn.htjyb.c.a.a(15.0f, this.itemView.getContext());
        } else {
            layoutParams.topMargin = cn.htjyb.c.a.a(0.0f, this.itemView.getContext());
        }
        layoutParams.height = this.f.oldPostData.postVote.getVoteItems().size() * this.itemView.getResources().getDimensionPixelOffset(R.dimen.divide_item_height_44);
        this.voteLayout.setLayoutParams(layoutParams);
    }

    private void b() {
        if (this.f.webPage == null) {
            this.linkLayout.setVisibility(8);
            return;
        }
        this.linkLayout.setVisibility(0);
        WebPageBean webPageBean = this.f.webPage;
        this.commonLinkLayout.setVisibility(8);
        this.wechatLinkLayout.setVisibility(8);
        this.wyLinkLayout.setVisibility(8);
        if (webPageBean.type == 0) {
            this.commonLinkLayout.setVisibility(0);
            this.ivCommonLink.setImageResource(c.a.c.e().c() ? R.drawable.image_link_placeholder_night : R.drawable.image_link_placeholder);
            this.ivCommonLink.setImageURI(webPageBean.thumbUrl);
            this.tvCommonLink.setText(TextUtils.isEmpty(webPageBean.title) ? webPageBean.url : webPageBean.title);
        } else if (webPageBean.type == 1) {
            this.wechatLinkLayout.setVisibility(0);
            this.ivWechatLink.setImageURI(webPageBean.thumbUrl);
            this.tvWechatTitle.setText(webPageBean.title);
            this.tvWechatDesc.setText(webPageBean.describe);
        } else if (webPageBean.type == 3) {
            this.wyLinkLayout.setVisibility(0);
            this.ivWyLink.setImageURI(webPageBean.thumbUrl);
            this.tvWyTitle.setText(webPageBean.title);
            this.tvWyAuthor.setText(webPageBean.author);
        }
    }

    private void c() {
        if (this.f.godReviews == null || this.f.godReviews.size() == 0 || this.f.oldPostData.comments == null || this.f.oldPostData.comments.size() == 0) {
            this.godReviewContainer.setVisibility(8);
            return;
        }
        this.godReviewContainer.setVisibility(0);
        CommentBean commentBean = (CommentBean) this.f.godReviews.get(0);
        cn.xiaochuankeji.tieba.ui.post.postitem.b bVar = (cn.xiaochuankeji.tieba.ui.post.postitem.b) this.e.get(Long.valueOf(this.f.getId()));
        if (bVar == null) {
            bVar = new cn.xiaochuankeji.tieba.ui.post.postitem.b();
            this.e.put(Long.valueOf(this.f.getId()), bVar);
        }
        this.godReviewIndicators.a(this.f.godReviews.size(), bVar);
        f fVar = (f) this.c.get(Long.valueOf(commentBean.commentId));
        if (fVar == null) {
            fVar = new f();
            this.c.put(Long.valueOf(commentBean.commentId), fVar);
        }
        Comment comment = (Comment) this.f.oldPostData.comments.get(0);
        this.k = new cn.xiaochuankeji.tieba.ui.comment.b(this.d);
        if (this.godReviewContainer.getChildCount() > 0) {
            this.godReviewContainer.removeAllViews();
        }
        this.godReviewContainer.addView(this.k.f_());
        if (this.k != null) {
            this.k.a(comment, this.f.oldPostData, fVar);
            this.k.a(b(this.l));
        }
    }

    private void d() {
        int i = 0;
        if (this.f.member != null) {
            this.avatarView.setWebImage(cn.xiaochuankeji.tieba.background.f.b.a(this.f.member.getId(), this.f.member.getAvatarId()));
            if (this.d instanceof TopicDetailActivity) {
            }
            if (this.f.member.getNickName() != null) {
                this.tvNickname.setText(this.f.member.getNickName().trim());
            }
            final Medal medal = this.f.member.getMedal();
            if (medal != null) {
                this.telentFlag.setVisibility(0);
                if (medal.original == 1) {
                    this.telentFlag.setImageResource(c.a.d.a.a.a().d(R.drawable.talent_original));
                } else if (medal.original == 2) {
                    this.telentFlag.setImageResource(c.a.d.a.a.a().d(R.drawable.talent));
                } else if (medal.original == 3) {
                    this.telentFlag.setImageResource(c.a.d.a.a.a().d(R.drawable.topic_talent_small_icon));
                } else {
                    this.telentFlag.setVisibility(8);
                }
                this.telentFlag.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ RecPostViewHolder b;

                    public void onClick(View view) {
                        new cn.xiaochuankeji.tieba.ui.widget.d(this.b.d, medal).a(this.b.telentFlag).show();
                    }
                });
            } else {
                this.telentFlag.setVisibility(8);
            }
            ImageView imageView = this.iv_official;
            if (!this.f.member.isOfficial()) {
                i = 8;
            }
            imageView.setVisibility(i);
            this.iv_official.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ RecPostViewHolder a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    e.d(this.a.d);
                }
            });
            OnClickListener anonymousClass22 = new OnClickListener(this) {
                final /* synthetic */ RecPostViewHolder a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    MemberDetailActivity.a(this.a.itemView.getContext(), this.a.f.member.getId(), this.a.f.postId, 1, 0);
                }
            };
            this.tvNickname.setOnClickListener(anonymousClass22);
            this.avatarView.setOnClickListener(anonymousClass22);
        }
    }

    private void e() {
        int i;
        if (this.f.postType == 1) {
            i = 3;
        } else {
            i = 2;
        }
        int a = c.a.d.a.a.a().a((int) R.color.CT_4);
        if (this.d instanceof PostDetailActivity) {
            this.tvPost.setExpandable(false);
        }
        if (TextUtils.isEmpty(this.f.content)) {
            this.tvPost.setVisibility(8);
        } else {
            this.tvPost.setVisibility(0);
            this.tvPost.a(this.f.content, this.b, this.f.postId, a, i);
            this.tvPost.setOnExpandableTextViewListener(new MultipleLineEllipsisTextView.c(this) {
                final /* synthetic */ RecPostViewHolder a;

                {
                    this.a = r1;
                }

                public void onClick() {
                    this.a.a("post");
                }

                public void a() {
                    this.a.a(this.a.f, true);
                }
            });
        }
        if (this.f.images == null || this.f.images.size() <= 0) {
            this.ivPostImages.setVisibility(8);
        } else {
            this.ivPostImages.setVisibility(0);
            if (this.f.videoJsons != null && this.f.videoJsons.size() > 0) {
                for (ServerImageBean serverImageBean : this.f.images) {
                    serverImageBean.videoBean = (ServerVideoBean) this.f.videoJsons.get(String.valueOf(serverImageBean.id));
                }
            }
            this.ivPostImages.setImageUris(this.f.images);
            this.ivPostImages.setOnItemClickListener(new ResizeMultiDraweeView.a(this) {
                final /* synthetic */ RecPostViewHolder a;

                {
                    this.a = r1;
                }

                public void a(int i, Rect rect) {
                    this.a.b(i);
                }

                public void a() {
                    this.a.a(this.a.f, true);
                }
            });
        }
        if (this.f.topic != null) {
            this.tvTopicName.setText(this.f.topic.topicName);
        }
        this.tvShare.setText(this.f.shareCount > 0 ? this.f.shareCount + "" : "分享");
        this.tvComment.setText(this.f.reviewCount > 0 ? this.f.reviewCount + "" : "评论");
        this.postItemUpDownView.a(this.f.isLiked, this.f.likeCount, new PostItemUpDownView.a(this) {
            final /* synthetic */ RecPostViewHolder a;

            {
                this.a = r1;
            }

            public void a(boolean z) {
                LikedUsersActivity.a(this.a.d, this.a.f.getId(), z, 1);
            }

            public void a(int i, int i2, boolean z) {
                if (z) {
                    if (1 == i) {
                        this.a.g.a(this.a.f.getId(), this.a.h, new d.b(this) {
                            final /* synthetic */ AnonymousClass2 a;

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
                        this.a.g.a(this.a.f.getId(), 0, this.a.h, new d.a(this) {
                            final /* synthetic */ AnonymousClass2 a;

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
                this.a.f.isLiked = i;
                this.a.f.likeCount = i2;
                if (PostDetailActivity.class.isInstance(this.a.d)) {
                    r.a().a(this.a.f.getId(), i, i2);
                }
                if (1 == i) {
                    if (HomePageActivity.class.isInstance(this.a.d) && HomePageActivity.e()) {
                        h.a(this.a.d, "zy_event_homepage_tab_recommend", "顶点击次数");
                    } else if (PostDetailActivity.class.isInstance(this.a.d)) {
                        h.a(this.a.d, "zy_event_postdetail_page", "帖子顶");
                    }
                } else if (HomePageActivity.class.isInstance(this.a.d) && HomePageActivity.e()) {
                    h.a(this.a.d, "zy_event_homepage_tab_recommend", "踩点击次数");
                } else if (PostDetailActivity.class.isInstance(this.a.d)) {
                    h.a(this.a.d, "zy_event_postdetail_page", "帖子踩");
                }
            }
        });
        this.tvShare.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ RecPostViewHolder a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.f();
            }
        });
        this.tvComment.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ RecPostViewHolder a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.a("review");
            }
        });
        this.topicFrameLayout.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ RecPostViewHolder a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.k();
                TopicDetailActivity.a(this.a.d, this.a.f.oldPostData._topic, this.a.h, this.a.f.getId());
            }
        });
        if (a.q().b(this.l)) {
            this.tediumView.setVisibility(8);
            this.tediumView.setOnClickListener(null);
            return;
        }
        this.tediumView.setVisibility(0);
        this.tediumView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ RecPostViewHolder a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                cn.xiaochuankeji.tieba.ui.widget.k kVar = new cn.xiaochuankeji.tieba.ui.widget.k(this.a.d);
                kVar.a(this.a.f.oldPostData, cn.xiaochuankeji.tieba.background.utils.c.a.c().n(), new cn.xiaochuankeji.tieba.ui.widget.k.b(this) {
                    final /* synthetic */ AnonymousClass6 a;

                    {
                        this.a = r1;
                    }

                    public void a(ArrayList<String> arrayList, String str) {
                        this.a.a.a((ArrayList) arrayList, str);
                        if (this.a.a.a != null) {
                            this.a.a.a.a(this.a.a.f.postId);
                        }
                    }
                });
                kVar.show();
                if (HomePageActivity.class.isInstance(this.a.d) && HomePageActivity.e()) {
                    h.a(this.a.d, "zy_event_homepage_tab_recommend", "X 点击次数");
                }
            }
        });
    }

    private void a(String str) {
        if (!PostDetailActivity.class.isInstance(this.d) && !UserAssessActivity.class.isInstance(this.d)) {
            boolean z = this.d instanceof TopicDetailActivity;
            PostDetailActivity.a(this.d, this.f.oldPostData, z, this.f.oldPostData._member.getTopicRole(), str, a(this.l));
        }
    }

    public void a(final RecPostDataBean recPostDataBean, boolean z) {
        if (!UserAssessActivity.class.isInstance(this.d)) {
            long id = recPostDataBean.member.getId();
            SDBottomSheet sDBottomSheet = new SDBottomSheet((Activity) this.d, this);
            sDBottomSheet.a(sDBottomSheet.c(), a(id, z));
            sDBottomSheet.b();
            a.p().d().execute(new Runnable(this) {
                final /* synthetic */ RecPostViewHolder b;

                public void run() {
                    cn.xiaochuankeji.tieba.a.d.a(RecPostDataBean.getOriginPostData(recPostDataBean));
                }
            });
        }
    }

    private void f() {
        SDBottomSheet sDBottomSheet = new SDBottomSheet((Activity) this.d, this);
        sDBottomSheet.a(sDBottomSheet.c(), null);
        sDBottomSheet.b();
    }

    private ArrayList<SDBottomSheet.c> a(long j, boolean z) {
        Object cVar;
        ArrayList<SDBottomSheet.c> arrayList = new ArrayList();
        if (!TextUtils.isEmpty(this.tvPost.getText()) && z) {
            arrayList.add(new SDBottomSheet.c(R.drawable.icon_option_copy, "复制文字", 6));
        }
        Object obj = a.g().c() == j ? 1 : null;
        if (this.f.oldPostData.isFavored()) {
            cVar = new SDBottomSheet.c(R.drawable.icon_option_favorite, "取消收藏", 8);
        } else {
            cVar = new SDBottomSheet.c(R.drawable.icon_option_favorite, "收藏", 7);
        }
        arrayList.add(cVar);
        if (this.d instanceof HomePageActivity) {
            arrayList.add(new SDBottomSheet.c(R.drawable.toast_shield, "屏蔽该话题", 11));
        }
        if (obj != null) {
            arrayList.add(new SDBottomSheet.c(R.drawable.icon_option_delete, "删除", 9));
        } else {
            arrayList.add(new SDBottomSheet.c(R.drawable.icon_option_report, "举报", 12));
        }
        return arrayList;
    }

    private void b(int i) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator it = this.f.oldPostData._imgList.iterator();
        while (it.hasNext()) {
            cn.htjyb.b.a a;
            ServerImage serverImage = (ServerImage) it.next();
            arrayList2.add(a.f().a(Type.kPostPic228, serverImage.postImageId));
            if (serverImage.isVideo()) {
                a = a.f().a(this.f.oldPostData.getImgVideoBy(serverImage.postImageId).getUrl(), Type.kVideo, serverImage.postImageId);
            } else if (serverImage.isMP4()) {
                a = a.f().a(Type.kMP4, (long) serverImage.mp4Id);
            } else if (serverImage.isGif()) {
                a = a.f().a(Type.kGif, serverImage.postImageId);
            } else {
                a = a.f().a(Type.kPostPicLarge, serverImage.postImageId);
            }
            a.setRotate(serverImage.rotate);
            arrayList.add(a);
        }
        MediaBrowseActivity.a(this.d, i, this.f.oldPostData, arrayList2, arrayList, this.f.oldPostData._imgList, this.f.oldPostData.imgVideos, a(this.l));
    }

    private void c(final int i) {
        final ShareDataModel postShareDataModel = new PostShareDataModel(this.f.oldPostData, this.f.oldPostData.comments.size() > 0 ? (Comment) this.f.oldPostData.comments.get(0) : null, i);
        postShareDataModel.prepareData(new ShareDataModel.a(this) {
            final /* synthetic */ RecPostViewHolder c;

            public void a() {
                cn.xiaochuankeji.tieba.background.utils.share.b.a().a((Activity) this.c.d, postShareDataModel);
                cn.xiaochuankeji.tieba.background.i.a.a(this.c.f.oldPostData._ID, this.c.h, (String) cn.xiaochuankeji.tieba.d.d.b.get(Integer.valueOf(i)), postShareDataModel.getABTestId());
                if (this.c.f.shareCount != -1) {
                    RecPostDataBean a = this.c.f;
                    a.shareCount++;
                    this.c.tvShare.setText("" + this.c.f.shareCount);
                }
            }
        });
    }

    private void g() {
        if ((this.d instanceof HomePageActivity) && HomePageActivity.e()) {
            h.a(this.d, "zy_event_homepage_tab_recommend", "分享点击次数");
        }
    }

    private void h() {
        if (cn.xiaochuankeji.tieba.ui.auth.a.a((AppCompatActivity) this.d, "home_tab", 11, 0)) {
            if (this.i == null) {
                this.i = new cn.xiaochuankeji.tieba.ui.post.postitem.a((Activity) this.d, new cn.xiaochuankeji.tieba.ui.post.postitem.a.a(this) {
                    final /* synthetic */ RecPostViewHolder a;

                    {
                        this.a = r1;
                    }

                    public void a(boolean z) {
                        if (z) {
                            g.a("收藏成功");
                            this.a.f.favored = 1;
                            return;
                        }
                        g.a("收藏失败");
                    }
                });
            }
            this.i.a(this.f.getId());
            this.i.e();
        }
    }

    private void i() {
        new cn.xiaochuankeji.tieba.background.favorite.g(System.currentTimeMillis(), this.f.getId(), new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
            final /* synthetic */ RecPostViewHolder a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onResponse(Object obj, Object obj2) {
                a((JSONObject) obj, obj2);
            }

            public void a(JSONObject jSONObject, Object obj) {
                if (jSONObject != null) {
                    Favorite favorite = new Favorite(jSONObject);
                    cn.xiaochuankeji.tieba.background.favorite.f.a().a(favorite.getId(), favorite.getPostCount());
                    g.a("取消收藏成功");
                    this.a.f.favored = 0;
                    MessageEvent messageEvent = new MessageEvent(MessageEventType.MESSAGE_POST_CANCEL_FAVORED);
                    messageEvent.setData(this.a.f.oldPostData);
                    org.greenrobot.eventbus.c.a().d(messageEvent);
                }
            }
        }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
            final /* synthetic */ RecPostViewHolder a;

            {
                this.a = r1;
            }

            public void onErrorResponse(XCError xCError, Object obj) {
                g.a("取消收藏失败");
            }
        }).execute();
    }

    private void a(long j) {
        new BlockTopicActionRequest(j, a.g().a(), null, new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
            final /* synthetic */ RecPostViewHolder a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onResponse(Object obj, Object obj2) {
                a((JSONObject) obj, obj2);
            }

            public void a(JSONObject jSONObject, Object obj) {
                g.a("屏蔽成功,可在\"我的\"中取消");
                this.a.j();
            }
        }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
            final /* synthetic */ RecPostViewHolder a;

            {
                this.a = r1;
            }

            public void onErrorResponse(XCError xCError, Object obj) {
                g.a(xCError.getMessage());
            }
        }).execute();
    }

    private void j() {
        MessageEvent messageEvent = new MessageEvent(MessageEventType.MESSAGE_POST_DELETE);
        messageEvent.setData(Long.valueOf(this.f.getId()));
        org.greenrobot.eventbus.c.a().d(messageEvent);
    }

    private void k() {
        if (HomePageActivity.class.isInstance(this.d) && HomePageActivity.e()) {
            h.a(this.d, "zy_event_homepage_tab_recommend", "话题点击次数");
        }
    }

    private void a(ArrayList<String> arrayList, String str) {
        long id = this.f.getId();
        final long j = this.f.topic.topicID;
        new n(id, j, 0, arrayList, str, this.l.ename, new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
            final /* synthetic */ RecPostViewHolder b;

            public /* synthetic */ void onResponse(Object obj, Object obj2) {
                a((JSONObject) obj, obj2);
            }

            public void a(JSONObject jSONObject, Object obj) {
                g.a("将减少类似内容推荐");
                if (jSONObject != null && 1 == jSONObject.optInt("block_topic")) {
                    this.b.a(j, this.b.f.topic.topicName);
                }
                this.b.j();
            }
        }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
            final /* synthetic */ RecPostViewHolder a;

            {
                this.a = r1;
            }

            public void onErrorResponse(XCError xCError, Object obj) {
                g.a(xCError.getMessage());
            }
        }).execute();
    }

    private void a(final long j, String str) {
        StringBuilder stringBuilder = new StringBuilder("小右看你总是删除");
        stringBuilder.append(str);
        stringBuilder.append("的帖子，需不需要屏蔽该话题呀？");
        cn.xiaochuankeji.tieba.ui.widget.f a = cn.xiaochuankeji.tieba.ui.widget.f.a("提示", stringBuilder.toString(), (Activity) this.d, new cn.xiaochuankeji.tieba.ui.widget.f.a(this) {
            final /* synthetic */ RecPostViewHolder b;

            public void a(boolean z) {
                if (z) {
                    this.b.a(j);
                }
            }
        });
        a.setConfirmTip("屏蔽它");
        a.b();
    }
}
