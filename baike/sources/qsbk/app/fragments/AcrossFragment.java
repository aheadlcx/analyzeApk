package qsbk.app.fragments;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.imagepipeline.cache.MediaVariationsIndexDatabase.IndexEntry;
import org.json.JSONArray;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseArticleListViewFragment;
import qsbk.app.adapter.BaseImageAdapter;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.model.AcrossTips;
import qsbk.app.model.ArticleListConfig;
import qsbk.app.model.RssArticle;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.PtrLayout;

public class AcrossFragment extends BaseArticleListViewFragment {
    private static final String Q = AcrossFragment.class.getSimpleName();
    private String R;
    private String S;
    private ChangeDateListener T;
    private boolean U = false;
    private int V = 1;
    private boolean W = false;
    private AcrossTips X = new AcrossTips();

    public interface ChangeDateListener {
        void changeDate(String str);
    }

    public static AcrossFragment newInstance() {
        return new AcrossFragment();
    }

    public static AcrossFragment newInstance(ArticleListConfig articleListConfig) {
        AcrossFragment acrossFragment = new AcrossFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("config", articleListConfig);
        acrossFragment.setArguments(bundle);
        return acrossFragment;
    }

    protected void a() {
        super.a();
        ArticleListConfig articleListConfig = (ArticleListConfig) getArguments().getSerializable("config");
        if (articleListConfig == null || !Constants.CONTENT_DOMAINS.equalsIgnoreCase(Constants.QIUBAI_DOMAINS)) {
            this.u = Constants.URL_ACROSS;
            this.v = AcrossFragment.class.getSimpleName();
            this.w = false;
            return;
        }
        this.u = articleListConfig.mUrl;
        this.v = articleListConfig.mUniqueName;
        this.w = articleListConfig.mIsShuffle;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.across_layout_ptr_listview, null);
        if (bundle != null) {
            this.R = bundle.getString(IndexEntry.COLUMN_NAME_DATE);
        }
        a(inflate);
        if (UIHelper.isNightTheme()) {
            this.m.setDivider(new ColorDrawable(-16777216));
            this.m.setDividerHeight((int) (getResources().getDisplayMetrics().density / 2.0f));
        } else {
            this.m.setDivider(null);
            this.m.setDividerHeight(0);
        }
        this.F = new a(this, this.m);
        this.F.setEnable(false);
        if (!isSelected()) {
            this.F.stopAll();
        } else if (QsbkApp.getInstance().isAutoPlayConfiged()) {
            this.F.autoPlay();
        }
        b = 0;
        return inflate;
    }

    protected void a(View view) {
        int i;
        int i2 = -14013903;
        this.l = (PtrLayout) view.findViewById(R.id.ptr);
        this.m = (ListView) view.findViewById(R.id.listview);
        this.l.setLoadMoreEnable(false);
        this.l.setPtrListener(this);
        this.l.setOnScrollListener(this);
        this.i = b();
        this.footView = LayoutInflater.from(getActivity()).inflate(R.layout.across_listview_foot, null);
        LinearLayout linearLayout = (LinearLayout) this.footView.findViewById(R.id.foot_lin);
        View findViewById = this.footView.findViewById(R.id.foot_left_line);
        View findViewById2 = this.footView.findViewById(R.id.foot_right_line);
        TextView textView = (TextView) this.footView.findViewById(R.id.foot_remind);
        ImageView imageView = (ImageView) this.footView.findViewById(R.id.foot_drawable);
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
        imageView.setImageResource(UIHelper.isNightTheme() ? R.drawable.cross_clock_night : R.drawable.cross_clock);
        this.footView.setOnClickListener(new b(this));
        this.m.addFooterView(this.footView);
        this.footView.setVisibility(this.E ? 0 : 8);
        this.m.setAdapter(this.i);
        this.G = (RelativeLayout) view.findViewById(R.id.across_tips_view);
        this.H = (TextView) this.G.findViewById(R.id.across_date);
        this.I = (TextView) this.G.findViewById(R.id.change_date);
        this.J = this.G.findViewById(R.id.across_line);
        if (UIHelper.isNightTheme()) {
            this.J.setBackgroundColor(UIHelper.getColor(R.color.main_bg_night));
        } else {
            this.J.setBackgroundColor(UIHelper.getColor(R.color.main_bg));
        }
        if (TextUtils.isEmpty(this.R)) {
            this.G.setVisibility(8);
            return;
        }
        this.G.setVisibility(0);
        this.H.setText(this.R);
        this.I.setOnClickListener(new c(this));
    }

    public void onAttach(Activity activity) {
        if (activity instanceof ChangeDateListener) {
            this.T = (ChangeDateListener) activity;
        }
        super.onAttach(activity);
    }

    public void onDetach() {
        if (this.T != null) {
            this.T = null;
        }
        super.onDetach();
    }

    protected boolean x() {
        return false;
    }

    protected boolean o() {
        return false;
    }

    protected BaseImageAdapter b() {
        return super.b();
    }

    protected void d() {
        this.r = true;
        onRefresh();
    }

    protected boolean a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONArray jSONArray = jSONObject.getJSONArray("items");
            if (this.j.contains(this.K)) {
                this.j.clear();
            }
            if (jSONObject.optInt(NotificationCompat.CATEGORY_ERROR) == 0 || !isSelected()) {
                if (!TextUtils.isEmpty(this.R)) {
                    this.S = this.R;
                }
                this.R = jSONObject.optString(IndexEntry.COLUMN_NAME_DATE);
                if (!(TextUtils.isEmpty(this.R) || this.R.equals(this.S) || this.T == null)) {
                    this.T.changeDate(this.R);
                }
                int length = jSONArray.length();
                if (length == 0) {
                    this.q = true;
                    this.U = true;
                }
                if (this.q) {
                    this.footView.setVisibility(0);
                }
                if (this.o == 1 || this.e.equals("top_refresh")) {
                    this.j.clear();
                    this.footView.setVisibility(8);
                }
                if (length > 0 && this.r && isSelected() && this.e.equals("top_refresh")) {
                    ToastAndDialog.makePositiveToast(QsbkApp.mContext, String.format("成功穿越至%s", new Object[]{this.R}), Integer.valueOf(0)).show();
                }
                if (TextUtils.isEmpty(this.R)) {
                    this.G.setVisibility(8);
                } else {
                    this.G.setVisibility(0);
                    this.H = (TextView) this.G.findViewById(R.id.across_date);
                    this.I = (TextView) this.G.findViewById(R.id.change_date);
                    this.H.setText(this.R);
                    this.I.setOnClickListener(new d(this));
                }
                for (int i = 0; i < length; i++) {
                    try {
                        if (jSONArray.optJSONObject(i) != null) {
                            RssArticle rssArticle = new RssArticle(jSONArray.optJSONObject(i));
                            if (!this.j.contains(rssArticle)) {
                                this.j.add(rssArticle);
                            }
                        }
                    } catch (QiushibaikeException e) {
                    }
                }
                this.o++;
                return true;
            }
            Object optString = jSONObject.optString("err_msg");
            if (!TextUtils.isEmpty(optString)) {
                ToastAndDialog.makeNegativeToast(QsbkApp.mContext, optString, Integer.valueOf(0)).show();
            }
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public void onRefresh() {
        this.o = 1;
        this.u = Constants.URL_ACROSS;
        super.onRefresh();
    }

    public void onLoadMore() {
        if (this.o > 1 && !TextUtils.isEmpty(this.R)) {
            this.u = Constants.URL_ACROSS + "?date=" + this.R;
        }
        super.onLoadMore();
    }

    public void onOtherDay() {
        refresh();
    }

    public void onChangeDate() {
        if (this.l != null) {
            this.l.refresh();
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        if (!TextUtils.isEmpty(this.R)) {
            bundle.putString(IndexEntry.COLUMN_NAME_DATE, this.R);
        }
        super.onSaveInstanceState(bundle);
    }

    public void onResume() {
        super.onResume();
    }
}
