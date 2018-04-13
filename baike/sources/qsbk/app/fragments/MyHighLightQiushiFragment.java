package qsbk.app.fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.ManageQiuShiNewActivity;
import qsbk.app.adapter.BaseImageAdapter;
import qsbk.app.adapter.ManageMyContentsAdapter.IViewClickListener;
import qsbk.app.cache.FileCache;
import qsbk.app.cache.MemoryCache;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.loader.OnAsyncLoadListener;
import qsbk.app.model.Article;
import qsbk.app.model.UserInfo;
import qsbk.app.share.ShareUtils;
import qsbk.app.slide.SlideActivity;
import qsbk.app.utils.CollectionUtils;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.ListViewHelper;
import qsbk.app.utils.LoginPermissionClickDelegate;
import qsbk.app.utils.Md5;
import qsbk.app.utils.QiushiArticleBus;
import qsbk.app.utils.QiushiArticleBus.OnArticleActionListener;
import qsbk.app.utils.ReadQiushi;
import qsbk.app.utils.ReportArticle;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;

public class MyHighLightQiushiFragment extends PureArticleListFragment implements IViewClickListener, OnArticleActionListener {
    private UserInfo Q;

    class a implements OnAsyncLoadListener {
        final /* synthetic */ MyHighLightQiushiFragment a;
        private String b = "";

        public a(MyHighLightQiushiFragment myHighLightQiushiFragment) {
            this.a = myHighLightQiushiFragment;
        }

        public void onPrepareListener() {
            this.b = String.format(Constants.MY_HIGHLIGHT_CONTENT, new Object[]{this.a.Q.userId, Integer.valueOf(this.a.o)});
        }

        public String onStartListener() {
            String str = "";
            try {
                str = MemoryCache.findOrCreateMemoryCache().get(Md5.MD5(this.b));
                if (TextUtils.isEmpty(str)) {
                    return HttpClient.getIntentce().get(this.b);
                }
                MemoryCache.findOrCreateMemoryCache().clear();
                this.a.lastRefreshFirstPageTime = Long.valueOf(0);
                return str;
            } catch (QiushibaikeException e) {
                this.a.a(0, false);
                return str;
            } catch (Exception e2) {
                this.a.a(0, false);
                return str;
            }
        }

        public void onFinishListener(String str) {
            if (!TextUtils.isEmpty(str)) {
                boolean a = this.a.a(str);
                if (this.a.o == 2 && a) {
                    FileCache.cacheTextToDisk(this.a.B, this.a.v, str);
                }
                this.a.lastRefreshFirstPageTime = Long.valueOf(System.currentTimeMillis());
                ListViewHelper.saveLastUpdateTime(this.a.B, this.a.v);
                if (this.a.isSelected()) {
                    ReadQiushi.markSend();
                }
                if (this.a.isSelected() && this.a.o == 2) {
                    ReadQiushi.setFirstArticleRead(this.a.j);
                }
                this.a.y = false;
                if (this.a.o()) {
                    this.a.v();
                }
                this.a.a(0, true);
            } else if (TextUtils.isEmpty(str)) {
                if (this.a.B != null) {
                    ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "加载失败，请稍后再试！", Integer.valueOf(0)).show();
                }
                this.a.a(0, false);
            }
        }
    }

    public static MyHighLightQiushiFragment newInstance(UserInfo userInfo) {
        MyHighLightQiushiFragment myHighLightQiushiFragment = new MyHighLightQiushiFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", userInfo);
        myHighLightQiushiFragment.setArguments(bundle);
        return myHighLightQiushiFragment;
    }

    protected boolean u() {
        return false;
    }

    protected boolean r() {
        return false;
    }

    protected boolean o() {
        return false;
    }

    protected boolean s() {
        return false;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        QiushiArticleBus.registerOnArticleActionListener(this);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.Q = (UserInfo) getArguments().getSerializable("user");
        if (this.Q == null) {
            getActivity().finish();
            return;
        }
        this.u = Constants.MY_HIGHLIGHT_CONTENT;
        this.v = "myhighlight";
        this.w = false;
        b = 0;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.layout_ptr_listview, null);
        a(inflate);
        if (UIHelper.isNightTheme()) {
            this.m.setDivider(new ColorDrawable(-16777216));
            this.m.setDividerHeight((int) (getResources().getDisplayMetrics().density / 2.0f));
        } else {
            this.m.setDivider(null);
            this.m.setDividerHeight(0);
        }
        this.F = new gb(this, this.m);
        this.F.setEnable(false);
        if (!isSelected()) {
            this.F.stopAll();
        } else if (QsbkApp.getInstance().isAutoPlayConfiged()) {
            this.F.autoPlay();
        }
        return inflate;
    }

    public void onDetach() {
        super.onDetach();
        QiushiArticleBus.unregisterOnArticleActionListener(this);
    }

    public void onArticleDeleted(OnArticleActionListener onArticleActionListener, Article article) {
        delete(article);
    }

    protected BaseImageAdapter b() {
        return super.b();
    }

    protected boolean y() {
        return true;
    }

    public void onStop() {
        super.onStop();
        if (this.i != null) {
            this.i.onStop();
        }
    }

    protected void k() {
        this.m.setOnItemClickListener(new gc(this));
    }

    protected void l() {
        this.m.setOnItemLongClickListener(new gd(this));
    }

    private void c() {
        boolean z = false;
        if (this.O != null && this.O.getTag().equals("active")) {
            z = true;
        }
        if (getActivity() instanceof ManageQiuShiNewActivity) {
            ShareUtils.openShareDialog(getParentFragment() == null ? this : getParentFragment(), this.B, 1, z, this.M, true);
        } else {
            ShareUtils.openShareDialog(getParentFragment() == null ? this : getParentFragment(), this.B, 1, z, this.M, this.O);
        }
    }

    public void delete(Article article) {
        if (article != null) {
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        ShareUtils shareUtils = new ShareUtils();
        if (i2 >= 1 && i2 == SlideActivity.SLIDE_REQUEST) {
            this.j = QsbkApp.currentDataSource;
            b().notifyDataSetChanged();
            int intExtra = intent.getIntExtra("position", 0) + this.m.getHeaderViewsCount();
            this.o = intent.getIntExtra(ParamKey.PAGE, this.o);
            this.m.post(new ge(this, intExtra));
        }
    }

    public void report(Article article, int i) {
        if (article != null) {
            if (QsbkApp.currentUser == null) {
                ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "登录后才能举报哦！", Integer.valueOf(1)).show();
                LoginPermissionClickDelegate.startLoginActivity(getActivity());
                return;
            }
            ReportArticle.setReportArticle(article, i);
            this.j.remove(article);
            this.i.notifyDataSetChanged();
            FileCache.cacheTextToDiskImmediately(this.B, this.v, CollectionUtils.artilesToJsonString(this.j));
            ReportArticle.reportHandler(true);
        }
    }

    protected OnAsyncLoadListener b(String str) {
        return new a(this);
    }

    public void onPause() {
        super.onPause();
    }

    protected boolean g() {
        return false;
    }
}
