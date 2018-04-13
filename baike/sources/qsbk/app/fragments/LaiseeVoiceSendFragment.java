package qsbk.app.fragments;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.LaiseeSendActivity;
import qsbk.app.activity.PayPasswordSetActivity;
import qsbk.app.activity.pay.PayPWDUniversalActivity;
import qsbk.app.cache.FileCache;
import qsbk.app.core.AsyncTask;
import qsbk.app.http.EncryptHttpTask;
import qsbk.app.http.HttpTask;
import qsbk.app.model.Laisee;
import qsbk.app.model.LaiseeVoice;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.ToastUtil;
import qsbk.app.utils.Util;
import qsbk.app.widget.BlackProgressDialog;
import qsbk.app.widget.ExpandableLayout;
import qsbk.app.widget.LoadingLayout;

public class LaiseeVoiceSendFragment extends BaseFragment implements OnClickListener {
    public static final String VOICE_WORDS = "voice_words";
    private int A;
    private double B;
    private TextView C;
    private double D;
    private String E;
    private int F;
    private View G;
    private TextView H;
    private int I;
    private String J;
    private double K = 0.0d;
    private BigDecimal L;
    private double M;
    private Laisee N;
    private double O;
    private String P;
    private b Q;
    private String R;
    private double S;
    private boolean T;
    NumberFormat a = new DecimalFormat("#0.00");
    TreeMap<String, List<b>> b = new TreeMap(new c(this));
    List<String> c = new ArrayList();
    TextView d;
    Button e;
    Button f;
    View g;
    View h;
    EditText i;
    TextView j;
    ExpandableLayout k;
    EditText l;
    EditText m;
    TextView n;
    Button o;
    TextView p;
    TextView q;
    TextView r;
    TextView s;
    TextView t;
    TabLayout u;
    LoadingLayout v;
    BlackProgressDialog w;
    TextWatcher x = new ef(this);
    TextWatcher y = new eh(this);
    TextWatcher z = new ei(this);

    class a implements InputFilter {
        final /* synthetic */ LaiseeVoiceSendFragment a;

        a(LaiseeVoiceSendFragment laiseeVoiceSendFragment) {
            this.a = laiseeVoiceSendFragment;
        }

