package cn.v6.sixrooms.room.adapter;

import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cn.v6.sixrooms.bean.FansBean;
import cn.v6.sixrooms.room.BaseRoomActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.List;

public class FansListOfFullScreenAdapter extends BaseAdapter {
    private BaseRoomActivity a;
    private List<FansBean> b;
    private int c = 0;
    private boolean d = false;

    public static class FansSecondViewHolder {
        TextView a;
        SimpleDraweeView b;
        TextView c;
        TextView d;
        View e;
    }

    public static class FansViewHolder {
        TextView a;
        SimpleDraweeView b;
        ImageView c;
        TextView d;
        View e;
    }

    public void setmIsStarLight(boolean z) {
        this.d = z;
    }

    public FansListOfFullScreenAdapter(BaseRoomActivity baseRoomActivity, List<FansBean> list) {
        this.a = baseRoomActivity;
        this.b = list;
    }

    public void setmState(int i) {
        this.c = i;
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

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View getView(int r10, android.view.View r11, android.view.ViewGroup r12) {
        /*
        r9 = this;
        r6 = 1117782016; // 0x42a00000 float:80.0 double:5.522576936E-315;
        r8 = 1113063424; // 0x42580000 float:54.0 double:5.499263994E-315;
        r5 = 1;
        r7 = 0;
        r3 = 0;
        r0 = r9.getItemViewType(r10);
        switch(r0) {
            case 0: goto L_0x000f;
            case 1: goto L_0x01a5;
            default: goto L_0x000e;
        };
    L_0x000e:
        return r11;
    L_0x000f:
        if (r11 == 0) goto L_0x00b6;
    L_0x0011:
        r0 = r11.getTag();
        r0 = (cn.v6.sixrooms.room.adapter.FansListOfFullScreenAdapter.FansViewHolder) r0;
        r1 = r0;
    L_0x0018:
        r0 = r9.a;
        r0 = r0.getResources();
        r0 = r0.getConfiguration();
        r0 = r0.orientation;
        r2 = 2;
        if (r0 != r2) goto L_0x00f8;
    L_0x0027:
        r0 = r1.a;
        r2 = 5;
        r0.setMaxEms(r2);
    L_0x002d:
        r0 = r1.e;
        r0.setVisibility(r3);
        r0 = cn.v6.sixrooms.R.drawable.v6_ic_pop_rank_1;
        switch(r10) {
            case 0: goto L_0x010f;
            case 1: goto L_0x011a;
            case 2: goto L_0x011e;
            default: goto L_0x0037;
        };
    L_0x0037:
        r2 = r1.c;
        r2.setBackgroundResource(r0);
        r0 = r9.b;
        r0 = r0.get(r10);
        r0 = (cn.v6.sixrooms.bean.FansBean) r0;
        r2 = r1.b;
        r4 = r0.getPicuser();
        r4 = android.net.Uri.parse(r4);
        r2.setImageURI(r4);
        r2 = r1.a;
        r4 = r0.getUname();
        r2.setText(r4);
        r2 = r0.getContribution();
        r4 = android.text.TextUtils.isEmpty(r2);
        if (r4 == 0) goto L_0x0335;
    L_0x0064:
        r0 = r0.getMoney();
    L_0x0068:
        r2 = r9.a;
        r2 = r2.getResources();
        r4 = cn.v6.sixrooms.R.drawable.transparent;
        r2 = r2.getDrawable(r4);
        r4 = r9.a;
        r4 = r4.isSuperGirlRoom();
        r4 = r4.booleanValue();
        if (r4 == 0) goto L_0x0153;
    L_0x0080:
        r4 = r9.c;
        if (r4 == 0) goto L_0x0153;
    L_0x0084:
        r4 = r1.d;
        r6 = "";
        r4.setText(r6);
        r4 = r1.d;
        r4 = r4.getLayoutParams();
        r6 = cn.v6.sixrooms.utils.DensityUtil.dip2px(r8);
        r4.width = r6;
        r4 = -1;
        r6 = r0.hashCode();
        switch(r6) {
            case 49: goto L_0x0122;
            case 50: goto L_0x012d;
            default: goto L_0x009f;
        };
    L_0x009f:
        r5 = r4;
    L_0x00a0:
        switch(r5) {
            case 0: goto L_0x0137;
            case 1: goto L_0x0145;
            default: goto L_0x00a3;
        };
    L_0x00a3:
        r0 = r2;
    L_0x00a4:
        r2 = r0.getMinimumWidth();
        r4 = r0.getMinimumHeight();
        r0.setBounds(r3, r3, r2, r4);
        r1 = r1.d;
        r1.setCompoundDrawables(r0, r7, r7, r7);
        goto L_0x000e;
    L_0x00b6:
        r0 = r9.a;
        r1 = cn.v6.sixrooms.R.layout.pop_rank_fans_item;
        r11 = android.view.View.inflate(r0, r1, r7);
        r1 = new cn.v6.sixrooms.room.adapter.FansListOfFullScreenAdapter$FansViewHolder;
        r1.<init>();
        r0 = cn.v6.sixrooms.R.id.iv_rank;
        r0 = r11.findViewById(r0);
        r0 = (android.widget.ImageView) r0;
        r1.c = r0;
        r0 = cn.v6.sixrooms.R.id.iv_identity;
        r0 = r11.findViewById(r0);
        r0 = (com.facebook.drawee.view.SimpleDraweeView) r0;
        r1.b = r0;
        r0 = cn.v6.sixrooms.R.id.tv_name;
        r0 = r11.findViewById(r0);
        r0 = (android.widget.TextView) r0;
        r1.a = r0;
        r0 = cn.v6.sixrooms.R.id.tv_coin6;
        r0 = r11.findViewById(r0);
        r0 = (android.widget.TextView) r0;
        r1.d = r0;
        r0 = cn.v6.sixrooms.R.id.division;
        r0 = r11.findViewById(r0);
        r1.e = r0;
        r11.setTag(r1);
        goto L_0x0018;
    L_0x00f8:
        r0 = r9.a;
        r0 = r0.getResources();
        r0 = r0.getConfiguration();
        r0 = r0.orientation;
        if (r0 != r5) goto L_0x002d;
    L_0x0106:
        r0 = r1.a;
        r2 = 20;
        r0.setMaxEms(r2);
        goto L_0x002d;
    L_0x010f:
        r0 = cn.v6.sixrooms.R.drawable.v6_ic_pop_rank_1;
        r2 = r1.e;
        r4 = 8;
        r2.setVisibility(r4);
        goto L_0x0037;
    L_0x011a:
        r0 = cn.v6.sixrooms.R.drawable.v6_ic_pop_rank_2;
        goto L_0x0037;
    L_0x011e:
        r0 = cn.v6.sixrooms.R.drawable.v6_ic_pop_rank_3;
        goto L_0x0037;
    L_0x0122:
        r5 = "1";
        r0 = r0.equals(r5);
        if (r0 == 0) goto L_0x009f;
    L_0x012a:
        r5 = r3;
        goto L_0x00a0;
    L_0x012d:
        r6 = "2";
        r0 = r0.equals(r6);
        if (r0 == 0) goto L_0x009f;
    L_0x0135:
        goto L_0x00a0;
    L_0x0137:
        r0 = r9.a;
        r0 = r0.getResources();
        r2 = cn.v6.sixrooms.R.drawable.intimate_down;
        r0 = r0.getDrawable(r2);
        goto L_0x00a4;
    L_0x0145:
        r0 = r9.a;
        r0 = r0.getResources();
        r2 = cn.v6.sixrooms.R.drawable.intimate_up;
        r0 = r0.getDrawable(r2);
        goto L_0x00a4;
    L_0x0153:
        r2 = r1.d;
        r2.setText(r0);
        r0 = r1.d;
        r0 = r0.getLayoutParams();
        r2 = cn.v6.sixrooms.utils.DensityUtil.dip2px(r6);
        r0.width = r2;
        r0 = r9.a;
        r0 = r0.isSuperGirlRoom();
        r0 = r0.booleanValue();
        if (r0 == 0) goto L_0x0197;
    L_0x0170:
        r0 = r9.d;
        if (r0 == 0) goto L_0x0189;
    L_0x0174:
        r0 = r9.a;
        r0 = r0.getResources();
        r2 = cn.v6.sixrooms.R.drawable.star_light_icon;
        r0 = r0.getDrawable(r2);
        r2 = r1.a;
        r4 = android.text.TextUtils.TruncateAt.MIDDLE;
        r2.setEllipsize(r4);
        goto L_0x00a4;
    L_0x0189:
        r0 = r9.a;
        r0 = r0.getResources();
        r2 = cn.v6.sixrooms.R.drawable.intimate_icon;
        r0 = r0.getDrawable(r2);
        goto L_0x00a4;
    L_0x0197:
        r0 = r9.a;
        r0 = r0.getResources();
        r2 = cn.v6.sixrooms.R.drawable.v6_ic_pop_rank_coin;
        r0 = r0.getDrawable(r2);
        goto L_0x00a4;
    L_0x01a5:
        if (r11 == 0) goto L_0x0256;
    L_0x01a7:
        r0 = r11.getTag();
        r0 = (cn.v6.sixrooms.room.adapter.FansListOfFullScreenAdapter.FansSecondViewHolder) r0;
        r1 = r0;
    L_0x01ae:
        r0 = r9.a;
        r0 = r0.getResources();
        r0 = r0.getConfiguration();
        r0 = r0.orientation;
        r2 = 2;
        if (r0 != r2) goto L_0x0298;
    L_0x01bd:
        r0 = r1.a;
        r2 = 5;
        r0.setMaxEms(r2);
    L_0x01c3:
        r0 = r1.e;
        r0.setVisibility(r3);
        r0 = r1.c;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r4 = r10 + 1;
        r2 = r2.append(r4);
        r2 = r2.toString();
        r0.setText(r2);
        r0 = r9.b;
        r0 = r0.get(r10);
        r0 = (cn.v6.sixrooms.bean.FansBean) r0;
        r2 = r1.b;
        r4 = r0.getPicuser();
        r4 = android.net.Uri.parse(r4);
        r2.setImageURI(r4);
        r2 = r1.a;
        r4 = r0.getUname();
        r2.setText(r4);
        r2 = r0.getContribution();
        r4 = android.text.TextUtils.isEmpty(r2);
        if (r4 == 0) goto L_0x0332;
    L_0x0204:
        r0 = r0.getMoney();
    L_0x0208:
        r2 = r9.a;
        r2 = r2.getResources();
        r4 = cn.v6.sixrooms.R.drawable.transparent;
        r2 = r2.getDrawable(r4);
        r4 = r9.a;
        r4 = r4.isSuperGirlRoom();
        r4 = r4.booleanValue();
        if (r4 == 0) goto L_0x02e0;
    L_0x0220:
        r4 = r9.c;
        if (r4 == 0) goto L_0x02e0;
    L_0x0224:
        r4 = r1.d;
        r6 = "";
        r4.setText(r6);
        r4 = r1.d;
        r4 = r4.getLayoutParams();
        r6 = cn.v6.sixrooms.utils.DensityUtil.dip2px(r8);
        r4.width = r6;
        r4 = -1;
        r6 = r0.hashCode();
        switch(r6) {
            case 49: goto L_0x02af;
            case 50: goto L_0x02b9;
            default: goto L_0x023f;
        };
    L_0x023f:
        r0 = r4;
    L_0x0240:
        switch(r0) {
            case 0: goto L_0x02c4;
            case 1: goto L_0x02d2;
            default: goto L_0x0243;
        };
    L_0x0243:
        r0 = r2;
    L_0x0244:
        r2 = r0.getMinimumWidth();
        r4 = r0.getMinimumHeight();
        r0.setBounds(r3, r3, r2, r4);
        r1 = r1.d;
        r1.setCompoundDrawables(r0, r7, r7, r7);
        goto L_0x000e;
    L_0x0256:
        r0 = r9.a;
        r1 = cn.v6.sixrooms.R.layout.pop_rank_fans_second_item;
        r11 = android.view.View.inflate(r0, r1, r7);
        r1 = new cn.v6.sixrooms.room.adapter.FansListOfFullScreenAdapter$FansSecondViewHolder;
        r1.<init>();
        r0 = cn.v6.sixrooms.R.id.tv_rank;
        r0 = r11.findViewById(r0);
        r0 = (android.widget.TextView) r0;
        r1.c = r0;
        r0 = cn.v6.sixrooms.R.id.iv_identity;
        r0 = r11.findViewById(r0);
        r0 = (com.facebook.drawee.view.SimpleDraweeView) r0;
        r1.b = r0;
        r0 = cn.v6.sixrooms.R.id.tv_name;
        r0 = r11.findViewById(r0);
        r0 = (android.widget.TextView) r0;
        r1.a = r0;
        r0 = cn.v6.sixrooms.R.id.tv_coin6;
        r0 = r11.findViewById(r0);
        r0 = (android.widget.TextView) r0;
        r1.d = r0;
        r0 = cn.v6.sixrooms.R.id.division;
        r0 = r11.findViewById(r0);
        r1.e = r0;
        r11.setTag(r1);
        goto L_0x01ae;
    L_0x0298:
        r0 = r9.a;
        r0 = r0.getResources();
        r0 = r0.getConfiguration();
        r0 = r0.orientation;
        if (r0 != r5) goto L_0x01c3;
    L_0x02a6:
        r0 = r1.a;
        r2 = 20;
        r0.setMaxEms(r2);
        goto L_0x01c3;
    L_0x02af:
        r5 = "1";
        r0 = r0.equals(r5);
        if (r0 == 0) goto L_0x023f;
    L_0x02b7:
        r0 = r3;
        goto L_0x0240;
    L_0x02b9:
        r6 = "2";
        r0 = r0.equals(r6);
        if (r0 == 0) goto L_0x023f;
    L_0x02c1:
        r0 = r5;
        goto L_0x0240;
    L_0x02c4:
        r0 = r9.a;
        r0 = r0.getResources();
        r2 = cn.v6.sixrooms.R.drawable.intimate_down;
        r0 = r0.getDrawable(r2);
        goto L_0x0244;
    L_0x02d2:
        r0 = r9.a;
        r0 = r0.getResources();
        r2 = cn.v6.sixrooms.R.drawable.intimate_up;
        r0 = r0.getDrawable(r2);
        goto L_0x0244;
    L_0x02e0:
        r2 = r1.d;
        r2.setText(r0);
        r0 = r1.d;
        r0 = r0.getLayoutParams();
        r2 = cn.v6.sixrooms.utils.DensityUtil.dip2px(r6);
        r0.width = r2;
        r0 = r9.a;
        r0 = r0.isSuperGirlRoom();
        r0 = r0.booleanValue();
        if (r0 == 0) goto L_0x0324;
    L_0x02fd:
        r0 = r9.d;
        if (r0 == 0) goto L_0x0316;
    L_0x0301:
        r0 = r9.a;
        r0 = r0.getResources();
        r2 = cn.v6.sixrooms.R.drawable.star_light_icon;
        r0 = r0.getDrawable(r2);
        r2 = r1.a;
        r4 = android.text.TextUtils.TruncateAt.MIDDLE;
        r2.setEllipsize(r4);
        goto L_0x0244;
    L_0x0316:
        r0 = r9.a;
        r0 = r0.getResources();
        r2 = cn.v6.sixrooms.R.drawable.intimate_icon;
        r0 = r0.getDrawable(r2);
        goto L_0x0244;
    L_0x0324:
        r0 = r9.a;
        r0 = r0.getResources();
        r2 = cn.v6.sixrooms.R.drawable.v6_ic_pop_rank_coin;
        r0 = r0.getDrawable(r2);
        goto L_0x0244;
    L_0x0332:
        r0 = r2;
        goto L_0x0208;
    L_0x0335:
        r0 = r2;
        goto L_0x0068;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.v6.sixrooms.room.adapter.FansListOfFullScreenAdapter.getView(int, android.view.View, android.view.ViewGroup):android.view.View");
    }

    public int getItemViewType(int i) {
        if (i <= 2) {
            return 0;
        }
        return 1;
    }

    public int getViewTypeCount() {
        return 2;
    }
}
