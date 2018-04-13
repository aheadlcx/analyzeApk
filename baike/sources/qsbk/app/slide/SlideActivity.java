package qsbk.app.slide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.ViewGroup;
import com.baidu.mobstat.Config;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.tencent.connect.common.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.ReAuthActivity;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.ad.feedsad.FeedsAd;
import qsbk.app.ad.feedsad.baiduad.BaiduAdItemData;
import qsbk.app.ad.feedsad.gdtad.GdtAdItemData;
import qsbk.app.ad.feedsad.qbad.QbAdItem;
import qsbk.app.ad.feedsad.qhad.QhAdItemData;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.fragments.IPageableFragment;
import qsbk.app.guide.dialog.LoginGuideDialog;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.loader.AsyncDataLoader;
import qsbk.app.loader.OnAsyncLoadListener;
import qsbk.app.model.Article;
import qsbk.app.model.CircleArticle;
import qsbk.app.model.CircleTopic;
import qsbk.app.model.CircleTopicBanner;
import qsbk.app.model.GroupRecommend;
import qsbk.app.model.LivePackage;
import qsbk.app.model.News;
import qsbk.app.model.Qsjx;
import qsbk.app.model.RssArticle;
import qsbk.app.model.UserLoginGuideCard;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.KeyboardUtils;
import qsbk.app.utils.ReadQiushi;
import qsbk.app.utils.ReportArticle;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;

public class SlideActivity extends BaseActionBarActivity implements OnLoadMoreSlideItemsListener {
    public static final int MORE_CIRCLE_VIDEO = 10088;
    public static final int MORE_NEWS = 10089;
    public static final int SLIDE_REQUEST = 10087;
    public static final int USER_LOGIN_GUIDE = -1;
    public static String showKeyboardArticleId;
    protected AsyncDataLoader a = null;
    private ArrayList<Object> b = new ArrayList();
    private ArrayList<Object> c = new ArrayList();
    private ViewPager d;
    private b e;
    private int f;
    private int g;
    private int h = 0;
    private boolean i = true;
    private Map<Integer, Integer> j = new HashMap();
    private boolean k = false;
    private boolean l = true;
    private int m = 0;
    private String n = null;
    private int o = 1;
    private boolean p = false;
    private Runnable q = new bl(this);
    private boolean r;

    class a implements OnAsyncLoadListener {
        final /* synthetic */ SlideActivity a;
        private String b = "";

        public a(SlideActivity slideActivity) {
            this.a = slideActivity;
        }

        public void onPrepareListener() {
            this.a.k = true;
            StringBuffer stringBuffer = new StringBuffer(this.a.n);
            if (this.a.n.contains("?")) {
                stringBuffer.append("&page=").append(this.a.o);
            } else {
                stringBuffer.append("?page=").append(this.a.o);
            }
            if (UIHelper.isNightTheme()) {
                stringBuffer.append("&theme=night");
            }
            stringBuffer.append("&count=").append(30);
            String string = ReadQiushi.getString();
            if (string != null && string.length() > 2) {
                stringBuffer.append("&readarticles=").append(string);
            }
            this.b = stringBuffer.indexOf("?") == -1 ? this.a.n : stringBuffer.toString();
        }

        public String onStartListener() {
            String str = null;
            try {
                str = HttpClient.getIntentce().get(this.b);
            } catch (QiushibaikeException e) {
                e.printStackTrace();
            }
            return str;
        }

