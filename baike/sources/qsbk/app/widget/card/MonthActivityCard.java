package qsbk.app.widget.card;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import qsbk.app.R;
import qsbk.app.im.group.vote.adapter.DayActivityLevelAdapter;
import qsbk.app.im.group.vote.bean.DayActivityInfo;
import qsbk.app.im.group.vote.bean.MonthActivityInfo;
import qsbk.app.widget.CustomGridView;

public class MonthActivityCard extends RelativeLayout {
    private static final String a = MonthActivityCard.class.getSimpleName();
    private Context b;
    private TextView c;
    private CustomGridView d;
    private MonthActivityInfo e = new MonthActivityInfo();
    private ArrayList<DayActivityInfo> f = new ArrayList();
    private DayActivityLevelAdapter g;

    public MonthActivityCard(Context context) {
        super(context);
        this.b = context;
    }

    public MonthActivityCard(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = context;
    }

    public MonthActivityCard(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = context;
    }

    public void init() {
        if (!isInEditMode()) {
            removeAllViews();
            View inflate = LayoutInflater.from(this.b).inflate(R.layout.image_card_view, null, false);
            this.c = (TextView) inflate.findViewById(R.id.cardTitle);
            this.d = (CustomGridView) inflate.findViewById(R.id.cardGrid);
            addView(inflate);
            updateCells();
        }
    }

    public void initData(MonthActivityInfo monthActivityInfo) {
        this.e = monthActivityInfo;
        this.f = monthActivityInfo.mDayActivityInfos;
    }

    public void updateCells() {
        Log.d(a, "updateCells, size=" + this.f.size());
        this.g = new DayActivityLevelAdapter(this.b, this.f);
        this.d.setAdapter(this.g);
    }

    public void setCardTitle() {
        this.c.setText(String.valueOf(this.e.mMonth) + "月");
    }

    public void notifyChanges() {
        updateCells();
    }
}
