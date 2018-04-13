package cn.tatagou.sdk.fragment;

import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.tatagou.sdk.R;
import cn.tatagou.sdk.a.a;
import cn.tatagou.sdk.a.e;
import cn.tatagou.sdk.adapter.b;
import cn.tatagou.sdk.android.TtgConfig;
import cn.tatagou.sdk.android.TtgConfigKey;
import cn.tatagou.sdk.pojo.CommListPojo;
import cn.tatagou.sdk.pojo.CommPojo;
import cn.tatagou.sdk.pojo.Config;
import cn.tatagou.sdk.pojo.Feedback;
import cn.tatagou.sdk.pojo.FeedbackData;
import cn.tatagou.sdk.pojo.FeedbackType;
import cn.tatagou.sdk.pojo.MyMap;
import cn.tatagou.sdk.pojo.Page;
import cn.tatagou.sdk.pojo.SendFeedback;
import cn.tatagou.sdk.pojo.TitleBar;
import cn.tatagou.sdk.util.j;
import cn.tatagou.sdk.util.l;
import cn.tatagou.sdk.util.p;
import cn.tatagou.sdk.util.q;
import cn.tatagou.sdk.view.pullview.AutoPullAbleListView;
import cn.tatagou.sdk.view.pullview.AutoPullAbleListView$a;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import okhttp3.ab;

public class FeedbackFragment extends BaseFragment implements AutoPullAbleListView$a {
    private static final String a = FeedbackFragment.class.getSimpleName();
    private AutoPullAbleListView b;
    private TextView c;
    private EditText d;
    private EditText e;
    private TextView f;
    private b g;
    private List<FeedbackType> h = new ArrayList();
    private List<Feedback> i = new ArrayList();
    private FeedbackType j;
    private String k;
    private int l = 1;
    private String m;
    private LinkedHashMap<String, Object> n;
    private DialogFeedbackFragment o;
    private boolean p = false;
    private boolean q = false;
    private retrofit2.b<ab> r;
    private retrofit2.b<ab> s;
    private retrofit2.b<ab> t;
    private String u = "feedback";
    private String v;
    private a<CommListPojo<FeedbackType>> w = new FeedbackFragment$3(this);
    private a<CommPojo<SendFeedback>> x = new FeedbackFragment$5(this);
    private a<CommPojo<FeedbackData>> y = new FeedbackFragment$6(this);

    public static FeedbackFragment newInstance() {
        Bundle bundle = new Bundle();
        FeedbackFragment feedbackFragment = new FeedbackFragment();
        feedbackFragment.setArguments(bundle);
        return feedbackFragment;
    }

