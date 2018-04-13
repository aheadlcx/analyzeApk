package com.budejie.www.adapter.d;

import android.app.Activity;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.adapter.RowType;
import com.budejie.www.adapter.a;
import com.budejie.www.adapter.b;
import com.budejie.www.adapter.f.e;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.bean.OperationButton;
import com.umeng.analytics.MobclickAgent;

public class i extends a {
    protected final ListItemObject a;
    protected final Activity b;
    protected final LayoutInflater c;
    protected final com.budejie.www.adapter.e.a d;
    protected final int e;
    protected int f;

    public /* synthetic */ Object d() {
        return a();
    }

    public i(Activity activity, com.budejie.www.adapter.e.a aVar, ListItemObject listItemObject, int i) {
        this.a = listItemObject;
        this.b = activity;
        this.d = aVar;
        this.c = LayoutInflater.from(activity);
        this.e = i;
    }

    public View b() {
        this.f = 0;
        e eVar = new e();
        ViewGroup viewGroup = (ViewGroup) this.c.inflate(R.layout.new_new_list_item_opreation, null);
        eVar.a = (AsyncImageView) viewGroup.findViewById(R.id.background_img);
        eVar.b = (TextView) viewGroup.findViewById(R.id.title_txt);
        eVar.c = (TextView) viewGroup.findViewById(R.id.content_txt);
        eVar.d = (ImageButton) viewGroup.findViewById(R.id.refersh_imgbtn);
        eVar.e = (Button) viewGroup.findViewById(R.id.jion_btn);
        eVar.f = (Button) viewGroup.findViewById(R.id.invite_btn);
        viewGroup.setTag(eVar);
        return viewGroup;
    }

    public int c() {
        return RowType.OPERATION_ROW.ordinal();
    }

    public void a(b bVar) {
        e eVar = (e) bVar;
        if (this.a.getOperation() != null) {
            eVar.a.setAsyncCacheImage(this.a.getOperation().backgroud_pic);
            eVar.b.setText(this.a.getOperation().title);
            eVar.c.setText(this.a.getOperation().words);
            try {
                if (this.a.getOperation().operationButtonList != null) {
                    if (this.a.getOperation().operationButtonList.size() == 1) {
                        eVar.e.setText(((OperationButton) this.a.getOperation().operationButtonList.get(0)).words);
                        eVar.f.setVisibility(8);
                    } else if (this.a.getOperation().operationButtonList.size() == 2) {
                        eVar.e.setText(((OperationButton) this.a.getOperation().operationButtonList.get(0)).words);
                        CharSequence charSequence = ((OperationButton) this.a.getOperation().operationButtonList.get(1)).words;
                        if (TextUtils.isEmpty(charSequence)) {
                            eVar.f.setVisibility(8);
                        } else {
                            eVar.f.setVisibility(0);
                            eVar.f.setText(charSequence);
                        }
                    }
                }
                String[] split = this.a.getOperation().font_color.split(",");
                eVar.b.setTextColor(Color.parseColor(split[0]));
                eVar.c.setTextColor(Color.parseColor(split[1]));
                if (this.a.getOperation().show_num.equals("1")) {
                    eVar.d.setVisibility(8);
                } else {
                    eVar.d.setVisibility(0);
                }
            } catch (Exception e) {
            }
            eVar.d.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ i a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    i iVar = this.a;
                    iVar.f++;
                    if (this.a.f < 6) {
                        MobclickAgent.onEvent(this.a.b, "E01_A07", "运营位刷新点击" + this.a.f + "次");
                    } else {
                        MobclickAgent.onEvent(this.a.b, "E01_A07", "运营位刷新点击大于5次");
                    }
                    MobclickAgent.onEvent(this.a.b, "E01-A05", "运营位刷新");
                    this.a.d.a_("refersh");
                }
            });
            eVar.e.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ i a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    MobclickAgent.onEvent(this.a.b, "E01-A05", "运营位立即参加：" + this.a.a.getOperation().order_id);
                    this.a.d.a_(((OperationButton) this.a.a.getOperation().operationButtonList.get(0)).jump_type);
                }
            });
            eVar.f.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ i a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    MobclickAgent.onEvent(this.a.b, "E01-A05", "运营位邀请好友" + this.a.a.getOperation().order_id);
                    this.a.d.a_(((OperationButton) this.a.a.getOperation().operationButtonList.get(1)).jump_type);
                }
            });
        }
    }

    public ListItemObject a() {
        return this.a;
    }
}
