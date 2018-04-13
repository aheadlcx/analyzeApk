package qsbk.app.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import qsbk.app.adapter.QiuYouCircleAdapter.ItemType;
import qsbk.app.share.ShareUtils$OnCircleShareStartListener;
import qsbk.app.widget.BaseCell;
import qsbk.app.widget.GroupRecommendQiushiCell;
import qsbk.app.widget.LiveRecommendCell;
import qsbk.app.widget.TopicCell;
import qsbk.app.widget.qiuyoucircle.BaseAdCell.BaiduAdCell;
import qsbk.app.widget.qiuyoucircle.BaseAdCell.GDTAdCell;
import qsbk.app.widget.qiuyoucircle.BaseAdCell.QHAdCell;
import qsbk.app.widget.qiuyoucircle.BaseAdCell.QbAdCell;
import qsbk.app.widget.qiuyoucircle.UnknownCell;
import qsbk.app.widget.qiuyoucircle.UnsupportCell;

public class CircleHotCommentAdapter extends QiuYouCircleAdapter {
    public CircleHotCommentAdapter(ArrayList<Object> arrayList, Activity activity, ShareUtils$OnCircleShareStartListener shareUtils$OnCircleShareStartListener) {
        super(arrayList, activity, shareUtils$OnCircleShareStartListener);
    }

    public CircleHotCommentAdapter(ArrayList<Object> arrayList, Activity activity, ShareUtils$OnCircleShareStartListener shareUtils$OnCircleShareStartListener, boolean z) {
        super((ArrayList) arrayList, activity, shareUtils$OnCircleShareStartListener, z);
    }

    public CircleHotCommentAdapter(ArrayList<Object> arrayList, Activity activity, ShareUtils$OnCircleShareStartListener shareUtils$OnCircleShareStartListener, String str) {
        super((ArrayList) arrayList, activity, shareUtils$OnCircleShareStartListener, str);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        BaseCell unsupportCell;
        if (view == null) {
            int itemViewType = getItemViewType(i);
            if (itemViewType == ItemType.UNSUPPORT.ordinal()) {
                unsupportCell = new UnsupportCell();
            } else if (itemViewType == ItemType.NORMAL.ordinal()) {
                unsupportCell = new au(this, this, isFromCircleTopic());
            } else if (itemViewType == ItemType.FORWARD.ordinal()) {
                unsupportCell = new aw(this, this, isFromCircleTopic());
            } else if (itemViewType == ItemType.SHARE.ordinal()) {
                unsupportCell = new ay(this, this, isFromCircleTopic());
            } else if (itemViewType == ItemType.TOPICS.ordinal()) {
                unsupportCell = new TopicCell(false);
            } else if (itemViewType == ItemType.AD_GDT.ordinal()) {
                unsupportCell = new GDTAdCell();
            } else if (itemViewType == ItemType.GROUP_RECOMMEND.ordinal()) {
                unsupportCell = new GroupRecommendQiushiCell();
            } else if (itemViewType == ItemType.AD_BAIDU.ordinal()) {
                unsupportCell = new BaiduAdCell();
            } else if (itemViewType == ItemType.AD_QB.ordinal()) {
                unsupportCell = new QbAdCell();
            } else if (itemViewType == ItemType.LIVE_RECOMMEND.ordinal()) {
                unsupportCell = new LiveRecommendCell(getScenario(), false);
            } else if (itemViewType == ItemType.AD_QH.ordinal()) {
                unsupportCell = new QHAdCell();
            } else {
                unsupportCell = new UnknownCell();
            }
            unsupportCell.performCreate(i, viewGroup, getItem(i));
            view = unsupportCell.getCellView();
            view.setOnClickListener(new ba(this, unsupportCell));
            view.setTag(unsupportCell);
        } else {
            unsupportCell = (BaseCell) view.getTag();
        }
        unsupportCell.performUpdate(i, viewGroup, getItem(i));
        return view;
    }
}
