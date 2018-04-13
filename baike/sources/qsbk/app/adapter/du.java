package qsbk.app.adapter;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.qq.e.comm.constants.ErrorCode$OtherError;
import qsbk.app.QsbkApp;
import qsbk.app.ad.feedsad.FeedsAdStat;
import qsbk.app.ad.feedsad.FeedsAdUtils;
import qsbk.app.adapter.VideoImmersionAdapter.GdtVideoImmersionCell;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.ReportAdForMedalUtil;
import qsbk.app.utils.ReportAdForMedalUtil.AD_PROVIDER;
import qsbk.app.utils.ReportAdForMedalUtil.AD_TYPE;
import qsbk.app.utils.ToastAndDialog;

class du implements OnClickListener {
    final /* synthetic */ GdtVideoImmersionCell a;

    du(GdtVideoImmersionCell gdtVideoImmersionCell) {
        this.a = gdtVideoImmersionCell;
    }

    public void onClick(View view) {
        if (this.a.v == null || !this.a.v.isVideoAD() || !this.a.v.isVideoLoaded() || this.a.v.isPlaying()) {
            int aPPStatus = this.a.v.getAPPStatus();
            if (aPPStatus == 1 || aPPStatus == 4 || aPPStatus == 8) {
                this.a.v.onClicked(this.a.getCellView());
                FeedsAdStat.onClick(view.getContext(), "gdt_native");
                ReportAdForMedalUtil.report(AD_PROVIDER.GDT, AD_TYPE.QIUSHILIST);
                return;
            }
            Context context = view.getContext();
            String network = HttpUtils.getNetwork(context);
            if (FeedsAdUtils.needShowConfirm(network)) {
                new Builder(context).setTitle("温馨提示").setCancelable(true).setMessage("当前为" + network + "网络，开始下载应用？").setNegativeButton("取消", new dw(this)).setPositiveButton("确认", new dv(this, context)).create().show();
                return;
            }
            Object optString = QsbkApp.indexConfig.optString("ad_click_toast");
            if (!TextUtils.isEmpty(optString)) {
                ToastAndDialog.makeNeutralToast(QsbkApp.mContext, optString, Integer.valueOf(0)).show();
            }
            this.a.v.onClicked(this.a.getCellView());
            FeedsAdStat.onClick(view.getContext(), "gdt_native");
            ReportAdForMedalUtil.report(AD_PROVIDER.GDT, AD_TYPE.QIUSHILIST);
            return;
        }
        this.a.p.l.smoothScrollToPositionFromTop(this.a.q + this.a.p.l.getHeaderViewsCount(), this.a.p.e, ErrorCode$OtherError.CONTENT_FORCE_EXPOSURE);
    }
}