        public void onFinishListener(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.a.a(str);
            }
        }
    }

    class b extends FragmentPagerAdapter {
        final /* synthetic */ SlideActivity a;
        private Map<Integer, String> b = new HashMap();
        private OnLoadMoreSlideItemsListener c;

        public b(SlideActivity slideActivity, FragmentManager fragmentManager, OnLoadMoreSlideItemsListener onLoadMoreSlideItemsListener) {
            this.a = slideActivity;
            super(fragmentManager);
            this.c = onLoadMoreSlideItemsListener;
        }

        public int getCount() {
            return this.a.c.size();
        }

        public Fragment getItem(int i) {
            Object obj = this.a.c.get(i);
            if (obj instanceof Article) {
                return SingleArticleFragment.newInstance((Article) obj);
            }
            if (obj instanceof GdtAdItemData) {
                return new GdtAdFragment((GdtAdItemData) obj);
            }
            if (obj instanceof BaiduAdItemData) {
                return new BdAdFragment((BaiduAdItemData) obj);
            }
            if (obj instanceof QbAdItem) {
                return new QbAdFragment((QbAdItem) obj, i);
            }
            if (obj instanceof QhAdItemData) {
                return new QHAdFragment((QhAdItemData) obj);
            }
            if (obj instanceof CircleTopic) {
                return TopicFragment.newInstance((CircleTopic) obj);
            }
            if (obj instanceof CircleTopicBanner) {
                return CircleTopicBannerFragment.newInstance((CircleTopicBanner) obj);
            }
            if (obj instanceof LivePackage) {
                return LiveRecommendFragment.newInstance((LivePackage) obj, i);
            }
            if (obj instanceof UserLoginGuideCard) {
                return new LoginGuideCardFragment();
            }
            if (obj instanceof GroupRecommend) {
                return new GroupRecommendFragment();
            }
            if (obj instanceof CircleArticle) {
                return CircleVideoInQsFragment.newInstance((CircleArticle) obj, i);
            }
            if (obj instanceof Qsjx) {
                return QsjxFragment.newInstance((Qsjx) obj, i);
            }
            if (!(obj instanceof News)) {
                return null;
            }
            News news = (News) obj;
            if (news.isOneImageNews()) {
                return NewsRecommendOneImage.newInstance(news, i);
            }
            return NewsRecommendThreeImage.newInstance(news, i);
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            if (this.a.c.size() - i < 5) {
                this.c.onLoadMoreSlideItem();
            }
            if (!this.b.containsKey(Integer.valueOf(i))) {
                this.b.put(Integer.valueOf(i), a(viewGroup.getId(), (long) i));
            }
            return super.instantiateItem(viewGroup, i);
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            super.destroyItem(viewGroup, i, obj);
        }

        public void update(int i) {
            Fragment findFragmentByTag = this.a.getSupportFragmentManager().findFragmentByTag((String) this.b.get(Integer.valueOf(i)));
            if (findFragmentByTag != null && (findFragmentByTag instanceof SingleArticleFragment)) {
                SingleArticleFragment singleArticleFragment = (SingleArticleFragment) findFragmentByTag;
                singleArticleFragment.initVoteState();
                singleArticleFragment.initVoteInfo();
                notifyDataSetChanged();
            }
        }

        private String a(int i, long j) {
            return "android:switcher:" + i + Config.TRACE_TODAY_VISIT_SPLIT + j;
        }
    }

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    protected String f() {
        return null;
    }

    protected int a() {
        return R.layout.slide_main_view;
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        Intent intent = getIntent();
        this.f = intent.getIntExtra("position", 0);
        this.n = intent.getStringExtra("url");
        this.o = intent.getIntExtra(ParamKey.PAGE, 1);
        this.i = intent.getBooleanExtra("hasAd", true);
        this.b = QsbkApp.currentDataSource;
        if (this.b == null || this.b.size() < this.f) {
            finish();
            return;
        }
        this.d = (ViewPager) findViewById(R.id.slide_viewpager);
        this.d.setBackgroundColor(getResources().getColor(UIHelper.isNightTheme() ? R.color.main_bg_night : R.color.main_bg));
        j();
        this.e = new b(this, getSupportFragmentManager(), this);
        this.d.setAdapter(this.e);
        this.d.setOnPageChangeListener(new bm(this));
        this.d.setCurrentItem(this.g);
        setTitle(i());
        KeyboardUtils.setOnKeyboardVisibleChangeListener(this, new bn(this));
    }

    private String i() {
        if (this.g >= this.c.size()) {
            return "糗事百科";
        }
        Object obj = this.c.get(this.g);
        if (obj instanceof Article) {
            return "糗事" + ((Article) obj).id;
        }
        if ((obj instanceof GdtAdItemData) || (obj instanceof BaiduAdItemData) || (obj instanceof QbAdItem) || (obj instanceof QhAdItemData)) {
            return "推广";
        }
        if (obj instanceof CircleTopic) {
            return "话题";
        }
        if (obj instanceof LivePackage) {
            return "直播";
        }
        if (obj instanceof UserLoginGuideCard) {
            return "登录";
        }
        if (obj instanceof GroupRecommend) {
            return "群组";
        }
        if (obj instanceof CircleArticle) {
            return "糗友圈视频";
        }
        if (obj instanceof Qsjx) {
            return "糗事精选";
        }
        if (obj instanceof News) {
            return "糗闻";
        }
        return "糗事百科";
    }

    private void j() {
        if (this.b != null) {
            for (int i = this.h; i < this.b.size(); i++) {
                Object obj = this.b.get(i);
                if (!this.j.containsKey(Integer.valueOf(obj.hashCode()))) {
                    if (obj instanceof Article) {
                        this.c.add(obj);
                        this.j.put(Integer.valueOf(obj.hashCode()), Integer.valueOf(i));
                        if (i == this.f && this.c.size() > 1) {
                            this.g = this.c.size() - 1;
                        }
                    } else if ((obj instanceof GdtAdItemData) || (obj instanceof BaiduAdItemData) || (obj instanceof QbAdItem) || (obj instanceof QhAdItemData)) {
                        this.c.add(obj);
                        this.j.put(Integer.valueOf(obj.hashCode()), Integer.valueOf(i));
                    } else if (obj instanceof CircleTopic) {
                        this.c.add(obj);
                        this.j.put(Integer.valueOf(obj.hashCode()), Integer.valueOf(i));
                    } else if (obj instanceof LivePackage) {
                        this.c.add(obj);
                        this.j.put(Integer.valueOf(obj.hashCode()), Integer.valueOf(i));
                    } else if (obj instanceof UserLoginGuideCard) {
                        this.c.add(obj);
                        this.j.put(Integer.valueOf(-1), Integer.valueOf(i));
                    } else if (obj instanceof GroupRecommend) {
                        this.c.add(obj);
                        this.j.put(Integer.valueOf(obj.hashCode()), Integer.valueOf(i));
                    } else if (obj instanceof CircleArticle) {
                        this.c.add(obj);
                        this.j.put(Integer.valueOf(obj.hashCode()), Integer.valueOf(i));
                    } else if (obj instanceof Qsjx) {
                        this.c.add(obj);
                        this.j.put(Integer.valueOf(obj.hashCode()), Integer.valueOf(i));
                    } else if (obj instanceof News) {
                        this.c.add(obj);
                        this.j.put(Integer.valueOf(obj.hashCode()), Integer.valueOf(i));
                    }
                }
            }
            this.h = this.b.size();
        }
    }

    private void k() {
        this.g = this.d.getCurrentItem();
        if (this.g < this.c.size() - 1) {
            this.d.setCurrentItem(this.g + 1);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        l();
        return true;
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        Fragment findFragmentByTag = getSupportFragmentManager().findFragmentByTag((String) this.e.b.get(Integer.valueOf(this.g)));
        if (findFragmentByTag instanceof OnBackPressListener) {
            OnBackPressListener onBackPressListener = (OnBackPressListener) findFragmentByTag;
            if (onBackPressListener != null && onBackPressListener.onBackPressed()) {
                return true;
            }
        }
        if (i != 4 || keyEvent.getRepeatCount() != 0) {
            return super.onKeyDown(i, keyEvent);
        }
        l();
        return true;
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i == Constants.REQUEST_LOGIN && i2 == -1) {
            if (UserLoginGuideCard.handleQQloginRequest(i, i2, intent)) {
                k();
            } else {
                LoginGuideDialog.handleQQloginRequest(i, i2, intent);
            }
        }
        super.onActivityResult(i, i2, intent);
    }

    private void l() {
        int intValue;
        QsbkApp.currentDataSource = this.b;
        Object obj = this.c.get(this.g);
        if (obj instanceof UserLoginGuideCard) {
            intValue = ((Integer) this.j.get(Integer.valueOf(-1))).intValue();
        } else {
            intValue = ((Integer) this.j.get(Integer.valueOf(obj.hashCode()))).intValue();
        }
        Intent intent = new Intent();
        intent.putExtra("position", intValue);
        intent.putExtra(ParamKey.PAGE, this.o);
        setResult(SLIDE_REQUEST, intent);
        finish();
    }

    protected void onResume() {
        super.onResume();
        if (this.e != null && this.g < this.c.size()) {
            this.e.update(this.g);
        }
        if (this.p) {
            a(false);
        } else {
            this.d.postDelayed(this.q, 100);
        }
    }

    private void a(boolean z) {
        Fragment findFragmentByTag = getSupportFragmentManager().findFragmentByTag((String) this.e.b.get(Integer.valueOf(this.g)));
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments != null) {
            this.p = true;
            for (Fragment fragment : fragments) {
                if (fragment instanceof IPageableFragment) {
                    if (this.c != null && this.c.size() >= this.g) {
                        Object obj = this.c.get(this.g);
                        if ((obj instanceof Article) && (fragment instanceof SingleArticleFragment) && TextUtils.equals(showKeyboardArticleId, ((Article) obj).id)) {
                            ((SingleArticleFragment) fragment).c();
                            showKeyboardArticleId = "";
                        }
                        if (fragment != findFragmentByTag || z) {
                            ((IPageableFragment) fragment).doPause();
                            ((IPageableFragment) fragment).doStop();
                        } else {
                            ((IPageableFragment) fragment).doResume();
                        }
                    } else {
                        return;
                    }
                }
            }
        }
    }

    protected void onStop() {
        super.onStop();
        a(true);
    }

    public void onLoadMoreSlideItem() {
        if (!TextUtils.isEmpty(this.n) && this.o > 0 && this.l && !this.k) {
            this.a = new AsyncDataLoader(new a(this), "qsbk-slide-more");
            this.a.execute(new Void[0]);
        }
    }

    protected void a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            int optInt = jSONObject.optInt(NotificationCompat.CATEGORY_ERROR);
            if (optInt == 0) {
                JSONArray optJSONArray = jSONObject.optJSONArray("items");
                this.m = jSONObject.optInt("total");
                if (this.m != 0 || (optJSONArray != null && optJSONArray.length() != 0)) {
                    if (this.m <= 0 || !(optJSONArray == null || optJSONArray.length() == 0)) {
                        int i;
                        if (this.m > ((this.o - 1) * 30) + optJSONArray.length()) {
                            this.l = true;
                        } else {
                            this.l = false;
                        }
                        int length = optJSONArray.length();
                        this.h = this.b.size();
                        for (i = 0; i < length; i++) {
                            try {
                                if (optJSONArray.optJSONObject(i) != null) {
                                    Article rssArticle = new RssArticle(optJSONArray.optJSONObject(i));
                                    if (!(this.b.contains(rssArticle) || ReportArticle.mReportArtcicle.keySet().contains(rssArticle.id))) {
                                        this.b.add(rssArticle);
                                    }
                                }
                            } catch (QiushibaikeException e) {
                            }
                        }
                        if (this.i) {
                            i = (this.o - 1) * 30;
                            while (i + 5 > this.b.size()) {
                                i -= 30;
                            }
                            FeedsAd.getInstance().insertFeedAd(i, this.b, false);
                        }
                        if (this.l) {
                            this.o++;
                        }
                        j();
                        this.k = false;
                        this.e.notifyDataSetChanged();
                    }
                }
            } else if (optInt != SimpleHttpTask.ERROR_DOUBLE_LOGIN.intValue() || QsbkApp.currentUser == null) {
                Object optString = jSONObject.optString("err_msg");
                if (!TextUtils.isEmpty(optString)) {
                    ToastAndDialog.makeNegativeToast(QsbkApp.mContext, optString, Integer.valueOf(0)).show();
                }
            } else {
                QsbkApp.mContext.startActivity(ReAuthActivity.getIntent(QsbkApp.mContext));
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    protected void g() {
        if (this != null) {
            UIHelper.hideKeyboard(this);
        }
    }
}
