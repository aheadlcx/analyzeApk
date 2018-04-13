package cn.v6.sixrooms.ui.phone;

import android.text.TextUtils;
import android.util.SparseArray;
import cn.v6.sixrooms.bean.BillBean;
import cn.v6.sixrooms.engine.BillEngine.OnCallBack;
import cn.v6.sixrooms.ui.phone.BillManagerActivity.Popup;
import java.util.List;

final class i implements OnCallBack {
    final /* synthetic */ BillManagerActivity a;

    i(BillManagerActivity billManagerActivity) {
        this.a = billManagerActivity;
    }

    public final void success(SparseArray<String> sparseArray, List<BillBean> list, int i) {
        BillManagerActivity.a(this.a);
        String str = (String) sparseArray.get(0);
        if (this.a.v && !TextUtils.isEmpty(str)) {
            this.a.q = str;
            this.a.i = this.a.a(Popup.Calendar);
            if (this.a.k != null) {
                this.a.k.setList(this.a.i);
                this.a.k.notifyDataSetChanged();
            }
            this.a.v = false;
        }
        if (list.size() <= 0) {
            this.a.a.setVisibility(0);
            if (sparseArray == null || TextUtils.isEmpty(str)) {
                this.a.d.setVisibility(8);
            } else {
                this.a.d.setVisibility(0);
                this.a.b.setText(this.a.n.getWeekTime(str, "yyyy年MM月dd日"));
            }
        } else {
            this.a.d.setVisibility(8);
        }
        this.a.s = sparseArray;
        if (this.a.u) {
            this.a.u = false;
            this.a.t.addAll(list);
        } else {
            this.a.t = list;
        }
        this.a.n.setListGiftBill(this.a.t, i, sparseArray);
        this.a.n.notifyDataSetChanged();
        if (BillManagerActivity.l(this.a)) {
            this.a.o.isBanPullUpRefresh(false);
        } else {
            this.a.o.isBanPullUpRefresh(true);
        }
    }

    public final void handleErrorInfo(String str, String str2) {
        BillManagerActivity.a(this.a);
        this.a.handleErrorResult(str, str2);
    }

    public final void error(int i) {
        BillManagerActivity.a(this.a);
        this.a.showErrorToast(i);
    }
}
