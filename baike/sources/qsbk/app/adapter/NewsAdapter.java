package qsbk.app.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.ArrayList;
import qsbk.app.R;
import qsbk.app.ad.feedsad.baiduad.BaiduAdItemData;
import qsbk.app.ad.feedsad.gdtad.GdtAdItemData;
import qsbk.app.ad.feedsad.qbad.QbAdItem;
import qsbk.app.ad.feedsad.qhad.QhAdItemData;
import qsbk.app.model.News;
import qsbk.app.model.NewsEmpty;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.BaseCell;
import qsbk.app.widget.qbnews.ad.BaiduNewsAdCell;
import qsbk.app.widget.qbnews.ad.GdtNewsAdCell;
import qsbk.app.widget.qbnews.ad.NewsAdSign;
import qsbk.app.widget.qbnews.ad.QbNewsAdCell;
import qsbk.app.widget.qbnews.ad.QhNewsAdCell;
import qsbk.app.widget.qbnews.news.HotNewsCell;
import qsbk.app.widget.qbnews.news.OneImageNewsCell;
import qsbk.app.widget.qbnews.news.TextNewsCell;
import qsbk.app.widget.qbnews.news.ThreeImageNewsCell;

@Deprecated
public class NewsAdapter extends BaseImageAdapter {
    public static final int NEWS_360_AD_TYPE = 7;
    public static final int NEWS_BD_AD_TYPE = 6;
    public static final int NEWS_EMPTY_VIEW = 8;
    public static final int NEWS_GDT_AD_TYPE = 5;
    public static final int NEWS_HOT_TYPE = 0;
    public static final int NEWS_ONE_IMAGE_TYPE = 2;
    public static final int NEWS_QB_AD_TYPE = 4;
    public static final int NEWS_TEXT_TYPE = 1;
    public static final int NEWS_THREE_IMAGE_TYPE = 3;
    public static final int NEWS_VIEW_COUNT = 9;
    private static NewsAdSign a = NewsAdSign.FIRST;

    public NewsAdapter(Activity activity, ListView listView, ArrayList<Object> arrayList) {
        super(arrayList, activity);
        this.l = listView;
    }

    public int getCount() {
        return super.getCount();
    }

    public int getViewTypeCount() {
        return 9;
    }

    public int getItemViewType(int i) {
        Object item = getItem(i);
        if (item instanceof News) {
            News news = (News) item;
            if (news.isHotNews()) {
                return 0;
            }
            if (news.isThreeImageNews()) {
                return 3;
            }
            if (news.isOneImageNews()) {
                return 2;
            }
            return 1;
        } else if (item instanceof QbAdItem) {
            return 4;
        } else {
            if (item instanceof GdtAdItemData) {
                return 5;
            }
            if (item instanceof BaiduAdItemData) {
                return 6;
            }
            if (item instanceof QhAdItemData) {
                return 7;
            }
            if (item instanceof NewsEmpty) {
                return 8;
            }
            return super.getItemViewType(i);
        }
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        BaseCell baseCell;
        View view2;
        BaseCell hotNewsCell;
        switch (getItemViewType(i)) {
            case 0:
                if (view != null && (view.getTag() instanceof HotNewsCell)) {
                    baseCell = (BaseCell) view.getTag();
                    view2 = view;
                    break;
                }
                hotNewsCell = new HotNewsCell();
                hotNewsCell.performCreate(i, viewGroup, getItem(i));
                baseCell = hotNewsCell;
                view2 = view;
                break;
                break;
            case 1:
                if (view != null && (view.getTag() instanceof TextNewsCell)) {
                    baseCell = (BaseCell) view.getTag();
                    view2 = view;
                    break;
                }
                hotNewsCell = new TextNewsCell();
                hotNewsCell.performCreate(i, viewGroup, getItem(i));
                baseCell = hotNewsCell;
                view2 = view;
                break;
                break;
            case 2:
                if (view != null && (view.getTag() instanceof OneImageNewsCell)) {
                    baseCell = (BaseCell) view.getTag();
                    view2 = view;
                    break;
                }
                hotNewsCell = new OneImageNewsCell();
                hotNewsCell.performCreate(i, viewGroup, getItem(i));
                baseCell = hotNewsCell;
                view2 = view;
                break;
                break;
            case 3:
                if (view != null && (view.getTag() instanceof ThreeImageNewsCell)) {
                    baseCell = (BaseCell) view.getTag();
                    view2 = view;
                    break;
                }
                hotNewsCell = new ThreeImageNewsCell();
                hotNewsCell.performCreate(i, viewGroup, getItem(i));
                baseCell = hotNewsCell;
                view2 = view;
                break;
            case 4:
                baseCell = new QbNewsAdCell(a);
                baseCell.performCreate(i, viewGroup, getItem(i));
                a = a == NewsAdSign.FIRST ? NewsAdSign.SECOND : NewsAdSign.FIRST;
                view2 = view;
                break;
            case 5:
                baseCell = new GdtNewsAdCell(a);
                baseCell.performCreate(i, viewGroup, getItem(i));
                a = a == NewsAdSign.FIRST ? NewsAdSign.SECOND : NewsAdSign.FIRST;
                view2 = view;
                break;
            case 6:
                baseCell = new BaiduNewsAdCell(a);
                baseCell.performCreate(i, viewGroup, getItem(i));
                a = a == NewsAdSign.FIRST ? NewsAdSign.SECOND : NewsAdSign.FIRST;
                view2 = view;
                break;
            case 7:
                baseCell = new QhNewsAdCell(a);
                baseCell.performCreate(i, viewGroup, getItem(i));
                a = a == NewsAdSign.FIRST ? NewsAdSign.SECOND : NewsAdSign.FIRST;
                view2 = view;
                break;
            case 8:
                if (view != null) {
                    baseCell = null;
                    view2 = view;
                    break;
                }
                baseCell = null;
                view2 = this.n.inflate(R.layout.layout_news_empty, viewGroup, false);
                break;
            default:
                baseCell = new NewsAdapter$a(null);
                view2 = view;
                break;
        }
        if (baseCell != null) {
            view2 = baseCell.getCellView();
            view2.setTag(baseCell);
            view2.setOnClickListener(new cl(this, baseCell));
            baseCell.performUpdate(i, viewGroup, getItem(i));
        }
        if (this.k != null) {
            if (UIHelper.isNightTheme()) {
                view2.setBackgroundColor(this.k.getResources().getColor(R.color.transparent));
            } else {
                view2.setBackgroundResource(R.drawable.items_card);
            }
        }
        return view2;
    }
}
