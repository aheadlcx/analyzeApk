package cn.xiaochuankeji.tieba.ui.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.htjyb.netlib.NetworkMonitor;
import cn.htjyb.netlib.h;
import cn.xiaochuankeji.tieba.R;

public class f extends LinearLayout {
    private Context a;
    private ImageView b;
    private TextView c;
    private TextView d;
    private TextView e;
    private a f;

    public interface a {
        void a();
    }

    public f(Context context, a aVar) {
        super(context);
        this.a = context;
        this.f = aVar;
        a();
        setVisibility(8);
    }

    private void a() {
        LayoutInflater.from(this.a).inflate(R.layout.view_query_exception, this);
        this.b = (ImageView) findViewById(R.id.ivImage);
        this.c = (TextView) findViewById(R.id.tvTips);
        this.d = (TextView) findViewById(R.id.tvOptionRefresh);
        this.e = (TextView) findViewById(R.id.tvOptionSetNetWork);
        this.d.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.f != null) {
                    this.a.f.a();
                }
            }
        });
        this.e.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                h.b(this.a.a);
            }
        });
    }

    public void a(int i, String str) {
        TextView textView = this.c;
        if (TextUtils.isEmpty(str)) {
            str = "呀，网络开小差啦";
        }
        textView.setText(str);
        ImageView imageView = this.b;
        if (i == 0) {
            i = R.drawable.img_exception_network_error_in_musicselect;
        }
        imageView.setImageResource(i);
        this.d.setVisibility(0);
        this.e.setVisibility(NetworkMonitor.a() ? 8 : 0);
        if (this.b.getPaddingTop() == 0) {
            setVisibility(4);
            b();
            return;
        }
        setVisibility(0);
    }

    public void b(int i, String str) {
        this.d.setVisibility(8);
        this.e.setVisibility(8);
        this.b.setImageResource(i);
        this.c.setText(str);
        if (this.b.getPaddingTop() == 0) {
            setVisibility(4);
            b();
            return;
        }
        setVisibility(0);
    }

    private void b() {
        this.b.post(new Runnable(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.b.setPadding(0, (int) ((((float) this.a.getMeasuredHeight()) * 0.38f) - (((float) this.a.b.getMeasuredHeight()) * 0.5f)), 0, 0);
                this.a.setVisibility(0);
            }
        });
    }
}
