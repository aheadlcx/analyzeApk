package cn.xiaochuankeji.tieba.ui.comment;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.beans.Member;
import cn.xiaochuankeji.tieba.background.data.Comment;
import cn.xiaochuankeji.tieba.background.data.CommentSound;
import cn.xiaochuankeji.tieba.background.data.post.Post;
import cn.xiaochuankeji.tieba.background.member.MemberCommentInfo;
import cn.xiaochuankeji.tieba.background.net.XCError;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.background.utils.newshare.CommentShareDataModel;
import cn.xiaochuankeji.tieba.background.utils.newshare.ShareDataModel;
import cn.xiaochuankeji.tieba.d.h;
import cn.xiaochuankeji.tieba.ui.CustomReportReasonActivity;
import cn.xiaochuankeji.tieba.ui.base.j;
import cn.xiaochuankeji.tieba.ui.comment.CommentBaseElementLinearLayout.a;
import cn.xiaochuankeji.tieba.ui.comment.CommentBaseElementLinearLayout.b;
import cn.xiaochuankeji.tieba.ui.mediabrowse.EntranceType;
import cn.xiaochuankeji.tieba.ui.member.MemberDetailActivity;
import cn.xiaochuankeji.tieba.ui.post.LikedUsersActivity;
import cn.xiaochuankeji.tieba.ui.post.postdetail.InnerCommentDetailActivity;
import cn.xiaochuankeji.tieba.ui.post.postdetail.PostDetailActivity;
import cn.xiaochuankeji.tieba.ui.widget.SDBottomSheet;
import cn.xiaochuankeji.tieba.ui.widget.SDCheckSheet;
import cn.xiaochuankeji.tieba.ui.widget.customtv.ExpandableTextView.f;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import cn.xiaochuankeji.tieba.ui.widget.updown.CommentItemUpDownView;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.iflytek.aiui.AIUIConstant;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.c;
import org.greenrobot.eventbus.l;

public class d extends j implements OnClickListener, OnLongClickListener, a, b, SDBottomSheet.b, CommentItemUpDownView.a {
    private CommentItemUpDownView a;
    private CommentBaseElementLinearLayout b;
    private TextView c;
    private TextView d;
    private PostOrPgcViewInComment e;
    private Context f;
    private MemberCommentInfo g;
    private cn.xiaochuankeji.tieba.background.member.a h;
    private int i;
    private WebImageView j;
    private ImageView k;
    private ImageView l;
    private boolean m;
    private View n;

    public d(Context context, cn.xiaochuankeji.tieba.background.member.a aVar) {
        super(context);
        this.f = context;
        this.h = aVar;
    }

