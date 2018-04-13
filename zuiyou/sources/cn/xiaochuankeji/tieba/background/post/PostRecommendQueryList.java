package cn.xiaochuankeji.tieba.background.post;

import cn.htjyb.b.a.b.b;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuankeji.tieba.background.AppController;
import cn.xiaochuankeji.tieba.background.ad.PostAdExtraInfo;
import cn.xiaochuankeji.tieba.background.data.ServerVideo;
import cn.xiaochuankeji.tieba.background.data.post.AbstractPost;
import cn.xiaochuankeji.tieba.background.data.post.Moment;
import cn.xiaochuankeji.tieba.background.data.post.Post;
import cn.xiaochuankeji.tieba.background.data.post.Post.PostVote;
import cn.xiaochuankeji.tieba.background.data.post.Subject;
import cn.xiaochuankeji.tieba.background.utils.h;
import cn.xiaochuankeji.tieba.network.custom.exception.ClientErrorException;
import cn.xiaochuankeji.tieba.ui.mediabrowse.EntranceType;
import cn.xiaochuankeji.tieba.ui.post.a.c;
import com.alibaba.fastjson.JSON;
import com.iflytek.cloud.SpeechConstant;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import rx.d;
import rx.d$a;
import rx.j;

public class PostRecommendQueryList extends m implements b {
    public int a;
    public String b;
    private boolean c;
    private String d;
    private int e;
    private int f;
    private int g;
    private String h;
    private String i;
    private RecommendType j;
    private HashMap<Long, Post> k;
    private String l;
    private PostAdExtraInfo m;
    private String n;

    public enum RecommendType {
        INDEX_IMGTXT,
        INDEX_RECOMMEND,
        TAB_VIDEO,
        OTHER
    }

    public interface a {
        void a();

        void b();
    }

    PostRecommendQueryList(RecommendType recommendType, String str) {
        this.c = false;
        this.f = -1;
        this.g = 0;
        this.a = 24;
        this.k = new HashMap();
        this.b = "key_all_rec_visible_pos";
        this.j = recommendType;
        if (RecommendType.INDEX_RECOMMEND == recommendType) {
            this.h = SpeechConstant.PLUS_LOCAL_ALL;
            this.a = 24;
            this.i = "post_recommend_list_new.dat";
            this.b = "key_all_rec_visible_pos";
        } else if (RecommendType.INDEX_IMGTXT == recommendType) {
            this.h = "imgtxt";
            this.a = 24;
            this.i = "index_imgtxt_post_list_new.dat";
            this.b = "key_image_rec_visible_pos";
        } else if (RecommendType.TAB_VIDEO == recommendType) {
            this.h = "video";
            this.a = 12;
            this.i = "video_post_recommend_list_new.dat";
            this.b = "key_video_rec_visible_pos";
        } else {
            this.h = str;
            this.a = 24;
            this.i = str + "_new.dat";
            this.b = str + "_position";
        }
        registerOnQueryFinishListener(this);
    }

    PostRecommendQueryList(String str, String str2) {
        this.c = false;
        this.f = -1;
        this.g = 0;
        this.a = 24;
        this.k = new HashMap();
        this.b = "key_all_rec_visible_pos";
        this.j = RecommendType.OTHER;
        this.h = str2;
        this.a = 24;
        this.i = str2 + "_new.dat";
        this.b = str2 + "_position";
        registerOnQueryFinishListener(this);
    }

