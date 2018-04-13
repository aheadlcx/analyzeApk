package qsbk.app.live.widget;

import java.util.List;
import java.util.Map;
import qsbk.app.core.net.response.BaseResponse;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.ConfigInfoUtil;
import qsbk.app.core.widget.RefreshRecyclerView.RefreshListener;
import qsbk.app.live.R;
import qsbk.app.live.model.LiveRedEnvelopesRecord;
import qsbk.app.live.model.LiveRedEnvelopesRecordResponse;
import qsbk.app.live.model.LiveUser;

class if implements RefreshListener {
    final /* synthetic */ RedEnvelopesResultDialog a;

    if(RedEnvelopesResultDialog redEnvelopesResultDialog) {
        this.a = redEnvelopesResultDialog;
    }

    public List onSuccess(BaseResponse baseResponse) {
        LiveRedEnvelopesRecordResponse liveRedEnvelopesRecordResponse = (LiveRedEnvelopesRecordResponse) baseResponse.getResponse(new ig(this));
        if (liveRedEnvelopesRecordResponse == null) {
            return null;
        }
        int i;
        List list = liveRedEnvelopesRecordResponse.items;
        Map template = ConfigInfoUtil.instance().getTemplate();
        if (!(list == null || template == null)) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                LiveUser liveUser = ((LiveRedEnvelopesRecord) list.get(i2)).user;
                if (!(!template.containsKey(liveUser.t) || liveUser.av == null || liveUser.av.startsWith("http"))) {
                    liveUser.av = ((String) template.get(liveUser.t)).replace("$", liveUser.av);
                }
            }
        }
        int i3 = liveRedEnvelopesRecordResponse.info != null ? liveRedEnvelopesRecordResponse.info.foldCount : 0;
        if (liveRedEnvelopesRecordResponse.info != null) {
            i = liveRedEnvelopesRecordResponse.info.count;
        } else {
            i = 0;
        }
        this.a.k.setText(AppUtils.getInstance().getAppContext().getString(R.string.live_red_envelopes_size, new Object[]{String.valueOf(i3), String.valueOf(i)}));
        return list;
    }

    public void onFailed(int i, String str) {
    }
}