        public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
            CharSequence stringBuilder = new StringBuilder();
            while (i < i2) {
                char charAt = charSequence.charAt(i);
                if (LaiseeVoiceSendFragment.b(charAt)) {
                    stringBuilder.append(charAt);
                }
                i++;
            }
            return stringBuilder;
        }
    }

    class b {
        String a;
        String b;
        final /* synthetic */ LaiseeVoiceSendFragment c;

        public b(LaiseeVoiceSendFragment laiseeVoiceSendFragment, String str, String str2) {
            this.c = laiseeVoiceSendFragment;
            this.a = str;
            this.b = str2;
        }
    }

    class c implements Comparator<String> {
        final /* synthetic */ LaiseeVoiceSendFragment a;

        c(LaiseeVoiceSendFragment laiseeVoiceSendFragment) {
            this.a = laiseeVoiceSendFragment;
        }

        public int compare(String str, String str2) {
            return str.toLowerCase().compareTo(str2.toLowerCase());
        }
    }

    public static LaiseeVoiceSendFragment newInstance(String str) {
        LaiseeVoiceSendFragment laiseeVoiceSendFragment = new LaiseeVoiceSendFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("KEY_TYPE", 0);
        bundle.putString("kEY_ID", str);
        laiseeVoiceSendFragment.setArguments(bundle);
        return laiseeVoiceSendFragment;
    }

    public static LaiseeVoiceSendFragment newInstance(String str, int i) {
        LaiseeVoiceSendFragment laiseeVoiceSendFragment = new LaiseeVoiceSendFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("KEY_TYPE", 2);
        bundle.putString("kEY_ID", str);
        bundle.putInt("member_num", i);
        laiseeVoiceSendFragment.setArguments(bundle);
        return laiseeVoiceSendFragment;
    }

    private static boolean b(char c) {
        return c >= '一' && c <= '龻';
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        return layoutInflater.inflate(R.layout.fragment_laisee_send_voice, null);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        a(view);
        e();
    }

    private void e() {
        Bundle arguments = getArguments();
        this.I = arguments.getInt("member_num", 0);
        this.J = arguments.getString("kEY_ID");
        this.A = arguments.getInt("KEY_TYPE");
        g();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        h();
        f();
    }

    public void onStart() {
        super.onStart();
        i();
    }

    private void a(View view) {
        this.u = (TabLayout) view.findViewById(R.id.words_tabs);
        this.u.addOnTabSelectedListener(new ej(this));
        this.e = (Button) view.findViewById(R.id.word_next);
        this.f = (Button) view.findViewById(R.id.word_done);
        this.d = (TextView) view.findViewById(R.id.word_content);
        this.e.setOnClickListener(this);
        this.f.setOnClickListener(this);
        this.k = (ExpandableLayout) view.findViewById(R.id.expandable_layout);
        this.h = view.findViewById(R.id.voice_before_hand_arrow);
        this.g = view.findViewById(R.id.before_hand_container);
        this.g.setOnClickListener(new ek(this));
        this.q = (TextView) view.findViewById(R.id.input_tips);
        this.i = (EditText) view.findViewById(R.id.watchword);
        this.i.setFilters(new InputFilter[]{new a(this), new LengthFilter(18)});
        this.l = (EditText) view.findViewById(R.id.count);
        this.j = (TextView) view.findViewById(R.id.info);
        this.n = (TextView) view.findViewById(R.id.amount_desc);
        this.m = (EditText) view.findViewById(R.id.amount);
        this.C = (TextView) view.findViewById(R.id.amount_copy);
        this.p = (TextView) view.findViewById(R.id.yuan);
        this.G = view.findViewById(R.id.count_container);
        this.r = (TextView) view.findViewById(R.id.count_desc);
        this.s = (TextView) view.findViewById(R.id.ge);
        this.H = (TextView) view.findViewById(R.id.group_members_count);
        this.o = (Button) view.findViewById(R.id.submit);
        this.t = (TextView) view.findViewById(R.id.fee_desc);
        this.v = (LoadingLayout) view.findViewById(R.id.voice_loading);
        this.o.setOnClickListener(new el(this));
        this.m.addTextChangedListener(this.z);
        this.m.addTextChangedListener(this.y);
        this.l.addTextChangedListener(this.x);
        this.l.addTextChangedListener(this.y);
        this.i.addTextChangedListener(this.y);
        this.w = new BlackProgressDialog(getActivity());
        this.v.setOnLoadingClickListener(new em(this));
    }

    public void onDestroyView() {
        super.onDestroyView();
        if (this.m != null) {
            this.m.removeTextChangedListener(this.y);
            this.m.removeTextChangedListener(this.z);
        }
        if (this.l != null) {
            this.l.removeTextChangedListener(this.y);
            this.l.removeTextChangedListener(this.x);
        }
        if (this.i != null) {
            this.i.removeTextChangedListener(this.y);
        }
    }

    private void f() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.b.keySet());
        this.u.removeAllTabs();
        if (arrayList.size() > 0) {
            this.v.hide();
        } else {
            this.v.show((CharSequence) "加载失败，请点击重试", true);
        }
        for (int i = 0; i < arrayList.size(); i++) {
            this.u.addTab(this.u.newTab().setCustomView(R.layout.layout_laisee_tab_item).setText((CharSequence) arrayList.get(i)));
        }
    }

    private void g() {
        this.G.setVisibility(0);
        this.H.setVisibility(0);
        this.H.setText(String.format("本群%d人", new Object[]{Integer.valueOf(this.I)}));
        this.n.setText("赏金");
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        switch (this.A) {
            case 0:
                this.G.setVisibility(8);
                this.H.setVisibility(8);
                return;
            default:
                return;
        }
    }

    private void h() {
        if (this.b.size() == 0) {
            this.v.onLoading();
        }
        try {
            a(new JSONObject(FileCache.getContentFromDisk(QsbkApp.getInstance().getApplicationContext(), VOICE_WORDS)));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        HttpTask encryptHttpTask = new EncryptHttpTask(Constants.LAISEE_VOICE_WORDS, new en(this));
        Map hashMap = new HashMap();
        hashMap.put("version", Integer.valueOf(0));
        encryptHttpTask.setMapParams(hashMap);
        encryptHttpTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(org.json.JSONObject r13) {
        /*
        r12 = this;
        r1 = 0;
        r0 = "version";
        r13.optInt(r0);
        r0 = "words";
        r6 = r13.optJSONArray(r0);
        r0 = r6.length();
        if (r0 <= 0) goto L_0x001c;
    L_0x0012:
        r0 = r12.c;
        r0.clear();
        r0 = r12.b;
        r0.clear();
    L_0x001c:
        r0 = r1;
    L_0x001d:
        r2 = r6.length();
        if (r0 >= r2) goto L_0x008b;
    L_0x0023:
        r2 = r6.getJSONObject(r0);	 Catch:{ JSONException -> 0x0075 }
        r3 = "desc";
        r7 = r2.getString(r3);	 Catch:{ JSONException -> 0x0075 }
        r3 = "items";
        r8 = r2.getJSONArray(r3);	 Catch:{ JSONException -> 0x0075 }
        r9 = new java.util.ArrayList;	 Catch:{ JSONException -> 0x0075 }
        r9.<init>();	 Catch:{ JSONException -> 0x0075 }
        r5 = r1;
    L_0x0039:
        r2 = r8.length();	 Catch:{ JSONException -> 0x0075 }
        if (r5 >= r2) goto L_0x007c;
    L_0x003f:
        r2 = r8.optJSONObject(r5);	 Catch:{ JSONException -> 0x0075 }
        if (r2 == 0) goto L_0x006b;
    L_0x0045:
        r4 = 0;
        r3 = new qsbk.app.fragments.LaiseeVoiceSendFragment$b;	 Catch:{ JSONException -> 0x006f }
        r10 = "id";
        r10 = r2.optString(r10);	 Catch:{ JSONException -> 0x006f }
        r11 = "content";
        r2 = r2.getString(r11);	 Catch:{ JSONException -> 0x006f }
        r3.<init>(r12, r10, r2);	 Catch:{ JSONException -> 0x006f }
        if (r0 != 0) goto L_0x0066;
    L_0x0059:
        if (r5 != 0) goto L_0x0066;
    L_0x005b:
        r12.Q = r3;	 Catch:{ JSONException -> 0x00a6 }
        r2 = r12.d;	 Catch:{ JSONException -> 0x00a6 }
        r4 = r12.Q;	 Catch:{ JSONException -> 0x00a6 }
        r4 = r4.b;	 Catch:{ JSONException -> 0x00a6 }
        r2.setText(r4);	 Catch:{ JSONException -> 0x00a6 }
    L_0x0066:
        if (r3 == 0) goto L_0x006b;
    L_0x0068:
        r9.add(r3);	 Catch:{ JSONException -> 0x0075 }
    L_0x006b:
        r2 = r5 + 1;
        r5 = r2;
        goto L_0x0039;
    L_0x006f:
        r2 = move-exception;
        r3 = r4;
    L_0x0071:
        r2.printStackTrace();	 Catch:{ JSONException -> 0x0075 }
        goto L_0x0066;
    L_0x0075:
        r2 = move-exception;
        r2.printStackTrace();
    L_0x0079:
        r0 = r0 + 1;
        goto L_0x001d;
    L_0x007c:
        if (r0 != 0) goto L_0x0080;
    L_0x007e:
        r12.P = r7;	 Catch:{ JSONException -> 0x0075 }
    L_0x0080:
        r2 = r12.c;	 Catch:{ JSONException -> 0x0075 }
        r2.add(r7);	 Catch:{ JSONException -> 0x0075 }
        r2 = r12.b;	 Catch:{ JSONException -> 0x0075 }
        r2.put(r7, r9);	 Catch:{ JSONException -> 0x0075 }
        goto L_0x0079;
    L_0x008b:
        r0 = r12.c;
        r1 = new qsbk.app.fragments.LaiseeVoiceSendFragment$c;
        r1.<init>(r12);
        java.util.Collections.sort(r0, r1);
        r0 = r12.Q;
        if (r0 == 0) goto L_0x00a2;
    L_0x0099:
        r0 = r12.d;
        r1 = r12.Q;
        r1 = r1.b;
        r0.setText(r1);
    L_0x00a2:
        r12.f();
        return;
    L_0x00a6:
        r2 = move-exception;
        goto L_0x0071;
        */
        throw new UnsupportedOperationException("Method not decompiled: qsbk.app.fragments.LaiseeVoiceSendFragment.a(org.json.JSONObject):void");
    }

    private void i() {
        new EncryptHttpTask(Constants.LAISEE_VOICE_FEE, new eo(this)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.word_next:
                if (!TextUtils.isEmpty(this.P)) {
                    List list = (List) this.b.get(this.P);
                    if (list != null && list.size() > 0) {
                        int indexOf = list.indexOf(this.Q) + 1;
                        if (indexOf >= list.size()) {
                            indexOf = 0;
                        }
                        this.Q = (b) list.get(indexOf);
                    }
                }
                this.d.setText(this.Q.b);
                return;
            case R.id.word_done:
                this.i.setText(this.Q.b);
                this.g.performClick();
                return;
            default:
                return;
        }
    }

    private void j() {
        if (this.K == 0.0d) {
            ToastAndDialog.makeText(getActivity(), "手续费费率获取中...请稍后再试").show();
            i();
        } else if (!QsbkApp.currentUser.hasPhone()) {
            AlertDialog create = new Builder(getActivity()).setTitle("账号安全系数低，请先绑定手机，并设置支付密码").setPositiveButton("绑定手机", new eg(this)).create();
            create.setCanceledOnTouchOutside(true);
            create.show();
        } else if (QsbkApp.currentUser.hasPaypass()) {
            this.N = new LaiseeVoice();
            this.N.totalMoney = this.B;
            this.N.content = this.R;
            LaiseeSendActivity laiseeSendActivity = (LaiseeSendActivity) getActivity();
            if (laiseeSendActivity != null) {
                laiseeSendActivity.laiseePay(this.N, this.L, l(), this.A);
            }
        } else {
            ToastUtil.Short("为了您的资金安全，请先设置支付密码");
            PayPasswordSetActivity.launch(getActivity());
        }
    }

    boolean a() {
        this.R = this.i.getText().toString();
        this.R = this.R.trim();
        if (!TextUtils.isEmpty(this.R)) {
            return true;
        }
        this.E = "口令不能为空";
        return false;
    }

    boolean b() {
        try {
            this.S = Double.parseDouble(this.m.getText().toString());
        } catch (Exception e) {
            this.S = 0.0d;
        }
        this.M = this.S * this.K;
        if (this.M < this.O) {
            this.M = this.O;
        }
        this.B = this.S + this.M;
        this.t.setVisibility(this.M > 0.0d ? 0 : 8);
        this.t.setText(k());
        switch (this.A) {
            case 0:
                this.D = this.S;
                break;
        }
        this.C.setText(this.S > 0.0d ? "￥" + Util.formatMoney(this.B) : "￥0.00");
        if (this.D > 200.0d && this.A != 2) {
            this.E = String.format("单个红包金额不超过%s元", new Object[]{"200.0"});
            return false;
        } else if (this.B > 20000.0d) {
            this.E = String.format("单次支付总额不超过%s元", new Object[]{"20000.0"});
            return false;
        } else if (this.S >= 0.1d) {
            return true;
        } else {
            this.E = String.format("语音红包金额不能低于%s元", new Object[]{"0.1"});
            return false;
        }
    }

    boolean c() {
        try {
            this.F = Integer.parseInt(this.l.getText().toString());
        } catch (Exception e) {
            this.F = 0;
        }
        if (this.A == 0 && this.F == 0) {
            this.F = 1;
        }
        if (this.F > 100) {
            this.E = String.format("一次最多可发%d个红包", new Object[]{Integer.valueOf(100)});
            return false;
        } else if (this.F >= 1) {
            return true;
        } else {
            this.E = "至少需要设置1个红包";
            return false;
        }
    }

    boolean d() {
        if (!c() || !b() || !a()) {
            return false;
        }
        boolean z;
        int i = this.A;
        switch (this.A) {
            case 2:
                double doubleValue = new BigDecimal(this.S * 100.0d).divide(new BigDecimal(this.F), 2, 4).doubleValue();
                this.D = doubleValue >= 1.0d ? doubleValue / 100.0d : 0.0d;
                break;
        }
        if (this.D > 200.0d) {
            this.E = "单个红包金额不超过200元";
            z = false;
        } else if (this.B > 20000.0d) {
            this.E = "单次支付总额不超过20000元";
            z = false;
        } else if (this.D < 0.01d) {
            this.E = "单个红包金额不能低于0.01元";
            z = false;
        } else {
            z = true;
        }
        return z;
    }

    private String k() {
        return String.format("语音鉴别服务加收服务费¥%1$s", new Object[]{Util.formatMoney(this.M)});
    }

    private HashMap<String, Object> l() {
        HashMap<String, Object> hashMap = new HashMap();
        hashMap.put("sub_type", Laisee.SUB_TYPE_VOICE);
        hashMap.put(PayPWDUniversalActivity.MONEY, Util.formatMoney(this.S));
        hashMap.put("extra_fee", Double.valueOf(this.M));
        if (this.A == 0) {
            hashMap.put("toid", this.J);
            hashMap.put("content", this.R);
        } else {
            hashMap.put("tribe_id", this.J);
            hashMap.put("count", Integer.valueOf(this.F));
            hashMap.put("distribute", this.A == 2 ? "random" : "average");
            hashMap.put("content", this.R);
        }
        return hashMap;
    }
}
