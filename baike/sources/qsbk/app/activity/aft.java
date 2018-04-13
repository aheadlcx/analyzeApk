package qsbk.app.activity;

import android.view.View;
import android.widget.ListView;
import com.qq.e.ads.nativ.MediaView;
import com.qq.e.ads.nativ.NativeMediaADData;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.adapter.VideoImmersionCircleAdapter.GdtVideoImmersionCell;
import qsbk.app.utils.TupleTwo;
import qsbk.app.video.CircleVideoPlayerView;
import qsbk.app.video.VideoInListHelper;
import qsbk.app.video.VideoPlayerView;
import qsbk.app.widget.qiuyoucircle.NormalCell;

class aft extends VideoInListHelper {
    final /* synthetic */ VideoImmersionCircleActivity a;

    aft(VideoImmersionCircleActivity videoImmersionCircleActivity, ListView listView) {
        this.a = videoImmersionCircleActivity;
        super(listView);
    }

    public Map<String, Object> getVideoFromItemView(View view) {
        Map hashMap = new HashMap();
        Object tag = view.getTag();
        if (tag != null && (tag instanceof NormalCell)) {
            NormalCell normalCell = (NormalCell) tag;
            hashMap.put(VideoInListHelper.VIEW, normalCell.playerView);
            hashMap.put(VideoInListHelper.TAG, Integer.valueOf(1));
            hashMap.put("article", normalCell.circleArticle);
        }
        return hashMap;
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
