package cn.xiaochuankeji.tieba.ui.comment;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import cn.htjyb.ui.a;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.data.Comment;
import cn.xiaochuankeji.tieba.background.data.post.Post;
import cn.xiaochuankeji.tieba.background.member.d;
import cn.xiaochuankeji.tieba.background.post.r;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.background.utils.h;
import cn.xiaochuankeji.tieba.background.utils.newshare.CommentShareDataModel;
import cn.xiaochuankeji.tieba.background.utils.newshare.ShareDataModel;
import cn.xiaochuankeji.tieba.ui.base.j;
import cn.xiaochuankeji.tieba.ui.homepage.HomePageActivity;
import cn.xiaochuankeji.tieba.ui.mediabrowse.EntranceType;
import cn.xiaochuankeji.tieba.ui.member.MemberDetailActivity;
import cn.xiaochuankeji.tieba.ui.post.LikedUsersActivity;
import cn.xiaochuankeji.tieba.ui.post.postdetail.InnerCommentDetailActivity;
import cn.xiaochuankeji.tieba.ui.post.postdetail.PostDetailActivity;
import cn.xiaochuankeji.tieba.ui.topic.TopicDetailActivity;
import cn.xiaochuankeji.tieba.ui.widget.SDBottomSheet;
import cn.xiaochuankeji.tieba.ui.widget.SDBottomSheet.c;
import cn.xiaochuankeji.tieba.ui.widget.customtv.ExpandableTextView.f;
import cn.xiaochuankeji.tieba.ui.widget.updown.CommentItemUpDownView;
import com.iflytek.aiui.AIUIConstant;
import java.util.ArrayList;
import java.util.HashMap;

public class b extends j implements OnClickListener, OnLongClickListener, a, CommentBaseElementLinearLayout.a, cn.xiaochuankeji.tieba.ui.comment.CommentBaseElementLinearLayout.b, CommentItemUpDownView.a {
    private static HashMap<Long, Boolean> j = new HashMap();
    private Context a;
    private Comment b;
    private Post c;
    private d d;
    private LinearLayout e;
    private CommentBaseElementLinearLayout f;
    private CommentItemUpDownView g;
    private ImageView h;
    private ImageView i;
    private EntranceType k = EntranceType.CommentImage;

    public b(Context context) {
        super(context);
        this.a = context;
        this.d = d.a();
    }

