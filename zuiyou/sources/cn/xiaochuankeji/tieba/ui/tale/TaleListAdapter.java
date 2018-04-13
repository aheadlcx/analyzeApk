package cn.xiaochuankeji.tieba.ui.tale;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.api.tale.TaleService;
import cn.xiaochuankeji.tieba.api.tale.a;
import cn.xiaochuankeji.tieba.api.user.UserService;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.background.utils.newshare.ShareDataModel;
import cn.xiaochuankeji.tieba.json.EmptyJson;
import cn.xiaochuankeji.tieba.json.tale.FollowPostArticleJson;
import cn.xiaochuankeji.tieba.json.tale.FollowPostThemeJson;
import cn.xiaochuankeji.tieba.ui.CustomReportReasonActivity;
import cn.xiaochuankeji.tieba.ui.base.c;
import cn.xiaochuankeji.tieba.ui.member.MemberDetailActivity;
import cn.xiaochuankeji.tieba.ui.utils.d;
import cn.xiaochuankeji.tieba.ui.widget.SDBottomSheet;
import cn.xiaochuankeji.tieba.ui.widget.SDCheckSheet;
import cn.xiaochuankeji.tieba.ui.widget.f;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import cn.xiaochuankeji.tieba.ui.widget.updown.PostItemUpDownView;
import cn.xiaochuankeji.tieba.webview.WebActivity;
import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.l;
import rx.e;
import rx.j;

public class TaleListAdapter extends c<FollowPostArticleJson> {
    private FollowPostThemeJson d;
    private a e = new a();
    private final int f = 1;
    private int g;

    class FooterHolder extends c.a {
        View b;
        final /* synthetic */ TaleListAdapter c;
        @BindView
        TextView tv_content;
        @BindView
        TextView tv_why;

        public FooterHolder(TaleListAdapter taleListAdapter, View view) {
            this.c = taleListAdapter;
            super(taleListAdapter, view);
            this.b = view;
            ButterKnife.a(this, view);
        }
    }

    public class FooterHolder_ViewBinding implements Unbinder {
        private FooterHolder b;

        @UiThread
        public FooterHolder_ViewBinding(FooterHolder footerHolder, View view) {
            this.b = footerHolder;
            footerHolder.tv_content = (TextView) b.b(view, R.id.tv_content, "field 'tv_content'", TextView.class);
            footerHolder.tv_why = (TextView) b.b(view, R.id.tv_why, "field 'tv_why'", TextView.class);
        }

        @CallSuper
        public void a() {
            FooterHolder footerHolder = this.b;
            if (footerHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.b = null;
            footerHolder.tv_content = null;
            footerHolder.tv_why = null;
        }
    }

    class TaleListItemHolder extends c.a {
        View b;
        FollowPostArticleJson c;
        final /* synthetic */ TaleListAdapter d;
        @BindView
        WebImageView iv_author;
        @BindView
        WebImageView iv_img;
        @BindView
        PostItemUpDownView postItemUpDownView;
        @BindView
        View rl_img;
        @BindView
        TextView tvCommentCount;
        @BindView
        TextView tvShare;
        @BindView
        TextView tv_author_name;
        @BindView
        TextView tv_content;
        @BindView
        TextView tv_img_count;

        public TaleListItemHolder(TaleListAdapter taleListAdapter, View view) {
            this.d = taleListAdapter;
            super(taleListAdapter, view);
            this.b = view;
            ButterKnife.a(this, view);
        }

        public void a(final FollowPostArticleJson followPostArticleJson) {
            this.c = followPostArticleJson;
            if (this.c.author != null) {
                this.iv_author.setWebImage(cn.xiaochuankeji.tieba.background.f.b.a(followPostArticleJson.author.id, followPostArticleJson.author.avatar));
                this.tv_author_name.setText(d.b(followPostArticleJson.author.name));
                this.tvShare.setText(followPostArticleJson.shareCount < 1 ? "分享" : d.b(followPostArticleJson.shareCount));
                this.tvShare.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ TaleListItemHolder b;

                    public void onClick(View view) {
                        this.b.b(followPostArticleJson);
                    }
                });
                if (followPostArticleJson.coverId == 0) {
                    this.rl_img.setVisibility(8);
                } else {
                    this.rl_img.setVisibility(0);
                    this.iv_img.setWebImage(cn.xiaochuankeji.tieba.background.f.b.a(followPostArticleJson.coverId, true));
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
                this.iv_author.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ TaleListItemHolder b;

                    public void onClick(View view) {
                        MemberDetailActivity.a(this.b.d.a, followPostArticleJson.author.id);
                    }
                });
                this.tvCommentCount.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ TaleListItemHolder b;

