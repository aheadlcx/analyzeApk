package qsbk.app.fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.core.LimitFIFOQueue;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.CircleTopic;
import qsbk.app.utils.CircleTopicManager;
import qsbk.app.utils.UIHelper;

class CircleTopicsFragment$a extends BaseAdapter {
    boolean a;
    List<CircleTopic> b = new ArrayList();
    final /* synthetic */ CircleTopicsFragment c;

    class a {
        SimpleDraweeView a;
        TextView b;
        final /* synthetic */ CircleTopicsFragment$a c;

        a(CircleTopicsFragment$a circleTopicsFragment$a, View view) {
            this.c = circleTopicsFragment$a;
            this.a = (SimpleDraweeView) view.findViewById(R.id.icon);
            this.b = (TextView) view.findViewById(R.id.title);
        }
    }

    public CircleTopicsFragment$a(CircleTopicsFragment circleTopicsFragment, boolean z) {
        this.c = circleTopicsFragment;
        this.a = z;
        a();
    }

    private void a() {
        this.b.clear();
        if (this.a) {
            LimitFIFOQueue lruTopics = CircleTopicManager.getInstance().getLruTopics();
            if (lruTopics != null && lruTopics.size() > 0) {
                Iterator it = lruTopics.iterator();
                while (it.hasNext()) {
                    CircleTopic circleTopic = (CircleTopic) it.next();
                    if (!circleTopic.isClocked()) {
                        this.b.add(circleTopic);
                    }
                }
                return;
            }
            return;
        }
        this.b.addAll(CircleTopicManager.getInstance().getLruTopics());
    }

    public void notifyDataSetChanged() {
        a();
        super.notifyDataSetChanged();
    }

    public int getCount() {
        return this.b.size();
    }

    public CircleTopic getItem(int i) {
        if (i < this.b.size()) {
            return (CircleTopic) this.b.get(i);
        }
        return null;
    }

    public long getItemId(int i) {
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        CircleTopic item = getItem(i);
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_topic_recent, viewGroup, false);
            aVar = new a(this, view);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        aVar.b.setText(item.content);
        FrescoImageloader.displayAvatar(aVar.a, QsbkApp.absoluteUrlOfCircleWebpImage(item.icon == null ? "" : item.icon.url, item.createAt), 0, false, UIHelper.dip2px(aVar.a.getContext(), 4.0f));
        view.setOnClickListener(new bs(this, item));
        return view;
    }
}
