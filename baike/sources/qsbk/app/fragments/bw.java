package qsbk.app.fragments;

import android.view.View;
import android.widget.ListView;
import com.qq.e.ads.nativ.MediaView;
import com.qq.e.ads.nativ.NativeMediaADData;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.utils.TupleTwo;
import qsbk.app.video.VideoInListHelper;
import qsbk.app.widget.qiuyoucircle.BaseAdCell.GDTAdCell;
import qsbk.app.widget.qiuyoucircle.NormalCell;

class bw extends VideoInListHelper {
    final /* synthetic */ CircleVideoFragment a;

    bw(CircleVideoFragment circleVideoFragment, ListView listView) {
        this.a = circleVideoFragment;
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
        if (tag == null || !(tag instanceof GDTAdCell)) {
            return null;
        }
        GDTAdCell gDTAdCell = (GDTAdCell) tag;
        return new TupleTwo(gDTAdCell.getMediaView(), gDTAdCell.getRef());
    }
}
