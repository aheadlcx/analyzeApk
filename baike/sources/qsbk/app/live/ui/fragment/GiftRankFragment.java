package qsbk.app.live.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.ArrayList;
import qsbk.app.core.model.User;
import qsbk.app.core.net.NetRequest;
import qsbk.app.core.net.UrlConstants;
import qsbk.app.core.ui.base.BaseFragment;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.widget.EmptyPlaceholderView;
import qsbk.app.core.widget.refresh.SwipeRefreshLayoutBoth;
import qsbk.app.live.R;
import qsbk.app.live.adapter.GiftRankAdapter;
import qsbk.app.live.model.GiftRankData;
import qsbk.app.live.ui.helper.LevelHelper;
import qsbk.app.live.widget.FamilyLevelView;

public class GiftRankFragment extends BaseFragment {
    public static final int TAB_ID_FIRST = 1;
    public static final int TAB_ID_SECOND = 2;
    private SwipeRefreshLayoutBoth a;
    private RecyclerView b;
    private LinearLayoutManager c;
    private EmptyPlaceholderView d;
    private GiftRankAdapter e;
    private int f = 0;
    private boolean g = false;
    private boolean h = true;
    private ArrayList<GiftRankData> i = new ArrayList();
    private boolean j;
    private User k;
    private int l;
    private int m;
    private FrameLayout n;
    private GiftRankData o;
    private GiftRankData p;

