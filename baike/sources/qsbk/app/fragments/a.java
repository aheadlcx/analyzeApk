package qsbk.app.fragments;

import android.view.View;
import android.widget.ListView;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.adapter.ArticleAdapter.ViewHolder;
import qsbk.app.video.VideoInListHelper;
import qsbk.app.widget.CircleVideoCell;

class a extends VideoInListHelper {
    final /* synthetic */ AcrossFragment a;

    a(AcrossFragment acrossFragment, ListView listView) {
        this.a = acrossFragment;
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
}
