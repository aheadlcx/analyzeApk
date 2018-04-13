package qsbk.app.live.ui.bag;

import java.util.List;
import java.util.Map;
import qsbk.app.core.net.response.BaseResponse;
import qsbk.app.core.utils.ConfigInfoUtil;
import qsbk.app.core.widget.RefreshRecyclerView.RefreshListener;
import qsbk.app.live.model.LiveMarketDataRecord;
import qsbk.app.live.model.LiveMarketDataRecordResponse;

class q implements RefreshListener {
    final /* synthetic */ n a;

    q(n nVar) {
        this.a = nVar;
    }

    public List onSuccess(BaseResponse baseResponse) {
        ConfigInfoUtil.instance().setUpdateConfigCallback(null);
        LiveMarketDataRecordResponse liveMarketDataRecordResponse = (LiveMarketDataRecordResponse) baseResponse.getResponse(new r(this));
        if (liveMarketDataRecordResponse == null) {
            return null;
        }
        List list = liveMarketDataRecordResponse.items;
        Map template = ConfigInfoUtil.instance().getTemplate();
        if (!(list == null || template == null)) {
            for (int i = 0; i < list.size(); i++) {
                LiveMarketDataRecord liveMarketDataRecord = (LiveMarketDataRecord) list.get(i);
                if (!(!template.containsKey(liveMarketDataRecord.t) || liveMarketDataRecord.c == null || liveMarketDataRecord.c.startsWith("http"))) {
                    liveMarketDataRecord.c = ((String) template.get(liveMarketDataRecord.t)).replace("$", liveMarketDataRecord.c);
                }
            }
        }
        return list;
    }

    public void onFailed(int i, String str) {
    }
}
