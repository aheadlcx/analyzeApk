package cn.v6.sixrooms.room.sofa;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.SofaBean;
import cn.v6.sixrooms.presenter.runnable.SofaControlable;
import com.facebook.common.util.UriUtil;
import com.facebook.drawee.view.SimpleDraweeView;

public class SofaDialog extends Dialog implements TextWatcher, OnClickListener, Sofaable {
    private EditText a;
    private TextView b;
    private LinearLayout c;
    private LinearLayout d;
    private LinearLayout e;
    private LinearLayout f;
    private int g = -1;
    private SofaControlable h;
    private SimpleDraweeView i;
    private SimpleDraweeView j;
    private SimpleDraweeView k;
    private SimpleDraweeView l;
    private TextView m;
    private TextView n;
    private TextView o;
    private TextView p;
    private TextView q;
    private TextView r;
    private TextView s;
    private TextView t;
    private TextView u;
    private int v;

    public SofaDialog(Context context) {
        super(context, R.style.ImprovedDialogInput);
        getWindow().setWindowAnimations(0);
        setCanceledOnTouchOutside(true);
        if (getContext().getResources().getConfiguration().orientation == 2) {
            getWindow().addFlags(1024);
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_sofa);
        findViewById(R.id.iv_close).setOnClickListener(this);
        this.a = (EditText) findViewById(R.id.et_input_num);
        this.a.addTextChangedListener(this);
        this.b = (TextView) findViewById(R.id.tv_send);
        this.b.setOnClickListener(this);
        this.c = (LinearLayout) findViewById(R.id.ll1_sofa);
        this.d = (LinearLayout) findViewById(R.id.ll2_sofa);
        this.e = (LinearLayout) findViewById(R.id.ll3_sofa);
        this.f = (LinearLayout) findViewById(R.id.ll4_sofa);
        this.d.setOnClickListener(this);
        this.e.setOnClickListener(this);
        this.f.setOnClickListener(this);
        this.c.setOnClickListener(this);
        this.i = (SimpleDraweeView) findViewById(R.id.sdv1_header);
        this.j = (SimpleDraweeView) findViewById(R.id.sdv2_header);
        this.k = (SimpleDraweeView) findViewById(R.id.sdv3_header);
        this.l = (SimpleDraweeView) findViewById(R.id.sdv4_header);
        this.m = (TextView) findViewById(R.id.tv1_name);
        this.n = (TextView) findViewById(R.id.tv2_name);
        this.o = (TextView) findViewById(R.id.tv3_name);
        this.p = (TextView) findViewById(R.id.tv4_name);
        this.q = (TextView) findViewById(R.id.tv1_num);
        this.r = (TextView) findViewById(R.id.tv2_num);
        this.s = (TextView) findViewById(R.id.tv3_num);
        this.t = (TextView) findViewById(R.id.tv4_num);
        this.u = (TextView) findViewById(R.id.tv_explain);
        this.u.setText(getContext().getString(R.string.sofa_item, new Object[]{Integer.valueOf(0)}));
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_close) {
            dismiss();
        } else if (id == R.id.ll1_sofa) {
            this.c.setSelected(true);
            this.d.setSelected(false);
            this.e.setSelected(false);
            this.f.setSelected(false);
            this.g = 1;
            this.v = Integer.parseInt(this.q.getText().toString());
            this.u.setText(getContext().getString(R.string.sofa_item, new Object[]{Integer.valueOf(this.v)}));
        } else if (id == R.id.ll2_sofa) {
            this.c.setSelected(false);
            this.d.setSelected(true);
            this.e.setSelected(false);
            this.f.setSelected(false);
            this.g = 2;
            this.v = Integer.parseInt(this.r.getText().toString());
            this.u.setText(getContext().getString(R.string.sofa_item, new Object[]{Integer.valueOf(this.v)}));
        } else if (id == R.id.ll3_sofa) {
            this.c.setSelected(false);
            this.d.setSelected(false);
            this.e.setSelected(true);
            this.f.setSelected(false);
            this.g = 3;
            this.v = Integer.parseInt(this.s.getText().toString());
            this.u.setText(getContext().getString(R.string.sofa_item, new Object[]{Integer.valueOf(this.v)}));
        } else if (id == R.id.ll4_sofa) {
            this.c.setSelected(false);
            this.d.setSelected(false);
            this.e.setSelected(false);
            this.f.setSelected(true);
            this.g = 4;
            this.v = Integer.parseInt(this.t.getText().toString());
            this.u.setText(getContext().getString(R.string.sofa_item, new Object[]{Integer.valueOf(this.v)}));
        } else if (id == R.id.tv_send && this.h != null) {
            if (this.g == -1) {
                Toast.makeText(getContext(), "请选择座位", 0).show();
            } else if (!TextUtils.isEmpty(this.a.getText().toString())) {
                try {
                    id = Integer.parseInt(this.a.getText().toString());
                    if (id <= this.v) {
                        Toast.makeText(getContext(), "沙发数量不足", 0).show();
                        return;
                    }
                    this.h.sendSofa(this.g, id);
                    dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void show(int i) {
        super.show();
        switch (i) {
            case 0:
                this.c.performClick();
                return;
            case 1:
                this.d.performClick();
                return;
            case 2:
                this.e.performClick();
                return;
            case 3:
                this.f.performClick();
                return;
            default:
                return;
        }
    }

    public void setSofaControl(SofaControlable sofaControlable) {
        this.h = sofaControlable;
    }

    public void dismiss() {
        super.dismiss();
        this.c.setSelected(false);
        this.d.setSelected(false);
        this.e.setSelected(false);
        this.f.setSelected(false);
        this.g = -1;
        this.a.setText("");
        this.u.setText(getContext().getString(R.string.sofa_item, new Object[]{Integer.valueOf(0)}));
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (TextUtils.isEmpty(charSequence)) {
            this.b.setSelected(false);
            this.b.setClickable(false);
            return;
        }
        this.b.setSelected(true);
        this.b.setClickable(true);
    }

    public void afterTextChanged(Editable editable) {
    }

    public void updateSofa(SofaBean sofaBean) {
        switch (sofaBean.getSite()) {
            case 1:
                a(this.i, this.m, this.q, sofaBean);
                break;
            case 2:
                a(this.j, this.n, this.r, sofaBean);
                break;
            case 3:
                a(this.k, this.o, this.s, sofaBean);
                break;
            case 4:
                a(this.l, this.p, this.t, sofaBean);
                break;
        }
        if (this.g != -1 && this.g == sofaBean.getSite()) {
            this.v = sofaBean.getNum();
            this.u.setText(getContext().getString(R.string.sofa_item, new Object[]{Integer.valueOf(this.v)}));
        }
    }

    private static void a(SimpleDraweeView simpleDraweeView, TextView textView, TextView textView2, SofaBean sofaBean) {
        simpleDraweeView.setImageURI(UriUtil.parseUriOrNull(sofaBean.getPic()));
        textView.setText(sofaBean.getAlias());
        textView2.setText(String.valueOf(sofaBean.getNum()));
    }
}
