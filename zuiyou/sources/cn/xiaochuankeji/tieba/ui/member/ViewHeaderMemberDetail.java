package cn.xiaochuankeji.tieba.ui.member;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.xiaochuan.jsbridge.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.beans.Medal;
import cn.xiaochuankeji.tieba.background.beans.Member;
import cn.xiaochuankeji.tieba.background.utils.d.a;
import cn.xiaochuankeji.tieba.ui.follow.UserBeFollowedActivity;
import cn.xiaochuankeji.tieba.ui.follow.UserFollowActivity;
import cn.xiaochuankeji.tieba.ui.utils.e;
import cn.xiaochuankeji.tieba.ui.widget.SDGuideDialog;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import cn.xiaochuankeji.tieba.webview.WebActivity;

public class ViewHeaderMemberDetail extends LinearLayout {
    private Member a;
    private String b;
    @BindView
    AppCompatTextView fans_count;
    @BindView
    AppCompatTextView follow_count;
    @BindView
    AppCompatImageView iv_talent;
    @BindView
    AppCompatImageView iv_talent_text;
    @BindView
    AppCompatTextView like_count;
    @BindView
    LinearLayout ll_talent;
    @BindView
    AppCompatImageView member_assessor;
    @BindView
    WebImageView member_avatar;
    @BindView
    AppCompatImageView member_community;
    @BindView
    WebImageView member_cover;
    @BindView
    AppCompatImageView member_gender;
    @BindView
    AppCompatTextView member_name;
    @BindView
    AppCompatImageView member_official;
    @BindView
    AppCompatTextView member_sign;
    @BindView
    AppCompatTextView post_count;
    @BindView
    AppCompatTextView tv_talent;

    public ViewHeaderMemberDetail(Context context) {
        super(context);
        inflate(context, R.layout.view_header_member_detail, this);
        ButterKnife.a(this, this);
    }

    @OnClick
    public void openAvatar() {
        MemberAvatarActivity.a(getContext(), this.a);
    }

    @OnClick
    public void follow() {
        UserFollowActivity.a(getContext(), this.a.getId());
    }

    @OnClick
    public void fans() {
        UserBeFollowedActivity.a(getContext(), this.a.getId());
    }

    @OnClick
    public void talent() {
        if (!TextUtils.isEmpty(this.b)) {
            WebActivity.a(getContext(), b.a(null, a.d("https://$$" + this.b)));
        }
    }

    @OnClick
    public void assessor() {
        int assessorRole = this.a.getAssessorRole();
        assessorRole = assessorRole == 0 ? R.drawable.img_assessor_primary_detail : assessorRole == 1 ? R.drawable.img_assessor_middle_detail : assessorRole == 2 ? R.drawable.img_assessor_senior_detail : 0;
        if (assessorRole != 0 && !((Activity) getContext()).isFinishing()) {
            SDGuideDialog sDGuideDialog = new SDGuideDialog((Activity) getContext());
            sDGuideDialog.a(assessorRole, 17);
            sDGuideDialog.b();
        }
    }

    @OnClick
    public void community() {
        int communityRole = this.a.getCommunityRole();
        communityRole = communityRole == 1 ? R.drawable.personal_shixizly_bg : communityRole == 2 ? R.drawable.personal_zhengshizly_bg : 0;
        if (communityRole != 0 && !((Activity) getContext()).isFinishing()) {
            SDGuideDialog sDGuideDialog = new SDGuideDialog((Activity) getContext());
            sDGuideDialog.a(communityRole, 17);
            sDGuideDialog.b();
        }
    }

    public void setDataBy(cn.xiaochuankeji.tieba.background.member.b bVar) {
        int i;
        this.a = bVar.a;
        CharSequence sign = this.a.getSign();
        this.member_avatar.setWebImage(cn.xiaochuankeji.tieba.background.f.b.a(this.a.getId(), this.a.getAvatarID()));
        this.member_name.setText(this.a.getName().replace("\n", ""));
        this.post_count.setText(String.valueOf(bVar.c));
        this.like_count.setText(String.valueOf(bVar.b));
        this.follow_count.setText(String.valueOf(this.a.getAtts()));
        this.fans_count.setText(String.valueOf(this.a.getFans()));
        AppCompatTextView appCompatTextView = this.member_sign;
        if (TextUtils.isEmpty(sign)) {
            sign = "这家伙很懒，什么都没有写~";
        }
        appCompatTextView.setText(sign);
        this.member_gender.setImageResource(this.a.isFemale() ? R.drawable.ic_female : R.drawable.ic_male);
        AppCompatImageView appCompatImageView = this.member_official;
        if (this.a.isOfficial()) {
            i = 0;
        } else {
            i = 8;
        }
        appCompatImageView.setVisibility(i);
        this.member_official.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ViewHeaderMemberDetail a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                e.d(this.a.getContext());
            }
        });
        i = this.a.getAssessorRole();
        if (i == 0) {
            this.member_assessor.setImageResource(R.drawable.ic_assessor_primary);
            this.member_assessor.setVisibility(0);
        } else if (i == 1) {
            this.member_assessor.setImageResource(R.drawable.ic_assessor_middle);
            this.member_assessor.setVisibility(0);
        } else if (i == 2) {
            this.member_assessor.setImageResource(R.drawable.ic_assessor_senior);
            this.member_assessor.setVisibility(0);
        } else {
            this.member_assessor.setVisibility(8);
        }
        i = this.a.getCommunityRole();
        if (i == 1) {
            this.member_community.setImageResource(R.drawable.personal_shixizly);
            this.member_community.setVisibility(0);
        } else if (i == 2) {
            this.member_community.setImageResource(R.drawable.personal_zhengshizly);
            this.member_community.setVisibility(0);
        } else {
            this.member_community.setVisibility(8);
        }
        a(this.member_cover, this.a.getId(), this.a.getCoverId());
        Medal medal = bVar.a.getMedal();
        if (medal != null) {
            this.b = medal.click_url;
            this.ll_talent.setVisibility(0);
            if (medal.original == 1) {
                this.iv_talent.setImageResource(c.a.d.a.a.a().d(R.drawable.talent_original));
                this.iv_talent_text.setVisibility(0);
            } else if (medal.original == 2) {
                this.iv_talent.setImageResource(c.a.d.a.a.a().d(R.drawable.talent));
                this.iv_talent_text.setVisibility(8);
            } else if (medal.original == 3) {
                this.iv_talent.setImageResource(c.a.d.a.a.a().d(R.drawable.topic_talent_small_icon));
                this.iv_talent_text.setVisibility(8);
            } else {
                this.ll_talent.setVisibility(8);
            }
            this.tv_talent.setText(medal.name);
            return;
        }
        this.ll_talent.setVisibility(8);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    public void a(int i) {
        this.fans_count.setText(String.valueOf(i));
    }

    public static void a(WebImageView webImageView, long j, long j2) {
        if (0 == j2) {
            webImageView.setImageResource(a(j));
        } else {
            webImageView.setWebImage(cn.xiaochuankeji.tieba.background.f.b.a(j2));
        }
    }

    private static int a(long j) {
        long j2 = j % 5;
        if (j2 == 0) {
            return R.drawable.img_member_cover_1;
        }
        if (j2 == 1) {
            return R.drawable.img_member_cover_2;
        }
        if (j2 == 2) {
            return R.drawable.img_member_cover_3;
        }
        if (j2 == 3) {
            return R.drawable.img_member_cover_4;
        }
        return R.drawable.img_member_cover_5;
    }
}
