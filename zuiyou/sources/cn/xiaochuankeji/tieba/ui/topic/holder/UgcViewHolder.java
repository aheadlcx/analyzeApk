package cn.xiaochuankeji.tieba.ui.topic.holder;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.TextView;
import butterknife.BindView;
import c.a.d.a.a;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.data.post.Moment;
import cn.xiaochuankeji.tieba.background.e.b;
import cn.xiaochuankeji.tieba.background.net.XCError;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.json.UgcVideoInfoBean;
import cn.xiaochuankeji.tieba.ui.CustomReportReasonActivity;
import cn.xiaochuankeji.tieba.ui.post.LikedUsersActivity;
import cn.xiaochuankeji.tieba.ui.topic.data.PostDataBean;
import cn.xiaochuankeji.tieba.ui.topic.data.UgcDataBean;
import cn.xiaochuankeji.tieba.ui.topic.data.c;
import cn.xiaochuankeji.tieba.ui.topic.holder.HolderCreator.PostFromType;
import cn.xiaochuankeji.tieba.ui.topic.weight.PostMemberView;
import cn.xiaochuankeji.tieba.ui.topic.weight.PostMemberView.ViewType;
import cn.xiaochuankeji.tieba.ui.ugcvideodetail.UgcVideoActivity;
import cn.xiaochuankeji.tieba.ui.utils.d;
import cn.xiaochuankeji.tieba.ui.widget.MultipleLineEllipsisTextView;
import cn.xiaochuankeji.tieba.ui.widget.SDBottomSheet;
import cn.xiaochuankeji.tieba.ui.widget.SDCheckSheet;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import cn.xiaochuankeji.tieba.ui.widget.updown.PostItemUpDownView;
import com.facebook.drawee.a.a.e;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import java.util.ArrayList;
import org.json.JSONObject;

public class UgcViewHolder extends BaseViewHolder {
    @BindView
    View coverView;
    @BindView
    TextView playNumber;
    @BindView
    TextView topicName;
    @BindView
    MultipleLineEllipsisTextView ugcDescription;
    @BindView
    WebImageView videoCover;
    @BindView
    WebImageView videoCoverBackground;

    UgcViewHolder(View view, Activity activity, PostFromType postFromType) {
        super(view, activity, postFromType);
    }

    protected PostDataBean b(c cVar) {
        if (cVar != null && (cVar instanceof UgcDataBean)) {
            UgcDataBean ugcDataBean = (UgcDataBean) cVar;
            if (!(ugcDataBean.ugcVideos == null || ugcDataBean.ugcVideos.isEmpty() || ugcDataBean.ugcVideos.get(0) == null)) {
                a(ugcDataBean);
                b(ugcDataBean);
                e(ugcDataBean);
            }
        }
        return null;
    }

