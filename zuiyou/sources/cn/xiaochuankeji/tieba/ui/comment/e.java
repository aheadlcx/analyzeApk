package cn.xiaochuankeji.tieba.ui.comment;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.data.Comment;
import cn.xiaochuankeji.tieba.background.data.CommentSound;
import cn.xiaochuankeji.tieba.background.data.post.InnerComment;
import cn.xiaochuankeji.tieba.background.data.post.Post;
import cn.xiaochuankeji.tieba.background.post.r;
import cn.xiaochuankeji.tieba.background.review.g;
import cn.xiaochuankeji.tieba.ui.base.j;
import cn.xiaochuankeji.tieba.ui.comment.CommentBaseElementLinearLayout.b;
import cn.xiaochuankeji.tieba.ui.mediabrowse.EntranceType;
import cn.xiaochuankeji.tieba.ui.member.MemberDetailActivity;
import cn.xiaochuankeji.tieba.ui.post.LikedUsersActivity;
import cn.xiaochuankeji.tieba.ui.post.postdetail.InnerCommentDetailActivity;
import cn.xiaochuankeji.tieba.ui.widget.customtv.ExpandableTextView.f;
import cn.xiaochuankeji.tieba.ui.widget.updown.CommentItemUpDownView;
import java.util.ArrayList;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.c;
import org.greenrobot.eventbus.l;

public class e extends j implements OnClickListener, OnLongClickListener, cn.xiaochuankeji.tieba.ui.comment.CommentBaseElementLinearLayout.a, b, cn.xiaochuankeji.tieba.ui.widget.updown.CommentItemUpDownView.a {
    private Context a;
    private Post b;
    private Comment c;
    private int d;
    private boolean e = false;
    private cn.xiaochuankeji.tieba.ui.post.postdetail.d.a f;
    private CommentItemUpDownView g;
    private CommentTopMemberView h;
    private ImageView i;
    private CommentBaseElementLinearLayout j;
    private LinearLayout k;
    private TextView l;
    private TextView m;
    private TextView n;
    private View o;

    class a extends ClickableSpan {
        final /* synthetic */ e a;
        private long b;

        public a(e eVar, long j) {
            this.a = eVar;
            this.b = j;
        }

        public void updateDrawState(TextPaint textPaint) {
            super.updateDrawState(textPaint);
            textPaint.setColor(c.a.d.a.a.a().a((int) R.color.CM));
            textPaint.setUnderlineText(false);
        }

        public void onClick(View view) {
            MemberDetailActivity.a(this.a.e_(), this.b, this.a.c._id, 2, this.a.c._pid);
        }
    }

    public e(Context context) {
        super(context);
        this.a = context;
    }