    public static Fragment newInstance(int i, User user, boolean z, int i2, GiftRankData giftRankData) {
        Fragment giftRankFragment = new GiftRankFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", i);
        bundle.putSerializable("user", user);
        bundle.putBoolean("is_anchor", z);
        bundle.putInt("type", i2);
        if (giftRankData != null) {
            bundle.putSerializable("anchor", giftRankData);
        }
        giftRankFragment.setArguments(bundle);
        return giftRankFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.l = arguments.getInt("id", 1);
            this.k = (User) arguments.getSerializable("user");
            this.j = arguments.getBoolean("is_anchor");
            this.m = arguments.getInt("type", 0);
            if (this.p == null) {
                this.p = (GiftRankData) arguments.getSerializable("anchor");
            }
        }
    }

    protected int getLayoutId() {
        return R.layout.fragment_gift_rank;
    }

    protected void initView() {
        this.a = (SwipeRefreshLayoutBoth) findViewById(R.id.refresher);
        this.b = (RecyclerView) findViewById(R.id.recyclerview);
        this.d = (EmptyPlaceholderView) findViewById(R.id.empty);
        this.n = (FrameLayout) findViewById(R.id.live_gift_rank_me);
        if (this.p != null) {
            this.n.setVisibility(0);
        }
    }

    protected void initData() {
        this.c = new LinearLayoutManager(getActivity());
        this.c.setOrientation(1);
        this.b.setLayoutManager(this.c);
        this.e = new GiftRankAdapter(getActivity(), this.i, this.j, this.m, this.l);
        this.b.setAdapter(this.e);
        this.b.setItemAnimator(new DefaultItemAnimator());
        this.b.setHasFixedSize(true);
        this.b.addOnScrollListener(new a(this));
        this.a.setOnRefreshListener(new b(this));
        a();
        f();
        if (this.p != null) {
            a(this.p);
        }
    }

    private void a() {
        if (this.a != null && this.d != null) {
            this.a.setVisibility(0);
            this.d.hide();
            this.a.post(new c(this));
        }
    }

    private void b() {
        this.g = true;
        String str = null;
        if (this.m == 1) {
            if (this.l == 1) {
                str = UrlConstants.LIVE_GIFT_RANK_WEEK;
            } else {
                str = UrlConstants.LIVE_GIFT_RANK;
            }
        } else if (this.m == 2) {
            if (this.l == 1) {
                str = UrlConstants.LIVE_RANK_SEND_WEEK;
            } else {
                str = UrlConstants.LIVE_RANK_RECEIVE_WEEK;
            }
        }
        NetRequest.getInstance().get(str, new d(this));
    }

    private void c() {
        if (this.c.getChildCount() + this.c.findFirstVisibleItemPosition() >= this.c.getItemCount() - 4) {
            if (this.f == 1) {
                this.f++;
            }
            b();
            return;
        }
        this.a.setRefreshing(false);
    }

    private void a(GiftRankData giftRankData) {
        if (this.n != null) {
            ((TextView) this.n.findViewById(R.id.live_gift_username)).setText(giftRankData.n);
            if (giftRankData.r > 0 && giftRankData.r <= 100) {
                ((TextView) this.n.findViewById(R.id.rank_num)).setText(giftRankData.r + "");
            } else if (giftRankData.r > 100) {
                ((TextView) this.n.findViewById(R.id.rank_num)).setText("100+");
            } else {
                ((TextView) this.n.findViewById(R.id.rank_num)).setText(R.string.giftrank_no);
            }
            AppUtils.getInstance().getImageProvider().loadAvatar((ImageView) this.n.findViewById(R.id.avatar), giftRankData.a, true);
            LevelHelper.setLevelText((TextView) this.n.findViewById(R.id.live_gift_user_lv), giftRankData.l);
            if (this.m == 2 && this.l == 1) {
                ((TextView) this.n.findViewById(R.id.gift_num)).setText(Long.toString(giftRankData.c) + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + getString(R.string.live_diamond));
            } else {
                ((TextView) this.n.findViewById(R.id.gift_num)).setText(Long.toString(giftRankData.c) + getString(R.string.live_gift_certificate));
            }
            this.n.setOnClickListener(new g(this, giftRankData));
            TextView textView = (TextView) this.n.findViewById(R.id.rank_change_num);
            ImageView imageView = (ImageView) this.n.findViewById(R.id.rank_change_icon);
            if (this.p != null) {
                imageView.setVisibility(8);
            } else {
                imageView.setVisibility(0);
                if (giftRankData.u == 0) {
                    textView.setVisibility(8);
                    imageView.setImageResource(R.drawable.live_rank_equal);
                } else if (giftRankData.u > 0) {
                    textView.setVisibility(0);
                    textView.setText(Math.abs(giftRankData.u) + "");
                    textView.setTextColor(getResources().getColor(R.color.color_ff4468));
                    imageView.setImageResource(R.drawable.live_rank_up);
                } else {
                    textView.setVisibility(0);
                    textView.setText(Math.abs(giftRankData.u) + "");
                    textView.setTextColor(getResources().getColor(R.color.color_72d36b));
                    imageView.setImageResource(R.drawable.live_rank_down);
                }
            }
            if (this.m == 1 && this.l == 2) {
                imageView.setVisibility(8);
                textView.setVisibility(8);
            }
            if (this.p == null) {
                this.n.findViewById(R.id.iv_me).setVisibility(0);
            } else {
                this.n.findViewById(R.id.iv_me).setVisibility(4);
            }
            FamilyLevelView familyLevelView = (FamilyLevelView) this.n.findViewById(R.id.fl_level);
            if (TextUtils.isEmpty(giftRankData.d)) {
                familyLevelView.setVisibility(8);
                return;
            }
            familyLevelView.setVisibility(0);
            familyLevelView.setLevelAndName(giftRankData.fl, giftRankData.d);
        }
    }

    private void d() {
        if (this.n != null && this.o != null) {
            this.n.setVisibility(0);
        }
    }

    private void e() {
        if (this.n != null && this.p == null) {
            this.n.setVisibility(4);
        }
    }

    private void f() {
        String str = null;
        if (this.m == 1) {
            if (this.l == 1) {
                str = UrlConstants.LIVE_MYRANK_WEEK;
            } else {
                str = UrlConstants.LIVE_MYRANK_TOTAL;
            }
        } else if (this.m == 2) {
            if (this.l == 1) {
                str = UrlConstants.LIVE_MYRANK_SEND_WEEK;
            } else {
                str = UrlConstants.LIVE_MYRANK_RECEIVE_WEEK;
            }
        }
        NetRequest.getInstance().get(str, new h(this), "my_rank_" + this.l, true);
    }
}
