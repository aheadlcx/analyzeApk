package cn.v6.sixrooms.widgets.phone;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.GuardPropBean;
import cn.v6.sixrooms.bean.GuardPropDetailBean;
import cn.v6.sixrooms.bean.RoominfoBean;
import cn.v6.sixrooms.engine.CommodityInfoEngine;
import cn.v6.sixrooms.engine.PurchaseGuardEngine;
import cn.v6.sixrooms.utils.DialogUtils;
import cn.v6.sixrooms.utils.GlobleValue;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;
import java.util.ArrayList;
import java.util.List;

public class OpenGuardPage extends RelativeLayout implements OnClickListener {
    private Context a;
    private ImageView b;
    private TextView c;
    private GiftGridView d;
    private TextView e;
    private TextView f;
    private TextView g;
    public RelativeLayout guardPage;
    private TextView h;
    private RelativeLayout i;
    private List<GuardPropBean> j;
    private OpenGuardAdapter k;
    private List<GuardPropDetailBean> l;
    private List<GuardPropDetailBean> m;
    private CommodityInfoEngine n;
    private PurchaseGuardEngine o;
    private int p = 0;
    private OnItemClickListener q;
    private DialogUtils r;
    private Dialog s;
    private RoominfoBean t;
    private OnPurchaseGuardListener u;
    private int[] v;

    public interface OnPurchaseGuardListener {
        void onHandleErrorInfo(String str, String str2);

        void onPurchaseSuccess();

        void onShowErrorCode(int i);
    }

    public class OpenGuardAdapter extends BaseAdapter {
        final /* synthetic */ OpenGuardPage a;
        private List<GuardPropDetailBean> b;
        private int c = 0;

        public OpenGuardAdapter(OpenGuardPage openGuardPage, List<GuardPropDetailBean> list) {
            this.a = openGuardPage;
            this.b = list;
        }

        public void changeData(List<GuardPropDetailBean> list, int i) {
            this.b = list;
            this.c = i;
            notifyDataSetChanged();
        }

        public int getCount() {
            return this.b.size();
        }

        public Object getItem(int i) {
            return this.b.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            a aVar;
            if (view == null) {
                view = View.inflate(this.a.a, R.layout.phone_room_open_guard_page_item, null);
                aVar = new a(this.a);
                aVar.b = (TextView) view.findViewById(R.id.tv_price);
                aVar.c = (TextView) view.findViewById(R.id.tv_days);
                view.setTag(aVar);
            } else {
                aVar = (a) view.getTag();
            }
            if (this.c == 0) {
                view.setBackgroundResource(R.drawable.rooms_third_room_guard_gold_item_bg);
                aVar.b.setTextColor(this.a.getResources().getColor(R.color.guard_gold_title_color));
                aVar.c.setTextColor(this.a.getResources().getColor(R.color.guard_gold_title_color));
            } else {
                view.setBackgroundResource(R.drawable.rooms_third_room_guard_silver_item_bg);
                aVar.b.setTextColor(this.a.getResources().getColor(R.color.guard_silver_title_color));
                aVar.c.setTextColor(this.a.getResources().getColor(R.color.guard_silver_title_color));
            }
            aVar.b.setText(((GuardPropDetailBean) this.b.get(i)).getPrice() + "六币");
            aVar.c.setText(((GuardPropDetailBean) this.b.get(i)).getDays() + "天");
            return view;
        }
    }

    class a {
        final /* synthetic */ OpenGuardPage a;
        private TextView b;
        private TextView c;

        a(OpenGuardPage openGuardPage) {
            this.a = openGuardPage;
        }
    }

