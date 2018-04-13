package qsbk.app.widget;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import qsbk.app.R;
import qsbk.app.utils.UIHelper;

public class GroupLevelHelpDialog extends BaseGroupDialog {
    private int a;
    private int b;
    private RelativeLayout c;

    public GroupLevelHelpDialog(Context context, int i, int i2) {
        super(context);
        this.a = i;
        this.b = i2;
    }

    protected void onCreate(Bundle bundle) {
        int i;
        int i2;
        int i3 = -1;
        int i4 = -14869214;
        int i5 = -9802626;
        super.onCreate(bundle);
        setContentView(R.layout.dialog_group_level_help);
        this.c = (RelativeLayout) findViewById(R.id.dialog_group_level_rel);
        this.c.setBackgroundColor(UIHelper.isNightTheme() ? -14803421 : -1);
        ((TextView) findViewById(R.id.dialog_group_level)).setText(String.valueOf(this.a));
        TextView textView = (TextView) findViewById(R.id.dialog_group_level);
        if (UIHelper.isNightTheme()) {
            i = -5066062;
        } else {
            i = -1;
        }
        textView.setTextColor(i);
        ((TextView) findViewById(R.id.dialog_group_level)).setBackgroundResource(UIHelper.isNightTheme() ? R.drawable.group_info_level_bg_night : R.drawable.group_info_level_bg);
        ((TextView) findViewById(R.id.dialog_group_level_text)).setText(this.a + "等级");
        ((TextView) findViewById(R.id.dialog_group_level_text)).setTextColor(UIHelper.isNightTheme() ? -9802626 : -10263970);
        textView = (TextView) findViewById(R.id.dialog_group_level_require);
        if (UIHelper.isNightTheme()) {
            i = -12171438;
        } else {
            i = -6908266;
        }
        textView.setTextColor(i);
        if (this.a >= 4) {
            ((TextView) findViewById(R.id.dialog_group_level_require)).setText("此群已达到最高级别");
        } else {
            ((TextView) findViewById(R.id.dialog_group_level_require)).setText(String.format("还需要%d成员才能达到%d等级", new Object[]{Integer.valueOf(this.b), Integer.valueOf(this.a + 1)}));
        }
        findViewById(R.id.dialog_close).setOnClickListener(new bv(this));
        findViewById(R.id.dialog_close).setBackgroundResource(UIHelper.isNightTheme() ? R.drawable.shake_close_night : R.drawable.shake_close);
        ((LinearLayout) findViewById(R.id.group_level_lin)).setBackgroundColor(UIHelper.isNightTheme() ? -14935008 : -1315861);
        textView = (TextView) findViewById(R.id.group_info_label);
        if (UIHelper.isNightTheme()) {
            i = -12171438;
        } else {
            i = -1;
        }
        textView.setTextColor(i);
        textView = (TextView) findViewById(R.id.group_info_label_limit);
        if (UIHelper.isNightTheme()) {
            i3 = -12171438;
        }
        textView.setTextColor(i3);
        textView = (TextView) findViewById(R.id.group_level_one);
        if (UIHelper.isNightTheme()) {
            i3 = -9802626;
        } else {
            i3 = -6908266;
        }
        textView.setTextColor(i3);
        textView = (TextView) findViewById(R.id.group_level_twe);
        if (UIHelper.isNightTheme()) {
            i3 = -9802626;
        } else {
            i3 = -6908266;
        }
        textView.setTextColor(i3);
        textView = (TextView) findViewById(R.id.group_level_twe);
        if (UIHelper.isNightTheme()) {
            i3 = -14869214;
        } else {
            i3 = -657931;
        }
        textView.setBackgroundColor(i3);
        textView = (TextView) findViewById(R.id.group_level_three);
        if (UIHelper.isNightTheme()) {
            i3 = -9802626;
        } else {
            i3 = -6908266;
        }
        textView.setTextColor(i3);
        textView = (TextView) findViewById(R.id.group_level_four);
        if (UIHelper.isNightTheme()) {
            i3 = -9802626;
        } else {
            i3 = -6908266;
        }
        textView.setTextColor(i3);
        textView = (TextView) findViewById(R.id.group_level_four);
        if (UIHelper.isNightTheme()) {
            i3 = -14869214;
        } else {
            i3 = -657931;
        }
        textView.setBackgroundColor(i3);
        View findViewById = findViewById(R.id.group_level_view);
        if (UIHelper.isNightTheme()) {
            i2 = -14935008;
        } else {
            i2 = -657931;
        }
        findViewById.setBackgroundColor(i2);
        textView = (TextView) findViewById(R.id.group_level_number_one);
        if (UIHelper.isNightTheme()) {
            i3 = -9802626;
        } else {
            i3 = -6908266;
        }
        textView.setTextColor(i3);
        textView = (TextView) findViewById(R.id.group_level_number_twe);
        if (UIHelper.isNightTheme()) {
            i3 = -9802626;
        } else {
            i3 = -6908266;
        }
        textView.setTextColor(i3);
        textView = (TextView) findViewById(R.id.group_level_number_twe);
        if (UIHelper.isNightTheme()) {
            i3 = -14869214;
        } else {
            i3 = -657931;
        }
        textView.setBackgroundColor(i3);
        textView = (TextView) findViewById(R.id.group_level_number_three);
        if (UIHelper.isNightTheme()) {
            i3 = -9802626;
        } else {
            i3 = -6908266;
        }
        textView.setTextColor(i3);
        textView = (TextView) findViewById(R.id.group_level_number_four);
        if (UIHelper.isNightTheme()) {
            i3 = -9802626;
        } else {
            i3 = -6908266;
        }
        textView.setTextColor(i3);
        textView = (TextView) findViewById(R.id.group_level_number_four);
        if (!UIHelper.isNightTheme()) {
            i4 = -657931;
        }
        textView.setBackgroundColor(i4);
        textView = (TextView) findViewById(R.id.group_level_foot_remind);
        if (!UIHelper.isNightTheme()) {
            i5 = -6908266;
        }
        textView.setTextColor(i5);
    }
}
