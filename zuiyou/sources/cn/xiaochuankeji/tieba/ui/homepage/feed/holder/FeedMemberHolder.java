package cn.xiaochuankeji.tieba.ui.homepage.feed.holder;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.view.View.OnClickListener;
import butterknife.BindView;
import cn.xiaochuankeji.tieba.background.e.a;
import cn.xiaochuankeji.tieba.background.e.b;
import cn.xiaochuankeji.tieba.background.net.XCError;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.json.MemberInfoBean;
import cn.xiaochuankeji.tieba.ui.homepage.feed.model.FeedViewModel;
import cn.xiaochuankeji.tieba.ui.member.MemberDetailActivity;
import cn.xiaochuankeji.tieba.ui.utils.d;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

public class FeedMemberHolder extends b<FeedMemberHolder> {
    private b a;
    @BindView
    WebImageView avatar;
    private a b;
    @BindView
    AppCompatTextView follow;
    @BindView
    AppCompatTextView nickname;

    public FeedMemberHolder(View view) {
        super(view);
    }

    public void a(final FeedMemberHolder feedMemberHolder, int i, final MemberInfoBean memberInfoBean) {
        final long id = memberInfoBean.getId();
        feedMemberHolder.avatar.setWebImage(cn.xiaochuankeji.tieba.background.f.b.a(id, memberInfoBean.getAvatarId()));
        feedMemberHolder.avatar.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ FeedMemberHolder b;

            public void onClick(View view) {
                MemberDetailActivity.a(view.getContext(), id);
            }
        });
        feedMemberHolder.nickname.setText(d.a(memberInfoBean.getNickName(), 14));
        boolean z = memberInfoBean.getFollowStatus() > 0;
        feedMemberHolder.follow.setSelected(z);
        feedMemberHolder.follow.setText(z ? "已关注" : "关注");
        com.jakewharton.a.b.a.a(feedMemberHolder.follow).d(200, TimeUnit.MILLISECONDS).a(rx.a.b.a.a()).a(new rx.b.b<Void>(this) {
            final /* synthetic */ FeedMemberHolder c;

            public /* synthetic */ void call(Object obj) {
                a((Void) obj);
            }

            public void a(Void voidR) {
                boolean z = true;
                if (feedMemberHolder.itemView != null) {
                    Context context = feedMemberHolder.itemView.getContext();
                    boolean z2 = memberInfoBean.getFollowStatus() > 0;
                    if (context instanceof FragmentActivity) {
                        if (cn.xiaochuankeji.tieba.ui.auth.a.a((FragmentActivity) context, "home_tab", z2 ? -10 : 10) && this.c.a == null && this.c.b == null) {
                            CharSequence charSequence;
                            if (z2) {
                                this.c.b(feedMemberHolder, memberInfoBean);
                            } else {
                                this.c.a(feedMemberHolder, memberInfoBean);
                            }
                            if (z2) {
                                z = false;
                            }
                            feedMemberHolder.follow.setSelected(z);
                            AppCompatTextView appCompatTextView = feedMemberHolder.follow;
                            if (z) {
                                charSequence = "已关注";
                            } else {
                                charSequence = "关注";
                            }
                            appCompatTextView.setText(charSequence);
                        }
                    }
                }
            }
        });
    }

    private void a(final FeedMemberHolder feedMemberHolder, final MemberInfoBean memberInfoBean) {
        if (FeedViewModel.a) {
            FeedViewModel.a = false;
            g.a("下拉刷新获取关注内容");
        }
        if (this.a == null) {
            this.a = new b(memberInfoBean.getId(), "follow_suggest_page", null, new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
                final /* synthetic */ FeedMemberHolder c;

                public /* synthetic */ void onResponse(Object obj, Object obj2) {
                    a((JSONObject) obj, obj2);
                }

                public void a(JSONObject jSONObject, Object obj) {
                    this.c.a = null;
                    if (memberInfoBean != null) {
                        memberInfoBean.setFollowStatus(1);
                    }
                    if (feedMemberHolder.follow != null) {
                        feedMemberHolder.follow.setSelected(true);
                        feedMemberHolder.follow.setText("已关注");
                    }
                }
            }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
                final /* synthetic */ FeedMemberHolder a;

                {
                    this.a = r1;
                }

                public void onErrorResponse(XCError xCError, Object obj) {
                    this.a.a = null;
                    g.a(xCError.getMessage());
                }
            });
            this.a.execute();
        }
    }

    private void b(final FeedMemberHolder feedMemberHolder, final MemberInfoBean memberInfoBean) {
        if (this.b == null) {
            this.b = new a(memberInfoBean.getId(), null, new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
                final /* synthetic */ FeedMemberHolder c;

                public /* synthetic */ void onResponse(Object obj, Object obj2) {
                    a((JSONObject) obj, obj2);
                }

                public void a(JSONObject jSONObject, Object obj) {
                    this.c.b = null;
                    if (memberInfoBean != null) {
                        memberInfoBean.setFollowStatus(0);
                    }
                    if (feedMemberHolder.follow != null) {
                        feedMemberHolder.follow.setSelected(false);
                        feedMemberHolder.follow.setText("关注");
                    }
                }
            }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
                final /* synthetic */ FeedMemberHolder a;

                {
                    this.a = r1;
                }

                public void onErrorResponse(XCError xCError, Object obj) {
                    this.a.b = null;
                    g.a(xCError.getMessage());
                }
            });
            this.b.execute();
        }
    }
}
