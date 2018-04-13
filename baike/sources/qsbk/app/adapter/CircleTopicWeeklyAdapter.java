package qsbk.app.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import java.util.ArrayList;
import qsbk.app.model.CircleArticle;
import qsbk.app.model.CircleTopic;
import qsbk.app.share.ShareUtils$OnCircleShareStartListener;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.BaseCell;
import qsbk.app.widget.qiuyoucircle.BaseUserCell;
import qsbk.app.widget.qiuyoucircle.CircleTopicWeeklyCell;
import qsbk.app.widget.qiuyoucircle.CommentCell;
import qsbk.app.widget.qiuyoucircle.ForwardCell;
import qsbk.app.widget.qiuyoucircle.NormalCell;
import qsbk.app.widget.qiuyoucircle.ShareCell;
import qsbk.app.widget.qiuyoucircle.UnknownCell;

public class CircleTopicWeeklyAdapter extends BaseImageAdapter implements ShareUtils$OnCircleShareStartListener {
    ShareUtils$OnCircleShareStartListener a;

    private enum a {
        UNKNOWN,
        FORWARD,
        NORMAL,
        SHARE,
        TOPIC,
        COMMENT,
        GROUP_RECOMMEND,
        AD_GDT,
        AD_BAIDU,
        AD_QB,
        LIVE_SHARE,
        LIVE_RECOMMEND,
        AD_QH,
        UNSUPPORT
    }

    public CircleTopicWeeklyAdapter(ArrayList arrayList, Activity activity, ShareUtils$OnCircleShareStartListener shareUtils$OnCircleShareStartListener) {
        super(arrayList, activity);
        this.a = shareUtils$OnCircleShareStartListener;
    }

    public int getViewTypeCount() {
        return 7;
    }

    public void onCircleShareStart(CircleArticle circleArticle) {
        if (this.a != null) {
            this.a.onCircleShareStart(circleArticle);
        }
    }

    public void onCircleShareStart(CircleArticle circleArticle, String str, View view) {
        if (this.a != null) {
            this.a.onCircleShareStart(circleArticle, str, view);
        }
    }

    public int getItemViewType(int i) {
        Object item = getItem(i);
        if (item instanceof CircleArticle) {
            CircleArticle circleArticle = (CircleArticle) getItem(i);
            if (circleArticle.isUnSupport()) {
                return a.UNSUPPORT.ordinal();
            }
            if (circleArticle.isForward()) {
                return a.FORWARD.ordinal();
            }
            if (circleArticle.type <= 3 || circleArticle.type == 10) {
                return a.NORMAL.ordinal();
            }
            if (circleArticle.isShare()) {
                return a.SHARE.ordinal();
            }
        } else if (item instanceof CircleTopic) {
            return a.TOPIC.ordinal();
        } else {
            if (item instanceof String) {
                return a.COMMENT.ordinal();
            }
        }
        return a.UNKNOWN.ordinal();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        BaseCell baseCell;
        int itemViewType = getItemViewType(i);
        if (view == null) {
            BaseCell normalCell;
            if (itemViewType == a.NORMAL.ordinal()) {
                normalCell = new NormalCell(this);
            } else if (itemViewType == a.FORWARD.ordinal()) {
                normalCell = new ForwardCell(this);
            } else if (itemViewType == a.SHARE.ordinal()) {
                normalCell = new ShareCell(this);
            } else if (itemViewType == a.TOPIC.ordinal()) {
                normalCell = new CircleTopicWeeklyCell();
            } else if (itemViewType == a.COMMENT.ordinal()) {
                normalCell = new CommentCell();
            } else {
                normalCell = new UnknownCell();
            }
            normalCell.performCreate(i, viewGroup, getItem(i));
            view = normalCell.getCellView();
            view.setTag(normalCell);
            baseCell = normalCell;
        } else {
            baseCell = (BaseCell) view.getTag();
        }
        baseCell.performUpdate(i, viewGroup, getItem(i));
        if (baseCell instanceof BaseUserCell) {
            ((BaseUserCell) baseCell).divider.setVisibility(8);
            if (i > 0 && (getItem(i - 1) instanceof CircleArticle)) {
                ((BaseUserCell) baseCell).divider.setVisibility(0);
                LayoutParams layoutParams = ((BaseUserCell) baseCell).divider.getLayoutParams();
                if (layoutParams == null) {
                    layoutParams = new LayoutParams(-1, UIHelper.dip2px(this.k, 1.0f));
                } else {
                    layoutParams.height = UIHelper.dip2px(this.k, 1.0f);
                }
                ((BaseUserCell) baseCell).divider.setLayoutParams(layoutParams);
            }
        }
        view.setBackgroundColor(UIHelper.isNightTheme() ? Color.parseColor("#1e1e23") : Color.parseColor("#ffffff"));
        return view;
    }
}