    public OpenGuardPage(Context context, RoominfoBean roominfoBean, int[] iArr, OnPurchaseGuardListener onPurchaseGuardListener) {
        super(context);
        this.a = context;
        this.t = roominfoBean;
        this.v = iArr;
        this.u = onPurchaseGuardListener;
        this.guardPage = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.phone_room_open_guard_page, this, true);
        a();
        this.b = (ImageView) findViewById(R.id.iv_title_close);
        this.c = (TextView) findViewById(R.id.tv_guard_body_title);
        this.d = (GiftGridView) findViewById(R.id.gv_guard_content);
        this.e = (TextView) findViewById(R.id.tv_guard_desc_title);
        this.f = (TextView) findViewById(R.id.tv_guard_desc);
        this.g = (TextView) findViewById(R.id.tv_gold_guard);
        this.h = (TextView) findViewById(R.id.tv_silver_guard);
        this.g.setEnabled(false);
        this.h.setEnabled(true);
        this.d.setAdapter(this.k);
        this.b.setOnClickListener(this);
        this.g.setOnClickListener(this);
        this.h.setOnClickListener(this);
        this.q = new ac(this);
        this.d.setOnItemClickListener(null);
    }

    public void setGuardPermisssion(int[] iArr) {
        this.v = iArr;
    }

    public void setmRoomInfoBean(RoominfoBean roominfoBean) {
        this.t = roominfoBean;
    }

    public OnPurchaseGuardListener getPurchaseGuardListener() {
        return this.u;
    }

    public void setPurchaseGuardListener(OnPurchaseGuardListener onPurchaseGuardListener) {
        this.u = onPurchaseGuardListener;
    }

    private void a() {
        this.i = (RelativeLayout) findViewById(R.id.pb_loading);
        this.j = new ArrayList();
        this.l = new ArrayList();
        this.m = new ArrayList();
        for (int i = 0; i < 4; i++) {
            GuardPropDetailBean guardPropDetailBean = new GuardPropDetailBean("99", "----", 0);
            this.l.add(guardPropDetailBean);
            this.m.add(guardPropDetailBean);
        }
        this.k = new OpenGuardAdapter(this, this.l);
        this.i.setVisibility(0);
        this.r = new DialogUtils(this.a);
        this.n = new CommodityInfoEngine(new z(this));
        this.n.getProps(SaveUserInfoUtils.getEncpass(this.a), GlobleValue.getUserBean().getId(), GlobleValue.getUserBean().getRid());
        this.o = new PurchaseGuardEngine(new aa(this));
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_title_close) {
            this.guardPage.setVisibility(8);
        } else if (id == R.id.tv_gold_guard) {
            b();
        } else if (id == R.id.tv_silver_guard) {
            this.g.setEnabled(true);
            this.g.setTextColor(this.a.getResources().getColor(R.color.room_bottombar_textcolor_unchecked));
            this.h.setEnabled(false);
            this.h.setTextColor(this.a.getResources().getColor(R.color.room_bottombar_textcolor_checked));
            this.c.setText(this.a.getString(R.string.open_guard_silver));
            this.c.setTextColor(this.a.getResources().getColor(R.color.guard_silver_title_color));
            this.e.setText(this.a.getString(R.string.guard_silver_privilege));
            this.e.setTextColor(this.a.getResources().getColor(R.color.guard_silver_title_color));
            this.c.setBackgroundResource(R.drawable.rooms_third_room_guard_sivler_title);
            this.k.changeData(this.m, 1);
            this.p = 1;
            if (this.j.size() != 0) {
                this.f.setText(((GuardPropBean) this.j.get(1)).getDesc().replaceAll("\n", "\n\n"));
            }
        }
    }

    private void b() {
        this.g.setEnabled(false);
        this.g.setTextColor(this.a.getResources().getColor(R.color.room_bottombar_textcolor_checked));
        this.h.setEnabled(true);
        this.h.setTextColor(this.a.getResources().getColor(R.color.room_bottombar_textcolor_unchecked));
        this.c.setText(this.a.getString(R.string.open_guard_gold));
        this.c.setTextColor(this.a.getResources().getColor(R.color.guard_gold_title_color));
        this.e.setText(this.a.getString(R.string.guard_gold_privilege));
        this.e.setTextColor(this.a.getResources().getColor(R.color.guard_gold_title_color));
        this.c.setBackgroundResource(R.drawable.rooms_third_room_guard_gold_title);
        this.k.changeData(this.l, 0);
        this.p = 0;
        if (this.j.size() != 0) {
            this.f.setText(((GuardPropBean) this.j.get(0)).getDesc().replaceAll("\n", "\n\n"));
        }
    }

    public void setOpenGuardPageVisible(int i) {
        this.guardPage.setVisibility(i);
    }

    public void getGuardInfo() {
        this.i.setVisibility(0);
        this.n.getProps(SaveUserInfoUtils.getEncpass(this.a), GlobleValue.getUserBean().getId(), GlobleValue.getUserBean().getRid());
    }

    public void dismissGuardpageDialog() {
        if (this.s != null) {
            this.s.dismiss();
        }
    }

    static /* synthetic */ void a(OpenGuardPage openGuardPage, String str) {
        if (openGuardPage.s != null) {
            openGuardPage.s.dismiss();
            openGuardPage.s = null;
        }
        openGuardPage.s = openGuardPage.r.createConfirmDialog(0, openGuardPage.a.getString(R.string.tip_show_tip_title), str, openGuardPage.a.getString(R.string.cancel), openGuardPage.a.getString(R.string.guard_charge_now), new ab(openGuardPage));
        openGuardPage.s.show();
    }

    static /* synthetic */ void a(OpenGuardPage openGuardPage, GuardPropDetailBean guardPropDetailBean) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("守护对象： ").append(openGuardPage.t.getAlias()).append("(" + openGuardPage.t.getRid() + ")").append("\n");
        stringBuilder.append("守护类型： ").append(openGuardPage.p == 0 ? "黄金守护" : "白银守护").append("\n");
        stringBuilder.append("购买天数： ").append(guardPropDetailBean.getDays() + "天\n");
        stringBuilder.append("价格： ").append(guardPropDetailBean.getPrice() + "六币");
        if (openGuardPage.s != null) {
            openGuardPage.s.dismiss();
            openGuardPage.s = null;
        }
        openGuardPage.s = openGuardPage.r.createConfirmDialog(0, "购买守护确认", stringBuilder.toString(), openGuardPage.a.getString(R.string.cancel), openGuardPage.a.getString(R.string.confirm), new ae(openGuardPage, guardPropDetailBean));
        openGuardPage.s.show();
    }
}
