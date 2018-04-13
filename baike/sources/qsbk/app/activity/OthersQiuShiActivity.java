package qsbk.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.activity.publish.PublishActivity;
import qsbk.app.core.AsyncTask;
import qsbk.app.fragments.OthersQiuShiFragment;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.QiushiArticleBus.OnArticleVoteStateChangeListener;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;

public class OthersQiuShiActivity extends BaseActionBarActivity {
    private int a;
    private TextView b;
    private SimpleHttpTask c;
    private OnArticleVoteStateChangeListener d = new ze(this);

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    protected String f() {
        if (QsbkApp.currentUser != null) {
            return TextUtils.equals(getIntent().getStringExtra("uid"), QsbkApp.currentUser.userId) ? "我的糗事" : "TA的糗事";
        } else {
            return "TA的糗事";
        }
    }

    protected int a() {
        return R.layout.activity_fragment_container;
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        this.b = (TextView) findViewById(R.id.smile_count);
        CharSequence stringExtra = getIntent().getStringExtra("user_info");
        String stringExtra2 = getIntent().getStringExtra("uid");
        if (TextUtils.isEmpty(stringExtra)) {
            getUserInfo(stringExtra2);
            return;
        }
        Fragment othersQiuShiFragment = new OthersQiuShiFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putString("uid", getIntent().getStringExtra("uid"));
        bundle2.putString("user_info", getIntent().getStringExtra("user_info"));
        othersQiuShiFragment.setArguments(bundle2);
        getSupportFragmentManager().beginTransaction().add(R.id.container, othersQiuShiFragment).commit();
    }

    public void getUserInfo(String str) {
        this.c = new SimpleHttpTask(String.format(Constants.PERSONAL_INFO_URL, new Object[]{str}), new zf(this, str));
        this.c.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        if (getIntent().getStringExtra("uid").equals(QsbkApp.currentUser.userId)) {
            getMenuInflater().inflate(R.menu.other_qiushi, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        if (getIntent().getStringExtra("uid").equals(QsbkApp.currentUser.userId)) {
            MenuItem findItem = menu.findItem(R.id.qiushi_notification);
            if (findItem != null) {
                findItem.setIcon(UIHelper.isNightTheme() ? R.drawable.ic_bell_night : R.drawable.ic_bell);
            }
            MenuItem findItem2 = menu.findItem(R.id.add);
            if (findItem2 != null) {
                findItem2.setIcon(UIHelper.getNewSubmitMenuIcon());
            }
        }
        return super.onPrepareOptionsMenu(menu);
    }

    public void setQiushiSmileCount(int i) {
        this.a = i;
        this.b.setVisibility(0);
        this.b.setText(String.format("获得笑脸总数 ：%s", new Object[]{Integer.valueOf(i)}));
    }

    protected void onDestroy() {
        if (this.c != null) {
            this.c.cancel(true);
            this.c = null;
        }
        super.onDestroy();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                LogUtil.d("on home click");
                finish();
                break;
            case R.id.add:
                startPublish();
                break;
            case R.id.qiushi_notification:
                QiushiNotificationListActivity.gotoQiushi(this, 0);
                break;
        }
        return false;
    }

    public void startPublish() {
        if (QsbkApp.currentUser == null) {
            ToastAndDialog.showLoginGuideDialog(this, R.string.login_guide_dialog_content_publish);
            return;
        }
        Intent intent = new Intent(this, PublishActivity.class);
        intent.putExtra("flag", "article");
        startActivity(intent);
    }
}
