package cn.xiaochuankeji.tieba.ui.publish;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.utils.g;
import java.util.ArrayList;
import java.util.List;

public class b extends com.flyco.dialog.c.a.b<a> implements OnClickListener {
    private FrameLayout A;
    private ViewGroup B;
    private ImageView C;
    private ImageView D;
    private ImageView E;
    private List<String> F = new ArrayList(4);
    private TextView G;
    private TextView H;
    private a I;
    private TextView u;
    private EditText v;
    private EditText w;
    private EditText x;
    private EditText y;
    private FrameLayout z;

    public interface a {
        void a(List<String> list);
    }

    public b(Context context, a aVar) {
        super(context);
        this.I = aVar;
    }

    public View a() {
        View inflate = View.inflate(this.c, R.layout.dialog_input_vote, null);
        this.u = (TextView) inflate.findViewById(R.id.tv_add_vote);
        this.v = (EditText) inflate.findViewById(R.id.et_vote1);
        this.w = (EditText) inflate.findViewById(R.id.et_vote2);
        this.x = (EditText) inflate.findViewById(R.id.et_vote3);
        this.y = (EditText) inflate.findViewById(R.id.et_vote4);
        this.D = (ImageView) inflate.findViewById(R.id.ivDelete3);
        this.E = (ImageView) inflate.findViewById(R.id.ivDelete4);
        this.B = (ViewGroup) inflate.findViewById(R.id.add_container);
        getWindow().setSoftInputMode(48);
        this.C = (ImageView) inflate.findViewById(R.id.dialog_close);
        this.G = (TextView) inflate.findViewById(R.id.tv_add);
        this.H = (TextView) inflate.findViewById(R.id.tv_title);
        this.z = (FrameLayout) inflate.findViewById(R.id.flEditText3Container);
        this.A = (FrameLayout) inflate.findViewById(R.id.flEditText4Container);
        return inflate;
    }

    public void b() {
        this.u.setOnClickListener(this);
        this.D.setOnClickListener(this);
        this.E.setOnClickListener(this);
        this.G.setOnClickListener(this);
        this.C.setOnClickListener(this);
        this.v.requestFocus();
        this.v.postDelayed(new Runnable(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void run() {
                cn.htjyb.c.a.a(this.a.v, this.a.getContext());
            }
        }, 500);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dialog_close:
                dismiss();
                return;
            case R.id.tv_add:
                i();
                return;
            case R.id.ivDelete3:
                a(this.x);
                return;
            case R.id.ivDelete4:
                a(this.y);
                return;
            case R.id.tv_add_vote:
                h();
                return;
            default:
                return;
        }
    }

    public void a(List<String> list) {
        if (list != null && list.size() != 0) {
            this.F = list;
            if (this.F.size() >= 1) {
                this.v.setText((CharSequence) this.F.get(0));
            }
            if (this.F.size() >= 2) {
                this.w.setText((CharSequence) this.F.get(1));
            }
            if (this.F.size() >= 3) {
                this.x.setText((CharSequence) this.F.get(2));
                this.z.setVisibility(0);
            }
            if (this.F.size() == 4) {
                this.y.setText((CharSequence) this.F.get(3));
                this.A.setVisibility(0);
            }
        }
    }

    private void a(View view) {
        if (this.A.getVisibility() == 0) {
            this.A.setVisibility(8);
            if (view.equals(this.x)) {
                this.x.setText(this.y.getText());
            }
            this.B.setVisibility(0);
            this.y.setText("");
            return;
        }
        this.z.setVisibility(8);
        this.x.setText("");
    }

    private void h() {
        if (this.z.getVisibility() == 8) {
            this.z.setVisibility(0);
        } else if (this.A.getVisibility() == 8) {
            this.A.setVisibility(0);
            this.B.setVisibility(8);
        } else {
            g.b("只能添加4个选项");
        }
    }

    private void i() {
        this.F.clear();
        CharSequence obj = this.v.getText().toString();
        CharSequence obj2 = this.w.getText().toString();
        CharSequence obj3 = this.x.getText().toString();
        CharSequence obj4 = this.y.getText().toString();
        if (!TextUtils.isEmpty(obj)) {
            this.F.add(obj);
        }
        if (!TextUtils.isEmpty(obj2)) {
            this.F.add(obj2);
        }
        if (!TextUtils.isEmpty(obj3)) {
            this.F.add(obj3);
        }
        if (!TextUtils.isEmpty(obj4)) {
            this.F.add(obj4);
        }
        if (this.F.size() <= 1) {
            g.b("您必须有两个非空选项");
            return;
        }
        this.I.a(this.F);
        dismiss();
    }
}
