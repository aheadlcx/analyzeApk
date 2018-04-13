package qsbk.app.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;
import com.facebook.imagepipeline.cache.MediaVariationsIndexDatabase.IndexEntry;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.activity.dialog.UserInfoDialog;
import qsbk.app.fragments.MyAuditFragment;
import qsbk.app.utils.UIHelper;

public class MyAuditListActivity extends BaseActionBarActivity {
    String a;
    private UserInfoDialog b;
    private Window c;

    class a implements Runnable {
        final /* synthetic */ MyAuditListActivity a;

        a(MyAuditListActivity myAuditListActivity) {
            this.a = myAuditListActivity;
        }

        public void run() {
            if (this.a.b != null) {
                this.a.b.dismiss();
            }
        }
    }

    public static void launch(Context context) {
        Intent intent = new Intent(context, MyAuditListActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
        }
        context.startActivity(intent);
    }

    public static void launch(Context context, String str) {
        Intent intent = new Intent(context, MyAuditListActivity.class);
        intent.putExtra(IndexEntry.COLUMN_NAME_DATE, str);
        if (!(context instanceof Activity)) {
            intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
        }
        context.startActivity(intent);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_hot_comment_pager, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.user_remind:
                showUserRemindDialog();
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void showUserRemindDialog() {
        this.b = new UserInfoDialog(this, R.style.user_info_dialog);
        this.c = this.b.getWindow();
        LayoutParams attributes = this.c.getAttributes();
        this.c.setGravity(53);
        int height = getSupportActionBar().getHeight();
        attributes.x = UIHelper.dip2px(this, 10.0f);
        attributes.y = height + UIHelper.dip2px(this, 4.0f);
        this.c.setAttributes(attributes);
        this.b.setCanceledOnTouchOutside(true);
        this.b.show();
        this.c.getDecorView().postDelayed(new a(this), 4000);
        TextView textView = (TextView) this.b.findViewById(R.id.user_info_textView);
        textView.setText("被删除的帖子不会出现在列表中");
        textView.setOnClickListener(new ui(this));
    }

    protected CharSequence getCustomTitle() {
        return "朕审的的帖子";
    }

    protected int a() {
        return R.layout.activity_my_audit;
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        Intent intent = getIntent();
        if (intent != null) {
            this.a = intent.getStringExtra(IndexEntry.COLUMN_NAME_DATE);
        }
        View findViewById = findViewById(R.id.content_container);
        if (findViewById != null) {
            findViewById.getViewTreeObserver().addOnPreDrawListener(new uj(this, findViewById));
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.content_container, MyAuditFragment.newInstance(this.a)).commit();
    }
}
