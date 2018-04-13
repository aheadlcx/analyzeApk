package cn.xiaochuankeji.tieba.ui.a;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import cn.htjyb.b.a.b.b;
import cn.htjyb.ui.widget.headfooterlistview.QueryListView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.modules.chat.models.beans.MessageEvent;
import cn.xiaochuankeji.tieba.background.modules.chat.models.beans.MessageEvent.MessageEventType;
import cn.xiaochuankeji.tieba.background.topic.Topic;
import cn.xiaochuankeji.tieba.background.topic.TopicFollowModel;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.background.utils.h;
import cn.xiaochuankeji.tieba.ui.base.f;
import cn.xiaochuankeji.tieba.ui.discovery.moretopic.MoreTopicRecommendActivity;
import cn.xiaochuankeji.tieba.ui.topic.TopicDetailActivity;
import cn.xiaochuankeji.tieba.ui.widget.SDBottomSheet;
import java.util.ArrayList;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.c;
import org.greenrobot.eventbus.l;

public class a extends f implements cn.xiaochuankeji.tieba.background.modules.a.a.a {
    private TopicFollowModel a;
    private QueryListView b;
    private a c;
    private View d;
    private boolean e;
    private boolean f;
    private View g;
    private b h = new b(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void a(boolean z, boolean z2, String str) {
            if (z && z2) {
                this.a.a.sort();
                this.a.c.notifyDataSetChanged();
                this.a.d();
            }
            c.a().d(new MessageEvent(MessageEventType.MESSAGE_TOPIC_REFRESH_FINISH));
        }
    };

    private class a extends BaseAdapter {
        final /* synthetic */ a a;

        private a(a aVar) {
            this.a = aVar;
        }

        public int getCount() {
            return this.a.a.itemCount();
        }

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            c cVar;
            if (view == null) {
                c cVar2 = new c(this.a.getActivity());
                view = cVar2.f_();
                view.setTag(cVar2);
                cVar = cVar2;
            } else {
                cVar = (c) view.getTag();
            }
            cVar.a((Topic) this.a.a.itemAt(i), true);
            return view;
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        cn.xiaochuankeji.tieba.background.a.g().a(this);
    }

    public void b() {
        if (this.a != null) {
            this.a.refresh();
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        this.g = layoutInflater.inflate(R.layout.activity_myfollowed_topic_list, viewGroup, false);
        this.b = (QueryListView) this.g.findViewById(R.id.queryListView);
        this.b.m().setId(R.id.id_stickynavlayout_innerscrollview);
        this.d = this.g.findViewById(R.id.llEmpty);
        this.a = TopicFollowModel.getInstance();
        this.a.registerOnQueryFinishListener(this.h);
        c();
        b();
        d();
        return this.g;
    }

    @l(a = ThreadMode.MAIN)
    public void message(MessageEvent messageEvent) {
        if (messageEvent.getEventType() == MessageEventType.MESSAGE_TOPIC_TOGGLE_FOLLOW_ACTION) {
            this.e = true;
        }
        if (this.f && messageEvent.getEventType() == MessageEventType.MESSAGE_TOPIC_REFRESH) {
            b();
        }
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        this.f = z;
        if (z) {
            b();
        }
    }

    private void c() {
        this.c = new a();
        this.b.f();
        this.b.m().setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (this.a.a.itemCount() >= 10) {
                    this.a.a(i - 1);
                } else if (i - 1 == this.a.a.itemCount()) {
                    MoreTopicRecommendActivity.a(this.a.getContext());
                } else {
                    this.a.a(i - 1);
                }
            }
        });
        this.b.m().setOnItemLongClickListener(new OnItemLongClickListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (this.a.a.itemCount() >= 10 || i - 1 != this.a.a.itemCount()) {
                    String str;
                    final Topic topic = (Topic) this.a.a.itemAt(i - 1);
                    SDBottomSheet sDBottomSheet = new SDBottomSheet(this.a.getActivity(), new SDBottomSheet.b(this) {
                        final /* synthetic */ AnonymousClass2 b;

                        public void a(int i) {
                            if (i == 0) {
                                this.b.a.a.toggleTop(topic._topicID);
                            } else if (i != 1) {
                            } else {
                                if (topic.role > 1) {
                                    g.b("身为话题的管理员，不能取消关注哦");
                                    return;
                                }
                                this.b.a.a.unFollow(topic._topicID);
                                this.b.a.a(topic);
                            }
                        }
                    });
                    String str2 = "置顶";
                    if (topic._topTime > 0) {
                        str = "取消置顶";
                    } else {
                        str = str2;
                    }
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(new SDBottomSheet.c(R.drawable.icon_option_topic_top, str, 0));
                    arrayList.add(new SDBottomSheet.c(R.drawable.icon_option_follow, "取消关注", 1));
                    sDBottomSheet.a(arrayList, null);
                    sDBottomSheet.b();
                }
                return true;
            }
        });
        this.b.a(this.a, this.c);
    }

    private void a(Topic topic) {
        Topic topic2 = new Topic();
        topic2._topicID = topic._topicID;
        topic2._isAttention = false;
        topic2._isadm = 0;
        MessageEvent messageEvent = new MessageEvent(MessageEventType.MESSAGE_TOPIC_TOGGLE_FOLLOW_ACTION);
        messageEvent.setData(topic2);
        c.a().d(messageEvent);
    }

    private void a(int i) {
        Topic topic = (Topic) this.a.itemAt(i);
        topic._newPostCount = 0;
        topic._isAttention = true;
        h.a(getActivity(), "zy_event_topic_tab_follow", "点击话题");
        TopicDetailActivity.a(getActivity(), topic, "attention");
        h.a(getActivity(), "zy_event_follow_tab_topic", "点击话题");
    }

    public void onResume() {
        super.onResume();
        if (this.f) {
            b();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.b != null) {
            this.b.c();
        }
        if (this.a != null) {
            this.a.unregisterOnQueryFinishedListener(this.h);
        }
        cn.xiaochuankeji.tieba.background.a.g().b(this);
    }

    private void d() {
        if (this.a.itemCount() == 0) {
            if (this.d.getVisibility() != 0) {
                this.d.setVisibility(0);
            }
        } else if (this.d.getVisibility() != 8) {
            this.d.setVisibility(8);
        }
    }

    public void a(boolean z) {
        super.a(z);
        if (this.c != null) {
            this.c.notifyDataSetChanged();
        }
    }

    public void onTokenChanged() {
        if (!cn.xiaochuankeji.tieba.background.a.g().d()) {
            b();
        }
    }
}
