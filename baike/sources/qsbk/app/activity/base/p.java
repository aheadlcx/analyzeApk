package qsbk.app.activity.base;

import android.view.View;
import android.widget.ListView;
import com.qq.e.ads.nativ.MediaView;
import com.qq.e.ads.nativ.NativeMediaADData;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.ad.feedsad.gdtad.GdtAdView;
import qsbk.app.adapter.ArticleAdapter.ViewHolder;
import qsbk.app.utils.TupleTwo;
import qsbk.app.video.VideoInListHelper;
import qsbk.app.widget.CircleVideoCell;

class p extends VideoInListHelper {
    final /* synthetic */ BaseArticleListViewFragment a;

    p(BaseArticleListViewFragment baseArticleListViewFragment, ListView listView) {
        this.a = baseArticleListViewFragment;
        super(listView);
    }

    public Map<String, Object> getVideoFromItemView(View view) {
        HashMap hashMap = new HashMap();
        Object tag = view.getTag();
        if (tag != null && (tag instanceof ViewHolder)) {
            ViewHolder viewHolder = (ViewHolder) tag;
            hashMap.put(VideoInListHelper.VIEW, viewHolder.videoPlayer);
            hashMap.put(VideoInListHelper.TAG, Integer.valueOf(0));
            hashMap.put("article", viewHolder.article);
            return hashMap;
        } else if (tag == null || !(tag instanceof CircleVideoCell)) {
            return hashMap;
        } else {
            CircleVideoCell circleVideoCell = (CircleVideoCell) tag;
            hashMap.put(VideoInListHelper.VIEW, circleVideoCell.player);
            hashMap.put(VideoInListHelper.TAG, Integer.valueOf(1));
            hashMap.put("article", circleVideoCell.article);
            return hashMap;
        }
    }

    public TupleTwo<MediaView, NativeMediaADData> getGdtVideoFromItemView(View view) {
        Object tag = view.getTag();
        if (tag == null || !(tag instanceof GdtAdView.ViewHolder)) {
            return null;
        }
        GdtAdView.ViewHolder viewHolder = (GdtAdView.ViewHolder) tag;
        return new TupleTwo(viewHolder.getMediaView(), viewHolder.getRef());
    }
}
