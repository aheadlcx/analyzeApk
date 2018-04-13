package qsbk.app.fragments;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.ArrayList;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.adapter.BaseImageAdapter;
import qsbk.app.model.CircleTopic;
import qsbk.app.utils.UIHelper;

public class TopicsTopFragment$CircleTopicAdapter extends BaseImageAdapter {
    final /* synthetic */ TopicsTopFragment a;
    private int b;
    private boolean c;
    private LayoutInflater e;

    private class a {
        int a;
        ImageView b;
        TextView c;
        TextView d;
        TextView e;
        TextView f;
        TextView g;
        TextView h;
        final /* synthetic */ TopicsTopFragment$CircleTopicAdapter i;

        private a(TopicsTopFragment$CircleTopicAdapter topicsTopFragment$CircleTopicAdapter) {
            this.i = topicsTopFragment$CircleTopicAdapter;
        }
    }

    public TopicsTopFragment$CircleTopicAdapter(TopicsTopFragment topicsTopFragment, ArrayList<Object> arrayList, Activity activity, boolean z) {
        this.a = topicsTopFragment;
        super(arrayList, activity);
        this.c = z;
        this.e = LayoutInflater.from(activity);
    }

    protected Drawable d() {
        return this.k.getResources().getDrawable(R.drawable.circle_topic_default);
    }

    public int getViewTypeCount() {
        return 3;
    }

    public int getItemViewType(int i) {
        Object item = getItem(i);
        if (item instanceof CircleTopic) {
            return 0;
        }
        if (item instanceof TopicsTopFragment$OtherItem) {
            return ((TopicsTopFragment$OtherItem) item).type;
        }
        return 1;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        switch (getItemViewType(i)) {
            case 0:
            case 2:
                return getTopicView(i, view, viewGroup);
            default:
                return a(i, view, viewGroup);
        }
    }

    public View getTopicView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        int i2 = -1;
        if (view == null) {
            view = LayoutInflater.from(this.k).inflate(R.layout.cell_circle_topic_topic, viewGroup, false);
            aVar = new a();
            aVar.b = (ImageView) view.findViewById(R.id.icon);
            aVar.c = (TextView) view.findViewById(R.id.title);
            aVar.d = (TextView) view.findViewById(R.id.intro);
            aVar.e = (TextView) view.findViewById(R.id.article_count);
            aVar.f = (TextView) view.findViewById(R.id.create);
            aVar.g = (TextView) view.findViewById(R.id.ranking_num);
            aVar.h = (TextView) view.findViewById(R.id.topic_owner);
            view.setOnClickListener(new lg(this, aVar));
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        aVar.a = i;
        int itemViewType = getItemViewType(i);
        if (itemViewType == 0) {
            CircleTopic circleTopic = (CircleTopic) getItem(i);
            if (!TopicsTopFragment.d(this.a) && i >= this.b) {
                i2 = circleTopic.rank;
            }
            aVar.c.setText(circleTopic.content);
            if (circleTopic.isAnonymous) {
                aVar.c.setCompoundDrawablesWithIntrinsicBounds(0, 0, UIHelper.getTopicAnonymous(), 0);
            } else {
                aVar.c.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }
            if ((circleTopic.user == null || QsbkApp.currentUser == null || !TextUtils.equals(QsbkApp.currentUser.userId, circleTopic.user.userId)) && (circleTopic.master_id <= 0 || QsbkApp.currentUser == null || !TextUtils.equals(String.valueOf(circleTopic.master_id), QsbkApp.currentUser.userId))) {
                aVar.h.setVisibility(8);
            } else {
                aVar.h.setVisibility(0);
            }
            if (TextUtils.isEmpty(circleTopic.intro)) {
                aVar.d.setVisibility(8);
            } else {
                aVar.d.setText(circleTopic.intro);
                aVar.d.setVisibility(0);
            }
            Object absoluteUrlOfCircleWebpImage = QsbkApp.absoluteUrlOfCircleWebpImage(circleTopic.icon == null ? "" : circleTopic.icon.url, circleTopic.createAt);
            if (TextUtils.isEmpty(absoluteUrlOfCircleWebpImage)) {
                aVar.b.setImageResource(UIHelper.getDefaultAvatar());
            } else {
                a(aVar.b, absoluteUrlOfCircleWebpImage, false);
            }
            aVar.e.setText(String.format("动态 %d  今日 %d", new Object[]{Integer.valueOf(circleTopic.articleCount), Integer.valueOf(circleTopic.todayCount)}));
            if (i2 == 1) {
                aVar.g.setText(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                aVar.g.setBackgroundResource(UIHelper.isNightTheme() ? R.drawable.group_levle_gold_night : R.drawable.group_level_gold);
                aVar.g.setVisibility(0);
            } else if (i2 == 2) {
                aVar.g.setText(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                aVar.g.setBackgroundResource(UIHelper.isNightTheme() ? R.drawable.group_levle_silver_night : R.drawable.group_level_silver);
                aVar.g.setVisibility(0);
            } else if (i2 == 3) {
                aVar.g.setText(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                aVar.g.setBackgroundResource(UIHelper.isNightTheme() ? R.drawable.group_levle_copper_night : R.drawable.group_level_copper);
                aVar.g.setVisibility(0);
            } else if (i2 > 3) {
                aVar.g.setBackgroundResource(R.drawable.topic_rank_default_bg);
                aVar.g.setText(String.format("%d", new Object[]{Integer.valueOf(i2)}));
                aVar.g.setVisibility(0);
            } else {
                aVar.g.setVisibility(8);
            }
            aVar.f.setVisibility(8);
        } else if (itemViewType == 2) {
            aVar.g.setVisibility(8);
            TopicsTopFragment$OtherItem topicsTopFragment$OtherItem = (TopicsTopFragment$OtherItem) getItem(i);
            aVar.c.setText(topicsTopFragment$OtherItem.msg);
            if (topicsTopFragment$OtherItem.msg.contains("打卡")) {
                aVar.c.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            } else if (topicsTopFragment$OtherItem.msg.contains("树洞")) {
                aVar.c.setCompoundDrawablesWithIntrinsicBounds(0, 0, UIHelper.getTopicAnonymous(), 0);
            } else {
                aVar.c.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }
            aVar.d.setVisibility(8);
            aVar.e.setText("新话题");
            aVar.b.setImageResource(R.drawable.circle_topic_default);
            aVar.f.setVisibility(0);
        }
        return view;
    }

    private View a(int i, View view, ViewGroup viewGroup) {
        View inflate = this.e.inflate(R.layout.cell_circle_topic_label, viewGroup, false);
        ((TextView) inflate.findViewById(R.id.label)).setText(((TopicsTopFragment$OtherItem) getItem(i)).msg);
        return inflate;
    }
}