    protected View a(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.view_item_godreview, null);
    }

    protected void a(View view) {
        view.setClickable(true);
        this.f = (CommentBaseElementLinearLayout) view.findViewById(R.id.baseCommentElementView);
        this.e = (LinearLayout) view.findViewById(R.id.llBaseContainer);
        this.g = (CommentItemUpDownView) view.findViewById(R.id.viewCommentUpDown);
        this.h = (ImageView) view.findViewById(R.id.bg_godview_action);
        this.i = (ImageView) view.findViewById(R.id.godreview_flag);
        this.g.setRefer("god_review");
        c(view);
    }

    private void c(View view) {
        this.f.setCommonLongClickAction(this);
        view.setOnLongClickListener(this);
        this.f.setCommonClickAction(this);
        view.setOnClickListener(this);
    }

    public void a(Comment comment, Post post, f fVar) {
        this.b = comment;
        this.c = post;
        this.f.a(comment._commentContent, null, comment.getCommentImage(), this.b.commentSound, fVar);
        this.g.a(this.b.liked, this.b._likeCount, this);
        this.i.setVisibility(comment.isGod() ? 0 : 4);
    }

    private void a(String str) {
        cn.xiaochuankeji.tieba.ui.utils.d.a(str);
        g.a("已复制");
    }

    private void f() {
        this.d.a(this.c);
        PostDetailActivity.a(this.a, this.c, 1, null, "review");
        if (!(this.a instanceof HomePageActivity) || !HomePageActivity.e()) {
            return;
        }
        if (this.b == null || this.b.isGod()) {
            h.a(this.a, "zy_event_homepage_tab_recommend", "神评点击次数");
        } else {
            h.a(this.a, "zy_event_god_or_fine_Comments", "精彩评论点击进入详情页");
        }
    }

    private void g() {
        SDBottomSheet sDBottomSheet = new SDBottomSheet((Activity) this.a, new cn.xiaochuankeji.tieba.ui.widget.SDBottomSheet.b(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void a(int i) {
                if (i == 4 || i == 2 || i == 3 || i == 5 || i == 1) {
                    this.a.a(i);
                    this.a.h();
                } else if (i == 6) {
                    this.a.a(this.a.b._commentContent);
                } else if (i == 18) {
                    this.a.b.copyLink();
                }
            }
        });
        ArrayList arrayList = new ArrayList();
        if (this.b._commentContent.length() > 0) {
            arrayList.add(new c(R.drawable.icon_option_copy, "复制文字", 6));
        }
        sDBottomSheet.a(sDBottomSheet.c(), arrayList);
        sDBottomSheet.b();
        this.d.a(this.c);
    }

    private void h() {
        if ((this.a instanceof HomePageActivity) && HomePageActivity.e()) {
            h.a(this.a, "zy_event_homepage_tab_recommend", "神评分享");
        }
    }

    private void a(final int i) {
        final ShareDataModel commentShareDataModel = new CommentShareDataModel(this.b, this.c, i);
        commentShareDataModel.prepareData(new ShareDataModel.a(this) {
            final /* synthetic */ b c;

            public void a() {
                cn.xiaochuankeji.tieba.background.utils.share.b.a().a((Activity) this.c.e_(), commentShareDataModel);
                String str = "other";
                if (this.c.a instanceof PostDetailActivity) {
                    str = "postdetail";
                } else if (this.c.a instanceof HomePageActivity) {
                    str = "index";
                } else if (this.c.a instanceof InnerCommentDetailActivity) {
                    str = "commentdetail";
                }
                cn.xiaochuankeji.tieba.background.i.a.a(this.c.b._pid, this.c.b._id, str, (String) cn.xiaochuankeji.tieba.d.d.b.get(Integer.valueOf(i)), commentShareDataModel.getABTestId());
            }
        });
    }

    public void a() {
        f();
    }

    public void a(View view, int i) {
        cn.xiaochuankeji.tieba.ui.utils.c.a(this.a, this.c, this.b, this.b._id, 0, this.b.getCommentImage(), this.b.getImgVideos(), i, this.k);
        this.d.a(this.c);
    }

    public void b() {
    }

    public void d() {
        this.d.a(this.c);
    }

    public void b(View view) {
        g();
    }

    public void onClick(View view) {
        if (view.getId() == R.id.tvName) {
            MemberDetailActivity.a(e_(), this.b._writerID, this.b._id, 2, this.b._pid);
            return;
        }
        r.a = "review";
        f();
    }

    public boolean onLongClick(View view) {
        g();
        return true;
    }

    public void c() {
        this.f.b();
    }

    public void a(boolean z) {
        boolean z2 = z;
        LikedUsersActivity.a(this.a, this.c._ID, this.b._id, z2, 2, e(), this.b._status);
    }

    public void a(int i, int i2, boolean z) {
        this.b.liked = i;
        this.b._likeCount = i2;
        String e;
        if (this.b.isGod()) {
            if (z) {
                e = e();
                if (1 == i) {
                    cn.xiaochuankeji.tieba.background.review.g.a().a(this.c._ID, this.b._id, e, this.b._status, new cn.xiaochuankeji.tieba.background.review.g.a(this) {
                        final /* synthetic */ b a;

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
                    cn.xiaochuankeji.tieba.background.review.g.a().c(this.c._ID, this.b._id, e, this.b._status, new cn.xiaochuankeji.tieba.background.review.g.a(this) {
                        final /* synthetic */ b a;

                        {
                            this.a = r1;
                        }

                        public void a(boolean z, String str) {
                            if (!z) {
                                g.a(str);
                            }
                        }
                    });
                }
            }
            this.d.a(this.c);
            if (1 != i) {
                if ((this.a instanceof HomePageActivity) && HomePageActivity.e()) {
                    h.a(this.a, "zy_event_homepage_tab_recommend", "神评顶");
                    return;
                }
            } else if (-1 == i && (this.a instanceof HomePageActivity) && HomePageActivity.e()) {
                h.a(this.a, "zy_event_homepage_tab_recommend", "神评踩");
                return;
            } else {
                return;
            }
        }
        if (z) {
            e = e();
            if (1 == i) {
                cn.xiaochuankeji.tieba.background.review.g.a().a(this.c._ID, this.b._id, e, this.b._status, /* anonymous class already generated */);
            } else if (-1 == i) {
                cn.xiaochuankeji.tieba.background.review.g.a().c(this.c._ID, this.b._id, e, this.b._status, /* anonymous class already generated */);
            }
        }
        this.d.a(this.c);
        if (1 != i) {
            if (-1 == i) {
            }
        } else if (this.a instanceof HomePageActivity) {
        }
    }

    public String e() {
        String str = "other";
        if (e_() instanceof HomePageActivity) {
            return "index";
        }
        if (this.a instanceof TopicDetailActivity) {
            return "topicdetail";
        }
        if (this.a instanceof MemberDetailActivity) {
            return AIUIConstant.USER;
        }
        return str;
    }

    public void a(cn.xiaochuankeji.tieba.background.d.b bVar) {
        if (bVar != null && bVar.a() == this.b._id) {
            this.g.a();
        }
    }

    public void a(EntranceType entranceType) {
        this.k = entranceType;
    }
}
