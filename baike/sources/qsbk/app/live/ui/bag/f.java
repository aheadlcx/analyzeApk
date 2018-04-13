package qsbk.app.live.ui.bag;

import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import java.util.List;
import java.util.Map;
import qsbk.app.core.net.response.BaseResponse;
import qsbk.app.core.utils.ConfigInfoUtil;
import qsbk.app.core.widget.RefreshRecyclerView.RefreshListener;
import qsbk.app.live.model.LiveBagDataRecord;
import qsbk.app.live.model.LiveBagDataRecordResponse;

class f implements RefreshListener {
    final /* synthetic */ BagFragment a;

    f(BagFragment bagFragment) {
        this.a = bagFragment;
    }

    public List onSuccess(BaseResponse baseResponse) {
        LiveBagDataRecordResponse liveBagDataRecordResponse = (LiveBagDataRecordResponse) baseResponse.getResponse(new g(this));
        if (liveBagDataRecordResponse == null) {
            return null;
        }
        List list = liveBagDataRecordResponse.items;
        Map template = ConfigInfoUtil.instance().getTemplate();
        if (!(list == null || template == null)) {
            for (int i = 0; i < list.size(); i++) {
                LiveBagDataRecord liveBagDataRecord = (LiveBagDataRecord) list.get(i);
                if (!(!template.containsKey(liveBagDataRecord.tp) || liveBagDataRecord.c == null || liveBagDataRecord.c.startsWith("http"))) {
                    liveBagDataRecord.c = ((String) template.get(liveBagDataRecord.tp)).replace("$", liveBagDataRecord.c);
                }
            }
        }
        if (list.size() == 0 && this.a.a.getParams() != null && this.a.a.getParams().get(ParamKey.PAGE).equals("1")) {
            list.add(new LiveBagDataRecord());
        }
        return list;
    }

    public void onFailed(int i, String str) {
    }
}
