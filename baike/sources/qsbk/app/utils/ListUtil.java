package qsbk.app.utils;

import android.widget.ListView;

public class ListUtil {
    public static int getHeaderCount(ListView listView) {
        if (listView == null) {
            return 0;
        }
        return listView.getHeaderViewsCount();
    }

    public static boolean isOnBottom(ListView listView) {
        if (listView == null || listView.getAdapter() == null || listView.getChildCount() == 0) {
            return true;
        }
        if (listView.getLastVisiblePosition() != listView.getAdapter().getCount() - 1 || listView.getChildAt(listView.getChildCount() - 1).getBottom() > listView.getHeight()) {
            return false;
        }
        return true;
    }
}
