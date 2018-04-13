package qsbk.app.fragments;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.BaseTabActivity.ILoadingState;
import qsbk.app.adapter.QiuYouCircleAdapter;
import qsbk.app.core.AsyncTask;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.model.CircleArticle;
import qsbk.app.nearby.api.LocationHelper;
import qsbk.app.nearby.api.LocationHelper.LocationCallBack;
import qsbk.app.utils.CircleArticleBus;
import qsbk.app.utils.CircleArticleBus.OnArticleUpdateListener;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.PtrLayout;
import qsbk.app.widget.PtrLayout.PtrListener;
import qsbk.app.widget.TipsHelper;

public class MyQiuYouDynamicFragment extends BaseNearbyFragment implements ILoadingState, LocationCallBack, OnArticleUpdateListener, PtrListener {
    LinearLayout a;
    private int b = 1;
    private ArrayList<Object> c;
    private PtrLayout d;
    private ListView e;
    private QiuYouCircleAdapter f;
    private TipsHelper g;
    private String o;
    private View p;
    private boolean q;

    public void onAttach(Activity activity) {
        this.o = getArguments().getString("uid");
        this.q = getArguments().getBoolean("fromManage");
        super.onAttach(activity);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        CircleArticleBus.register(this);
    }

    public void onViewCreated(View view, Bundle bundle) {
        onLocateDone();
    }

    public void onDestroy() {
        CircleArticleBus.unregister(this);
        super.onDestroy();
    }

    public void onFillContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i;
        int i2 = -14013903;
        View inflate = layoutInflater.inflate(R.layout.layout_ptr_listview, viewGroup, false);
        this.g = new TipsHelper(inflate.findViewById(R.id.tips));
        this.d = (PtrLayout) inflate.findViewById(R.id.ptr);
        this.e = (ListView) inflate.findViewById(R.id.listview);
        viewGroup.addView(inflate);
        this.d.setLoadMoreEnable(false);
        this.d.setPtrListener(this);
        if (UIHelper.isNightTheme()) {
            this.e.setDivider(new ColorDrawable(-16777216));
            this.e.setDividerHeight((int) (getResources().getDisplayMetrics().density / 2.0f));
            this.e.setBackgroundColor(getResources().getColor(R.color.main_bg_night));
        } else {
            this.e.setDivider(null);
            this.e.setDividerHeight(0);
            this.e.setBackgroundColor(getResources().getColor(R.color.main_bg));
        }
        this.c = new ArrayList();
        this.f = new QiuYouCircleAdapter(this.c, getActivity(), this);
        this.p = LayoutInflater.from(getActivity()).inflate(R.layout.my_dynamic_listview_foot, null);
        this.a = (LinearLayout) this.p.findViewById(R.id.foot_lin);
        View findViewById = this.p.findViewById(R.id.foot_left_line);
        View findViewById2 = this.p.findViewById(R.id.foot_right_line);
        TextView textView = (TextView) this.p.findViewById(R.id.foot_remind);
        this.a.setBackgroundColor(UIHelper.isNightTheme() ? -15132387 : -855310);
        if (UIHelper.isNightTheme()) {
            i = -14013903;
        } else {
            i = -2236961;
        }
        findViewById.setBackgroundColor(i);
        if (!UIHelper.isNightTheme()) {
            i2 = -2236961;
        }
        findViewById2.setBackgroundColor(i2);
        textView.setTextColor(UIHelper.isNightTheme() ? -12829625 : -5197644);
        textView.setText("到底啦~");
        this.e.addFooterView(this.p);
        this.p.setVisibility(8);
        this.e.setAdapter(this.f);
        this.e.setOnItemClickListener(new gy(this));
    }

    private void e() {
        String str = "";
        Object[] objArr;
        LocationHelper locationHelper;
        if (QsbkApp.currentUser == null || !QsbkApp.currentUser.userId.equals(this.o)) {
            str = Constants.CIRCLE_USER_LIST;
            objArr = new Object[4];
            locationHelper = this.l;
            objArr[2] = String.valueOf(LocationHelper.getLatitude());
            locationHelper = this.l;
            objArr[3] = String.valueOf(LocationHelper.getLongitude());
            str = String.format(str, objArr);
        } else if (this.q) {
            str = String.format(Constants.CIRCLE_MY_LIST, new Object[]{Integer.valueOf(this.b)});
        } else {
            str = Constants.CIRCLE_USER_LIST;
            objArr = new Object[4];
            locationHelper = this.l;
            objArr[2] = String.valueOf(LocationHelper.getLatitude());
            locationHelper = this.l;
            objArr[3] = String.valueOf(LocationHelper.getLongitude());
            str = String.format(str, objArr);
        }
        new SimpleHttpTask(str, new gz(this)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private void f() {
        if (this.c == null || this.c.size() == 0) {
            if (QsbkApp.currentUser == null || !QsbkApp.currentUser.userId.equals(this.o)) {
                this.g.hide();
            } else {
                this.g.set(UIHelper.getEmptyImg(), getString(R.string.push_some_feeds));
                this.g.show();
            }
            this.a.setVisibility(8);
            return;
        }
        this.g.hide();
        this.a.setVisibility(0);
    }

    public void onRefresh() {
        a(false);
        this.b = 1;
        e();
    }

    public void onLoadMore() {
        e();
    }

    public void onLocateDone() {
        e();
    }

    public void onArticleCreate(CircleArticle circleArticle) {
        if (this.c != null && this.c.size() != 0 && QsbkApp.currentUser != null && QsbkApp.currentUser.userId.equals(this.o)) {
            this.c.add(0, circleArticle);
            this.f.notifyDataSetChanged();
            f();
        }
    }

    public void onArticleUpdate(CircleArticle circleArticle) {
        if (this.c != null && this.c.size() != 0) {
            for (int i = 0; i < this.c.size(); i++) {
                if (this.c.get(i) instanceof CircleArticle) {
                    CircleArticle circleArticle2 = (CircleArticle) this.c.get(i);
                    if (TextUtils.equals(circleArticle2.id, circleArticle.id)) {
                        circleArticle2.updateWith(circleArticle);
                        break;
                    }
                }
            }
            this.f.notifyDataSetChanged();
            f();
        }
    }

    public void onArticleDelete(CircleArticle circleArticle) {
        if (this.c != null && this.c.size() != 0 && this.c.contains(circleArticle)) {
            this.c.remove(circleArticle);
            this.f.notifyDataSetChanged();
        }
    }
}
