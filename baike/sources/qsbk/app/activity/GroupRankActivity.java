package qsbk.app.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.BaseGroupDialog;

public class GroupRankActivity extends BaseActionBarActivity {
    private Fragment a;

    public class RemindDialog extends BaseGroupDialog {
        final /* synthetic */ GroupRankActivity a;

        public RemindDialog(GroupRankActivity groupRankActivity, Context context) {
            this.a = groupRankActivity;
            super(context);
        }

        protected void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            setContentView(R.layout.group_ranking_explain);
            findViewById(R.id.group_ranking_rel).setBackgroundColor(UIHelper.isNightTheme() ? -14803421 : -1);
            findViewById(R.id.group_ranking_explain_cup).setBackgroundResource(UIHelper.isNightTheme() ? R.drawable.group_ranking_info_cup_night : R.drawable.group_ranking_info_cup);
            ((TextView) findViewById(R.id.group_ranking_explain_title)).setTextColor(UIHelper.isNightTheme() ? -9802626 : -12894910);
            ((TextView) findViewById(R.id.group_ranking_explain_twe)).setTextColor(UIHelper.isNightTheme() ? -12171438 : -6908266);
            ((ImageView) findViewById(R.id.dialog_close)).setImageResource(UIHelper.isNightTheme() ? R.drawable.shake_close_night : R.drawable.shake_close);
            findViewById(R.id.dialog_close).setOnClickListener(new ob(this));
        }
    }

    protected /* synthetic */ CharSequence getCustomTitle() {
        return e();
    }

    public static void launch(Context context, int i) {
        Intent intent = new Intent(context, GroupRankActivity.class);
        intent.putExtra("level", i);
        context.startActivity(intent);
    }

    protected boolean d() {
        return true;
    }

    protected String e() {
        return "群天梯";
    }

    protected int a() {
        return R.layout.activity_frame;
    }

    protected void c_() {
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.Night);
        } else {
            setTheme(R.style.Day.GroupInfo);
        }
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        int intExtra = getIntent().getIntExtra("level", 0);
        if (bundle == null) {
            this.a = GroupRankFragment.newInstance(intExtra);
            getSupportFragmentManager().beginTransaction().add(R.id.frame, this.a).commit();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.group_ranking_level_remind, menu);
        return true;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem findItem = menu.findItem(R.id.group_level_remind);
        if (findItem != null) {
            findItem.setIcon(UIHelper.isNightTheme() ? R.drawable.manage_qiushi_info_dark : R.drawable.manage_qiushi_info_light);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.group_level_remind:
                new RemindDialog(this, this).show();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    public void finish() {
        super.finish();
    }
}
