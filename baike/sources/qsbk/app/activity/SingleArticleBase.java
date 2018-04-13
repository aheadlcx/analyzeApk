package qsbk.app.activity;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.qiushibaike.statsdk.StatSDK;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.model.Article;
import qsbk.app.slide.SingleArticleFragment;
import qsbk.app.slide.SingleArticleFragment.OnFragmentCreatedListener;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;

public class SingleArticleBase extends BaseActionBarActivity implements OnFragmentCreatedListener {
    public static final String EXTRA_ARTICLE = "ARTICLEJSON";
    public static final String EXTRA_ARTICLE_ID = "article_id";
    public static final String EXTRA_ARTICLE_SERIAL = "article";
    public static final String EXTRA_SHOW_KEYBOARD = "showKeyboard";
    public static final String FROM_MSG = "FROM_MSG";
    public static final String FROM_PROMOTE = "_FROM_PROMOTE_";
    public static final String KEY_AUTO_SCROLL_TO_COMMENT = "scroll_to_comment";
    public static final String KEY_FROM_PUSH = "from_push";
    public static final String KEY_ONLY_ARTICLE_ID = "only_article_id";
    public static final String KEY_SHOW_USERINFO = "show_userinfo";
    public static final String LOCAL_COMMENT_ID = "-1";
    public static final String QIUSHI_NEED_SHOW_BUDDLE = "_qiushi_need_show_buddle";
    public static final String QIUSHI_PUSH_ID = "qiushi_push";
    protected String a = SingleArticleBase.class.getName();
    protected StringBuffer b = new StringBuffer("糗事");
    protected Article c = null;
    protected boolean d = false;
    protected boolean e = false;
    protected String f = null;
    protected boolean g = true;
    protected boolean h;
    boolean i = false;

    protected /* synthetic */ CharSequence getCustomTitle() {
        return g();
    }

    protected void a(Intent intent, Bundle bundle) {
        this.f = intent.getStringExtra("article_id");
        if (this.c == null) {
            this.c = (Article) intent.getSerializableExtra("article");
        }
        if (intent.getBooleanExtra("FROM_MSG", false)) {
            this.d = true;
            try {
                this.c = new Article(new JSONObject(getIntent().getStringExtra("ARTICLEJSON")));
            } catch (Exception e) {
                ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "消息携带的帖子内容出错，查看失败!", Integer.valueOf(0)).show();
                finish();
            }
        } else if (!this.e && this.c == null) {
            if (f() && TextUtils.isEmpty(this.f)) {
                Object obj = QsbkApp.currentDataSource.get(QsbkApp.currentSelectItem);
                if (obj instanceof Article) {
                    this.c = (Article) obj;
                }
            }
            if (bundle != null) {
                this.c = (Article) bundle.getSerializable("currentArticle");
            }
        }
        if (this.c == null && TextUtils.isEmpty(this.f)) {
            finish();
        } else if (this.c != null) {
            this.f = this.c.id;
        }
    }

    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        this.c = (Article) bundle.getSerializable("currentArticle");
    }

    protected void onSaveInstanceState(Bundle bundle) {
        bundle.putSerializable("currentArticle", this.c);
        super.onSaveInstanceState(bundle);
    }

    protected void onStop() {
        super.onStop();
        this.h = false;
    }

    protected boolean f() {
        return QsbkApp.currentDataSource != null && QsbkApp.currentDataSource.size() > QsbkApp.currentSelectItem && QsbkApp.currentSelectItem > -1;
    }

    protected boolean a(Class cls) {
        for (RunningTaskInfo runningTaskInfo : ((ActivityManager) getBaseContext().getSystemService("activity")).getRunningTasks(Integer.MAX_VALUE)) {
            if (cls.getCanonicalName().equalsIgnoreCase(runningTaskInfo.baseActivity.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public void finish() {
        UIHelper.hideKeyboard(this);
        if (isTaskRoot() && !a(MainActivity.class)) {
            startActivity(new Intent(this, MainActivity.class));
            StatSDK.onEvent(this, "qiushi_push", "mainpage");
        }
        super.finish();
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    protected String g() {
        return null;
    }

    protected int a() {
        return R.layout.activity_singlearticle_base;
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        a(getIntent(), bundle);
        Fragment fragment = null;
        if (!this.e && this.c != null) {
            fragment = SingleArticleFragment.newInstance(this.c);
        } else if (!TextUtils.isEmpty(this.f)) {
            Article article = new Article();
            article.id = this.f;
            fragment = SingleArticleFragment.newInstance(article);
        }
        if (fragment != null) {
            fragment.setOnFragmentCreatedListener(this);
            getSupportFragmentManager().beginTransaction().replace(R.id.article_base, fragment, "singleArticle").commitNowAllowingStateLoss();
            setTitle(this.b.append(this.f != null ? MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.f : "").toString());
            return;
        }
        finish();
    }

    public void onFragmentCreated() {
        Fragment findFragmentByTag = getSupportFragmentManager().findFragmentByTag("singleArticle");
        if (findFragmentByTag instanceof SingleArticleFragment) {
            ((SingleArticleFragment) findFragmentByTag).handleIntent(getIntent(), null);
        }
    }
}
