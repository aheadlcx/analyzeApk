package qsbk.app.utils;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import qsbk.app.cache.FileCache;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.model.Article;
import qsbk.app.utils.image.issue.TaskExecutor;
import qsbk.app.utils.image.issue.TaskExecutor.Task;

public class ListViewHelper {
    public static final long DEFAULT_PAGE_IGNORE_INTERVAL = 60000;
    public static final long DEFAULT_TIPS_TO_REFRESH_INTERVAL = 300000;
    private static final String a = ListViewHelper.class.getName();
    private static final boolean b = DebugUtil.DEBUG;
    private static Boolean c = null;

    private static class a implements Task {
        private final List<Object> a;
        private final Context b;
        private final String c;

        public a(List<Object> list, Context context, String str) {
            this.a = list;
            this.b = context;
            this.c = str;
        }

        public Object proccess() throws QiushibaikeException {
            File diskCacheDir = FileCache.getDiskCacheDir(this.b, this.c);
            if (diskCacheDir.exists()) {
                diskCacheDir.delete();
            }
            FileCache.writeFile(this.b, this.c, CollectionUtils.artilesToJsonString(this.a));
            return null;
        }

        public void success(Object obj) {
        }

        public void fail(Throwable th) {
        }
    }

    private ListViewHelper() {
    }

    private static String a(ListView listView, ListAdapter listAdapter) {
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        int count = listAdapter.getCount();
        int headerViewsCount = firstVisiblePosition - listView.getHeaderViewsCount();
        if (headerViewsCount < 0) {
            headerViewsCount = 0;
        }
        if (count > firstVisiblePosition) {
            Object item = listAdapter.getItem(headerViewsCount);
            if (b) {
                Log.e(a, String.format(listView.getContext().getClass().getSimpleName() + " count: %s, firstVisibleItem: %s, head count: %s", new Object[]{Integer.valueOf(count), Integer.valueOf(firstVisiblePosition), Integer.valueOf(listView.getHeaderViewsCount())}));
            }
            if (item instanceof Article) {
                return ((Article) item).id;
            }
            if (count > firstVisiblePosition + 1) {
                item = listAdapter.getItem(firstVisiblePosition + 1);
            } else if (firstVisiblePosition - 1 > 0) {
                item = listAdapter.getItem(firstVisiblePosition - 1);
            }
            if (item instanceof Article) {
                return ((Article) item).id;
            }
        }
        return null;
    }

    public static void onSaveListViewFirstVisibleItem(Context context, ListView listView, ListAdapter listAdapter, String str, boolean z) {
        if (context != null && listAdapter != null && listView != null) {
            String a = a(listView, listAdapter);
            if (b) {
                Log.e(a, context.getClass().getSimpleName() + " onSaveListViewFirstVisibleItem : " + a);
            }
            if (!TextUtils.isEmpty(a)) {
                Editor edit = context.getSharedPreferences("list_state", 0).edit();
                edit.putString(str, a);
                edit.apply();
            }
            if (z) {
                onSaveListAdapterDataIfNeed(context, listAdapter, str, a);
            }
        }
    }

