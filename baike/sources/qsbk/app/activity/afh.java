package qsbk.app.activity;

import android.view.View;
import android.widget.ListView;
import com.qq.e.ads.nativ.MediaView;
import com.qq.e.ads.nativ.NativeMediaADData;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.adapter.VideoImmersionAdapter.GdtVideoImmersionCell;
import qsbk.app.adapter.VideoImmersionAdapter.VideoImmersionCell;
import qsbk.app.utils.TupleTwo;
import qsbk.app.video.CircleVideoPlayerView;
import qsbk.app.video.VideoInListHelper;
import qsbk.app.video.VideoPlayerView;
import qsbk.app.widget.CircleVideoCell;

class afh extends VideoInListHelper {
    final /* synthetic */ VideoImmersionActivity a;

    afh(VideoImmersionActivity videoImmersionActivity, ListView listView) {
        this.a = videoImmersionActivity;
        super(listView);
    }

    public Map<String, Object> getVideoFromItemView(View view) {
        HashMap hashMap = new HashMap();
        Object tag = view.getTag();
        if (tag != null && (tag instanceof VideoImmersionCell)) {
            VideoImmersionCell videoImmersionCell = (VideoImmersionCell) tag;
            hashMap.put(VideoInListHelper.VIEW, videoImmersionCell.player);
            hashMap.put(VideoInListHelper.TAG, Integer.valueOf(0));
            hashMap.put("article", videoImmersionCell.article);
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
        if (tag == null || !(tag instanceof GdtVideoImmersionCell)) {
            return null;
        }
        GdtVideoImmersionCell gdtVideoImmersionCell = (GdtVideoImmersionCell) tag;
        return new TupleTwo(gdtVideoImmersionCell.getMediaView(), gdtVideoImmersionCell.getRef());
    }

    public void onItemFound(View view, VideoPlayerView videoPlayerView, boolean z) {
        super.onItemFound(view, videoPlayerView, z);
        if (videoPlayerView != null) {
            ((CircleVideoPlayerView) videoPlayerView).showControlBar(false, true);
        }
    }
}