    public void a(final a aVar) {
        d.b(new d$a<JSONObject>(this) {
            final /* synthetic */ PostRecommendQueryList a;

            {
                this.a = r1;
            }

            public /* synthetic */ void call(Object obj) {
                a((j) obj);
            }

            public void a(j<? super JSONObject> jVar) {
                JSONObject a = cn.htjyb.c.a.b.a(new File(this.a.f()), AppController.kDataCacheCharsetUTF8.name());
                if (a != null) {
                    this.a.f = a.optInt("key_recommend_post_loadmore_index", -1);
                    Iterator it = this.a._items.iterator();
                    while (it.hasNext()) {
                        AbstractPost abstractPost = (AbstractPost) it.next();
                        if (abstractPost.isRealPost()) {
                            Post post = (Post) abstractPost;
                            this.a.k.put(Long.valueOf(post._ID), post);
                        }
                    }
                    jVar.onNext(a);
                } else {
                    jVar.onError(new ClientErrorException(0, "缓存为空", null));
                }
                jVar.onCompleted();
            }
        }).b(rx.f.a.c()).a(rx.a.b.a.a()).b(new j<JSONObject>(this) {
            final /* synthetic */ PostRecommendQueryList b;

            public /* synthetic */ void onNext(Object obj) {
                a((JSONObject) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                aVar.a();
            }

            public void a(JSONObject jSONObject) {
                this.b.a(jSONObject);
                if (this.b._items.isEmpty()) {
                    aVar.a();
                } else {
                    aVar.b();
                }
                this.b.b();
            }
        });
    }

    public int getLoadMoreItemIndex() {
        if (cn.xiaochuankeji.tieba.d.a.b() == cn.xiaochuankeji.tieba.d.a.b) {
            return -1;
        }
        return this.f;
    }

    public void a() {
        this.g = 1;
    }

    public void a(String str) {
        this.l = str;
    }

    public void b() {
        cn.xiaochuankeji.tieba.background.a.p().b().execute(new Runnable(this) {
            final /* synthetic */ PostRecommendQueryList a;

            {
                this.a = r1;
            }

            public void run() {
                try {
                    int itemCount;
                    JSONObject jSONObject = new JSONObject();
                    JSONArray jSONArray = new JSONArray();
                    if (this.a.itemCount() < 200) {
                        itemCount = this.a.itemCount();
                    } else {
                        itemCount = 200;
                    }
                    int i = 0;
                    while (i < itemCount) {
                        AbstractPost abstractPost = (AbstractPost) this.a.itemAt(i);
                        if (!(abstractPost == null || abstractPost.getCtype() > 3 || ((AbstractPost) this.a.itemAt(i)).serializeTo() == null)) {
                            jSONArray.put(i, ((AbstractPost) this.a.itemAt(i)).serializeTo());
                        }
                        i++;
                    }
                    if (jSONArray.length() > 0) {
                        jSONObject.put("list", jSONArray);
                        jSONObject.put("key_recommend_post_loadmore_index", this.a.f);
                        cn.htjyb.c.a.b.a(jSONObject, new File(this.a.f()), AppController.kDataCacheCharsetUTF8.name());
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
    }

    private String f() {
        return cn.xiaochuankeji.tieba.background.a.e().r() + this.i;
    }

    protected void handleQuerySuccResult(JSONObject jSONObject) {
        this.d = jSONObject.optString("tips");
        this.e = 0;
        g();
        JSONArray optJSONArray = jSONObject.optJSONArray("list");
        Collection arrayList = new ArrayList();
        boolean isQueryMore = isQueryMore();
        if (optJSONArray == null || optJSONArray.length() == 0) {
            this.c = true;
            return;
        }
        this.c = false;
        if (!isQueryMore && cn.xiaochuankeji.tieba.d.a.b() == cn.xiaochuankeji.tieba.d.a.b) {
            this._items.clear();
            this.k.clear();
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                AbstractPost create;
                if (optJSONObject.optInt("AbstractPost_classType", 0) > 0) {
                    create = AbstractPost.create(optJSONObject);
                } else {
                    create = AbstractPost.createByCtype(optJSONObject);
                }
                if (!(create == null || (create instanceof Subject))) {
                    if (create.isRealPost() && (create instanceof Post)) {
                        Post post = (Post) create;
                        if (!(a(post._ID) || post.postType == 3)) {
                            arrayList.add(create);
                            this.k.put(Long.valueOf(post._ID), post);
                        }
                    } else if (!arrayList.contains(create)) {
                        arrayList.add(create);
                    }
                }
            }
        }
        this.e = arrayList.size();
        if (!arrayList.isEmpty()) {
            if (isQueryMore) {
                this._items.addAll(arrayList);
            } else {
                if (this._items.size() != 0) {
                    this.f = arrayList.size();
                }
                this._items.addAll(0, arrayList);
            }
        }
        if (jSONObject.has(PushConstants.EXTRA)) {
            try {
                optJSONObject = jSONObject.getJSONObject(PushConstants.EXTRA);
                if (optJSONObject != null && optJSONObject.has("ad")) {
                    this.m = (PostAdExtraInfo) JSON.parseObject(optJSONObject.getJSONObject("ad").toString(), PostAdExtraInfo.class);
                    c.a().a(this.m);
                    c.a().a(1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        notifyListUpdate();
        b();
    }

    private void g() {
        b.a().a(System.currentTimeMillis(), this.h + "_refresh_time");
    }

    public void refresh() {
        super.refresh();
        if (RecommendType.INDEX_RECOMMEND != this.j) {
            return;
        }
        if (cn.xiaochuankeji.tieba.ui.homepage.d.a) {
            cn.xiaochuankeji.tieba.ui.homepage.d.a = false;
        } else {
            h.a(BaseApplication.getAppContext(), "zy_event_homepage_tab_recommend", "下拉刷帖次数");
        }
    }

    protected cn.htjyb.netlib.b getHttpEngine() {
        return cn.xiaochuankeji.tieba.background.a.d();
    }

    protected String getQueryUrl() {
        return cn.xiaochuankeji.tieba.background.utils.d.a.b("/index/recommend");
    }

    protected AbstractPost parseItem(JSONObject jSONObject) {
        AbstractPost create;
        if (jSONObject.optInt("AbstractPost_classType", 0) > 0) {
            create = AbstractPost.create(jSONObject);
        } else {
            create = AbstractPost.createByCtype(jSONObject);
        }
        if (create instanceof Subject) {
            return null;
        }
        return create;
    }

    protected void fillJSONObjectHeaderInfo(JSONObject jSONObject) {
        cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
        if (isQueryMore() && RecommendType.INDEX_RECOMMEND == this.j) {
            h.a(BaseApplication.getAppContext(), "zy_event_homepage_tab_recommend", "上拉刷帖次数");
        }
    }

    public boolean hasMore() {
        if (this._items.size() == 0) {
            return false;
        }
        return true;
    }

    protected void fillQueryBody(JSONObject jSONObject) throws JSONException {
        super.fillQueryBody(jSONObject);
        String str = isQueryMore() ? "up" : "down";
        if (this.l != null) {
            str = this.l;
            this.l = null;
        }
        this.n = str;
        jSONObject.put("filter", this.h);
        jSONObject.put("tab", "rec");
        jSONObject.put("direction", str);
        jSONObject.put("auto", this.g);
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(1);
        jSONArray.put(3);
        jSONArray.put(7);
        jSONArray.put(8);
        jSONObject.put("c_types", jSONArray);
        h();
    }

    private void h() {
        if (this.h.equals(SpeechConstant.PLUS_LOCAL_ALL)) {
            h.a(BaseApplication.getAppContext(), "zy_event_homepage_tab_recommend", "总刷帖次数");
        } else if (this.h.equals("imgtxt")) {
            h.a(BaseApplication.getAppContext(), "zy_event_homepage_tab_imgtxt", "总刷帖次数");
        } else if (this.h.equals("video")) {
            h.a(BaseApplication.getAppContext(), "zy_event_homepage_tab_video", "总刷帖次数");
        }
    }

    public String c() {
        return this.d;
    }

    public int d() {
        return this.e;
    }

    protected long getQueryMoreOffset() {
        return 0;
    }

    public void a(boolean z, boolean z2, String str) {
        this.g = 0;
    }

    public void remove(AbstractPost abstractPost) {
        if (abstractPost.isRealPost()) {
            Post post = (Post) abstractPost;
            Post post2 = (Post) this.k.get(Long.valueOf(post._ID));
            if (post2 != null) {
                this._items.remove(post2);
                this.k.remove(Long.valueOf(post._ID));
                b();
                notifyListUpdate();
            }
        } else if (abstractPost instanceof Subject) {
            this._items.remove(abstractPost);
            b();
            notifyListUpdate();
        } else if (abstractPost instanceof Moment) {
            this._items.remove(abstractPost);
            b();
            notifyListUpdate();
        }
    }

    public void remove(int i) {
        com.izuiyou.a.a.b.b();
        super.remove(i);
        b();
    }

    private boolean a(long j) {
        return this.k.containsKey(Long.valueOf(j));
    }

    public void a(JSONObject jSONObject) {
        int i;
        if (0 == this._offset) {
            this._items.clear();
        }
        this._total = jSONObject.optInt("total");
        this._offset = (long) jSONObject.optInt("offset");
        JSONArray optJSONArray = jSONObject.optJSONArray("list");
        if (optJSONArray != null) {
            for (i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    AbstractPost parseItem = parseItem(optJSONObject);
                    if (parseItem != null) {
                        this._items.add(parseItem);
                    }
                }
            }
        }
        if (cn.xiaochuankeji.tieba.d.a.b() == cn.xiaochuankeji.tieba.d.a.b) {
            this.a = 40;
        }
        if (this.f >= this.a) {
            this.f = -1;
        }
        Collection arrayList = new ArrayList();
        if (this._items.size() > this.a) {
            int size = this._items.size();
            i = this.a;
            int i2 = cn.xiaochuankeji.tieba.background.a.a().getInt(this.b, 0);
            AbstractPost abstractPost;
            if (cn.xiaochuankeji.tieba.d.a.b() == cn.xiaochuankeji.tieba.d.a.b) {
                i = cn.xiaochuankeji.tieba.background.utils.c.a.c().z() + 3;
                if (i2 + i < itemCount()) {
                    size = i + i2;
                }
                i = size - this.a;
                int i3 = i < 0 ? 0 : i;
                i = i2 - i3;
                if (i < 0) {
                    i = 0;
                }
                cn.xiaochuankeji.tieba.background.a.a().edit().putInt(this.b, i).apply();
                for (i2 = 0; i2 < i3; i2++) {
                    abstractPost = (AbstractPost) this._items.get(i2);
                    if (abstractPost.isRealPost()) {
                        this.k.remove(Long.valueOf(((Post) abstractPost)._ID));
                    }
                    arrayList.add(abstractPost);
                }
                while (size < this._items.size()) {
                    abstractPost = (AbstractPost) this._items.get(size);
                    if (abstractPost.isRealPost()) {
                        this.k.remove(Long.valueOf(((Post) abstractPost)._ID));
                    }
                    arrayList.add(abstractPost);
                    size++;
                }
            } else {
                for (i2 = i; i2 < size; i2++) {
                    abstractPost = (AbstractPost) this._items.get(i2);
                    if (abstractPost.isRealPost()) {
                        this.k.remove(Long.valueOf(((Post) abstractPost)._ID));
                    }
                    arrayList.add(abstractPost);
                }
            }
        }
        if (arrayList.size() > 0) {
            this._items.removeAll(arrayList);
        }
        notifyListUpdate();
    }

    public void a(long j, boolean z) {
        Post post = (Post) this.k.get(Long.valueOf(j));
        if (post != null) {
            post.setFavored(z);
        }
    }

    public void a(long j, PostVote postVote) {
        Post post = (Post) this.k.get(Long.valueOf(j));
        if (post != null) {
            post.postVote = postVote;
            notifyListUpdate();
            b();
        }
    }

    public void a(Post post) {
        if (!a(post._ID)) {
            this._items.add(0, post);
            this.k.put(Long.valueOf(post._ID), post);
            notifyListUpdate();
            b();
        }
    }

    public void a(AbstractPost abstractPost, int i) {
        if (this._items != null) {
            int i2;
            if (this.n == null || !this.n.equals("up")) {
                i2 = i;
            } else {
                i2 = this.e - i;
                i2 = i2 < 0 ? itemCount() - i : itemCount() - i2;
            }
            this._items.add(i2, abstractPost);
            notifyListUpdate();
        }
    }

    public void a(Moment moment) {
        this._items.add(0, moment);
        notifyListUpdate();
        b();
    }

    public void a(long j, int i, int i2) {
    }

    public boolean b(Post post) {
        Post post2 = (Post) this.k.get(Long.valueOf(post._ID));
        if (post2 == null) {
            return false;
        }
        boolean z = false;
        for (Entry entry : post.imgVideos.entrySet()) {
            boolean z2;
            long longValue = ((Long) entry.getKey()).longValue();
            ServerVideo serverVideo = (ServerVideo) entry.getValue();
            ServerVideo imgVideoBy = post2.getImgVideoBy(longValue);
            if (imgVideoBy == null || imgVideoBy.priority == serverVideo.priority) {
                z2 = z;
            } else {
                imgVideoBy.priority = serverVideo.priority;
                z2 = true;
            }
            z = z2;
        }
        if (z) {
            b();
        }
        return true;
    }

    public EntranceType e() {
        switch (this.j) {
            case INDEX_IMGTXT:
                return EntranceType.Post_RecommendImgTxt;
            case TAB_VIDEO:
                return EntranceType.Post_RecommendVideo;
            case INDEX_RECOMMEND:
                return EntranceType.Post_RecommendIndex;
            default:
                return EntranceType.PostItem;
        }
    }
}