    public static boolean onSaveListAdapterDataIfNeed(Context context, ListAdapter listAdapter, String str, String str2) {
        if (context == null || listAdapter == null) {
            return false;
        }
        if (listAdapter.getCount() < 30) {
            return false;
        }
        boolean z;
        List dataFromListAdapter = getDataFromListAdapter(listAdapter);
        int findIndex = findIndex(dataFromListAdapter, str2);
        int size = dataFromListAdapter.size();
        saveLastPage(context, str, findIndex % 30 == 0 ? findIndex / 30 : (findIndex / 30) + 1);
        a(findIndex, dataFromListAdapter);
        if (size > dataFromListAdapter.size()) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            TaskExecutor.getInstance().addTask(new a(dataFromListAdapter, context, str));
            if (b) {
                Iterator it = dataFromListAdapter.iterator();
                while (it.hasNext()) {
                    Object next = it.next();
                    Log.e(a, ((Article) next).id + " : " + ((Article) next).content);
                }
            }
        }
        return z;
    }

    private static void a(int i, List<Object> list) {
        int size = list.size();
        if (i > 25 && size > 50) {
            int min = Math.min((size - i) - 1, 15);
            for (size--; size > i + min; size--) {
                list.remove(size);
            }
            size = 30 - min;
            if (b) {
                Log.e(a, "headFromIndexCount -> " + size + " ,tailFromIndexCount " + min);
            }
            for (size = i - size; size >= 0; size--) {
                list.remove(size);
            }
        }
    }

    public static int findIndex(List<Object> list, String str) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (TextUtils.equals(((Article) list.get(i)).id, str)) {
                return i;
            }
        }
        return -1;
    }

    public static ArrayList<Object> getDataFromListAdapter(ListAdapter listAdapter) {
        int count = listAdapter.getCount();
        ArrayList<Object> arrayList = new ArrayList(count);
        for (int i = 0; i < count; i++) {
            Object item = listAdapter.getItem(i);
            if (item instanceof Article) {
                arrayList.add((Article) item);
            }
        }
        arrayList.trimToSize();
        return arrayList;
    }

    public static String getSaveArticleId(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return null;
        }
        return context.getSharedPreferences("list_state", 0).getString(str, "");
    }

    public static int getSaveSelection(Context context, String str, ArrayList<Object> arrayList) {
        int size = arrayList.size();
        String saveArticleId = getSaveArticleId(context, str);
        if (saveArticleId != null) {
            int i = 0;
            while (i < size) {
                if ((arrayList.get(i) instanceof Article) && ((Article) arrayList.get(i)).id.equals(saveArticleId)) {
                    return i;
                }
                i++;
            }
        }
        return 0;
    }

    public static void onRestoreListViewSelection(Context context, String str, ArrayList<Object> arrayList, ListView listView) {
        if (context != null && listView != null) {
            int saveSelection = getSaveSelection(context, str, arrayList);
            int headerViewsCount = listView.getHeaderViewsCount();
            if (b) {
                Log.e(a, context.getClass().getSimpleName() + " get from file, index : " + saveSelection + ", headCount : " + headerViewsCount);
            }
            saveSelection += headerViewsCount;
            if (saveSelection > 1) {
                listView.setSelectionFromTop(saveSelection, 0);
            }
        }
    }

    public static void setSelectionSaveble(Context context, boolean z) {
        if (context != null) {
            c = Boolean.valueOf(z);
            Editor edit = context.getSharedPreferences("list_state", 0).edit();
            edit.putBoolean("selection_saveble", z);
            edit.apply();
        }
    }

    public static boolean canSelectionSaveable(Context context) {
        return true;
    }

    public static boolean isOutSideInterval(Context context, String str, long j) {
        boolean z = true;
        if (context == null) {
            return false;
        }
        long j2 = context.getSharedPreferences("list_state", 0).getLong(a(str), 0);
        if (j < 0) {
            j = DEFAULT_TIPS_TO_REFRESH_INTERVAL;
        }
        if (b) {
            Log.e(a, String.format("save time: %s, interval: %s, now: %s", new Object[]{Long.valueOf(j2), Long.valueOf(j), Long.valueOf(System.currentTimeMillis())}));
        }
        if (j2 == 0 || Math.abs(System.currentTimeMillis() - j2) <= j) {
            z = false;
        }
        return z;
    }

    public static boolean isOutSizeIntervalOfPage(Context context, String str, long j) {
        boolean z = true;
        if (context == null) {
            return false;
        }
        long j2 = context.getSharedPreferences("list_state", 0).getLong(a(str), 0);
        if (j < 0) {
            j = 60000;
        }
        if (b) {
            Log.e(a, String.format("page save time: %s, interval: %s, now: %s", new Object[]{Long.valueOf(j2), Long.valueOf(j), Long.valueOf(System.currentTimeMillis())}));
        }
        if (j2 == 0 || Math.abs(System.currentTimeMillis() - j2) <= j) {
            z = false;
        }
        return z;
    }

    public static void saveLastUpdateTime(Context context, String str) {
        if (context != null && !TextUtils.isEmpty(str)) {
            context.getSharedPreferences("list_state", 0).edit().putLong(a(str), System.currentTimeMillis()).apply();
        }
    }

    private static String a(String str) {
        return a(str, "_last");
    }

    public static void saveLastPage(Context context, String str, int i) {
        if (context != null) {
            String b = b(str);
            if (b) {
                Log.e(a, "save last pageNo : " + i);
            }
            context.getSharedPreferences("list_state", 0).edit().putInt(b, i).apply();
        }
    }

    public static int getSaveLastPage(Context context, String str) {
        if (context == null) {
            return 0;
        }
        return Math.max(context.getSharedPreferences("list_state", 0).getInt(b(str), 0), 0);
    }

    private static String b(String str) {
        return a(str, "_page");
    }

    private static String a(String str, String str2) {
        return new StringBuffer().append(str).append(str2).toString();
    }
}
