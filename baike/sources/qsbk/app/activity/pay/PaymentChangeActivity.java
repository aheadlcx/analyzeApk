package qsbk.app.activity.pay;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.math.BigDecimal;
import java.util.ArrayList;
import qsbk.app.R;
import qsbk.app.adapter.BaseImageAdapter;
import qsbk.app.core.ui.base.BaseActivity;
import qsbk.app.model.Payment;

public class PaymentChangeActivity extends BaseActivity {
    ArrayList<Payment> a = new ArrayList();
    ListView b;
    ImageView c;
    BaseAdapter d;
    private BigDecimal e;
    private boolean f;
    public int type;

    class a extends BaseImageAdapter {
        final /* synthetic */ PaymentChangeActivity a;

        public a(PaymentChangeActivity paymentChangeActivity, ArrayList<Payment> arrayList, Activity activity) {
            this.a = paymentChangeActivity;
            super(arrayList, activity);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            b bVar;
            if (view == null) {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_payment, null);
                b bVar2 = new b(this.a, view);
                view.setTag(bVar2);
                bVar = bVar2;
            } else {
                bVar = (b) view.getTag();
            }
            Payment payment = (Payment) this.a.a.get(i);
            if (payment != null) {
                if (payment.mId == 1) {
                    bVar.b.setImageResource(payment.mPaymentIcon);
                    if (this.a.f) {
                        bVar.c.setText(payment.mPaymentDesc + "(¥" + this.a.e.setScale(2, 3).toString() + "元)");
                        bVar.c.setTextColor(this.k.getResources().getColor(R.color.gray_laisee));
                        bVar.a.setOnClickListener(new bm(this, payment));
                    } else {
                        bVar.c.setText(payment.mPaymentDesc + "余额不足，剩余(¥" + this.a.e.setScale(2, 3).toString() + "元)");
                        bVar.c.setTextColor(this.k.getResources().getColor(R.color.light_gray_laisee));
                        bVar.a.setOnClickListener(null);
                    }
                } else {
                    bVar.b.setImageResource(payment.mPaymentIcon);
                    bVar.c.setText(payment.mPaymentDesc);
                    bVar.c.setTextColor(this.k.getResources().getColor(R.color.gray_laisee));
                    bVar.a.setOnClickListener(new bn(this, payment));
                }
            }
            return view;
        }
    }

    class b {
        View a;
        ImageView b;
        TextView c;
        final /* synthetic */ PaymentChangeActivity d;

        public b(PaymentChangeActivity paymentChangeActivity, View view) {
            this.d = paymentChangeActivity;
            this.a = view;
            this.b = (ImageView) view.findViewById(R.id.icon);
            this.c = (TextView) view.findViewById(R.id.desc);
        }
    }

    public static void launch(Activity activity, BigDecimal bigDecimal, boolean z, int i, int i2) {
        Intent intent = new Intent(activity, PaymentChangeActivity.class);
        intent.putExtra("balance", bigDecimal);
        intent.putExtra("is_balacne_enough", z);
        intent.putExtra("type", i2);
        activity.startActivityForResult(intent, i);
    }

    protected int getLayoutId() {
        return R.layout.activity_laisee_change_payment;
    }

    protected void initView() {
        this.type = getIntent().getIntExtra("type", 0);
        if (this.type < 0) {
            finish();
            return;
        }
        this.b = (ListView) findViewById(R.id.list);
        this.c = (ImageView) findViewById(R.id.close);
        this.c.setOnClickListener(new bl(this));
    }

    public void setResult(Payment payment) {
        Intent intent = new Intent();
        intent.putExtra("payment", payment);
        setResult(-1, intent);
        finish();
    }

    protected void initData() {
        Intent intent = getIntent();
        this.e = (BigDecimal) intent.getSerializableExtra("balance");
        this.f = intent.getBooleanExtra("is_balacne_enough", true);
        if (this.type == 0) {
            this.a.add(Payment.PAY_WALLET);
        }
        this.a.add(Payment.PAY_ALIPAY);
        this.a.add(Payment.PAY_WEXIN);
        this.d = new a(this, this.a, this);
        this.b.setAdapter(this.d);
    }

    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }
}
