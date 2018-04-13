package cn.xiaochuankeji.tieba.ui.publish;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.webview.WebActivity;
import com.flyco.dialog.c.a.b;

public class a extends b<a> implements OnClickListener {
    private View A;
    private a B;
    private String C = "";
    private ImageView u;
    private ImageView v;
    private TextView w;
    private EditText x;
    private TextView y;
    private TextView z;

    public interface a {
        void a(String str);
    }

    public a(Context context, a aVar) {
        super(context);
        this.B = aVar;
    }

    public View a() {
        View inflate = View.inflate(this.c, R.layout.dialog_input_link, null);
        this.u = (ImageView) inflate.findViewById(R.id.dialog_close);
        this.v = (ImageView) inflate.findViewById(R.id.ivClear);
        this.w = (TextView) inflate.findViewById(R.id.tv_add);
        this.x = (EditText) inflate.findViewById(R.id.et_url);
        this.y = (TextView) inflate.findViewById(R.id.tv_title);
        this.z = (TextView) inflate.findViewById(R.id.tv_describe);
        this.A = inflate.findViewById(R.id.split_line);
        getWindow().setSoftInputMode(48);
        return inflate;
    }

    public void b() {
        this.z.setText(cn.xiaochuankeji.tieba.background.utils.c.a.c().A());
        this.x.setText(this.C);
        this.u.setOnClickListener(this);
        this.w.setOnClickListener(this);
        this.z.setOnClickListener(this);
        this.v.setOnClickListener(this);
        this.x.requestFocus();
        this.x.postDelayed(new Runnable(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void run() {
                cn.htjyb.c.a.a(this.a.x, this.a.getContext());
            }
        }, 500);
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    public void a(String str) {
        this.C = str;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dialog_close:
                dismiss();
                return;
            case R.id.tv_add:
                this.B.a(this.x.getText().toString());
                dismiss();
                return;
            case R.id.ivClear:
                this.x.setText("");
                return;
            case R.id.tv_describe:
                WebActivity.a(this.c, cn.xiaochuan.jsbridge.b.a(null, cn.xiaochuankeji.tieba.background.utils.d.a.d("https://$$/help/external_link.html")));
                return;
            default:
                return;
        }
    }
}
