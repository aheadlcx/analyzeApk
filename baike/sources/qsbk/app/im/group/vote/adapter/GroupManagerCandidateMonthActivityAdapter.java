package qsbk.app.im.group.vote.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import qsbk.app.im.group.vote.bean.MonthActivityInfo;
import qsbk.app.utils.DebugUtil;
import qsbk.app.widget.card.MonthActivityCard;

public class GroupManagerCandidateMonthActivityAdapter extends BaseAdapter {
    private static final String a = GroupManagerCandidateMonthActivityAdapter.class.getSimpleName();
    private Context b;
    private ArrayList<MonthActivityInfo> c = new ArrayList();

    public GroupManagerCandidateMonthActivityAdapter(Context context) {
        this.b = context;
    }

    public void setActivityInfo(ArrayList<MonthActivityInfo> arrayList) {
        DebugUtil.debug(a, "setActivityInfo, size=" + arrayList.size());
        this.c = arrayList;
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.c.size();
    }

    public Object getItem(int i) {
        return this.c.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View monthActivityCard;
        DebugUtil.debug(a, "getView position=" + i + ",month=" + ((MonthActivityInfo) this.c.get(i)).mMonth + "");
        if (view == null) {
            monthActivityCard = new MonthActivityCard(this.b, null);
            monthActivityCard.setLayoutParams(new LayoutParams(-1, -2));
        } else {
            monthActivityCard = view;
        }
        ((MonthActivityCard) monthActivityCard).initData((MonthActivityInfo) this.c.get(i));
        ((MonthActivityCard) monthActivityCard).init();
        ((MonthActivityCard) monthActivityCard).setCardTitle();
        return monthActivityCard;
    }
}