    protected View a(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.view_item_post_detail_comment, null);
    }

    protected void a(View view) {
        this.g = (CommentItemUpDownView) view.findViewById(R.id.commentItemUpDownView);
        this.h = (CommentTopMemberView) view.findViewById(R.id.topMemberView);
        this.i = (ImageView) view.findViewById(R.id.ivGodFlag);
        this.j = (CommentBaseElementLinearLayout) view.findViewById(R.id.baseCommentElementView);
        this.k = (LinearLayout) view.findViewById(R.id.llAreaSecondComment);
        this.l = (TextView) view.findViewById(R.id.tvSecondCommentOne);
        this.m = (TextView) view.findViewById(R.id.tvSecondCommentTwo);
        this.n = (TextView) view.findViewById(R.id.tvTips);
        this.o = view.findViewById(R.id.view_divider);
        this.g.setRefer("post_detail");
        c(view);
    }

    public void b(boolean z) {
        this.o.setVisibility(z ? 0 : 8);
    }

    private void c(View view) {
        c.a().a(this);
        view.setOnClickListener(this);
        this.j.setCommonClickAction(this);
        view.setOnLongClickListener(this);
        this.j.setCommonLongClickAction(this);
        this.k.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                InnerCommentDetailActivity.a((Activity) this.a.a, this.a.b, this.a.c._id, this.a.c._status);
            }
        });
        this.l.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                InnerCommentDetailActivity.a((Activity) this.a.a, this.a.b, this.a.c._id, this.a.c._status);
            }
        });
        this.m.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                InnerCommentDetailActivity.a((Activity) this.a.a, this.a.b, this.a.c._id, this.a.c._status);
            }
        });
    }

    public void a(Comment comment, Post post, int i, f fVar, boolean z) {
        int d;
        this.c = comment;
        this.b = post;
        this.d = i;
        this.g.a(this.c.liked, this.c._likeCount, this);
        a aVar = new a();
        aVar.a(this.c._writerAvatarID);
        aVar.b = this.c._gender;
        aVar.a = this.c._writerID;
        aVar.d = this.c._id;
        aVar.e = this.c._pid;
        aVar.c = this.c._writerName.replace("\n", "");
        this.h.a(aVar, this.c._createTime, comment._writerID == post._member.getId());
        CommentSound commentSound = this.c.commentSound;
        String str = null;
        if (!(this.c._sourceID == this.c._prid || TextUtils.isEmpty(this.c._sourceWriterName))) {
            str = this.c._sourceWriterName;
        }
        if (this.b._topic._skin == 1) {
            this.j.setMaxLines(8);
            this.j.a(this.c._commentContent, str, this.c.getCommentImage(), commentSound, fVar);
            this.e = z;
        } else {
            this.j.setMaxLines(8);
            this.j.a(this.c._commentContent, str, this.c.getCommentImage(), commentSound, fVar);
            this.e = z;
        }
        if (this.c.isGod()) {
            this.i.setVisibility(0);
        } else {
            this.i.setVisibility(8);
        }
        Object obj = ((this.a instanceof InnerCommentDetailActivity) && this.c.isInnerComment()) ? 1 : null;
        View f_ = f_();
        if (obj != null) {
            d = c.a.d.a.a.a().d(R.drawable.bg_post_detail_comment);
        } else {
            d = c.a.d.a.a.a().d(R.color.CB);
        }
        f_.setBackgroundResource(d);
        e();
        if (!c.a().b(this)) {
            c.a().a(this);
        }
    }

    private void e() {
        int secondCommentCount = this.c.getSecondCommentCount();
        ArrayList secondCommentList = this.c.getSecondCommentList();
        if (this.a instanceof InnerCommentDetailActivity) {
            this.k.setVisibility(8);
            return;
        }
        int i;
        int i2 = secondCommentCount > 0 ? 1 : 0;
        if (secondCommentList.size() > 0) {
            i = 1;
        } else {
            i = 0;
        }
        if (i2 == 0 && i == 0) {
            this.k.setVisibility(8);
            return;
        }
        this.k.setVisibility(0);
        if (i2 != 0) {
            this.n.setText("查看" + secondCommentCount + "条评论");
            this.n.setVisibility(0);
        } else {
            this.n.setVisibility(8);
        }
        if (i != 0) {
            this.l.setVisibility(0);
            a(this.l, (InnerComment) secondCommentList.get(0));
            if (secondCommentList.size() > 1) {
                this.m.setVisibility(0);
                a(this.m, (InnerComment) secondCommentList.get(1));
                return;
            }
            this.m.setVisibility(8);
            return;
        }
        this.l.setVisibility(8);
        this.m.setVisibility(8);
    }

    private void a(TextView textView, InnerComment innerComment) {
        CharSequence charSequence;
        String replace = innerComment.getName().replace("\n", "");
        Object replace2 = innerComment.getSourceName().replace("\n", "");
        String content = innerComment.getContent();
        if (innerComment.getSid() == this.c._id || TextUtils.isEmpty(replace2)) {
            charSequence = replace + ": " + content;
        } else {
            charSequence = replace + " 回复 " + replace2 + "：" + content;
        }
        CharSequence spannableString = new SpannableString(charSequence);
        int length = replace.length();
        if (length > spannableString.length()) {
            length = spannableString.length();
        }
        spannableString.setSpan(new a(this, innerComment.getMid()), 0, length, 33);
        textView.setText(spannableString);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void a(cn.xiaochuankeji.tieba.ui.post.postdetail.d.a aVar) {
        this.f = aVar;
    }

    public void a(boolean z) {
        LikedUsersActivity.a(this.a, this.b._ID, this.c._id, z, 2, f(), this.c._status);
    }

    public void a(int i, int i2, boolean z) {
        this.c.liked = i;
        this.c._likeCount = i2;
        String f;
        if (this.c.isGod()) {
            if (z) {
                f = f();
                if (1 == i) {
                    g.a().a(this.b._ID, this.c._id, f, this.c._status, new cn.xiaochuankeji.tieba.background.review.g.a(this) {
                        final /* synthetic */ e a;

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
                    g.a().c(this.b._ID, this.c._id, f, this.c._status, new cn.xiaochuankeji.tieba.background.review.g.a(this) {
                        final /* synthetic */ e a;

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
        } else if (z) {
            f = f();
            if (1 == i) {
                g.a().a(this.b._ID, this.c._id, f, this.c._status, /* anonymous class already generated */);
            } else if (-1 == i) {
                g.a().c(this.b._ID, this.c._id, f, this.c._status, /* anonymous class already generated */);
            }
        }
    }

    @NonNull
    private String f() {
        if (e_() instanceof InnerCommentDetailActivity) {
            return "review";
        }
        return this.e ? "postdetailhot" : "postdetailnew";
    }

    @l(a = ThreadMode.MAIN)
    public void cancelLikeState(cn.xiaochuankeji.tieba.background.d.b bVar) {
        if (bVar.a() == this.c._id) {
            this.g.a();
        }
    }

    public void c() {
        this.j.b();
        if (c.a().b(this)) {
            c.a().c(this);
        }
    }

    public void a() {
        if (this.f != null) {
            this.f.a(this.c, this.d);
        }
    }

    public void a(View view, int i) {
        long j = 0;
        if (view == this.j) {
            long j2 = this.c._id;
            if (this.c._prid > 0) {
                j = this.c._prid;
            }
            r.a = "review";
            cn.xiaochuankeji.tieba.ui.utils.c.a(this.a, this.b, this.c, j2, j, this.c.getCommentImage(), this.c.getImgVideos(), i, EntranceType.CommentImage);
        }
    }

    public void b() {
        long j = this.c.sourceMid;
        if (0 != j) {
            MemberDetailActivity.a(this.a, j, this.c._id, 2, this.c._pid);
        }
    }

    public void d() {
    }

    public void b(View view) {
        if (this.f != null) {
            this.f.a(this.c);
        }
    }

    public void onClick(View view) {
        if (this.f != null) {
            this.f.a(this.c, this.d);
        }
    }

    public boolean onLongClick(View view) {
        if (this.f != null) {
            this.f.a(this.c);
        }
        return true;
    }
}
