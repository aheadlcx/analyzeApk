package qsbk.app.widget.qbnews.ad;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.view.View;
import com.qq.e.ads.nativ.NativeMediaADData;
import qsbk.app.ad.feedsad.FeedsAdStat;
import qsbk.app.ad.feedsad.FeedsAdUtils;
import qsbk.app.ad.feedsad.gdtad.GdtAdItemData;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.ReportAdForMedalUtil;
import qsbk.app.utils.ReportAdForMedalUtil.AD_PROVIDER;
import qsbk.app.utils.ReportAdForMedalUtil.AD_TYPE;

public class GdtNewsAdCell extends BaseNewsAdCell {
    public GdtNewsAdCell(NewsAdSign newsAdSign) {
        a(newsAdSign);
    }

    public void onUpdate() {
        NativeMediaADData ref = ((GdtAdItemData) getItem()).getView().getRef();
        this.e.setText(ref.getDesc());
        displayImage(this.f, ref.getImgUrl(), this.s);
        this.g.setText("腾讯广点通");
    }

    public void onClick() {
        super.onClick();
        NativeMediaADData ref = ((GdtAdItemData) getItem()).getView().getRef();
        int aPPStatus = ref.getAPPStatus();
        View cellView = getCellView();
        if (aPPStatus == 1 || aPPStatus == 4 || aPPStatus == 8) {
            ref.onClicked(cellView);
            FeedsAdStat.onClick(cellView.getContext(), "gdt_native");
            ReportAdForMedalUtil.report(AD_PROVIDER.GDT, AD_TYPE.QIUWEN);
            return;
        }
        Context context = cellView.getContext();
        String network = HttpUtils.getNetwork(context);
        if (FeedsAdUtils.needShowConfirm(network)) {
            new Builder(context).setTitle("温馨提示").setCancelable(true).setMessage("当前为" + network + "网络，开始下载应用？").setNegativeButton("取消", new d(this)).setPositiveButton("确认", new c(this, ref, cellView, context)).create().show();
            return;
        }
        ref.onClicked(cellView);
        FeedsAdStat.onClick(cellView.getContext(), "gdt_native");
        ReportAdForMedalUtil.report(AD_PROVIDER.GDT, AD_TYPE.QIUWEN);
    }
}
