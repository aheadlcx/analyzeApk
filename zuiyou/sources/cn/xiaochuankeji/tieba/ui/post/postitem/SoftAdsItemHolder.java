package cn.xiaochuankeji.tieba.ui.post.postitem;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.support.v4.view.PointerIconCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import butterknife.BindView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.a.d;
import cn.xiaochuankeji.tieba.background.ad.AdvertismentSoftBean;
import cn.xiaochuankeji.tieba.background.ad.AdvertismentSoftBean.Advert;
import cn.xiaochuankeji.tieba.background.ad.AdvertismentSoftBean.Img;
import cn.xiaochuankeji.tieba.background.ad.AdvertismentSoftBean.Video;
import cn.xiaochuankeji.tieba.ui.mediabrowse.local.LocalVideoPlayActivity;
import cn.xiaochuankeji.tieba.ui.post.a.a;
import cn.xiaochuankeji.tieba.ui.post.a.b;
import cn.xiaochuankeji.tieba.ui.recommend.widget.ResizeMultiDraweeView;
import cn.xiaochuankeji.tieba.ui.topic.data.PostDataBean;
import cn.xiaochuankeji.tieba.ui.topic.holder.HolderCreator.PostFromType;
import cn.xiaochuankeji.tieba.ui.topic.holder.PostViewHolder;
import cn.xiaochuankeji.tieba.ui.topic.weight.PostMemberView;
import cn.xiaochuankeji.tieba.ui.topic.weight.PostMemberView.ViewType;
import cn.xiaochuankeji.tieba.ui.widget.k;
import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.greenrobot.eventbus.c;

public class SoftAdsItemHolder extends PostViewHolder {
    private Advert e;
    private AdvertismentSoftBean f;
    @BindView
    PostMemberView postMemberView;

    public SoftAdsItemHolder(Activity activity, PostFromType postFromType, ViewGroup viewGroup) {
        super(LayoutInflater.from(activity).inflate(R.layout.layout_soft_post_item, viewGroup, false), activity, postFromType, viewGroup);
    }

    public void a(AdvertismentSoftBean advertismentSoftBean) {
        this.f = advertismentSoftBean;
        this.e = advertismentSoftBean.advert;
    }

    public void a(final PostDataBean postDataBean) {
        this.netLinkView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SoftAdsItemHolder b;

            public void onClick(View view) {
                try {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    intent.setData(Uri.parse(postDataBean.webPage.url));
                    this.b.itemView.getContext().startActivity(intent);
                    a.a(this.b.f, 1006);
                } catch (Exception e) {
                }
            }
        });
        Object obj = null;
        if (this.f.imgs != null && this.f.imgs.size() > 0) {
            obj = String.valueOf(((Img) this.f.imgs.get(0)).id);
        }
        if (!TextUtils.isEmpty(obj)) {
            this.images.setOnItemClickListener(new ResizeMultiDraweeView.a(this) {
                final /* synthetic */ SoftAdsItemHolder c;

                public void a(int i, Rect rect) {
                    Video videoInfo = this.c.f.getVideoInfo(obj);
                    if (videoInfo != null) {
                        LocalVideoPlayActivity.a(this.c.itemView.getContext(), videoInfo.url, this.c.f);
                        a.a(this.c.f, (int) PointerIconCompat.TYPE_VERTICAL_TEXT);
                    }
                }

                public void a() {
                    this.c.a(postDataBean, false, true);
                }
            });
        }
    }

    protected void b(final PostDataBean postDataBean) {
        if (postDataBean.member != null) {
            this.postMemberView.a(postDataBean.member, 0, false, this.e.label, ViewType.DELETE);
            this.postMemberView.setOnMemberViewClickListener(new PostMemberView.a(this) {
                final /* synthetic */ SoftAdsItemHolder b;

                public void a(ViewType viewType) {
                    switch (viewType) {
                        case DELETE:
                            this.b.a(this.b.e, postDataBean.c_type, postDataBean.postType);
                            return;
                        default:
                            return;
                    }
                }

                public void a() {
                    this.b.a(postDataBean.member.getId(), postDataBean.postId);
                    d.a(postDataBean);
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
    }

    private void a(final Advert advert, final int i, final int i2) {
        if (advert == null || advert.filters == null || advert.filters.size() == 0) {
            c.a().d(new b(advert));
            return;
        }
        k kVar = new k(this.itemView.getContext());
        kVar.a(AdvertismentSoftBean.createTediumReason(advert), new k.b(this) {
            final /* synthetic */ SoftAdsItemHolder d;

            public void a(ArrayList<String> arrayList, String str) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("type", Integer.valueOf(i2));
                jSONObject.put("adid", Long.valueOf(advert.adid));
                jSONObject.put("c_type", Integer.valueOf(i));
                List arrayList2 = new ArrayList();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    try {
                        arrayList2.add(Integer.valueOf((String) it.next()));
                    } catch (Exception e) {
                    }
                }
                jSONObject.put("reasons", arrayList2);
                new cn.xiaochuankeji.tieba.api.ad.a().a(jSONObject).g();
                c.a().d(new b(advert));
            }
        });
        kVar.show();
    }
}