                    public void onClick(View view) {
                        TaleDetailActivity.a(this.b.d.a, "theme", followPostArticleJson.id, true);
                    }
                });
                this.postItemUpDownView.a(this.c.liked, this.c.likeCount, new PostItemUpDownView.a(this) {
                    final /* synthetic */ TaleListItemHolder a;

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
                                final /* synthetic */ AnonymousClass4 a;

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
            }
        }

        private void b(final FollowPostArticleJson followPostArticleJson) {
            if (this.d.d != null) {
                Object obj;
                followPostArticleJson.theme = this.d.d;
                if (cn.xiaochuankeji.tieba.background.a.g().c() == followPostArticleJson.author.id) {
                    obj = 1;
                } else {
                    obj = null;
                }
                SDBottomSheet sDBottomSheet = new SDBottomSheet((Activity) this.d.a, new SDBottomSheet.b(this) {
                    final /* synthetic */ TaleListItemHolder b;

                    public void a(final int i) {
                        if (i == 4 || i == 2 || i == 3 || i == 5 || i == 1) {
                            final ShareDataModel articleShareDataModel = new ArticleShareDataModel(followPostArticleJson, i);
                            articleShareDataModel.prepareData(new ShareDataModel.a(this) {
                                final /* synthetic */ AnonymousClass5 c;

                                public void a() {
                                    CharSequence charSequence;
                                    cn.xiaochuankeji.tieba.background.utils.share.b.a().a((Activity) this.c.b.d.a, articleShareDataModel);
                                    cn.xiaochuankeji.tieba.background.i.a.a("tale_article", followPostArticleJson.id, "theme", (String) cn.xiaochuankeji.tieba.d.d.b.get(Integer.valueOf(i)), articleShareDataModel.getABTestId());
                                    FollowPostArticleJson followPostArticleJson = followPostArticleJson;
                                    followPostArticleJson.shareCount++;
                                    TextView textView = this.c.b.tvShare;
                                    if (followPostArticleJson.shareCount < 1) {
                                        charSequence = "分享";
                                    } else {
                                        charSequence = d.b(followPostArticleJson.shareCount);
                                    }
                                    textView.setText(charSequence);
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
                                final /* synthetic */ AnonymousClass5 a;

                                {
                                    this.a = r1;
                                }

                                public void a(boolean z) {
                                    if (z) {
                                        this.a.b.d.e.a(followPostArticleJson.id).a(rx.a.b.a.a()).b(new j<String>(this) {
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
        }

        private void c(final FollowPostArticleJson followPostArticleJson) {
            LinkedHashMap m = cn.xiaochuankeji.tieba.background.utils.c.a.c().m();
            if (m.size() != 0) {
                SDCheckSheet sDCheckSheet = new SDCheckSheet((Activity) this.d.a, new SDCheckSheet.a(this) {
                    final /* synthetic */ TaleListItemHolder b;

                    public void a(int i) {
                        if (i == -123) {
                            CustomReportReasonActivity.a(this.b.d.a, 0, followPostArticleJson.id, this.b.d.g, "tale_article");
                            return;
                        }
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("id", Long.valueOf(followPostArticleJson.id));
                        jSONObject.put("type", "tale_article");
                        jSONObject.put("reason", Integer.valueOf(i));
                        ((UserService) cn.xiaochuankeji.tieba.network.c.b(UserService.class)).reportUser(jSONObject).a(rx.a.b.a.a()).a(new e<Object>(this) {
                            final /* synthetic */ AnonymousClass6 a;

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
                        this.d.g = parseInt;
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

    public class TaleListItemHolder_ViewBinding implements Unbinder {
        private TaleListItemHolder b;

        @UiThread
        public TaleListItemHolder_ViewBinding(TaleListItemHolder taleListItemHolder, View view) {
            this.b = taleListItemHolder;
            taleListItemHolder.iv_author = (WebImageView) b.b(view, R.id.iv_author, "field 'iv_author'", WebImageView.class);
            taleListItemHolder.tv_author_name = (TextView) b.b(view, R.id.tv_author_name, "field 'tv_author_name'", TextView.class);
            taleListItemHolder.iv_img = (WebImageView) b.b(view, R.id.iv_img, "field 'iv_img'", WebImageView.class);
            taleListItemHolder.tv_content = (TextView) b.b(view, R.id.tv_content, "field 'tv_content'", TextView.class);
            taleListItemHolder.tvCommentCount = (TextView) b.b(view, R.id.tvCommentCount, "field 'tvCommentCount'", TextView.class);
            taleListItemHolder.postItemUpDownView = (PostItemUpDownView) b.b(view, R.id.like, "field 'postItemUpDownView'", PostItemUpDownView.class);
            taleListItemHolder.rl_img = b.a(view, R.id.rl_img, "field 'rl_img'");
            taleListItemHolder.tv_img_count = (TextView) b.b(view, R.id.tv_img_count, "field 'tv_img_count'", TextView.class);
            taleListItemHolder.tvShare = (TextView) b.b(view, R.id.tvShare, "field 'tvShare'", TextView.class);
        }

        @CallSuper
        public void a() {
            TaleListItemHolder taleListItemHolder = this.b;
            if (taleListItemHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.b = null;
            taleListItemHolder.iv_author = null;
            taleListItemHolder.tv_author_name = null;
            taleListItemHolder.iv_img = null;
            taleListItemHolder.tv_content = null;
            taleListItemHolder.tvCommentCount = null;
            taleListItemHolder.postItemUpDownView = null;
            taleListItemHolder.rl_img = null;
            taleListItemHolder.tv_img_count = null;
            taleListItemHolder.tvShare = null;
        }
    }

    public TaleListAdapter(Context context) {
        super(context);
    }

    public void a(FollowPostThemeJson followPostThemeJson) {
        this.d = followPostThemeJson;
    }

    public void a(ViewHolder viewHolder, final int i) {
        if (viewHolder instanceof TaleListItemHolder) {
            TaleListItemHolder taleListItemHolder = (TaleListItemHolder) viewHolder;
            taleListItemHolder.a((FollowPostArticleJson) this.c.get(i));
            taleListItemHolder.b.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ TaleListAdapter b;

                public void onClick(View view) {
                    if (this.b.d != null) {
                        TaleDetailActivity.a(this.b.a, "theme", ((FollowPostArticleJson) this.b.c.get(i)).id);
                    }
                }
            });
        } else if (viewHolder instanceof FooterHolder) {
            FooterHolder footerHolder = (FooterHolder) viewHolder;
            final FollowPostArticleJson followPostArticleJson = (FollowPostArticleJson) this.c.get(i);
            footerHolder.b.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ TaleListAdapter b;

                public void onClick(View view) {
                    TaleInvisibleActivity.a(this.b.a, "theme", followPostArticleJson.theme.id);
                }
            });
            footerHolder.tv_content.setText(this.a.getString(R.string.tale_invisible_tip, new Object[]{Long.valueOf(followPostArticleJson.theme.folded_article_cnt)}));
            footerHolder.tv_why.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ TaleListAdapter a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    WebActivity.a(this.a.a, cn.xiaochuan.jsbridge.b.a(null, cn.xiaochuankeji.tieba.background.utils.d.a.d("https://$$/help/theme/describe")));
                }
            });
        }
    }

    public c.a a(ViewGroup viewGroup, int i) {
        if (i == 0) {
            return new TaleListItemHolder(this, LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_follow_list_item, viewGroup, false));
        }
        return new FooterHolder(this, LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_follow_list_footer, viewGroup, false));
    }

    public int a(int i) {
        if (((FollowPostArticleJson) this.c.get(i)).type == 1) {
            return 1;
        }
        return 0;
    }

    public void onViewAttachedToWindow(ViewHolder viewHolder) {
        super.onViewAttachedToWindow(viewHolder);
        if (viewHolder instanceof TaleListItemHolder) {
            ((TaleListItemHolder) viewHolder).a();
        }
    }

    public void a(FollowPostArticleJson followPostArticleJson) {
        this.c.add(0, followPostArticleJson);
        notifyDataSetChanged();
    }

    public void onViewDetachedFromWindow(ViewHolder viewHolder) {
        super.onViewDetachedFromWindow(viewHolder);
        if (viewHolder instanceof TaleListItemHolder) {
            ((TaleListItemHolder) viewHolder).b();
        }
    }
}
