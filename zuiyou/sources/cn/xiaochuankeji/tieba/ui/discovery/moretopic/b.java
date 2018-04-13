package cn.xiaochuankeji.tieba.ui.discovery.moretopic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import cn.htjyb.ui.widget.headfooterlistview.QueryListView;
import cn.htjyb.ui.widget.headfooterlistview.QueryListView.EmptyPaddingStyle;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.c.e;
import cn.xiaochuankeji.tieba.background.c.e.a;
import cn.xiaochuankeji.tieba.background.modules.chat.models.beans.MessageEvent;
import cn.xiaochuankeji.tieba.background.modules.chat.models.beans.MessageEvent.MessageEventType;
import cn.xiaochuankeji.tieba.background.topic.RecommendTopicInitModel;
import cn.xiaochuankeji.tieba.background.topic.RecommendTopicList;
import cn.xiaochuankeji.tieba.background.topic.Topic;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.ui.base.f;
import cn.xiaochuankeji.tieba.ui.topic.TopicDetailActivity;
import java.util.ArrayList;
import java.util.Iterator;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.c;
import org.greenrobot.eventbus.l;

public class b extends f implements OnItemClickListener, cn.htjyb.b.a.b.b {
    private long a;
    private RecommendTopicList b;
    private e c;
    private d d;
    private boolean e;
    private QueryListView f;

    public static b a(long j) {
        Bundle bundle = new Bundle();
        bundle.putLong("category_type_id", j);
        b bVar = new b();
        bVar.setArguments(bundle);
        return bVar;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.a = getArguments().getLong("category_type_id");
        b();
    }

    private void b() {
        this.b = new RecommendTopicList(this.a);
        this.b.setNextListCb(RecommendTopicInitModel.getNextListCb());
        this.b.registerOnQueryFinishListener(this);
        this.c = e.a();
        a a = this.c.a(this.a);
        if (a != null && a.a.size() > 0) {
            this.b.init((ArrayList) a.a.clone(), a.c, a.b ? 1 : 0);
        }
        this.d = new d(getActivity(), this.b);
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        this.e = z;
    }

    @l(a = ThreadMode.MAIN)
    public void message(MessageEvent messageEvent) {
        if (messageEvent.getEventType() == MessageEventType.MESSAGE_TOPIC_TOGGLE_FOLLOW_ACTION && Topic.class.isInstance(messageEvent.getData())) {
            Topic topic = (Topic) messageEvent.getData();
            if (this.d != null && this.b != null) {
                Iterator it = this.b.getItems().iterator();
                while (it.hasNext()) {
                    Topic topic2 = (Topic) it.next();
                    if (topic2._topicID == topic._topicID) {
                        topic2._isAttention = topic._isAttention;
                        this.d.notifyDataSetChanged();
                        break;
                    }
                }
            }
        }
        if (this.e && messageEvent.getEventType() == MessageEventType.MESSAGE_TOPIC_REFRESH && this.b != null) {
            this.b.refresh();
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        if (this.f == null) {
            this.f = c();
            this.f.a(this.b, this.d);
            this.f.m().setOnItemClickListener(this);
        }
        if (this.b.itemCount() == 0) {
            this.b.refresh();
        }
        return this.f;
    }

    private QueryListView c() {
        QueryListView queryListView = new QueryListView(getActivity());
        queryListView.f();
        queryListView.m().setId(R.id.id_stickynavlayout_innerscrollview);
        queryListView.a("空空如也~", (int) R.drawable.ic_post_empty, EmptyPaddingStyle.PADDING20);
        return queryListView;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        TopicDetailActivity.a(getActivity(), (Topic) this.b.itemAt(i - 1), false, "discovery", 0);
    }

    public void a(boolean z, boolean z2, String str) {
        if (!z) {
            g.a(str);
        } else if (z2 && this.b.itemCount() > 0) {
            ArrayList items = this.b.getItems();
            this.c.a(this.a, (ArrayList) items.clone(), this.b.hasMore(), this.b.getQueryMoreOffset());
        }
        c.a().d(new MessageEvent(MessageEventType.MESSAGE_TOPIC_REFRESH_FINISH));
    }

    public void a(boolean z) {
        super.a(z);
        if (this.d != null) {
            this.d.notifyDataSetChanged();
        }
    }
}
