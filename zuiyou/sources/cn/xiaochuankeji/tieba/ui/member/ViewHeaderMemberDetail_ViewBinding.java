package cn.xiaochuankeji.tieba.ui.member;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.a.a;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;

public class ViewHeaderMemberDetail_ViewBinding implements Unbinder {
    private ViewHeaderMemberDetail b;
    private View c;
    private View d;
    private View e;
    private View f;
    private View g;
    private View h;

    @UiThread
    public ViewHeaderMemberDetail_ViewBinding(final ViewHeaderMemberDetail viewHeaderMemberDetail, View view) {
        this.b = viewHeaderMemberDetail;
        viewHeaderMemberDetail.member_cover = (WebImageView) b.b(view, R.id.member_cover, "field 'member_cover'", WebImageView.class);
        View a = b.a(view, R.id.member_avatar, "field 'member_avatar' and method 'openAvatar'");
        viewHeaderMemberDetail.member_avatar = (WebImageView) b.c(a, R.id.member_avatar, "field 'member_avatar'", WebImageView.class);
        this.c = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ ViewHeaderMemberDetail_ViewBinding c;

            public void a(View view) {
                viewHeaderMemberDetail.openAvatar();
            }
        });
        viewHeaderMemberDetail.member_name = (AppCompatTextView) b.b(view, R.id.member_name, "field 'member_name'", AppCompatTextView.class);
        viewHeaderMemberDetail.member_gender = (AppCompatImageView) b.b(view, R.id.member_gender, "field 'member_gender'", AppCompatImageView.class);
        viewHeaderMemberDetail.member_official = (AppCompatImageView) b.b(view, R.id.member_official, "field 'member_official'", AppCompatImageView.class);
        a = b.a(view, R.id.member_assessor, "field 'member_assessor' and method 'assessor'");
        viewHeaderMemberDetail.member_assessor = (AppCompatImageView) b.c(a, R.id.member_assessor, "field 'member_assessor'", AppCompatImageView.class);
        this.d = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ ViewHeaderMemberDetail_ViewBinding c;

            public void a(View view) {
                viewHeaderMemberDetail.assessor();
            }
        });
        a = b.a(view, R.id.member_community, "field 'member_community' and method 'community'");
        viewHeaderMemberDetail.member_community = (AppCompatImageView) b.c(a, R.id.member_community, "field 'member_community'", AppCompatImageView.class);
        this.e = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ ViewHeaderMemberDetail_ViewBinding c;

            public void a(View view) {
                viewHeaderMemberDetail.community();
            }
        });
        viewHeaderMemberDetail.member_sign = (AppCompatTextView) b.b(view, R.id.member_sign, "field 'member_sign'", AppCompatTextView.class);
        viewHeaderMemberDetail.post_count = (AppCompatTextView) b.b(view, R.id.post_count, "field 'post_count'", AppCompatTextView.class);
        viewHeaderMemberDetail.like_count = (AppCompatTextView) b.b(view, R.id.like_count, "field 'like_count'", AppCompatTextView.class);
        viewHeaderMemberDetail.follow_count = (AppCompatTextView) b.b(view, R.id.follow_count, "field 'follow_count'", AppCompatTextView.class);
        viewHeaderMemberDetail.fans_count = (AppCompatTextView) b.b(view, R.id.fans_count, "field 'fans_count'", AppCompatTextView.class);
        viewHeaderMemberDetail.ll_talent = (LinearLayout) b.b(view, R.id.ll_talent, "field 'll_talent'", LinearLayout.class);
        a = b.a(view, R.id.iv_talent, "field 'iv_talent' and method 'talent'");
        viewHeaderMemberDetail.iv_talent = (AppCompatImageView) b.c(a, R.id.iv_talent, "field 'iv_talent'", AppCompatImageView.class);
        this.f = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ ViewHeaderMemberDetail_ViewBinding c;

            public void a(View view) {
                viewHeaderMemberDetail.talent();
            }
        });
        viewHeaderMemberDetail.iv_talent_text = (AppCompatImageView) b.b(view, R.id.iv_talent_text, "field 'iv_talent_text'", AppCompatImageView.class);
        viewHeaderMemberDetail.tv_talent = (AppCompatTextView) b.b(view, R.id.tv_talent, "field 'tv_talent'", AppCompatTextView.class);
        View a2 = b.a(view, R.id.follow, "method 'follow'");
        this.g = a2;
        a2.setOnClickListener(new a(this) {
            final /* synthetic */ ViewHeaderMemberDetail_ViewBinding c;

            public void a(View view) {
                viewHeaderMemberDetail.follow();
            }
        });
        a2 = b.a(view, R.id.fans, "method 'fans'");
        this.h = a2;
        a2.setOnClickListener(new a(this) {
            final /* synthetic */ ViewHeaderMemberDetail_ViewBinding c;

            public void a(View view) {
                viewHeaderMemberDetail.fans();
            }
        });
    }

    @CallSuper
    public void a() {
        ViewHeaderMemberDetail viewHeaderMemberDetail = this.b;
        if (viewHeaderMemberDetail == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        viewHeaderMemberDetail.member_cover = null;
        viewHeaderMemberDetail.member_avatar = null;
        viewHeaderMemberDetail.member_name = null;
        viewHeaderMemberDetail.member_gender = null;
        viewHeaderMemberDetail.member_official = null;
        viewHeaderMemberDetail.member_assessor = null;
        viewHeaderMemberDetail.member_community = null;
        viewHeaderMemberDetail.member_sign = null;
        viewHeaderMemberDetail.post_count = null;
        viewHeaderMemberDetail.like_count = null;
        viewHeaderMemberDetail.follow_count = null;
        viewHeaderMemberDetail.fans_count = null;
        viewHeaderMemberDetail.ll_talent = null;
        viewHeaderMemberDetail.iv_talent = null;
        viewHeaderMemberDetail.iv_talent_text = null;
        viewHeaderMemberDetail.tv_talent = null;
        this.c.setOnClickListener(null);
        this.c = null;
        this.d.setOnClickListener(null);
        this.d = null;
        this.e.setOnClickListener(null);
        this.e = null;
        this.f.setOnClickListener(null);
        this.f = null;
        this.g.setOnClickListener(null);
        this.g = null;
        this.h.setOnClickListener(null);
        this.h = null;
    }
}
