package qsbk.app.live.ui.family;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import qsbk.app.core.net.NetRequest;
import qsbk.app.core.net.UrlConstants;
import qsbk.app.core.ui.base.BaseFragment;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.Constants;
import qsbk.app.core.web.ui.WebActivity;
import qsbk.app.core.widget.EmptyPlaceholderView;
import qsbk.app.core.widget.refresh.SwipeRefreshLayoutBoth;
import qsbk.app.live.R;
import qsbk.app.live.model.FamilyRankData;
import qsbk.app.live.widget.FamilyLevelView;

public class FamilyRankFragment extends BaseFragment implements OnClickListener {
    public static final int REQUEST_CODE_LOGIN = 1001;
    public static String family_help_url = "https://static.app-remix.com/html/family.html";
    protected RecyclerView a;
    protected LinearLayoutManager b;
    protected ArrayList<FamilyRankData> c = new ArrayList();
    FamilyRankData d;
    private SwipeRefreshLayoutBoth e;
    private EmptyPlaceholderView f;
    private FamilyRankAdapter g;
    private int h = 0;
    private boolean i = false;
    private boolean j = true;
    private FrameLayout k;
    private ImageView l;
    private ImageView m;
    private FrameLayout n;
    private TextView o;
    private int p = 0;

    protected int getLayoutId() {
        return R.layout.fragment_family_rank;
    }

    protected void initView() {
        this.k = (FrameLayout) $(R.id.live_family_rank_banner);
        this.l = (ImageView) $(R.id.iv_create);
        this.m = (ImageView) $(R.id.iv_about);
        this.l.setOnClickListener(this);
        this.m.setOnClickListener(this);
        this.e = (SwipeRefreshLayoutBoth) findViewById(R.id.refresher);
        this.a = (RecyclerView) findViewById(R.id.recyclerview);
        this.f = (EmptyPlaceholderView) findViewById(R.id.empty);
        this.n = (FrameLayout) findViewById(R.id.live_gift_rank_me);
        if (Constants.APP_FLAG == 1) {
            LayoutParams layoutParams = (LayoutParams) this.n.getLayoutParams();
            layoutParams.bottomMargin = 0;
            this.n.setLayoutParams(layoutParams);
        }
        this.o = (TextView) $(R.id.tv_title);
        this.o.setAlpha(0.0f);
    }

    protected void initData() {
        this.b = new LinearLayoutManager(getActivity());
        this.b.setOrientation(1);
        this.a.setLayoutManager(this.b);
        this.g = new FamilyRankAdapter(getActivity(), this.c);
        this.a.setAdapter(this.g);
        this.a.setItemAnimator(new DefaultItemAnimator());
        this.a.setHasFixedSize(true);
        this.a.addOnScrollListener(new bb(this));
        this.e.setOnRefreshListener(new bc(this));
        if (Constants.APP_FLAG == 1) {
            this.m.setVisibility(8);
            this.l.setVisibility(8);
            this.e.setPadding(0, 0, 0, 0);
        }
        forceRefresh();
        e();
    }

    public void forceRefresh() {
        if (this.e != null && this.f != null && this.a != null) {
            this.e.setVisibility(0);
            this.a.smoothScrollToPosition(0);
            this.f.hide();
            this.e.post(new bd(this));
        }
    }

    private void a() {
        this.i = true;
        NetRequest.getInstance().get(UrlConstants.LIVE_RANK_FAMILY_WEEK, new be(this));
    }

    private void b() {
        if (this.b.getChildCount() + this.b.findFirstVisibleItemPosition() >= this.b.getItemCount() - 4) {
            if (this.h == 1) {
                this.h++;
            }
            a();
            return;
        }
        this.e.setRefreshing(false);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.iv_create) {
            Context activity = getActivity();
            if (activity != null) {
                activity.startActivity(new Intent(activity, FamilyBlankActivity.class));
            }
        } else if (view.getId() == R.id.iv_about) {
            WebActivity.launch(getActivity(), family_help_url, getString(R.string.family_help));
        }
    }

    private void a(FamilyRankData familyRankData) {
        if (this.n != null) {
            ((TextView) this.n.findViewById(R.id.live_gift_username)).setText(familyRankData.n);
            if (familyRankData.r > 0 && familyRankData.r <= 100) {
                ((TextView) this.n.findViewById(R.id.rank_num)).setText(familyRankData.r + "");
            } else if (familyRankData.r > 100) {
                ((TextView) this.n.findViewById(R.id.rank_num)).setText("100+");
            } else {
                ((TextView) this.n.findViewById(R.id.rank_num)).setText(R.string.giftrank_no);
            }
            AppUtils.getInstance().getImageProvider().loadAvatar((ImageView) this.n.findViewById(R.id.avatar), familyRankData.c, true);
            ((TextView) this.n.findViewById(R.id.gift_num)).setText(getString(R.string.family_fame, new Object[]{Long.toString(familyRankData.f)}));
            this.n.setOnClickListener(new bh(this, familyRankData));
            TextView textView = (TextView) this.n.findViewById(R.id.rank_change_num);
            ImageView imageView = (ImageView) this.n.findViewById(R.id.rank_change_icon);
            if (familyRankData.u == 0) {
                textView.setVisibility(8);
                imageView.setImageResource(R.drawable.live_rank_equal);
            } else if (familyRankData.u > 0) {
                textView.setVisibility(0);
                textView.setText(Math.abs(familyRankData.u) + "");
                textView.setTextColor(getResources().getColor(R.color.color_ff4468));
                imageView.setImageResource(R.drawable.live_rank_up);
            } else {
                textView.setVisibility(0);
                textView.setText(Math.abs(familyRankData.u) + "");
                textView.setTextColor(getResources().getColor(R.color.color_72d36b));
                imageView.setImageResource(R.drawable.live_rank_down);
            }
            FamilyLevelView familyLevelView = (FamilyLevelView) this.n.findViewById(R.id.fl_level);
            familyLevelView.setVisibility(0);
            if (TextUtils.isEmpty(familyRankData.b)) {
                familyLevelView.setVisibility(8);
                return;
            }
            familyLevelView.setVisibility(0);
            familyLevelView.setLevelAndName(familyRankData.l, familyRankData.b);
        }
    }

    private void c() {
        if (this.n != null && this.d != null && AppUtils.getInstance().getUserInfoProvider().isLogin()) {
            this.n.setVisibility(0);
        }
    }

    private void d() {
        if (this.n != null) {
            this.n.setVisibility(4);
        }
    }

    private void e() {
        NetRequest.getInstance().get(UrlConstants.FAMILY_MY_DATA, new bi(this), "my_family", true);
    }

    public void onResume() {
        super.onResume();
        if (!AppUtils.getInstance().getUserInfoProvider().isLogin()) {
            this.n.setVisibility(4);
        }
    }
}
