package cn.xiaochuankeji.tieba.ui.topic.weight;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.a.d;
import cn.xiaochuankeji.tieba.background.data.Comment;
import cn.xiaochuankeji.tieba.background.data.ServerImage;
import cn.xiaochuankeji.tieba.background.data.ServerVideo;
import cn.xiaochuankeji.tieba.background.data.post.Post;
import cn.xiaochuankeji.tieba.background.review.g;
import cn.xiaochuankeji.tieba.background.utils.newshare.CommentShareDataModel;
import cn.xiaochuankeji.tieba.background.utils.newshare.ShareDataModel;
import cn.xiaochuankeji.tieba.json.recommend.CommentBean;
import cn.xiaochuankeji.tieba.json.recommend.CommentBean.CommentAudio;
import cn.xiaochuankeji.tieba.json.recommend.ServerImageBean;
import cn.xiaochuankeji.tieba.json.recommend.ServerVideoBean;
import cn.xiaochuankeji.tieba.ui.comment.CommentBaseElementLinearLayout;
import cn.xiaochuankeji.tieba.ui.mediabrowse.EntranceType;
import cn.xiaochuankeji.tieba.ui.member.MemberDetailActivity;
import cn.xiaochuankeji.tieba.ui.post.LikedUsersActivity;
import cn.xiaochuankeji.tieba.ui.post.postdetail.PostDetailActivity;
import cn.xiaochuankeji.tieba.ui.tempview.DealChildLongClickRelativeLayout;
import cn.xiaochuankeji.tieba.ui.topic.data.PostDataBean;
import cn.xiaochuankeji.tieba.ui.utils.c;
import cn.xiaochuankeji.tieba.ui.utils.e;
import cn.xiaochuankeji.tieba.ui.widget.SDBottomSheet;
import cn.xiaochuankeji.tieba.ui.widget.customtv.ExpandableTextView.f;
import cn.xiaochuankeji.tieba.ui.widget.updown.CommentItemUpDownView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.l;

public class b extends DealChildLongClickRelativeLayout {
    private static final int a = e.a(44.0f);
    private CommentItemUpDownView b;
    private CommentBean c;
    private PostDataBean d;
    private int e;

    interface a {
        void a(int i);
    }

    public b(Context context) {
        super(context);
        b();
    }

