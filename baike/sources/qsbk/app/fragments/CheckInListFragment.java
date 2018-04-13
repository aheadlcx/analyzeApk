package qsbk.app.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.R;
import qsbk.app.adapter.CheckInTopAdapter;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.model.UserInfo;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.PtrLayout;
import qsbk.app.widget.PtrLayout.PtrListener;
import qsbk.app.widget.TipsHelper;

public class CheckInListFragment extends Fragment implements PtrListener {
    public static final int TYPE_FANS = 0;
    public static final int TYPE_TOTAL = 1;
    public static final int TYPE_WEEK = 2;
    ArrayList a = new ArrayList();
    SimpleHttpTask b;
    private PtrLayout c;
    private ListView d;
    private BaseAdapter e;
    private View f;
    private boolean g = false;
    private boolean h;
    private int i;
    private boolean j;
    private TipsHelper k;
    private ProgressBar l;
    private int m;
    private String n;

    public static class CheckInInfo {
        public int days;
        public boolean isSigned;
        public int rank;
        public String signDate;
        public UserInfo user = new UserInfo();

        public CheckInInfo(JSONObject jSONObject) {
            this.user.parseBaseInfo(jSONObject);
            this.isSigned = jSONObject.optBoolean("is_sign");
            this.signDate = jSONObject.optString("sign_date");
            this.rank = jSONObject.optInt("rank");
            this.days = jSONObject.optInt("days");
        }

        public static ArrayList<CheckInInfo> parseJsonArray(JSONArray jSONArray) {
            ArrayList<CheckInInfo> arrayList = new ArrayList();
            if (jSONArray != null && jSONArray.length() > 0) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    try {
                        arrayList.add(new CheckInInfo(jSONArray.getJSONObject(i)));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            return arrayList;
        }
    }

    public static CheckInListFragment newInstance(String str, int i) {
        CheckInListFragment checkInListFragment = new CheckInListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id", str);
        bundle.putInt("type", i);
        checkInListFragment.setArguments(bundle);
        return checkInListFragment;
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.m = arguments.getInt("type");
            this.n = arguments.getString("id");
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.layout_ptr_listview, null);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        a(view);
        b();
    }

    private void a(View view) {
        int i;
        int i2 = -14013903;
        this.c = (PtrLayout) view.findViewById(R.id.ptr);
        this.d = (ListView) view.findViewById(R.id.listview);
        this.c.setLoadMoreEnable(false);
        this.c.setPtrListener(this);
        this.e = a();
        this.k = new TipsHelper(view.findViewById(R.id.tips));
        this.l = (ProgressBar) view.findViewById(R.id.progress);
        this.f = LayoutInflater.from(getActivity()).inflate(R.layout.qiushi_listview_foot, null);
        LinearLayout linearLayout = (LinearLayout) this.f.findViewById(R.id.foot_lin);
        View findViewById = this.f.findViewById(R.id.foot_left_line);
        View findViewById2 = this.f.findViewById(R.id.foot_right_line);
        TextView textView = (TextView) this.f.findViewById(R.id.foot_remind);
        linearLayout.setBackgroundColor(UIHelper.isNightTheme() ? -15132387 : -855310);
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
        textView.setText("没有更多了");
        this.d.addFooterView(this.f);
        this.f.setVisibility(this.g ? 0 : 8);
        this.d.setAdapter(this.e);
        this.c.post(new ak(this));
    }

    private BaseAdapter a() {
        return new CheckInTopAdapter(this.a, getActivity());
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    private void a(int i) {
        this.h = true;
        if (i == 1) {
            this.k.hide();
        }
        String str = "";
        if (this.m == 1) {
            str = String.format(Constants.CIRCLE_SIGN_RANK_TOTAL, new Object[]{Integer.valueOf(i), this.n});
        } else if (this.m == 0) {
            str = String.format(Constants.CIRCLE_SIGN_RANK_FANS, new Object[]{Integer.valueOf(i), this.n});
        } else if (this.m == 2) {
            str = String.format(Constants.CIRCLE_SIGN_RANK_WEEK, new Object[]{Integer.valueOf(i), this.n});
        }
        if (!(this.b == null || this.b.isCancelled())) {
            this.b.cancel(true);
        }
        this.b = new SimpleHttpTask(str, new al(this, i));
        this.b.execute();
    }

    private void b() {
        this.l.setVisibility(0);
    }

    private void c() {
        if (this.l != null && this.l.isShown()) {
            this.l.setVisibility(8);
        }
    }

    public void onRefresh() {
        if (!this.h) {
            a(1);
        }
    }

    public void onLoadMore() {
        if (!this.h) {
            a(this.i + 1);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.b != null && !this.b.isCancelled()) {
            this.b.cancel(true);
        }
    }
}
