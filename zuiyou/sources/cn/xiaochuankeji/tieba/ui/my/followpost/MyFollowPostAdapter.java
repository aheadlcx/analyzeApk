package cn.xiaochuankeji.tieba.ui.my.followpost;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.api.tale.TaleService;
import cn.xiaochuankeji.tieba.api.tale.a;
import cn.xiaochuankeji.tieba.api.user.UserService;
import cn.xiaochuankeji.tieba.background.f.b;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.background.utils.newshare.ShareDataModel;
import cn.xiaochuankeji.tieba.json.EmptyJson;
import cn.xiaochuankeji.tieba.json.tale.FollowPostArticleJson;
import cn.xiaochuankeji.tieba.ui.CustomReportReasonActivity;
import cn.xiaochuankeji.tieba.ui.base.c;
import cn.xiaochuankeji.tieba.ui.member.MemberDetailActivity;
import cn.xiaochuankeji.tieba.ui.tale.ArticleShareDataModel;
import cn.xiaochuankeji.tieba.ui.tale.TaleDetailActivity;
import cn.xiaochuankeji.tieba.ui.tale.TaleListActivity;
import cn.xiaochuankeji.tieba.ui.tale.TaleThumbUsersActivity;
import cn.xiaochuankeji.tieba.ui.utils.d;
import cn.xiaochuankeji.tieba.ui.widget.SDBottomSheet;
import cn.xiaochuankeji.tieba.ui.widget.SDCheckSheet;
import cn.xiaochuankeji.tieba.ui.widget.f;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import cn.xiaochuankeji.tieba.ui.widget.updown.PostItemUpDownView;
import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.l;
import rx.e;
import rx.j;

public class MyFollowPostAdapter extends c<FollowPostArticleJson> {
    private a d = new a();
    private String e;
    private int f;

    class MyFollowPostHolder extends c.a {
        View b;
        FollowPostArticleJson c;
        final /* synthetic */ MyFollowPostAdapter d;
        @BindView
        WebImageView iv_avatar;
        @BindView
        WebImageView iv_img;
        @BindView
        PostItemUpDownView postItemUpDownView;
        @BindView
        View rl_img;
        @BindView
        TextView tvCommentCount;
        @BindView
        TextView tv_content;
        @BindView
        TextView tv_img_count;
        @BindView
        TextView tv_name;
        @BindView
        TextView tv_share;
        @BindView
        TextView tv_subject;

        public MyFollowPostHolder(MyFollowPostAdapter myFollowPostAdapter, View view) {
            this.d = myFollowPostAdapter;
            super(myFollowPostAdapter, view);
            this.b = view;
            ButterKnife.a(this, view);
        }

        public void a(final FollowPostArticleJson followPostArticleJson) {
            this.c = followPostArticleJson;
            if (followPostArticleJson.author != null && followPostArticleJson.theme != null) {
                CharSequence charSequence;
                TextView textView = this.tv_share;
                if (followPostArticleJson.shareCount < 1) {
                    charSequence = "分享";
                } else {
                    charSequence = d.b(followPostArticleJson.shareCount);
                }
                textView.setText(charSequence);
                this.tv_share.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ MyFollowPostHolder b;

                    public void onClick(View view) {
                        this.b.b(followPostArticleJson);
                    }
                });
                this.iv_avatar.setWebImage(b.a(followPostArticleJson.author.id, followPostArticleJson.author.avatar));
                this.iv_avatar.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ MyFollowPostHolder b;

