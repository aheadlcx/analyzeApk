package qsbk.app.widget.card;

import android.content.Context;
import android.support.v4.internal.view.SupportMenu;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import qsbk.app.R;
import qsbk.app.im.group.vote.bean.GroupMonthActive;

public class GroupActiveLayout extends LinearLayout {
    public GroupActiveLayout(Context context) {
        super(context);
        a();
    }

    public GroupActiveLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        setOrientation(1);
        if (isInEditMode()) {
            GroupMonthActive[] groupMonthActiveArr = new GroupMonthActive[3];
            for (int i = 0; i < 3; i++) {
                GroupMonthActive groupMonthActive = new GroupMonthActive();
                groupMonthActive.month = i + 6;
                groupMonthActive.actives = new int[]{0, 1, 9, 10, 49, 50, 100, SupportMenu.USER_MASK, -1, 30, 69, 9, 0, 100, 20, 50, 10, 20, 0, 0, 1, 900, 10, 0};
                groupMonthActiveArr[i] = groupMonthActive;
            }
            setMouthActives(groupMonthActiveArr);
        }
    }

    public void setMouthActives(GroupMonthActive[] groupMonthActiveArr) {
        int i = 0;
        int length = groupMonthActiveArr == null ? 0 : groupMonthActiveArr.length;
        int childCount = (getChildCount() / 2) - length;
        for (int i2 = childCount; i2 < 0; i2++) {
            View.inflate(getContext(), R.layout.group_mouth_active, this);
        }
        for (int i3 = 0; i3 < length; i3++) {
            TextView textView = (TextView) getChildAt(i3 * 2);
            textView.setText(groupMonthActiveArr[i3].month + "月");
            textView.setVisibility(0);
            GroupMonthActiveView groupMonthActiveView = (GroupMonthActiveView) getChildAt((i3 * 2) + 1);
            groupMonthActiveView.setActives(groupMonthActiveArr[i3].actives);
            groupMonthActiveView.setVisibility(0);
        }
        while (i < childCount) {
            getChildAt(i * 2).setVisibility(8);
            getChildAt((i * 2) + 1).setVisibility(8);
            i++;
        }
    }
}
