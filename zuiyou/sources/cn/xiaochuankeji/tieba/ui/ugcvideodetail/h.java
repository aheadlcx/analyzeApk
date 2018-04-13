package cn.xiaochuankeji.tieba.ui.ugcvideodetail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.b.b;
import cn.xiaochuankeji.tieba.background.net.XCError;
import cn.xiaochuankeji.tieba.background.utils.c.c;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.ui.CustomReportReasonActivity;
import cn.xiaochuankeji.tieba.ui.utils.e;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import java.util.ArrayList;
import org.json.JSONObject;

public class h extends com.flyco.dialog.c.a.a<h> implements OnClickListener {
    private GridView a;
    private LinearLayout l;
    private ArrayList<c> m = new ArrayList();
    private long n;
    private long o;
    private a p;
    private LayoutInflater q;

    class a extends BaseAdapter {
        final /* synthetic */ h a;

        class a {
            final /* synthetic */ a a;
            private WebImageView b;
            private TextView c;
            private c d;

            public a(final a aVar, View view) {
                this.a = aVar;
                this.b = (WebImageView) view.findViewById(R.id.wivIcon);
                this.c = (TextView) view.findViewById(R.id.tvTitle);
                view.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ a b;

                    public void onClick(View view) {
                        this.b.a.a.a(this.b.d);
                        this.b.a.a.dismiss();
                    }
                });
            }

            public void a(c cVar) {
                this.d = cVar;
                this.b.setImageURI(cVar.c);
                this.c.setText(cVar.b);
            }
        }

        a(h hVar) {
            this.a = hVar;
        }

        public int getCount() {
            return this.a.m.size();
        }

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            a aVar;
            if (view == null) {
                view = this.a.q.inflate(R.layout.view_report_item, null);
                a aVar2 = new a(this, view);
                view.setTag(aVar2);
                aVar = aVar2;
            } else {
                aVar = (a) view.getTag();
            }
            aVar.a((c) this.a.m.get(i));
            return view;
        }
    }

    public h(Context context, ArrayList<c> arrayList) {
        super(context, false);
        this.m = arrayList;
    }

    public View a() {
        this.q = LayoutInflater.from(this.c);
        View inflate = View.inflate(this.c, R.layout.view_ugcvideo_report, null);
        this.a = (GridView) inflate.findViewById(R.id.gvReason);
        this.l = (LinearLayout) inflate.findViewById(R.id.llReportContent);
        inflate.setOnClickListener(this);
        return inflate;
    }

    public void b() {
        this.p = new a(this);
        this.a.setAdapter(this.p);
        this.j.setOnClickListener(this);
        this.l.setOnClickListener(this);
        int b = ((e.b() - e.a(20.0f)) - (e.a(57.0f) * 3)) / 4;
        this.a.setHorizontalSpacing(b - 20);
        int a = e.a(20.0f);
        this.a.setPadding(b, a, b, a);
    }

    public void a(long j, long j2) {
        this.n = j;
        this.o = j2;
        show();
    }

    public void onClick(View view) {
        if (view == this.j) {
            dismiss();
        } else if (view != this.l) {
        }
    }

    private void a(c cVar) {
        if (cVar.b.equals("其他")) {
            CustomReportReasonActivity.a(this.c, this.n, this.o, cVar.a, "ugcvideo");
        } else {
            new b(this.n, this.o, "ugcvideo", cVar.a, null, new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
                final /* synthetic */ h a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onResponse(Object obj, Object obj2) {
                    a((JSONObject) obj, obj2);
                }

                public void a(JSONObject jSONObject, Object obj) {
                    g.b("举报成功");
                }
            }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
                final /* synthetic */ h a;

                {
                    this.a = r1;
                }

                public void onErrorResponse(XCError xCError, Object obj) {
                    g.b(xCError.getMessage());
                }
            }).execute();
        }
    }
}
