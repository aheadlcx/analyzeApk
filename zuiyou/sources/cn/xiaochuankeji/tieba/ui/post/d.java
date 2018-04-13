package cn.xiaochuankeji.tieba.ui.post;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import cn.htjyb.ui.a;
import cn.xiaochuankeji.tieba.background.ad.AdvertismentBean;
import cn.xiaochuankeji.tieba.background.ad.AdvertismentSoftBean;
import cn.xiaochuankeji.tieba.background.ad.GDTAdvertisment;
import cn.xiaochuankeji.tieba.background.data.post.AbstractPost;
import cn.xiaochuankeji.tieba.background.data.post.Moment;
import cn.xiaochuankeji.tieba.background.data.post.Post;
import cn.xiaochuankeji.tieba.background.post.PostRecommendQueryList;
import cn.xiaochuankeji.tieba.background.post.m;
import cn.xiaochuankeji.tieba.ui.homepage.HomePageActivity;
import cn.xiaochuankeji.tieba.ui.homepage.g;
import cn.xiaochuankeji.tieba.ui.post.postitem.AdsItemHolder;
import cn.xiaochuankeji.tieba.ui.post.postitem.SoftAdsItemHolder;
import cn.xiaochuankeji.tieba.ui.post.postitem.b;
import cn.xiaochuankeji.tieba.ui.post.postitem.c;
import cn.xiaochuankeji.tieba.ui.post.postitem.e;
import cn.xiaochuankeji.tieba.ui.post.postitem.h;
import cn.xiaochuankeji.tieba.ui.topic.TopicDetailActivity;
import cn.xiaochuankeji.tieba.ui.topic.data.PostDataBean;
import cn.xiaochuankeji.tieba.ui.topic.holder.HolderCreator.PostFromType;
import cn.xiaochuankeji.tieba.ui.widget.customtv.ExpandableTextView.f;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class d extends BaseAdapter implements a {
    private final Context a;
    private final m b;
    private final HashMap<Long, Boolean> c;
    private final HashMap<Long, HashMap<Long, f>> d;
    private final HashMap<Long, b> e;
    private HashSet<a> f = new HashSet();
    private boolean g = false;

    public d(Context context, m mVar) {
        this.a = context;
        this.b = mVar;
        this.c = new HashMap();
        this.d = new HashMap();
        this.e = new HashMap();
    }

    public void a() {
        this.g = true;
    }

    public int a(long j) {
        b bVar = (b) this.e.get(Long.valueOf(j));
        return bVar == null ? 0 : bVar.a;
    }

    public void a(long j, boolean z) {
        b bVar = (b) this.e.get(Long.valueOf(j));
        if (bVar == null) {
            return;
        }
        if (z) {
            bVar.f--;
            bVar.a--;
            return;
        }
        bVar.f++;
        bVar.a++;
    }

    public int getCount() {
        if (this.b == null) {
            return 0;
        }
        int loadMoreItemIndex = this.b.getLoadMoreItemIndex();
        int itemCount = this.b.itemCount();
        if (loadMoreItemIndex < 0 || loadMoreItemIndex > itemCount) {
            return itemCount;
        }
        return itemCount + 1;
    }

    public int getViewTypeCount() {
        return 10;
    }

    public int getItemViewType(int i) {
        int loadMoreItemIndex = this.b.getLoadMoreItemIndex();
        if (i == loadMoreItemIndex) {
            return 3;
        }
        AbstractPost abstractPost;
        if (loadMoreItemIndex < 0 || i <= loadMoreItemIndex) {
            abstractPost = (AbstractPost) this.b.itemAt(i);
        } else {
            abstractPost = (AbstractPost) this.b.itemAt(i - 1);
        }
        if (abstractPost.isRealPost()) {
            loadMoreItemIndex = ((Post) abstractPost)._imgList.size();
            if (loadMoreItemIndex == 0) {
                return 0;
            }
            if (loadMoreItemIndex == 1) {
                return 1;
            }
            return 2;
        } else if (abstractPost.isUgcPost()) {
            return 6;
        } else {
            if (abstractPost.isGDTAds()) {
                return 7;
            }
            if (abstractPost.isApiHardAd()) {
                return 8;
            }
            return abstractPost.isApiSoftAd() ? 9 : 0;
        }
    }

    public Object getItem(int i) {
        int loadMoreItemIndex = this.b.getLoadMoreItemIndex();
        if (i == loadMoreItemIndex) {
            return null;
        }
        if (loadMoreItemIndex < 0 || i <= loadMoreItemIndex) {
            return (AbstractPost) this.b.itemAt(i);
        }
        return (AbstractPost) this.b.itemAt(i - 1);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        int itemViewType = getItemViewType(i);
        if (itemViewType == 3) {
            return view == null ? new g(this.a) : view;
        } else {
            int i2;
            int loadMoreItemIndex = this.b.getLoadMoreItemIndex();
            if (loadMoreItemIndex < 0 || i <= loadMoreItemIndex) {
                i2 = i;
            } else {
                i2 = i - 1;
            }
            AbstractPost abstractPost = (AbstractPost) this.b.itemAt(i2);
            boolean z = loadMoreItemIndex >= 0 && i == loadMoreItemIndex - 1;
            if (abstractPost.isRealPost()) {
                e fVar;
                HashMap hashMap;
                b bVar;
                Post post = (Post) abstractPost;
                if (itemViewType == 2) {
                    fVar = (view == null || !(view.getTag() instanceof cn.xiaochuankeji.tieba.ui.post.postitem.f)) ? new cn.xiaochuankeji.tieba.ui.post.postitem.f(this.a, post._imgList.size()) : (cn.xiaochuankeji.tieba.ui.post.postitem.f) view.getTag();
                } else if (itemViewType == 1) {
                    if (view == null || !(view.getTag() instanceof h)) {
                        fVar = new h(this.a);
                    } else {
                        h hVar = (h) view.getTag();
                    }
                } else if (view == null || !(view.getTag() instanceof cn.xiaochuankeji.tieba.ui.post.postitem.g)) {
                    fVar = new cn.xiaochuankeji.tieba.ui.post.postitem.g(this.a);
                } else {
                    cn.xiaochuankeji.tieba.ui.post.postitem.g gVar = (cn.xiaochuankeji.tieba.ui.post.postitem.g) view.getTag();
                }
                if ((this.b instanceof PostRecommendQueryList) && fVar != null) {
                    fVar.a(((PostRecommendQueryList) this.b).e());
                }
                HashMap hashMap2 = (HashMap) this.d.get(Long.valueOf(post._ID));
                if (hashMap2 == null) {
                    hashMap = new HashMap();
                    this.d.put(Long.valueOf(post._ID), hashMap);
                } else {
                    hashMap = hashMap2;
                }
                b bVar2 = (b) this.e.get(Long.valueOf(post._ID));
                if (bVar2 == null) {
                    bVar = new b();
                    this.e.put(Long.valueOf(post._ID), bVar);
                } else {
                    bVar = bVar2;
                }
                fVar.a(post, this.b, i2, z, this.c, hashMap, bVar);
                fVar.n();
                if (this.g) {
                    fVar.b();
                }
                fVar.d(i);
                view = fVar.i();
                view.setTag(fVar);
                this.f.add(fVar);
                return view;
            } else if (abstractPost.isUgcPost()) {
                c cVar;
                Moment moment = (Moment) abstractPost;
                if (view == null || !(view.getTag() instanceof c)) {
                    c cVar2 = new c(this.a, null, this.a instanceof TopicDetailActivity ? "topic" : "index");
                    view = cVar2.d();
                    view.setTag(cVar2);
                    cVar = cVar2;
                } else {
                    cVar = (c) view.getTag();
                }
                cVar.a(moment);
                if (!(this.a instanceof HomePageActivity)) {
                    cVar.b();
                }
                this.f.add(cVar);
                return view;
            } else if (abstractPost.isGDTAds()) {
                GDTAdvertisment gDTAdvertisment = (GDTAdvertisment) abstractPost;
                if (view == null || !(view.getTag() instanceof AdsItemHolder)) {
                    r1 = new AdsItemHolder(this.a, null);
                    view = r1.a();
                    view.setTag(r1);
                } else {
                    r1 = (AdsItemHolder) view.getTag();
                }
                r1.a(gDTAdvertisment.mAD, gDTAdvertisment.extraInfo);
                return view;
            } else if (abstractPost.isApiHardAd()) {
                AdvertismentBean advertismentBean = (AdvertismentBean) abstractPost;
                if (view == null || !(view.getTag() instanceof AdsItemHolder)) {
                    r1 = new AdsItemHolder(this.a, null);
                    view = r1.a();
                    view.setTag(r1);
                } else {
                    r1 = (AdsItemHolder) view.getTag();
                }
                r1.a(advertismentBean);
                return view;
            } else if (!abstractPost.isApiSoftAd()) {
                return view;
            } else {
                AdvertismentSoftBean advertismentSoftBean = (AdvertismentSoftBean) abstractPost;
                if (advertismentSoftBean == null) {
                    return null;
                }
                SoftAdsItemHolder softAdsItemHolder;
                if (view == null || !(view.getTag() instanceof SoftAdsItemHolder)) {
                    SoftAdsItemHolder softAdsItemHolder2 = new SoftAdsItemHolder((Activity) this.a, PostFromType.FROM_RECOMMEND, null);
                    view = softAdsItemHolder2.itemView;
                    view.setTag(softAdsItemHolder2);
                    softAdsItemHolder = softAdsItemHolder2;
                } else {
                    softAdsItemHolder = (SoftAdsItemHolder) view.getTag();
                }
                softAdsItemHolder.a(advertismentSoftBean);
                PostDataBean ConvertToPostDataBean = AdvertismentSoftBean.ConvertToPostDataBean(advertismentSoftBean);
                softAdsItemHolder.a((cn.xiaochuankeji.tieba.ui.topic.data.c) ConvertToPostDataBean);
                softAdsItemHolder.a(ConvertToPostDataBean);
                return view;
            }
        }
    }

    public void c() {
        Iterator it = this.f.iterator();
        while (it.hasNext()) {
            ((a) it.next()).c();
        }
    }
}