    private void b() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_god_element_view, this);
        this.e = 0;
    }

    public void a(final a aVar) {
        if (this.e == 0) {
            post(new Runnable(this) {
                final /* synthetic */ b b;

                public void run() {
                    this.b.e = b.a + this.b.findViewById(R.id.god_element_content).getHeight();
                    aVar.a(this.b.e);
                }
            });
        } else {
            aVar.a(this.e);
        }
    }

    public void a(CommentBean commentBean, PostDataBean postDataBean) {
        if (commentBean != null) {
            this.d = postDataBean;
            this.c = commentBean;
            c();
            d();
            e();
            setOnLongClickListener(new OnLongClickListener(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public boolean onLongClick(View view) {
                    this.a.f();
                    return true;
                }
            });
            setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.g();
                }
            });
        }
    }

    private void c() {
        CommentBaseElementLinearLayout commentBaseElementLinearLayout = (CommentBaseElementLinearLayout) findViewById(R.id.god_element_content);
        commentBaseElementLinearLayout.a(this.c.reviewContent, null, a(this.c.serverImages), CommentAudio.getCommentSoundFromCommentAudio(this.c.audio), new f());
        commentBaseElementLinearLayout.setMaxLines(8);
        commentBaseElementLinearLayout.setCommonClickAction(new cn.xiaochuankeji.tieba.ui.comment.CommentBaseElementLinearLayout.a(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.g();
            }

            public void a(View view, int i) {
                long j = 0;
                Context context = this.a.getContext();
                Post postFromPostDataBean = PostDataBean.getPostFromPostDataBean(this.a.d);
                Comment commentFromBean = CommentBean.getCommentFromBean(this.a.c);
                long j2 = this.a.c.commentId;
                if (this.a.c.parentCommentId > 0) {
                    j = this.a.c.parentCommentId;
                }
                c.a(context, postFromPostDataBean, commentFromBean, j2, j, this.a.a(this.a.c.serverImages), this.a.a(this.a.c.commentVideos), i, EntranceType.CommentImage);
                d.a(this.a.d);
            }

            public void b() {
                if (this.a.c.sourceId != 0) {
                    MemberDetailActivity.a(this.a.getContext(), this.a.c.sourceId, this.a.c.commentId, 2, this.a.c.postId);
                    d.a(this.a.d);
                }
            }

            public void d() {
            }
        });
        commentBaseElementLinearLayout.setCommonLongClickAction(new cn.xiaochuankeji.tieba.ui.comment.CommentBaseElementLinearLayout.b(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void b(View view) {
                this.a.f();
            }
        });
    }

    private ArrayList<ServerImage> a(List<ServerImageBean> list) {
        if (list == null) {
            return null;
        }
        ArrayList<ServerImage> arrayList = new ArrayList(list.size());
        for (ServerImageBean serverImageFromServerImageBean : list) {
            arrayList.add(ServerImageBean.getServerImageFromServerImageBean(serverImageFromServerImageBean));
        }
        return arrayList;
    }

    private HashMap<Long, ServerVideo> a(Map<String, ServerVideoBean> map) {
        if (map == null) {
            return null;
        }
        HashMap<Long, ServerVideo> hashMap = new HashMap();
        for (Entry entry : map.entrySet()) {
            hashMap.put(Long.valueOf((String) entry.getKey()), ServerVideoBean.getServerVideoFromBean((ServerVideoBean) entry.getValue()));
        }
        return hashMap;
    }

    private void d() {
        this.b = (CommentItemUpDownView) findViewById(R.id.god_element_ud_view);
        this.b.setRefer("god_review");
        this.b.a(this.c.liked, this.c.likeCount, new cn.xiaochuankeji.tieba.ui.widget.updown.CommentItemUpDownView.a(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void a(boolean z) {
                LikedUsersActivity.a(this.a.getContext(), this.a.d.postId, this.a.c.commentId, z, 2, "topicdetail", this.a.d.status);
                d.a(this.a.d);
            }

            public void a(int i, int i2, boolean z) {
                this.a.c.likeCount = i2;
                this.a.c.liked = i;
                if (!z) {
                    return;
                }
                if (1 == i) {
                    g.a().a(this.a.d.postId, this.a.c.mid, "topicdetail", 0, new cn.xiaochuankeji.tieba.background.review.g.a(this) {
                        final /* synthetic */ AnonymousClass6 a;

                        {
                            this.a = r1;
                        }

                        public void a(boolean z, String str) {
                            if (!z) {
                                cn.xiaochuankeji.tieba.background.utils.g.a(str);
                            }
                        }
                    });
                } else if (-1 == i) {
                    g.a().c(this.a.d.postId, this.a.c.mid, "topicdetail", 0, new cn.xiaochuankeji.tieba.background.review.g.a(this) {
                        final /* synthetic */ AnonymousClass6 a;

                        {
                            this.a = r1;
                        }

                        public void a(boolean z, String str) {
                            if (!z) {
                                cn.xiaochuankeji.tieba.background.utils.g.a(str);
                            }
                        }
                    });
                }
            }
        });
    }

    private void e() {
        findViewById(R.id.god_element_flag).setVisibility(this.c.isGod == 1 ? 0 : 8);
        setOnLongClickListener(new OnLongClickListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public boolean onLongClick(View view) {
                this.a.f();
                return true;
            }
        });
    }

    private void f() {
        SDBottomSheet sDBottomSheet = new SDBottomSheet((Activity) getContext(), new cn.xiaochuankeji.tieba.ui.widget.SDBottomSheet.b(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            @SuppressLint({"SwitchIntDef"})
            public void a(int i) {
                switch (i) {
                    case 1:
                    case 2:
                    case 3:
                    case 5:
                        this.a.a(CommentBean.getCommentFromBean(this.a.c), i);
                        return;
                    case 6:
                        cn.xiaochuankeji.tieba.ui.utils.d.a(this.a.c.reviewContent);
                        cn.xiaochuankeji.tieba.background.utils.g.a("已复制");
                        return;
                    default:
                        return;
                }
            }
        });
        if (TextUtils.isEmpty(this.c.reviewContent)) {
            sDBottomSheet.a(sDBottomSheet.c(), null);
        } else {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SDBottomSheet.c(R.drawable.icon_option_copy, "复制文字", 6));
            sDBottomSheet.a(sDBottomSheet.c(), arrayList);
        }
        sDBottomSheet.b();
        d.a(this.d);
    }

    private void a(Comment comment, final int i) {
        final ShareDataModel commentShareDataModel = new CommentShareDataModel(comment, PostDataBean.getPostFromPostDataBean(this.d), i);
        commentShareDataModel.prepareData(new cn.xiaochuankeji.tieba.background.utils.newshare.ShareDataModel.a(this) {
            final /* synthetic */ b c;

            public void a() {
                cn.xiaochuankeji.tieba.background.utils.share.b.a().a((Activity) this.c.getContext(), commentShareDataModel);
                cn.xiaochuankeji.tieba.background.i.a.a(this.c.c.postId, this.c.c.commentId, "other", (String) cn.xiaochuankeji.tieba.d.d.b.get(Integer.valueOf(i)), commentShareDataModel.getABTestId());
            }
        });
    }

    private void g() {
        PostDetailActivity.a(getContext(), PostDataBean.getPostFromPostDataBean(this.d), true, this.d.member.getTopicRole(), "review", EntranceType.PostDetail);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        org.greenrobot.eventbus.c.a().a(this);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        org.greenrobot.eventbus.c.a().c(this);
    }

    @l(a = ThreadMode.MAIN)
    public void cancelLikeState(cn.xiaochuankeji.tieba.background.d.b bVar) {
        if (bVar.a() == this.c.commentId) {
            this.b.a();
        }
    }
}
