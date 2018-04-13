package com.budejie.www.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.v6.sixrooms.room.gift.GiftConfigUtil;
import com.budejie.www.R;
import com.budejie.www.activity.base.BaseTitleActivity;
import com.budejie.www.adapter.a.f;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.h;
import com.budejie.www.http.j;
import com.budejie.www.type.MySquareIcon;
import com.budejie.www.type.MySquareItem;
import com.budejie.www.util.aa;
import com.budejie.www.util.an;
import com.budejie.www.widget.XListView;
import com.google.gson.Gson;
import java.util.List;
import net.tsz.afinal.a.a;

public class MySquareMoreIcon extends BaseTitleActivity {
    a<String> a = new a<String>(this) {
        final /* synthetic */ MySquareMoreIcon a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void a(String str) {
            aa.a("MySquareMoreIcon", "结果：" + str);
            try {
                MySquareItem mySquareItem = (MySquareItem) new Gson().fromJson(str, MySquareItem.class);
                if (mySquareItem != null && mySquareItem.getMySquaresMoreIcon() != null && mySquareItem.getMySquaresMoreIcon().size() > 0) {
                    this.a.j = mySquareItem.getMySquaresMoreIcon();
                    this.a.i.a(this.a.j);
                    this.a.e.setAdapter(this.a.i);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void onFailure(Throwable th, int i, String str) {
            aa.a("MyInfoActivity", "加载广场数据失败：" + str);
        }
    };
    private Activity b;
    private Button c;
    private TextView d;
    private XListView e;
    private f i;
    private List<MySquareIcon> j;
    private OnClickListener k = new OnClickListener(this) {
        final /* synthetic */ MySquareMoreIcon a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            this.a.b.finish();
        }
    };

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.my_square_more_icon_list_layout);
        this.b = this;
        a();
        b();
    }

    private void a() {
        an.a((LinearLayout) findViewById(R.id.TitleGapLayout));
        this.c = (Button) findViewById(R.id.title_left_btn);
        this.c.setVisibility(0);
        this.c.setOnClickListener(this.k);
        this.d = (TextView) findViewById(R.id.title_center_txt);
        this.d.setText("更多");
        this.e = (XListView) findViewById(R.id.moreIcon_listview);
        this.e.setPullLoadEnable(false);
        this.e.setPullRefreshEnable(false);
        this.i = new f(this.b);
    }

    private void b() {
        h hVar = new h("http://s.budejie.com/op/squaremore");
        hVar.a("0", GiftConfigUtil.SUPER_GIRL_GIFT_TAG).a();
        BudejieApplication.a.a(RequstMethod.GET, hVar.toString(), new j(this), this.a);
    }
}
