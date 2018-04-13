package qsbk.app.fragments;

import android.view.View;
import android.widget.ListView;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.adapter.ManageMyContentsAdapter.ViewHolder;
import qsbk.app.video.VideoInListHelper;

class gb extends VideoInListHelper {
    final /* synthetic */ MyHighLightQiushiFragment a;

    gb(MyHighLightQiushiFragment myHighLightQiushiFragment, ListView listView) {
        this.a = myHighLightQiushiFragment;
        super(listView);
    }

    public Map<String, Object> getVideoFromItemView(View view) {
        HashMap hashMap = new HashMap();
        Object tag = view.getTag();
        if (tag == null || !(tag instanceof ViewHolder)) {
            return hashMap;
        }
        ViewHolder viewHolder = (ViewHolder) tag;
        hashMap.put(VideoInListHelper.VIEW, viewHolder.player);
        hashMap.put(VideoInListHelper.TAG, Integer.valueOf(0));
        hashMap.put("article", viewHolder.article);
        return hashMap;
    }
}
