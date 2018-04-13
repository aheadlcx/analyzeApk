package qsbk.app.live.debug;

import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import qsbk.app.core.model.CommonVideo;
import qsbk.app.core.model.User;
import qsbk.app.core.net.NetRequest;
import qsbk.app.core.net.UrlConstants;
import qsbk.app.core.net.response.BaseResponse;
import qsbk.app.core.ui.base.BaseFragment;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.ToastUtil;
import qsbk.app.core.utils.WindowUtils;
import qsbk.app.core.widget.EmptyPlaceholderView;
import qsbk.app.core.widget.refresh.SwipeRefreshLayoutBoth;
import qsbk.app.core.widget.refresh.SwipeRefreshLayoutBoth$SwipeRefreshLayoutDirection;
import qsbk.app.live.R;

public class LiveDebugListFragment extends BaseFragment {
    public static final int COVER_HEIGHT = ((int) (((float) COVER_WIDTH) * 0.75f));
    public static final int COVER_WIDTH = (WindowUtils.getScreenWidth() / 2);
    protected static int c = 2;
    protected RecyclerView a;
    protected GridLayoutManager b;
    protected ArrayList<CommonVideo> d = new ArrayList();
    protected boolean e = false;
    protected boolean f = true;
    protected SwipeRefreshLayoutBoth g;
    protected LiveDebugListAdapter h;
    protected TextView i;
    protected EmptyPlaceholderView j;
    protected Handler k = new Handler();
    private int l = 1;
    private TextView m;
    private int n;
    private ImageView o;
    private ImageView p;

    public static LiveDebugListFragment newInstance() {
        return new LiveDebugListFragment();
    }

    protected int getLayoutId() {
        return R.layout.fragment_debug_livlist;
    }

    protected void initView() {
        this.i = (TextView) findViewById(R.id.tv_title);
        this.g = (SwipeRefreshLayoutBoth) findViewById(R.id.refresher);
        this.a = (RecyclerView) findViewById(R.id.recyclerview);
        this.j = (EmptyPlaceholderView) findViewById(R.id.empty);
        this.m = (TextView) $(R.id.tv_test);
        this.o = (ImageView) $(R.id.iv_left);
        this.p = (ImageView) $(R.id.iv_right);
    }

    protected void initData() {
        i();
        this.i.setText(getTitle());
        this.g.setOnRefreshListener(new b(this));
        this.b = new GridLayoutManager(getActivity(), c);
        this.a.setLayoutManager(this.b);
        this.h = new LiveDebugListAdapter(getActivity(), this.d);
        this.a.setAdapter(this.h);
        this.a.setItemAnimator(new DefaultItemAnimator());
        this.a.setHasFixedSize(true);
        this.a.addOnScrollListener(new c(this));
        AppUtils.weakenRecyclerViewAnimations(this.a);
        a();
        this.o.setOnClickListener(new d(this));
        this.p.setOnClickListener(new e(this));
    }

    private void h() {
        if (this.b.getChildCount() + this.b.findFirstVisibleItemPosition() >= this.b.getItemCount() - c) {
            c();
            g();
            return;
        }
        this.g.setRefreshing(false);
    }

    protected void a() {
        if (this.g != null && this.j != null) {
            this.g.setVisibility(0);
            this.g.setEnabled(true);
            this.j.hide();
            this.g.post(new f(this));
        }
    }

    protected String b() {
        return "live_list_cache";
    }

    protected void c() {
        if (this.l == 1) {
            this.l++;
        }
    }

    protected void d() {
        this.l = 1;
    }

    protected String e() {
        return UrlConstants.GET_LIVE_LIST;
    }

    public String getTitle() {
        return "直播列表";
    }

    protected void a(BaseResponse baseResponse) {
        boolean z;
        if (this.l == 1) {
            this.d.clear();
            this.h.notifyDataSetChanged();
        }
        List listResponse = baseResponse.getListResponse("feeds", new g(this));
        if (listResponse == null || listResponse.size() <= 0) {
            z = false;
        } else {
            z = true;
        }
        this.f = z;
        if (this.f) {
            User user = AppUtils.getInstance().getUserInfoProvider().getUser();
            for (int i = 0; i < listResponse.size(); i++) {
                CommonVideo commonVideo = (CommonVideo) listResponse.get(i);
                if (user == null || commonVideo.author.getOriginId() != user.getOriginId() || commonVideo.author.getOrigin() != user.getOrigin()) {
                    Object obj;
                    int i2 = 0;
                    while (i2 < this.d.size()) {
                        if (commonVideo.author.getOriginId() == ((CommonVideo) this.d.get(i2)).author.getOriginId() && commonVideo.author.getOrigin() == ((CommonVideo) this.d.get(i2)).author.getOrigin()) {
                            obj = null;
                            break;
                        }
                        i2++;
                    }
                    int i3 = 1;
                    if (obj != null) {
                        this.d.add(commonVideo);
                    }
                }
            }
            this.h.notifyDataSetChanged();
        } else if (this.g.isRefreshing() && this.g.getDirection() == SwipeRefreshLayoutBoth$SwipeRefreshLayoutDirection.BOTTOM) {
            ToastUtil.Short(R.string.no_more_content);
        }
        this.l++;
    }

    protected Map<String, String> f() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put(ParamKey.PAGE, this.l + "");
        return hashMap;
    }

    protected void g() {
        this.e = true;
        NetRequest.getInstance().get(e(), new h(this), b());
    }

    private void i() {
        for (int i = 1; i <= 6; i++) {
            if (UrlConstants.getApiDomain().contains("test" + i)) {
                this.m.setText("测试环境" + i);
                this.n = i;
                return;
            }
        }
        this.m.setText("正式环境");
        this.n = 0;
    }

    private void j() {
        if (this.n == 0) {
            UrlConstants.setLiveDomain(UrlConstants.LIVE_DOMAIN);
            UrlConstants.setLiveHttpsDomain(UrlConstants.LIVE_DOMAIN_HTTPS);
            UrlConstants.setApiDomain(UrlConstants.API_DOMAIN);
            UrlConstants.setPayDomain(UrlConstants.PAY_DOMAIN);
            ToastUtil.Short("切到正式环境");
            this.m.setText("正式环境");
        } else if (this.n == 1) {
            UrlConstants.setLiveDomain(UrlConstants.LIVE_TEST_DOMAIN);
            UrlConstants.setLiveHttpsDomain(UrlConstants.LIVE_TEST_DOMAIN_HTTPS);
            UrlConstants.setApiDomain(UrlConstants.TEST_DOMAIN);
            UrlConstants.setPayDomain(UrlConstants.PAY_TEST_DOMAIN);
            ToastUtil.Short("切到测试环境1");
            this.m.setText("测试环境1");
        } else {
            UrlConstants.setLiveDomain(UrlConstants.LIVE_TEST_DOMAIN.replace("livetest1", "livetest" + this.n));
            UrlConstants.setLiveHttpsDomain(UrlConstants.LIVE_TEST_DOMAIN_HTTPS.replace("livetest1", "livetest" + this.n));
            UrlConstants.setApiDomain(UrlConstants.TEST_DOMAIN.replace("test1", "test" + this.n));
            UrlConstants.setPayDomain(UrlConstants.PAY_TEST_DOMAIN);
            ToastUtil.Short("切到测试环境" + this.n);
            this.m.setText("测试环境" + this.n);
        }
        a();
    }
}