    public View newView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mView == null) {
            this.mView = layoutInflater.inflate(R.layout.ttg_feedback_fragment, viewGroup, false);
        }
        return this.mView;
    }

    public void initView(View view) {
        super.initView(view);
        a(view);
        this.b = (AutoPullAbleListView) view.findViewById(R.id.lv_feedback);
        this.c = (TextView) view.findViewById(R.id.tv_feedback_type);
        this.d = (EditText) view.findViewById(R.id.edit_feedback);
        this.e = (EditText) view.findViewById(R.id.edit_contact);
        this.f = (TextView) view.findViewById(R.id.tv_commit);
        q.onResetShapeThemeColor(this.f, 2, 0, TtgConfig.getInstance().getThemeColor());
        ((RelativeLayout) view.findViewById(R.id.rl_feedback_type)).setOnClickListener(this);
        this.b.setPullDownFlag(false);
        this.f.setOnClickListener(this);
    }

    private void a(View view) {
        TitleBar titleBar = new TitleBar();
        titleBar.setLeftIconShow(true);
        titleBar.setRightIconShow(false);
        titleBar.setTitle(getString(R.string.my_feedback));
        setBarTitle(view, titleBar);
    }

    private void f() {
        if (isAdded()) {
            MyMap myMap = (MyMap) getActivity().getIntent().getSerializableExtra("FeedbackParams");
            if (myMap != null) {
                String mapValues = myMap.getMapValues(TtgConfigKey.Feedback.KEY_FEEDBACKTYPE);
                CharSequence mapValues2 = myMap.getMapValues(TtgConfigKey.Feedback.KEY_FEEDBACKTITLE);
                this.v = myMap.getMapValues(TtgConfigKey.Feedback.KEY_EXTRAPARAMS);
                if (!(this.mTvTitle == null || p.isEmpty(mapValues2))) {
                    this.mTvTitle.setText(mapValues2);
                }
                if ("hostAppFb".equals(mapValues)) {
                    this.q = true;
                }
            }
        }
    }

    public void initData() {
        super.initData();
        f();
        this.m = "手机型号:" + Build.MODEL + ", OS:" + VERSION.RELEASE + ", TTG:" + "2.4.4.6" + ", APP:" + Config.getInstance().getAppVersion();
        this.k = p.phoneImei(getActivity());
        this.h = new ArrayList();
        this.j = new FeedbackType();
        g();
        d();
        this.b.setLoadDataFlag(true);
        this.b.setOnLoadListener(this);
    }

    private void g() {
        String a = a();
        if (!p.isEmpty(a)) {
            this.h = (List) JSON.parseObject(a, new FeedbackFragment$1(this), new Feature[0]);
        }
    }

    protected String a() {
        if (this.q) {
            this.r = ((cn.tatagou.sdk.a.a.a) e.getInstance().getService(cn.tatagou.sdk.a.a.a.class)).outFeedbackType();
            this.u = "outFeedback";
        } else {
            this.r = ((cn.tatagou.sdk.a.a.a) e.getInstance().getService(cn.tatagou.sdk.a.a.a.class)).feedbackType();
        }
        cn.tatagou.sdk.a.b.onCommRequestApi(this.w, this.r, new FeedbackFragment$2(this).getType());
        return cn.tatagou.sdk.b.a.getStr(this.u);
    }

    private void h() {
        if (this.n == null) {
            this.n = new LinkedHashMap();
        }
        this.n.put("content", this.d.getText().toString());
        this.n.put("pusher", this.k);
        this.n.put("type", this.j.getId());
        this.n.put("model", this.m);
        if (!p.isEmpty(this.d.getText().toString().replace(" ", "").trim())) {
            this.n.put("contact", this.e.getText().toString());
        }
        if (!TextUtils.isEmpty(this.v)) {
            this.n.put("extInfo", this.v);
        }
        this.f.setEnabled(false);
        b();
    }

    protected void b() {
        if (this.q) {
            this.t = ((cn.tatagou.sdk.a.a.a) e.getInstance().getService(cn.tatagou.sdk.a.a.a.class)).outSendFeedback(this.n);
        } else {
            this.t = ((cn.tatagou.sdk.a.a.a) e.getInstance().getService(cn.tatagou.sdk.a.a.a.class)).sendFeedback(this.n);
        }
        Type type = new FeedbackFragment$4(this).getType();
        if (this.t != null) {
            cn.tatagou.sdk.a.b.onCommRequestApi(this.x, this.t, type);
        }
    }

    protected void a(List<FeedbackType> list) {
        cn.tatagou.sdk.b.a.saveStr(this.u, JSON.toJSONString(list));
    }

    private void a(FeedbackData feedbackData) {
        boolean z = false;
        if (feedbackData != null) {
            Page feedback = feedbackData.getFeedback();
            int nextPage = j.nextPage(feedback.getCurrent_page(), feedback.getLast_page());
            if (nextPage > 0) {
                this.l = nextPage;
                this.b.finishLoading(0);
            } else {
                this.b.setFinishText(getString(R.string.ttg_icon_pull_data));
                this.b.finishLoading(2);
            }
            Collection data = feedback.getData();
            if (data == null || data.size() <= 0) {
                a(getString(R.string.ttg_icon_pull_data), false);
            } else {
                this.i.removeAll(data);
                this.i.addAll(data);
                i();
            }
            if (!p.isEmpty(feedbackData.getUnRead())) {
                z = Integer.parseInt(feedbackData.getUnRead());
            }
            if (z <= false) {
                j();
                return;
            }
            return;
        }
        a(getString(R.string.ttg_icon_pull_data), false);
    }

    private void i() {
        if (this.g == null) {
            this.g = new b(getActivity(), this.i);
            this.b.setAdapter(this.g);
            return;
        }
        this.g.setItems(this.i);
    }

    private void a(String str, boolean z) {
        this.b.setFinishText(str);
        this.b.finishLoading(2);
        this.b.setLoadDataFlag(z);
    }

    protected retrofit2.b<ab> c() {
        if (this.q) {
            return ((cn.tatagou.sdk.a.a.a) e.getInstance().getService(cn.tatagou.sdk.a.a.a.class)).outReadAll(this.k);
        }
        return ((cn.tatagou.sdk.a.a.a) e.getInstance().getService(cn.tatagou.sdk.a.a.a.class)).readAll(this.k);
    }

    private void j() {
        retrofit2.b c = c();
        if (c != null) {
            c.a(new FeedbackFragment$7(this));
        }
    }

    protected void d() {
        if (this.q) {
            this.s = ((cn.tatagou.sdk.a.a.a) e.getInstance().getService(cn.tatagou.sdk.a.a.a.class)).outGetFeedback(this.l, this.k);
        } else {
            this.s = ((cn.tatagou.sdk.a.a.a) e.getInstance().getService(cn.tatagou.sdk.a.a.a.class)).getFeedback(this.l, this.k);
        }
        cn.tatagou.sdk.a.b.onCommRequestApi(this.y, this.s, new FeedbackFragment$8(this).getType());
    }

    public void onClick(View view) {
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.rl_feedback_type) {
            if (this.h == null || this.h.size() < 1) {
                this.p = true;
                g();
                return;
            }
            k();
        } else if (id != R.id.tv_commit) {
        } else {
            if (p.isNetworkOpen(getActivity())) {
                l();
            } else {
                l.showToastCenter(getActivity(), getResources().getString(R.string.set_net_prompt));
            }
        }
    }

    private void k() {
        if (isAdded()) {
            if (this.o == null) {
                this.o = new DialogFeedbackFragment(this.h);
                this.o.setCancelable(true);
            }
            if (!(this.o.isAdded() || this.o.isVisible() || this.o.isRemoving())) {
                this.o.show(getActivity().getFragmentManager(), "feedbackFragment");
            }
            this.o.setCallBackListener(new FeedbackFragment$9(this));
        }
    }

    private void l() {
        if (TextUtils.isEmpty(this.c.getText())) {
            l.showToastCenter(getActivity(), getString(R.string.sel_feedback_type));
            if (!p.isEmpty(this.d.getText().toString().replace(" ", "").trim())) {
                this.d.setText(this.d.getText().toString().trim());
            }
        } else if (TextUtils.isEmpty(this.d.getText()) || p.isEmpty(this.d.getText().toString().replace(" ", "").trim())) {
            l.showToastCenter(getActivity(), getString(R.string.input_feedback_content));
        } else if ((p.isEmpty(this.k) || (!p.isEmpty(this.k) && this.k.equals("0"))) && TextUtils.isEmpty(this.e.getText())) {
            l.showToastCenter(getActivity(), getString(R.string.input_feedback_contact));
            this.e.requestFocus();
            this.d.setText(this.d.getText().toString().trim());
        } else if (TextUtils.isEmpty(this.e.getText()) || this.e.getText().toString().length() <= 64) {
            h();
        } else {
            l.showToastCenter(getActivity(), getString(R.string.input_content_length));
            this.d.setText(this.d.getText().toString().trim());
        }
    }

    public void onLoad(AutoPullAbleListView autoPullAbleListView) {
        d();
    }

    public void onDestroy() {
        if (this.r != null) {
            this.r.b();
            this.r = null;
        }
        if (this.s != null) {
            this.s.b();
            this.s = null;
        }
        if (this.t != null) {
            this.t.b();
            this.t = null;
        }
        super.onDestroy();
    }
}
