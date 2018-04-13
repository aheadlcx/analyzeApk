package com.budejie.www.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import com.budejie.www.R;
import com.budejie.www.R$styleable;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.mycomment.d;
import com.budejie.www.activity.textcomment.MakeTextCommentsActivity;
import com.budejie.www.bean.NewCommentItem;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.c;
import com.budejie.www.http.j;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;
import java.io.Serializable;
import net.tsz.afinal.a.b;

public class CmtPopupWindow extends PopupWindow {
    OnClickListener a = new OnClickListener(this) {
        final /* synthetic */ CmtPopupWindow a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            this.a.a("0", this.a.k.a.id);
        }
    };
    OnClickListener b = new OnClickListener(this) {
        final /* synthetic */ CmtPopupWindow a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            this.a.a("1", this.a.k.a.id);
        }
    };
    net.tsz.afinal.a.a<String> c = new net.tsz.afinal.a.a<String>(this) {
        final /* synthetic */ CmtPopupWindow a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void onFailure(Throwable th, int i, String str) {
            this.a.l = Toast.makeText(this.a.e, this.a.e.getString(R.string.operate_fail), 0);
            this.a.l.show();
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a(java.lang.String r6) {
            /*
            r5 = this;
            r1 = com.budejie.www.util.z.k(r6);	 Catch:{ Exception -> 0x0064 }
            if (r1 == 0) goto L_0x0069;
        L_0x0006:
            r0 = "result_desc";
            r0 = r1.get(r0);	 Catch:{ Exception -> 0x0064 }
            r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x0064 }
            r2 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x0064 }
            if (r2 != 0) goto L_0x0046;
        L_0x0014:
            r2 = r5.a;	 Catch:{ Exception -> 0x0064 }
            r3 = r5.a;	 Catch:{ Exception -> 0x0064 }
            r3 = r3.e;	 Catch:{ Exception -> 0x0064 }
            r4 = 0;
            r0 = android.widget.Toast.makeText(r3, r0, r4);	 Catch:{ Exception -> 0x0064 }
            r2.l = r0;	 Catch:{ Exception -> 0x0064 }
            r0 = "result";
            r0 = r1.get(r0);	 Catch:{ Exception -> 0x0064 }
            r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x0064 }
            r1 = "0";
            r0 = r1.equals(r0);	 Catch:{ Exception -> 0x0064 }
            if (r0 == 0) goto L_0x0034;
        L_0x0034:
            r0 = r5.a;	 Catch:{ Exception -> 0x0064 }
            r0 = r0.l;	 Catch:{ Exception -> 0x0064 }
            if (r0 == 0) goto L_0x0045;
        L_0x003c:
            r0 = r5.a;	 Catch:{ Exception -> 0x0064 }
            r0 = r0.l;	 Catch:{ Exception -> 0x0064 }
            r0.show();	 Catch:{ Exception -> 0x0064 }
        L_0x0045:
            return;
        L_0x0046:
            r0 = r5.a;	 Catch:{ Exception -> 0x0064 }
            r1 = r5.a;	 Catch:{ Exception -> 0x0064 }
            r1 = r1.e;	 Catch:{ Exception -> 0x0064 }
            r2 = r5.a;	 Catch:{ Exception -> 0x0064 }
            r2 = r2.e;	 Catch:{ Exception -> 0x0064 }
            r3 = 2131231714; // 0x7f0803e2 float:1.8079517E38 double:1.052968373E-314;
            r2 = r2.getString(r3);	 Catch:{ Exception -> 0x0064 }
            r3 = 0;
            r1 = android.widget.Toast.makeText(r1, r2, r3);	 Catch:{ Exception -> 0x0064 }
            r0.l = r1;	 Catch:{ Exception -> 0x0064 }
            goto L_0x0034;
        L_0x0064:
            r0 = move-exception;
            r0.printStackTrace();
            goto L_0x0045;
        L_0x0069:
            r0 = r5.a;	 Catch:{ Exception -> 0x0064 }
            r1 = r5.a;	 Catch:{ Exception -> 0x0064 }
            r1 = r1.e;	 Catch:{ Exception -> 0x0064 }
            r2 = r5.a;	 Catch:{ Exception -> 0x0064 }
            r2 = r2.e;	 Catch:{ Exception -> 0x0064 }
            r3 = 2131231714; // 0x7f0803e2 float:1.8079517E38 double:1.052968373E-314;
            r2 = r2.getString(r3);	 Catch:{ Exception -> 0x0064 }
            r3 = 0;
            r1 = android.widget.Toast.makeText(r1, r2, r3);	 Catch:{ Exception -> 0x0064 }
            r0.l = r1;	 Catch:{ Exception -> 0x0064 }
            goto L_0x0034;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.budejie.www.widget.CmtPopupWindow.4.a(java.lang.String):void");
        }
    };
    private a d;
    private Context e;
    private TextView f;
    private TextView g;
    private TextView h;
    private TextView i;
    private int j = R$styleable.Theme_Custom_shape_cmt_like4_bg;
    private d k;
    private Toast l;
    private c m;
    private String n;
    private OnClickListener o = new OnClickListener(this) {
        final /* synthetic */ CmtPopupWindow a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            if (view == this.a.f) {
                this.a.d.a(this.a.k);
            } else if (view == this.a.h) {
                this.a.a();
            } else if (view == this.a.i) {
                this.a.c();
            }
            this.a.dismiss();
        }
    };

    public interface a {
        void a(d dVar);
    }

    public CmtPopupWindow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = context;
        this.n = ai.b(context);
        a(context);
    }

    public CmtPopupWindow(Context context) {
        super(context);
        this.e = context;
        a(context);
    }

    public void a(c cVar) {
        this.m = cVar;
    }

    private void a(Context context) {
        this.n = ai.b(context);
        View inflate = LayoutInflater.from(context).inflate(R.layout.cmt_pop_item, null);
        this.f = (TextView) inflate.findViewById(R.id.cmt_pop_ding);
        this.g = (TextView) inflate.findViewById(R.id.likeCountAnim);
        this.h = (TextView) inflate.findViewById(R.id.cmt_pop_reply);
        if (an.v(context)) {
            ImageView imageView = (ImageView) inflate.findViewById(R.id.cmt_iv_d1);
            TextView textView = (TextView) inflate.findViewById(R.id.cmt_pop_delete);
            TextView textView2 = (TextView) inflate.findViewById(R.id.cmt_pop_blacklist);
            ((ImageView) inflate.findViewById(R.id.cmt_iv_d0)).setVisibility(0);
            imageView.setVisibility(0);
            textView.setVisibility(0);
            textView2.setVisibility(0);
            textView.setOnClickListener(this.a);
            textView2.setOnClickListener(this.b);
            this.j = R$styleable.Theme_Custom_label_list_search_icon;
        }
        this.i = (TextView) inflate.findViewById(R.id.cmt_pop_report);
        this.i.setText("举报");
        this.i.setOnClickListener(this.o);
        this.f.setOnClickListener(this.o);
        this.h.setOnClickListener(this.o);
        setContentView(inflate);
    }

    private void a() {
        Serializable b = b();
        Intent intent = new Intent(this.e, MakeTextCommentsActivity.class);
        intent.putExtra("posts_id", this.k.c.getWid());
        intent.putExtra("reply_comment", b);
        this.e.startActivity(intent);
        ((Activity) this.e).overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    private NewCommentItem b() {
        if (this.k == null || this.k.a == null) {
            return null;
        }
        NewCommentItem newCommentItem = new NewCommentItem();
        newCommentItem.id = this.k.a.id;
        newCommentItem.data_id = this.k.a.data_id;
        newCommentItem.status = this.k.a.status;
        newCommentItem.content = this.k.a.content;
        newCommentItem.ctime = this.k.a.ctime;
        newCommentItem.precid = this.k.a.precid;
        newCommentItem.preuid = this.k.a.preuid;
        newCommentItem.like_count = this.k.a.like_count;
        newCommentItem.voiceuri = this.k.a.voiceuri;
        newCommentItem.voicetime = this.k.a.voicetime;
        newCommentItem.user = this.k.a.user;
        return newCommentItem;
    }

    private void c() {
        String str = this.k.a.id;
        this.m.a(this.e, this.k.c.getWid(), str, this.n);
    }

    private void a(String str, String str2) {
        BudejieApplication.a.a(RequstMethod.GET, "https://api.budejie.com/api/api_open.php", b(str, str2), this.c);
        dismiss();
    }

    private b b(String str, String str2) {
        return new j().k(this.e, str, str2);
    }
}
