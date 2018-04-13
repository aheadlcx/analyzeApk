package qsbk.app.fragments;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v4.view.LiveBannerViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.adapter.BaseImageAdapter;
import qsbk.app.model.CircleTopic;
import qsbk.app.model.CircleTopicBanner;
import qsbk.app.model.CircleTopicCategory;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.CircleCategoryFlowCell;
import qsbk.app.widget.CircleTopicPagerBannerCell;
import qsbk.app.widget.LoopBannerCell;

public class CircleTopicsFragment$CircleTopicAdapter extends BaseImageAdapter {
    final /* synthetic */ CircleTopicsFragment a;
    private int b;
    private boolean c;

    private class a {
        final /* synthetic */ CircleTopicsFragment$CircleTopicAdapter a;
        private View b;
        private GridView c;

        a(CircleTopicsFragment$CircleTopicAdapter circleTopicsFragment$CircleTopicAdapter, View view) {
            this.a = circleTopicsFragment$CircleTopicAdapter;
            this.b = view.findViewById(R.id.close);
            this.c = (GridView) view.findViewById(R.id.grid);
            this.c.setAdapter(CircleTopicsFragment.u(circleTopicsFragment$CircleTopicAdapter.a));
        }
    }

    private class b {
        int a;
        ImageView b;
        TextView c;
        TextView d;
        TextView e;
        TextView f;
        TextView g;
        TextView h;
        final /* synthetic */ CircleTopicsFragment$CircleTopicAdapter i;

        private b(CircleTopicsFragment$CircleTopicAdapter circleTopicsFragment$CircleTopicAdapter) {
            this.i = circleTopicsFragment$CircleTopicAdapter;
        }
    }

    public CircleTopicsFragment$CircleTopicAdapter(CircleTopicsFragment circleTopicsFragment, ArrayList<Object> arrayList, Activity activity, boolean z) {
        this.a = circleTopicsFragment;
        super(arrayList, activity);
        this.c = z;
        a();
    }

    private void a() {
        if (this.c && this.m != null && this.m.size() > 0) {
            Iterator it = this.m.iterator();
            while (it.hasNext()) {
                Object next = it.next();
                if ((next instanceof CircleTopic) && ((CircleTopic) next).isClocked()) {
                    it.remove();
                }
            }
        }
    }

    public void notifyDataSetChanged() {
        a();
        super.notifyDataSetChanged();
    }

    protected Drawable d() {
        return this.k.getResources().getDrawable(R.drawable.circle_topic_default);
    }

    public int getViewTypeCount() {
        return 6;
    }

    public int getItemViewType(int i) {
        Object item = getItem(i);
        if (item instanceof CircleTopic) {
            return 0;
        }
        if (item instanceof Collection) {
            if (item instanceof List) {
                List list = (List) item;
                if (list != null && list.size() > 0) {
                    if (list.get(0) instanceof CircleTopicBanner) {
                        return 4;
                    }
                    if (list.get(0) instanceof CircleTopicCategory) {
                        return 5;
                    }
                }
            }
            return 3;
        } else if (item instanceof CircleTopicsFragment$OtherItem) {
            return ((CircleTopicsFragment$OtherItem) item).type;
        } else {
            return 1;
        }
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        switch (getItemViewType(i)) {
            case 0:
            case 2:
                return getTopicView(i, view, viewGroup);
            case 3:
                return a(i, view, viewGroup);
            case 4:
                return b(i, view, viewGroup);
            case 5:
                return c(i, view, viewGroup);
            default:
                return getLabelView(i, view, viewGroup);
        }
    }