    protected View a(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.member_comment_item, null);
    }

    protected void a(View view) {
        this.a = (CommentItemUpDownView) view.findViewById(R.id.commentItemUpDownView);
        this.b = (CommentBaseElementLinearLayout) view.findViewById(R.id.baseCommentElementView);
        this.c = (TextView) view.findViewById(R.id.tvTime);
        this.d = (TextView) view.findViewById(R.id.tvWriterName);
        this.e = (PostOrPgcViewInComment) view.findViewById(R.id.postView);
        this.j = (WebImageView) view.findViewById(R.id.ivAvatar);
        this.k = (ImageView) view.findViewById(R.id.selector_btn);
        this.l = (ImageView) view.findViewById(R.id.post_privacy);
        this.n = view.findViewById(R.id.split_line);
        this.a.setRefer("member_detail");
        c(view);
    }

    private void c(View view) {
        c.a().a(this);
        view.setOnLongClickListener(this);
        this.b.setCommonLongClickAction(this);
        view.setOnClickListener(this);
        this.b.setCommonClickAction(this);
    }

    public void a(MemberCommentInfo memberCommentInfo, f fVar, boolean z) {
        this.g = memberCommentInfo;
        this.m = z;
        Comment comment = memberCommentInfo.comment;
        this.c.setText(h.a(comment._createTime * 1000));
        this.d.setText(comment._writerName);
        Member writerMember = comment.getWriterMember();
        this.j.setWebImage(cn.xiaochuankeji.tieba.background.f.b.a(writerMember.getId(), writerMember.getAvatarID()));
        this.a.a(comment.liked, comment._likeCount, this);
        if (z) {
            this.a.setVisibility(8);
            this.l.setVisibility(8);
            this.k.setVisibility(0);
            this.k.setSelected(this.g.isSelected);
            memberCommentInfo.isSelected = this.k.isSelected();
        } else {
            int i;
            this.a.setVisibility(0);
            ImageView imageView = this.l;
            if (comment.isHide()) {
                i = 0;
            } else {
                i = 8;
            }
            imageView.setVisibility(i);
            this.k.setVisibility(8);
            memberCommentInfo.isSelected = false;
        }
        CommentSound commentSound = comment.commentSound;
        String str = null;
        if (comment._sourceID != comment._prid && !TextUtils.isEmpty(comment._sourceWriterName)) {
            str = comment._sourceWriterName;
        } else if (memberCommentInfo.parentComment != null) {
            str = memberCommentInfo.parentComment._writerName;
        }
        this.b.setEditMode(z);
        this.b.a(comment._commentContent, str, comment.getCommentImage(), commentSound, fVar);
        Post post = memberCommentInfo.relativePost;
        if (post != null) {
            this.e.a(post, z);
        }
    }

    public void b(int i) {
        this.n.setVisibility(i);
    }

    private void e() {
        if (!this.g.comment.isInnerComment()) {
            PostDetailActivity.a(this.f, new Post(this.g.relativePost._ID));
        } else if (this.g.parentComment != null) {
            InnerCommentDetailActivity.a((Activity) this.f, this.g.comment._pid, this.g.parentComment._id, this.g.parentComment._status);
        }
    }

    private void f() {
        Object obj = null;
        SDBottomSheet sDBottomSheet = new SDBottomSheet((Activity) this.f, this);
        ArrayList arrayList = new ArrayList();
        Comment comment = this.g.comment;
        Object obj2 = (comment._commentContent == null || comment._commentContent.trim().length() <= 0) ? null : 1;
        if (comment._writerID == cn.xiaochuankeji.tieba.background.a.g().c()) {
            obj = 1;
        }
        if (obj2 != null) {
            arrayList.add(new SDBottomSheet.c(R.drawable.icon_option_copy, "复制文字", 6));
        }
        if (obj != null) {
            int d;
            arrayList.add(new SDBottomSheet.c(R.drawable.icon_option_delete, "删除", 9));
            if (comment.hide == 1) {
                d = c.a.d.a.a.a().d(R.drawable.toast_privacy);
            } else {
                d = c.a.d.a.a.a().d(R.drawable.toast_privacy_on);
            }
            arrayList.add(new SDBottomSheet.c(d, comment.hide == 1 ? "个人页可见" : "个人页隐藏", 16));
        } else {
            arrayList.add(new SDBottomSheet.c(R.drawable.icon_option_report, "举报", 12));
        }
        if (comment.isInnerComment()) {
            sDBottomSheet.a(arrayList, null);
        } else {
            sDBottomSheet.a(sDBottomSheet.c(), arrayList);
        }
        sDBottomSheet.b();
    }

    public void a(int i) {
        int i2 = 1;
        final Comment comment = this.g.comment;
        if (i == 4 || i == 2 || i == 3 || i == 5 || i == 1) {
            a(i, comment, this.g.relativePost);
            return;
        }
        switch (i) {
            case 6:
                cn.xiaochuankeji.tieba.ui.utils.d.a(comment._commentContent);
                g.a("已复制");
                return;
            case 9:
                String str;
                if (comment.isInnerComment()) {
                    str = "删除后不可恢复，确定删除？";
                } else {
                    str = "删除评论后,下面的回复也会被删除,确定删除？";
                }
                cn.xiaochuankeji.tieba.ui.widget.f.a("提示", str, (Activity) this.f, new cn.xiaochuankeji.tieba.ui.widget.f.a(this) {
                    final /* synthetic */ d b;

                    public void a(boolean z) {
                        if (z) {
                            cn.xiaochuankeji.tieba.background.a.k().a(this.b.g.relativePost._ID, comment._id, new cn.xiaochuankeji.tieba.background.review.b.b(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = r1;
                                }

                                public void a(boolean z, long j, String str) {
                                    if (z) {
                                        this.a.b.h.a(comment._id);
                                    } else {
                                        g.a(str);
                                    }
                                }
                            });
                        }
                    }
                });
                return;
            case 12:
                g();
                return;
            case 16:
                JSONArray jSONArray = new JSONArray();
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("pid", Long.valueOf(this.g.relativePost._ID));
                jSONObject.put("rid", Long.valueOf(comment._id));
                String str2 = "hide";
                if (comment.hide == 1) {
                    i2 = 0;
                }
                jSONObject.put(str2, Integer.valueOf(i2));
                jSONArray.add(jSONObject);
                new cn.xiaochuankeji.tieba.api.post.c().a(jSONArray).a(rx.a.b.a.a()).b(new rx.j<Void>(this) {
                    final /* synthetic */ d b;

                    public /* synthetic */ void onNext(Object obj) {
                        a((Void) obj);
                    }

                    public void onCompleted() {
                    }

                    public void onError(Throwable th) {
                    }

                    public void a(Void voidR) {
                        int i = 1;
                        int i2 = 0;
                        if (comment != null) {
                            Comment comment = comment;
                            if (comment.hide == 1) {
                                i = 0;
                            }
                            comment.hide = i;
                            ImageView c = this.b.l;
                            if (!comment.isHide()) {
                                i2 = 8;
                            }
                            c.setVisibility(i2);
                        }
                    }
                });
                return;
            case 18:
                comment.copyLink();
                return;
            default:
                return;
        }
    }

    private void a(final int i, final Comment comment, Post post) {
        final ShareDataModel commentShareDataModel = new CommentShareDataModel(comment, post, i);
        commentShareDataModel.prepareData(new ShareDataModel.a(this) {
            final /* synthetic */ d d;

            public void a() {
                cn.xiaochuankeji.tieba.background.utils.share.b.a().a((Activity) this.d.e_(), commentShareDataModel);
                cn.xiaochuankeji.tieba.background.i.a.a(comment._pid, comment._id, "other", (String) cn.xiaochuankeji.tieba.d.d.b.get(Integer.valueOf(i)), commentShareDataModel.getABTestId());
            }
        });
    }

    private void g() {
        LinkedHashMap o = cn.xiaochuankeji.tieba.background.utils.c.a.c().o();
        if (o.size() == 0) {
            c(0);
            return;
        }
        SDCheckSheet sDCheckSheet = new SDCheckSheet((Activity) this.f, new SDCheckSheet.a(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void a(int i) {
                long j = this.a.g.relativePost._ID;
                long j2 = this.a.g.comment._id;
                if (i == -123) {
                    CustomReportReasonActivity.a(this.a.f, j, j2, this.a.i, "review");
                } else {
                    this.a.c(i);
                }
            }
        });
        int i = 0;
        for (Entry entry : o.entrySet()) {
            int i2;
            String str = (String) entry.getKey();
            String str2 = (String) entry.getValue();
            int parseInt = Integer.parseInt(str);
            int i3 = i + 1;
            String trim = str2.trim();
            if (trim.equals("其他")) {
                this.i = parseInt;
                i2 = -123;
            } else {
                i2 = parseInt;
            }
            if (i3 == o.size()) {
                sDCheckSheet.a(trim, i2, true);
            } else {
                sDCheckSheet.a(trim, i2, false);
            }
            i = i3;
        }
        sDCheckSheet.b();
    }

    private void c(int i) {
        new cn.xiaochuankeji.tieba.background.b.b(this.g.relativePost._ID, this.g.comment._id, "review", i, null, new cn.xiaochuankeji.tieba.background.net.a.b<org.json.JSONObject>(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onResponse(Object obj, Object obj2) {
                a((org.json.JSONObject) obj, obj2);
            }

            public void a(org.json.JSONObject jSONObject, Object obj) {
                g.a("举报成功");
            }
        }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void onErrorResponse(XCError xCError, Object obj) {
                g.a(xCError.getMessage());
            }
        }).execute();
    }

    public void c() {
        this.b.b();
        if (c.a().b(this)) {
            c.a().c(this);
        }
    }

    public boolean onLongClick(View view) {
        if (this.m) {
            h();
        } else {
            f();
        }
        return true;
    }

    public void a() {
        if (this.m) {
            h();
        } else {
            e();
        }
    }

    public void a(View view, int i) {
        if (this.m) {
            h();
            return;
        }
        Post post = this.g.relativePost;
        Comment comment = this.g.comment;
        long j = 0;
        if (this.g.parentComment != null) {
            j = this.g.parentComment._prid;
        }
        cn.xiaochuankeji.tieba.ui.utils.c.a(this.f, post, comment, comment._id, j, comment.getCommentImage(), comment.getImgVideos(), i, EntranceType.CommentImage);
    }

    public void b() {
        if (this.m) {
            h();
            return;
        }
        long j = this.g.comment.sourceMid;
        if (0 != j) {
            MemberDetailActivity.a(this.f, j, this.g.comment._id, 2, this.g.comment._pid);
        }
    }

    public void d() {
        if (this.m) {
            h();
        }
    }

    private void h() {
        this.k.setSelected(!this.k.isSelected());
        this.g.isSelected = this.k.isSelected();
    }

    public void b(View view) {
        if (this.m) {
            h();
        } else {
            f();
        }
    }

    public void onClick(View view) {
        if (this.m) {
            h();
        } else {
            e();
        }
    }

    public void a(boolean z) {
        if (this.m) {
            h();
            return;
        }
        boolean z2 = z;
        LikedUsersActivity.a(this.f, this.g.relativePost._ID, this.g.comment._id, z2, 2, AIUIConstant.USER, this.g.comment._status);
    }

    public void a(int i, int i2, boolean z) {
        this.g.comment.liked = i;
        this.g.comment._likeCount = i2;
        if (z) {
            String str = AIUIConstant.USER;
            if (1 == i) {
                cn.xiaochuankeji.tieba.background.review.g.a().a(this.g.relativePost._ID, this.g.comment._id, str, this.g.comment._status, new cn.xiaochuankeji.tieba.background.review.g.a(this) {
                    final /* synthetic */ d a;

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
                cn.xiaochuankeji.tieba.background.review.g.a().c(this.g.relativePost._ID, this.g.comment._id, str, this.g.comment._status, new cn.xiaochuankeji.tieba.background.review.g.a(this) {
                    final /* synthetic */ d a;

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
    }

    @l(a = ThreadMode.MAIN)
    public void cancelLikeState(cn.xiaochuankeji.tieba.background.d.b bVar) {
        if (bVar.a() == this.g.comment._id) {
            this.a.a();
        }
    }
}