    @SuppressLint({"SetTextI18n"})
    private void a(final UgcDataBean ugcDataBean) {
        if (TextUtils.isEmpty(ugcDataBean.title)) {
            this.ugcDescription.setVisibility(8);
        } else {
            this.ugcDescription.setVisibility(0);
            this.ugcDescription.a(ugcDataBean.title, this.a, ugcDataBean.getId(), a.a().a((int) R.color.CT_4), 2);
            this.ugcDescription.setOnExpandableTextViewListener(new MultipleLineEllipsisTextView.c(this) {
                final /* synthetic */ UgcViewHolder b;

                public void onClick() {
                    this.b.f(ugcDataBean);
                }

                public void a() {
                    this.b.a(ugcDataBean, false, true);
                }
            });
        }
        this.topicName.setText(ugcDataBean.topic.topicName);
        UgcVideoInfoBean ugcVideoInfoBean = (UgcVideoInfoBean) ugcDataBean.ugcVideos.get(0);
        String a = cn.xiaochuankeji.tieba.background.utils.d.a.a("/img/view/id/", (long) ugcVideoInfoBean.img.id, "/sz/360");
        this.videoCoverBackground.setController(((e) com.facebook.drawee.a.a.c.a().b(ImageRequestBuilder.a(Uri.parse(a)).a(new com.facebook.imagepipeline.j.a(50)).n())).k());
        this.videoCover.setImageURI(a);
        this.playNumber.setVisibility(ugcVideoInfoBean.plays == 0 ? 8 : 0);
        this.playNumber.setText("" + ugcVideoInfoBean.plays);
        this.postGodReview.setVisibility(8);
        this.coverView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ UgcViewHolder b;

            public void onClick(View view) {
                this.b.f(ugcDataBean);
            }
        });
        this.coverView.setOnLongClickListener(new OnLongClickListener(this) {
            final /* synthetic */ UgcViewHolder b;

            public boolean onLongClick(View view) {
                this.b.a(ugcDataBean, false, true);
                return true;
            }
        });
        this.itemView.setOnLongClickListener(new OnLongClickListener(this) {
            final /* synthetic */ UgcViewHolder b;

            public boolean onLongClick(View view) {
                this.b.a(ugcDataBean, false, true);
                return true;
            }
        });
    }

    private void b(final UgcDataBean ugcDataBean) {
        this.postMemberView.a(ugcDataBean.member, HolderCreator.a(this.b) ? 0 : ugcDataBean.createTime, false, HolderCreator.a(this.b, ugcDataBean.member.isFollowed(), ugcDataBean.hasUpdate));
        this.postMemberView.setOnMemberViewClickListener(new PostMemberView.a(this) {
            final /* synthetic */ UgcViewHolder b;

            public void a(ViewType viewType) {
                switch (viewType) {
                    case MORE:
                        this.b.a(ugcDataBean, false, false);
                        return;
                    case FOLLOW:
                        this.b.c(ugcDataBean);
                        return;
                    case CANCEL_FOLLOW:
                        this.b.d(ugcDataBean);
                        return;
                    default:
                        return;
                }
            }

            public void a() {
                this.b.a(ugcDataBean.member.getId(), ugcDataBean.getId());
            }

            public void b() {
                this.b.a(ugcDataBean, false, true);
            }

            public void c() {
                this.b.f(ugcDataBean);
            }

            public void d() {
            }
        });
    }

    private void c(final UgcDataBean ugcDataBean) {
        new b(ugcDataBean.member.getId(), null, null, new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
            final /* synthetic */ UgcViewHolder b;

            public /* synthetic */ void onResponse(Object obj, Object obj2) {
                a((JSONObject) obj, obj2);
            }

            public void a(JSONObject jSONObject, Object obj) {
                ugcDataBean.member.setFollowStatus(1);
                ugcDataBean.hasUpdate = true;
                org.greenrobot.eventbus.c.a().d(new cn.xiaochuankeji.tieba.ui.topic.data.b(ugcDataBean.member.getId(), true));
            }
        }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
            final /* synthetic */ UgcViewHolder a;

            {
                this.a = r1;
            }

            public void onErrorResponse(XCError xCError, Object obj) {
                g.a(xCError.getMessage());
            }
        }).execute();
    }

    private void d(final UgcDataBean ugcDataBean) {
        new cn.xiaochuankeji.tieba.background.e.a(ugcDataBean.member.getId(), null, new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
            final /* synthetic */ UgcViewHolder b;

            public /* synthetic */ void onResponse(Object obj, Object obj2) {
                a((JSONObject) obj, obj2);
            }

            public void a(JSONObject jSONObject, Object obj) {
                ugcDataBean.member.setFollowStatus(0);
                ugcDataBean.hasUpdate = true;
                org.greenrobot.eventbus.c.a().d(new cn.xiaochuankeji.tieba.ui.topic.data.b(ugcDataBean.member.getId(), false));
            }
        }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
            final /* synthetic */ UgcViewHolder a;

            {
                this.a = r1;
            }

            public void onErrorResponse(XCError xCError, Object obj) {
                g.a(xCError.getMessage());
            }
        }).execute();
    }

    private void e(final UgcDataBean ugcDataBean) {
        this.operateView.a(ugcDataBean, new OnClickListener(this) {
            final /* synthetic */ UgcViewHolder b;

            public void onClick(View view) {
                this.b.a(ugcDataBean, true, false);
            }
        }, new OnClickListener(this) {
            final /* synthetic */ UgcViewHolder b;

            public void onClick(View view) {
                this.b.f(ugcDataBean);
            }
        }, new PostItemUpDownView.a(this) {
            final /* synthetic */ UgcViewHolder b;

            public void a(boolean z) {
                if (ugcDataBean != null) {
                    LikedUsersActivity.a(this.b.d, ugcDataBean.getId(), z, 3, HolderCreator.c(this.b.b), 0);
                }
            }

            public void a(int i, int i2, boolean z) {
                boolean z2 = true;
                ugcDataBean.likeCount = (long) i2;
                ugcDataBean.isLiked = i;
                if (z) {
                    HolderOperator holderOperator = this.b.c;
                    long id = ugcDataBean.getId();
                    if (i != 1) {
                        z2 = false;
                    }
                    holderOperator.a(id, z2, HolderCreator.c(this.b.b));
                }
            }
        });
        this.operateView.setOnLongClickListener(new OnLongClickListener(this) {
            final /* synthetic */ UgcViewHolder b;

            public boolean onLongClick(View view) {
                this.b.a(ugcDataBean, false, true);
                return true;
            }
        });
    }

    private void a(final UgcDataBean ugcDataBean, boolean z, boolean z2) {
        ArrayList arrayList;
        final UgcVideoInfoBean ugcVideoInfoBean = (UgcVideoInfoBean) ugcDataBean.ugcVideos.get(0);
        SDBottomSheet sDBottomSheet = new SDBottomSheet(this.d, new SDBottomSheet.b(this) {
            final /* synthetic */ UgcViewHolder c;

            @SuppressLint({"SwitchIntDef"})
            public void a(int i) {
                switch (i) {
                    case 1:
                    case 2:
                    case 3:
                    case 5:
                        this.c.c.a(this.c.d, ugcDataBean, i);
                        return;
                    case 6:
                        d.a(ugcDataBean.eventDesc);
                        g.a("已复制");
                        return;
                    case 9:
                        this.c.a(ugcVideoInfoBean.id);
                        return;
                    case 12:
                        this.c.b(ugcVideoInfoBean.id, ugcDataBean.getId());
                        return;
                    case 18:
                        d.a("#最右#分享一条有趣的内容给你，不好看算我输。请戳链接>>" + cn.xiaochuankeji.tieba.background.utils.d.a.b(ugcDataBean.getId(), ugcVideoInfoBean.id));
                        g.a("复制成功");
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
            arrayList = a(ugcDataBean, z2);
        }
        sDBottomSheet.a(c, arrayList);
        sDBottomSheet.b();
    }

    private ArrayList<SDBottomSheet.c> a(UgcDataBean ugcDataBean, boolean z) {
        ArrayList<SDBottomSheet.c> arrayList = new ArrayList();
        if (z && !TextUtils.isEmpty(ugcDataBean.title)) {
            arrayList.add(new SDBottomSheet.c(R.drawable.icon_option_copy, "复制文字", 6));
        }
        if (cn.xiaochuankeji.tieba.background.a.g().c() == ugcDataBean.member.getId()) {
            arrayList.add(new SDBottomSheet.c(R.drawable.icon_option_delete, "删除", 9));
        } else {
            arrayList.add(new SDBottomSheet.c(R.drawable.icon_option_report, "举报", 12));
        }
        return arrayList;
    }

    private void f(UgcDataBean ugcDataBean) {
        UgcVideoActivity.a(this.d, (UgcVideoInfoBean) ugcDataBean.ugcVideos.get(0), false, "index", (Moment) ugcDataBean);
    }

    private void a(final long j) {
        new cn.xiaochuankeji.tieba.ui.widget.b.a.a(this.d, "提示", "确定删除跟拍帖子吗？").b("确定", new OnClickListener(this) {
            final /* synthetic */ UgcViewHolder b;

            public void onClick(View view) {
                this.b.c.d(j);
            }
        }).a("取消", null).a();
    }

    private void b(long j, long j2) {
        final ArrayList s = cn.xiaochuankeji.tieba.background.utils.c.a.c().s();
        if (s != null && s.size() != 0) {
            final long j3 = j;
            final long j4 = j2;
            SDCheckSheet sDCheckSheet = new SDCheckSheet(this.d, new SDCheckSheet.a(this) {
                final /* synthetic */ UgcViewHolder d;

                public void a(int i) {
                    if (((cn.xiaochuankeji.tieba.background.utils.c.c) s.get(i)).b.equals("其他")) {
                        CustomReportReasonActivity.a(this.d.d, j3, j4, ((cn.xiaochuankeji.tieba.background.utils.c.c) s.get(i)).a, "post");
                    } else {
                        this.d.c.a(j4, ((cn.xiaochuankeji.tieba.background.utils.c.c) s.get(i)).a, "ugcvideo");
                    }
                }
            });
            for (int i = 0; i < s.size(); i++) {
                boolean z;
                String str = ((cn.xiaochuankeji.tieba.background.utils.c.c) s.get(i)).b;
                if (i == s.size() - 1) {
                    z = true;
                } else {
                    z = false;
                }
                sDCheckSheet.a(str, i, z);
            }
            sDCheckSheet.b();
        }
    }
}
