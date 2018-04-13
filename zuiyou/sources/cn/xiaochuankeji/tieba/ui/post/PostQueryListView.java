package cn.xiaochuankeji.tieba.ui.post;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView.RecyclerListener;
import android.widget.ListView;
import cn.htjyb.b.a.b;
import cn.htjyb.ui.a;
import cn.htjyb.ui.widget.headfooterlistview.QueryListView;
import cn.htjyb.ui.widget.headfooterlistview.header.State;
import cn.htjyb.ui.widget.headfooterlistview.header.d;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.AppController;
import cn.xiaochuankeji.tieba.background.data.Comment;
import cn.xiaochuankeji.tieba.background.data.post.AbstractPost;
import cn.xiaochuankeji.tieba.background.data.post.Post;
import cn.xiaochuankeji.tieba.background.modules.chat.models.beans.MessageEvent;
import cn.xiaochuankeji.tieba.background.modules.chat.models.beans.MessageEvent.MessageEventType;
import cn.xiaochuankeji.tieba.background.post.m;
import cn.xiaochuankeji.tieba.background.utils.h;
import cn.xiaochuankeji.tieba.ui.homepage.HomePageActivity;
import cn.xiaochuankeji.tieba.ui.post.postitem.e;
import cn.xiaochuankeji.tieba.ui.topic.TopicDetailActivity;
import java.util.HashMap;
import java.util.Map;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.c;
import org.greenrobot.eventbus.l;

public class PostQueryListView extends QueryListView implements RecyclerListener, a {
    private m d;
    public d e;
    private i f;

    public PostQueryListView(Context context) {
        this(context, null);
    }

    public PostQueryListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        c.a().a(this);
        this.a.setRecyclerListener(this);
    }

    public void a(m mVar) {
        a(mVar, false);
    }

    public void a(m mVar, boolean z) {
        this.d = mVar;
        this.e = new d(this.b, mVar);
        if (z) {
            this.e.a();
        }
        super.a((b) mVar, this.e);
        m().a(3, new int[]{0, 1, 2}, R.id.llGodReview, new cn.htjyb.ui.widget.headfooterlistview.a(this) {
            final /* synthetic */ PostQueryListView a;

            {
                this.a = r1;
            }

            public void a(ListView listView, View view, boolean z, int i) {
                Object item = this.a.e.getItem(i);
                if (item != null && (item instanceof Post)) {
                    Post post = (Post) item;
                    if (post != null) {
                        this.a.e.a(post._ID, z);
                        this.a.e.notifyDataSetChanged();
                        this.a.a(post, z);
                    }
                }
            }

            public int a(int i) {
                Object item = this.a.e.getItem(i);
                if (item != null && (item instanceof Post)) {
                    Post post = (Post) item;
                    if (post != null) {
                        int a = this.a.e.a(post._ID);
                        int size = post.comments.size();
                        if (size == 1) {
                            return 0;
                        }
                        if (size > 1) {
                            if (a == 0) {
                                return 1;
                            }
                            if (a == size - 1) {
                                return 2;
                            }
                            return 3;
                        }
                    }
                }
                return 0;
            }
        });
    }

    private void a(Post post, boolean z) {
        int a = this.e.a(post._ID);
        int size = post.comments.size();
        if (a >= 0 && a < size) {
            Comment comment = (Comment) post.comments.get(a);
            if (comment != null) {
                Map hashMap = new HashMap();
                hashMap.put("direction", z ? "right" : "left");
                hashMap.put("count", Integer.valueOf(size));
                hashMap.put("index", Integer.valueOf(a + 1));
                hashMap.put("appchannel", AppController.instance().packageChannel());
                hashMap.put("rtype", comment.isGod() ? "god" : "fine");
                Object obj = "other";
                Context context = getContext();
                if (context instanceof HomePageActivity) {
                    obj = "index";
                } else if (context instanceof TopicDetailActivity) {
                    obj = "topic";
                }
                hashMap.put("from", obj);
                cn.xiaochuankeji.tieba.background.utils.monitor.a.b.a().a("godreview_expose", "review", comment._id, post._ID, null, hashMap);
            }
        }
        if ((getContext() instanceof HomePageActivity) && HomePageActivity.e()) {
            h.a(getContext(), "zy_event_homepage_tab_recommend", "神评切换次数");
        }
    }

    protected d e() {
        if (!(this.b instanceof HomePageActivity)) {
            return super.e();
        }
        this.f = new i(this.b);
        return this.f;
    }

    public void h() {
        if (m().getViewHeader().getState() != State.kStateRefreshing) {
            super.h();
        }
    }

    @l(a = ThreadMode.MAIN)
    public void message(MessageEvent messageEvent) {
        if (messageEvent.getEventType() == MessageEventType.MESSAGE_POST_DELETE || messageEvent.getEventType() == MessageEventType.MESSAGE_POST_HAS_DELETED) {
            if ((messageEvent.getData() instanceof AbstractPost) && this.d != null) {
                this.d.remove((AbstractPost) messageEvent.getData());
            }
        } else if (messageEvent.getEventType() == MessageEventType.MESSAGE_TEXT_VIEW_COLLAPSE && ((Context) messageEvent.getData()) == this.b) {
            int intValue = ((Integer) messageEvent.getExtraData()).intValue() + m().getHeaderViewsCount();
            if (m().getFirstVisiblePosition() == intValue) {
                m().setSelection(intValue);
            }
        }
    }

    @l(a = ThreadMode.MAIN)
    public void onFollowMember(cn.xiaochuankeji.tieba.ui.topic.data.b bVar) {
        if (bVar != null && this.d != null && this.e != null) {
            for (AbstractPost abstractPost : this.d.getItems()) {
                if (abstractPost.getMemberId() == bVar.b) {
                    abstractPost.setFollowStatus(bVar.a ? 1 : 0);
                }
            }
            this.e.notifyDataSetChanged();
        }
    }

    public void c() {
        super.c();
        if (this.e != null) {
            this.e.c();
        }
        c.a().c(this);
    }

    public void onMovedToScrapHeap(View view) {
        if (view.getTag() == null) {
            return;
        }
        if (view.getTag() instanceof e) {
            ((e) view.getTag()).o();
        } else if (view.getTag() instanceof cn.xiaochuankeji.tieba.ui.post.postitem.c) {
            ((cn.xiaochuankeji.tieba.ui.post.postitem.c) view.getTag()).c();
        }
    }
}
