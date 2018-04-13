package qsbk.app.live.widget;

import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import java.util.List;
import java.util.Map;
import qsbk.app.core.model.CommonVideo;
import qsbk.app.core.net.Callback;
import qsbk.app.core.net.response.BaseResponse;
import qsbk.app.core.utils.Constants;
import qsbk.app.live.R;
import qsbk.app.live.adapter.LiveRecommendAdapter;

class hj extends Callback {
    final /* synthetic */ Map a;
    final /* synthetic */ List b;
    final /* synthetic */ LiveRecommendAdapter c;
    final /* synthetic */ LivePullEndDialog d;

    hj(LivePullEndDialog livePullEndDialog, Map map, List list, LiveRecommendAdapter liveRecommendAdapter) {
        this.d = livePullEndDialog;
        this.a = map;
        this.b = list;
        this.c = liveRecommendAdapter;
    }

    public Map<String, String> getParams() {
        this.a.put(ParamKey.PAGE, "1");
        this.a.put("type", "re");
        this.a.put("count", "3");
        return this.a;
    }

    public void onSuccess(BaseResponse baseResponse) {
        this.b.clear();
        String str = "feeds";
        if (Constants.APP_FLAG == 1) {
            str = "lives";
        }
        List listResponse = baseResponse.getListResponse(str, new hk(this));
        if (listResponse != null) {
            int size = listResponse.size();
            for (int i = 0; i < size && this.b.size() < 2; i++) {
                CommonVideo commonVideo = (CommonVideo) listResponse.get(i);
                if (!(commonVideo == null || commonVideo.author == null || (this.d.k != null && commonVideo.author.id == this.d.k.id))) {
                    this.b.add(commonVideo);
                }
            }
        }
        if (this.b.size() > 0) {
            this.d.findViewById(R.id.tv_recommend).setVisibility(0);
            this.c.notifyDataSetChanged();
        }
    }

    public void onFailed(int i, String str) {
    }

    public void onFinished() {
    }
}