                    public void onClick(View view) {
                        MemberDetailActivity.a(this.b.d.a, followPostArticleJson.author.id);
                    }
                });
                this.tv_name.setText(d.b(followPostArticleJson.author.name));
                this.tv_subject.setText(followPostArticleJson.theme.title);
                this.tv_subject.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ MyFollowPostHolder b;

                    public void onClick(View view) {
                        TaleListActivity.a(this.b.d.a, this.b.d.e, followPostArticleJson.theme.id, null);
                    }
                });
                if (followPostArticleJson.coverId == 0) {
                    this.rl_img.setVisibility(8);
                } else {
                    this.rl_img.setVisibility(0);
                    this.iv_img.setWebImage(b.a(followPostArticleJson.coverId, true));
                    if (followPostArticleJson.imgCount < 2) {
                        this.tv_img_count.setVisibility(4);
                    } else {
                        this.tv_img_count.setText(this.d.a.getString(R.string.follow_post_img_count, new Object[]{String.valueOf(followPostArticleJson.imgCount)}));
                    }
                }
                if (TextUtils.isEmpty(followPostArticleJson.summary)) {
                    this.tv_content.setVisibility(8);
                } else {
                    this.tv_content.setVisibility(0);
                    this.tv_content.setText(followPostArticleJson.summary);
                }
                this.tvCommentCount.setText(followPostArticleJson.commentCount < 1 ? "评论" : d.b(followPostArticleJson.commentCount));
                this.tvCommentCount.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ MyFollowPostHolder a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        TaleDetailActivity.a(this.a.d.a, this.a.d.e, this.a.c.id, true);
                    }
                });
                this.postItemUpDownView.a(followPostArticleJson.liked, followPostArticleJson.likeCount, new PostItemUpDownView.a(this) {
                    final /* synthetic */ MyFollowPostHolder a;

                    {
                        this.a = r1;
                    }

                    public void a(boolean z) {
                        TaleThumbUsersActivity.a(this.a.b.getContext(), this.a.c.id, z, "detail");
                    }

                    public void a(int i, int i2, boolean z) {
                        if (z) {
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("from", "theme");
                            jSONObject.put("id", Long.valueOf(this.a.c.id));
                            jSONObject.put("op", i == 1 ? "up" : "down");
                            jSONObject.put("value", Integer.valueOf(1));
                            FollowPostArticleJson followPostArticleJson = this.a.c;
                            followPostArticleJson.likeCount = (i == 1 ? 1 : 0) + followPostArticleJson.likeCount;
                            ((TaleService) cn.xiaochuankeji.tieba.network.c.b(TaleService.class)).taleThumb(jSONObject).a(rx.a.b.a.a()).a(new e<EmptyJson>(this) {
                                final /* synthetic */ AnonymousClass5 a;

                                {
                                    this.a = r1;
                                }

                                public /* synthetic */ void onNext(Object obj) {
                                    a((EmptyJson) obj);
                                }

                                public void onCompleted() {
                                }

                                public void onError(Throwable th) {
                                    g.b(th.getMessage());
                                }

                                public void a(EmptyJson emptyJson) {
                                }
                            });
                        }
                    }
                });
                this.b.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ MyFollowPostHolder a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        TaleDetailActivity.a(this.a.d.a, this.a.d.e, this.a.c.id);
                    }
                });
                if ("myArticles".equals(this.d.e)) {
                    this.b.setOnLongClickListener(new OnLongClickListener(this) {
                        final /* synthetic */ MyFollowPostHolder b;

                        public boolean onLongClick(View view) {
                            f.a("提示", "确定要删除？", (Activity) this.b.d.a, new f.a(this) {
                                final /* synthetic */ AnonymousClass7 a;

                                {
                                    this.a = r1;
                                }

                                public void a(boolean z) {
                                    if (z) {
                                        this.a.b.d.c.remove(followPostArticleJson);
                                        this.a.b.d.notifyDataSetChanged();
                                        this.a.b.d.d.a(followPostArticleJson.id).a(rx.a.b.a.a()).b(new j<String>(this) {
                                            final /* synthetic */ AnonymousClass1 a;

                                            {
                                                this.a = r1;
                                            }

                                            public /* synthetic */ void onNext(Object obj) {
                                                a((String) obj);
                                            }

                                            public void onCompleted() {
                                            }

                                            public void onError(Throwable th) {
                                            }

                                            public void a(String str) {
                                            }
                                        });
                                    }
                                }
                            });
                            return true;
                        }
                    });
                }
            }
        }

        private void b(final FollowPostArticleJson followPostArticleJson) {
            Object obj;
            if (cn.xiaochuankeji.tieba.background.a.g().c() == followPostArticleJson.author.id) {
                obj = 1;
            } else {
                obj = null;
            }
            SDBottomSheet sDBottomSheet = new SDBottomSheet((Activity) this.d.a, new SDBottomSheet.b(this) {
                final /* synthetic */ MyFollowPostHolder b;

                public void a(final int i) {
                    if (i == 4 || i == 2 || i == 3 || i == 5 || i == 1) {
                        final ShareDataModel articleShareDataModel = new ArticleShareDataModel(followPostArticleJson, i);
                        articleShareDataModel.prepareData(new ShareDataModel.a(this) {
                            final /* synthetic */ AnonymousClass8 c;

                            public void a() {
                                cn.xiaochuankeji.tieba.background.utils.share.b.a().a((Activity) this.c.b.d.a, articleShareDataModel);
                                cn.xiaochuankeji.tieba.background.i.a.a("tale_article", followPostArticleJson.id, "theme", (String) cn.xiaochuankeji.tieba.d.d.b.get(Integer.valueOf(i)), articleShareDataModel.getABTestId());
                                FollowPostArticleJson followPostArticleJson = followPostArticleJson;
                                followPostArticleJson.shareCount++;
                                this.c.b.tv_share.setText(followPostArticleJson.shareCount < 1 ? "分享" : d.b(followPostArticleJson.shareCount));
                            }
                        });
                    }
                    if (i == 18) {
                        d.a(followPostArticleJson.theme.title + "(分享自@最右APP)看详情戳链接→_→" + cn.xiaochuankeji.tieba.background.utils.d.a.c(followPostArticleJson.id));
                        g.a("复制成功");
                    }
                    if (i == 12) {
                        this.b.c(followPostArticleJson);
                    }
                    if (i == 9) {
                        f.a("提示", "确定要删除？", (Activity) this.b.d.a, new f.a(this) {
                            final /* synthetic */ AnonymousClass8 a;

                            {
                                this.a = r1;
                            }

                            public void a(boolean z) {
                                if (z) {
                                    this.a.b.d.d.a(followPostArticleJson.id).a(rx.a.b.a.a()).b(new j<String>(this) {
                                        final /* synthetic */ AnonymousClass2 a;

                                        {
                                            this.a = r1;
                                        }

                                        public /* synthetic */ void onNext(Object obj) {
                                            a((String) obj);
                                        }

                                        public void onCompleted() {
                                        }

                                        public void onError(Throwable th) {
                                        }

                                        public void a(String str) {
                                            this.a.a.b.d.c.remove(followPostArticleJson);
                                            this.a.a.b.d.notifyDataSetChanged();
                                        }
                                    });
                                }
                            }
                        });
                    }
                }
            });
            ArrayList arrayList = new ArrayList();
            String str = "举报";
            if (obj != null) {
                arrayList.add(new SDBottomSheet.c(R.drawable.icon_option_delete, "删除", 9));
            } else {
                arrayList.add(new SDBottomSheet.c(R.drawable.icon_option_report, str, 12));
            }
            sDBottomSheet.a(sDBottomSheet.c(), arrayList);
            sDBottomSheet.b();
        }

        private void c(final FollowPostArticleJson followPostArticleJson) {
            LinkedHashMap m = cn.xiaochuankeji.tieba.background.utils.c.a.c().m();
            if (m.size() != 0) {
                SDCheckSheet sDCheckSheet = new SDCheckSheet((Activity) this.d.a, new SDCheckSheet.a(this) {
                    final /* synthetic */ MyFollowPostHolder b;

                    public void a(int i) {
                        if (i == -123) {
                            CustomReportReasonActivity.a(this.b.d.a, 0, followPostArticleJson.id, this.b.d.f, "tale_article");
                            return;
                        }
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("id", Long.valueOf(followPostArticleJson.id));
                        jSONObject.put("type", "tale_article");
                        jSONObject.put("reason", Integer.valueOf(i));
                        ((UserService) cn.xiaochuankeji.tieba.network.c.b(UserService.class)).reportUser(jSONObject).a(rx.a.b.a.a()).a(new e<Object>(this) {
                            final /* synthetic */ AnonymousClass9 a;

                            {
                                this.a = r1;
                            }

                            public void onCompleted() {
                            }

                            public void onError(Throwable th) {
                                g.a(th == null ? "举报失败" : th.getMessage());
                            }

                            public void onNext(Object obj) {
                                g.a("已举报");
                            }
                        });
                    }
                });
                int i = 0;
                for (Entry entry : m.entrySet()) {
                    int i2;
                    String str = (String) entry.getKey();
                    String str2 = (String) entry.getValue();
                    int parseInt = Integer.parseInt(str);
                    int i3 = i + 1;
                    String trim = str2.trim();
                    if (trim.equals("其他")) {
                        this.d.f = parseInt;
                        i2 = -123;
                    } else {
                        i2 = parseInt;
                    }
                    if (i3 == m.size()) {
                        sDCheckSheet.a(trim, i2, true);
                    } else {
                        sDCheckSheet.a(trim, i2, false);
                    }
                    i = i3;
                }
                sDCheckSheet.b();
            }
        }

        public void a() {
            org.greenrobot.eventbus.c.a().a(this);
        }

        public void b() {
            org.greenrobot.eventbus.c.a().c(this);
        }

        @l(a = ThreadMode.MAIN)
        public void updateSocialState(cn.xiaochuankeji.tieba.b.e eVar) {
            if (this.c.id == eVar.b) {
                if (eVar.a == 3) {
                    if (this.postItemUpDownView != null) {
                        this.postItemUpDownView.b();
                    }
                } else if (eVar.a == 2) {
                    if (this.postItemUpDownView != null) {
                        this.postItemUpDownView.b();
                    }
                    if (this.c != null) {
                        this.c.liked = 0;
                    }
                }
            }
        }
    }

    public class MyFollowPostHolder_ViewBinding implements Unbinder {
        private MyFollowPostHolder b;

        @UiThread
        public MyFollowPostHolder_ViewBinding(MyFollowPostHolder myFollowPostHolder, View view) {
            this.b = myFollowPostHolder;
            myFollowPostHolder.iv_avatar = (WebImageView) butterknife.a.b.b(view, R.id.iv_avatar, "field 'iv_avatar'", WebImageView.class);
            myFollowPostHolder.tv_name = (TextView) butterknife.a.b.b(view, R.id.tv_name, "field 'tv_name'", TextView.class);
            myFollowPostHolder.iv_img = (WebImageView) butterknife.a.b.b(view, R.id.iv_img, "field 'iv_img'", WebImageView.class);
            myFollowPostHolder.tv_content = (TextView) butterknife.a.b.b(view, R.id.tv_content, "field 'tv_content'", TextView.class);
            myFollowPostHolder.tvCommentCount = (TextView) butterknife.a.b.b(view, R.id.tvCommentCount, "field 'tvCommentCount'", TextView.class);
            myFollowPostHolder.postItemUpDownView = (PostItemUpDownView) butterknife.a.b.b(view, R.id.like, "field 'postItemUpDownView'", PostItemUpDownView.class);
            myFollowPostHolder.tv_subject = (TextView) butterknife.a.b.b(view, R.id.tv_subject, "field 'tv_subject'", TextView.class);
            myFollowPostHolder.rl_img = butterknife.a.b.a(view, R.id.rl_img, "field 'rl_img'");
            myFollowPostHolder.tv_share = (TextView) butterknife.a.b.b(view, R.id.tv_share, "field 'tv_share'", TextView.class);
            myFollowPostHolder.tv_img_count = (TextView) butterknife.a.b.b(view, R.id.tv_img_count, "field 'tv_img_count'", TextView.class);
        }

        @CallSuper
        public void a() {
            MyFollowPostHolder myFollowPostHolder = this.b;
            if (myFollowPostHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.b = null;
            myFollowPostHolder.iv_avatar = null;
            myFollowPostHolder.tv_name = null;
            myFollowPostHolder.iv_img = null;
            myFollowPostHolder.tv_content = null;
            myFollowPostHolder.tvCommentCount = null;
            myFollowPostHolder.postItemUpDownView = null;
            myFollowPostHolder.tv_subject = null;
            myFollowPostHolder.rl_img = null;
            myFollowPostHolder.tv_share = null;
            myFollowPostHolder.tv_img_count = null;
        }
    }

    public MyFollowPostAdapter(Context context, String str) {
        super(context);
        this.e = str;
    }

    public void a(ViewHolder viewHolder, int i) {
        ((MyFollowPostHolder) viewHolder).a((FollowPostArticleJson) this.c.get(i));
    }

    public c.a a(ViewGroup viewGroup, int i) {
        return new MyFollowPostHolder(this, LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_my_follow_post_item, viewGroup, false));
    }

    public int a(int i) {
        return 0;
    }

    public void onViewAttachedToWindow(ViewHolder viewHolder) {
        super.onViewAttachedToWindow(viewHolder);
        if (viewHolder instanceof MyFollowPostHolder) {
            ((MyFollowPostHolder) viewHolder).a();
        }
    }

    public void onViewDetachedFromWindow(ViewHolder viewHolder) {
        super.onViewDetachedFromWindow(viewHolder);
        if (viewHolder instanceof MyFollowPostHolder) {
            ((MyFollowPostHolder) viewHolder).b();
        }
    }
}
