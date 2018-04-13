package qsbk.app.adapter;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import qsbk.app.ad.feedsad.baiduad.BaiduAdItemData;
import qsbk.app.ad.feedsad.gdtad.GdtAdItemData;
import qsbk.app.ad.feedsad.qbad.QbAdItem;
import qsbk.app.ad.feedsad.qhad.QhAdItemData;
import qsbk.app.model.CircleArticle;
import qsbk.app.model.CircleTopicPackage;
import qsbk.app.model.FollowWelcomCard;
import qsbk.app.model.GroupRecommend;
import qsbk.app.model.LivePackage;
import qsbk.app.share.ShareUtils$OnCircleShareStartListener;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.BaseCell;
import qsbk.app.widget.GroupRecommendQiushiCell;
import qsbk.app.widget.LiveRecommendCell;
import qsbk.app.widget.TopicCell;
import qsbk.app.widget.qiuyoucircle.BaseAdCell.BaiduAdCell;
import qsbk.app.widget.qiuyoucircle.BaseAdCell.GDTAdCell;
import qsbk.app.widget.qiuyoucircle.BaseAdCell.QHAdCell;
import qsbk.app.widget.qiuyoucircle.BaseAdCell.QbAdCell;
import qsbk.app.widget.qiuyoucircle.FollowWelcomeCell;
import qsbk.app.widget.qiuyoucircle.ForwardCell;
import qsbk.app.widget.qiuyoucircle.NormalCell;
import qsbk.app.widget.qiuyoucircle.ShareCell;
import qsbk.app.widget.qiuyoucircle.UnknownCell;
import qsbk.app.widget.qiuyoucircle.UnsupportCell;
import qsbk.app.widget.qiuyoucircle.WebAdCell;

public class QiuYouCircleAdapter extends BaseImageAdapter implements ShareUtils$OnCircleShareStartListener {
    private boolean a = false;
    private String b;
    private ShareUtils$OnCircleShareStartListener c;

    public enum ItemType {
        UNKNOWN,
        FORWARD,
        NORMAL,
        SHARE,
        TOPICS,
        GROUP_RECOMMEND,
        AD_GDT,
        AD_BAIDU,
        AD_QB,
        AD_CELL,
        LIVE_SHARE,
        LIVE_RECOMMEND,
        AD_QH,
        FOLLOW_WELCOME,
        UNSUPPORT
    }

    public QiuYouCircleAdapter(ArrayList<Object> arrayList, Activity activity, ShareUtils$OnCircleShareStartListener shareUtils$OnCircleShareStartListener) {
        super(arrayList, activity);
        this.c = shareUtils$OnCircleShareStartListener;
    }

    public QiuYouCircleAdapter(ArrayList<Object> arrayList, Activity activity, ShareUtils$OnCircleShareStartListener shareUtils$OnCircleShareStartListener, boolean z) {
        super(arrayList, activity);
        this.a = z;
        this.c = shareUtils$OnCircleShareStartListener;
    }

    public QiuYouCircleAdapter(ArrayList<Object> arrayList, Activity activity, ShareUtils$OnCircleShareStartListener shareUtils$OnCircleShareStartListener, String str) {
        super(arrayList, activity);
        this.c = shareUtils$OnCircleShareStartListener;
        this.b = str;
    }

    public void onCircleShareStart(CircleArticle circleArticle) {
        this.c.onCircleShareStart(circleArticle);
    }

    public void onCircleShareStart(CircleArticle circleArticle, String str, View view) {
        this.c.onCircleShareStart(circleArticle, str, view);
    }

    protected Drawable d() {
        return this.k.getResources().getDrawable(UIHelper.getShare2IMIcon());
    }

    public int getViewTypeCount() {
        return ItemType.values().length;
    }

    public int getItemViewType(int i) {
        Object item = getItem(i);
        if (item instanceof CircleArticle) {
            CircleArticle circleArticle = (CircleArticle) getItem(i);
            if (circleArticle.isUnSupport()) {
                return ItemType.UNSUPPORT.ordinal();
            }
            if (circleArticle.isForward()) {
                return ItemType.FORWARD.ordinal();
            }
            if (circleArticle.type <= 3 || circleArticle.type == 10) {
                return ItemType.NORMAL.ordinal();
            }
            if (circleArticle.isShare()) {
                return ItemType.SHARE.ordinal();
            }
            if (circleArticle.isAd()) {
                return ItemType.AD_CELL.ordinal();
            }
        } else if (item instanceof GdtAdItemData) {
            return ItemType.AD_GDT.ordinal();
        } else {
            if (item instanceof GroupRecommend) {
                return ItemType.GROUP_RECOMMEND.ordinal();
            }
            if (item instanceof CircleTopicPackage) {
                return ItemType.TOPICS.ordinal();
            }
            if (item instanceof BaiduAdItemData) {
                return ItemType.AD_BAIDU.ordinal();
            }
            if (item instanceof QbAdItem) {
                return ItemType.AD_QB.ordinal();
            }
            if (item instanceof LivePackage) {
                return ItemType.LIVE_RECOMMEND.ordinal();
            }
            if (item instanceof QhAdItemData) {
                return ItemType.AD_QH.ordinal();
            }
            if (item instanceof FollowWelcomCard) {
                return ItemType.FOLLOW_WELCOME.ordinal();
            }
        }
        return ItemType.UNKNOWN.ordinal();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        BaseCell unsupportCell;
        if (view == null) {
            int itemViewType = getItemViewType(i);
            if (itemViewType == ItemType.UNSUPPORT.ordinal()) {
                unsupportCell = new UnsupportCell();
            } else if (itemViewType == ItemType.NORMAL.ordinal()) {
                unsupportCell = new NormalCell(this, isFromCircleTopic());
            } else if (itemViewType == ItemType.FORWARD.ordinal()) {
                unsupportCell = new ForwardCell(this, isFromCircleTopic());
            } else if (itemViewType == ItemType.SHARE.ordinal()) {
                unsupportCell = new ShareCell(this, isFromCircleTopic());
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
            } else if (itemViewType == ItemType.AD_CELL.ordinal()) {
                unsupportCell = new WebAdCell(this, isFromCircleTopic());
            } else if (itemViewType == ItemType.FOLLOW_WELCOME.ordinal()) {
                unsupportCell = new FollowWelcomeCell();
            } else {
                unsupportCell = new UnknownCell();
            }
            unsupportCell.performCreate(i, viewGroup, getItem(i));
            view = unsupportCell.getCellView();
            view.setOnClickListener(new dd(this, unsupportCell));
            view.setTag(unsupportCell);
        } else {
            unsupportCell = (BaseCell) view.getTag();
        }
        unsupportCell.performUpdate(i, viewGroup, getItem(i));
        return view;
    }

    public boolean isFromCircleTopic() {
        return this.a;
    }

    public String getScenario() {
        return this.b;
    }
}
