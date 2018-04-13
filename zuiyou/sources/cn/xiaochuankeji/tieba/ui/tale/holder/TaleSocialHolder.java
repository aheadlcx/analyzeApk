package cn.xiaochuankeji.tieba.ui.tale.holder;

import android.app.Activity;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import butterknife.BindView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.api.tale.TaleService;
import cn.xiaochuankeji.tieba.api.user.UserService;
import cn.xiaochuankeji.tieba.background.a;
import cn.xiaochuankeji.tieba.background.tale.TaleComment;
import cn.xiaochuankeji.tieba.background.tale.TaleDetail;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.background.utils.newshare.ShareDataModel;
import cn.xiaochuankeji.tieba.json.EmptyJson;
import cn.xiaochuankeji.tieba.network.c;
import cn.xiaochuankeji.tieba.ui.CustomReportReasonActivity;
import cn.xiaochuankeji.tieba.ui.tale.ArticleShareDataModel;
import cn.xiaochuankeji.tieba.ui.tale.TaleThumbUsersActivity;
import cn.xiaochuankeji.tieba.ui.utils.d;
import cn.xiaochuankeji.tieba.ui.widget.SDBottomSheet;
import cn.xiaochuankeji.tieba.ui.widget.SDBottomSheet.b;
import cn.xiaochuankeji.tieba.ui.widget.SDCheckSheet;
import cn.xiaochuankeji.tieba.ui.widget.f;
import cn.xiaochuankeji.tieba.ui.widget.updown.PostItemUpDownView;
import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.l;
import rx.e;
import rx.j;

public class TaleSocialHolder extends b {
    private int c;
    @BindView
    AppCompatTextView comment;
    private TaleDetail d;
    @BindView
    PostItemUpDownView postItemUpDownView;
    @BindView
    AppCompatTextView tvShare;

    public TaleSocialHolder(ViewGroup viewGroup, int i) {
        super(viewGroup, i);
    }

    public void a(TaleComment taleComment, int i) {
        this.d = taleComment.detail;
        this.comment.setText(this.d.commentCnt < 1 ? "评论" : d.b(this.d.commentCnt));
        this.tvShare.setText(this.d.shareCnt < 1 ? "分享" : d.b(this.d.shareCnt));
        this.tvShare.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ TaleSocialHolder a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                Object obj;
                if (a.g().c() == this.a.d.author.id) {
                    obj = 1;
                } else {
                    obj = null;
                }
                SDBottomSheet sDBottomSheet = new SDBottomSheet((Activity) this.a.itemView.getContext(), new b(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void a(int i) {
                        if (i == 4 || i == 2 || i == 3 || i == 5 || i == 1) {
                            this.a.a.b(i);
                        }
                        if (i == 18) {
                            d.a(this.a.a.d.theme.title + "(分享自@最右APP)看详情戳链接→_→" + cn.xiaochuankeji.tieba.background.utils.d.a.c(this.a.a.d.id));
                            g.a("复制成功");
                        }
                        if (i == 12) {
                            this.a.a.c();
                        }
                        if (i == 9) {
                            f.a("提示", "确定要删除？", (Activity) this.a.a.itemView.getContext(), new f.a(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = r1;
                                }

                                public void a(boolean z) {
                                    if (z) {
                                        JSONObject jSONObject = new JSONObject();
                                        jSONObject.put("id", Long.valueOf(this.a.a.a.d.id));
                                        ((TaleService) c.b(TaleService.class)).delete(jSONObject).a(rx.a.b.a.a()).b(new j<String>(this) {
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
                                                org.greenrobot.eventbus.c.a().d(new cn.xiaochuankeji.tieba.ui.tale.a.b());
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
        });
        this.postItemUpDownView.a(this.d.liked, this.d.likeCnt, new PostItemUpDownView.a(this) {
            final /* synthetic */ TaleSocialHolder a;

            {
                this.a = r1;
            }

            public void a(boolean z) {
                TaleThumbUsersActivity.a(this.a.itemView.getContext(), this.a.d.id, z, "detail");
            }

            public void a(int i, int i2, boolean z) {
                if (z) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("from", "theme");
                    jSONObject.put("id", Long.valueOf(this.a.d.id));
                    jSONObject.put("op", i == 1 ? "up" : "down");
                    jSONObject.put("value", Integer.valueOf(1));
                    TaleDetail a = this.a.d;
                    a.likeCnt = (i == 1 ? 1 : 0) + a.likeCnt;
                    this.a.d.liked = i;
                    ((TaleService) c.b(TaleService.class)).taleThumb(jSONObject).a(rx.a.b.a.a()).a(new e<EmptyJson>(this) {
                        final /* synthetic */ AnonymousClass2 a;

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

    private void b(final int i) {
        final ShareDataModel articleShareDataModel = new ArticleShareDataModel(this.d, i);
        articleShareDataModel.prepareData(new ShareDataModel.a(this) {
            final /* synthetic */ TaleSocialHolder c;

            public void a() {
                cn.xiaochuankeji.tieba.background.utils.share.b.a().a((Activity) this.c.itemView.getContext(), articleShareDataModel);
                cn.xiaochuankeji.tieba.background.i.a.a("tale_article", this.c.d.id, "article", (String) cn.xiaochuankeji.tieba.d.d.b.get(Integer.valueOf(i)), articleShareDataModel.getABTestId());
                TaleDetail a = this.c.d;
                a.shareCnt++;
                this.c.tvShare.setText(this.c.d.shareCnt < 1 ? "分享" : d.b(this.c.d.shareCnt));
            }
        });
    }

    private void c() {
        LinkedHashMap m = cn.xiaochuankeji.tieba.background.utils.c.a.c().m();
        if (m.size() != 0) {
            SDCheckSheet sDCheckSheet = new SDCheckSheet((Activity) this.itemView.getContext(), new SDCheckSheet.a(this) {
                final /* synthetic */ TaleSocialHolder a;

                {
                    this.a = r1;
                }

                public void a(int i) {
                    if (i == -123) {
                        CustomReportReasonActivity.a((Activity) this.a.itemView.getContext(), 0, this.a.d.id, this.a.c, "tale_article");
                        return;
                    }
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("id", Long.valueOf(this.a.d.id));
                    jSONObject.put("type", "tale_article");
                    jSONObject.put("reason", Integer.valueOf(i));
                    ((UserService) c.b(UserService.class)).reportUser(jSONObject).a(rx.a.b.a.a()).a(new e<Object>(this) {
                        final /* synthetic */ AnonymousClass4 a;

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
                    this.c = parseInt;
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
        super.a();
        org.greenrobot.eventbus.c.a().a(this);
    }

    public void b() {
        super.b();
        org.greenrobot.eventbus.c.a().c(this);
    }

    @l(a = ThreadMode.MAIN)
    public void updateSocialState(cn.xiaochuankeji.tieba.b.e eVar) {
        if (this.d.id != eVar.b || eVar.c != 1) {
            return;
        }
        if (eVar.a == 3) {
            if (this.postItemUpDownView != null) {
                this.postItemUpDownView.b();
            }
        } else if (eVar.a == 2) {
            if (this.postItemUpDownView != null) {
                this.postItemUpDownView.b();
            }
            if (this.d != null) {
                this.d.liked = 0;
            }
        }
    }
}
