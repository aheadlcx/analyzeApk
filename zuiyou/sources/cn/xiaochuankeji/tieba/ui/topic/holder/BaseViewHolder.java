package cn.xiaochuankeji.tieba.ui.topic.holder;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.arch.lifecycle.q;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.a;
import cn.xiaochuankeji.tieba.background.data.post.Post;
import cn.xiaochuankeji.tieba.background.data.tag.NavigatorTag;
import cn.xiaochuankeji.tieba.background.net.XCError;
import cn.xiaochuankeji.tieba.background.post.d;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.background.utils.h;
import cn.xiaochuankeji.tieba.json.EmptyJson;
import cn.xiaochuankeji.tieba.network.custom.exception.ClientErrorException;
import cn.xiaochuankeji.tieba.ui.CustomReportReasonActivity;
import cn.xiaochuankeji.tieba.ui.mediabrowse.EntranceType;
import cn.xiaochuankeji.tieba.ui.member.MemberDetailActivity;
import cn.xiaochuankeji.tieba.ui.post.LikedUsersActivity;
import cn.xiaochuankeji.tieba.ui.post.postdetail.PostDetailActivity;
import cn.xiaochuankeji.tieba.ui.topic.TopicDetailActivity;
import cn.xiaochuankeji.tieba.ui.topic.TopicPostTopActivity;
import cn.xiaochuankeji.tieba.ui.topic.data.PostDataBean;
import cn.xiaochuankeji.tieba.ui.topic.data.c;
import cn.xiaochuankeji.tieba.ui.topic.holder.HolderCreator.PostFromType;
import cn.xiaochuankeji.tieba.ui.topic.weight.PostGodReview;
import cn.xiaochuankeji.tieba.ui.topic.weight.PostMemberView;
import cn.xiaochuankeji.tieba.ui.topic.weight.PostMemberView.ViewType;
import cn.xiaochuankeji.tieba.ui.topic.weight.PostOperateView;
import cn.xiaochuankeji.tieba.ui.widget.SDBottomSheet;
import cn.xiaochuankeji.tieba.ui.widget.SDCheckSheet;
import cn.xiaochuankeji.tieba.ui.widget.k;
import cn.xiaochuankeji.tieba.ui.widget.k.b;
import cn.xiaochuankeji.tieba.ui.widget.updown.PostItemUpDownView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public abstract class BaseViewHolder extends ViewHolder {
    HashMap<Long, Boolean> a;
    PostFromType b;
    HolderOperator c;
    protected Activity d;
    private d e = a.j();
    private NavigatorTag f;
    @BindView
    PostOperateView operateView;
    @BindView
    PostGodReview postGodReview;
    @BindView
    PostMemberView postMemberView;

    protected abstract PostDataBean b(c cVar);

    public BaseViewHolder(View view, Activity activity, PostFromType postFromType) {
        super(view);
        this.d = activity;
        this.b = postFromType;
        this.c = (HolderOperator) q.a((FragmentActivity) activity).a(HolderOperator.class);
        ButterKnife.a(this, view);
    }

    public void a(HashMap<Long, Boolean> hashMap) {
        this.a = hashMap;
    }

    public final void a(c cVar, NavigatorTag navigatorTag) {
        this.f = navigatorTag;
        a(cVar);
    }

    public final void a(c cVar) {
        a(b(cVar));
    }

    private void a(final PostDataBean postDataBean) {
        if (postDataBean != null) {
            b(postDataBean);
            f(postDataBean);
            g(postDataBean);
            this.itemView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ BaseViewHolder b;

                public void onClick(View view) {
                    this.b.a(postDataBean, "post");
                }
            });
            this.itemView.setOnLongClickListener(new OnLongClickListener(this) {
                final /* synthetic */ BaseViewHolder b;

                public boolean onLongClick(View view) {
                    this.b.a(postDataBean, false, true);
                    return true;
                }
            });
        }
    }

    protected void b(final PostDataBean postDataBean) {
        this.postMemberView.a(postDataBean.member, postDataBean.createTime, postDataBean.status == 3, HolderCreator.a(this.b, postDataBean.member.isFollowed(), postDataBean.hasUpdate));
        this.postMemberView.setOnMemberViewClickListener(new PostMemberView.a(this) {
            final /* synthetic */ BaseViewHolder b;

            public void a(ViewType viewType) {
                switch (viewType) {
                    case MORE:
                        this.b.a(postDataBean, false, false);
                        return;
                    case DELETE:
                        this.b.c(postDataBean);
                        return;
                    case FOLLOW:
                        this.b.d(postDataBean);
                        return;
                    case CANCEL_FOLLOW:
                        this.b.e(postDataBean);
                        return;
                    case CANCEL_FAVOR:
                        this.b.h(postDataBean);
                        return;
                    default:
                        return;
                }
            }

            public void a() {
                this.b.a(postDataBean.member.getId(), postDataBean.postId);
                cn.xiaochuankeji.tieba.a.d.a(postDataBean);
            }

            public void b() {
                this.b.a(postDataBean, false, true);
            }

            public void c() {
                this.b.a(postDataBean, "post");
            }

            public void d() {
            }
        });
    }

    private void c(final PostDataBean postDataBean) {
        k kVar = new k(this.d);
        kVar.a(cn.xiaochuankeji.tieba.background.utils.c.a.c().n(), new b(this) {
            final /* synthetic */ BaseViewHolder b;

            public void a(ArrayList<String> arrayList, String str) {
                if (arrayList != null && !arrayList.isEmpty() && this.b.f != null) {
                    this.b.c.a(this.b.d, postDataBean.postId, postDataBean.topic.topicID, postDataBean.topic.topicName, arrayList, this.b.f.ename);
                }
            }
        });
        kVar.show();
    }

    private void d(final PostDataBean postDataBean) {
        String d = HolderCreator.d(this.b);
        if (cn.xiaochuankeji.tieba.ui.auth.a.a((FragmentActivity) this.d, d, d == null ? -999 : 10)) {
            new cn.xiaochuankeji.tieba.background.e.b(postDataBean.member.getId(), null, null, new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
                final /* synthetic */ BaseViewHolder b;

                public /* synthetic */ void onResponse(Object obj, Object obj2) {
                    a((JSONObject) obj, obj2);
                }

                public void a(JSONObject jSONObject, Object obj) {
                    postDataBean.member.setFollowStatus(1);
                    postDataBean.hasUpdate = true;
                    org.greenrobot.eventbus.c.a().d(new cn.xiaochuankeji.tieba.ui.topic.data.b(postDataBean.member.getId(), true));
                }
            }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
                final /* synthetic */ BaseViewHolder a;

                {
                    this.a = r1;
                }

                public void onErrorResponse(XCError xCError, Object obj) {
                    g.a(xCError.getMessage());
                }
            }).execute();
        }
    }

    private void e(final PostDataBean postDataBean) {
        String d = HolderCreator.d(this.b);
        if (cn.xiaochuankeji.tieba.ui.auth.a.a((FragmentActivity) this.d, d, d == null ? -999 : -10)) {
            new cn.xiaochuankeji.tieba.background.e.a(postDataBean.member.getId(), null, new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
                final /* synthetic */ BaseViewHolder b;

                public /* synthetic */ void onResponse(Object obj, Object obj2) {
                    a((JSONObject) obj, obj2);
                }

                public void a(JSONObject jSONObject, Object obj) {
                    postDataBean.member.setFollowStatus(0);
                    postDataBean.hasUpdate = true;
                    org.greenrobot.eventbus.c.a().d(new cn.xiaochuankeji.tieba.ui.topic.data.b(postDataBean.member.getId(), false));
                }
            }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
                final /* synthetic */ BaseViewHolder a;

                {
                    this.a = r1;
                }

                public void onErrorResponse(XCError xCError, Object obj) {
                    g.a(xCError.getMessage());
                }
            }).execute();
        }
    }

    public void a(long j, long j2) {
        MemberDetailActivity.a(this.d, j, j2, 1, 0);
    }

    private void f(final PostDataBean postDataBean) {
        if (postDataBean.godReviews == null || postDataBean.godReviews.size() == 0) {
            this.postGodReview.setVisibility(8);
            return;
        }
        int a = c.a.d.a.a.a().a((int) R.color.CT_6);
        int a2 = c.a.d.a.a.a().a((int) R.color.CH_1);
        this.postGodReview.setVisibility(0);
        this.postGodReview.a(postDataBean, a, a2);
        this.postGodReview.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ BaseViewHolder b;

            public void onClick(View view) {
                this.b.a(postDataBean, "review");
            }
        });
    }

    private void g(final PostDataBean postDataBean) {
        this.operateView.a(postDataBean, new OnClickListener(this) {
            final /* synthetic */ BaseViewHolder b;

            public void onClick(View view) {
                this.b.a(postDataBean, true, false);
            }
        }, new OnClickListener(this) {
            final /* synthetic */ BaseViewHolder b;

            public void onClick(View view) {
                this.b.a(postDataBean, "review");
            }
        }, new PostItemUpDownView.a(this) {
            final /* synthetic */ BaseViewHolder b;

            public void a(boolean z) {
                LikedUsersActivity.a(this.b.d, postDataBean.postId, z, 1);
            }

            public void a(int i, int i2, boolean z) {
                if (z) {
                    if (1 == i) {
                        this.b.e.a(postDataBean.postId, HolderCreator.c(this.b.b), new d.b(this) {
                            final /* synthetic */ AnonymousClass4 a;

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
                        this.b.e.a(postDataBean.postId, 0, HolderCreator.c(this.b.b), new d.a(this) {
                            final /* synthetic */ AnonymousClass4 a;

                            {
                                this.a = r1;
                            }

                            public void a(boolean z, boolean z2, String str) {
                                if (!z) {
                                    g.a(str);
                                }
                            }
                        });
                    }
                }
                postDataBean.likeCount = i2;
                postDataBean.isLiked = i;
                if (1 == i) {
                    h.a(this.b.d, "zy_event_postdetail_page", "帖子顶");
                } else {
                    h.a(this.b.d, "zy_event_postdetail_page", "帖子踩");
                }
            }
        });
        this.operateView.setOnLongClickListener(new OnLongClickListener(this) {
            final /* synthetic */ BaseViewHolder b;

            public boolean onLongClick(View view) {
                this.b.a(postDataBean, false, true);
                return true;
            }
        });
    }

    public void a(PostDataBean postDataBean, String str) {
        PostDetailActivity.a(this.d, PostDataBean.getPostFromPostDataBean(postDataBean), true, postDataBean.member.getTopicRole(), str, EntranceType.PostDetail);
    }

    public void a(final PostDataBean postDataBean, boolean z, boolean z2) {
        ArrayList arrayList;
        SDBottomSheet sDBottomSheet = new SDBottomSheet(this.d, new SDBottomSheet.b(this) {
            final /* synthetic */ BaseViewHolder b;

            @SuppressLint({"SwitchIntDef"})
            public void a(int i) {
                switch (i) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                        if (this.b instanceof VoiceViewHolder) {
                            this.b.c.a(this.b.d, postDataBean, i, HolderCreator.c(this.b.b));
                        } else {
                            this.b.c.a(this.b.d, PostDataBean.getPostFromPostDataBean(postDataBean), i, HolderCreator.c(this.b.b));
                        }
                        PostDataBean postDataBean = postDataBean;
                        postDataBean.shareCount++;
                        this.b.operateView.a(postDataBean);
                        return;
                    case 6:
                        cn.xiaochuankeji.tieba.ui.utils.d.a(postDataBean.content);
                        g.a("已复制");
                        return;
                    case 7:
                        this.b.i(postDataBean);
                        return;
                    case 8:
                        this.b.h(postDataBean);
                        return;
                    case 9:
                        this.b.a(postDataBean.postId);
                        return;
                    case 10:
                        this.b.b(postDataBean, true);
                        return;
                    case 11:
                        this.b.c.c(postDataBean.topic.topicID);
                        return;
                    case 12:
                        this.b.b(postDataBean.postId, postDataBean.topic.topicID);
                        return;
                    case 13:
                        this.b.a(PostDataBean.getPostFromPostDataBean(postDataBean));
                        return;
                    case 17:
                        this.b.b(postDataBean, false);
                        return;
                    case 18:
                        cn.xiaochuankeji.tieba.ui.utils.d.a(HolderCreator.a(postDataBean));
                        g.a("已复制链接");
                        return;
                    default:
                        return;
                }
            }
        });
        ArrayList c = sDBottomSheet.c();
        if (z) {
            arrayList = null;
        } else {
            arrayList = a(postDataBean, z2);
        }
        sDBottomSheet.a(c, arrayList);
        sDBottomSheet.b();
        cn.xiaochuankeji.tieba.a.d.a(postDataBean);
    }

    private ArrayList<SDBottomSheet.c> a(PostDataBean postDataBean, boolean z) {
        ArrayList<SDBottomSheet.c> arrayList = new ArrayList();
        if (z && !TextUtils.isEmpty(postDataBean.content)) {
            arrayList.add(new SDBottomSheet.c(R.drawable.icon_option_copy, "复制文字", 6));
        }
        if (postDataBean.favored == 1) {
            arrayList.add(new SDBottomSheet.c(R.drawable.icon_option_favorite, "取消收藏", 8));
        } else {
            arrayList.add(new SDBottomSheet.c(R.drawable.icon_option_favorite, "收藏", 7));
        }
        if (this.b.equals(PostFromType.FROM_RECOMMEND)) {
            arrayList.add(new SDBottomSheet.c(R.drawable.toast_shield, "屏蔽该话题", 11));
        }
        if (a.g().c() == postDataBean.member.getId()) {
            arrayList.add(new SDBottomSheet.c(R.drawable.icon_option_delete, "删除", 9));
        } else {
            arrayList.add(new SDBottomSheet.c(R.drawable.icon_option_report, "举报", 12));
        }
        if (this.b.equals(PostFromType.FROM_TOPIC)) {
            int a = ((TopicDetailActivity) this.d).a(postDataBean.topic.topicID);
            if (a == 4) {
                if (a.g().c() != postDataBean.member.getId()) {
                    arrayList.add(new SDBottomSheet.c(R.drawable.icon_option_delete, "移除帖子", 10));
                    arrayList.add(new SDBottomSheet.c(R.drawable.toast_limit_post, "限制发帖", 17));
                }
                arrayList.add(new SDBottomSheet.c(R.drawable.icon_option_topic_top, "置顶帖子", 13));
            }
            if (a == 2 && a.g().c() != postDataBean.member.getId()) {
                arrayList.add(new SDBottomSheet.c(R.drawable.icon_option_delete, "移除帖子", 10));
                arrayList.add(new SDBottomSheet.c(R.drawable.toast_limit_post, "限制发帖", 17));
            }
            if (a == 8 && a.g().c() != postDataBean.member.getId()) {
                arrayList.add(new SDBottomSheet.c(R.drawable.icon_option_delete, "移除帖子", 10));
            }
        }
        return arrayList;
    }

    private void h(final PostDataBean postDataBean) {
        this.c.b(postDataBean.postId).a(new rx.b.b<EmptyJson>(this) {
            final /* synthetic */ BaseViewHolder b;

            public /* synthetic */ void call(Object obj) {
                a((EmptyJson) obj);
            }

            public void a(EmptyJson emptyJson) {
                g.a("取消收藏成功");
                postDataBean.favored = 0;
                org.greenrobot.eventbus.c.a().d(new cn.xiaochuankeji.tieba.ui.my.mypost.a(postDataBean.postId));
            }
        }, new rx.b.b<Throwable>(this) {
            final /* synthetic */ BaseViewHolder a;

            {
                this.a = r1;
            }

            public /* synthetic */ void call(Object obj) {
                a((Throwable) obj);
            }

            public void a(Throwable th) {
                if (th instanceof ClientErrorException) {
                    g.a(th.getMessage());
                } else {
                    g.a("网络错误");
                }
            }
        });
    }

    private void a(final long j) {
        new cn.xiaochuankeji.tieba.ui.widget.b.a.a(this.d, "提示", "确定删除帖子吗？").a("取消", null).b("确定", new OnClickListener(this) {
            final /* synthetic */ BaseViewHolder b;

            public void onClick(View view) {
                this.b.c.a(j);
            }
        }).a();
    }

    private void i(final PostDataBean postDataBean) {
        if (cn.xiaochuankeji.tieba.ui.auth.a.a(this.d, "home_tab", 11, 0)) {
            cn.xiaochuankeji.tieba.ui.post.postitem.a aVar = new cn.xiaochuankeji.tieba.ui.post.postitem.a(this.d, new cn.xiaochuankeji.tieba.ui.post.postitem.a.a(this) {
                final /* synthetic */ BaseViewHolder b;

                public void a(boolean z) {
                    if (z) {
                        g.a("收藏成功");
                        postDataBean.favored = 1;
                        return;
                    }
                    g.a("收藏失败");
                }
            });
            aVar.a(postDataBean.postId);
            aVar.e();
        }
    }

    private void b(long j, long j2) {
        Map m = cn.xiaochuankeji.tieba.background.utils.c.a.c().m();
        if (m != null && m.size() != 0) {
            List arrayList = new ArrayList(m.keySet());
            final int[] iArr = new int[1];
            final long j3 = j2;
            final long j4 = j;
            SDCheckSheet sDCheckSheet = new SDCheckSheet(this.d, new SDCheckSheet.a(this) {
                final /* synthetic */ BaseViewHolder d;

                public void a(int i) {
                    if (i == -123) {
                        CustomReportReasonActivity.a(this.d.d, j3, j4, iArr[0], "post");
                    } else {
                        this.d.c.a(j4, i, "post");
                    }
                }
            });
            int i = 0;
            while (i < arrayList.size()) {
                String str = (String) m.get(arrayList.get(i));
                if (str.equals("其他")) {
                    sDCheckSheet.a(str, -123, i == arrayList.size() + -1);
                    iArr[0] = Integer.parseInt((String) arrayList.get(i));
                } else {
                    sDCheckSheet.a(str, Integer.parseInt((String) arrayList.get(i)), i == arrayList.size() + -1);
                }
                i++;
            }
            sDCheckSheet.b();
        }
    }

    private void b(final PostDataBean postDataBean, final boolean z) {
        Map r = cn.xiaochuankeji.tieba.background.utils.c.a.c().r();
        if (r != null && !r.isEmpty()) {
            SDCheckSheet sDCheckSheet = new SDCheckSheet(this.d, new SDCheckSheet.a(this) {
                final /* synthetic */ BaseViewHolder c;

                public void a(int i) {
                    if (z) {
                        this.c.c.a(postDataBean.postId, i);
                    } else {
                        this.c.c.a(postDataBean.member.getId(), postDataBean.topic.topicID, i);
                    }
                }
            });
            List arrayList = new ArrayList(r.keySet());
            for (int i = 0; i < arrayList.size(); i++) {
                boolean z2;
                String str = (String) r.get(arrayList.get(i));
                int parseInt = Integer.parseInt((String) arrayList.get(i));
                if (i == arrayList.size() - 1) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                sDCheckSheet.a(str, parseInt, z2);
            }
            sDCheckSheet.b();
        }
    }

    private void a(Post post) {
        TopicPostTopActivity.a(this.d, post, -1, "");
    }
}