    public View getTopicView(int i, View view, ViewGroup viewGroup) {
        b bVar;
        if (view == null) {
            view = LayoutInflater.from(this.k).inflate(R.layout.cell_circle_topic_topic, viewGroup, false);
            bVar = new b();
            bVar.b = (ImageView) view.findViewById(R.id.icon);
            bVar.c = (TextView) view.findViewById(R.id.title);
            bVar.d = (TextView) view.findViewById(R.id.intro);
            bVar.e = (TextView) view.findViewById(R.id.article_count);
            bVar.f = (TextView) view.findViewById(R.id.create);
            bVar.g = (TextView) view.findViewById(R.id.ranking_num);
            bVar.h = (TextView) view.findViewById(R.id.topic_owner);
            view.setOnClickListener(new bo(this, bVar));
            view.setTag(bVar);
        } else {
            bVar = (b) view.getTag();
        }
        bVar.a = i;
        int itemViewType = getItemViewType(i);
        if (itemViewType == 0) {
            CircleTopic circleTopic = (CircleTopic) getItem(i);
            if (!CircleTopicsFragment.q(this.a) && i >= this.b) {
                int i2 = circleTopic.rank;
            }
            bVar.c.setText(circleTopic.content);
            if (circleTopic.isAnonymous) {
                bVar.c.setCompoundDrawablesWithIntrinsicBounds(0, 0, UIHelper.getTopicAnonymous(), 0);
            } else {
                bVar.c.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }
            if ((circleTopic.user == null || QsbkApp.currentUser == null || !TextUtils.equals(QsbkApp.currentUser.userId, circleTopic.user.userId)) && (circleTopic.master_id <= 0 || QsbkApp.currentUser == null || !TextUtils.equals(String.valueOf(circleTopic.master_id), QsbkApp.currentUser.userId))) {
                bVar.h.setVisibility(8);
                bVar.c.setMaxWidth(Integer.MAX_VALUE);
            } else {
                bVar.h.setVisibility(0);
                bVar.c.setMaxWidth(UIHelper.dip2px(this.a.getContext(), 200.0f));
            }
            if (TextUtils.isEmpty(circleTopic.intro)) {
                bVar.d.setVisibility(8);
            } else {
                bVar.d.setText(circleTopic.intro);
                bVar.d.setVisibility(0);
            }
            a(bVar.b, QsbkApp.absoluteUrlOfCircleWebpImage(circleTopic.icon == null ? "" : circleTopic.icon.url, circleTopic.createAt), false, UIHelper.dip2px(bVar.b.getContext(), 6.0f));
            bVar.e.setText(String.format("动态 %d  今日 %d", new Object[]{Integer.valueOf(circleTopic.articleCount), Integer.valueOf(circleTopic.todayCount)}));
            bVar.g.setVisibility(8);
            bVar.f.setVisibility(8);
        } else if (itemViewType == 2) {
            bVar.g.setVisibility(8);
            CircleTopicsFragment$OtherItem circleTopicsFragment$OtherItem = (CircleTopicsFragment$OtherItem) getItem(i);
            bVar.c.setText(circleTopicsFragment$OtherItem.msg);
            if (circleTopicsFragment$OtherItem.msg.contains("打卡")) {
                bVar.c.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            } else if (circleTopicsFragment$OtherItem.msg.contains("树洞")) {
                bVar.c.setCompoundDrawablesWithIntrinsicBounds(0, 0, UIHelper.getTopicAnonymous(), 0);
            } else {
                bVar.c.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }
            bVar.h.setVisibility(8);
            bVar.d.setVisibility(8);
            bVar.e.setText("新话题");
            bVar.b.setImageResource(R.drawable.circle_topic_default);
            bVar.f.setVisibility(0);
        }
        return view;
    }

    public View getLabelView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(this.k).inflate(R.layout.cell_circle_topic_label, viewGroup, false);
        }
        ((TextView) view.findViewById(R.id.label)).setText(((CircleTopicsFragment$OtherItem) getItem(i)).msg);
        return view;
    }

    private View a(int i, View view, ViewGroup viewGroup) {
        a aVar;
        int i2 = 0;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_circle_topic_recent, CircleTopicsFragment.c(this.a), false);
            aVar = new a(this, view);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        aVar.c.setAdapter(CircleTopicsFragment.u(this.a));
        View b = aVar.b;
        if (CircleTopicsFragment.u(this.a).getCount() == 0) {
            i2 = 8;
        }
        b.setVisibility(i2);
        aVar.b.setOnClickListener(new bp(this));
        return view;
    }

    private View b(int i, View view, ViewGroup viewGroup) {
        LoopBannerCell circleTopicPagerBannerCell;
        List list = (List) getItem(i);
        if (view == null) {
            circleTopicPagerBannerCell = new CircleTopicPagerBannerCell(list);
            circleTopicPagerBannerCell.performCreate(i, viewGroup, list);
            circleTopicPagerBannerCell.getCellView().setTag(circleTopicPagerBannerCell);
        } else {
            circleTopicPagerBannerCell = (LoopBannerCell) view.getTag();
        }
        if (CircleTopicsFragment.v(this.a)) {
            circleTopicPagerBannerCell.requestDataChange();
        }
        circleTopicPagerBannerCell.performUpdate(i, viewGroup, list);
        ((LiveBannerViewPager) circleTopicPagerBannerCell.getCellView().findViewById(R.id.pager)).bindView(CircleTopicsFragment.d(this.a));
        return circleTopicPagerBannerCell.getCellView();
    }

    private View c(int i, View view, ViewGroup viewGroup) {
        CircleCategoryFlowCell circleCategoryFlowCell;
        List list = (List) getItem(i);
        if (view == null) {
            circleCategoryFlowCell = new CircleCategoryFlowCell();
            circleCategoryFlowCell.performCreate(i, viewGroup, list);
            view = circleCategoryFlowCell.getCellView();
            view.setTag(circleCategoryFlowCell);
        } else {
            circleCategoryFlowCell = (CircleCategoryFlowCell) view.getTag();
        }
        circleCategoryFlowCell.performUpdate(i, viewGroup, list);
        return view;
    }
}
